package com.linkGap.projectManage.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.linkGap.projectManage.dao.LoginLogMapper;
import com.linkGap.projectManage.dao.ProjectHandoverDetailMapper;
import com.linkGap.projectManage.dao.ProjectInfoMapper;
import com.linkGap.projectManage.dao.SysRoleUserMapper;
import com.linkGap.projectManage.dao.SysUserMapper;
import com.linkGap.projectManage.dao.UserRelationMapper;
import com.linkGap.projectManage.model.entity.LoginLog;
import com.linkGap.projectManage.model.entity.ProjectHandoverDetail;
import com.linkGap.projectManage.model.entity.ProjectInfo;
import com.linkGap.projectManage.model.entity.SysPermission;
import com.linkGap.projectManage.model.entity.SysRole;
import com.linkGap.projectManage.model.entity.SysRoleUser;
import com.linkGap.projectManage.model.entity.SysUser;
import com.linkGap.projectManage.model.entity.UserRelation;
import com.linkGap.projectManage.model.view.ChangePasswdView;
import com.linkGap.projectManage.model.view.LinkManInfoView;
import com.linkGap.projectManage.model.view.MailListView;
import com.linkGap.projectManage.model.view.UserInfoView;
import com.linkGap.projectManage.model.view.UserParamView;
import com.linkGap.projectManage.model.view.UserView;
import com.linkGap.projectManage.service.SysUserService;
import com.linkGap.projectManage.utils.AddressUtils;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Autowired
	private ProjectInfoMapper  projectInfoMapper;
	
	@Autowired
	private UserRelationMapper userRelationMapper;
	
	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;
	
	@Autowired
	private ProjectHandoverDetailMapper projectHandoverDetailMapper;

	public void updatePasswordByUserId(Map<String, Object> map) throws Exception {
		sysUserMapper.updatePasswordByUserId(map);
	}

	@Override
	public SysUser findByUserName(String userName) {
		return sysUserMapper.findByUserName(userName);
	}
	@Override
	public SysRole findRoleByUserId(int userId) {
		return sysUserMapper.findRoleByUserId(userId);
	}

	@Override
	public int updatePasswdByUserName(SysUser user) {
		// TODO Auto-generated method stub
		return sysUserMapper.updatePasswdByUserName(user);
	}

	@Override
	public ChangePasswdView getUserInfoByUserName(String userName) {
		// TODO Auto-generated method stub
		ChangePasswdView userInfoByUserNameObj = sysUserMapper.getUserInfoByUserName(userName);
		String roleId = userInfoByUserNameObj.getRoleId();
		if (!"".equals(roleId) && roleId != null) {
			List<SysPermission> permissionByRoleId = sysUserMapper.getPermissionByRoleId(Integer.parseInt(roleId));
			userInfoByUserNameObj.setMenus(permissionByRoleId);
		}
		return userInfoByUserNameObj;
	}

	@Override
	public UserView getUserRoleInfo(Integer userId) {
		// TODO Auto-generated method stub
		return sysUserMapper.getUserRoleInfo(userId);
	}

	@Override
	public List<UserInfoView> getKindsUserRoleList(UserParamView view) {
		// TODO Auto-generated method stub
		return sysUserMapper.getKindsUserRoleList(view);
	}

	@Override
	public int saveLoginLog(int userId, String uniqueDeviceIdentifier, String ip) {
		LoginLog record = new LoginLog();
		record.setSysUserId(userId);
		String address = "";
		try {
			 address = AddressUtils.getAddresses("ip=" + ip, "utf-8");
			 record.setLoginAddress(address);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		record.setLoginTime(new Date());
		record.setLoginIp(ip);
		record.setUniqueDeviceIdentifier(uniqueDeviceIdentifier);
		return loginLogMapper.insert(record);
	}
	
    /**
     * 
    * @Title: selectLastBySysUserId  
    * @Description: 获取用户的最后一次登录记录 
    * @param sysUserId
    * @return    参数  
    * LoginLog    返回类型
     */
    public LoginLog selectLastBySysUserId(Integer sysUserId){
    	return loginLogMapper.selectLastBySysUserId(sysUserId);
    }
	@Override
	public int updateMobileBySysUserId(SysUser user) {
		return sysUserMapper.updateMobileBySysUserId(user);
	}
	
	/** 
	 * @author xujie
	 * @date: 2018年1月16日 下午2:26:48 
	 */
	@Override
	public LinkManInfoView getLinkManInfo(Integer sysUserId) {
		
		return sysUserMapper.getLinkManInfo(sysUserId);
	}

	/** 
	 * @author xujie
	 * @date: 2018年1月16日 下午2:59:24 
	 */
	@Override
	public String delLinkManInfo(Integer sysUserId,Integer currentUserId) {
		//验证该人员是否有项目未交接
		Integer num=projectInfoMapper.checkIsHandover(sysUserId);
		if(num>0){
			return "请先交接工作！";
		}
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("sysUserId", sysUserId);
		map.put("userId",currentUserId);
		sysUserMapper.deleteByUserNameOrUserId(map);
		return "ok";
	}

	@Override
	public int saveStaff(SysUser sysUser, SysRoleUser sysRoleUser, UserRelation relation) {
		//保存用户信息，返回用户id
		sysUserMapper.insert(sysUser);
		sysRoleUser.setSysUserId(sysUser.getSysUserId());
		sysRoleUserMapper.insert(sysRoleUser);
		relation.setSysUserId(sysUser.getSysUserId());
		userRelationMapper.insert(relation);
		return relation.getId();
	}
	
		/** 
	 * @author xujie
	 * @date: 2018年1月16日 下午4:33:15 
	 */
	@Override
	public String handoverWork(Integer handoverId, Integer beHandoverId,Integer userId) {
		//查询交接人的角色
		Integer roleId=sysRoleUserMapper.querySysRoleId(handoverId);
		Map<String,Integer> map =new HashMap<String,Integer>();
		map.put("beHandoverdMan", beHandoverId);
		map.put("userId", userId);
		if(roleId==3){
			map.put("constructLeader", handoverId);
		}else if(roleId==4){
			map.put("designMan", handoverId);
		}else if(roleId==5){
			map.put("supervisor", handoverId);
		}else if(roleId==6){
			map.put("saleMan", handoverId);
		}else{
			return  "该人员角色无须交接工作";
		}
		//校验被交接人角色与交接人角色是否相同
		Integer beHandoverRoleId=sysRoleUserMapper.querySysRoleId(beHandoverId);
		if(roleId!=beHandoverRoleId){
			return  "被交接人员与交接人不是同一角色";
		}
		
		List<ProjectInfo> relatedMen=projectInfoMapper.queryProjectRelatedMan(map);
		if(relatedMen==null||relatedMen.size()==0){
			return  "该人员手上没有可以交接的工作";
		}
		//封装交接日志数据
		List<ProjectHandoverDetail> logs=new ArrayList<ProjectHandoverDetail>();
		for (ProjectInfo projectInfo : relatedMen) {
			ProjectHandoverDetail log=new ProjectHandoverDetail();
			log.setProjectInfoId(projectInfo.getProjectInfoId());
			log.setOldConstructLeader(projectInfo.getConstructLeader());
			log.setOldDesignMan(projectInfo.getDesignMan());
			log.setOldSaleMan(projectInfo.getSaleMan());
			log.setOldSupervisor(projectInfo.getSupervisor());
			if(roleId==3){
				log.setNewConstructLeader(beHandoverId);
			}else if(roleId==4){
				log.setNewDesignMan(beHandoverId);
			}else if(roleId==5){
				log.setNewSupervisor(beHandoverId);
			}else if(roleId==6){
				log.setNewSaleMan(beHandoverId);
			}
			log.setCreateTime(new Date());
			log.setCreateUserId(userId);
			log.setStatus("1");
			logs.add(log);
		}
		//更新项目中角色Id（交接）
		projectInfoMapper.handoverWork(map);
		//插入交接日志
		projectHandoverDetailMapper.insertBatch(logs);
		return "ok";
		
	}

	@Override
	public List<MailListView> selectUserByParams(Map<String, Object> map) {
		return sysUserMapper.selectUsersByParams(map);
	}
}
