/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 100118
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 100118
File Encoding         : 65001

Date: 2016-12-06 00:27:28
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
  `parent_id` varchar(32) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `department_type` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
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
  `department_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
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
  `parent_id` varchar(32) DEFAULT NULL,
  `class` varchar(128) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `ui_sref` varchar(255) DEFAULT NULL,
  `order_` int(8) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `is_show` varchar(8) DEFAULT NULL,
  `project` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `class` (`class`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_function
-- ----------------------------

-- ----------------------------
-- Table structure for tsys_function_module
-- ----------------------------
DROP TABLE IF EXISTS `tsys_function_module`;
CREATE TABLE `tsys_function_module` (
  `id` varchar(32) NOT NULL,
  `function_id` varchar(32) NOT NULL,
  `uid` int(8) DEFAULT NULL,
  `class` varchar(255) NOT NULL,
  `key_` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `functionId` (`function_id`,`key_`,`uid`,`class`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_function_module
-- ----------------------------

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
  `role_id` varchar(32) DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
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
  `user_type` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `department_id` varchar(32) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
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
  `user_id` varchar(32) NOT NULL,
  `department_role_id` varchar(32) DEFAULT NULL,
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
  `user_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_user_role
-- ----------------------------
