package com.linkGap.projectManage.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.ProjectInfo;
import com.linkGap.projectManage.model.view.ProjectInfoView;
import com.linkGap.projectManage.model.view.ProjectRequestParams;

public interface ProjectService {
    /**
     * 工程列表查询（初始化查询，条件查询）
     * @author  songyunfei
     * 2018年1月18日
     */
	ReturnResultUtil queryList(ProjectRequestParams params);

	void saveProject(ProjectInfo projectInfo);
	
	/**
	 * 根据工程id获取详情
	 * @param projectInfoId
	 * @return
	 */
	ProjectInfoView selectByProjectId(Integer projectInfoId);
    /**
     * tab查询工程列表（切换tab时查询）
     * @author  songyunfei
     * 2018年1月18日
     */
	ReturnResultUtil queryListByTab(ProjectRequestParams params);
    /**
     * 修改工程
     * @author  songyunfei
     * 2018年1月18日
     * @param contractImageShortfiles 
     * @param contractImagefiles 
     * @param constructionShortfiles 
     * @param constructionfiles 
     * @throws IOException 
     * @throws IllegalStateException 
     */
	void updateProject(ProjectInfo projectInfo, MultipartFile[] constructionfiles, MultipartFile[] constructionShortfiles, MultipartFile[] contractImagefiles, MultipartFile[] contractImageShortfiles) throws IllegalStateException, IOException;

}
