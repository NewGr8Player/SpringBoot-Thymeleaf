package com.xavier.config.Shiro;

import com.xavier.bean.*;
import com.xavier.service.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Shiro鉴权
 *
 * @author NewGr8Player
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    /**
     * 此处使用 {@code @Lazy} 注解原因
     * 不使用会影响Redis缓存的正常使用
     * https://docs.spring.io/spring-framework/docs/4.0.0.RELEASE/javadoc-api/org/springframework/context/annotation/Lazy.html
     */
    @Lazy
    @Autowired
    private UserService userService;
    @Lazy
    @Autowired
    private UserRoleService userRoleService;
    @Lazy
    @Autowired
    private RolePermissionService rolePermissionService;
    @Lazy
    @Autowired
    private RoleService roleService;
    @Lazy
    @Autowired
    private PermissionService permissionService;

    /**
     * 权限验证
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /* 获取登录用户名 */
        String userName = (String) principalCollection.getPrimaryPrincipal();
        /* 查询用户名称 */
        Optional<User> userOptional = this.userService.findByUserName(userName);
        /* 添加角色和权限 */
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<UserRole> userRoleList = this.userRoleService.findByUserId(user.getId());
            for (UserRole userRole : userRoleList) {
                /* 角色 */
                Optional<Role> roleOptional = this.roleService.findById(userRole.getRoleId());
                if (roleOptional.isPresent()) {
                    simpleAuthorizationInfo.addRole(roleOptional.get().getRoleName());
                    List<RolePermission> rolePermissionList = this.rolePermissionService.findByRoleId(roleOptional.get().getId());
                    for (RolePermission rolePermission : rolePermissionList) {
                        /* 权限 */
                        Optional<Permission> permissionOptional = this.permissionService.findById(rolePermission.getId());
                        if (permissionOptional.isPresent()) {
                            simpleAuthorizationInfo.addStringPermission(permissionOptional.get().getPermissionName());
                        }
                    }
                }
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        /* 获取用户信息 */
        String userName = authenticationToken.getPrincipal().toString();
        Optional<User> userOptional = this.userService.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new SimpleAuthenticationInfo(userName, user.getPassword(), user.getUsername());
        } else {
            return null;
        }
    }
}
