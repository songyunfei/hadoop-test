package com.linkGap.projectManage.dao;

import java.util.List;

import com.linkGap.projectManage.model.entity.ProjectPic;

public interface ProjectPicMapper {
    int deleteByPrimaryKey(Integer projectPicId);

    int insert(ProjectPic record);

    int insertSelective(ProjectPic record);

    ProjectPic selectByPrimaryKey(Integer projectPicId);

    int updateByPrimaryKeySelective(ProjectPic record);

    int updateByPrimaryKey(ProjectPic record);

	List<ProjectPic> queryByProjectInfoId(Integer projectInfoId);
}