package com.linkGap.projectManage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.linkGap.projectManage.model.entity.User;

/**
 * 基于mybatis xml sql 与redis 缓存注解 demo
 * @author renhengli
 *
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper1 {
	@Cacheable(keyGenerator = "keyGenerator")
	public List<User> findAll(int page, int rows);
}
