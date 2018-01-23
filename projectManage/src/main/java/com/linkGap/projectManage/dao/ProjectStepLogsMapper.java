package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.ProjectStepLogs;
import com.linkGap.projectManage.model.view.ProjectStepLogView;

public interface ProjectStepLogsMapper {
    int deleteByPrimaryKey(Integer projectStepLogId);

    int insert(ProjectStepLogs record);

    int insertSelective(ProjectStepLogs record);

    ProjectStepLogs selectByPrimaryKey(Integer projectStepLogId);

    int updateByPrimaryKeySelective(ProjectStepLogs record);

    int updateByPrimaryKey(ProjectStepLogs record);

	List<ProjectStepLogView> getLogByStepId(Integer stepId);
}