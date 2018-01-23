package com.linkGap.projectManage.model.view;

import java.util.Date;
import java.util.List;

public class ProjectStepLogView {

	private Integer projectStepLogId;

    private String operateTitle;

    private String operateContent;

    private Date rectifyDate;
    
    private List<ProjectStepLogsPicView> picList;

	public List<ProjectStepLogsPicView> getPicList() {
		return picList;
	}

	public void setPicList(List<ProjectStepLogsPicView> picList) {
		this.picList = picList;
	}

	public Integer getProjectStepLogId() {
		return projectStepLogId;
	}

	public void setProjectStepLogId(Integer projectStepLogId) {
		this.projectStepLogId = projectStepLogId;
	}

	public String getOperateTitle() {
		return operateTitle;
	}

	public void setOperateTitle(String operateTitle) {
		this.operateTitle = operateTitle;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public Date getRectifyDate() {
		return rectifyDate;
	}

	public void setRectifyDate(Date rectifyDate) {
		this.rectifyDate = rectifyDate;
	}
    
    
}
