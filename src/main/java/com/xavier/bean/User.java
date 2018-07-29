package com.xavier.bean;

import com.xavier.bean.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 用户Bean
 * @author NewGr8Player
 */
@Entity
@Table(name = "sys_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
}
