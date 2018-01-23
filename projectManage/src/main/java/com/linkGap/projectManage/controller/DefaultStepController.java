package com.linkGap.projectManage.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.DefaultStep;
import com.linkGap.projectManage.model.view.DefaultStepView;
import com.linkGap.projectManage.service.DefaultStepService;


@Controller
@RequestMapping(value="/defaultStep")
public class DefaultStepController {
    
	@Autowired
	private DefaultStepService defaultStepService;
	
	/**
	 * 根据公司，类型查询模板
	 * @param customerId
	 * @param projectType
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil list(@RequestParam(value="customerId", defaultValue = "")String customerId,@RequestParam(value="projectType", defaultValue = "")String projectType, HttpServletRequest request){
		try {
			if(customerId.equals("")){
				return new ReturnResultUtil("01","公司id为空");
			}
			if(projectType.equals("")){
				return new ReturnResultUtil("01","模板类型为空");
			}
			Map params=new HashMap();
			params.put("customerId", customerId);
			params.put("projectType", projectType);
			List<DefaultStepView> list=defaultStepService.list(params);
			if(list==null||list.size()==0){
				params.put("customerId", null);
				list=defaultStepService.list(params);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						list.get(i).setCustomerId(Integer.parseInt(customerId));
					}
				}	
			}
			return new ReturnResultUtil("00", "成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	
	}
	
	
	/**
	 * 保存修改公司步骤模板
	 * @param defaultStepList
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@OperationLogAnotation(moduleName="保存修改公司步骤模板",operation="根据公司信息保存修改公司步骤模板")
	@ResponseBody
	public ReturnResultUtil add(@RequestParam(value = "defaultStepJson", required = false) String defaultStepJson, HttpServletRequest request){
		try {     
			List<DefaultStep> defaultStepList=null;
			if(StringUtils.isEmpty(defaultStepJson)){
				return new ReturnResultUtil("01","数据为空");
			}else{
				ObjectMapper mapper = new ObjectMapper();
				defaultStepList = mapper.readValue(defaultStepJson, new TypeReference<List<DefaultStep>>() {
				});
			}
			BigDecimal precent=new BigDecimal(0.0);
			for(int i=0;i<defaultStepList.size();i++){
				DefaultStep defaultStep=defaultStepList.get(i);
				if(StringUtil.isEmpty(defaultStep.getCustomerId()+"")){
					return new ReturnResultUtil("01","公司id为空");
				}if(StringUtil.isEmpty(defaultStep.getCreateUserId()+"")){
					//return new ReturnResultUtil("01","用户id为空");
				}if(StringUtil.isEmpty(defaultStep.getSysRoleId()+"")){
					return new ReturnResultUtil("01","角色id为空");
				}if(StringUtil.isEmpty(defaultStep.getProjectType()+"")){
					return new ReturnResultUtil("01","工程类型为空");
				}if(StringUtil.isEmpty(defaultStep.getStepName()+"")){
					return new ReturnResultUtil("01","步骤名称为空");
				}if(StringUtil.isEmpty(defaultStep.getStepPercent()+"")){
					//return new ReturnResultUtil("01","步骤百分比为空");
				}if(StringUtil.isEmpty(defaultStep.getStepOrder()+"")){
					return new ReturnResultUtil("01","步骤排序为空");
				}
				precent=precent.add(defaultStep.getStepPercent());
			}
			if(precent.doubleValue()!=100){
				return new ReturnResultUtil("01","步骤百分比总和100");
			}
			defaultStepService.update(defaultStepList);
			return new ReturnResultUtil("00","成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	                                                             
	}
}
