package com.linkGap.projectManage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkGap.projectManage.model.entity.ProjectStep;
import com.linkGap.projectManage.model.view.ProjectStepView;

public interface ProjectStepMapper {
    int deleteByPrimaryKey(Integer stepId);

    int insert(ProjectStep record);

    int insertSelective(ProjectStep record);

    ProjectStep selectByPrimaryKey(Integer stepId);

    int updateByPrimaryKeySelective(ProjectStep record);

    int updateByPrimaryKey(ProjectStep record);
    
    List<ProjectStepView> getProjectStepByProjectInfoId(Integer projectInfoId);

	void deleteStepByProjectInfoId(Integer projectInfoId);

	int insertBatch(List<ProjectStep> projectSteps);

	int oprateStep(@Param("stepId")Integer stepId, @Param("updateUserId")Integer updateUserId, @Param("projectStatus")String projectStatus);
}