package com.linkGap.projectManage.model.view;

import java.io.Serializable;
import java.util.Date;

public class ProjectListView implements Serializable{
   
	/**
	 * @author songyunfei
	 * 2018年1月12日
	 */
	private static final long serialVersionUID = 7775233081133068604L;
	private String bulidingName;//小区的名称
	private Date createTime;//工程创建时间
	private String currentProgress;//工程进度
	private String projectType ; //工程类型
	private String customerShopName;//门店名称
	private String constructLeader;//施工队长
	private String supervisor;//监理人员
	private String saleMan;//销售人员
	private String colorShow;//5个颜色（蓝，红，黄，绿 ，灰）
	private Integer projectInfoId;//工程id
	private String customerName;// 客户名
	private String score;
	
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the bulidingName
	 */
	public String getBulidingName() {
		return bulidingName;
	}
	/**
	 * @param bulidingName the bulidingName to set
	 */
	public void setBulidingName(String bulidingName) {
		this.bulidingName = bulidingName;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the currentProgress
	 */
	public String getCurrentProgress() {
		return currentProgress;
	}
	/**
	 * @param currentProgress the currentProgress to set
	 */
	public void setCurrentProgress(String currentProgress) {
		this.currentProgress = currentProgress;
	}
	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}
	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	/**
	 * @return the customerShopName
	 */
	public String getCustomerShopName() {
		return customerShopName;
	}
	/**
	 * @param customerShopName the customerShopName to set
	 */
	public void setCustomerShopName(String customerShopName) {
		this.customerShopName = customerShopName;
	}
	/**
	 * @return the constructLeader
	 */
	public String getConstructLeader() {
		return constructLeader;
	}
	/**
	 * @param constructLeader the constructLeader to set
	 */
	public void setConstructLeader(String constructLeader) {
		this.constructLeader = constructLeader;
	}
	/**
	 * @return the supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}
	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	/**
	 * @return the saleMan
	 */
	public String getSaleMan() {
		return saleMan;
	}
	/**
	 * @param saleMan the saleMan to set
	 */
	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}
	/**
	 * @return the colorShow
	 */
	public String getColorShow() {
		return colorShow;
	}
	/**
	 * @param colorShow the colorShow to set
	 */
	public void setColorShow(String colorShow) {
		this.colorShow = colorShow;
	}
	/**
	 * @return the projectInfoId
	 */
	public Integer getProjectInfoId() {
		return projectInfoId;
	}
	/**
	 * @param projectInfoId the projectInfoId to set
	 */
	public void setProjectInfoId(Integer projectInfoId) {
		this.projectInfoId = projectInfoId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
}
