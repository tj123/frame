/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-12-05 17:54:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tsys_config
-- ----------------------------
DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_config
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_department
-- ----------------------------
DROP TABLE IF EXISTS `tsys_department`;
CREATE TABLE `tsys_department` (
  `id` varchar(32) NOT NULL,
  `parentId` varchar(32) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `departmentType` varchar(32) DEFAULT NULL,
  `statusType` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `areaId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_department
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_department_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_department_role`;
CREATE TABLE `tsys_department_role` (
  `id` varchar(32) NOT NULL,
  `departmentId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_department_role
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_function
-- ----------------------------
DROP TABLE IF EXISTS `tsys_function`;
CREATE TABLE `tsys_function` (
  `id` varchar(32) NOT NULL,
  `uid` int(8) NOT NULL,
  `parentId` varchar(32) DEFAULT NULL,
  `class` varchar(128) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `uiSref` varchar(255) DEFAULT NULL,
  `order` int(8) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `isShow` varchar(8) DEFAULT NULL,
  `project` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `class` (`class`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_function
-- ----------------------------
INSERT INTO `tsys_function` VALUES ('09a6c41cec054619a6f03e3bf6e27ca7', '3', null, 'com.shundian.frame.common.function.sys.UserFunction', '用户管理', 'sys.user', '15', null, null, '1', 'FRM');
INSERT INTO `tsys_function` VALUES ('23484d0981364201b143a74e593f62d7', '4', null, 'com.shundian.frame.common.function.sys.RoleFunction', '角色管理', 'sys.role', '16', null, null, '1', 'FRM');
INSERT INTO `tsys_function` VALUES ('3be0d6d258d54b9681c1f08860f42c35', '5', null, 'com.shundian.frame.common.function.sys.ConfigFunction', '参数配置管理', 'sys.cfg', '19', null, null, '1', 'FRM');
INSERT INTO `tsys_function` VALUES ('445fc60b99d142a9bea2de8a8065addf', '6', null, 'com.shundian.frame.common.function.sys.LoginLogFunction', '登录日志管理', 'sys.log', '16', null, null, '1', 'FRM');
INSERT INTO `tsys_function` VALUES ('986289f06eef4c82821fdb3d079f413f', '2', null, 'com.shundian.frame.common.function.sys.DepartmentFunction', '部门管理', 'sys.dep', '9', null, null, '1', 'FRM');
INSERT INTO `tsys_function` VALUES ('b2d6157b34534f83ac7950584761fae9', '1', null, 'com.shundian.frame.common.function.sys.FuncFunction', '功能管理', 'sys.func', '8', null, null, '1', 'FRM');

-- ----------------------------
-- Table structure for tsys_function_module
-- ----------------------------
DROP TABLE IF EXISTS `tsys_function_module`;
CREATE TABLE `tsys_function_module` (
  `id` varchar(32) NOT NULL,
  `functionId` varchar(32) NOT NULL,
  `uid` int(8) DEFAULT NULL,
  `key` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `functionId` (`functionId`,`key`),
  UNIQUE KEY `uid` (`uid`,`functionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_function_module
-- ----------------------------
INSERT INTO `tsys_function_module` VALUES ('0242875b9d05499697f4b7ea22bcb044', '3be0d6d258d54b9681c1f08860f42c35', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('06a711f0e62e4c42a2a203290de1bc25', '09a6c41cec054619a6f03e3bf6e27ca7', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('0882ac8d43ab491cab89f540f57ee489', '986289f06eef4c82821fdb3d079f413f', '5', 'SBMT', '提交');
INSERT INTO `tsys_function_module` VALUES ('18409ebea42e4cc3b63463c45bcedb5a', '23484d0981364201b143a74e593f62d7', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('3bd95d2f1c234f49a2c04c4d8b624f5c', '3be0d6d258d54b9681c1f08860f42c35', '6', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('54fd0aa1934d45b89827db5148564a0a', '445fc60b99d142a9bea2de8a8065addf', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('67a8aab7b4504c30a586f4faa562d6d0', '445fc60b99d142a9bea2de8a8065addf', '6', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('72846d69cc434840a50778aad909fec2', '986289f06eef4c82821fdb3d079f413f', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('b233d72a8d4a420492d7ba674ebdb1f5', 'b2d6157b34534f83ac7950584761fae9', '6', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('c581c25b11b14393b69d1397a16522c0', 'b2d6157b34534f83ac7950584761fae9', '0', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('f7229de0b15c4f099e0af7b155b064e1', '986289f06eef4c82821fdb3d079f413f', '6', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('fb86e3b1b16540cfa79daaa549e6dd8e', '09a6c41cec054619a6f03e3bf6e27ca7', '6', 'SCAN', '扫描');

-- ----------------------------
-- Table structure for tsys_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_role
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_role_function_module
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role_function_module`;
CREATE TABLE `tsys_role_function_module` (
  `id` varchar(32) NOT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  `functionId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_role_function_module
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_user
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userType` varchar(32) DEFAULT NULL,
  `statusType` varchar(32) DEFAULT NULL,
  `departmentId` varchar(32) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_user
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_user_department_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user_department_role`;
CREATE TABLE `tsys_user_department_role` (
  `id` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `departmentRoleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_user_department_role
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user_role`;
CREATE TABLE `tsys_user_role` (
  `id` varchar(32) NOT NULL,
  `userId` varchar(32) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_user_role
-- ----------------------------
