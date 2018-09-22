package com.xavier.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.xavier.bean.base.BaseEntity;
import com.xavier.common.util.PasswordUtil;
import lombok.*;
import org.apache.ibatis.annotations.Update;

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
     * 密码加密使用的盐
     */
    @TableField("pass_salt")
    private String passSalt;
}
