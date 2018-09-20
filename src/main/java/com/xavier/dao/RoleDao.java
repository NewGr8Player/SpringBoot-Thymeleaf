package com.xavier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xavier.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 角色Dao
 *
 * @author NewGr8Player
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

	/**
	 * 根据Id查找角色
	 *
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM sys_role WHERE id = #{userId}")
	Role findById(@Param("id") String id);

}
