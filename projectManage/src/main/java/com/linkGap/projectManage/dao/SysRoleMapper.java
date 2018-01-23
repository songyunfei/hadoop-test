package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.SysRole;
import com.linkGap.projectManage.model.view.SysRoleView;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer sysRoleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer sysRoleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * 获取所有的系统角色
     * 
     * @return List<SysRole>
     */
    List<SysRoleView> selectAllRole();
}