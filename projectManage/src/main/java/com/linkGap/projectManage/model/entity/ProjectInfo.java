package com.linkGap.projectManage.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectInfo implements Serializable{
    /**
	 * @author songyunfei
	 * 2018年1月15日
	 */
	private static final long serialVersionUID = 3801372110036005939L;

	private Integer projectInfoId;

    private String projectType;

    private Integer customerShopId;

    private String buildingName;

    private String addressDetail;

    private String customerName;

    private String customerMobile;

    private String customerOpenId;

    private String contractNumber;

    private String contractShortImageUrls;

    private String contractImageUrls;

    private String constructionShortImageUrls;

    private String constructionImageUrls;

    private Integer saleMan;

    private Integer designMan;

    private Integer constructLeader;

    private Integer supervisor;

    private String currentProgress;

    private String score;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Byte deleted;

    private String status;

    private Byte projectStatus;
    
    
    private List<ProjectContacts> projectContactsList;
    
    private String coustomerId;  //公司Id


	/**
	 * @return the coustomerId
	 */
	public String getCoustomerId() {
		return coustomerId;
	}

	/**
	 * @param coustomerId the coustomerId to set
	 */
	public void setCoustomerId(String coustomerId) {
		this.coustomerId = coustomerId;
	}

	/**
	 * @return the projectContactsList
	 */
	public List<ProjectContacts> getProjectContactsList() {
		return projectContactsList;
	}

	/**
	 * @param projectContactsList the projectContactsList to set
	 */
	public void setProjectContactsList(List<ProjectContacts> projectContactsList) {
		this.projectContactsList = projectContactsList;
	}

	public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public Integer getCustomerShopId() {
        return customerShopId;
    }

    public void setCustomerShopId(Integer customerShopId) {
        this.customerShopId = customerShopId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    public String getCustomerOpenId() {
        return customerOpenId;
    }

    public void setCustomerOpenId(String customerOpenId) {
        this.customerOpenId = customerOpenId == null ? null : customerOpenId.trim();
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public String getContractShortImageUrls() {
        return contractShortImageUrls;
    }

    public void setContractShortImageUrls(String contractShortImageUrls) {
        this.contractShortImageUrls = contractShortImageUrls == null ? null : contractShortImageUrls.trim();
    }

    public String getContractImageUrls() {
        return contractImageUrls;
    }

    public void setContractImageUrls(String contractImageUrls) {
        this.contractImageUrls = contractImageUrls == null ? null : contractImageUrls.trim();
    }

    public String getConstructionShortImageUrls() {
        return constructionShortImageUrls;
    }

    public void setConstructionShortImageUrls(String constructionShortImageUrls) {
        this.constructionShortImageUrls = constructionShortImageUrls == null ? null : constructionShortImageUrls.trim();
    }

    public String getConstructionImageUrls() {
        return constructionImageUrls;
    }

    public void setConstructionImageUrls(String constructionImageUrls) {
        this.constructionImageUrls = constructionImageUrls == null ? null : constructionImageUrls.trim();
    }

    public Integer getSaleMan() {
        return saleMan;
    }

    public void setSaleMan(Integer saleMan) {
        this.saleMan = saleMan;
    }

    public Integer getDesignMan() {
        return designMan;
    }

    public void setDesignMan(Integer designMan) {
        this.designMan = designMan;
    }

    public Integer getConstructLeader() {
        return constructLeader;
    }

    public void setConstructLeader(Integer constructLeader) {
        this.constructLeader = constructLeader;
    }

    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }

    public String getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(String currentProgress) {
        this.currentProgress = currentProgress == null ? null : currentProgress.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
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

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	/**
	 * @return the projectStatus
	 */
	public Byte getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(Byte projectStatus) {
		this.projectStatus = projectStatus;
	}

  
}