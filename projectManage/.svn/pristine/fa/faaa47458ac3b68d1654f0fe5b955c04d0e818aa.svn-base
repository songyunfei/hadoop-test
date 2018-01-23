package com.linkGap.projectManage.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 生成短网址并返回
 * 
 * @author: rhl
 * @date: 2016年12月20日下午15:22:15
 */
@SuppressWarnings("deprecation")
public class GenerateShortUrlUtil {
	private final static Logger logger = Logger.getLogger(GenerateShortUrlUtil.class);
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	public static DefaultHttpClient httpclient;

	static {
		httpclient = new DefaultHttpClient();
		// httpclient = (DefaultHttpClient)
		// HttpClientConnectionManager.getSSLInstance(httpclient); //
		// 接受任何证书的浏览器客户端
	}

	/**
	 * 生成短连接信息 (百度API)
	 * 
	 * @author: rhl
	 * @date: 2016年12月20日下午15:22:15
	 */
	public static String generateShortUrlByBaidu(String url) {
		try {
			HttpPost httpost = new HttpPost("http://dwz.cn/create.php");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("url", url));
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(jsonStr);
			logger.info(jsonStr);
			JSONObject object = JSON.parseObject(jsonStr);
			System.out.println(object.getString("tinyurl"));
			logger.info(object.getString("tinyurl"));
			return object.getString("tinyurl");
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {

			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");

			resultString.trim();

			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
		} catch (Exception ex) {
		}
		return resultString;
	}

	// public static void main(String[] args) {
	// String url = "http://www.5uplus.com";
	// for (String string : ShortText(url)) {
	// print(string);
	// }
	// }

	public static String[] ShortText(String string) {
		String key = "Renhengli"; // 自定义生成MD5加密字符串前的混合KEY
		String[] chars = new String[] { // 要使用生成URL的字符
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0",
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };

		String hex = MD5Encode(key + string);
		int hexLen = hex.length();
		int subHexLen = hexLen / 8;
		String[] ShortStr = new String[4];

		for (int i = 0; i < subHexLen; i++) {
			String outChars = "";
			int j = i + 1;
			String subHex = hex.substring(i * 8, j * 8);
			long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

			for (int k = 0; k < 6; k++) {
				int index = (int) (Long.valueOf("0000003D", 16) & idx);
				outChars += chars[index];
				idx = idx >> 5;
			}
			ShortStr[i] = outChars;
		}

		return ShortStr;
	}

	public static void print(Object messagr) {
		System.out.println(messagr);
	}

	/**
	 * 测试生成端连接
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @date: 2016年12月20日下午15:44:05
	 */
	 public static void main(String[] args) throws ClientProtocolException, IOException {
		 //generateShortUrlByBaidu("http://www.5uplus.com");
		 System.out.println(generateShortUrlByXinlangWeibo("http://www.5uplus.com"));
	 }
	

	 /**
	  * 
	  * @param url
	  * @return
	  * @throws ClientProtocolException
	  * @throws IOException
	  * 生成短连接信息 (新浪API)
	  * @author: rhl
	  * @date: 2016年12月20日下午15:22:15
	  */
	public static String generateShortUrlByXinlangWeibo(String url){
		// 创建Get方法实例
		HttpGet httpgets = new HttpGet("http://api.t.sina.com.cn/short_url/shorten.json?source=2061978073&url_long="+url);
		try {
			HttpResponse response = httpclient.execute(httpgets);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				String str = convertStreamToString(instreams);
				logger.info(str);
				JSONArray array = JSONArray.parseArray(str);
				JSONObject jo = array.getJSONObject(0);
				String url_short = jo.getString("url_short");
				logger.info("短网址为："+url_short);
				return url_short;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			httpgets.abort();
		}
	
		
		
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}