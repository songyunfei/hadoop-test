package com.linkGap.projectManage.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkGap.projectManage.dao.DefaultStepMapper;
import com.linkGap.projectManage.dao.ProjectContactsMapper;
import com.linkGap.projectManage.dao.ProjectInfoMapper;
import com.linkGap.projectManage.dao.ProjectPicMapper;
import com.linkGap.projectManage.dao.ProjectStepMapper;
import com.linkGap.projectManage.model.EUDataGridResult;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.ProjectContacts;
import com.linkGap.projectManage.model.entity.ProjectInfo;
import com.linkGap.projectManage.model.entity.ProjectPic;
import com.linkGap.projectManage.model.entity.ProjectStep;
import com.linkGap.projectManage.model.view.DefaultStepView;
import com.linkGap.projectManage.model.view.ProjectInfoView;
import com.linkGap.projectManage.model.view.ProjectListCount;
import com.linkGap.projectManage.model.view.ProjectListView;
import com.linkGap.projectManage.model.view.ProjectRequestParams;
import com.linkGap.projectManage.service.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService {
    
	@Autowired
	private ProjectInfoMapper  projectInfoMapper;
	@Autowired
	private ProjectContactsMapper projectContactsMapper;
	@Autowired
	private ProjectPicMapper   projectPicMapper; 
	
	@Autowired
	private DefaultStepMapper  efaultStepMapper;
	@Autowired
	private ProjectStepMapper  projectStepMapper;
	
	@Value("${upload.image.path}")
	private String picPath;
	/**
	 * 列表查询
	 */
	@Override
	public ReturnResultUtil queryList(ProjectRequestParams params) {
		//分页处理
 		PageHelper.startPage(params.getPageNub(), params.getPageSize());
		List<ProjectListView> list=projectInfoMapper.queryList(params);
		ReturnResultUtil result=new ReturnResultUtil();
		PageInfo<ProjectListView> pageInfo = new PageInfo<>(list);
		EUDataGridResult eresult=new EUDataGridResult();
		eresult.setRows(list);
		eresult.setTotal(pageInfo.getTotal());
		result.setResultValue(eresult);
		Map<String,Integer> projectTypeMap=new HashMap<String,Integer>();
		projectTypeMap.put("fordispatching", 0);
		projectTypeMap.put("ongoing", 0);
		projectTypeMap.put("completed", 0);
		List<ProjectListCount> countlist=projectInfoMapper.getTotalByParams(params);
		if(countlist!=null&&countlist.size()>0){
			for (ProjectListCount projectListCount : countlist) {
				if(projectListCount.getType().equals("1")){
					 projectTypeMap.put("fordispatching", projectListCount.getCount());
				}else if(projectListCount.getType().equals("2")){
					 projectTypeMap.put("ongoing", projectListCount.getCount());
				}else if(projectListCount.getType().equals("3")){
					 projectTypeMap.put("completed", projectListCount.getCount());
				}
			}
		}
		result.setExtras(projectTypeMap);
		return result;
	}
	/**
	 * 添加工程
	 */
	@Override
	public void saveProject(ProjectInfo projectInfo) {
		
		//设置工程基本参数
		projectInfo.setCreateTime(new Date());
		projectInfo.setUpdateTime(new Date());
		projectInfo.setUpdateUserId(projectInfo.getCreateUserId());
		projectInfo.setDeleted((byte)0);
		projectInfo.setCurrentProgress("0");
		projectInfo.setStatus("3");
	    //工程状态判断
		if(projectInfo.getSaleMan()!=null&&projectInfo.getConstructLeader()!=null
				&&projectInfo.getSupervisor()!=null&&projectInfo.getCustomerShopId()!=null
						&&projectInfo.getDesignMan()!=null){
			projectInfo.setProjectStatus((byte) 2);
		 }else{
			projectInfo.setProjectStatus((byte) 1); 
		 }
		this.projectInfoMapper.insert(projectInfo);
		 //合同图片
		String constructionImageUrls=projectInfo.getConstructionImageUrls();
		String constructionShortImageUrls=projectInfo.getConstructionShortImageUrls();
		if(StringUtils.isNotEmpty(constructionImageUrls)&&StringUtils.isNotEmpty(constructionShortImageUrls)){
			String[] constructionImage = constructionImageUrls.split(",");
			String[] constructionShort =constructionShortImageUrls.split(",");
			for (int i = 0; i < constructionImage.length; i++) {
				 ProjectPic propic=new ProjectPic();
				 propic.setCreateTime(new Date());
				 propic.setCreateUserId(projectInfo.getCreateUserId());
				 propic.setImgOrder(i);
				 propic.setProjectInfoId(projectInfo.getProjectInfoId());
				 propic.setStatus("1");
				 propic.setType("1");
				 propic.setImgUrls(constructionImage[i]);
				 propic.setSmallImageUrls(constructionShort[i]);
				 projectPicMapper.insert(propic);
			}
		 //施工图片	
			String contractImageUrls = projectInfo.getContractImageUrls();
			String contractShortImageUrls = projectInfo.getContractShortImageUrls();
			if(StringUtils.isNotEmpty(contractImageUrls)&&StringUtils.isNotEmpty(contractShortImageUrls)){
				 String[] contractUrls = contractImageUrls.split(",");
				 String[] contractShortUrls = contractShortImageUrls.split(",");
				for (int i = 0; i < contractUrls.length; i++) {
					 ProjectPic propic=new ProjectPic();
					 propic.setCreateTime(new Date());
					 propic.setCreateUserId(projectInfo.getCreateUserId());
					 propic.setImgOrder(i);
					 propic.setProjectInfoId(projectInfo.getProjectInfoId());
					 propic.setStatus("1");
					 propic.setType("2");
					 propic.setImgUrls(contractUrls[i]);
					 propic.setSmallImageUrls(contractShortUrls[i]);
					 projectPicMapper.insert(propic);
				 }	
		     }
		  }
		 //添加工程联系人
		 List<ProjectContacts> projectContacts = projectInfo.getProjectContactsList();
		 if(projectContacts!=null&&projectContacts.size()>0){
			for (ProjectContacts projectContacts2 : projectContacts) {
				projectContacts2.setCreateTime(new Date());
				projectContacts2.setCreateUserId(projectInfo.getCreateUserId());
				projectContacts2.setProjectInfoId(projectInfo.getProjectInfoId());
				projectContacts2.setStatus("1");
				projectContacts2.setUpdateTime(new Date());
				projectContacts2.setUpdateUserId(projectInfo.getCreateUserId());
				projectContactsMapper.insert(projectContacts2);
			}
		  }
		//工程步骤添加
		Map params=new HashMap();
		params.put("customerId", projectInfo.getCoustomerId());
		params.put("projectType", projectInfo.getProjectType());
		List<DefaultStepView> list = efaultStepMapper.list(params);
		if(list==null||list.size()<1){
			params.remove("customerId");
			list=efaultStepMapper.list(params);
		}
		List<ProjectStep> newProjectList=new ArrayList<ProjectStep>();
		if(list!=null&&list.size()>0){
			for (DefaultStepView defaultStepView : list) {
				 ProjectStep ps=new ProjectStep();
				 ps.setCreateTime(new Date());
				 ps.setCreateUserId(projectInfo.getCreateUserId());
				 ps.setProjectInfoId(projectInfo.getProjectInfoId());
				 ps.setProjectStatus("3");
				 ps.setProjectType(projectInfo.getProjectType());
				 ps.setStatus("1");
				 ps.setStepName(defaultStepView.getStepName());
				 ps.setStepOrder(defaultStepView.getStepOrder());
				 ps.setStepPercent(defaultStepView.getStepPercent());
				 ps.setSysRoleId(defaultStepView.getSysRoleId());
				 ps.setUpdateTime(new Date());
				 ps.setUpdateUserId(projectInfo.getUpdateUserId());
				 newProjectList.add(ps);
			}
		 }
		 projectStepMapper.insertBatch(newProjectList);
		
	  }
	
	@Override
	public ProjectInfoView selectByProjectId(Integer projectInfoId) {
		return projectInfoMapper.selectByProjectId(projectInfoId);
	}
	/**
	 * 工程类别tab页的查询
	 */
	@Override
	public ReturnResultUtil queryListByTab(ProjectRequestParams params) {
		//分页处理
 		PageHelper.startPage(params.getPageNub(), params.getPageSize());
		List<ProjectListView> list=projectInfoMapper.queryList(params);
		ReturnResultUtil result=new ReturnResultUtil();
		PageInfo<ProjectListView> pageInfo = new PageInfo<>(list);
		EUDataGridResult eresult=new EUDataGridResult();
		eresult.setRows(list);
		eresult.setTotal(pageInfo.getTotal());
		result.setResultValue(eresult);
		Map<String,Object> projectTypeMap=new HashMap<String,Object>();
		projectTypeMap.put("fordispatching", "");
		projectTypeMap.put("ongoing", "");
		projectTypeMap.put("completed", "");
		result.setExtras(projectTypeMap);
		return result;
	}
	/**
	 * 修改工程
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Override
	public void updateProject(ProjectInfo projectInfo, MultipartFile[] constructionfiles, MultipartFile[] constructionShortfiles, MultipartFile[] contractImagefiles, MultipartFile[] contractImageShortfiles) throws IllegalStateException, IOException {
		ProjectInfoView projectInfoView=projectInfoMapper.selectByProjectId(projectInfo.getProjectInfoId());
		//基本数据设置
		projectInfo.setUpdateTime(new Date());
		projectInfo.setUpdateUserId(projectInfo.getCreateUserId());
		
		//图片操作原先的图片进行删除，将新提交的图片进行保存
		List<ProjectPic> picList=projectPicMapper.queryByProjectInfoId(projectInfo.getProjectInfoId());
		if(picList!=null&&picList.size()>0){
			for (ProjectPic projectPic : picList) {
				 File file=new File(projectPic.getImgUrls());
				 file.delete();
				 File file2=new File(projectPic.getSmallImageUrls());
				 file2.delete();
				 //删除
				 projectPicMapper.deleteByPrimaryKey(projectPic.getProjectPicId());
			}
		 }

		//施工图纸
		String constructionImageUrls ="";
		//施工图纸缩略图
		String constructionShortImageUrls ="";
		if(constructionfiles!=null&&constructionfiles.length>0){
			for (MultipartFile multipartFile : constructionfiles) {
			     String fileName=multipartFile.getOriginalFilename();
			     String path=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(path));
			     constructionImageUrls+=path+",";
			}
		}
		if(constructionShortfiles!=null&&constructionShortfiles.length>0){
			for (MultipartFile multipartFile : constructionShortfiles) {
			     String fileName=multipartFile.getOriginalFilename();
			     String path=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(path));
			     constructionShortImageUrls+=path+",";
			}
		}
		//合同图纸
		String contractImageUrls ="";
		String contractShortImageUrls ="";
	    if(contractImagefiles!=null&&contractImagefiles.length>0){
			for (MultipartFile multipartFile : contractImagefiles) {
				 String fileName=multipartFile.getOriginalFilename();
			     String path=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(path));
			     contractImageUrls+=path+",";	 
			}
		}
		if(contractImageShortfiles!=null&&contractImageShortfiles.length>0){
			for (MultipartFile multipartFile : contractImageShortfiles) {
				 String fileName=multipartFile.getOriginalFilename();
			     String path=picPath+File.separator+fileName;
			     multipartFile.transferTo(new File(path));
			     contractShortImageUrls+=path+",";	 
			}
		}
		projectInfo.setConstructionImageUrls(constructionImageUrls);
		projectInfo.setConstructionShortImageUrls(constructionShortImageUrls);
		projectInfo.setContractImageUrls(contractImageUrls);
		projectInfo.setContractShortImageUrls(contractShortImageUrls);
		
		//联系人更新，删除之前保存的联系人
		projectContactsMapper.deleteByProjectInfoId(projectInfo.getProjectInfoId());
		
		//重新添加添加工程联系人
		List<ProjectContacts> projectContacts = projectInfo.getProjectContactsList();
		if(projectContacts!=null&&projectContacts.size()>0){
			for (ProjectContacts projectContacts2 : projectContacts) {
				projectContacts2.setCreateTime(new Date());
				projectContacts2.setCreateUserId(projectInfo.getCreateUserId());
				projectContacts2.setProjectInfoId(projectInfo.getProjectInfoId());
				projectContacts2.setStatus("1");
				projectContacts2.setUpdateTime(new Date());
				projectContacts2.setUpdateUserId(projectInfo.getCreateUserId());
				projectContactsMapper.insert(projectContacts2);
			}
		  }
		String saveconstructionImageUrls=projectInfo.getConstructionImageUrls();
		String saveconstructionShortImageUrls=projectInfo.getConstructionShortImageUrls();
		if(StringUtils.isNotEmpty(saveconstructionImageUrls)&&StringUtils.isNotEmpty(saveconstructionShortImageUrls)){
			String[] constructionImage = saveconstructionImageUrls.split(",");
			String[] constructionShort =saveconstructionShortImageUrls.split(",");
			for (int i = 0; i < constructionImage.length; i++) {
				 ProjectPic propic=new ProjectPic();
				 propic.setCreateTime(new Date());
				 propic.setCreateUserId(projectInfo.getCreateUserId());
				 propic.setImgOrder(i);
				 propic.setProjectInfoId(projectInfo.getProjectInfoId());
				 propic.setStatus("1");
				 propic.setType("1");
				 propic.setImgUrls(constructionImage[i]);
				 propic.setSmallImageUrls(constructionShort[i]);
				 projectPicMapper.insert(propic);
			}
		 //施工图片	
			String savecontractImageUrls = projectInfo.getContractImageUrls();
			String savecontractShortImageUrls = projectInfo.getContractShortImageUrls();
			if(StringUtils.isNotEmpty(savecontractImageUrls)&&StringUtils.isNotEmpty(savecontractShortImageUrls)){
				 String[] contractUrls = savecontractImageUrls.split(",");
				 String[] contractShortUrls = savecontractShortImageUrls.split(",");
				for (int i = 0; i < contractUrls.length; i++) {
					 ProjectPic propic=new ProjectPic();
					 propic.setCreateTime(new Date());
					 propic.setCreateUserId(projectInfo.getCreateUserId());
					 propic.setImgOrder(i);
					 propic.setProjectInfoId(projectInfo.getProjectInfoId());
					 propic.setStatus("1");
					 propic.setType("2");
					 propic.setImgUrls(contractUrls[i]);
					 propic.setSmallImageUrls(contractShortUrls[i]);
					 projectPicMapper.insert(propic);
				}	
		   }
		}
		this.projectInfoMapper.updateByPrimaryKeySelective(projectInfo);
		
	}
	
	
	
	
}
