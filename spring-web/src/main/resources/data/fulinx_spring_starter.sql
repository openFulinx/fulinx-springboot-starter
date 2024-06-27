/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : fulinx_spring_starter

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 27/06/2024 18:26:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `original_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原始文件名称',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
  `file_content_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
  `file_extension_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件扩展名',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL地址',
  `sha256` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sha256',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission_parent_id` int NOT NULL DEFAULT 0 COMMENT '权限父级ID',
  `permission_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限唯一标识符',
  `permission_type` int NOT NULL DEFAULT 1 COMMENT '权限类型：1. 节点 2. 分支',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限描述',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_permission_code`(`permission_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int NOT NULL COMMENT '用户角色ID',
  `permission_id` int NOT NULL COMMENT '权限类型',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Username',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Email',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Password',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Salt',
  `user_type` int NOT NULL DEFAULT 1 COMMENT 'User Type, 1: Normal, 9999: administrator',
  `status` int NOT NULL DEFAULT 1 COMMENT 'Status, 0: Disable, 1: Enable',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System User Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tb_system_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user_profile`;
CREATE TABLE `tb_system_user_profile`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `system_user_id` int NOT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` int NULL DEFAULT NULL COMMENT '性别',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `post` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户资料' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user_role`;
CREATE TABLE `tb_system_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `system_user_id` int NOT NULL COMMENT '用户ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id_role_id`(`system_user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
  `is_verify` int NOT NULL COMMENT '邮箱是否验证，0：未验证，1：已验证',
  `user_type` int NOT NULL DEFAULT 1 COMMENT '用户类型:1,普通用户，9999超级管理员',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态: 1: 启用,  9: 禁用, ',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_profile`;
CREATE TABLE `tb_user_profile`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` int NULL DEFAULT NULL COMMENT '性别',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `post` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '软删除标识',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `record_version` int NOT NULL DEFAULT 1 COMMENT '记录版本',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户资料' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
