package com.linkGap.projectManage.service;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.entity.LoginLog;
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

/**
 * 系统用户管理
 * 
 * @author cxh
 *
 */
public interface SysUserService {

	SysUser findByUserName(String userName);

	SysRole findRoleByUserId(int userId);

	/**
	 * @author lichen 通过用户名进行密码的修改操作
	 * @param user
	 * @return
	 */
	int updatePasswdByUserName(SysUser user);

	/**
	 * @author lichen 通过用户名获得修改密码成功后的返回信息
	 * @param userName
	 * @return
	 */
	ChangePasswdView getUserInfoByUserName(String userName);

	/**
	 * @author lichen 根据当前用户id获取用户的权限信息
	 * @param userId
	 * @return
	 */
	UserView getUserRoleInfo(Integer userId);

	/**
	 * @author lichen 获得指定公司或门店下的所有施工队长或监理或设计人员或销售人员
	 * @param id
	 * @return
	 */
	List<UserInfoView> getKindsUserRoleList(UserParamView view);

	/**
	 * 
	 * @Title: saveLoginLog
	 * @Description: 保存用户登录日志
	 * @param userId
	 * @return 参数 int 返回类型
	 */
	int saveLoginLog(int userId, String uniqueDeviceIdentifier, String ip);
	
   /**
    * 
    * @Title: selectLastBySysUserId  
    * @Description: 获取用户的最后一次登录记录 
    * @param sysUserId
    * @return    参数  
    * LoginLog    返回类型
     */
    LoginLog selectLastBySysUserId(Integer sysUserId);

    
    /**
     * 根据用户id修改用户的手机号码
     * 
     * @param user
     * @return int 
     */
    int updateMobileBySysUserId(SysUser user);
    
    /**
	 * 根据userId查找通讯录信息
	 * @author xujie
	 * @date: 2018年1月16日 下午2:25:50 
	 */
	LinkManInfoView getLinkManInfo(Integer sysUserId);

	/**
	 * 删除联系人
	 * @author xujie
	 * @param currentUserId 
	 * @date: 2018年1月16日 下午2:59:05 
	 */
	String delLinkManInfo(Integer sysUserId, Integer currentUserId);
	
    /**
     * 添加员工
     * 
     * @param sysUser 系统用户信息
     * @param sysRoleUser 用户角色信息
     * @param relation 用户和公司,门店关系
     * @return int
     */
    int saveStaff(SysUser sysUser, SysRoleUser sysRoleUser, UserRelation relation);
    
    /**
	 * 交接工作
	 * @author xujie
	 * @param userId 
	 * @date: 2018年1月16日 下午4:33:01 
	 */
    String handoverWork(Integer handoverId, Integer beHandoverId, Integer userId);
    
    
    /**
     * 根据条件获取通讯录列表
     * 
     * @param map
     * 
     * @return List<SysUser>
     */
    List<MailListView> selectUserByParams(Map<String, Object> map);
    
    
}
