package com.linkGap.projectManage.dao;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.entity.SysPermission;
import com.linkGap.projectManage.model.entity.SysRole;
import com.linkGap.projectManage.model.entity.SysUser;
import com.linkGap.projectManage.model.view.ChangePasswdView;
import com.linkGap.projectManage.model.view.LinkManInfoView;
import com.linkGap.projectManage.model.view.MailListView;
import com.linkGap.projectManage.model.view.UserInfoView;
import com.linkGap.projectManage.model.view.UserParamView;
import com.linkGap.projectManage.model.view.UserView;

public interface SysUserMapper {

	int deleteByPrimaryKey(Integer sysUserId);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(Integer sysUserId);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	SysUser findByUserName(String userName);

	List<SysRole> findRoleByUserName(String userName);
	

	SysUser findByUserNameAndSecondDomain(Map<String,String> map);

	SysRole findRoleByUserId(int userId);
	
    /**
     * 获取插入返回的id
     * 
     * @return int
     */
    int selectLastId();
    
    /**
     * 批量插入数据
     * 
     * @param sysUsers
     * @return int
     */
    int insertBatch(List<SysUser> sysUsers);
    
    /**
     * 根据用户名获取用户id
     * 
     * @param usernameS
     * @return List<Integer>
     */
    List<SysUser> getIdsByUserName(List<String> usernameS);
    
    
    
    int updatePasswordByUserId(Map<String, Object> map);
    
    /**
     * 删除用户
     * @param userName
     * @return
     */
    int deleteByUserNameOrUserId(Map<String, Object> map);
    
    
    /**
     * @author lichen
     * 通过用户名进行密码的修改操作
     * @param user
     * @return
     */
    int updatePasswdByUserName(SysUser user);
    
    /**
     * @author lichen
     * 通过用户名获得修改密码成功后的返回信息
     * @param userName
     * @return
     */
    ChangePasswdView getUserInfoByUserName(String userName);
    
    /**
     * @author lichen
     * 根据角色获得拥有的所有权限集合
     * @param roleId
     * @return
     */
    List<SysPermission> getPermissionByRoleId(Integer roleId);
    
    /**
     * @author lichen
     * 根据当前用户id获取用户的权限信息
     * @param userId
     * @return
     */
    UserView getUserRoleInfo(Integer userId);
    
    /**
     * @author lichen
     * 获得指定公司或门店下的所有施工队长或监理或设计人员或销售人员
     * @param id
     * @return
     */
    List<UserInfoView> getKindsUserRoleList(UserParamView view);
    
    

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
	 * @date: 2018年1月16日 下午2:31:55 
	 */
	LinkManInfoView getLinkManInfo(Integer sysUserId);
	
    /**
     * 根据条件查询通讯录
     * 
     * @param map
     * 
     * @return List<MailListView>
     */
    List<MailListView> selectUsersByParams(Map<String, Object> map);

}