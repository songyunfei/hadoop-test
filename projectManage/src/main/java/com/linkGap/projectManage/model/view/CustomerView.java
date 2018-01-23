package com.linkGap.projectManage.model.view;

import java.io.Serializable;
import java.util.List;

public class CustomerView implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6445105888143535607L;

	private Integer customerId;

    private String companyName;

    private String addressDetail;

    private String mobile;

    private List<CustomerPicView> picPist;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<CustomerPicView> getPicPist() {
		return picPist;
	}

	public void setPicPist(List<CustomerPicView> picPist) {
		this.picPist = picPist;
	}

    
}