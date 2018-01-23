package com.linkGap.projectManage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkGap.projectManage.dao.CustomerShopsMapper;
import com.linkGap.projectManage.dao.UserRelationMapper;
import com.linkGap.projectManage.model.entity.CustomerShops;
import com.linkGap.projectManage.model.entity.UserRelation;
import com.linkGap.projectManage.model.view.CustomerShopsView;
import com.linkGap.projectManage.service.CustomerShopsService;

@Service
public class CustomerShopsServiceImpl implements CustomerShopsService {
    
	@Autowired
	private CustomerShopsMapper  customerShopsMapper;
	
	@Autowired
	private UserRelationMapper userRelationMapper;

	@Override
	public List<CustomerShopsView> list(Map params) {
		if(params.get("userId")!=null){
			UserRelation userRelation= userRelationMapper.selectBySysUserId(Integer.parseInt((String) params.get("userId")));
			if(userRelation!=null){
				params.put("customerShopId", userRelation.getCustomerShopId());
			}	
		}
		return customerShopsMapper.list(params);
	}

	/** 
	 * @author xujie
	 * @date: 2018年1月15日 下午4:31:12 
	 */
	@Override
	public String addCustomerShop(Integer customerId, String shopName, Integer userId) {
		String[] shopNames=customerShopsMapper.getShopNames(customerId);
		String names = StringUtils.join(shopNames, ",");
		if(names.indexOf(shopName)>-1){
			return "店名已存在";
		}else if(StringUtils.isEmpty(shopName)){
			return "店名为空";
		}
		CustomerShops shop=new CustomerShops();
		shop.setCustomerId(customerId);
		shop.setShopName(shopName);
		shop.setCreateTime(new Date());
		shop.setCreateUserId(userId);
		shop.setUpdateTime(new Date());
		shop.setUpdateUserId(userId);
		shop.setStatus("1");
		customerShopsMapper.insert(shop);
		return "ok";
	}

	/** 
	 * @author xujie
	 * @date: 2018年1月15日 下午5:00:01 
	 */
	@Override
	public String deleteCustomerShop(Integer customerShopId, Integer userId) {
		CustomerShops shop=new CustomerShops();
		shop.setCustomerShopId(customerShopId);
		shop.setUpdateTime(new Date());
		shop.setUpdateUserId(userId);
		shop.setStatus("2");
		customerShopsMapper.updateByPrimaryKeySelective(shop);
		return "ok";
	}
}
