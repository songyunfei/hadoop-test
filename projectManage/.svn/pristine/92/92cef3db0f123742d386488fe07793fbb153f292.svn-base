package com.linkGap.projectManage.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkGap.projectManage.utils.MediumDateSerializer;

public class ProjectStepLogs implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2437975781535627793L;

	private Integer projectStepLogId;

    private Integer stepId;

    private String operateTitle;

    private String operateContent;

    private String smallImageUrls;

    private String imgUrls;

    @JsonSerialize(using = MediumDateSerializer.class)
    private Date createTime;

    private Integer createUserId;

    @JsonSerialize(using = MediumDateSerializer.class)
    private Date rectifyDate;

    private String status;
    
    private List<ProjectStepLogsPic> projectStepLogsPic;

    public List<ProjectStepLogsPic> getProjectStepLogsPic() {
		return projectStepLogsPic;
	}

	public void setProjectStepLogsPic(List<ProjectStepLogsPic> projectStepLogsPic) {
		this.projectStepLogsPic = projectStepLogsPic;
	}

	public Integer getProjectStepLogId() {
        return projectStepLogId;
    }

    public void setProjectStepLogId(Integer projectStepLogId) {
        this.projectStepLogId = projectStepLogId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getOperateTitle() {
        return operateTitle;
    }

    public void setOperateTitle(String operateTitle) {
        this.operateTitle = operateTitle == null ? null : operateTitle.trim();
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public String getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(String smallImageUrls) {
        this.smallImageUrls = smallImageUrls == null ? null : smallImageUrls.trim();
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls == null ? null : imgUrls.trim();
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

    public Date getRectifyDate() {
        return rectifyDate;
    }

    public void setRectifyDate(Date rectifyDate) {
        this.rectifyDate = rectifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}