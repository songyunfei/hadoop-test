package com.linkGap.projectManage.model.entity;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7405432573387090025L;

	private Integer sysUserId;

	private String username;

	private String mobile;

	private String realName;

	private String password;

	private String locked;

	/**
	 * 是否允许多点访问
	 */
	private Byte isAllowMultiAccess;

	private Date createTime;

	private Integer createUserId;

	private Date updateTime;

	private Integer updateUserId;

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked == null ? null : locked.trim();
	}

	public Byte getIsAllowMultiAccess() {
		return isAllowMultiAccess;
	}

	public void setIsAllowMultiAccess(Byte isAllowMultiAccess) {
		this.isAllowMultiAccess = isAllowMultiAccess;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

}