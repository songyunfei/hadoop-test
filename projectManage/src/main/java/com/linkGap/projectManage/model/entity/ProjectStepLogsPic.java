package com.linkGap.projectManage.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linkGap.projectManage.utils.MediumDateSerializer;

public class ProjectStepLogsPic implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8368223954099445329L;

	private Integer projectStepLogPicId;

    private Integer projectStepLogId;

    private String smallImageUrls;

    private String imgUrls;

    private Integer imgOrder;

    @JsonSerialize(using = MediumDateSerializer.class)
    private Date createTime;

    private Integer createUserId;

    private String status;

    public Integer getProjectStepLogPicId() {
        return projectStepLogPicId;
    }

    public void setProjectStepLogPicId(Integer projectStepLogPicId) {
        this.projectStepLogPicId = projectStepLogPicId;
    }

    public Integer getProjectStepLogId() {
        return projectStepLogId;
    }

    public void setProjectStepLogId(Integer projectStepLogId) {
        this.projectStepLogId = projectStepLogId;
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

    public Integer getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(Integer imgOrder) {
        this.imgOrder = imgOrder;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}