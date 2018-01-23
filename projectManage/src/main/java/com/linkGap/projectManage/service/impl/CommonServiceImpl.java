package com.linkGap.projectManage.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

import com.alibaba.druid.util.StringUtils;
import com.linkGap.projectManage.dao.LoginLogMapper;
import com.linkGap.projectManage.dao.SysPermissionMapper;
import com.linkGap.projectManage.dao.SysUserMapper;
import com.linkGap.projectManage.model.ActiveUser;
import com.linkGap.projectManage.model.entity.LoginLog;
import com.linkGap.projectManage.model.entity.SysPermission;
import com.linkGap.projectManage.model.entity.SysRole;
import com.linkGap.projectManage.model.entity.SysUser;
import com.linkGap.projectManage.redis.RedisUtils;
import com.linkGap.projectManage.service.CommonService;
import com.linkGap.projectManage.utils.Base64Util;
import com.linkGap.projectManage.utils.JPushUtils;

/**
 * 
 * @author renhengli
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysPermissionMapper sysPermissionMapper;

	@Autowired
	private LoginLogMapper loginLogMapper;

	/**
	 * 判断是否登录正常，验证token是否有效
	 */
	public boolean judgeIfLogin(HttpServletRequest request, String username) {
		// 移动端请求必须在header中携带token请求
		String token = request.getHeader("Authorization");
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		Object obj = redisUtils.get(token);
		// 未登录/登录过期
		if (obj == null) {
			//推送消息给移动端重新登录
			LoginLog loginLog = loginLogMapper.selectLastByUsername(username);
			if (loginLog != null && !StringUtils.isEmpty(loginLog.getUniqueDeviceIdentifier())) {
				try {
					JPushUtils.sendMessageWithRegistrationID("登录踢出", "账号已经在别处登录，如非本人操作，请修改密码！", loginLog.getUniqueDeviceIdentifier());
				} catch (APIConnectionException | APIRequestException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		String loginToken = (String) obj;
		String LoginTokenValue = Base64Util.decode(loginToken);
		// token 与 登录用户不匹配
		if (!username.equals(LoginTokenValue)) {
			return false;
		} else {// token 与用户匹配
			Object au = request.getSession().getAttribute("activeUser");
			// 如果server端登录session失效，重新设置session登录信息
			if (au == null) {
				ActiveUser activeUser = new ActiveUser();
				SysUser sysUser = sysUserMapper.findByUserName(username);
				// 如果查询不到返回null
				if (sysUser == null) {
					return false;
				}
				// 账号锁定
				if ("2".equals(sysUser.getLocked())) {
					return false;
				}

				activeUser.setUserid(sysUser.getSysUserId());
				activeUser.setUsername(sysUser.getUsername());
				activeUser.setUserStatus(sysUser.getLocked());

				SysRole sysRole = sysUserMapper.findRoleByUserId(sysUser.getSysUserId());
				if (sysRole != null) {
					activeUser.setRolename(sysRole.getName());
					activeUser.setRoleStatus(sysRole.getStatus());
				}

				// 根据用户id取出菜单
				List<SysPermission> menus = sysPermissionMapper.findByAdminUserId(sysUser.getSysUserId());
				// 将用户菜单设置到activeUser
				activeUser.setMenus(menus);
				request.getSession().setAttribute("activeUser", activeUser);
			}
			return true;
		}
	}

}
