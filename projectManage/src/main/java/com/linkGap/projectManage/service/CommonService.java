package com.linkGap.projectManage.service;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {

	public boolean judgeIfLogin(HttpServletRequest request, String username);
}