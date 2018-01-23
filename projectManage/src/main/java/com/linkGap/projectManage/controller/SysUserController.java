package com.linkGap.projectManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.SysRoleUser;
import com.linkGap.projectManage.model.entity.SysUser;
import com.linkGap.projectManage.model.entity.UserRelation;
import com.linkGap.projectManage.model.view.ChangePasswdView;
import com.linkGap.projectManage.model.view.LinkManInfoView;
import com.linkGap.projectManage.model.view.MailListView;
import com.linkGap.projectManage.model.view.SysRoleView;
import com.linkGap.projectManage.model.view.UserInfoView;
import com.linkGap.projectManage.model.view.UserParamView;
import com.linkGap.projectManage.model.view.UserView;
import com.linkGap.projectManage.service.SysRoleService;
import com.linkGap.projectManage.service.SysUserService;
import com.linkGap.projectManage.utils.PasswordHelper;

@Controller
@RequestMapping("/user")
public class SysUserController {
	
	private Logger logger = Logger.getLogger(SysUserController.class);
	
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@Autowired
	private SysRoleService sysRoleService;
	
	

	/**
	 * @author lichen
	 * 修改密码   成功后并返回对应用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	@OperationLogAnotation(moduleName="修改密码",operation="修改密码并返回用户的一些基本信息")
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResultUtil changePassword(@RequestParam(value="username")String username,@RequestParam(value="password")String password){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			SysUser user = new SysUser();
			user.setUsername(username);
			PasswordHelper passwordHelper = new PasswordHelper();
			SysUser userInfo = new SysUser();
			userInfo.setUsername(username);
			userInfo.setPassword(password);
			passwordHelper.encryptPassword(userInfo);
			user.setPassword(userInfo.getPassword());
			int updatePasswdFlag = sysUserService.updatePasswdByUserName(user);
			if(updatePasswdFlag>0){//表示更新密码成功
				returnResultUtil.setResultCode("00");
				returnResultUtil.setResultMsg("登录成功！");
				ChangePasswdView userInfoByUserObj = sysUserService.getUserInfoByUserName(username);
				returnResultUtil.setResultValue(userInfoByUserObj);
			}else{//表示更新密码失败
				returnResultUtil.setResultCode("01");
				returnResultUtil.setResultMsg("用户已经被锁定不能登录，请与管理员联系！");
			}
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("用户已经被锁定不能登录，请与管理员联系！");
			logger.error("修改密码失败！", e);
		}
		return returnResultUtil;
	}
	
	/**
	 * @author lichen
	 * 施工队长筛选查询
	 * @param userId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/constructLeader/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil getConstructLeaderList(@RequestParam(value="userId")Integer userId,@RequestParam(value="customerId")Integer customerId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			UserView userRoleInfo = sysUserService.getUserRoleInfo(userId);
			Integer roleId =userRoleInfo.getRoleId();
			Integer id=null;
			if(1==roleId){//公司权限
				id=userRoleInfo.getCustomerId();
			}else if(2==roleId){//店长权限
				id=userRoleInfo.getCustomerShopId();
			}
			UserParamView view = new UserParamView();
			view.setId(id);
			view.setRoleId(roleId);
			view.setRoleType(3);//施工队长
			List<UserInfoView> constructLeaderList = sysUserService.getKindsUserRoleList(view);
			returnResultUtil.setResultCode("00");
			returnResultUtil.setResultMsg("成功！");
			returnResultUtil.setResultValue(constructLeaderList);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("error！");
			logger.error("施工队长筛选查询", e);
		}
		return returnResultUtil;
	}
	
	
	/**
	 * @author lichen
	 * 监理筛选查询
	 * @param userId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/supervisor/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil getSupervisorList(@RequestParam(value="userId")Integer userId,@RequestParam(value="customerId")Integer customerId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			UserView userRoleInfo = sysUserService.getUserRoleInfo(userId);
			Integer roleId =userRoleInfo.getRoleId();
			Integer id=null;
			if(1==roleId){//公司权限
				id=userRoleInfo.getCustomerId();
			}else if(2==roleId){//店长权限
				id=userRoleInfo.getCustomerShopId();
			}
			UserParamView view = new UserParamView();
			view.setId(id);
			view.setRoleId(roleId);
			view.setRoleType(5);//监理
			List<UserInfoView> constructLeaderList = sysUserService.getKindsUserRoleList(view);
			returnResultUtil.setResultCode("00");
			returnResultUtil.setResultMsg("成功！");
			returnResultUtil.setResultValue(constructLeaderList);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("error！");
			logger.error("监理筛选查询", e);
		}
		return returnResultUtil;
	}
	
	
	
	/**
	 * @author lichen
	 * 设计人员筛选查询
	 * @param userId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/designMan/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil getDesignManList(@RequestParam(value="userId")Integer userId,@RequestParam(value="customerId")Integer customerId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			UserView userRoleInfo = sysUserService.getUserRoleInfo(userId);
			Integer roleId =userRoleInfo.getRoleId();
			Integer id=null;
			if(1==roleId){//公司权限
				id=userRoleInfo.getCustomerId();
			}else if(2==roleId){//店长权限
				id=userRoleInfo.getCustomerShopId();
			}
			UserParamView view = new UserParamView();
			view.setId(id);
			view.setRoleId(roleId);
			view.setRoleType(4);//设计
			List<UserInfoView> constructLeaderList = sysUserService.getKindsUserRoleList(view);
			returnResultUtil.setResultCode("00");
			returnResultUtil.setResultMsg("成功！");
			returnResultUtil.setResultValue(constructLeaderList);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("error！");
			logger.error("设计筛选查询", e);
		}
		return returnResultUtil;
	}
	
	
	/**
	 * @author lichen
	 * 销售筛选查询
	 * @param userId
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value="/saleMan/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil getSaleManList(@RequestParam(value="userId")Integer userId,@RequestParam(value="customerId")Integer customerId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			UserView userRoleInfo = sysUserService.getUserRoleInfo(userId);
			Integer roleId =userRoleInfo.getRoleId();
			Integer id=null;
			if(1==roleId){//公司权限
				id=userRoleInfo.getCustomerId();
			}else if(2==roleId){//店长权限
				id=userRoleInfo.getCustomerShopId();
			}
			UserParamView view = new UserParamView();
			view.setId(id);
			view.setRoleId(roleId);
			view.setRoleType(6);//销售
			List<UserInfoView> constructLeaderList = sysUserService.getKindsUserRoleList(view);
			returnResultUtil.setResultCode("00");
			returnResultUtil.setResultMsg("成功！");
			returnResultUtil.setResultValue(constructLeaderList);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("error！");
			logger.error("销售筛选查询", e);
		}
		return returnResultUtil;
	}
	
	
	/**
	 * 修改手机号
	 * 
	 * @param userId 用户id
	 * @param mobile 手机号码
	 * 
	 * @return  ReturnResultUtil
	 */
	@OperationLogAnotation(moduleName="修改用户手机号码",operation="修改用户手机号码")
	@RequestMapping(value="/updateMobile",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResultUtil updateMobile(@RequestParam(value="userId")String userId,@RequestParam(value="mobile")String mobile){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		String regex = "^1[0-9]{10}$";
		try{
			SysUser sysUser = new SysUser();
			if(StringUtil.isEmpty(mobile)){
				returnResultUtil.setResultCode("01");
				returnResultUtil.setResultMsg("手机号码不能为空");
				return returnResultUtil;
			}else{
				//匹配用户的手机号，正则校验
				 Pattern p = Pattern.compile(regex);
				 Matcher m = p.matcher(mobile);
				 if(!m.matches()){
					returnResultUtil.setResultCode("02");
					returnResultUtil.setResultMsg("手机号码不符合指定格式");
					return returnResultUtil; 
				 }
			}
			sysUser.setSysUserId(Integer.valueOf(userId));
			sysUser.setMobile(mobile);
			sysUserService.updateMobileBySysUserId(sysUser);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("修改用户手机号码失败!");
			logger.error("修改用户手机号码失败！", e);
		}
		return returnResultUtil;
	}
	
	
	/**
	 * 获取系统所有角色信息
	 * 
	 * 
	 * @return  ReturnResultUtil
	 */
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil roles(){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			List<SysRoleView> sysRoles = sysRoleService.selectAllRole();
			returnResultUtil.setResultValue(sysRoles);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("获取系统角色信息失败!");
			logger.error("获取系统角色信息失败！", e);
		}
		return returnResultUtil;
	}
	
