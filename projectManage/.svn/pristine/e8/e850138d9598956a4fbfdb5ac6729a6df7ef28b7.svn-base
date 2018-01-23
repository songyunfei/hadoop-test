
package com.linkGap.projectManage.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 压缩css,js工具类
 * 
 * @author renhengli
 * @date 2017年11月21日
 * @version 1.0
 */
public class CompressorUtil {
	private static final String encoding = "utf-8";
	private static final String[] suffixArray = { ".js", ".css" };

//	public static void main(String[] args) {
//		try {                 
//			String yuiPath = "c:/Users/a/.m2/repository/com/yahoo/platform/yui/yuicompressor/2.4.7/yuicompressor-2.4.7.jar";
//			String filePathcss = "f:/workspace_linkgap/projectManage/src/main/resources/static/css/";
//			String filePathjs = "f:/workspace_linkgap/projectManage/src/main/resources/static/js/";
//			compressFile(yuiPath, filePathcss,filePathjs);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 压缩指定文件夹下所有的js/css
	 * 
	 * @param yuiPath
	 *            yuicompressor-2.4.7.jar文件路径
	 * @param filePath
	 *            要压缩的文件夹路径
	 */
	public static void compressFile(String yuiPath, String filePathcss, String filePathjs) {
		File filecss = new File(filePathcss);
		File filejs = new File(filePathjs);
		List<String> commondList = new ArrayList<String>();
		initCommondList(yuiPath, commondList, filecss);
		initCommondList(yuiPath, commondList, filejs);
		excuteCompress(commondList);
	}

	/**
	 * 执行压缩命令
	 * 
	 * @param commondList
	 */
	private static void excuteCompress(List<String> commondList) {
		Runtime runTime = Runtime.getRuntime();
		Date startTime = new Date();
		Long count = 0L;
		for (String cmd : commondList) {
			try {
				System.out.println(cmd);
				runTime.exec(cmd);
				count++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Date endTime = new Date();
		Long cost = endTime.getTime() - startTime.getTime();
		System.out.println("压缩完成，耗时：" + cost + "ms，共压缩文件个数：" + count);
	}

	/**
	 * 初始化压缩命令
	 * 
	 * @param yuiPath
	 * @param commondList
	 * @param file
	 */
	private static void initCommondList(String yuiPath, List<String> commondList, File file) {
		if (file.isDirectory() && file.getPath().indexOf("prod") == -1) {
			File[] files = file.listFiles();
			// 如果某个文件夹是空文件夹，则跳过
			if (files == null) {
				return;
			}
			for (File f : files) {
				initCommondList(yuiPath, commondList, f);
			}
		} else if (file.getPath().indexOf("prod") == -1){
			String fileName = file.getName();
			String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

			List<String> suffixList = Arrays.asList(suffixArray);
			if (suffixList.contains(suffix) && !fileName.endsWith("-min" + suffix)) {
				StringBuffer sb = new StringBuffer();
				sb.append("java -jar ");
				sb.append(yuiPath);
				sb.append(" --type ");
				sb.append(suffix.substring(suffix.indexOf(".") + 1));
				sb.append(" --charset ");
				sb.append(encoding).append(" ");
				sb.append(file.getPath()).append(" ");
				sb.append("-o").append(" ");
				file.getParentFile();
				file.getName();
				sb.append(file.getParentFile()).append(File.separator+"prod"+File.separator).append(file.getName());
				//sb.append(file.getPath().replace(suffix, "-min" + suffix));

				commondList.add(sb.toString());
			}

		}else{
			
		}
	}
}
