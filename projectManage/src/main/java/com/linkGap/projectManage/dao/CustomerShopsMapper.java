package com.linkGap.projectManage.dao;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.entity.CustomerShops;
import com.linkGap.projectManage.model.view.CustomerShopsView;

public interface CustomerShopsMapper {
    int deleteByPrimaryKey(Integer customerShopId);

    int insert(CustomerShops record);

    int insertSelective(CustomerShops record);

    CustomerShops selectByPrimaryKey(Integer customerShopId);

    int updateByPrimaryKeySelective(CustomerShops record);

    int updateByPrimaryKey(CustomerShops record);
    
    List<CustomerShopsView> list(Map map);

	/**
	 * 获取公司下所有店铺名
	 * @author xujie
	 * @date: 2018年1月22日 上午11:25:08 
	 */
	String[] getShopNames(Integer customerId);
}