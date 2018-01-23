package com.linkGap.projectManage.dao;

import java.util.List;
import java.util.Map;

import com.linkGap.projectManage.model.entity.ProjectHandoverDetail;

public interface ProjectHandoverDetailMapper {
    int deleteByPrimaryKey(Integer projectHandoverDetailId);

    int insert(ProjectHandoverDetail record);

    int insertSelective(ProjectHandoverDetail record);

    ProjectHandoverDetail selectByPrimaryKey(Integer projectHandoverDetailId);

    int updateByPrimaryKeySelective(ProjectHandoverDetail record);

    int updateByPrimaryKey(ProjectHandoverDetail record);

	/**
	 * 记录交接日志
	 * @author xujie
	 * @date: 2018年1月16日 下午8:19:15 
	 */
	void insertBatch(List<ProjectHandoverDetail> logs);
}