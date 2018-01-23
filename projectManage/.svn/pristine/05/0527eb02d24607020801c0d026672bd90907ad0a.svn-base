package com.linkGap.projectManage.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	/**
	 * 上传文件
	 * 
	 * @param uploadFile
	 *            上传的文件
	 * @return 上传信息
	 */
	Map<String, Object> uploadFile(MultipartFile uploadFile, String path) throws Exception;

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return boolean
	 */
	boolean deleteFile(String fileName, String path) throws Exception;
}
