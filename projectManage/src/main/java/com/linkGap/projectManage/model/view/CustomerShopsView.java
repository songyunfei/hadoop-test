package com.linkGap.projectManage.model.view;


public class CustomerShopsView {
    private Integer customerShopId;

    private String shopName;

    public Integer getCustomerShopId() {
        return customerShopId;
    }

    public void setCustomerShopId(Integer customerShopId) {
        this.customerShopId = customerShopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }
   
}