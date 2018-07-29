package com.xavier.bean;

import com.xavier.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 用户角色对应关系Bean
 *
 * @author NewGr8Player
 */
@Entity
@Table(name = "sys_user_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private String roleId;
}
