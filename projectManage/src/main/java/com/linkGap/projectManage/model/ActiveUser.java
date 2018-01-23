
package com.linkGap.projectManage.model;

import java.util.List;

import com.linkGap.projectManage.model.entity.SysPermission;

/**
 * @author renhengli
 * @date 2017年11月10日
 * @version 1.0
 */
public class ActiveUser implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5055006704847483789L;
	private Integer userid;// 用户id（主键）
	private String username;// 用户名称
	private String userStatus;// 用户状态
	private String rolename;// 角色名称
	private String roleStatus;// 角色状态
	// private String secondDomain;// 二级域名
	private List<SysPermission> menus;// 菜单
	private Integer customerId;// 当前登录人所属公司
	private Integer customerShopId;//当前登录人所属门店

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public List<SysPermission> getMenus() {
		return menus;
	}

	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
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

	// public String getSecondDomain() {
	// return secondDomain;
	// }
	//
	// public void setSecondDomain(String secondDomain) {
	// this.secondDomain = secondDomain;
	// }

	@Override
	public String toString() {
		return "username=" + username;
	}

	public Integer getCustomerShopId() {
		return customerShopId;
	}

	public void setCustomerShopId(Integer customerShopId) {
		this.customerShopId = customerShopId;
	}
}
