package com.xavier.bean;

import com.xavier.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 权限角色对应关系Bean
 *
 * @author NewGr8Player
 */
@Entity
@Table(name = "sys_role_permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends BaseEntity {

	/**
	 * 角色Id
	 */
	@Column(name = "role_id")
	private String roleId;
	/**
	 * 权限Id
	 */
	@Column(name = "permission_id")
	private String permissionId;
}
