package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.SysRoleUser;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleUser record);
    
    int insertBatch(List<SysRoleUser> sysRoleUsers);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
    
    int updateByParams(SysRoleUser record);

	/**
	 * 查询角色id
	 * @author xujie
	 * @date: 2018年1月16日 下午4:55:34 
	 */
	Integer querySysRoleId(Integer userId);
}