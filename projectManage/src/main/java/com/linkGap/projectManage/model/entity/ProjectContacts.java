package com.linkGap.projectManage.model.entity;

import java.io.Serializable;
import java.util.Date;

public class ProjectContacts implements Serializable {
    /**
	 * @author songyunfei
	 * 2018年1月16日
	 */
	private static final long serialVersionUID = 7354038302028707002L;

	private Integer customerContactId;

    private Integer projectInfoId;

    private String name;

    private String telphone;

    private String typeOfWork;

    private String status;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    public Integer getCustomerContactId() {
        return customerContactId;
    }

    public void setCustomerContactId(Integer customerContactId) {
        this.customerContactId = customerContactId;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork == null ? null : typeOfWork.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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