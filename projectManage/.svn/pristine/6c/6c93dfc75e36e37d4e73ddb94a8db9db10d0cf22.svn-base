package com.linkGap.projectManage.service;

import java.util.List;

import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.ProjectStep;
import com.linkGap.projectManage.model.entity.ProjectStepLogs;

public interface ProjectStepService {

	ReturnResultUtil getProjectStepByProjectInfoId(Integer id);

	ReturnResultUtil saveProjectSteps(List<ProjectStep> projectSteps, Integer projectInfoId);

	ReturnResultUtil getLogByStepId(Integer stepId);

	ReturnResultUtil opreateStep(Integer projectInfoId, ProjectStepLogs projectStepLogs, String imgFileUrls, String smallImgFileUrls);

}
