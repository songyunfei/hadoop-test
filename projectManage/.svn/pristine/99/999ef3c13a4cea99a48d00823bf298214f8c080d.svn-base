package com.linkGap.projectManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkGap.projectManage.dao.SysRoleMapper;
import com.linkGap.projectManage.model.view.SysRoleView;
import com.linkGap.projectManage.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper SysRoleMapper;
	
	@Override
	public List<SysRoleView> selectAllRole() {
		return SysRoleMapper.selectAllRole();
	}

}