	/**
	 * 根据userId查找通讯录信息
	 * @author xujie
	 * @date: 2018年1月16日 下午2:25:50 
	 */
	@GetMapping(value="/{sysUserId}")
	@ResponseBody
	public ReturnResultUtil getLinkManInfo(@PathVariable("sysUserId")Integer sysUserId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		try{
			LinkManInfoView userInfo = sysUserService.getLinkManInfo(sysUserId);
			returnResultUtil.setResultValue(userInfo);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("获取系统角色信息失败!");
			logger.error("获取系统角色信息失败！", e);
		}
		return returnResultUtil;
	}
	
	/**
	 * 删除员工
	 * @author xujie
	 * @date: 2018年1月16日 下午2:25:50 
	 */
	@PutMapping(value="/{sysUserId}")
	@ResponseBody
	@OperationLogAnotation(moduleName="通讯录模块",operation="删除员工")
	public ReturnResultUtil delLinkManInfo(@PathVariable("sysUserId")Integer sysUserId,@RequestParam(value="currentUserId")Integer currentUserId){
		try{
			String msg = sysUserService.delLinkManInfo(sysUserId,currentUserId);
			if("ok".equals(msg)){
				return new ReturnResultUtil("00", "成功");
			}else{
				return new ReturnResultUtil("01", msg);
			}
		}catch(Exception e){
			return new ReturnResultUtil("02", e.toString());
		}
	}
	
