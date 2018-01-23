
package com.linkGap.projectManage.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
* @author renhengli
* @date 2018年1月15日
* @version 1.0 
*/
public class Base64Util {
	   /**
	    * 
	   * @Title: encode  
	   * @Description: 加密 
	   * @param s
	   * @return    参数  
	   * String    返回类型
	    */
	    public static String encode(String s) {
	        if (s == null)
	            return null;
	        String res = "";
	        try {
	        	//增加一个固定字符串
	        	s = s.concat("linkgap");
	            res = new BASE64Encoder().encode(s.getBytes("GBK"));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return res;
	    }

	    
	    /**
	     * 
	    * @Title: decode  
	    * @Description: 解密
	    * @param s
	    * @return    参数  
	    * String    返回类型
	     */
	    public static String decode(String s) {
	        if (s == null)
	            return null;
	        BASE64Decoder decoder = new BASE64Decoder();
	        try {
	            byte[] b = decoder.decodeBuffer(s);
	            String new_s =  new String(b,"GBK");
	            new_s = new_s.substring(0, new_s.length()-7);
	            return new_s;
	        } catch (Exception e) {
	            return null;
	        }
	    }
	    
//	    public static void main(String[] args) {
//	        System.out.println(Base64Util.encode("哈哈"));
//	        System.out.println(Base64Util.decode("uf65/mxpbmtnYXA="));
//	    }

}

