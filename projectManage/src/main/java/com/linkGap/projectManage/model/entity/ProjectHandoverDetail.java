package com.linkGap.projectManage.model.entity;

import java.util.Date;

public class ProjectHandoverDetail {
    private Integer projectHandoverDetailId;

    private Integer projectInfoId;

    private Integer oldSaleMan;

    private Integer oldDesignMan;

    private Integer oldConstructLeader;

    private Integer oldSupervisor;

    private Integer newSaleMan;

    private Integer newDesignMan;

    private Integer newConstructLeader;

    private Integer newSupervisor;

    private Date createTime;

    private Integer createUserId;

    private String status;

    public Integer getProjectHandoverDetailId() {
        return projectHandoverDetailId;
    }

    public void setProjectHandoverDetailId(Integer projectHandoverDetailId) {
        this.projectHandoverDetailId = projectHandoverDetailId;
    }

    public Integer getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(Integer projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public Integer getOldSaleMan() {
        return oldSaleMan;
    }

    public void setOldSaleMan(Integer oldSaleMan) {
        this.oldSaleMan = oldSaleMan;
    }

    public Integer getOldDesignMan() {
        return oldDesignMan;
    }

    public void setOldDesignMan(Integer oldDesignMan) {
        this.oldDesignMan = oldDesignMan;
    }

    public Integer getOldConstructLeader() {
        return oldConstructLeader;
    }

    public void setOldConstructLeader(Integer oldConstructLeader) {
        this.oldConstructLeader = oldConstructLeader;
    }

    public Integer getOldSupervisor() {
        return oldSupervisor;
    }

    public void setOldSupervisor(Integer oldSupervisor) {
        this.oldSupervisor = oldSupervisor;
    }

    public Integer getNewSaleMan() {
        return newSaleMan;
    }

    public void setNewSaleMan(Integer newSaleMan) {
        this.newSaleMan = newSaleMan;
    }

    public Integer getNewDesignMan() {
        return newDesignMan;
    }

    public void setNewDesignMan(Integer newDesignMan) {
        this.newDesignMan = newDesignMan;
    }

    public Integer getNewConstructLeader() {
        return newConstructLeader;
    }

    public void setNewConstructLeader(Integer newConstructLeader) {
        this.newConstructLeader = newConstructLeader;
    }

    public Integer getNewSupervisor() {
        return newSupervisor;
    }

    public void setNewSupervisor(Integer newSupervisor) {
        this.newSupervisor = newSupervisor;
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