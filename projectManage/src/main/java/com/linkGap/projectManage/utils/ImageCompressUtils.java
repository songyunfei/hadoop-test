
package com.linkGap.projectManage.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @author renhengli
 * @date 2018年1月16日
 * @version 1.0
 */
public class ImageCompressUtils {

	/**
	 * 
	* @Title: compressImageWithWatermark  
	* @Description: 压缩图片返回两个图片，一个缩略图，一个指定尺寸大图
	* @param width 缩略图宽度（例如：200）
	* @param height 缩略图高度（例如：200）
	* @param orginPath 原图片路径（例如：C:/Users/a/Pictures/Camera Roll/6.jpg）
	* @param destPath 目标图片路径（例如：C:/Users/a/Pictures/Camera Roll/）
	* @param returnImageName1  缩略图名称 （例如：image_200x200）
	* @param returnImageName2  大图名称 （例如：image）
	* @param outQuality 图片质量 （为0-1之间的数，1为图片本身同等质量,例如：一般为0.5f-0.8f）
	* @return 
	* @throws IOException    参数  
	* Map<String,String> 返回map里firstImagePath为缩略图路径，secondImagePath为大图路径
	 */
	public  static Map<String, String> compressImageWithWatermark(int width, int height, String orginPath, String destPath, String returnImageName1,
			String returnImageName2, float outQuality) throws IOException {

		// 缩略图
		Thumbnails.of(orginPath).size(width, height).outputQuality(outQuality).toFile(destPath.concat(returnImageName1).concat(".jpg"));

		// watermark(位置，水印图，透明度)，原图不保持原来尺寸，网站统一保留图片尺寸为1280, 1024
		Thumbnails.of(orginPath).size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("src/main/resources/static/imgs/watermark.png")), 1f)
				.outputQuality(outQuality).toFile(destPath.concat(returnImageName2).concat(".jpg"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("firstImagePath", destPath.concat(returnImageName1).concat(".jpg"));
		map.put("secondImagePath", destPath.concat(returnImageName2).concat(".jpg"));
		return map;
	}

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		compressImageWithWatermark(200, 200, "6.jpg", "C:/Users/a/Pictures/Camera Roll/", "image_200x200", "image", 0.8f);
		long end = System.currentTimeMillis();
		System.out.println("SPEND:" + (end - start));
	}

}
