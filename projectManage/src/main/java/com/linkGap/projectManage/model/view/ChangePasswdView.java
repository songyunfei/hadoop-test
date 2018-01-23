package com.linkGap.projectManage.model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkGap.projectManage.model.entity.SysPermission;

/**
 * 用户修改密码成功后返回的有效信息内容
 * @author lichen
 *
 */
public class ChangePasswdView implements Serializable{

	private static final long	serialVersionUID	= 6627778313489161955L;
	private String userid;
	private String username;
	private String userStatus;
	private String rolename;
	private String roleStatus;
	private String customerId;
	private String customerShopId;
	
	@JsonIgnore
	private String roleId;
	
	private List<SysPermission> menus = new ArrayList<SysPermission>();
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerShopId() {
		return customerShopId;
	}
	public void setCustomerShopId(String customerShopId) {
		this.customerShopId = customerShopId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public List<SysPermission> getMenus() {
		return menus;
	}
	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}

}
