package com.xavier.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.bean.base.BaseEntity;
import com.xavier.common.util.PasswordUtil;
import lombok.*;

/**
 * 用户Bean
 *
 * @author NewGr8Player
 */
@TableName("sys_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	/**
	 * 用户名
	 */
	@TableField("username")
	private String username;
	/**
	 * 密码
	 */
	@TableField("password")
	private String password;

	/**
	 * 获取加密后的密码串
	 *
	 * @return
	 */
	public String getEncryptedPassword() {
		return PasswordUtil.EncryptPassword(this.password);
	}

}
