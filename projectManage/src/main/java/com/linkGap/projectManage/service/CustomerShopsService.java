package com.linkGap.projectManage.service;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.view.CustomerShopsView;


public interface CustomerShopsService {

	List<CustomerShopsView> list(Map params);
	
	/**
	 * 添加门店
	 * @author xujie
	 * @param userId 
	 * @date: 2018年1月15日 下午4:30:52 
	 */
	public String addCustomerShop(Integer customerId, String shopName, Integer userId);

	/**
	 * 删除门店
	 * @author xujie
	 * @date: 2018年1月15日 下午4:59:49 
	 */
	public String deleteCustomerShop(Integer customerShopId,Integer userId);
}
