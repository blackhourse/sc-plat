/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mall_admin

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/12/2020 23:24:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实名字',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `status` tinyint(4) NOT NULL COMMENT '在职状态1:在职 2离职',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登陆账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '加密后的密码',
  `password_salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码的盐',
  `create_admin_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建 IP',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '管理员', '', NULL, 1, 'admin', '$2a$10$5sSIO3fq77iVvrnv4XjXwugz4D91D4gxIe7ClQzKsPySNPEZcr6za', '$2a$10$5sSIO3fq77iVvrnv4XjXwu', 0, '', '2020-04-17 19:20:14', '2020-07-04 21:33:30');
INSERT INTO `admin` VALUES (2, '小王', NULL, 1, 1, '15601691300', '$2a$10$gcVaFd8PrL6vRSBkAS4uD.33A1baZrYz898y2ahBXdPtugTQT0fCu', '$2a$10$gcVaFd8PrL6vRSBkAS4uD.', NULL, NULL, '2020-12-05 19:29:54', '2020-12-05 19:31:06');
INSERT INTO `admin` VALUES (3, '小马', NULL, 1, 1, 'maht2', '$2a$10$rksrs4abZG0WDSva9f/wnOk94vQNa54GqQ.c7MAkL24HwPzJ/Elf6', '$2a$10$rksrs4abZG0WDSva9f/wnO', NULL, NULL, '2020-12-05 19:30:12', '2020-12-05 19:30:43');
INSERT INTO `admin` VALUES (4, '小马', NULL, 1, 1, 'maht3', '$2a$10$DY7AKLJRshgrv3XOJWZ8iOxehE.gS5XzPd6z.tvJpCAekSiqOVrPW', '$2a$10$DY7AKLJRshgrv3XOJWZ8iO', NULL, NULL, '2020-12-05 19:30:16', '2020-12-05 19:30:46');

-- ----------------------------
-- Table structure for admin_department
-- ----------------------------
DROP TABLE IF EXISTS `admin_department`;
CREATE TABLE `admin_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序字段',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级部门编号',
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
-- Table structure for oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_access_token`;
CREATE TABLE `oauth2_access_token`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问令牌',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
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
INSERT INTO `oauth2_access_token` VALUES ('3d99e8e0044a4678b23244a93b421c5c', 1, 2, 'e307796702a64bd99ee4a11b923b0390', '2020-12-07 15:29:55', '127.0.0.1', '2020-12-07 22:41:54', '2020-12-07 22:41:54', b'0');
INSERT INTO `oauth2_access_token` VALUES ('4bfdfae8871b45c2a9448ce8c868281d', 1, 1, '19910735f5584ce0952136fd972137e0', '2020-12-07 15:01:59', '127.0.0.1', '2020-12-07 22:14:00', '2020-12-07 22:14:00', b'0');
INSERT INTO `oauth2_access_token` VALUES ('a5fd8ad477214ee38f39630fc25d5cc7', 1, 2, '1bc873bdbcbd468da366d50f0c089eea', '2020-12-07 15:38:20', '127.0.0.1', '2020-12-07 22:50:19', '2020-12-07 22:50:19', b'0');
INSERT INTO `oauth2_access_token` VALUES ('yudaoyuanma', 1, 2, '7fc104020a2f428abece37c2d3f91839', '2021-07-05 10:39:14', '127.0.0.1', '2020-07-05 22:51:13', '2020-07-07 17:15:10', b'0');
INSERT INTO `oauth2_access_token` VALUES ('yunai', 243, 1, 'yunai_refresh', '2055-07-03 21:24:01', '127.0.0.1', '2020-07-04 09:36:04', '2020-07-24 11:08:46', b'0');

