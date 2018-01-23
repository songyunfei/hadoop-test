package com.linkGap.projectManage.dao;

import com.linkGap.projectManage.model.entity.ProjectContacts;

public interface ProjectContactsMapper {
    int deleteByPrimaryKey(Integer customerContactId);

    int insert(ProjectContacts record);

    int insertSelective(ProjectContacts record);

    ProjectContacts selectByPrimaryKey(Integer customerContactId);

    int updateByPrimaryKeySelective(ProjectContacts record);

    int updateByPrimaryKey(ProjectContacts record);

	void deleteByProjectInfoId(Integer projectInfoId);
}