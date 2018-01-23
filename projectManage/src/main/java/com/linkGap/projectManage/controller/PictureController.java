package com.linkGap.projectManage.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.linkGap.projectManage.service.PictureService;
import com.linkGap.projectManage.utils.JsonUtils;

import sun.misc.BASE64Decoder;

/**
 * 
 * 上传图片处理
 *
 * @author renhengli
 * @version 0.0.1
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	@Value("${upload.image.path}")
	private String path;

	@ResponseBody
	@RequestMapping(value="/pic/upload",produces="text/html;charset=UTF-8")
	public String pictureUpload(MultipartFile uploadFile) throws Exception {
		String originalName = uploadFile.getOriginalFilename();
		Map<String, Object> result = pictureService.uploadPicture(uploadFile, path);
		result.put("originalName", originalName);
		// 为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		String json = JsonUtils.objectToJson(result);
		return json;
	}

	@RequestMapping("/pic/delete")
	@ResponseBody
	public String pictureDelete(@RequestParam String picName) throws Exception {
		pictureService.deleteFile(picName, path);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", "success");
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
	//获取客户端传来的图片文件 （  客户端处理是文件转换为流  Base64编码成字符串）  
	public String getPhotoByAndroid(String photoPath){  
	      
	    //图片存放路径 放到正式需要修改  
	    String newFilePath="D:/";  
	      
	    String newFileName =UUID.randomUUID().toString()+"jpg";  
	        FileOutputStream fos = null;  
	        byte[] buffer;  
	        try {  
	            buffer = new BASE64Decoder().decodeBuffer(photoPath);  
	           
	        //对android传过来的图片字符串进行解码     
	        File destDir = new File(newFilePath);      
	        if(!destDir.exists()) destDir.mkdir();    
	        fos = new FileOutputStream(new File(destDir,newFileName));   //保存图片    
	        fos.write(buffer);    
	        fos.flush();    
	        fos.close();   
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }   
	        System.out.println("上传图片成功!" + newFilePath+newFileName);     
	    return newFileName;  
	      
	}  
}
