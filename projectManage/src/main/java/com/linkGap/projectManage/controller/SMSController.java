
package com.linkGap.projectManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.linkGap.projectManage.model.ReturnResultUtil;
import com.linkGap.projectManage.model.view.IdentifyCode;
import com.linkGap.projectManage.sms.SmsService;

/**
 * @author renhengli
 * @date 2018年1月15日
 * @version 1.0
 */
@RestController
public class SMSController {

	private Logger logger = Logger.getLogger(HomeController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sendSMSCode", method = RequestMethod.GET)
	public ReturnResultUtil sendCode(HttpServletRequest request, @RequestParam(value = "mobile") String mobile) {
		try {
			HttpSession session = request.getSession();
			if (validateRedoSendCode(session, mobile)) {
				return new ReturnResultUtil("01", "系统繁忙，请勿重复发送！");
			}
			// 发送短信，获取code，将code放入session
			int code = randomCode();
			String rel = SmsService.sendSmsRegisterCode(mobile, code);
			if (rel != null && rel.length() > 0) {
				// 发送失败，返回错误信息
				logger.info("发送失败》" + rel);
				return new ReturnResultUtil("01", "短信发送失败！");
			}
			Map<String, Object> identifyCodes = (Map<String, Object>) session.getAttribute("IdentifyCodes");
			if (identifyCodes == null) {
				identifyCodes = new HashMap<>();
			}
			IdentifyCode identifyCode = new IdentifyCode();
			identifyCode.setCode(code + "");
			identifyCode.setSendCodeTime(System.currentTimeMillis());
			// 设置过期时间为五分钟
			identifyCode.setExpired_time(identifyCode.getSendCodeTime() + (15 * 60000));
			identifyCodes.put(mobile, identifyCode);
			session.setAttribute("IdentifyCodes", identifyCodes);
			return new ReturnResultUtil();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new ReturnResultUtil("01", "短信发送失败！");
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/validateSMSCode")
	public ReturnResultUtil validateSMSCode(HttpServletRequest request, @RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "validateCode", defaultValue = "") String validateCode) {
		try {
			HttpSession sessionInfoss = request.getSession();
			Map<String, Object> identifyCodes = (Map<String, Object>) sessionInfoss.getAttribute("IdentifyCodes");
			IdentifyCode identifyCode = (IdentifyCode) identifyCodes.get(mobile);
			if (identifyCode == null) {
				return new ReturnResultUtil("01", "验证码不正确");
			}
			String sessionCode = identifyCode.getCode();
			if (identifyCode.getExpired_time() < System.currentTimeMillis()) {
				// 超时，删除code
				identifyCodes.remove(mobile);
				sessionInfoss.setAttribute("IdentifyCodes", identifyCodes);
				return new ReturnResultUtil("01", "验证码过期 ");
			}
			if (!validateCode.equals(sessionCode)) {
				return new ReturnResultUtil("01", "验证码不正确");
			}
			return new ReturnResultUtil("00", "验证成功");
		} catch (Exception e) {
			logger.error("验证失败", e);
			e.printStackTrace();
			return new ReturnResultUtil("01", "系统异常 ");
		}
	}

	/**
	 * @Title: validateRedoSendCode
	 * @Description: 重复发送验证码校验（20s内）
	 * @param sessionInfo
	 * @param cellNum
	 * @return 参数 Boolean 返回类型
	 */
	@SuppressWarnings("unchecked")
	private Boolean validateRedoSendCode(HttpSession sessionInfo, String cellNum) {
		Map<String, Object> identifyCodes = (Map<String, Object>) sessionInfo.getAttribute("IdentifyCodes");
		if (null != identifyCodes && identifyCodes.size() > 0 && !StringUtils.isEmpty(cellNum)) {
			IdentifyCode identifyCode = (IdentifyCode) identifyCodes.get(cellNum);
			if (null != identifyCode) {
				if (System.currentTimeMillis() - identifyCode.getSendCodeTime() <= 20000) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 生成四位随机数
	 * 
	 * @return
	 */
	private int randomCode() {
		return (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
	}
}
