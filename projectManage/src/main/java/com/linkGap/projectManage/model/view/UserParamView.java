package com.linkGap.projectManage.model.view;

import java.io.Serializable;

public class UserParamView implements Serializable{

	private static final long	serialVersionUID	= 2649243481947375816L;

	private Integer id;//K店的id或者公司id
	private Integer roleType;//角色id  施工队长3   或监理 5   或设计人员 4   或销售人员 6
	private Integer roleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
