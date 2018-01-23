package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.ProjectStepLogsPic;
import com.linkGap.projectManage.model.view.ProjectStepLogsPicView;

public interface ProjectStepLogsPicMapper {
    int deleteByPrimaryKey(Integer projectStepLogPicId);

    int insert(ProjectStepLogsPic record);

    int insertSelective(ProjectStepLogsPic record);

    ProjectStepLogsPic selectByPrimaryKey(Integer projectStepLogPicId);

    int updateByPrimaryKeySelective(ProjectStepLogsPic record);

    int updateByPrimaryKey(ProjectStepLogsPic record);

	List<ProjectStepLogsPicView> getPicByLog(Integer projectStepLogId);

	int insertBatch(List<ProjectStepLogsPic> projectStepLogsPic);
}