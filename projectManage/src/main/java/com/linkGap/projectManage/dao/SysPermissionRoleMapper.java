package com.linkGap.projectManage.dao;

import com.linkGap.projectManage.model.entity.SysPermissionRole;

public interface SysPermissionRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermissionRole record);

    int insertSelective(SysPermissionRole record);

    SysPermissionRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermissionRole record);

    int updateByPrimaryKey(SysPermissionRole record);
}