-- ----------------------------
-- Table structure for oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_refresh_token`;
CREATE TABLE `oauth2_refresh_token`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号，刷新令牌',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NOT NULL COMMENT '用户类型',
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
INSERT INTO `oauth2_refresh_token` VALUES ('19910735f5584ce0952136fd972137e0', 1, 1, '2020-12-08 02:13:31', '127.0.0.1', '2020-12-07 22:13:35', '2020-12-07 22:13:35', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('1bc873bdbcbd468da366d50f0c089eea', 1, 2, '2020-12-08 02:50:19', '127.0.0.1', '2020-12-07 22:50:19', '2020-12-07 22:50:19', b'0');
INSERT INTO `oauth2_refresh_token` VALUES ('e307796702a64bd99ee4a11b923b0390', 1, 2, '2020-12-08 02:41:46', '127.0.0.1', '2020-12-07 22:41:47', '2020-12-07 22:41:47', b'0');

-- ----------------------------
-- Table structure for permission_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_admin_role`;
CREATE TABLE `permission_admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `admin_id` int(11) NOT NULL COMMENT '管理员编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_admin_role
-- ----------------------------
INSERT INTO `permission_admin_role` VALUES (35, 1, 1, '2019-05-17 17:08:37', '2020-04-23 07:59:16', b'0');

-- ----------------------------
-- Table structure for permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_resource`;
CREATE TABLE `permission_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '菜单名',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` int(11) NOT NULL COMMENT '资源类型1：菜单，2:按钮',
  `sort` int(11) NOT NULL COMMENT '排序',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '父级资源编号(外键：{@link ResourceDO#id})',
  `route` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端路由',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `view` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '前端界面',
  `create_admin_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_resource
-- ----------------------------
INSERT INTO `permission_resource` VALUES (1, '管理员信息', NULL, 2, -1, 0, '/admin/admin/info', NULL, NULL, 0, '2019-02-24 09:07:43', '2019-05-15 22:57:30', b'1');
INSERT INTO `permission_resource` VALUES (13, '系统管理', '', 1, 8, 0, '/system', 'el-icon-s-tools', NULL, 0, '2019-03-10 03:59:07', '2020-07-11 16:53:17', b'0');
INSERT INTO `permission_resource` VALUES (14, '员工列表', 'system:admin:page', 1, 1, 13, 'admin-list', 'people', 'admin/admin/index', 0, '2019-03-10 03:59:29', '2020-07-14 13:31:15', b'0');
INSERT INTO `permission_resource` VALUES (15, '权限列表', 'system:resource:tree', 1, 4, 13, 'resource-list', 'el-icon-s-operation', 'permission/resource/index', 0, '2019-03-10 03:59:56', '2020-07-14 13:25:55', b'0');
INSERT INTO `permission_resource` VALUES (16, '角色列表', 'system:role:page', 1, 3, 13, 'role-list', 'peoples', 'permission/role/index', 0, '2019-03-10 04:00:35', '2020-07-14 13:25:59', b'0');
INSERT INTO `permission_resource` VALUES (19, '数据字典', 'system:data-dict:list', 1, 5, 13, 'data-dict-list', 'el-icon-reading', 'datadict/index', 0, '2019-03-15 19:10:30', '2020-07-15 10:58:48', b'0');
INSERT INTO `permission_resource` VALUES (20, '商品管理', '', 1, 4, 0, '/product', 'el-icon-potato-strips', NULL, 0, '2019-03-15 19:53:09', '2020-07-25 18:27:15', b'0');
INSERT INTO `permission_resource` VALUES (21, '商品列表', NULL, 1, 1, 20, 'product-spu-list', NULL, NULL, 0, '2019-03-15 19:55:22', '2020-07-10 12:24:49', b'0');
INSERT INTO `permission_resource` VALUES (22, '展示类目', 'product:category:tree', 1, 2, 20, 'product-category-list', 'el-icon-lollipop', 'product/category/list', 0, '2019-03-15 19:56:42', '2020-07-25 18:24:16', b'0');
INSERT INTO `permission_resource` VALUES (23, '营销管理', NULL, 1, 7, 0, 'promotion', NULL, NULL, 0, '2019-03-30 22:42:13', '2020-07-10 12:25:00', b'0');
INSERT INTO `permission_resource` VALUES (24, '首页广告', NULL, 1, 1, 23, 'banner-list', NULL, NULL, 0, '2019-03-30 22:54:57', '2020-07-10 12:25:04', b'0');
INSERT INTO `permission_resource` VALUES (25, '商品推荐', NULL, 1, 2, 23, 'product-recommend-list', NULL, NULL, 0, '2019-04-01 13:17:28', '2020-07-10 12:25:09', b'0');
INSERT INTO `permission_resource` VALUES (26, '优惠劵', NULL, 1, 3, 23, 'coupon-card-template-list', NULL, NULL, 0, '2019-04-04 16:02:14', '2020-07-10 12:25:14', b'0');
INSERT INTO `permission_resource` VALUES (27, '订单管理', NULL, 1, 5, 0, 'order', NULL, NULL, 0, '2019-04-06 12:53:55', '2020-07-10 12:25:17', b'0');
INSERT INTO `permission_resource` VALUES (28, '订单管理', NULL, 1, 1, 27, 'order-list', NULL, NULL, 0, '2019-04-06 12:57:17', '2020-07-10 12:25:21', b'0');
INSERT INTO `permission_resource` VALUES (29, '商品品牌', 'product:brand:page', 1, 3, 20, 'product-brand-list', 'el-icon-orange', 'product/brand/list', 0, '2019-04-09 17:58:36', '2020-07-25 23:40:02', b'0');
INSERT INTO `permission_resource` VALUES (30, '发布商品', NULL, 1, -99, 20, 'product-spu-add', NULL, NULL, 0, '2019-05-01 21:01:38', '2020-07-10 12:25:29', b'0');
INSERT INTO `permission_resource` VALUES (31, '概述', NULL, 1, 0, 0, '/home', NULL, NULL, 0, '2019-05-03 00:01:33', '2020-07-12 00:06:47', b'0');
INSERT INTO `permission_resource` VALUES (32, '数据分析', NULL, 1, 1, 0, '/statistic', NULL, NULL, 0, '2019-05-03 00:02:08', '2020-07-12 00:07:14', b'0');
INSERT INTO `permission_resource` VALUES (33, '店铺资产', '', 1, 2, 0, '/pay', 'money-collect', NULL, 0, '2019-05-03 00:02:57', '2020-07-12 00:25:41', b'0');
INSERT INTO `permission_resource` VALUES (34, '会员管理', NULL, 1, 6, 0, '/member', NULL, NULL, 0, '2019-05-03 00:03:55', '2020-07-23 07:11:42', b'0');
INSERT INTO `permission_resource` VALUES (41, '限时折扣', NULL, 1, 24, 23, 'time-limit-discount-list', NULL, NULL, 0, '2019-05-07 22:34:30', '2020-07-10 12:26:12', b'0');
INSERT INTO `permission_resource` VALUES (42, '满减送', NULL, 1, 25, 23, 'full-privilege-list', NULL, NULL, 0, '2019-05-08 00:05:20', '2020-07-10 12:26:24', b'0');
INSERT INTO `permission_resource` VALUES (43, '会员资料', NULL, 1, 1, 34, 'user-list', 'el-icon-user', 'user/user/index', 0, '2019-05-08 11:11:22', '2020-07-23 07:10:12', b'0');
INSERT INTO `permission_resource` VALUES (44, '支付单', NULL, 1, 1, 33, 'transaction-list', NULL, NULL, 0, '2019-05-08 14:17:15', '2020-07-10 12:25:47', b'0');
INSERT INTO `permission_resource` VALUES (45, '退款单', NULL, 1, 2, 33, 'refund-list', NULL, NULL, 0, '2019-05-08 16:58:05', '2020-07-10 12:25:51', b'0');
INSERT INTO `permission_resource` VALUES (46, '订单售后', NULL, 1, 1, 27, 'order-refunds', NULL, NULL, 0, '2019-05-09 19:57:23', '2020-07-10 12:25:57', b'0');
INSERT INTO `permission_resource` VALUES (47, '新建员工', 'system:admin:create', 2, 1, 14, '', NULL, NULL, 0, '2019-05-15 23:43:58', '2020-07-14 07:07:01', b'0');
INSERT INTO `permission_resource` VALUES (48, '新建字典', 'system:data-dict:create', 2, 1, 19, '', NULL, NULL, 0, '2019-05-16 16:00:57', '2020-07-15 10:50:09', b'0');
INSERT INTO `permission_resource` VALUES (49, '编辑字典', 'system:data-dict:update', 2, 2, 19, '', NULL, NULL, 0, '2019-05-16 16:01:18', '2020-07-15 10:50:20', b'0');
INSERT INTO `permission_resource` VALUES (50, '删除字典', 'system:data-dict:delete', 2, 3, 19, '', NULL, NULL, 0, '2019-05-16 16:01:36', '2020-07-15 10:50:45', b'0');
INSERT INTO `permission_resource` VALUES (51, '短信服务', '', 1, 3, 0, 'sms', 'user', NULL, 0, '2019-05-26 12:00:31', '2020-07-10 12:27:09', b'0');
INSERT INTO `permission_resource` VALUES (52, '短信签名', '', 1, 1, 51, 'sign-list', 'user', NULL, 0, '2019-05-26 12:01:56', '2020-07-10 12:26:08', b'0');
INSERT INTO `permission_resource` VALUES (53, '短信模板', '', 1, 2, 51, 'template-list', 'user', NULL, 0, '2019-05-26 12:02:19', '2020-07-10 11:37:10', b'0');
INSERT INTO `permission_resource` VALUES (54, '拼团购', '', 1, 6, 23, 'group-buy-list', ' ', NULL, 0, '2019-07-07 16:51:47', '2020-07-10 12:26:02', b'0');
INSERT INTO `permission_resource` VALUES (55, '新建权限', 'system:resource:create', 2, 1, 15, '', NULL, NULL, 0, '2020-04-27 15:45:21', '2020-07-14 07:06:52', b'0');
INSERT INTO `permission_resource` VALUES (56, '更新权限	', 'system:resource:update', 2, 2, 15, '', NULL, NULL, 0, '2020-04-27 15:51:01', '2020-04-27 15:51:01', b'0');
INSERT INTO `permission_resource` VALUES (57, '删除权限', 'system:resource:delete', 2, 3, 15, '', NULL, NULL, 0, '2020-04-27 15:51:32', '2020-04-27 15:51:31', b'0');
INSERT INTO `permission_resource` VALUES (59, '新建角色', 'system:role:create', 2, 1, 16, '', NULL, NULL, 0, '2020-04-27 20:49:43', '2020-07-14 07:06:41', b'0');
INSERT INTO `permission_resource` VALUES (60, '更新角色', 'system:role:update', 2, 2, 16, '', NULL, NULL, 0, '2020-04-27 20:50:15', '2020-04-28 09:51:33', b'0');
INSERT INTO `permission_resource` VALUES (62, '分配管理员角色', 'assign-role-resource', 2, 4, 14, '', NULL, NULL, 0, '2020-04-29 18:41:08', '2020-07-13 22:44:22', b'0');
INSERT INTO `permission_resource` VALUES (69, '23', NULL, 2, 1, 31, NULL, NULL, NULL, 1, '2020-07-11 17:03:30', '2020-07-12 00:06:35', b'1');
INSERT INTO `permission_resource` VALUES (70, '123', NULL, 2, 1, 51, NULL, NULL, NULL, 1, '2020-07-11 17:31:30', '2020-07-11 17:31:30', b'0');
INSERT INTO `permission_resource` VALUES (71, '1', NULL, 2, 1, 31, NULL, NULL, NULL, 1, '2020-07-11 17:38:25', '2020-07-12 00:06:37', b'1');
INSERT INTO `permission_resource` VALUES (72, '测试菜单', NULL, 1, 1, 0, 'test', NULL, 'ooxx', 1, '2020-07-11 18:02:23', '2020-07-12 00:06:13', b'1');
INSERT INTO `permission_resource` VALUES (73, '测试按钮', NULL, 2, 1, 0, NULL, NULL, NULL, 1, '2020-07-11 23:56:45', '2020-07-12 00:06:08', b'1');
INSERT INTO `permission_resource` VALUES (74, 'biu', NULL, 1, 1, 0, '11', NULL, NULL, 1, '2020-07-12 00:05:51', '2020-07-12 00:06:06', b'1');
INSERT INTO `permission_resource` VALUES (75, '修改员工', 'system:admin:update', 2, 2, 14, NULL, NULL, NULL, 1, '2020-07-13 22:33:14', '2020-07-13 22:33:14', b'0');
INSERT INTO `permission_resource` VALUES (76, '开启或禁用员工', 'system:admin:update-status', 2, 3, 14, NULL, NULL, NULL, 1, '2020-07-13 22:33:35', '2020-07-13 22:33:35', b'0');
INSERT INTO `permission_resource` VALUES (77, '删除角色', 'system:role:delete', 2, 3, 16, NULL, NULL, NULL, 1, '2020-07-13 22:40:20', '2020-07-13 22:40:20', b'0');
INSERT INTO `permission_resource` VALUES (78, '部门列表', 'system:department:tree', 1, 2, 13, 'department-list', 'el-icon-refrigerator', 'admin/department/index', 1, '2020-07-14 13:36:04', '2020-07-14 13:36:04', b'0');
INSERT INTO `permission_resource` VALUES (79, '新建部门', 'system:department:create', 2, 1, 78, NULL, NULL, NULL, 1, '2020-07-14 13:44:32', '2020-07-14 13:44:32', b'0');
INSERT INTO `permission_resource` VALUES (80, '修改部门', 'system:department:update', 2, 2, 78, NULL, NULL, NULL, 1, '2020-07-14 13:45:15', '2020-07-14 13:45:15', b'0');
INSERT INTO `permission_resource` VALUES (81, '删除部门', 'system:department:delete', 2, 3, 78, NULL, NULL, NULL, 1, '2020-07-14 13:47:40', '2020-07-14 13:47:40', b'0');
INSERT INTO `permission_resource` VALUES (82, '日志管理', NULL, 1, 9, 0, '/system-log', 'el-icon-pear', NULL, 1, '2020-07-15 17:44:33', '2020-07-23 07:08:40', b'0');
INSERT INTO `permission_resource` VALUES (83, '访问日志', 'system:system-access-log:page', 1, 1, 82, 'system-access-log-list', 'el-icon-receiving', 'system-log/system-access-log/index', 1, '2020-07-15 17:47:08', '2020-07-16 14:31:10', b'0');
INSERT INTO `permission_resource` VALUES (84, '异常日志', 'system:system-exception-log:page', 1, 2, 82, 'system-exception-log-list', 'el-icon-collection', 'system-log/system-exception-log/index', 1, '2020-07-16 12:56:15', '2020-07-16 14:32:00', b'0');
INSERT INTO `permission_resource` VALUES (85, '处理异常', 'system:system-exception-log:process', 2, 1, 84, NULL, NULL, NULL, 1, '2020-07-16 14:32:42', '2020-07-16 14:32:42', b'0');
INSERT INTO `permission_resource` VALUES (86, '错误码', 'system:error-dict:page', 1, 6, 13, 'error-code-list', 'el-icon-picture-outline-round', 'errorCode/index.vue', 1, '2020-07-20 19:25:35', '2020-07-20 19:27:09', b'0');
INSERT INTO `permission_resource` VALUES (87, '新建错误码', 'system:error-code:create', 2, 1, 86, NULL, NULL, NULL, 1, '2020-07-20 20:27:47', '2020-07-20 20:27:47', b'0');
INSERT INTO `permission_resource` VALUES (88, '修改错误码', 'system:error-code:update', 2, 2, 86, NULL, NULL, NULL, 1, '2020-07-20 20:28:04', '2020-07-20 20:28:04', b'0');
INSERT INTO `permission_resource` VALUES (89, '删除错误码', 'system:error-code:delete', 2, 3, 86, NULL, NULL, NULL, 1, '2020-07-20 20:28:22', '2020-07-20 20:28:22', b'0');
INSERT INTO `permission_resource` VALUES (90, '会员地址', NULL, 1, 2, 34, 'user-address-list', NULL, NULL, 1, '2020-07-23 07:11:23', '2020-07-23 07:11:23', b'0');
INSERT INTO `permission_resource` VALUES (91, '新建类目', 'product:category:create', 2, 1, 22, NULL, NULL, NULL, 1, '2020-07-25 18:25:13', '2020-07-25 18:25:13', b'0');
INSERT INTO `permission_resource` VALUES (92, '修改类目', 'product:category:update', 2, 2, 22, NULL, NULL, NULL, 1, '2020-07-25 18:25:41', '2020-07-25 18:25:41', b'0');
INSERT INTO `permission_resource` VALUES (93, '删除类目', 'product:category:delete', 2, 3, 22, NULL, NULL, NULL, 1, '2020-07-25 18:26:02', '2020-07-25 18:26:02', b'0');
INSERT INTO `permission_resource` VALUES (94, '新建品牌', 'product:brand:create', 2, 1, 29, NULL, NULL, NULL, 1, '2020-07-25 23:40:47', '2020-07-25 23:40:47', b'0');
INSERT INTO `permission_resource` VALUES (95, '修改品牌', 'product:brand:update', 2, 2, 29, NULL, NULL, NULL, 1, '2020-07-25 23:41:01', '2020-07-25 23:41:01', b'0');
INSERT INTO `permission_resource` VALUES (96, '删除品牌', 'product:brand:delete', 2, 3, 29, NULL, NULL, NULL, 1, '2020-07-25 23:41:17', '2020-07-25 23:41:17', b'0');
INSERT INTO `permission_resource` VALUES (97, '商品规格', NULL, 1, 5, 20, 'product-attr-list', 'el-icon-dish', 'product/attr/list', 1, '2020-07-28 13:44:06', '2020-07-30 01:11:57', b'0');
INSERT INTO `permission_resource` VALUES (98, '规格键列表', 'product:attr-value:page', 2, 1, 97, NULL, NULL, NULL, 1, '2020-07-30 01:09:02', '2020-07-30 01:09:47', b'0');
INSERT INTO `permission_resource` VALUES (99, '新建规格键', 'product:attr-value:create', 2, 2, 97, NULL, NULL, NULL, 1, '2020-07-30 01:09:39', '2020-07-30 01:09:39', b'0');
INSERT INTO `permission_resource` VALUES (100, '修改规格键', 'product:attr-key:update', 2, 3, 97, NULL, NULL, NULL, 1, '2020-07-30 01:10:07', '2020-07-30 01:10:07', b'0');
INSERT INTO `permission_resource` VALUES (101, '规格值列表', 'product:attr-value:list', 2, 11, 97, NULL, NULL, NULL, 1, '2020-07-30 01:10:26', '2020-07-30 01:10:26', b'0');
INSERT INTO `permission_resource` VALUES (102, '新建规格值', 'product:attr-value:create', 2, 12, 97, NULL, NULL, NULL, 1, '2020-07-30 01:10:48', '2020-07-30 01:10:48', b'0');
INSERT INTO `permission_resource` VALUES (103, '修改规格值', 'product:attr-value:update', 2, 13, 97, NULL, NULL, NULL, 1, '2020-07-30 01:11:04', '2020-07-30 01:11:04', b'0');
INSERT INTO `permission_resource` VALUES (104, 'testadd', 'resource:add', 1, 1, 0, 'test/add', 'add', '@/views/example/edit', NULL, '2020-12-05 22:34:40', '2020-12-05 22:34:40', b'0');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色名',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `type` tinyint(4) NOT NULL COMMENT '角色类型1：内置角色 2：自定义角色',
  `create_admin_id` int(11) NULL DEFAULT NULL COMMENT '创建管理员编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES (1, '超级管理员', 'SUPER_ADMIN', 1, 0, '2019-02-24 09:03:51', '2020-04-30 16:53:38', b'0');
INSERT INTO `permission_role` VALUES (17, '管理员', 'ADMIN', 2, NULL, '2020-12-05 19:33:53', '2020-12-05 19:33:53', b'0');

-- ----------------------------
-- Table structure for permission_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `permission_role_resource`;
CREATE TABLE `permission_role_resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(11) NOT NULL DEFAULT -1 COMMENT '角色编号(外键：{@link RoleDO}',
  `resource_id` int(11) NOT NULL DEFAULT -1 COMMENT '资源编号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 860 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_access_log
