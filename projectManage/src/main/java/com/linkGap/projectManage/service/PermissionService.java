package com.linkGap.projectManage.service;

import java.util.List;

import com.linkGap.projectManage.model.entity.SysPermission;

public interface PermissionService {

	public List<SysPermission> findAll();

	public List<SysPermission> findByAdminUserId(Integer userId);
}
