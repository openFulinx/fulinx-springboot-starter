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

 Date: 28/06/2024 15:56:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'File Id',
  `original_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Original File Name',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'File Name',
  `file_content_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'File Content Type',
  `file_extension_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'File Extension Name',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Path',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'File Url',
  `sha256` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sha256',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'File Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_file
-- ----------------------------

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Permission Id',
  `permission_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Permission Unique Identifier',
  `permission_parent_id` int NOT NULL DEFAULT 0 COMMENT 'Permission Parent Id',
  `permission_type` int NOT NULL DEFAULT 1 COMMENT 'Permission Type: 1. Node 2. Branch',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_permission_code`(`permission_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Permission Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES (1, 'sys:role', 0, 1, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (2, 'sys:role:pagination', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (3, 'sys:role:list', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (4, 'sys:role:add', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (5, 'sys:role:remove', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (6, 'sys:role:update', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (7, 'sys:role:show', 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (8, 'sys:user', 0, 1, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (9, 'sys:user:pagination', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (10, 'sys:user:list', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (11, 'sys:user:add', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (12, 'sys:user:remove', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (13, 'sys:user:update', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (14, 'sys:user:show', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (15, 'sys:user:updatePassword', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_permission` VALUES (16, 'sys:user:resetPassword', 8, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Role Id',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Role Name',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Role Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'Administrator', 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission_relation`;
CREATE TABLE `tb_role_permission_relation`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Role Permission Id',
  `role_id` int NOT NULL COMMENT 'Role Id',
  `permission_id` int NOT NULL COMMENT 'Permission Id',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Role Permission Relation Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role_permission_relation
-- ----------------------------
INSERT INTO `tb_role_permission_relation` VALUES (1, 1, 1, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (2, 1, 2, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (3, 1, 3, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (4, 1, 4, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (5, 1, 5, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (6, 1, 6, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (7, 1, 7, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (8, 1, 8, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (9, 1, 9, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (10, 1, 10, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (11, 1, 11, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (12, 1, 12, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (13, 1, 13, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (14, 1, 14, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (15, 1, 15, 0, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `tb_role_permission_relation` VALUES (16, 1, 16, 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_system_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'System User Id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Username',
  `email` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Email',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Password',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Salt',
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System User Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES (1, 'admin', 'admin@example.com', '$2a$10$G5rkSW/ykcZLRHHcyjNk2edjP4pfjkEs3571gqsbYRa0gAABboia.', '1719546327794', 9999, 1, 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_system_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user_profile`;
CREATE TABLE `tb_system_user_profile`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'System User Profile Id',
  `system_user_id` int NOT NULL COMMENT 'User Id',
  `first_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'First Name',
  `last_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Last Name',
  `gender` int NULL DEFAULT NULL COMMENT 'Gender, 1 - Male, 2 - Female',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Telephone',
  `post` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Post',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System User Profile Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_system_user_profile
-- ----------------------------
INSERT INTO `tb_system_user_profile` VALUES (1, 1, 'admin', 'admin', NULL, NULL, NULL, 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_system_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user_role_relation`;
CREATE TABLE `tb_system_user_role_relation`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'System User Role Id',
  `system_user_id` int NOT NULL COMMENT 'System User Id',
  `role_id` int NOT NULL COMMENT '角色ID',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'SOft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_id_role_id`(`system_user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'System User And Role Relationship Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_system_user_role_relation
-- ----------------------------
INSERT INTO `tb_system_user_role_relation` VALUES (1, 1, 1, 0, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'User Id',
  `email` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Email',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Password',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Salt',
  `is_email_verify` int NOT NULL COMMENT 'Email Verification Status: 0 - Unverified, 1 - Verified',
  `user_type` int NOT NULL DEFAULT 1 COMMENT 'User Type: 1 - Regular User, 9999 - Super Administrator',
  `status` int NOT NULL DEFAULT 1 COMMENT 'Status: 1 - Enabled, 9 - Disabled',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'User Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_profile`;
CREATE TABLE `tb_user_profile`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'User Profile Id',
  `user_id` int NOT NULL COMMENT 'User Id',
  `first_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'First Name',
  `last_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Last Name',
  `gender` int NULL DEFAULT NULL COMMENT 'Gender, 1 - Male, 2 - Female',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Telephone',
  `post` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Post',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT 'Soft Delete Flag',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
  `record_version` int NOT NULL DEFAULT 1 COMMENT 'Record Version',
  `record_create_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Create Name',
  `record_update_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Record Update Name',
  `record_create_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Create Time',
  `record_update_time` datetime(6) NULL DEFAULT NULL COMMENT 'Record Update Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'User Profile Table' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_profile
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
