package com.linkGap.projectManage.model.view;


public class ProjectContactsView {
    private Integer customerContactId;

    private Integer projectInfoId;

    private String name;

    private String telphone;

    private String typeOfWork;
    
    private String status;


    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

    
}