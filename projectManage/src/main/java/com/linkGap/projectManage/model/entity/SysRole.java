package com.linkGap.projectManage.model.entity;

import java.io.Serializable;

public class SysRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3228189668958527431L;

	private Integer sysRoleId;

    private String name;

    private String description;

    private String status;

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}