/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mall_admin

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 04/01/2021 20:37:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实名字',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `department_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `status` tinyint(0) NOT NULL COMMENT '在职状态1:在职 2离职',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登陆账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '加密后的密码',
  `password_salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码的盐',
  `create_admin_id` int(0) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建 IP',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '马红涛', NULL, 1, 1, '15088661964', '$2a$10$x48u8a91Y/QxgToAw8q/cuyJOFRiuKl.mebP0q/CfSgov8zcFZiVq', '$2a$10$x48u8a91Y/QxgToAw8q/cu', 0, NULL, '2020-12-29 15:50:51', '2020-12-29 15:51:40');
INSERT INTO `admin` VALUES (6, '马红涛', NULL, 1, 1, '15136212594', '$2a$10$3uoJgEflYma7UgmL2xSrFe6.tRER2VtkoV/YmKcGrOA0/XSP6at3q', '$2a$10$3uoJgEflYma7UgmL2xSrFe', NULL, NULL, '2020-12-29 22:12:28', '2020-12-29 22:12:28');

-- ----------------------------
-- Table structure for admin_department
-- ----------------------------
DROP TABLE IF EXISTS `admin_department`;
CREATE TABLE `admin_department`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序字段',
  `pid` int(0) NOT NULL DEFAULT 0 COMMENT '父级部门编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_department
-- ----------------------------
INSERT INTO `admin_department` VALUES (1, '测试部门', 0, 0, '2020-07-14 13:19:40', '2020-07-14 13:19:40', b'0');
INSERT INTO `admin_department` VALUES (2, '技术部', 1, 0, '2020-07-14 13:51:02', '2020-07-14 13:51:02', b'0');
INSERT INTO `admin_department` VALUES (3, '测试小组10', 1, 1, '2020-07-14 13:51:09', '2020-07-14 13:51:30', b'0');
INSERT INTO `admin_department` VALUES (4, '测试小组2', 2, 1, '2020-07-14 13:51:14', '2020-07-14 13:51:14', b'0');
INSERT INTO `admin_department` VALUES (5, '测试小组3', 3, 1, '2020-07-14 13:51:21', '2020-07-14 13:51:21', b'0');
INSERT INTO `admin_department` VALUES (6, '技术部1', 2, 1, '2020-12-05 19:32:40', '2020-12-05 19:32:40', b'0');
INSERT INTO `admin_department` VALUES (7, '技术部2', 2, 1, '2020-12-05 19:32:51', '2020-12-05 19:32:51', b'0');
INSERT INTO `admin_department` VALUES (8, '技术部3', 2, 1, '2020-12-05 19:32:54', '2020-12-05 19:32:54', b'0');

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int(0) NOT NULL,
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `checksum` int(0) NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `installed_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(0) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1.0.0', '20201231', 'SQL', 'V1.0.0__20201231.sql', 2011660869, 'root', '2020-12-31 17:38:47', 73, 1);

-- ----------------------------
-- Table structure for flyway_test2
-- ----------------------------
DROP TABLE IF EXISTS `flyway_test2`;
CREATE TABLE `flyway_test2`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_test2
-- ----------------------------
INSERT INTO `flyway_test2` VALUES (1, 'Jone', 18, 'test1@baomidou.com');
INSERT INTO `flyway_test2` VALUES (2, 'Jack', 20, 'test2@baomidou.com');
INSERT INTO `flyway_test2` VALUES (3, 'Tom', 28, 'test3@baomidou.com');
INSERT INTO `flyway_test2` VALUES (4, 'Sandy', 21, 'test4@baomidou.com');
INSERT INTO `flyway_test2` VALUES (5, 'Billie', 24, 'test5@baomidou.com');

-- ----------------------------
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问令牌',
  `user_id` int(0) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(0) NOT NULL COMMENT '用户类型',
  `refresh_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '刷新令牌',
  `expires_time` datetime(0) NOT NULL COMMENT '过期时间',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建 IP',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE,
  INDEX `idx_refreshToken`(`refresh_token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '访问令牌' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_access_token
-- ----------------------------
INSERT INTO `oauth2_access_token` VALUES ('06252591ffa24606924a69e599af63e4', 6, 2, 'b7bd9c1e944943bf86829fa789d8d0a9', '2020-12-30 04:09:17', '172.16.16.128', '2020-12-30 11:21:16', '2020-12-30 11:21:16', b'0');
INSERT INTO `oauth2_access_token` VALUES ('09094950e47a4c6e8c6836cca7c5e7cc', 1, 2, 'd0a08adbb2a0485cbb65c0ac1aa81dc1', '2020-12-30 04:36:38', '172.16.16.128', '2020-12-30 11:48:38', '2020-12-30 11:48:38', b'0');
INSERT INTO `oauth2_access_token` VALUES ('52e6098e54ed4f6ea6bce3daaef979f5', 1, 2, '46ec65ca0bcf4fcd97f718372199f4b9', '2021-01-04 07:55:07', '172.16.16.128', '2021-01-04 15:07:07', '2021-01-04 15:07:07', b'0');
INSERT INTO `oauth2_access_token` VALUES ('7a900d41aee64860b670c84d89f8af21', 1, 2, 'aa8df5652d804d62b0b0b349c4da388b', '2020-12-30 04:07:47', '172.16.16.128', '2020-12-30 11:19:47', '2020-12-30 11:19:47', b'0');
INSERT INTO `oauth2_access_token` VALUES ('7ab6b84677cc4010a0b223fb2eb82fad', 6, 2, 'ac25d25f8b3e4972936805cc1e2365d7', '2021-01-04 07:26:28', '172.16.16.128', '2021-01-04 14:38:28', '2021-01-04 14:38:28', b'0');
INSERT INTO `oauth2_access_token` VALUES ('926fdd6010d04dbabf2568facc500761', 1, 2, '15f913a08d934b6fbc0ccbf8ea01d4a6', '2021-01-04 08:10:37', '172.16.16.128', '2021-01-04 15:22:36', '2021-01-04 15:22:36', b'0');
INSERT INTO `oauth2_access_token` VALUES ('ac77a3ea47ff433aa47311826466a6d0', 1, 2, '317c5b9cf5d948cba77655811da3fb7f', '2020-12-30 04:36:12', '172.16.16.128', '2020-12-30 11:48:12', '2020-12-30 11:48:12', b'0');

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号，刷新令牌',
  `user_id` int(0) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(0) NOT NULL COMMENT '用户类型',
  `expires_time` datetime(0) NOT NULL COMMENT '过期时间',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建 IP',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '刷新令牌' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_refresh_token
