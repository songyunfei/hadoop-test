package com.linkGap.projectManage.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.linkGap.projectManage.model.ActiveUser;
import com.linkGap.projectManage.model.entity.SysPermission;
import com.linkGap.projectManage.model.entity.SysRole;
import com.linkGap.projectManage.model.entity.SysUser;
import com.linkGap.projectManage.model.view.UserView;
import com.linkGap.projectManage.service.PermissionService;
import com.linkGap.projectManage.service.SysUserService;

/**
 * 
 * @author renhengli
 *
 */
public class MyShiroRealm extends AuthorizingRealm {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SysUserService userService;

	@Resource
	private PermissionService resourcesService;
	
	/**
	 * realm的认证方法，从数据库查询用户信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// token是用户输入的用户名和密码
		// 第一步从token中取出用户名
		String username = (String) token.getPrincipal();

		// 第二步：根据用户输入的username从数据库查询
		SysUser sysUser = null;

		try {
			sysUser = userService.findByUserName(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 如果查询不到返回null
		if (sysUser == null) {
			throw new UnknownAccountException();// 用户不存在
		}
		if ("2".equals(sysUser.getLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		String password = sysUser.getPassword();

		// 如果查询到返回认证信息AuthenticationInfo
		// activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();

		activeUser.setUserid(sysUser.getSysUserId());
		activeUser.setUsername(sysUser.getUsername());
		activeUser.setUserStatus(sysUser.getLocked());

		SysRole sysRole = userService.findRoleByUserId(sysUser.getSysUserId());
		if (sysRole != null) {
			activeUser.setRolename(sysRole.getName());
			activeUser.setRoleStatus(sysRole.getStatus());
		}

		//查询登录用户所属门店和公司放入activeUser
		UserView userView = userService.getUserRoleInfo(sysUser.getSysUserId());
		if(userView != null){
			activeUser.setCustomerId(userView.getCustomerId());
			activeUser.setCustomerShopId(userView.getCustomerShopId());
		}
		// 根据用户id取出菜单
		List<SysPermission> menus = null;
		try {
			// 通过service取出菜单
			menus = resourcesService.findByAdminUserId(sysUser.getSysUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将用户菜单设置到activeUser
		activeUser.setMenus(menus);

		// ByteSource q = ByteSource.Util.bytes(sysUser.getSalt());
		// 将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password, ByteSource.Util.bytes(username),
				this.getName());
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("activeUser", activeUser);
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//		request.getSession().setAttribute("activeUser", activeUser);
		return simpleAuthenticationInfo;
	}

	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 从 principals获取主身份信息
		// 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();

		// 根据身份信息获取权限信息
		// 从数据库获取到权限数据
		List<SysPermission> permissionList = null;

		try {
			permissionList = resourcesService.findByAdminUserId(activeUser.getUserid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 单独定一个集合对象
		List<String> permissions = new ArrayList<String>();
		if (permissionList != null) {
			for (SysPermission sysPermission : permissionList) {
				// 将数据库中的权限标签 符放入集合
				if (sysPermission.getUrl() != null && StringUtils.isNotEmpty(sysPermission.getUrl())) {
					permissions.add(sysPermission.getUrl());
				}
			}
		}

		// 查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}

	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {

		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
