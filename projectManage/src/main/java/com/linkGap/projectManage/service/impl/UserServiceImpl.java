package com.linkGap.projectManage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkGap.projectManage.dao.UserMapper;
import com.linkGap.projectManage.dao.UserMapper1;
import com.linkGap.projectManage.model.EUDataGridResult;
import com.linkGap.projectManage.model.entity.User;
import com.linkGap.projectManage.service.UserService;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserMapper1 userMapper1;

	@Override
	public User findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public List<User> findAllUser() {
		return userMapper1.findAll(0, 0);
	}

	@Override
	public EUDataGridResult findUserPage(Integer page, Integer rows) {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<User> list = userMapper1.findAll(page, rows);
		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 取记录总条数
		PageInfo<User> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public PageInfo<User> findUserPageInfo(Integer page, Integer rows) {
		// 分页处理
		PageHelper.startPage(page, rows);
		List<User> list = userMapper1.findAll(page, rows);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int save(User user) {
		int i = userMapper.save(new User(3, "renhengli001", "renhengli1@linkgap.com", 27));
		//username超过20的长度，无法插入，报错回滚
		//int j = userMapper.save(new User(4, "renhengli0021111111111111111111111111", "renhengli2@linkgap.com", 28));
		int k = userMapper.save(new User(5, "renhengli003", "renhengli3@linkgap.com", 29));
		return 0;
	}
	
	@Override
	public User findByUserName1(String userName){
		return userMapper.findByUserName1(userName);
	}

	@Override
	public int update(User user){
		return userMapper.update(user);
	}
}
