package com.linkGap.projectManage.service;

import org.springframework.web.multipart.MultipartFile;

import com.linkGap.projectManage.model.view.CustomerView;


public interface CustomerService {

	/**
	 * 门店设置
	 * @author xujie
	 * @param smallImgFiles 
	 * @param integer 
	 * @date: 2018年1月15日 下午3:47:53 
	 */
	public String update(Integer customerId, String companyName, String mobile,String addressDetail,MultipartFile[] imgFiles, MultipartFile[] smallImgFiles, Integer integer)throws Exception;

	/**
	 * 查询门店
	 * @author xujie
	 * @date: 2018年1月15日 下午5:26:55 
	 */
	public CustomerView queryCustomer(Integer userId);

	

}
