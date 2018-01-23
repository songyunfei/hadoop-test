package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.CustomerPic;

public interface CustomerPicMapper {
    int deleteByPrimaryKey(Integer customerPicId);

    int insert(CustomerPic record);

    int insertSelective(CustomerPic record);

    CustomerPic selectByPrimaryKey(Integer customerPicId);

    int updateByPrimaryKeySelective(CustomerPic record);

    int updateByPrimaryKey(CustomerPic record);

	/**
	 * 根据公司id查找图片地址
	 * @author xujie
	 * @date: 2018年1月17日 下午5:43:45 
	 */
	List<CustomerPic> queryUrlByCustomerId(Integer customerId);

	/**
	 * 批量插入图片数据
	 * @author xujie
	 * @date: 2018年1月17日 下午5:49:11 
	 */
	void insertBatch(List<CustomerPic> list);
}