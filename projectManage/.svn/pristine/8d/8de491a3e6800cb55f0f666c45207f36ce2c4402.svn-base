package com.linkGap.projectManage.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkGap.projectManage.dao.CustomerMapper;
import com.linkGap.projectManage.dao.CustomerPicMapper;
import com.linkGap.projectManage.model.entity.Customer;
import com.linkGap.projectManage.model.entity.CustomerPic;
import com.linkGap.projectManage.model.view.CustomerView;
import com.linkGap.projectManage.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private CustomerPicMapper customerPicMapper;
	
	@Value("${upload.image.path}")
	private String picPath;
    
	public static ObjectMapper mapper=new ObjectMapper();
	
	/** 
	 * @author xujie
	 * @date: 2018年1月15日 下午3:48:37 
	 */
	@Override
	public String update(Integer customerId, String companyName, String mobile,String addressDetail,MultipartFile[] imgFiles, MultipartFile[] smallImgFiles,Integer userId) throws Exception {
		if(StringUtils.isEmpty(companyName)){
			return "公司名不能为空";
		}
		Integer id=customerMapper.queryIdByCompanyName(companyName);
		if(customerId!=id){
			return "该公司名已存在";
		}
		//施工图纸
		String imgFileUrls ="";
		//施工图纸缩略图
		String smallImgFileUrls ="";
		if(imgFiles!=null&&imgFiles.length>0){
			for (MultipartFile multipartFile : imgFiles) {
			     String fileName=multipartFile.getOriginalFilename();
			     fileName=System.currentTimeMillis()+fileName;
			     String path=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(path));
			     imgFileUrls+=path+",";
			}
		}
		if(smallImgFiles!=null&&smallImgFiles.length>0){
			for (MultipartFile multipartFile : smallImgFiles) {
			     String fileName=multipartFile.getOriginalFilename();
			     fileName=System.currentTimeMillis()+fileName;
			     String zippath=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(zippath));
			     smallImgFileUrls+=zippath+",";		 
			}
		}
		
		Customer customer =new Customer();
		customer.setCustomerId(customerId);
		if(StringUtils.isNotEmpty(companyName)){
			customer.setCompanyName(companyName);
		}
		if(StringUtils.isNotEmpty(mobile)){
			customer.setMobile(mobile);
		}
		if(StringUtils.isNotEmpty(addressDetail)){
			customer.setAddressDetail(addressDetail);
		}
		if(StringUtils.isNotEmpty(imgFileUrls)){
			customer.setImageUrls(imgFileUrls);
		}
		customer.setUpdateTime(new Date());
		customer.setUpdateUserId(userId);
		customerMapper.updateByPrimaryKeySelective(customer);
		if(StringUtils.isNotEmpty(imgFileUrls)){
			//删除原图片及数据
			List<CustomerPic> pics=customerPicMapper.queryUrlByCustomerId(customerId);
			for (CustomerPic customerPic : pics) {
				File pic=new File(customerPic.getImgUrls());
				pic.delete();
				File smallPic=new File(customerPic.getSmallImageUrls());
				smallPic.delete();
				customerPicMapper.deleteByPrimaryKey(customerPic.getCustomerPicId());
			}
			
			//插入公司设置图片数据
			List<CustomerPic> list = new ArrayList<CustomerPic>();
			String[] urls = imgFileUrls.split(",");
			String[] smallUrls =smallImgFileUrls.split(",");
			for(int i=0;i<urls.length;i++){
				CustomerPic customerPic=new CustomerPic();
				customerPic.setCustomerId(customerId);
				customerPic.setImgUrls(urls[i]);
				customerPic.setSmallImageUrls(smallUrls[i]);
				customerPic.setImgOrder(i);
				customerPic.setCreateTime(new Date());
				customerPic.setCreateUserId(userId);
				customerPic.setStatus("1");
				list.add(customerPic);
			}
			customerPicMapper.insertBatch(list);
		}
		return "ok";
	}

	/** 
	 * @author xujie
	 * @date: 2018年1月15日 下午5:27:15 
	 */
	@Override
	public CustomerView queryCustomer(Integer userId) {
		return customerMapper.queryCustomerByUserId(userId);
	}

}
