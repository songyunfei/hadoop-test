package com.linkGap.projectManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.view.CustomerShopsView;
import com.linkGap.projectManage.service.CustomerShopsService;



@Controller
@RequestMapping(value="/customerShop")
public class CustomerShopsController {
    
	@Autowired
	private CustomerShopsService customerShopsService;
	
	
	
	/**
	 * 
	 * @param customerId
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil list(@RequestParam(value="customerId", defaultValue = "")String customerId,@RequestParam(value="userId", defaultValue = "")String userId, HttpServletRequest request){
		try {
			if(customerId.equals("")){
				return new ReturnResultUtil("01","公司id为空");
			}
			Map params=new HashMap();
			if(!userId.equals("")){
				params.put("userId", userId);
			}
			params.put("customerId", customerId);
			List<CustomerShopsView> list=customerShopsService.list(params);
			return new ReturnResultUtil("00", "成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	
	}
	
	/**
	 * 添加门店
	 * @author xujie
	 * @date: 2018年1月15日 下午4:56:43
	 */
	@PostMapping
	@ResponseBody
	@OperationLogAnotation(moduleName="门店模块",operation="添加门店")
	public ReturnResultUtil addCustomerShop(@RequestParam(value="shopName")String shopName,@RequestParam(value="customerId")Integer customerId,@RequestParam(value="userId")Integer userId){
		try {
			String msg=this.customerShopsService.addCustomerShop(customerId,shopName,userId);
			if("店名已存在".equals(msg)){
				return new ReturnResultUtil("02", msg);
			}else if("店名为空".equals(msg)){
				return new ReturnResultUtil("02", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01", "修改失败");
		} 
		return new ReturnResultUtil("00", "成功");
	}
	
	/**
	 * 删除门店
	 * @author xujie
	 * @date: 2018年1月15日 下午4:56:43
	 */
	@DeleteMapping(value="/{customerShopId}")
	@ResponseBody
	@OperationLogAnotation(moduleName="门店模块",operation="删除门店")
	public ReturnResultUtil deleteCustomerShop(@PathVariable(value="customerShopId")Integer customerShopId,@RequestParam(value="userId")Integer userId){
		try {
			this.customerShopsService.deleteCustomerShop(customerShopId,userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01", "修改失败");
		} 
		return new ReturnResultUtil("00", "成功");
	}
}
