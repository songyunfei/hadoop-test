package com.linkGap.projectManage.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.linkGap.projectManage.anotation.OperationLogAnotation;
import com.linkGap.projectManage.dao.SysLogMapper;
import com.linkGap.projectManage.model.ActiveUser;
import com.linkGap.projectManage.model.entity.SysLog;

/**
 * 日志切面
 * @author renhengli
 *
 */
@Aspect
@Order(5)
@Component
public class WebLogAspect {

	private Logger logger = Logger.getLogger(getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Autowired
	private SysLogMapper sysLogDao;

	@Pointcut(value = "execution(* com.linkGap.projectManage.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	}

	@AfterReturning(pointcut = "webLog()", returning = "ret")
	public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	}

	/**
	 * 记录登录后的用户的操作日志（前提需要配置日志注解）
	 * @param pj
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "webLog()")
	public Object doAround(ProceedingJoinPoint pj) throws Throwable {
		MethodSignature signature = (MethodSignature) pj.getSignature();
		Method method = signature.getMethod();
		Object[] args = pj.getArgs();
		Object result = pj.proceed(args);
		OperationLogAnotation[] operationLogAnotations = method.getDeclaredAnnotationsByType(OperationLogAnotation.class);
		if (operationLogAnotations.length > 0) {
			System.out.println("moduleName:" + operationLogAnotations[0].moduleName());
			System.out.println("option:" + operationLogAnotations[0].operation());
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			Object req = request.getSession().getAttribute("activeUser");
//			Session session = SecurityUtils.getSubject().getSession();
//			Object req = session.getAttribute("activeUser");
			if (req == null) {//如果未登录，操作日志记录无意义
				return result;
			} else {
				ActiveUser user = (ActiveUser) req;
				Object[] objects = pj.getArgs();
				StringBuilder requestBody = new StringBuilder();
				if (objects != null && objects.length > 0) {
					for (Object object : objects) {
						if (object == null) {
							//requestBody = null;// POST接口参数为空 比如删除XXX
						} else if (object instanceof String) {
							requestBody.append((String) object).append(",");// 有些接口直接把参数转换成对象了
						} else if (object instanceof Long ) {
							requestBody.append(String.valueOf(object)).append(",");
						} else if (object instanceof Integer ) {
							requestBody.append(String.valueOf(object)).append(",");
						} else if (object instanceof Boolean ) {
							requestBody.append(String.valueOf(object)).append(",");
						}/* else {
							requestBody.append(JSONObject.toJSONString(object)).append(",");
						}*/
					}
				}
				Object resultJson = JSONObject.toJSON(result);
				SysLog sysLog = new SysLog();
				sysLog.setOperator(user.getUserid());
				sysLog.setRequestIp(getIpAddress(request));
				sysLog.setRequestUrl(request.getRequestURL().toString());
				// sysLog.setEventType(logTypeEnum.getKey());
				sysLog.setEventDesc(operationLogAnotations[0].operation());
				sysLog.setMoudleName(operationLogAnotations[0].moduleName());
				sysLog.setRequest(requestBody==null ? "" :requestBody.toString());
				sysLog.setResponse(JSONObject.toJSONString(resultJson));
				sysLog.setCreateTime(new Date());
				sysLogDao.save(sysLog);
			}

		}
		return result;
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
