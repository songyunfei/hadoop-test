package com.linkGap.projectManage.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SmsService {

	private static final Logger log = Logger.getLogger(SmsService.class);

	// 短信第三方调用API(通知类短信)
	public static final String SMS_SERVER_URL_TZ = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	// 短信第三方调用API(营销类短信)
	public static final String SMS_SERVER_URL_YX = "http://api.yx.ihuyi.com/webservice/sms.php?method=Submit";

	// 验证码通知类短信apikey
	public static final String SMS_APIKEY = "1a4009581c6bfd9bed9a3448e6e431ca";

	// 短信第三方接口账号
	public static final String ACCOUNT = "C61354611";

	// private static String indexUrl;
	//
	// static {
	// Resource resource = null;
	// Properties props = null;
	// try {
	// resource = new ClassPathResource("/config.properties");
	// props = PropertiesLoaderUtils.loadProperties(resource);
	// indexUrl = (String) props.get("index.url");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * 发送注册码短信
	 * 
	 * @param destMobile
	 * @return
	 */
	public static String sendSmsRegisterCode(String destMobile, int code) throws Exception {
		String content = "您的验证码是：" + code + "。请不要把验证码泄露给其他人。";
		return sendSms(destMobile, content);
	}

	/**
	 * @param destMobile
	 *            要发送的手机号
	 * @param content
	 *            短信内容
	 * @return ""：短信发送成功，"xxx"：短信发送失败 xxx为出错原因
	 **/
	public static String sendSms(String destMobile, String content, String... extraParams) throws Exception {
		log.info("发送短信开始---------------------------------------");
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		// 创建参数队列
		formparams.add(new BasicNameValuePair("account", ACCOUNT));
		formparams.add(new BasicNameValuePair("mobile", destMobile));
		formparams.add(new BasicNameValuePair("content", content));
		if (extraParams.length > 0) {
			String url = extraParams[0];
			httppost = new HttpPost(url);
			formparams.add(new BasicNameValuePair("password", SMS_APIKEY));
		} else {
			httppost = new HttpPost(SMS_SERVER_URL_TZ);
			formparams.add(new BasicNameValuePair("password", SMS_APIKEY));
		}

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			uefEntity.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
			httppost.setEntity(uefEntity);
			log.info("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String SubmitResult = EntityUtils.toString(entity, "UTF-8");
					log.info("Response content: " + SubmitResult);
					Document doc = DocumentHelper.parseText(SubmitResult);
					Element root = doc.getRootElement();
					String code = root.elementText("code");
					String msg = root.elementText("msg");

					if ("2".equals(code)) {
						log.info("短信发送成功！");
						return "";
					} else {
						log.error("短信发送失败！");
						return msg;
					}
				}
			} finally {
				response.close();
			}
		} catch (DocumentException e) {
			log.error(e, e);
			throw e;
		} catch (ClientProtocolException e) {
			log.error(e, e);
			throw e;
		} catch (UnsupportedEncodingException e) {
			log.error(e, e);
			throw e;
		} catch (IOException e) {
			log.error(e, e);
			throw e;
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e, e);
			}
		}
		log.info("发送短信结束---------------------------------------");
		return "亲，系统繁忙，请稍后再试。";
	}

	public static void main(String[] args) throws Exception {
		sendSmsRegisterCode("15214349646", 1234);
	}

}
