package com.xavier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户Dao
 *
 * @author NewGr8Player
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

	/**
	 * 根据姓名查找用户
	 *
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM sys_user WHERE username = #{username} LIMIT 1")
	User findByUsername(@Param("username") String username);

	@Override
	@Update("UPDATE sys_user SET username = #{user.username},password=#{user.password} WHERE id = #{user.id}")
	Integer updateById(@Param("user")User user);
}