-- ----------------------------
INSERT INTO `oauth2_refresh_token` VALUES ('036a2971ca4040269cb48e6f55033655', 6, 2, '2020-12-30 02:24:50', '192.168.1.4', '2020-12-29 22:24:49', '2020-12-29 22:24:49', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('151b82a762c24cbe9b3b36c0ad03bc0f', 1, 2, '2020-12-10 20:41:22', '127.0.0.1', '2020-12-10 16:41:21', '2020-12-10 16:41:21', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('15f913a08d934b6fbc0ccbf8ea01d4a6', 1, 2, '2021-01-04 19:22:36', '172.16.16.128', '2021-01-04 15:22:36', '2021-01-04 15:22:36', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('1658512541e94ec49f4761e53015320d', 1, 2, '2020-12-10 22:14:31', '127.0.0.1', '2020-12-10 18:14:30', '2020-12-10 18:14:30', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('2dbde81421c64371aeb7827a35b83fb0', 6, 2, '2020-12-30 02:28:39', '192.168.1.4', '2020-12-29 22:28:38', '2020-12-29 22:28:38', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('303284cfd8ec4cd8883d8d4fd380df2a', 1, 2, '2020-12-11 14:00:00', '0:0:0:0:0:0:0:1', '2020-12-11 10:00:00', '2020-12-11 10:00:00', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('317c5b9cf5d948cba77655811da3fb7f', 1, 2, '2020-12-30 15:48:12', '172.16.16.128', '2020-12-30 11:48:12', '2020-12-30 11:48:12', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('348928484d324843a45e5eb724ed37dc', 1, 2, '2020-12-29 21:36:38', '172.16.16.128', '2020-12-29 17:36:38', '2020-12-29 17:36:38', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('46317b0557094c3ba98819a77a477b64', 1, 2, '2020-12-29 21:37:01', '172.16.16.128', '2020-12-29 17:37:01', '2020-12-29 17:37:01', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('46ec65ca0bcf4fcd97f718372199f4b9', 1, 2, '2021-01-04 19:07:07', '172.16.16.128', '2021-01-04 15:07:07', '2021-01-04 15:07:07', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('489a264d9af249c1acb5f779ad34bb75', 6, 2, '2020-12-30 02:23:51', '192.168.1.4', '2020-12-29 22:23:50', '2020-12-29 22:23:50', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('581e579091b04675abecfdf2e4925e6a', 6, 2, '2020-12-30 02:12:45', '192.168.1.4', '2020-12-29 22:12:44', '2020-12-29 22:12:44', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('6d834440e44545b9a56a7287246c6673', 1, 2, '2020-12-11 14:00:41', '0:0:0:0:0:0:0:1', '2020-12-11 10:00:40', '2020-12-11 10:00:40', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('6f24695323654a59b1192975cb2e19c9', 1, 2, '2020-12-29 21:35:40', '172.16.16.128', '2020-12-29 17:35:39', '2020-12-29 17:35:39', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('70f0e685bb404b3e81de52f57cfc7ba4', 1, 2, '2020-12-11 14:08:28', '0:0:0:0:0:0:0:1', '2020-12-11 10:08:28', '2020-12-11 10:08:28', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('7c29ded05bb84c8588805a51e23a9f37', 6, 2, '2020-12-30 02:47:43', '192.168.1.4', '2020-12-29 22:47:42', '2020-12-29 22:47:42', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('905d6a5f9d10496bb2dcaee0e1ecd6ba', 1, 2, '2020-12-11 18:34:24', '0:0:0:0:0:0:0:1', '2020-12-11 14:34:24', '2020-12-11 14:34:24', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('9dc1f411c0f74006a3075c5e00545e5f', 1, 2, '2020-12-11 14:01:24', '0:0:0:0:0:0:0:1', '2020-12-11 10:01:24', '2020-12-11 10:01:24', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('a1e3c331f3404b48941f0283d4c9a6e3', 1, 2, '2020-12-30 02:28:28', '192.168.1.4', '2020-12-29 22:28:28', '2020-12-29 22:28:28', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('aa054d749d7c42c29037a01cf01c4ff1', 1, 2, '2020-12-30 02:22:11', '192.168.1.4', '2020-12-29 22:22:11', '2020-12-29 22:22:11', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('aa8df5652d804d62b0b0b349c4da388b', 1, 2, '2020-12-30 15:19:47', '172.16.16.128', '2020-12-30 11:19:47', '2020-12-30 11:19:47', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('ac25d25f8b3e4972936805cc1e2365d7', 6, 2, '2021-01-04 18:38:28', '172.16.16.128', '2021-01-04 14:38:28', '2021-01-04 14:38:28', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('b7bd9c1e944943bf86829fa789d8d0a9', 6, 2, '2020-12-30 15:21:17', '172.16.16.128', '2020-12-30 11:21:16', '2020-12-30 11:21:16', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('b7cd549014ce41d1a083bdf204b475d3', 1, 2, '2020-12-11 14:08:52', '0:0:0:0:0:0:0:1', '2020-12-11 10:08:51', '2020-12-11 10:08:51', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('c194d83034bc45618bcc0df46c624e61', 1, 2, '2020-12-30 02:07:03', '192.168.1.4', '2020-12-29 22:07:03', '2020-12-29 22:07:03', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('cfec076ce37f42e1a423a2ac65c9f666', 1, 2, '2020-12-30 02:25:01', '192.168.1.4', '2020-12-29 22:25:01', '2020-12-29 22:25:01', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('d0a08adbb2a0485cbb65c0ac1aa81dc1', 1, 2, '2020-12-30 15:48:38', '172.16.16.128', '2020-12-30 11:48:38', '2020-12-30 11:48:38', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('d484bd61d48f47269e6d6a4df464fa35', 6, 2, '2020-12-30 02:21:47', '192.168.1.4', '2020-12-29 22:21:46', '2020-12-29 22:21:46', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('d753af1e06f94e6282db387f5178131e', 3, 2, '2020-12-09 21:41:15', '127.0.0.1', '2020-12-09 17:41:15', '2020-12-09 17:41:15', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('d8e8a37bbe7f4cc09679ce65a8e84026', 1, 2, '2020-12-30 02:23:43', '192.168.1.4', '2020-12-29 22:23:43', '2020-12-29 22:23:43', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('e8d5ab304ea4438e9ea2166b382f2f4e', 1, 2, '2020-12-11 14:09:18', '0:0:0:0:0:0:0:1', '2020-12-11 10:09:18', '2020-12-11 10:09:18', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('ecf7f00daa3c42fd913cd5fdb97b37ee', 2, 2, '2020-12-09 21:37:33', '127.0.0.1', '2020-12-09 17:37:33', '2020-12-09 17:37:33', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('f52916ca7fde4f5d88eb5ead67e04c04', 1, 2, '2020-12-30 02:07:31', '192.168.1.4', '2020-12-29 22:07:31', '2020-12-29 22:07:31', b'0');

-- ----------------------------
-- Table structure for permission_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_admin_role`;
CREATE TABLE `permission_admin_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(0) NOT NULL COMMENT '管理员编号',
  `role_id` int(0) NOT NULL COMMENT '角色编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_admin_role
-- ----------------------------
INSERT INTO `permission_admin_role` VALUES (1, 1, 1, '2020-12-29 15:52:39', '2020-12-29 15:52:43', b'0');
INSERT INTO `permission_admin_role` VALUES (2, 6, 2, '2021-01-04 14:59:09', '2021-01-04 14:59:09', b'0');

-- ----------------------------
-- Table structure for permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource`;
CREATE TABLE `permission_resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` int(0) NOT NULL COMMENT '资源类型1：菜单，2:按钮',
  `sort` int(0) NOT NULL COMMENT '排序',
  `pid` int(0) NOT NULL DEFAULT 0 COMMENT '父级资源编号(外键：{@link ResourceDO#id})',
  `route` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `view` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端界面',
  `create_admin_id` int(0) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_resource
-- ----------------------------
INSERT INTO `permission_resource` VALUES (1, '系统管理', '', 1, 8, 0, '', '', NULL, 0, '2019-03-10 03:59:07', '2020-12-29 14:53:34', b'0');
INSERT INTO `permission_resource` VALUES (2, '部门管理', '', 1, 1, 0, '', '', '', NULL, '2020-12-29 14:55:11', '2020-12-29 14:55:11', b'0');
INSERT INTO `permission_resource` VALUES (3, '权限管理', '', 1, 1, 0, '', '', '', NULL, '2020-12-29 14:58:03', '2020-12-29 14:58:03', b'0');
INSERT INTO `permission_resource` VALUES (4, '资源管理', '', 1, 1, 0, '', '', '', NULL, '2020-12-29 14:58:26', '2020-12-29 14:58:26', b'0');
INSERT INTO `permission_resource` VALUES (5, '角色管理', '', 1, 1, 0, '', '', '', NULL, '2020-12-29 14:58:37', '2020-12-29 14:58:37', b'0');
INSERT INTO `permission_resource` VALUES (6, '新增部门', '/dept/create', 2, 1, 2, NULL, 'add', NULL, NULL, '2020-12-29 15:05:22', '2020-12-30 14:56:56', b'0');
INSERT INTO `permission_resource` VALUES (7, '部门列表', '/dept/list', 2, 1, 2, '', 'list', '', NULL, '2020-12-29 15:05:45', '2020-12-30 14:57:00', b'0');
INSERT INTO `permission_resource` VALUES (8, '修改部门', '/dep/tupdate', 2, 1, 2, NULL, NULL, NULL, NULL, '2020-12-29 15:07:08', '2020-12-30 14:57:08', b'0');
INSERT INTO `permission_resource` VALUES (9, '删除部门', '/dept/delete', 2, 1, 2, NULL, NULL, NULL, NULL, '2020-12-29 15:07:34', '2020-12-30 14:57:14', b'0');
INSERT INTO `permission_resource` VALUES (10, '获取部门信息', '/dept/get', 2, 1, 2, NULL, NULL, NULL, NULL, '2020-12-29 15:08:19', '2020-12-30 14:57:19', b'0');
INSERT INTO `permission_resource` VALUES (11, '创建管理员', '/admin/create', 2, 1, 1, NULL, NULL, NULL, NULL, '2020-12-29 15:10:22', '2020-12-30 14:57:25', b'0');
INSERT INTO `permission_resource` VALUES (12, '更新管理员', '/admin/update', 2, 1, 1, NULL, NULL, NULL, NULL, '2020-12-29 15:10:47', '2020-12-30 14:57:31', b'0');
INSERT INTO `permission_resource` VALUES (13, '更新管理员状态', '/admin/update-status', 2, 1, 1, NULL, NULL, NULL, NULL, '2020-12-29 15:11:11', '2020-12-30 14:57:37', b'0');
INSERT INTO `permission_resource` VALUES (14, '判断角色是否有超级管理员', '/admin/is-admin', 2, 1, 1, NULL, NULL, NULL, NULL, '2020-12-29 15:11:54', '2020-12-30 14:57:44', b'0');
INSERT INTO `permission_resource` VALUES (15, '创建资源', '/resource/create', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:13:17', '2020-12-30 14:57:54', b'0');
INSERT INTO `permission_resource` VALUES (16, '更新资源', '/resource/update', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:13:43', '2020-12-30 14:58:00', b'0');
INSERT INTO `permission_resource` VALUES (17, '删除资源', '/resource/delete', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:14:08', '2020-12-30 14:58:06', b'0');
INSERT INTO `permission_resource` VALUES (18, '获得资源', '/resource/get', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:14:28', '2020-12-30 14:58:13', b'0');
INSERT INTO `permission_resource` VALUES (19, '获得资源列表', '/resource/list', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:14:44', '2020-12-30 14:58:18', b'0');
INSERT INTO `permission_resource` VALUES (20, '获得资源树', '/resource/tree', 2, 1, 4, NULL, NULL, NULL, NULL, '2020-12-29 15:15:07', '2020-12-30 14:58:23', b'0');
INSERT INTO `permission_resource` VALUES (21, '获得部门树', '/dept/tree', 2, 1, 2, NULL, NULL, NULL, NULL, '2020-12-29 15:16:43', '2020-12-30 14:58:28', b'0');
INSERT INTO `permission_resource` VALUES (22, '添加角色', '/role/create', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:17:55', '2020-12-30 14:58:34', b'0');
INSERT INTO `permission_resource` VALUES (23, '修改角色', '/role/update', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:18:28', '2020-12-30 14:58:40', b'0');
INSERT INTO `permission_resource` VALUES (24, '删除角色', '/role/delete', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:18:48', '2020-12-30 14:58:49', b'0');
INSERT INTO `permission_resource` VALUES (25, '获取角色信息', '/role/get', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:19:04', '2020-12-30 14:58:55', b'0');
INSERT INTO `permission_resource` VALUES (26, '获得所有角色列表', '/role/list-all', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:19:34', '2020-12-30 14:59:00', b'0');
INSERT INTO `permission_resource` VALUES (27, '获得角色列表', '/role/list', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:19:55', '2020-12-30 14:59:05', b'0');
INSERT INTO `permission_resource` VALUES (28, '获得角色分页', '/role/page', 2, 1, 5, NULL, NULL, NULL, NULL, '2020-12-29 15:20:09', '2020-12-30 14:59:10', b'0');
INSERT INTO `permission_resource` VALUES (29, '获得角色拥有的资源编号', '/permission/list-role-resources', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:21:49', '2020-12-30 14:59:18', b'0');
INSERT INTO `permission_resource` VALUES (30, '赋予角色资源', '/permission/assign-role-resource', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:23:17', '2020-12-30 14:59:25', b'0');
INSERT INTO `permission_resource` VALUES (31, '获得管理员拥有的角色编号列表', '/permission/list-admin-roles', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:23:51', '2020-12-30 14:59:30', b'0');
INSERT INTO `permission_resource` VALUES (32, '赋予用户角色', '/permission/assign-admin-role', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:24:28', '2020-12-30 14:59:36', b'0');
INSERT INTO `permission_resource` VALUES (33, '查询权限对应资源', '/permission/list-permissions', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:24:55', '2020-12-30 14:59:41', b'0');
INSERT INTO `permission_resource` VALUES (34, '根据资源id获取角色资源信息', '/permission/list-resources', 2, 1, 3, NULL, NULL, NULL, NULL, '2020-12-29 15:25:15', '2020-12-30 14:59:50', b'0');
INSERT INTO `permission_resource` VALUES (35, '商品管理', '', 1, 1, 0, '', '', '', NULL, '2020-12-29 15:28:02', '2020-12-29 15:28:02', b'0');
INSERT INTO `permission_resource` VALUES (36, '商品品牌', '', 1, 1, 35, '', '', '@/views/example/edit', NULL, '2020-12-29 15:28:54', '2020-12-29 15:32:09', b'0');
INSERT INTO `permission_resource` VALUES (37, '商品分类', '', 1, 1, 35, '', '', '', NULL, '2020-12-29 15:30:10', '2020-12-29 15:32:33', b'0');
INSERT INTO `permission_resource` VALUES (38, '商品规格管理', '', 1, 1, 35, '', '', '', NULL, '2020-12-29 15:31:14', '2020-12-29 15:31:14', b'0');
INSERT INTO `permission_resource` VALUES (39, '商品spu管理', NULL, 1, 1, 35, NULL, NULL, NULL, NULL, '2020-12-29 15:33:06', '2020-12-29 15:33:06', b'0');
INSERT INTO `permission_resource` VALUES (40, '创建品牌', '/product-brand/create', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:34:34', '2020-12-30 15:00:00', b'0');
INSERT INTO `permission_resource` VALUES (41, '修改品牌', '/product-brand/update', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:35:10', '2020-12-30 15:00:06', b'0');
INSERT INTO `permission_resource` VALUES (42, '修改品牌状态', '/product-brand/update-status', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:35:28', '2020-12-30 15:00:12', b'0');
INSERT INTO `permission_resource` VALUES (43, '获取品牌信息', '/product-brand/info-id', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:36:05', '2020-12-30 15:00:17', b'0');
INSERT INTO `permission_resource` VALUES (44, '获得商品品牌列表', '/product-brand/list', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:36:27', '2020-12-30 15:00:23', b'0');
INSERT INTO `permission_resource` VALUES (45, '获得商品品牌分页', '/product-brand/page', 2, 1, 36, NULL, NULL, NULL, NULL, '2020-12-29 15:36:42', '2020-12-30 15:00:46', b'0');
INSERT INTO `permission_resource` VALUES (46, '添加分类', '/product-category/create', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:37:52', '2020-12-30 15:00:44', b'0');
INSERT INTO `permission_resource` VALUES (47, '修改分类', '/product-category/update', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:38:47', '2020-12-30 15:00:48', b'0');
INSERT INTO `permission_resource` VALUES (48, '删除分类', '/product-category/delete', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:39:26', '2020-12-30 15:00:50', b'0');
INSERT INTO `permission_resource` VALUES (49, '获取分类信息', '/product-category/info', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:40:25', '2020-12-30 15:00:54', b'0');
INSERT INTO `permission_resource` VALUES (50, '获取分类信息列表', '/product-category/list', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:40:42', '2020-12-30 15:01:01', b'0');
INSERT INTO `permission_resource` VALUES (51, '分类信息列表-分页', '/product-category/page', 2, 1, 37, NULL, NULL, NULL, NULL, '2020-12-29 15:41:02', '2020-12-30 15:01:06', b'0');
INSERT INTO `permission_resource` VALUES (52, '添加规格key', '/product-attr:key/create', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:42:54', '2020-12-30 15:01:10', b'0');
INSERT INTO `permission_resource` VALUES (53, '获取规格key', '/product-attr/key:info', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:43:10', '2020-12-30 15:01:15', b'0');
INSERT INTO `permission_resource` VALUES (54, '更新规格key', '/product-attr/key:update', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:43:35', '2020-12-30 15:01:20', b'0');
INSERT INTO `permission_resource` VALUES (55, '获得商品规格键分页', '/product-attr/key:page', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:43:52', '2020-12-30 15:01:25', b'0');
INSERT INTO `permission_resource` VALUES (56, '添加规格value', '/product-attr/value:create', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:44:08', '2020-12-30 15:01:31', b'0');
INSERT INTO `permission_resource` VALUES (57, '修改规格value', '/product-attr/value:update', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:44:21', '2020-12-30 15:01:38', b'0');
INSERT INTO `permission_resource` VALUES (58, '获得商品规格值', '/product-attr/value:get', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:44:35', '2020-12-30 15:01:43', b'0');
INSERT INTO `permission_resource` VALUES (59, '获得商品规格值列表', '/product-attr/value:list', 2, 1, 38, NULL, NULL, NULL, NULL, '2020-12-29 15:44:53', '2020-12-30 15:01:48', b'0');
INSERT INTO `permission_resource` VALUES (60, '创建商品SPU', '/product-spu/create', 2, 1, 39, NULL, NULL, NULL, NULL, '2020-12-29 15:45:36', '2020-12-30 15:01:53', b'0');
INSERT INTO `permission_resource` VALUES (61, '修改商品SPU', '/product-spu/update', 2, 1, 39, NULL, NULL, NULL, NULL, '2020-12-29 15:46:04', '2020-12-30 15:01:59', b'0');
INSERT INTO `permission_resource` VALUES (62, '获得商品SPU', '/product-spu/get', 2, 1, 39, NULL, NULL, NULL, NULL, '2020-12-29 15:46:21', '2020-12-30 15:02:04', b'0');
INSERT INTO `permission_resource` VALUES (63, '获得商品SPU列表', '/product-spu/list', 2, 1, 39, NULL, NULL, NULL, NULL, '2020-12-29 15:46:51', '2020-12-30 15:02:08', b'0');
INSERT INTO `permission_resource` VALUES (64, '获得商品SPU分页', '/product-spu/page', 2, 1, 39, NULL, NULL, NULL, NULL, '2020-12-29 15:47:12', '2020-12-30 15:02:24', b'0');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `type` tinyint(0) NOT NULL COMMENT '角色类型1：内置角色 2：自定义角色',
  `create_admin_id` int(0) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES (1, '超级管理员', 'SUPER_ADMIN', 1, 0, '2019-02-24 09:03:51', '2020-04-30 16:53:38', b'0');
INSERT INTO `permission_role` VALUES (2, '商品管理员', 'SHOP_ADMIN', 2, NULL, '2020-12-05 19:33:53', '2020-12-29 15:49:03', b'0');

-- ----------------------------
-- Table structure for permission_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_role_resource`;
CREATE TABLE `permission_role_resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(0) NOT NULL DEFAULT -1 COMMENT '角色编号(外键：{@link RoleDO}',
  `resource_id` int(0) NOT NULL DEFAULT -1 COMMENT '资源编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role_resource
-- ----------------------------
INSERT INTO `permission_role_resource` VALUES (1, 2, 36, '2020-12-09 17:35:22', '2020-12-29 16:05:26', b'0');
INSERT INTO `permission_role_resource` VALUES (2, 2, 37, '2020-12-29 15:57:08', '2020-12-29 16:03:52', b'0');
INSERT INTO `permission_role_resource` VALUES (3, 2, 38, '2020-12-29 15:57:08', '2020-12-29 16:03:57', b'0');
INSERT INTO `permission_role_resource` VALUES (4, 2, 40, '2020-12-29 15:57:08', '2020-12-29 16:03:59', b'0');
INSERT INTO `permission_role_resource` VALUES (5, 2, 41, '2020-12-29 15:59:41', '2020-12-29 16:04:00', b'0');
INSERT INTO `permission_role_resource` VALUES (6, 2, 42, '2020-12-29 15:59:41', '2020-12-29 16:04:02', b'0');
INSERT INTO `permission_role_resource` VALUES (7, 2, 43, '2020-12-29 15:59:41', '2020-12-29 16:04:03', b'0');
INSERT INTO `permission_role_resource` VALUES (8, 2, 44, '2020-12-29 15:59:42', '2020-12-29 16:04:04', b'0');
INSERT INTO `permission_role_resource` VALUES (9, 2, 45, '2020-12-29 15:59:54', '2020-12-29 16:04:06', b'0');
INSERT INTO `permission_role_resource` VALUES (10, 2, 46, '2020-12-29 16:00:21', '2020-12-29 16:04:08', b'0');
INSERT INTO `permission_role_resource` VALUES (11, 2, 47, '2020-12-29 16:00:21', '2020-12-29 16:04:09', b'0');
INSERT INTO `permission_role_resource` VALUES (12, 2, 48, '2020-12-29 16:00:21', '2020-12-29 16:04:10', b'0');
INSERT INTO `permission_role_resource` VALUES (13, 2, 49, '2020-12-29 16:00:22', '2020-12-29 16:04:12', b'0');
INSERT INTO `permission_role_resource` VALUES (14, 2, 50, '2020-12-29 16:00:22', '2020-12-29 16:04:13', b'0');
INSERT INTO `permission_role_resource` VALUES (15, 2, 51, '2020-12-29 16:01:15', '2020-12-29 16:04:14', b'0');
INSERT INTO `permission_role_resource` VALUES (16, 2, 52, '2020-12-29 16:01:45', '2020-12-29 16:04:16', b'0');
INSERT INTO `permission_role_resource` VALUES (17, 2, 53, '2020-12-29 16:01:45', '2020-12-29 16:04:18', b'0');
INSERT INTO `permission_role_resource` VALUES (18, 2, 54, '2020-12-29 16:01:45', '2020-12-29 16:04:20', b'0');
INSERT INTO `permission_role_resource` VALUES (19, 2, 55, '2020-12-29 16:01:45', '2020-12-29 16:04:22', b'0');
INSERT INTO `permission_role_resource` VALUES (20, 2, 56, '2020-12-29 16:01:47', '2020-12-29 16:04:24', b'0');
INSERT INTO `permission_role_resource` VALUES (21, 2, 57, '2020-12-29 16:01:48', '2020-12-29 16:04:26', b'0');
INSERT INTO `permission_role_resource` VALUES (22, 2, 58, '2020-12-29 16:01:48', '2020-12-29 16:04:27', b'0');
INSERT INTO `permission_role_resource` VALUES (23, 2, 59, '2020-12-29 16:01:49', '2020-12-29 16:04:30', b'0');
INSERT INTO `permission_role_resource` VALUES (24, 2, 60, '2020-12-29 16:02:20', '2020-12-29 16:04:34', b'0');
INSERT INTO `permission_role_resource` VALUES (25, 2, 61, '2020-12-29 16:02:20', '2020-12-29 16:04:36', b'0');
INSERT INTO `permission_role_resource` VALUES (26, 2, 62, '2020-12-29 16:02:20', '2020-12-29 16:04:37', b'0');
INSERT INTO `permission_role_resource` VALUES (27, 2, 63, '2020-12-29 16:02:20', '2020-12-29 16:04:38', b'0');
INSERT INTO `permission_role_resource` VALUES (28, 2, 64, '2020-12-29 16:02:21', '2020-12-29 16:04:42', b'0');
INSERT INTO `permission_role_resource` VALUES (29, 1, 1, '2020-12-29 16:05:52', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (30, 1, 2, '2020-12-29 16:05:57', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (31, 1, 3, '2020-12-29 16:05:57', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (32, 1, 4, '2020-12-29 16:05:57', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (33, 1, 5, '2020-12-29 16:05:58', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (34, 1, 6, '2020-12-29 16:05:58', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (35, 1, 7, '2020-12-29 16:05:59', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (36, 1, 8, '2020-12-29 16:05:59', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (37, 1, 9, '2020-12-29 16:05:59', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (38, 1, 10, '2020-12-29 16:05:59', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (39, 1, 11, '2020-12-29 16:05:59', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (40, 1, 12, '2020-12-29 16:06:00', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (41, 1, 13, '2020-12-29 16:06:00', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (42, 1, 14, '2020-12-29 16:06:00', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (43, 1, 15, '2020-12-29 16:06:00', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (44, 1, 16, '2020-12-29 16:06:00', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (45, 1, 17, '2020-12-29 16:06:01', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (46, 1, 18, '2020-12-29 16:06:01', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (47, 1, 19, '2020-12-29 16:06:01', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (48, 1, 20, '2020-12-29 16:06:01', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (49, 1, 21, '2020-12-29 16:06:01', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (50, 1, 22, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (51, 1, 23, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (52, 1, 24, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (53, 1, 25, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (54, 1, 26, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (55, 1, 27, '2020-12-29 16:06:02', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (56, 1, 28, '2020-12-29 16:06:03', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (57, 1, 29, '2020-12-29 16:06:03', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (58, 1, 30, '2020-12-29 16:06:03', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (59, 1, 31, '2020-12-29 16:06:03', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (60, 1, 32, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (61, 1, 33, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (62, 1, 34, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (63, 1, 35, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (64, 1, 36, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (65, 1, 37, '2020-12-29 16:06:04', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (66, 1, 38, '2020-12-29 16:06:05', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (67, 1, 39, '2020-12-29 16:06:05', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (68, 1, 40, '2020-12-29 16:06:05', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (69, 1, 41, '2020-12-29 16:06:05', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (70, 1, 42, '2020-12-29 16:06:05', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (71, 1, 43, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (72, 1, 44, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (73, 1, 45, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (74, 1, 46, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (75, 1, 47, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (76, 1, 48, '2020-12-29 16:06:06', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (77, 1, 49, '2020-12-29 16:06:07', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (78, 1, 50, '2020-12-29 16:06:07', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (79, 1, 51, '2020-12-29 16:06:07', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (80, 1, 52, '2020-12-29 16:06:07', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (81, 1, 53, '2020-12-29 16:06:07', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (82, 1, 54, '2020-12-29 16:06:08', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (83, 1, 55, '2020-12-29 16:06:15', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (84, 1, 56, '2020-12-29 16:06:15', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (85, 1, 57, '2020-12-29 16:06:24', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (86, 1, 58, '2020-12-29 16:06:35', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (87, 1, 59, '2020-12-29 16:06:35', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (88, 1, 60, '2020-12-29 16:06:35', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (89, 1, 61, '2020-12-29 16:06:35', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (90, 1, 62, '2020-12-29 16:06:35', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (91, 1, 63, '2020-12-29 16:06:36', '2020-12-29 16:07:26', b'0');
INSERT INTO `permission_role_resource` VALUES (92, 1, 64, '2020-12-29 16:06:36', '2020-12-29 16:07:26', b'0');

-- ----------------------------
-- Table structure for product_attr
-- ----------------------------
DROP TABLE IF EXISTS `product_attr`;
CREATE TABLE `product_attr`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '规格编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'product_attr' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attr
-- ----------------------------
INSERT INTO `product_attr` VALUES (1, '颜色', 1, '2019-03-05 15:40:56', '2019-03-05 15:40:58', b'0');
INSERT INTO `product_attr` VALUES (2, '尺寸', 1, '2019-03-05 17:23:07', '2019-03-05 17:23:07', b'0');
INSERT INTO `product_attr` VALUES (3, '测试规格', 1, '2019-03-06 10:29:58', '2019-03-07 00:29:58', b'0');
INSERT INTO `product_attr` VALUES (4, '厮大牛逼规格', 1, '2019-03-06 10:31:01', '2019-03-07 00:33:53', b'0');
INSERT INTO `product_attr` VALUES (5, '狼哥规格', 2, '2019-03-06 10:31:47', '2019-03-07 00:34:27', b'0');
INSERT INTO `product_attr` VALUES (6, '老徐规格', 1, '2019-03-06 10:48:42', '2019-03-07 00:48:41', b'0');
INSERT INTO `product_attr` VALUES (7, '徐妈规格', 1, '2019-03-06 10:53:37', '2019-03-07 00:53:37', b'0');
INSERT INTO `product_attr` VALUES (8, '粉色', 1, '2019-05-02 22:05:21', '2019-05-02 22:05:21', b'0');
INSERT INTO `product_attr` VALUES (9, '内存', 1, '2020-12-21 11:46:54', '2020-12-21 11:46:54', b'0');

-- ----------------------------
-- Table structure for product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attr_value`;
CREATE TABLE `product_attr_value`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '规格编号',
  `attr_id` int(0) NULL DEFAULT NULL COMMENT '规格编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态\n     *\n     * 1-开启\n     * 2-禁用',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'product_attr' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attr_value
-- ----------------------------
INSERT INTO `product_attr_value` VALUES (1, 1, '蓝色', 1, '2019-03-05 15:41:09', '2019-03-05 15:41:09', b'0');
INSERT INTO `product_attr_value` VALUES (2, 1, '绿色', 1, '2019-03-05 15:41:14', '2019-03-05 15:42:58', b'0');
INSERT INTO `product_attr_value` VALUES (3, 2, 'XL', 1, '2019-03-05 17:23:19', '2019-03-05 17:23:19', b'0');
INSERT INTO `product_attr_value` VALUES (4, 2, 'XXL', 1, '2019-03-05 17:23:25', '2020-12-21 11:40:56', b'0');
INSERT INTO `product_attr_value` VALUES (5, 1, '红色', 1, '2019-03-05 20:59:48', '2019-03-05 20:59:48', b'0');
INSERT INTO `product_attr_value` VALUES (6, 4, '狼哥规格', 1, '2019-03-06 19:55:38', '2019-03-07 09:55:38', b'0');
INSERT INTO `product_attr_value` VALUES (7, 4, '狼哥规格1', 1, '2019-03-06 19:56:26', '2019-03-07 09:56:25', b'0');
INSERT INTO `product_attr_value` VALUES (8, 4, '狼哥规格2', 1, '2019-03-06 19:57:19', '2019-03-07 09:57:19', b'0');
INSERT INTO `product_attr_value` VALUES (9, 4, '狼哥规格345', 1, '2019-03-06 20:00:02', '2019-03-07 10:01:18', b'0');
INSERT INTO `product_attr_value` VALUES (11, 1, '粉色', 1, '2019-05-02 22:08:22', '2019-05-02 22:08:21', b'0');
INSERT INTO `product_attr_value` VALUES (12, 1, '黄色', 1, '2019-05-02 22:10:30', '2019-05-02 22:10:30', b'0');
INSERT INTO `product_attr_value` VALUES (13, 1, '橙色', 1, '2019-05-02 22:12:20', '2019-05-02 22:12:19', b'0');
INSERT INTO `product_attr_value` VALUES (14, 1, '青色', 1, '2019-05-02 22:13:12', '2019-05-02 22:13:11', b'0');
INSERT INTO `product_attr_value` VALUES (15, 1, '海蓝色', 1, '2019-05-02 22:15:47', '2019-05-02 22:15:47', b'0');
INSERT INTO `product_attr_value` VALUES (16, 1, '浅蓝色', 1, '2019-05-02 22:16:25', '2019-05-02 22:16:25', b'0');
INSERT INTO `product_attr_value` VALUES (17, 1, '天蓝色', 1, '2019-05-02 22:17:36', '2019-05-02 22:17:35', b'0');
INSERT INTO `product_attr_value` VALUES (28, 3, 'niubi', 1, '2019-05-02 22:52:03', '2019-05-02 22:52:03', b'0');
INSERT INTO `product_attr_value` VALUES (29, 3, 'wocao', 1, '2019-05-02 22:52:27', '2019-05-02 22:52:27', b'0');
INSERT INTO `product_attr_value` VALUES (30, 3, '666', 1, '2019-05-02 22:54:44', '2019-05-02 22:54:43', b'0');
INSERT INTO `product_attr_value` VALUES (31, 3, 'gouwazi', 1, '2019-05-02 22:58:52', '2019-05-02 22:58:51', b'0');
INSERT INTO `product_attr_value` VALUES (32, 3, 'abc', 1, '2019-05-02 22:59:46', '2019-05-02 22:59:46', b'0');
INSERT INTO `product_attr_value` VALUES (33, 3, 'qilin', 1, '2019-05-02 23:00:06', '2019-05-02 23:00:05', b'0');
INSERT INTO `product_attr_value` VALUES (34, 3, 'xigua', 1, '2019-05-02 23:00:43', '2019-05-02 23:00:43', b'0');
INSERT INTO `product_attr_value` VALUES (35, 3, 'zhanxiaolang', 1, '2019-05-02 23:01:07', '2019-05-02 23:01:07', b'0');
INSERT INTO `product_attr_value` VALUES (36, 1, '333', 1, '2019-05-02 23:33:55', '2019-05-02 23:33:55', b'0');
INSERT INTO `product_attr_value` VALUES (37, 6, '111', 1, '2019-05-31 18:55:56', '2019-05-31 18:55:55', b'0');
INSERT INTO `product_attr_value` VALUES (38, 3, '22', 1, '2019-05-31 18:55:59', '2019-05-31 18:55:58', b'0');
INSERT INTO `product_attr_value` VALUES (39, 2, 'L', 1, '2019-05-31 18:56:06', '2020-12-21 11:43:11', b'0');
INSERT INTO `product_attr_value` VALUES (40, 7, 'kk', 1, '2019-05-31 19:53:01', '2019-05-31 19:53:00', b'0');
INSERT INTO `product_attr_value` VALUES (41, 7, 'nm', 1, '2019-05-31 19:53:05', '2019-05-31 19:53:04', b'0');
INSERT INTO `product_attr_value` VALUES (42, 6, 'sss', 1, '2019-06-02 12:22:44', '2019-06-02 12:22:43', b'0');
INSERT INTO `product_attr_value` VALUES (43, 6, 'sds', 1, '2019-06-02 12:22:46', '2019-06-02 12:22:45', b'0');
INSERT INTO `product_attr_value` VALUES (44, 2, '3333', 1, '2019-07-02 11:19:32', '2019-07-02 11:19:32', b'0');
INSERT INTO `product_attr_value` VALUES (45, 2, '123', 1, '2019-07-02 11:33:15', '2019-07-02 11:33:15', b'0');
INSERT INTO `product_attr_value` VALUES (46, 2, 'a', 1, '2019-07-05 15:40:51', '2019-07-05 15:40:51', b'0');
INSERT INTO `product_attr_value` VALUES (47, 2, '12312', 1, '2019-08-12 18:48:48', '2019-08-12 18:48:47', b'0');
INSERT INTO `product_attr_value` VALUES (48, 3, '222', 1, '2019-08-12 18:48:54', '2019-08-12 18:48:53', b'0');
INSERT INTO `product_attr_value` VALUES (49, 1, '金色', 1, '2019-09-05 10:38:55', '2019-09-05 10:38:55', b'0');
INSERT INTO `product_attr_value` VALUES (50, 2, '一克拉', 1, '2019-09-05 10:39:05', '2019-09-05 10:39:04', b'0');
INSERT INTO `product_attr_value` VALUES (51, 9, '8G+64G', 1, '2020-12-21 11:47:42', '2020-12-21 11:47:42', b'0');
INSERT INTO `product_attr_value` VALUES (52, 9, '8G+128G', 1, '2020-12-21 11:47:49', '2020-12-21 11:47:49', b'0');
INSERT INTO `product_attr_value` VALUES (53, 9, '8G+256G', 1, '2020-12-21 11:47:55', '2020-12-21 11:47:55', b'0');
INSERT INTO `product_attr_value` VALUES (54, 9, '6G+64G', 1, '2020-12-21 11:48:02', '2020-12-21 11:48:02', b'0');

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '品牌编号（主键）',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌描述',
  `pic_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌名图片',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态 1开启 2禁用',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除 0正常1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_brand
-- ----------------------------
INSERT INTO `product_brand` VALUES (1, '安踏更新', '安踏拖鞋更新', 'http://www.iocoder.cn', 1, '2019-05-30 13:43:44', '2019-05-31 13:42:29', b'0');
INSERT INTO `product_brand` VALUES (2, '特步', '特步描述', '23232', 1, '2019-05-31 13:42:22', '2019-05-31 13:42:22', b'0');
INSERT INTO `product_brand` VALUES (3, '小米', '得屌丝者得天下', 'www.xxxx.com', 4, '2020-12-17 15:04:06', '2020-12-17 15:04:06', b'0');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父分类编号',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` smallint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) NULL DEFAULT b'1' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 797 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (90, 0, '电视影音', NULL, NULL, 0, '2019-02-21 18:35:00', '2019-03-03 20:42:01', 1, b'0');
INSERT INTO `product_category` VALUES (579, 0, '手机电脑', NULL, NULL, 0, '2019-02-21 18:33:26', '2019-03-03 20:42:03', 1, b'0');
INSERT INTO `product_category` VALUES (637, 90, '32-40英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_904483f8aa3bbaaa9f53e4aae2382275.jpeg', 1, '2019-02-21 18:35:20', '2019-03-03 20:42:05', 1, b'0');
INSERT INTO `product_category` VALUES (638, 90, '43英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_0f26088b420afbe2c63df02246b94a34.jpeg', 2, '2019-02-21 18:35:41', '2019-03-03 20:42:07', 1, b'0');
INSERT INTO `product_category` VALUES (639, 90, '49-50英寸', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_c2c741283b2ce1c4bc8b0abe9ea4f65e.jpeg', 3, '2019-02-21 18:36:01', '2019-03-03 20:42:10', 1, b'0');
INSERT INTO `product_category` VALUES (783, 579, '小米系列', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_af3ae1e1444bc54af8b2251dd14aaa6b.jpeg', 1, '2019-02-21 18:33:56', '2019-03-03 20:42:12', 1, b'0');
INSERT INTO `product_category` VALUES (784, 579, '红米系列', NULL, 'https://shop.io.mi-img.com/app/shop/img?id=shop_72605808146ee82c9961f9e3be6d8696.jpeg', 2, '2019-02-21 18:34:29', '2019-03-03 20:42:14', 1, b'0');
INSERT INTO `product_category` VALUES (785, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:28:22', '2019-03-04 16:46:05', 2, b'1');
INSERT INTO `product_category` VALUES (786, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:31:46', '2019-03-15 22:08:04', 2, b'1');
INSERT INTO `product_category` VALUES (787, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:31:57', '2019-03-15 22:08:08', 2, b'1');
INSERT INTO `product_category` VALUES (788, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:04', '2019-03-25 15:38:38', 2, b'1');
INSERT INTO `product_category` VALUES (789, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:39', '2019-03-29 23:40:41', 2, b'1');
INSERT INTO `product_category` VALUES (790, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:32:54', '2019-03-29 23:40:46', 2, b'1');
INSERT INTO `product_category` VALUES (791, 0, '测试商品分类', '测试商品描述', NULL, 1, '2019-03-04 02:34:13', '2019-03-29 23:40:49', 2, b'1');
INSERT INTO `product_category` VALUES (792, 0, '大保健哟', '哈哈哈哈哈', NULL, 100, '2019-03-15 21:52:23', '2019-03-29 23:40:52', 2, b'1');
INSERT INTO `product_category` VALUES (793, 0, '图书', '书是个好东西，可惜看的少。', NULL, 3, '2019-05-05 16:12:39', '2019-05-05 16:12:39', 1, b'0');
INSERT INTO `product_category` VALUES (794, 793, 'Java', '半年一更，妥妥的', 'http://static.shop.iocoder.cn/5fd5709e-988d-4efd-b5d8-54a599ca538f', 1, '2019-05-05 16:35:03', '2019-05-06 23:05:53', 1, b'0');
INSERT INTO `product_category` VALUES (795, 793, '测试分类', '测试删除', 'http://static.shop.iocoder.cn/14a2169d-a2a2-4fc6-9f71-93dba32596c9', 3, '2019-05-05 16:45:51', '2019-05-05 16:47:14', 2, b'1');
INSERT INTO `product_category` VALUES (796, 579, '订单系统设计', 'sss', 'http://static.shop.iocoder.cn/3fd98e94-ad50-415a-8f17-8bd8a998e508', 5, '2019-06-26 20:28:35', '2019-06-26 20:28:34', 1, b'0');
INSERT INTO `product_category` VALUES (797, 579, '手机', '这个商品很吊', 'http://www.iocoder.cn/xx.jpg', 10, '2020-12-21 11:05:22', '2020-12-21 11:17:13', 1, b'0');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'sku 编号',
  `spu_id` int(0) NOT NULL DEFAULT -1 COMMENT '商品编号',
  `status` int(0) NOT NULL DEFAULT -1 COMMENT '状态\n     *\n     * 1-正常\n     * 2-禁用',
  `pic_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '图片地址',
  `attrs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '规格值({@link ProductAttrDO})数组\n     *\n     * 数组，以逗号分隔',
  `price` int(0) NOT NULL DEFAULT -1 COMMENT '价格，单位：分',
  `quantity` int(0) NOT NULL DEFAULT -1 COMMENT '库存数量',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'product_sku' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (3, 8, 1, NULL, '1', 1, 100, '2019-03-05 02:48:52', '2019-03-05 16:48:52', b'0');
INSERT INTO `product_sku` VALUES (4, 8, 1, NULL, '2', 1, 100, '2019-03-05 02:48:52', '2019-03-05 16:48:52', b'0');
INSERT INTO `product_sku` VALUES (5, 9, 1, NULL, '1', 1, 100, '2019-03-05 02:51:22', '2019-03-05 16:51:21', b'0');
INSERT INTO `product_sku` VALUES (6, 9, 1, NULL, '2', 1, 100, '2019-03-05 02:51:22', '2019-03-05 16:51:21', b'0');
INSERT INTO `product_sku` VALUES (7, 10, 1, NULL, '1', 1, 100, '2019-03-05 02:52:25', '2019-03-05 16:52:25', b'0');
INSERT INTO `product_sku` VALUES (8, 10, 1, NULL, '2', 1, 100, '2019-03-05 02:52:25', '2019-03-05 16:52:25', b'0');
INSERT INTO `product_sku` VALUES (9, 13, 1, NULL, '1', 1, 100, '2019-03-05 03:22:24', '2019-03-05 17:22:26', b'0');
INSERT INTO `product_sku` VALUES (10, 13, 1, NULL, '2', 1, 100, '2019-03-05 03:22:24', '2019-03-05 17:22:26', b'0');
INSERT INTO `product_sku` VALUES (11, 14, 1, NULL, '1', 1, 100, '2019-03-05 03:22:33', '2019-03-05 17:22:40', b'0');
INSERT INTO `product_sku` VALUES (12, 14, 1, NULL, '2', 1, 100, '2019-03-05 03:22:33', '2019-03-05 17:22:40', b'0');
INSERT INTO `product_sku` VALUES (13, 20, 1, NULL, '1,3', 1, 100, '2019-03-05 03:25:39', '2019-03-05 17:25:39', b'0');
INSERT INTO `product_sku` VALUES (14, 20, 1, NULL, '1,4', 1, 100, '2019-03-05 03:25:39', '2019-03-05 17:25:39', b'0');
INSERT INTO `product_sku` VALUES (15, 21, 1, NULL, '1,3', 1, 100, '2019-03-05 03:34:07', '2019-03-05 17:34:06', b'0');
INSERT INTO `product_sku` VALUES (16, 21, 1, NULL, '1,4', 1, 100, '2019-03-05 03:34:07', '2019-03-05 17:34:06', b'0');
INSERT INTO `product_sku` VALUES (17, 22, 1, NULL, '1,3', 1, 100, '2019-03-05 03:34:50', '2019-03-05 17:34:49', b'0');
INSERT INTO `product_sku` VALUES (18, 22, 1, NULL, '1,4', 1, 100, '2019-03-05 03:34:50', '2019-03-05 17:34:49', b'0');
INSERT INTO `product_sku` VALUES (19, 23, 1, NULL, '1,3', 1, 1, '2019-03-05 03:37:03', '2019-03-26 23:51:23', b'0');
INSERT INTO `product_sku` VALUES (20, 23, 1, NULL, '1,4', 2, 10, '2019-03-05 03:37:03', '2019-03-26 23:51:25', b'0');
INSERT INTO `product_sku` VALUES (23, 25, 1, NULL, '1,3', 1, 100, '2019-03-05 03:43:30', '2019-03-05 17:43:30', b'0');
INSERT INTO `product_sku` VALUES (24, 25, 1, NULL, '1,4', 1, 100, '2019-03-05 03:43:30', '2019-03-05 17:43:30', b'0');
INSERT INTO `product_sku` VALUES (25, 26, 1, NULL, '1', 1, 100, '2019-03-05 07:00:38', '2019-03-05 21:00:38', b'0');
INSERT INTO `product_sku` VALUES (26, 27, 1, NULL, '1', 1, 100, '2019-03-05 07:01:33', '2019-03-05 21:10:52', b'1');
INSERT INTO `product_sku` VALUES (27, 27, 1, NULL, '2', 21, 200, '2019-03-05 07:01:33', '2019-03-05 21:10:52', b'0');
INSERT INTO `product_sku` VALUES (30, 27, 1, NULL, '3', 3, 300, '2019-03-05 07:10:52', '2019-03-05 21:10:52', b'0');
INSERT INTO `product_sku` VALUES (31, 28, 1, NULL, '1', 100, 100, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_sku` VALUES (32, 28, 1, NULL, '2', 200, 200, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_sku` VALUES (33, 29, 1, NULL, '1', 10000000, 20, '2019-03-18 17:50:01', '2019-05-05 23:48:10', b'0');
INSERT INTO `product_sku` VALUES (34, 32, 1, NULL, '1,3', 125000000, 20, '2019-04-16 13:45:13', '2019-05-09 10:43:55', b'0');
INSERT INTO `product_sku` VALUES (35, 32, 1, NULL, '1,4', 1410065408, 30, '2019-04-16 13:45:13', '2019-05-09 10:43:55', b'0');
INSERT INTO `product_sku` VALUES (36, 33, 1, NULL, '3', 10000, 100, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_sku` VALUES (37, 33, 1, NULL, '4', 20000, 20, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_sku` VALUES (38, 34, 1, NULL, '1', 2000, 100, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_sku` VALUES (39, 34, 1, NULL, '2', 3000, 200, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_sku` VALUES (40, 35, 1, NULL, '3', 500000, 11, '2019-04-22 13:14:33', '2019-08-12 23:38:32', b'0');
INSERT INTO `product_sku` VALUES (41, 35, 1, NULL, '4', 200000, 22, '2019-04-22 13:14:33', '2019-08-12 23:38:33', b'0');
INSERT INTO `product_sku` VALUES (42, 36, 1, NULL, '3', 5100, 11, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_sku` VALUES (43, 36, 1, NULL, '4', 2100, 22, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_sku` VALUES (44, 37, 1, NULL, '3,2', 2000, 40, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_sku` VALUES (45, 37, 1, NULL, '4,2', 3000, 50, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_sku` VALUES (46, 38, 1, NULL, '2', 1000, 20, '2019-04-25 15:44:56', '2019-04-25 15:44:56', b'0');
INSERT INTO `product_sku` VALUES (47, 39, 1, NULL, '1,3', 1000, 20, '2019-05-02 02:20:50', '2019-05-02 02:20:49', b'0');
INSERT INTO `product_sku` VALUES (48, 40, 1, NULL, '1', 1000, 20, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_sku` VALUES (49, 40, 1, NULL, '2', 3000, 40, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_sku` VALUES (50, 41, 1, NULL, '1', 1000, 20, '2019-05-02 17:44:05', '2019-05-02 17:44:05', b'0');
INSERT INTO `product_sku` VALUES (51, 29, 1, NULL, '2', 30000000, 40, '2019-05-02 18:19:15', '2019-05-05 23:48:10', b'0');
INSERT INTO `product_sku` VALUES (52, 42, 1, NULL, '1', 1000, 20, '2019-05-02 22:17:01', '2019-05-02 22:54:16', b'1');
INSERT INTO `product_sku` VALUES (53, 42, 1, NULL, '18', 3000, 40, '2019-05-02 22:18:57', '2019-05-02 22:23:26', b'1');
INSERT INTO `product_sku` VALUES (54, 42, 1, NULL, '2', 3000, 40, '2019-05-02 22:25:19', '2019-05-02 22:54:16', b'1');
INSERT INTO `product_sku` VALUES (55, 42, 1, NULL, '28', 1000, 20, '2019-05-02 22:54:17', '2019-05-02 22:54:16', b'0');
INSERT INTO `product_sku` VALUES (56, 42, 1, NULL, '35', 3000, 40, '2019-05-02 23:01:17', '2019-05-02 23:01:17', b'0');
INSERT INTO `product_sku` VALUES (57, 43, 1, NULL, '1,3', 111100, 1111, '2019-07-02 11:36:25', '2019-07-02 11:36:25', b'0');
INSERT INTO `product_sku` VALUES (58, 43, 1, NULL, '2,3', 111100, 1111, '2019-07-02 11:36:25', '2019-07-02 11:36:25', b'0');
INSERT INTO `product_sku` VALUES (59, 44, 1, NULL, '1,3', 111100, 1111, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_sku` VALUES (60, 44, 1, NULL, '2,3', 111100, 1111, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_sku` VALUES (61, 45, 1, NULL, '1,3,6', 11100, 111, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_sku` VALUES (62, 45, 1, NULL, '2,3,6', 11100, 111, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_sku` VALUES (63, 46, 1, NULL, '46', 1100, 1111, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_sku` VALUES (64, 46, 1, NULL, '4', 1100, 111, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_sku` VALUES (65, 47, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:29:31', '2019-07-09 20:29:30', b'0');
INSERT INTO `product_sku` VALUES (66, 48, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:29:40', '2019-07-09 20:29:40', b'0');
INSERT INTO `product_sku` VALUES (67, 49, 1, NULL, '3', 21213300, 23123, '2019-07-09 20:32:27', '2019-07-09 20:32:27', b'0');
INSERT INTO `product_sku` VALUES (68, 50, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:48:58', '2019-08-12 18:48:58', b'0');
INSERT INTO `product_sku` VALUES (69, 51, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:49:03', '2019-08-12 18:49:02', b'0');
INSERT INTO `product_sku` VALUES (70, 52, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:49:19', '2019-08-12 18:49:19', b'0');
INSERT INTO `product_sku` VALUES (71, 53, 1, NULL, '47,48', 1200, 2, '2019-08-12 18:50:48', '2019-08-12 18:50:48', b'0');
INSERT INTO `product_sku` VALUES (72, 54, 1, NULL, '1', 10000, 100, '2019-08-12 23:46:12', '2019-08-12 23:46:11', b'0');
INSERT INTO `product_sku` VALUES (73, 55, 1, NULL, '49,50', 10000, 100, '2019-09-05 10:41:24', '2019-09-05 10:41:24', b'0');
INSERT INTO `product_sku` VALUES (74, 59, 1, NULL, '1,2,5,11', 4000, 6666, '2020-12-21 13:43:59', '2020-12-21 13:43:59', b'0');
INSERT INTO `product_sku` VALUES (75, 59, 1, NULL, '51,52,53,54', 3999, 5555, '2020-12-21 13:43:59', '2020-12-21 13:43:59', b'0');

-- ----------------------------
-- Table structure for product_spu
-- ----------------------------
DROP TABLE IF EXISTS `product_spu`;
CREATE TABLE `product_spu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'SPU 编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'SPU 名字',
  `sell_point` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '卖点',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `cid` int(0) NOT NULL DEFAULT -1 COMMENT '分类编号',
  `pic_urls` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品主图地址\n     *\n     * 数组，以逗号分隔\n     *\n     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张',
  `visible` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否上架商品（是否可见）。\n     *\n     * true 为已上架\n     * false 为已下架',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序字段',
  `price` int(0) NULL DEFAULT NULL COMMENT '价格',
  `quantity` int(0) NULL DEFAULT NULL COMMENT '库存数量',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'product_spu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_spu
-- ----------------------------
INSERT INTO `product_spu` VALUES (7, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/5d4374f27d078462!400x400_big.jpg', 1, 0, 7, 10, '2019-03-05 02:45:44', '2019-04-24 11:47:20', b'1');
INSERT INTO `product_spu` VALUES (8, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/52c349020e992e04!400x400_big.jpg', 1, 0, 8, 20, '2019-03-05 02:48:52', '2019-04-24 11:47:22', b'1');
INSERT INTO `product_spu` VALUES (9, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/4ba4264eb3416283!400x400_big.jpg', 1, 0, 9, 30, '2019-03-05 02:51:22', '2019-04-24 11:47:23', b'1');
INSERT INTO `product_spu` VALUES (10, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b258b691c543172d!400x400_big.jpg', 1, 0, 10, 40, '2019-03-05 02:52:25', '2019-04-24 11:47:25', b'1');
INSERT INTO `product_spu` VALUES (11, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/976d2210182f6816!400x400_big.jpg', 1, 0, 11, 50, '2019-03-05 03:21:46', '2019-04-24 11:47:47', b'1');
INSERT INTO `product_spu` VALUES (12, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/02c433787860a74a!400x400_big.jpg', 1, 0, 12, NULL, '2019-03-05 03:22:02', '2019-04-24 11:47:48', b'1');
INSERT INTO `product_spu` VALUES (13, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/7ee3855f60009a09!400x400_big.jpg', 1, 0, 13, NULL, '2019-03-05 03:22:24', '2019-04-24 11:47:49', b'1');
INSERT INTO `product_spu` VALUES (14, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 14, NULL, '2019-03-05 03:22:33', '2019-04-24 11:47:50', b'1');
INSERT INTO `product_spu` VALUES (15, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 15, NULL, '2019-03-05 03:23:40', '2019-04-24 11:47:51', b'1');
INSERT INTO `product_spu` VALUES (16, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 16, NULL, '2019-03-05 03:23:50', '2019-04-24 11:47:50', b'1');
INSERT INTO `product_spu` VALUES (20, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 17, NULL, '2019-03-05 03:25:39', '2019-04-24 11:47:55', b'1');
INSERT INTO `product_spu` VALUES (21, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 18, NULL, '2019-03-05 03:34:07', '2019-04-24 11:47:57', b'1');
INSERT INTO `product_spu` VALUES (22, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 19, NULL, '2019-03-05 03:34:50', '2019-04-24 11:47:57', b'1');
INSERT INTO `product_spu` VALUES (23, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 0, 20, NULL, '2019-03-05 03:37:03', '2019-04-24 11:47:58', b'1');
INSERT INTO `product_spu` VALUES (25, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/b657f0c288eef296!400x400_big.jpg', 1, 0, 21, NULL, '2019-03-05 03:43:30', '2019-04-24 11:47:59', b'1');
INSERT INTO `product_spu` VALUES (26, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 0, 22, NULL, '2019-03-05 07:00:38', '2019-04-24 11:48:00', b'1');
INSERT INTO `product_spu` VALUES (27, '测试商品', '又大又长', '厮大牛逼', 791, 'https://img2.woyaogexing.com/2019/03/15/3dd901d0166dddf7!400x400_big.jpg', 1, 100, 23, NULL, '2019-03-05 07:01:33', '2019-04-24 11:48:00', b'1');
INSERT INTO `product_spu` VALUES (28, '测试商品', '又大又长', '<p>厮大牛逼</p>', 638, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694b02ef0df7?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1,http://static.shop.iocoder.cn/49ace70a-bebc-4d1c-b4e7-2115cedbf2a8', 1, 1, 100, 300, '2019-03-07 01:34:15', '2019-05-13 14:48:36', b'0');
INSERT INTO `product_spu` VALUES (29, 'kafka 实战第一版', '最强', '<p>我是一个骚气的描述</p>', 637, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1,http://static.shop.iocoder.cn/ab34d8e5-3b28-4f74-bafa-48aa66b4cf58', 1, 2, 10000000, 60, '2019-03-18 17:50:00', '2019-05-06 15:31:21', b'0');
INSERT INTO `product_spu` VALUES (32, '农夫山泉', '有点甜', '<p>我就是一个水</p>', 637, 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 1, 125000000, 50, '2019-04-16 13:45:13', '2019-09-06 10:58:36', b'0');
INSERT INTO `product_spu` VALUES (33, 'Kafka 书籍汇总', '分布式发布订阅消息系统', 'kafka是一种高吞吐量的分布式发布订阅消息系统，她有如下特性：\n\n通过O(1)的磁盘数据结构提供消息的持久化，这种结构对于即使数以TB的消息存储也能够保持长时间的稳定性能。\n\n高吞吐量：即使是非常普通的硬件kafka也可以支持每秒数十万的消息。\n\n支持通过kafka服务器和消费机集群来分区消息。\n\n支持Hadoop并行数据加载。\n\n卡夫卡的目的是提供一个发布订阅解决方案，它可以处理消费者规模的网站中的所有动作流数据。 这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群机来提供实时的消费。', 783, 'http://static.iocoder.cn/kafka.png', 1, 0, 10000, 120, '2019-04-22 13:08:27', '2019-04-22 13:08:26', b'0');
INSERT INTO `product_spu` VALUES (34, 'MySQL', '数据库服务器', 'MySQL是一个开放源码的小型关联式数据库管理系统，开发者为瑞典MySQL AB公司。目前MySQL被广泛地应用在Internet上的中小型网站中。由于其体积小、速度快、总体拥有成本低，尤其是开放源码这一特点，许多中小型网站为了降低网站总体拥有成本而选择了MySQL作为网站数据库。', 579, 'https://static.oschina.net/img/logo/mysql.png', 1, 0, 2000, 300, '2019-04-22 13:11:56', '2019-04-22 13:11:55', b'0');
INSERT INTO `product_spu` VALUES (35, 'Oracle', '数据库服务器', '<p>Oracle是一个面向Internet计算环境的数据库。它是在数据库领域一直处于领先地位的Oracle（即甲骨文公司）的产品。可以说Oracle 关系数据库系统是目前世界上流行的关系数据库管理系统，系统可移植性好、使用方便、功能强，适用于各类大、中、小、微机环境。它是一种高效率、可靠性好的 适应高吞吐量的数据库解决方案。</p>', 639, 'https://static.oschina.net/img/logo/oracle.gif', 1, 0, 200000, 33, '2019-04-22 13:14:33', '2019-08-12 23:38:32', b'0');
INSERT INTO `product_spu` VALUES (36, 'Java', '编程语言', 'Java，是由Sun Microsystems公司于1995年5月推出的Java程序设计语言和Java平台的总称，最初推出的时候提出 “Write Once, Run Anywhere” 的理想愿景。\n\n    用 Java 实现的 HotJava 浏览器（支持Java applet）显示了 Java 的魅力：跨平台、动态的Web、Internet计算。从此，Java被广泛接受并推动了Web的迅速发展，常用的浏览器现在均支持Java applet。', 639, 'https://static.oschina.net/img/logo/java.png', 1, 0, 2100, 33, '2019-04-22 13:15:15', '2019-04-22 13:15:14', b'0');
INSERT INTO `product_spu` VALUES (37, '狼哥很骚', '骚气十足', '狼哥，死于 JVM 之手', 637, 'https://img.1000.com/qm-a-img/prod/000000/68635d48f57444c8a5ffd47a257dc3d7.jpg', 1, 0, 2000, 90, '2019-04-25 15:42:36', '2019-04-25 15:42:35', b'0');
INSERT INTO `product_spu` VALUES (38, '天天吃饭', '不吃不行', '美团外卖饿了么外卖', 783, 'https://static.oschina.net/img/logo/mysql.png', 1, 0, 1000, 20, '2019-04-25 15:44:56', '2019-04-25 15:44:56', b'0');
INSERT INTO `product_spu` VALUES (39, 'admin-server', '测试卖点', '30', 637, 'http://static.shop.iocoder.cn/8702680d-9145-490c-9bc1-13ed3337c4d1', 1, 0, 1000, 20, '2019-05-02 02:20:50', '2019-05-02 02:20:49', b'0');
INSERT INTO `product_spu` VALUES (40, '测试多规格商品', '我是多规格', 'nice', 637, 'http://static.shop.iocoder.cn/d434dc76-3766-4d82-bdb7-229d1db4749c', 1, 0, 1000, 60, '2019-05-02 12:38:00', '2019-05-02 12:37:59', b'0');
INSERT INTO `product_spu` VALUES (41, 'kafka 实战', '很吊', '厮大牛逼', 637, 'https://user-gold-cdn.xitu.io/2019/4/1/169d694ae392047b?imageView2/0/w/1280/h/960/format/jpeg/ignore-error/1', 1, 0, 1000, 0, '2019-05-02 17:44:05', '2019-05-06 13:27:22', b'0');
INSERT INTO `product_spu` VALUES (42, '我和僵尸有个约会', '有点甜', '我就是描述', 637, 'http://static.shop.iocoder.cn/e3904ca3-d37c-47ae-b53f-3b46c5916e41', 0, 0, 1000, 60, '2019-05-02 22:17:01', '2019-05-06 13:27:05', b'0');
INSERT INTO `product_spu` VALUES (43, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 111100, 2222, '2019-07-02 11:36:25', '2019-07-02 11:36:24', b'0');
INSERT INTO `product_spu` VALUES (44, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 111100, 2222, '2019-07-02 11:36:39', '2019-07-02 11:36:38', b'0');
INSERT INTO `product_spu` VALUES (45, '111', '111', '<p>123</p><div class=\"media-wrap image-wrap align-center\" style=\"text-align:center\"><img src=\"http://static.shop.iocoder.cn/57aa3c2a-56d7-4491-92b4-61c2461b3412\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/f97c1bce-48ff-4b26-8d34-d38137a306dd', 1, 0, 11100, 222, '2019-07-02 11:37:53', '2019-07-02 11:37:52', b'0');
INSERT INTO `product_spu` VALUES (46, 'aaaa', 'a', '<p>111111111111111111111111</p>', 784, 'http://static.shop.iocoder.cn/c7f1a2c8-4015-4c28-bc64-a92ad72fbe12', 1, 0, 1100, 1222, '2019-07-05 15:41:02', '2019-07-05 15:41:01', b'0');
INSERT INTO `product_spu` VALUES (47, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/1b4195db-93b9-4f8d-b8bb-3a88df28459d', 1, 0, 21213300, 23123, '2019-07-09 20:29:30', '2019-07-09 20:29:30', b'0');
INSERT INTO `product_spu` VALUES (48, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/1b4195db-93b9-4f8d-b8bb-3a88df28459d', 1, 0, 21213300, 23123, '2019-07-09 20:29:40', '2019-07-09 20:29:40', b'0');
INSERT INTO `product_spu` VALUES (49, '测试', '测试', '<p>21121212121</p>', 784, 'http://static.shop.iocoder.cn/124519dc-5b11-4bf1-96e3-ac29d18a603b', 1, 0, 21213300, 23123, '2019-07-09 20:32:27', '2019-07-09 20:32:26', b'0');
INSERT INTO `product_spu` VALUES (50, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:48:58', '2019-08-12 18:48:58', b'0');
INSERT INTO `product_spu` VALUES (51, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:49:03', '2019-08-12 18:49:02', b'0');
INSERT INTO `product_spu` VALUES (52, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:49:19', '2019-08-12 18:49:19', b'0');
INSERT INTO `product_spu` VALUES (53, '<script>alert(1111)</script>', '123', '<p>123123321</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/45bae085-0f13-42c8-a048-296ecc2dba2c\"/></div><p></p>', 784, 'http://static.shop.iocoder.cn/a7c70291-3d6e-46c2-90b8-f2b43f134b2b', 1, 0, 1200, 2, '2019-08-12 18:50:48', '2019-08-12 18:50:48', b'0');
INSERT INTO `product_spu` VALUES (54, 'Hadoop', '有点小贵', '<p>好看的不得了</p><div class=\"media-wrap image-wrap\"><img src=\"http://static.shop.iocoder.cn/d5dccd13-1f82-483e-bb87-fc47e14cb19f\"/></div><p></p>', 794, 'http://static.shop.iocoder.cn/4870d103-1c00-42e1-8eb7-177e227d5e03', 1, 0, 10000, 100, '2019-08-12 23:46:12', '2019-08-12 23:46:11', b'0');
INSERT INTO `product_spu` VALUES (55, '金坷垃', '农民伯伯都爱它', '<p>金坷拉</p>', 783, 'http://static.shop.iocoder.cn/8fb41fd6-2ace-4e66-870d-28cff91084ae', 1, 0, 10000, 100, '2019-09-05 10:41:24', '2019-09-05 10:41:24', b'0');
INSERT INTO `product_spu` VALUES (59, '小米P20横空出世', '4000万高清摄像', '屌丝们的福利', 797, '111,2222', 1, 0, 3999, 12221, '2020-12-21 13:43:59', '2020-12-21 13:43:59', b'0');

-- ----------------------------
-- Table structure for system_access_log
-- ----------------------------
DROP TABLE IF EXISTS `system_access_log`;
CREATE TABLE `system_access_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(0) NULL DEFAULT NULL COMMENT '用户类型',
  `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链路追踪编号',
  `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名',
  `uri` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '访问地址',
  `query_string` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '参数',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'http 方法',
  `user_agent` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'userAgent',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ip',
  `start_time` datetime(0) NOT NULL COMMENT '请求时间',
  `response_time` int(0) NOT NULL COMMENT '响应时长 -- 毫秒级',
  `error_code` int(0) NOT NULL COMMENT '错误码',
  `error_message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误提示',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_data_dict`;
CREATE TABLE `system_data_dict`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `enum_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '大类枚举值',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '小类数值',
  `display_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '展示名',
  `sort` int(0) NOT NULL DEFAULT -1 COMMENT '排序值',
  `memo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_data_dict
-- ----------------------------
INSERT INTO `system_data_dict` VALUES (1, 'gender', '1', '男', 1, '性别 - 男（嗨）', '2019-03-03 13:03:46', '2019-03-15 16:51:45', b'0');
INSERT INTO `system_data_dict` VALUES (2, 'gender', '2', '女', 2, '性别（女）', '2019-03-03 13:04:00', '2019-03-15 17:19:20', b'0');
INSERT INTO `system_data_dict` VALUES (3, 'gender', '3', '未知', 10, NULL, '2019-03-02 23:09:19', '2019-03-03 13:11:47', b'1');
INSERT INTO `system_data_dict` VALUES (4, 'common_status', '1', '开启', 1, '状态 - 开启', '2019-03-15 17:28:08', '2019-03-15 17:28:07', b'0');
INSERT INTO `system_data_dict` VALUES (5, 'common_status', '2', '禁用', 2, '状态 - 禁用', '2019-03-15 17:28:54', '2019-03-15 17:28:54', b'0');
INSERT INTO `system_data_dict` VALUES (6, 'test', '1', '测试', 1, '你猜', '2019-03-15 17:36:54', '2019-03-15 17:36:59', b'1');
INSERT INTO `system_data_dict` VALUES (7, 'test', '1', '测试', 1, '1', '2019-03-15 17:37:13', '2019-03-15 18:46:21', b'1');
INSERT INTO `system_data_dict` VALUES (8, 'delete_status', '0', '未删除', 0, '正常/未删除', '2019-03-22 21:20:59', '2019-03-22 21:20:59', b'0');
INSERT INTO `system_data_dict` VALUES (9, 'delete_status', '1', '删除', 1, '删除', '2019-03-22 21:23:20', '2019-03-22 21:23:20', b'0');
INSERT INTO `system_data_dict` VALUES (10, 'order_status', '1', '等待付款', 1, '订单状态', '2019-03-24 16:17:55', '2019-03-24 16:20:24', b'0');
INSERT INTO `system_data_dict` VALUES (11, 'order_status', '2', '等待发货', 2, '	等待发货', '2019-03-24 16:18:39', '2019-03-24 16:20:14', b'0');
INSERT INTO `system_data_dict` VALUES (12, 'order_status', '3', '已发货', 3, '已发货', '2019-03-24 16:19:14', '2019-03-24 16:20:07', b'0');
INSERT INTO `system_data_dict` VALUES (13, 'order_status', '4', '已完成', 4, '	已完成', '2019-03-24 16:19:37', '2019-03-24 16:20:03', b'0');
INSERT INTO `system_data_dict` VALUES (14, 'order_status', '5', '已关闭', 5, '已关闭', '2019-03-24 16:19:50', '2019-03-24 16:19:50', b'0');
INSERT INTO `system_data_dict` VALUES (15, 'order_has_return_exchange', '1', '无退换货', 1, '.', '2019-03-24 16:51:27', '2019-03-24 16:51:26', b'0');
INSERT INTO `system_data_dict` VALUES (16, 'order_has_return_exchange', '2', '退货', 2, '.', '2019-03-24 16:51:36', '2019-03-24 16:51:35', b'0');
INSERT INTO `system_data_dict` VALUES (17, 'order_has_return_exchange', '3', '换货', 3, '.', '2019-03-24 16:51:45', '2019-03-24 16:51:44', b'0');
INSERT INTO `system_data_dict` VALUES (18, 'order_has_return_exchange', '4', '退换货', 4, '。', '2019-03-24 16:52:01', '2019-03-24 16:52:00', b'0');
INSERT INTO `system_data_dict` VALUES (19, 'order_cancel_reasons', '1', '无法联系上买家', 1, '.', '2019-03-30 15:19:18', '2019-03-30 15:19:17', b'0');
INSERT INTO `system_data_dict` VALUES (20, 'order_cancel_reasons', '2', '买家误拍或重拍了', 2, ',', '2019-03-30 15:19:31', '2019-03-30 15:19:30', b'0');
INSERT INTO `system_data_dict` VALUES (21, 'order_cancel_reasons', '3', '买家无诚意完成交易', 3, '.', '2019-03-30 15:19:42', '2019-03-30 15:19:41', b'0');
INSERT INTO `system_data_dict` VALUES (22, 'order_cancel_reasons', '4', '已通过银行线下汇款', 4, '.', '2019-03-30 15:19:56', '2019-03-30 15:19:56', b'0');
INSERT INTO `system_data_dict` VALUES (23, 'order_cancel_reasons', '5', '已通过同城见面交易', 5, '.', '2019-03-30 15:20:21', '2019-03-30 15:20:21', b'0');
INSERT INTO `system_data_dict` VALUES (24, 'order_cancel_reasons', '6', '已通过货到付款交易', 6, '.', '2019-03-30 15:20:37', '2019-03-30 15:20:37', b'0');
INSERT INTO `system_data_dict` VALUES (25, 'order_cancel_reasons', '7', '已通过网上银行直接汇款', 7, '.', '2019-03-30 15:20:48', '2019-03-30 15:20:47', b'0');
INSERT INTO `system_data_dict` VALUES (26, 'order_cancel_reasons', '8', '已经缺货无法交易', 8, '.', '2019-03-30 15:21:01', '2019-03-30 15:21:01', b'0');
INSERT INTO `system_data_dict` VALUES (27, 'order_cancel_reasons', '20', '其他', 20, '.', '2019-03-30 17:16:27', '2019-03-30 17:16:27', b'0');
INSERT INTO `system_data_dict` VALUES (28, 'logistics_company', '1', '顺丰快递', 1, '.', '2019-04-05 16:26:22', '2019-04-05 16:26:22', b'0');
INSERT INTO `system_data_dict` VALUES (29, 'logistics_company', '2', '顺风速运', 2, '.', '2019-04-05 16:26:40', '2019-04-05 16:27:48', b'0');
INSERT INTO `system_data_dict` VALUES (30, 'logistics_company', '3', '圆通快递', 3, '.', '2019-04-05 16:26:57', '2019-04-05 16:26:56', b'0');
INSERT INTO `system_data_dict` VALUES (31, 'logistics_company', '4', '申通快递', 4, '.', '2019-04-05 16:27:14', '2019-04-05 16:27:14', b'0');
INSERT INTO `system_data_dict` VALUES (32, 'logistics_company', '5', 'EMS', 5, '.', '2019-04-05 16:28:00', '2019-04-05 16:28:00', b'0');
INSERT INTO `system_data_dict` VALUES (33, 'logistics_company', '6', '申通快递', 6, '.', '2019-04-05 16:28:40', '2019-04-05 16:28:40', b'0');
INSERT INTO `system_data_dict` VALUES (34, 'logistics_company', '7', '天天快递', 7, '.', '2019-04-05 16:28:51', '2019-04-05 16:28:51', b'0');
INSERT INTO `system_data_dict` VALUES (35, 'logistics_company', '8', '环球速运', 8, '.', '2019-04-05 16:29:12', '2019-04-05 16:29:12', b'0');
INSERT INTO `system_data_dict` VALUES (36, 'user_type', '1', '用户', 1, '用户类型 - 用户', '2019-04-11 17:48:29', '2020-07-15 18:46:42', b'0');
INSERT INTO `system_data_dict` VALUES (37, 'order_return_reason', '0', '退货原因', 1, '退货原因', '2019-04-25 23:25:10', '2019-04-25 23:27:02', b'1');
INSERT INTO `system_data_dict` VALUES (38, 'order_return_reason', '1', '不想要了', 1, '.', '2019-04-25 23:25:55', '2019-04-25 23:25:55', b'0');
INSERT INTO `system_data_dict` VALUES (39, 'order_return_reason', '2', '多拍重拍', 2, '.', '2019-04-25 23:26:23', '2019-04-25 23:26:23', b'0');
INSERT INTO `system_data_dict` VALUES (40, 'order_return_reason', '3', '少货', 3, '.', '2019-04-25 23:26:48', '2019-04-25 23:26:47', b'0');
INSERT INTO `system_data_dict` VALUES (41, 'order_return_return_type', '-1', '订单退货类型', 1, '.', '2019-04-27 11:57:45', '2019-04-27 11:58:16', b'1');
INSERT INTO `system_data_dict` VALUES (42, 'order_return_return_type', '1', '退货退款', 1, '.', '2019-04-27 11:58:05', '2019-04-27 12:12:01', b'1');
INSERT INTO `system_data_dict` VALUES (43, 'order_return_return_type', '2', '退款', 2, '.', '2019-04-27 11:58:14', '2019-04-27 12:12:03', b'1');
INSERT INTO `system_data_dict` VALUES (44, 'order_return_service_type', '1', '退货退款', 1, '.', '2019-04-27 12:12:26', '2019-04-27 12:12:25', b'0');
INSERT INTO `system_data_dict` VALUES (45, 'order_return_service_type', '2', '退款', 2, '2', '2019-04-27 12:12:36', '2019-04-27 12:12:35', b'0');
INSERT INTO `system_data_dict` VALUES (46, 'order_return_status', '1', '退货申请', 1, '1', '2019-05-06 22:50:06', '2019-05-06 22:50:05', b'0');
INSERT INTO `system_data_dict` VALUES (47, 'order_return_status', '2', '2', 2, '2', '2019-05-06 22:50:46', '2019-05-06 22:51:57', b'1');
INSERT INTO `system_data_dict` VALUES (48, 'order_return_status', '-1', '1', 1, '1', '2019-05-06 22:51:07', '2019-05-06 22:52:00', b'1');
INSERT INTO `system_data_dict` VALUES (49, 'order_return_status', '0', '0', 0, '0', '2019-05-06 22:51:39', '2019-05-06 22:52:02', b'1');
INSERT INTO `system_data_dict` VALUES (50, 'order_return_status', '2', '申请成功', 2, '.', '2019-05-06 22:52:13', '2019-05-06 22:52:12', b'0');
INSERT INTO `system_data_dict` VALUES (51, 'order_return_status', '3', '申请失败', 3, '.', '2019-05-06 22:52:34', '2019-05-06 22:52:34', b'0');
INSERT INTO `system_data_dict` VALUES (52, 'order_return_status', '4', '退货中', 4, '.', '2019-05-06 22:52:44', '2019-05-06 22:52:43', b'0');
INSERT INTO `system_data_dict` VALUES (53, 'order_return_status', '5', '已收货', 5, '.', '2019-05-06 22:52:57', '2019-05-09 22:17:35', b'0');
INSERT INTO `system_data_dict` VALUES (54, 'order_return_status', '6', '退款成功', 6, '.', '2019-05-09 22:17:52', '2019-05-09 22:17:51', b'0');
INSERT INTO `system_data_dict` VALUES (55, 'sms_platform', '1', '云片', 1, '短信服务云片', '2019-05-26 15:09:37', '2019-05-26 15:09:37', b'0');
INSERT INTO `system_data_dict` VALUES (56, 'sms_platform', '2', '阿里云', 2, '阿里云短信服务', '2019-05-26 15:09:56', '2019-05-26 15:09:56', b'0');
INSERT INTO `system_data_dict` VALUES (57, 'sms_apply_status', '1', '审核中', 1, '审核中', '2019-05-26 15:11:27', '2019-05-26 15:11:27', b'0');
INSERT INTO `system_data_dict` VALUES (58, 'sms_apply_status', '2', '审核成功', 2, '审核成功', '2019-05-26 15:11:40', '2019-05-26 15:11:40', b'0');
INSERT INTO `system_data_dict` VALUES (59, 'sms_apply_status', '10', '审核失败', 10, '审核失败', '2019-05-26 15:12:10', '2019-05-26 15:12:10', b'0');
INSERT INTO `system_data_dict` VALUES (60, 'sms_type', '1', '验证码', 1, '验证码', '2019-05-26 23:51:15', '2019-05-26 23:51:15', b'0');
INSERT INTO `system_data_dict` VALUES (61, 'sms_type', '2', '通知', 2, '通知', '2019-05-26 23:51:29', '2019-05-26 23:51:28', b'0');
INSERT INTO `system_data_dict` VALUES (62, 'sms_type', '3', '营销', 3, '营销', '2019-05-26 23:51:47', '2019-05-26 23:51:46', b'0');
INSERT INTO `system_data_dict` VALUES (63, 'testOne', '2', '测试下', 1, '是冯绍峰是', '2020-07-15 11:27:34', '2020-07-15 11:27:57', b'1');
INSERT INTO `system_data_dict` VALUES (64, 'user_type', '2', '管理员', 1, '用户类型 - 管理员', '2020-07-15 11:28:07', '2020-07-15 18:47:01', b'0');
INSERT INTO `system_data_dict` VALUES (65, 'resource_type', '1', '菜单', 1, '资源类型 - 菜单', '2020-07-15 14:43:17', '2020-07-15 14:43:46', b'0');
INSERT INTO `system_data_dict` VALUES (66, 'resource_type', '2', '按钮', 2, '资源类型 - 按钮', '2020-07-15 14:43:38', '2020-07-15 14:43:38', b'0');
INSERT INTO `system_data_dict` VALUES (67, 'role_type', '1', '内置角色', 1, '角色类型 - 内置角色', '2020-07-15 15:04:04', '2020-07-15 15:04:04', b'0');
INSERT INTO `system_data_dict` VALUES (68, 'role_type', '2', '自定义角色', 2, '角色类型 - 自定义角色', '2020-07-15 15:04:20', '2020-07-15 15:04:20', b'0');
INSERT INTO `system_data_dict` VALUES (69, 'system_exception_log_process_status', '0', '未处理', 1, '系统异常日志的处理状态 - 未处理', '2020-07-16 13:34:56', '2020-07-16 13:40:03', b'0');
INSERT INTO `system_data_dict` VALUES (70, 'system_exception_log_process_status', '1', '已处理', 2, '系统异常日志的处理状态 - 已处理', '2020-07-16 13:35:19', '2020-07-16 13:40:08', b'0');
INSERT INTO `system_data_dict` VALUES (71, 'system_exception_log_process_status', '2', '已忽略', 3, '系统异常日志的处理状态 - 已忽略', '2020-07-16 13:35:31', '2020-07-16 13:40:11', b'0');
INSERT INTO `system_data_dict` VALUES (72, 'error_code_type', '1', '自动生成', 1, '错误码的类型枚举 - 自动生成', '2020-07-20 19:49:23', '2020-07-20 19:50:45', b'0');
INSERT INTO `system_data_dict` VALUES (73, 'error_code_type', '2', '手动编辑', 2, '错误码的类型枚举 - 手动编辑', '2020-07-20 19:50:00', '2020-07-20 19:50:51', b'0');

-- ----------------------------
-- Table structure for system_error_code
-- ----------------------------
DROP TABLE IF EXISTS `system_error_code`;
CREATE TABLE `system_error_code`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '错误码编号',
  `code` int(0) NOT NULL DEFAULT 0 COMMENT '错误码编码',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '错误码错误提示',
  `type` tinyint(0) NOT NULL COMMENT '错误码类型',
  `group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '错误码分组',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误码备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '错误码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `system_exception_log`;
CREATE TABLE `system_exception_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(0) NULL DEFAULT NULL COMMENT '用户类型',
  `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '链路追踪编号\n     *\n     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。',
  `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名\n     *\n     * 目前读取 spring.application.name',
  `uri` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问地址',
  `query_string` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'http 方法',
  `user_agent` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'userAgent',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip',
  `exception_time` datetime(0) NOT NULL COMMENT '异常发生时间',
  `exception_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '异常名\n     *\n     * {@link Throwable#getClass()} 的类全名',
  `exception_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常导致的消息\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getMessage(Throwable)}',
  `exception_root_cause_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常导致的根消息\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getRootCauseMessage(Throwable)}',
  `exception_stack_trace` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常的栈轨迹\n     *\n     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getServiceException(Exception)}',
  `exception_class_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常发生的类全名\n     *\n     * {@link StackTraceElement#getClassName()}',
  `exception_file_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常发生的类文件\n     *\n     * {@link StackTraceElement#getFileName()}',
  `exception_method_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '异常发生的方法名\n     *\n     * {@link StackTraceElement#getMethodName()}',
  `exception_line_number` int(0) NOT NULL COMMENT '异常发生的方法所在行\n     *\n     * {@link StackTraceElement#getLineNumber()}',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统异常日志' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
