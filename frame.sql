/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 100118
Source Host           : localhost:3306
Source Database       : frame2

Target Server Type    : MYSQL
Target Server Version : 100118
File Encoding         : 65001

Date: 2017-09-20 00:04:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_dep
-- ----------------------------
DROP TABLE IF EXISTS `s_dep`;
CREATE TABLE `s_dep` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(64) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `area_id` varchar(32) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_dep_role
-- ----------------------------
DROP TABLE IF EXISTS `s_dep_role`;
CREATE TABLE `s_dep_role` (
  `id` varchar(32) NOT NULL,
  `department_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_func
-- ----------------------------
DROP TABLE IF EXISTS `s_func`;
CREATE TABLE `s_func` (
  `id` varchar(32) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `parent_uid` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `department_id` varchar(32) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `last_login` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `card_no` varchar(32) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_user_drole
-- ----------------------------
DROP TABLE IF EXISTS `s_user_drole`;
CREATE TABLE `s_user_drole` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
