package com.linkGap.projectManage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.linkGap.projectManage.model.entity.User;
/**
 * mybatis 注解sql与redis缓存注解使用demo
 * @author renhengli
 *
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface UserMapper {
	@Cacheable(keyGenerator = "keyGenerator")
	@Select("select * from user where userName = #{userName,jdbcType=VARCHAR}")
	User findByUserName(@Param("userName") String userName);

	@Cacheable(key = "'allUsers'",unless="#result.size()==0")
	@Results({ @Result(property = "name", column = "name"), @Result(property = "age", column = "age") })
	@Select("SELECT userName, age FROM user")
	List<User> findAll();

	@Insert("INSERT INTO USER(userId,userName, email,age) VALUES(#{userId}, #{userName},#{email}, #{age})")
	int save(User user);

	@Cacheable(key = "#p0",unless="#result == null")
	@Select("select * from user where userName = #{userName}")
	User findByUserName1(@Param("userName") String userName);

	@CacheEvict(key = "#p0.userName")
	@Update("update user set age=#{age} where userName=#{userName}")
	int update(User user);

}
