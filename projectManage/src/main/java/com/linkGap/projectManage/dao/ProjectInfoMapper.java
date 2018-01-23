package com.linkGap.projectManage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.linkGap.projectManage.model.entity.ProjectInfo;
import com.linkGap.projectManage.model.view.ProjectInfoView;
import com.linkGap.projectManage.model.view.ProjectListCount;
import com.linkGap.projectManage.model.view.ProjectListView;
import com.linkGap.projectManage.model.view.ProjectRequestParams;

@Repository
public interface ProjectInfoMapper {
    int deleteByPrimaryKey(Integer projectInfoId);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    ProjectInfo selectByPrimaryKey(Integer projectInfoId);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);
    /**
     * 列表查询
     * @author  songyunfei
     * 2018年1月12日
     */
	List<ProjectListView> queryList(ProjectRequestParams params);
    /**
     * 根据条件查询各个状态下工程总数
     * @author  songyunfei
     * 2018年1月15日
     */
	List<ProjectListCount> getTotalByParams(ProjectRequestParams params);

	/**
	 * 验证该人员是否有项目未交接
	 * @author xujie
	 * @date: 2018年1月16日 下午3:18:30 
	 */
	Integer checkIsHandover(Integer sysUserId);
	
	
	/**
	 * 根据工程id获取详情
	 * @param projectInfoId
	 * @return
	 */
	ProjectInfoView selectByProjectId(Integer projectInfoId);

	/**
	 * 交接工作
	 * @author xujie
	 * @date: 2018年1月16日 下午5:15:03 
	 */
	void handoverWork(Map<String, Integer> map);

	/**
	 * 查询项目相关人物
	 * @author xujie
	 * @date: 2018年1月16日 下午8:07:48 
	 */
	List<ProjectInfo> queryProjectRelatedMan(Map<String, Integer> map);

	int submit(@Param("projectInfoId")Integer projectInfoId);

	int updateStatus(@Param("projectInfoId")Integer projectInfoId, @Param("status")String status);

	int complateProject(@Param("projectInfoId")Integer projectInfoId);
	
}