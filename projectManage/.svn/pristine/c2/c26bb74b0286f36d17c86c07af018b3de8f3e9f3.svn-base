/**
 * @author xujie
 * @date: 2018年1月15日 下午2:38:13 
 */
package com.linkGap.projectManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.view.CustomerView;
import com.linkGap.projectManage.service.CustomerService;
import com.linkGap.projectManage.service.CustomerShopsService;

/**
 * @author xujie
 *
 */
@RestController
@RequestMapping(value = "customer")
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerShopsService customerShopsService;
	
	/**
	 * 查询公司设置
	 * @author xujie
	 * @date: 2018年1月15日 下午4:56:50
	 */
	@GetMapping(value="/{userId}")
	@ResponseBody
	public ReturnResultUtil queryCustomer(@PathVariable(value="userId")Integer userId){
		try {
			CustomerView view=this.customerService.queryCustomer(userId);
			return new ReturnResultUtil("00", "成功",view);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01", "查询失败");
		} 
	}
	
	/**
	 * 公司设置
	 * @author xujie
	 * @date: 2018年1月15日 下午4:56:50
	 */
	@PutMapping(value="/{customerId}")
	@ResponseBody
	@OperationLogAnotation(moduleName="公司设置模块",operation="修改公司设置")
	public ReturnResultUtil updateCustomer(@PathVariable(value="customerId")Integer customerId,String companyName,String mobile,String addressDetail,
											MultipartFile[] imgFiles,MultipartFile[] smallImgFiles,@RequestParam(value="userId")Integer userId){
		try {
			
			String msg=this.customerService.update(customerId,companyName,mobile,addressDetail,imgFiles,smallImgFiles,userId);
			if(!"ok".equals(msg)){
				return new ReturnResultUtil("02", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01", "修改失败");
		} 
		
		return new ReturnResultUtil("00", "修改成功");
	}
	
	
}
