package com.linkGap.projectManage.model.entity;

import java.util.Date;

public class CustomerPic {
    private Integer customerPicId;

    private Integer customerId;

    private String smallImageUrls;

    private String imgUrls;

    private Integer imgOrder;

    private Date createTime;

    private Integer createUserId;

    private String status;

    public Integer getCustomerPicId() {
        return customerPicId;
    }

    public void setCustomerPicId(Integer customerPicId) {
        this.customerPicId = customerPicId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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