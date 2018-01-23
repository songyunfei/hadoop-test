package com.linkGap.projectManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkGap.projectManage.dao.SysPermissionMapper;
import com.linkGap.projectManage.model.entity.SysPermission;
import com.linkGap.projectManage.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	SysPermissionMapper sysPermissionMapper;

	public List<SysPermission> findAll() {
		return sysPermissionMapper.findAll();
	}

	public List<SysPermission> findByAdminUserId(Integer userId) {
		return sysPermissionMapper.findByAdminUserId(userId);
	}
}