	/**
	 * 通讯录添加员工信息
	 * 
	 * @param username
	 * @param mobile
	 * @param createUserId
	 * @param customerId
	 * @param password
	 * @param locked
	 * @param sysRoleId
	 * @param customerShopId
	 * @return ReturnResultUtil
	 */
	@RequestMapping(value="/addEmployee",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResultUtil addEmployee(@RequestParam(value="username")String username, @RequestParam(value="realName")String realName, @RequestParam(value="mobile")String mobile, @RequestParam(value="createUserId")String createUserId, @RequestParam(value="customerId")String customerId,
			@RequestParam(value="password")String password, @RequestParam(value="locked")String locked, @RequestParam(value="sysRoleId")String sysRoleId, @RequestParam(value="customerShopId")String customerShopId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		PasswordHelper passwordHelper = new PasswordHelper();
		try{
			if(StringUtil.isEmpty(username)||StringUtil.isEmpty(realName)||StringUtil.isEmpty(mobile)||StringUtil.isEmpty(createUserId)||StringUtil.isEmpty(customerId)||StringUtil.isEmpty(password)||StringUtil.isEmpty(locked)||StringUtil.isEmpty(sysRoleId)||StringUtil.isEmpty(customerShopId)){
				returnResultUtil.setResultCode("01");
				returnResultUtil.setResultMsg("参数不能为空");
				return returnResultUtil;
			}
			//判断用户名是否存在
			SysUser user = sysUserService.findByUserName(username);
			if(user != null){
				returnResultUtil.setResultCode("02");
				returnResultUtil.setResultMsg("用户名重复");
				return returnResultUtil;
			}
			SysUser sysUser = new SysUser();
			sysUser.setLocked(locked);
			sysUser.setMobile(mobile);
			sysUser.setUsername(username);
			sysUser.setPassword(password);
			passwordHelper.encryptPassword(sysUser);
			sysUser.setPassword(sysUser.getPassword());
			sysUser.setCreateTime(new Date());
			sysUser.setUpdateTime(new Date());
			sysUser.setRealName(realName);
			sysUser.setCreateUserId(Integer.valueOf(createUserId));
			sysUser.setUpdateUserId(Integer.valueOf(createUserId));
			Byte mulTiAccess = 0;
			sysUser.setIsAllowMultiAccess(mulTiAccess);
			SysRoleUser roleUser = new SysRoleUser();
			roleUser.setSysRoleId(Integer.parseInt(sysRoleId));
			UserRelation relation = new UserRelation();
			relation.setCustomerId(Integer.parseInt(customerId));
			relation.setCustomerShopId(Integer.parseInt(customerShopId));
			sysUserService.saveStaff(sysUser, roleUser, relation);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("通讯录添加员工信息失败!");
			logger.error("通讯录添加员工信息失败！", e);
			e.printStackTrace();
		}
		return returnResultUtil;
	}
	
	/**
	 * 交接工作
	 * @author xujie
	 * @date: 2018年1月16日 下午2:25:50 
	 */
	@GetMapping(value="/handoverWork")
	@ResponseBody
	@OperationLogAnotation(moduleName="通讯录模块",operation="交接工作")
	public ReturnResultUtil handoverWork(@RequestParam(value="handoverId")Integer handoverId,@RequestParam(value="beHandoverId")Integer beHandoverId,@RequestParam(value="userId")Integer userId){
		try{
			String msg=sysUserService.handoverWork(handoverId,beHandoverId,userId);
			if("ok".equals(msg)){
				return new ReturnResultUtil("00", "成功");
			}else{
				return new ReturnResultUtil("02", msg);
			}
			
		}catch(Exception e){
			return new ReturnResultUtil("01", "交接失败");
		}
	}

	
	
	/**
	 * 获取用户通讯录
	 * 
	 * @param customerId
	 * @param searchContent
	 * @param sysRoleId
	 * @return ReturnResultUtil
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil list(@RequestParam(value="customerId")String customerId,@RequestParam(value="searchContent")String searchContent, @RequestParam(value="sysRoleId")String sysRoleId){
		ReturnResultUtil returnResultUtil=new ReturnResultUtil();
		PasswordHelper passwordHelper = new PasswordHelper();
		Map<String, Object>  map = new HashMap<>();
		try{
			if(StringUtil.isEmpty(customerId)){
				returnResultUtil.setResultCode("01");
				returnResultUtil.setResultMsg("公司id参数不能为空");
				return returnResultUtil;
			}
			map.put("customerId", customerId);
			map.put("searchContent", searchContent);
			map.put("sysRoleId", sysRoleId);
			List<MailListView> views = sysUserService.selectUserByParams(map);
			returnResultUtil.setResultValue(views);
		}catch(Exception e){
			returnResultUtil.setResultCode("01");
			returnResultUtil.setResultMsg("获取用户通讯录信息失败!");
			logger.error("获取用户通讯录信息失败！", e);
			e.printStackTrace();
		}
		return returnResultUtil;
	}
	
}
