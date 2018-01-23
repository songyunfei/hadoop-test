package com.linkGap.projectManage.model.view;

import java.io.Serializable;

public class LinkManInfoView implements Serializable{
	
	private static final long serialVersionUID = 8136153185253117075L;

	private String username;
	
	private String realName;
	
	private String mobile;
	
	private String sysRoleName;
	
	private Integer sysRoleId ;
	
	private String shopName;
	
	private String customerShopId;
	
	private String locked;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

	public Integer getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	public String getCustomerShopId() {
		return customerShopId;
	}

	public void setCustomerShopId(String customerShopId) {
		this.customerShopId = customerShopId;
	}
}
