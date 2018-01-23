package com.linkGap.projectManage.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.ProjectContacts;
import com.linkGap.projectManage.model.entity.ProjectInfo;
import com.linkGap.projectManage.model.entity.ProjectStep;
import com.linkGap.projectManage.model.entity.ProjectStepLogs;
import com.linkGap.projectManage.model.view.ProjectInfoView;
import com.linkGap.projectManage.model.view.ProjectRequestParams;
import com.linkGap.projectManage.service.ProjectService;
import com.linkGap.projectManage.service.ProjectStepService;



@RestController
@RequestMapping(value="/project")
public class ProjectController {
    
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectStepService projectStepService;
	
	@Value("${upload.image.path}")
	private String picPath;
	
	private final ObjectMapper MAPPER=new ObjectMapper();
	
	/**
	 * 工程列表查询
	 * @author  songyunfei
	 * 2018年1月12日
	 */
	@GetMapping(value="/list")
	@OperationLogAnotation(moduleName="工程",operation="工程列表查询")
	public ReturnResultUtil projectList(ProjectRequestParams params){
		try {
			ReturnResultUtil result=projectService.queryList(params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	/**
	 * 添加工程
	 * @author  songyunfei
	 * 2018年1月15日
	 */
	@PostMapping("/add")
	@OperationLogAnotation(moduleName="工程",operation="添加工程")
	public ReturnResultUtil addProject(ProjectInfo projectInfo, @RequestParam(value="projectContacts",required=false)String projectContacts,
			                           @RequestParam(value="constructionfiles",required=false) MultipartFile[] constructionfiles,
			                           @RequestParam(value="constructionShortfiles",required=false) MultipartFile[] constructionShortfiles,
			                           @RequestParam(value="contractImagefiles",required=false)MultipartFile[] contractImagefiles,
			                           @RequestParam(value="contractImageShortfiles",required=false)MultipartFile[] contractImageShortfiles){
		try {
			if(StringUtils.isNotEmpty(projectContacts)){
				//projectContacts=URLDecoder.decode(projectContacts);
				List<ProjectContacts> list= MAPPER.readValue(projectContacts,new TypeReference<List<ProjectContacts>>(){});
				projectInfo.setProjectContactsList(list);
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
			this.projectService.saveProject(projectInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","保存失败，请稍后再试!");
		}
		return new ReturnResultUtil();
	}
	
	
	/**
	 * 根据工程id查询工程详细
	 * @param request
	 * @param projectInfoId
	 * @return
	 */
	@RequestMapping(value="/{projectInfoId}",method=RequestMethod.GET)
	public ReturnResultUtil getProjectDetail(HttpServletRequest request, @PathVariable("projectInfoId") String projectInfoId){
		try {
			if(projectInfoId==null||projectInfoId.equals("")){
				return new ReturnResultUtil("01","工程id为空");
			}
			ProjectInfoView projectInfoView= projectService.selectByProjectId(Integer.parseInt(projectInfoId));
			return new ReturnResultUtil(projectInfoView);
		} catch (Exception e) {
			e.printStackTrace();

			return new ReturnResultUtil("01","后台异常");
		}
	
	}
	
	
	/**
	 * 根据项目id查步骤
	 * @author  lixin
	 * 2018年1月16日
	 */
	@RequestMapping(value="/projectStep/{projectInfoId}",method=RequestMethod.GET)
	public ReturnResultUtil getProjectStepByProjectInfoId(@PathVariable("projectInfoId") Integer projectInfoId, HttpServletRequest request){
		try {
			ReturnResultUtil result=projectStepService.getProjectStepByProjectInfoId(projectInfoId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	
	/**
	 * 保存工程步骤
	 * @author  lixin
	 * 2018年1月16日
	 */
	@RequestMapping(value="/projectStep/{projectInfoId}",method=RequestMethod.POST)
	@OperationLogAnotation(moduleName="工程步骤",operation="工程步骤保存")
	public ReturnResultUtil saveProjectSteps(@PathVariable("projectInfoId") Integer projectInfoId, @RequestParam(value="projectStepList", required = false)String projectStepList, HttpServletRequest request){
		try {
			ObjectMapper MAPPER=new ObjectMapper();
			List<ProjectStep> projectSteps = MAPPER.readValue(projectStepList, new TypeReference<List<ProjectStep>>(){});
			if(!CollectionUtils.isEmpty(projectSteps)){
				ReturnResultUtil result=projectStepService.saveProjectSteps(projectSteps, projectInfoId);
				return result;
			}else{
				return new ReturnResultUtil("01","步骤为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	
	/**
	 * 查步骤操作日志
	 * @author  lixin
	 * 2018年1月16日
	 */
	@RequestMapping(value="/projectStepLog/list/{stepId}",method=RequestMethod.GET)
	public ReturnResultUtil getLogByStepId(@PathVariable("stepId") Integer stepId, HttpServletRequest request){
		try {
			ReturnResultUtil result=projectStepService.getLogByStepId(stepId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	
	/**
	 * 步骤操作
	 * @author  lixin
	 * 2018年1月17日
	 */
	@RequestMapping(value="/opreateStep/{projectInfoId}",method=RequestMethod.POST)
	@OperationLogAnotation(moduleName="工程步骤",operation="步骤操作")
	public ReturnResultUtil opreateStep(@PathVariable("projectInfoId") Integer projectInfoId,@RequestParam("stepId") Integer stepId,
			@RequestParam("createUserId") Integer createUserId,@RequestParam("opreateTitle") String opreateTitle,
			@RequestParam(value="opreateContent",required=false) String opreateContent,@RequestParam(value="rectifyDate",required=false) String rectifyDate,
            @RequestParam(value="imgFiles",required=false) MultipartFile[] imgFiles,
            @RequestParam(value="smallImgFiles",required=false) MultipartFile[] smallImgFiles, HttpServletRequest request){
		try {
			String imgFileUrls ="";
			String smallImgFileUrls ="";
			if(imgFiles!=null&&imgFiles.length>0){
				for (MultipartFile multipartFile : imgFiles) {
				     String fileName=multipartFile.getOriginalFilename();
				     fileName=System.currentTimeMillis()+fileName;
				     String path=picPath+File.separator+fileName;
				     multipartFile.transferTo(new File(path));
				     imgFileUrls+=path+",";
				}
			}
			if(smallImgFiles!=null&&smallImgFiles.length>0){
				for (MultipartFile multipartFile : smallImgFiles) {
				     String fileName=multipartFile.getOriginalFilename();
				     fileName=System.currentTimeMillis()+fileName;
				     String zippath=picPath+File.separator+fileName;
				     multipartFile.transferTo(new File(zippath));
				     smallImgFileUrls+=zippath+",";		 
				}
			}
			ProjectStepLogs projectStepLog = new ProjectStepLogs();
			projectStepLog.setCreateTime(new Date());
			projectStepLog.setCreateUserId(createUserId);
			projectStepLog.setOperateContent(opreateContent);
			projectStepLog.setOperateTitle(opreateTitle);
			if(StringUtils.isNotEmpty(rectifyDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				projectStepLog.setRectifyDate(sdf.parse(rectifyDate));
			}
			projectStepLog.setStepId(stepId);
			projectStepLog.setStatus("1");
			ReturnResultUtil result=projectStepService.opreateStep(projectInfoId,projectStepLog,imgFileUrls,smallImgFileUrls);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	
	/**
	 * 工程列表查询(tab栏切换)
	 * @author  songyunfei
	 * 2018年1月12日
	 */
	@GetMapping("/list_tab")
	@OperationLogAnotation(moduleName="工程",operation="工程列表查询(tab页切换)")
	public ReturnResultUtil projectListTab(ProjectRequestParams params){
		try {
			ReturnResultUtil result=projectService.queryListByTab(params);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","后台异常");
		}
	}
	
	/**
	 * 修改工程
	 * @author  songyunfei
	 * 2018年1月18日
	 */
	@PutMapping(value="/{projectInfoId}")
	@OperationLogAnotation(moduleName="工程",operation="修改工程")
	public ReturnResultUtil updateProjectTab(ProjectInfo projectInfo, @RequestParam(value="projectContacts",required=false)String projectContacts,
                                            @RequestParam(value="constructionfiles",required=false) MultipartFile[] constructionfiles,
                                            @RequestParam(value="constructionShortfiles",required=false) MultipartFile[] constructionShortfiles,
                                            @RequestParam(value="contractImagefiles",required=false)MultipartFile[] contractImagefiles,
                                            @RequestParam(value="contractImageShortfiles",required=false)MultipartFile[] contractImageShortfiles,
                                            @PathVariable Integer projectInfoId){
		
		try {
			projectInfo.setProjectInfoId(projectInfoId);
			if(StringUtils.isNotEmpty(projectContacts)){
				//projectContacts=URLDecoder.decode(projectContacts);
				List<ProjectContacts> list= MAPPER.readValue(projectContacts,new TypeReference<List<ProjectContacts>>(){});
				projectInfo.setProjectContactsList(list);
			}
			this.projectService.updateProject(projectInfo,constructionfiles,constructionShortfiles,contractImagefiles,contractImageShortfiles);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnResultUtil("01","修改失败，请稍后再试!");
		}
		return new ReturnResultUtil();
	}
	
	
}
