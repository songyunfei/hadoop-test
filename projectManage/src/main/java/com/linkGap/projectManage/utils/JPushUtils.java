package com.linkGap.projectManage.utils;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * 
 * @author renhengli
 * 
 */
public class JPushUtils {
	protected static final Logger LOG = Logger.getLogger(JPushUtils.class);
	private static String APP_KEY = "c1fd945900bc18385bc8cb14";
	private static String MASTER_SECRET = "3098891bb7d70814be478b8f";

	public static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

	
	/**
	 * 
	* @Title: sendMessageWithRegistrationID  
	* @Description: 根据设备ID发送通知  
	* @param title
	* @param msgContent
	* @param registrationID
	* @param extraValue 用户名：username
	* @return
	* @throws APIConnectionException
	* @throws APIRequestException    参数  
	* PushResult    返回类型
	 */
	public static PushResult sendMessageWithRegistrationID(String title, String msgContent,String extraValue, String... registrationID)
            throws APIConnectionException, APIRequestException {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(registrationID))
                .setMessage(Message.newBuilder()
                        .setTitle(title)
                        .setMsgContent(msgContent).addExtra("extra_key", extraValue)
                        .build())
                .build();
        return jpushClient.sendPush(payload);
    }
	
	/**
	 * 发送通知 按指定设备发送
	 * 
	 * @param registrationId
	 *            设备标识
	 * @param alert
	 *            推送内容
	 */
	public static void jSend_notification(String registrationId, String alert) {
		PushPayload payload = send_N(registrationId, alert);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);

		} catch (APIConnectionException e) {
			System.out.println(e);
		} catch (APIRequestException e) {
			System.out.println(e);
			System.out.println("Error response from JPush server. Should review and fix it. " + e);
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
			System.out.println("Msg ID: " + e.getMsgId());
		} finally {
			jpushClient.close();
		}
	}

	/**
	 * 如果目标平台为 iOS 平台 需要在 options 中通过 apns_production 字段来制定推送环境。 True
	 * 表示推送生产环境，False 表示要推送开发环境； 如 果不指定则为推送生产环境
	 */
	public static PushPayload send_N(String registrationId, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				// 必填
				// 推送平台设置
				.setAudience(Audience.registrationId(registrationId)).setNotification(Notification.alert(alert))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
	}
	

	/**
	 * 按别名发送消息 alias 默认绑定user表，暂定alias别名就是user表中的Id
	 * 
	 * @param alias
	 * @param alert
	 * @author renhengli
	 * @return
	 */
	public static PushResult sendToOnePeopleByAlias(String alias, String alert) {
		LOG.info("发送通知开始：" + new Date());
		PushResult result = null;
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias.replaceAll("-", "")))
				.setNotification(Notification.alert(alert)).build();
		try {
			result = jpushClient.sendPush(pushPayload);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
			System.out.println("极光推送消息返回结果：" + result);
			return result;
		}
		LOG.info("发送通知结束：" + new Date());
		return result;
	}

	/**
	 * @author renhengli 按标签分组发送（tag定义为roleId，实现按角色发送消息通知）
	 * @param tagValue
	 *            标签分组
	 * @param alert
	 *            消息内容
	 * @param title
	 *            标题
	 * @return
	 */
	public static PushResult sendToGroupByTag(String tagValue, String title, String alert, String extraValue) {
		LOG.info("发送通知开始：" + new Date());
		PushResult pushResult = null;
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tagValue))
				.setNotification(
						Notification.newBuilder().setAlert(alert).addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build())
								.addPlatformNotification(IosNotification.newBuilder().autoBadge().addExtra("extra_key", extraValue).build()).build())
				.build();
		try {
			pushResult = jpushClient.sendPush(pushPayload);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
			return pushResult;
		}
		LOG.info("发送通知结束：" + new Date());
		return pushResult;
	}

	/**
	 * @author renhengli 发送给所有用户
	 * @param alert
	 *            通知内容
	 * @return
	 */
	public static PushResult sendToAll(String alert) {
		LOG.info("发送通知开始：" + new Date());
		PushResult pushResult = null;
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
				.setNotification(Notification.alert(alert)).build();
		try {
			pushResult = jpushClient.sendPush(pushPayload);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
			return pushResult;
		}
		System.out.println("--------" + pushResult);
		LOG.info("发送通知结束：" + new Date());
		return pushResult;
	}

	/**
	 * 按别名发送通知（带有附加信息）
	 * 
	 * @param title
	 * @param alert
	 * @param extras
	 * @param alias
	 * @return
	 */
	@SuppressWarnings("finally")
	public static PushResult sendToAllByAliasWithExtra(String title, String alert, Map<String, String> extras, String alias) {
		PushResult pushResult = null;
		try {
			pushResult = jpushClient.sendAndroidNotificationWithAlias(title, alert, extras, alias.replaceAll("-", ""));
			return pushResult;
		} catch (Exception e) {
			e.printStackTrace();
			return pushResult;
		} finally {
			try {
				PushPayload pushPayload = PushPayload.newBuilder().setAudience(Audience.alias(alias.replaceAll("-", ""))).setPlatform(Platform.ios())
						.setNotification(Notification.newBuilder().setAlert(alert).addPlatformNotification(
								IosNotification.newBuilder().setContentAvailable(true).autoBadge().addExtra("orderId", extras.get("orderId")).build())
								.build())
						.build();
				pushResult = jpushClient.sendPush(pushPayload);
			} catch (APIConnectionException | APIRequestException e) {
				e.printStackTrace();
				return pushResult;
			}
			return pushResult;
		}
	}

	/**
	 * 按标签发送通知（带有附加消息）
	 * 
	 * @param title
	 * @param alert
	 * @param extras
	 * @param tagName
	 * @return
	 */
	public static PushResult sendToAllByTagsWithExtra(String title, String alert, Map<String, String> extras, String... tagName) {
		PushResult pushResult = null;
		try {
			PushPayload pushPayload = PushPayload.newBuilder().setAudience(Audience.tag(tagName)).setPlatform(Platform.android_ios())
					.setNotification(Notification.android(alert, title, extras)).setNotification(Notification.ios(alert, extras)).build();
			pushResult = jpushClient.sendPush(pushPayload);
			return pushResult;
		} catch (Exception e) {
			e.printStackTrace();
			return pushResult;
		}
	}

}