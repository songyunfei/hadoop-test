package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.SysPermission;

public interface SysPermissionMapper {

	public List<SysPermission> findAll();

	public List<SysPermission> findByAdminUserId(Integer userId);
	
}