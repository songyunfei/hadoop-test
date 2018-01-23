package com.linkGap.projectManage.dao;

import com.linkGap.projectManage.model.entity.Customer;
import com.linkGap.projectManage.model.view.CustomerView;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

	/**
	 * 根据用户查询公司信息
	 * @author xujie
	 * @date: 2018年1月16日 上午10:46:41 
	 */
	CustomerView queryCustomerByUserId(Integer userId);

	/**
	 * 根据公司名查找公司id
	 * @author xujie
	 * @date: 2018年1月22日 下午2:00:56 
	 */
	Integer queryIdByCompanyName(String companyName);
}