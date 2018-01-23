
package com.linkGap.projectManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.model.ActiveUser;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.entity.LoginLog;
import com.linkGap.projectManage.redis.RedisUtils;
import com.linkGap.projectManage.service.SysUserService;
import com.linkGap.projectManage.utils.Base64Util;
import com.linkGap.projectManage.utils.JPushUtils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import sun.misc.BASE64Encoder;

/**
 * @author renhengli
 * @date 2018年1月10日
 * @version 1.0
 */
@Controller
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 
	 * @Title: login
	 * @Description: 登录
	 * @param request
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param uniqueDeviceIdentifier
	 *            登录设备唯一标识符
	 * @param model
	 * @return 参数 ReturnResultUtil 返回类型
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	@OperationLogAnotation(moduleName = "用户登录", operation = "用户登录")
	public ReturnResultUtil login(HttpServletRequest request, @RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "uniqueDeviceIdentifier", required = true) String uniqueDeviceIdentifier) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return new ReturnResultUtil("01", "用户名或密码不能为空！");
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
			// 判断uniqueDeviceIdentifier是否和上一次登录设备唯一标识符相同，如果不相同，则推送消息到原来的设备，告知在别的终端登录，并自动清空之前设备的登录信息
			LoginLog loginLog = sysUserService.selectLastBySysUserId(activeUser.getUserid());
			if (loginLog != null) {
				if (StringUtils.isNotEmpty(loginLog.getUniqueDeviceIdentifier())
						&& !uniqueDeviceIdentifier.equals(loginLog.getUniqueDeviceIdentifier())) {
					try {
						JPushUtils.sendMessageWithRegistrationID("登录踢出", "账号已经在别处登录，如非本人操作，请修改密码！", username, loginLog.getUniqueDeviceIdentifier());
					} catch (APIConnectionException | APIRequestException e) {
						e.printStackTrace();
					}
				}
			}

			// 2 登录成功后要记录登录日志
			int i = sysUserService.saveLoginLog(activeUser.getUserid(), uniqueDeviceIdentifier, getIpAddress(request));
			if (i != 1) {
				logger.error("用户：" + username + "登录日志记录失败");
			}
			Map<String, String> extras = new HashMap<String, String>();
			String token1 = new SimpleHash("md5", username, ByteSource.Util.bytes("linkgap"), 3).toHex();
			// 把token放入redis缓存中，token作为key，value为base64后的username
			redisUtils.set(token1, Base64Util.encode(username));
			extras.put("token", token1);
			return new ReturnResultUtil("00", "登录成功！", activeUser, extras);
		} catch (LockedAccountException lae) {
			token.clear();
			return new ReturnResultUtil("01", "用户已经被锁定不能登录，请与管理员联系！");
		} catch (UnknownAccountException e) {
			token.clear();
			return new ReturnResultUtil("01", "用户不存在！");
		} catch (AuthenticationException e) {
			token.clear();
			return new ReturnResultUtil("01", "用户或密码不正确！");
		}
	}

	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
