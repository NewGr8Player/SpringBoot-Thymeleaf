/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : db_minion

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 03/10/2018 10:18:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键Id',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父级Id',
  `menu_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型(model:模块.menu:菜单)',
  `menu_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单编码',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `menu_icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `menu_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单链接',
  `menu_order` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单顺序',
  `visiable` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '是否可见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', 'channel', 'root', '首页', '', '/index', '0', 'show');
INSERT INTO `sys_menu` VALUES ('2', '0', 'model', 'sys', '系统设置', 'layui-icon-set-sm', '', '1', 'show');
INSERT INTO `sys_menu` VALUES ('3', '2', 'model', 'role', '角色管理', 'layui-icon-auz', '/sys/role', '1', 'show');
INSERT INTO `sys_menu` VALUES ('31', '2', 'model', 'role.form', '角色表单', 'layui-icon-auz', '/sys/role/form', '3', 'hidden');
INSERT INTO `sys_menu` VALUES ('4', '2', 'model', 'menu', '菜单管理', 'layui-icon-menu-fill', '/sys/menu', '2', 'show');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '主键Id',
  `permission_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '权限名称',
  `permission_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '权限编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '首页', 'sys:root:index');
INSERT INTO `sys_permission` VALUES ('2', '系统管理', 'sys:set:view');
INSERT INTO `sys_permission` VALUES ('3', '权限管理', 'sys:role:view');
INSERT INTO `sys_permission` VALUES ('31', '权限表单', 'sys:role:form');

-- ----------------------------
-- Table structure for sys_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_menu`;
CREATE TABLE `sys_permission_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键Id',
  `permission_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限Id',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_menu
-- ----------------------------
INSERT INTO `sys_permission_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_permission_menu` VALUES ('2', '2', '2');
INSERT INTO `sys_permission_menu` VALUES ('3', '3', '3');
INSERT INTO `sys_permission_menu` VALUES ('4', '31', '31');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '主键Id',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `sys_role` VALUES ('97864573054353427', 'test', '5ce58f65-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353428', 'test', '5d04b805-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353429', 'test', '5d1d1b84-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353430', 'test', '5d327954-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353431', 'test', '5d48d00a-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353432', 'test', '5d5cd0e3-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353433', 'test', '5d74c744-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353434', 'test', '5d8cab89-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353435', 'test', '5da31aea-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353436', 'test', '5db9818b-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353437', 'test', '5dd39027-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353438', 'test', '5deac768-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353439', 'test', '5e018f93-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353440', 'test', '5e17e259-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353441', 'test', '5e30c52c-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353442', 'test', '5e482b17-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353443', 'test', '5e610ece-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353444', 'test', '5e77e63f-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353445', 'test', '5e907a25-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353446', 'test', '5ea9468f-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353447', 'test', '5ec2413a-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353448', 'test', '5edaa9c1-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353449', 'test', '5ef1c735-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353450', 'test', '5f0a0d34-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353451', 'test', '5f20eeb2-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353452', 'test', '5f3a367a-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353453', 'test', '5f50420c-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353454', 'test', '5f694f6b-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('97864573054353455', 'test', '5f7aa288-c3e0-11e8-95ac-509a4c2ba51a');
INSERT INTO `sys_role` VALUES ('9a5c2a120e4f43418383fb61ed80e0ee', '2', '2');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '主键Id',
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色Id',
  `permission_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '权限Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '31');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '主键Id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('7d67d3f8b72b43ce9890d46eadccd819', 'admin', '$2a$10$ztkGyZhJ36NkGHKey9Q3..dBK/JF3UxhpFZn85/4.f6yMGBGF.2FC', '呦呵丶晓晓');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '主键Id',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '用户Id',
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '7d67d3f8b72b43ce9890d46eadccd819', '1');

SET FOREIGN_KEY_CHECKS = 1;
