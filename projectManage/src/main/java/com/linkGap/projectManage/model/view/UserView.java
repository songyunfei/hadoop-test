package com.linkGap.projectManage.model.view;

import java.io.Serializable;

public class UserView implements Serializable{

	private static final long	serialVersionUID	= 779906171985549693L;
	private Integer roleId;
	private Integer customerId;
	private Integer customerShopId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCustomerShopId() {
		return customerShopId;
	}
	public void setCustomerShopId(Integer customerShopId) {
		this.customerShopId = customerShopId;
	}
	
	
}
