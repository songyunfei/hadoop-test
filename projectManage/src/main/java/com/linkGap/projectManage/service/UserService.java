package com.linkGap.projectManage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.linkGap.projectManage.model.EUDataGridResult;
import com.linkGap.projectManage.model.entity.User;

public interface UserService {
	
	public User findByUserName(String userName);

	public List<User> findAll();
	
	public List<User> findAllUser();
	
	public EUDataGridResult findUserPage(Integer page, Integer rows);
	
	public PageInfo<User> findUserPageInfo(Integer page, Integer rows);
	
	public int save(User user);
	
	public User findByUserName1(String userName);
	
	public int update(User user);

}
