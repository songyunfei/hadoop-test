package com.linkGap.projectManage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkGap.projectManage.dao.ProjectInfoMapper;
import com.linkGap.projectManage.dao.ProjectStepLogsMapper;
import com.linkGap.projectManage.dao.ProjectStepLogsPicMapper;
import com.linkGap.projectManage.dao.ProjectStepMapper;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.ProjectStep;
import com.linkGap.projectManage.model.entity.ProjectStepLogs;
import com.linkGap.projectManage.model.entity.ProjectStepLogsPic;
import com.linkGap.projectManage.model.view.ProjectStepLogView;
import com.linkGap.projectManage.model.view.ProjectStepLogsPicView;
import com.linkGap.projectManage.model.view.ProjectStepView;
import com.linkGap.projectManage.service.ProjectStepService;

@Service
public class ProjectStepServiceImpl implements ProjectStepService {
	
	@Autowired
	private ProjectStepMapper projectStepMapper;
	
	@Autowired
	private ProjectInfoMapper projectInfoMapper;
	
	@Autowired
	private ProjectStepLogsMapper projectStepLogsMapper;
	
	@Autowired
	private ProjectStepLogsPicMapper projectStepLogsPicMapper;

	@Override
	public ReturnResultUtil getProjectStepByProjectInfoId(Integer id) {
		List<ProjectStepView> stepList= projectStepMapper.getProjectStepByProjectInfoId(id);
		return new ReturnResultUtil(stepList);
	}

	@Override
	public ReturnResultUtil saveProjectSteps(List<ProjectStep> projectSteps, Integer projectInfoId) {
		for(ProjectStep projectStep:projectSteps){
			projectStep.setStatus("1");
			projectStep.setCreateTime(new Date());
			projectStep.setProjectInfoId(projectInfoId);
			projectStep.setProjectStatus(null);
		}
		projectStepMapper.deleteStepByProjectInfoId(projectInfoId);
		int i = projectStepMapper.insertBatch(projectSteps);
		if(i>0){
			return new ReturnResultUtil("00", "保存成功");
		}else{
			return new ReturnResultUtil("01", "保存失败");
		}
	}

	@Override
	public ReturnResultUtil getLogByStepId(Integer stepId) {
		List<ProjectStepLogView> projectStepLogs = projectStepLogsMapper.getLogByStepId(stepId);
		for(ProjectStepLogView projectStepLog:projectStepLogs){
			List<ProjectStepLogsPicView> projectStepLogsPics = projectStepLogsPicMapper.getPicByLog(projectStepLog.getProjectStepLogId());
			projectStepLog.setPicList(projectStepLogsPics);
		}
		return new ReturnResultUtil(projectStepLogs);
	}

	@Override
	public ReturnResultUtil opreateStep(Integer projectInfoId, ProjectStepLogs projectStepLogs, String imgFileUrls, String smallImgFileUrls) {
		//插入步骤图片
		projectStepLogs.setImgUrls(imgFileUrls);
		projectStepLogs.setSmallImageUrls(smallImgFileUrls);
		int logId = projectStepLogsMapper.insert(projectStepLogs);
		if(!("".equals(imgFileUrls)||"".equals(smallImgFileUrls))){
			List<ProjectStepLogsPic> list = new ArrayList<ProjectStepLogsPic>();
			String[] urls = imgFileUrls.split(",");
			String[] smallUrls =smallImgFileUrls.split(",");
			for(int i=0;i<urls.length;i++){
				ProjectStepLogsPic projectStepLogsPic=new ProjectStepLogsPic();
				projectStepLogsPic.setProjectStepLogId(logId);
				projectStepLogsPic.setImgUrls(urls[i]);
				projectStepLogsPic.setSmallImageUrls(smallUrls[i]);
				projectStepLogsPic.setImgOrder(i);
				projectStepLogsPic.setCreateTime(new Date());
				projectStepLogsPic.setCreateUserId(projectStepLogs.getCreateUserId());
				projectStepLogsPic.setStatus("1");
				list.add(projectStepLogsPic);
			}
			projectStepLogsPicMapper.insertBatch(list);
		}
		if("提交".equals(projectStepLogs.getOperateTitle())){
			projectInfoMapper.submit(projectInfoId);
			projectStepMapper.oprateStep(projectStepLogs.getStepId(),projectStepLogs.getCreateUserId(),"1");
		}else if ("整改".equals(projectStepLogs.getOperateTitle())){
			projectInfoMapper.updateStatus(projectInfoId,"2");
			projectStepMapper.oprateStep(projectStepLogs.getStepId(),projectStepLogs.getCreateUserId(),"2");
		}else if ("审核通过".equals(projectStepLogs.getOperateTitle())){
			projectStepMapper.oprateStep(projectStepLogs.getStepId(),projectStepLogs.getCreateUserId(),"3");
			List<ProjectStepView> projectSteps = projectStepMapper.getProjectStepByProjectInfoId(projectInfoId);
			String status = "3";
			Boolean isComplate = true;
			for(ProjectStepView projectStep:projectSteps){
				if(!"3".equals(projectStep.getProjectStatus())){
					isComplate = false;
				}
				if("2".equals(projectStep.getProjectStatus())){
					status = "2";
					break;
				}else if("1".equals(projectStep.getProjectStatus())){
					status = "1";
				}
			}
			projectInfoMapper.updateStatus(projectInfoId,status);
			if(isComplate){
				projectInfoMapper.complateProject(projectInfoId);
			}
		}
		return new ReturnResultUtil("00","操作成功");
	}

}