-- ----------------------------
DROP TABLE IF EXISTS `system_access_log`;
CREATE TABLE `system_access_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NULL DEFAULT NULL COMMENT '用户类型',
  `trace_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链路追踪编号',
  `application_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用名',
  `uri` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '访问地址',
  `query_string` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '参数',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'http 方法',
  `user_agent` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'userAgent',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ip',
  `start_time` datetime(0) NOT NULL COMMENT '请求时间',
  `response_time` int(11) NOT NULL COMMENT '响应时长 -- 毫秒级',
  `error_code` int(11) NOT NULL COMMENT '错误码',
  `error_message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误提示',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 87141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统访问日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_data_dict`;
CREATE TABLE `system_data_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `enum_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '大类枚举值',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '小类数值',
  `display_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '展示名',
  `sort` int(11) NOT NULL DEFAULT -1 COMMENT '排序值',
  `memo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '错误码编号',
  `code` int(11) NOT NULL DEFAULT 0 COMMENT '错误码编码',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '错误码错误提示',
  `type` tinyint(4) NOT NULL COMMENT '错误码类型',
  `group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '错误码分组',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误码备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 350 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '错误码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `system_exception_log`;
CREATE TABLE `system_exception_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `user_type` tinyint(4) NULL DEFAULT NULL COMMENT '用户类型',
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
  `exception_line_number` int(11) NOT NULL COMMENT '异常发生的方法所在行\n     *\n     * {@link StackTraceElement#getLineNumber()}',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统异常日志' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
