package com.linkGap.projectManage.dao;

import com.linkGap.projectManage.model.entity.LoginLog;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Integer loginLogId);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer loginLogId);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
    
    /**
     * 
    * @Title: selectLastBySysUserId  
    * @Description: 获取用户的最后一次登录记录 
    * @param sysUserId
    * @return    参数  
    * LoginLog    返回类型
     */
    LoginLog selectLastBySysUserId(Integer sysUserId);
    
    LoginLog selectLastByUsername(String username);
}