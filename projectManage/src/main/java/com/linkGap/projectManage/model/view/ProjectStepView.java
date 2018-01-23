package com.linkGap.projectManage.model.view;

import java.math.BigDecimal;

public class ProjectStepView {

	private Integer stepId;

    private Integer sysRoleId;

    private String stepName;

    private Integer stepOrder;

    private BigDecimal stepPercent;

    private String projectStatus;

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public Integer getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public Integer getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

	public BigDecimal getStepPercent() {
		return stepPercent;
	}

	public void setStepPercent(BigDecimal stepPercent) {
		this.stepPercent = stepPercent;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
    
    

}
