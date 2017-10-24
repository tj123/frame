/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : frame

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-10-24 17:54:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_dep
-- ----------------------------
DROP TABLE IF EXISTS `s_dep`;
CREATE TABLE `s_dep` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `area_id` varchar(32) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_by_id` varchar(32) DEFAULT NULL,
  `update_by_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_dep
-- ----------------------------
INSERT INTO `s_dep` VALUES ('asdf', 'asdf', 'asdf', 'asdf', 'adsfa', 'dsfa', 'sdfasdf', '2017-10-09 17:52:59', '2017-10-23 17:53:02');

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
-- Records of s_dep_role
-- ----------------------------

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
-- Records of s_func
-- ----------------------------

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by_id` varchar(32) DEFAULT NULL,
  `update_by_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------

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
  `name` varchar(255) DEFAULT NULL,
  `card_no` varchar(32) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_by_id` varchar(32) DEFAULT NULL,
  `update_by_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('060c7bd6b3244856a6311bb35421ae60', '丫丫丫丫', 'sfaasdfasdfsd', null, '丫丫丫丫', '阿斯达斯', null, '阿斯达斯', '啊飒飒的', '2017-10-16 17:28:21', 'asdfas阿斯达斯', '阿斯达斯', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('0ecfcb0a79104dbd912330e0ecdaddeb', '啊是短发说的发生的', 'sfaasdfasdfsd', null, '阿斯达斯', '阿斯达说的', null, '阿斯达斯', 'asdfa啊是短发', '2017-10-16 17:08:14', 'asdfasdf ', '啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('1eb7686fd7b747f083a69d99c54bf906', 'asdAASD', 'sfaasdfasdfsd', null, 'asdfasdf', '阿斯达斯', null, '啊是短发说的发生的', '啊是短发说的发生的asd', '2017-10-16 17:07:21', 'asdA', 'asdfasd啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('29401ec8ad7144eeb65863be0cbb15d2', '阿斯达斯', 'sfaasdfasdfsd', null, '阿斯达斯', 'asd阿斯达斯', null, '阿萨斯', '啊盛大的 ', '2017-10-16 16:50:05', '阿斯达斯', '啊盛大的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('2ef6c70efa1b4dbdbb6480652d280d93', '所发生的', 'sfaasdfasdfsd', null, '啊沙发上', 'asdf啊是短发', null, '啊是短发速度', '阿斯达斯', '2017-10-16 17:51:49', 'asdfasdf', 'asdf啊是短发', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('3b7a302e5b624c63bebc48dc2ce9cb4a', '无法大是大非', 'sfaasdfasdfsd', null, '啊是短发说的发生的as', '啊是短发 ', null, '阿斯达斯', '啊是短发', '2017-10-16 17:06:52', 'asdfasd ', '啊是短发', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('44e66a4602dc446a91a0b6f01bdad3ec', 'asdfasdf', 'sfaasdfasdfsd', null, '啊是短发方式asd', 'asdf阿斯达斯', null, '阿斯达斯', '阿斯达斯', '2017-10-16 17:18:22', '阿斯达斯', '啊是短发', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('5c1ea20dedee452dad544c1604e55199', '说的发生的', 'sfaasdfasdfsd', null, '阿萨法', ' 阿萨斯', null, '阿斯达斯', '啊是短发', '2017-10-16 17:52:34', '啊是短发说的发生的', '阿萨斯', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('5eea1b9f4feb4c0f92bf5ac3f9cfe9d3', '慰问费爱上阿斯达asd', 'sfaasdfasdfsd', null, '阿斯达斯', '啊是短发说的发生的gh', null, '阿斯达斯', '阿斯达斯', '2017-10-16 17:08:43', '阿斯达斯', 'asdgasdhas', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('7767d69e17944c2cb121578a17d4d849', '撒旦', 'sfaasdfasdfsd', null, '啊是短发方式', 'asdfasdf', null, '啊是短发速度asdfasa', '啊是短发说的发生的', '2017-10-16 17:10:04', 'asdfasdf', '啊沙发上', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('92d3a65797d1416dbd6233b266fd62d9', '撒大声的', 'sfaasdfasdfsd', null, '阿斯达斯', '啊沙发上的', null, '啊是短发速度', '啊是短发说的发生的a', '2017-10-16 17:07:39', 'asdfasd', 'asdfasdf', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('9c3ba227382f41809aebbd77569af7a5', '阿斯达斯', 'sfaasdfasdfsd', null, 'asdfasdf', '啊是短发说的发生的', null, '啊是短发说的发生的', 'asdfasdf', '2017-10-16 16:56:58', '啊是短发说的发生的', '啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('ae5efce8950543979d708b7de29f9d3c', '的发生的', 'sfaasdfasdfsd', null, '阿萨法', '啊是短发说的发生的', null, '阿斯顿', '阿斯顿', '2017-10-16 17:06:33', '啊是短发', '啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('ae70fa1c727b4be8aed3c237252fb77f', 'asdfasdf', 'sfaasdfasdfsd', null, 'asdfasdfas', '啊是短发', null, '阿斯顿', '阿斯达斯', '2017-10-16 17:05:56', '啊是短发', 'asdg', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('ae82f3f304ef462796b427a25f5c9f10', '发生大幅阿斯顿', 'sfaasdfasdfsd', null, '啊是短发说的发生的', 'asdfasdf', null, '啊是短发速度asdfas', '啊是短发说的发生的', '2017-10-16 17:06:16', '啊是短发', '阿斯达', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('asd', 'fasd', 'fasd', 'fas', 'dfas', 'gasd', 'ga', 'asdgasdg', 'gasd', '2017-10-03 15:36:33', 'asdg', 'asdg', 'asdga', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdg', 'asd', 'gasd', 'agsd', 'hasd', 'hasd', 'hasdha', 'sdh', 'asdha', '2017-10-10 15:35:57', 'asdha', 'sdhasd', 'hasdh', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdga', 'sdasdh', 'asdhas', 'dhasd', 'hasd', 'hasd', 'hasdh', 'asdhas', 'asdha', '2017-10-16 15:36:45', 'asdhas', 'dhasdhas', 'asdhasdh', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdgas', 'dhas', 'dha', 'sdha', 'sdh', 'asdh', 'asd', 'has', 'dhas', '2017-10-10 15:37:14', 'asd', 'hasd', 'has', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdgasd', 'gasd', 'gasd', 'gasd', 'gas', 'dg', 'asdga', 'sdga', 'sdg', '2017-09-19 15:38:03', 'asd', 'gasd', 'gas', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdgsdsd', 'asdg', 'asdh', 'asdh', 'asdh', 'asdh', 'asdhas', 'asdh', 'asdh', '2017-10-02 15:38:58', 'asdh', 'asdh', 'asdhdh', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdgw', 'asdg', 'asd', 'gasd', 'gasd', 'ga', 'sdgas', 'dga', 'dgas', '2017-10-08 15:36:57', 'asdgasdg', 'asdgasd', 'gasdgasd', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdh', 'asdha', 'sdhas', 'dhasd', 'hasd', 'hasdh', 'asdhas', 'dhas', 'dhasd', '2017-10-23 15:37:37', 'asdh', 'asdh', 'asdhas', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdsd', 'hassd', 'dhash', 'asd', 'has', 'dhas', 'dhas', 'dhas', 'dhasd', '2017-10-12 07:38:49', 'asdhas', 'dhasd', 'hasdh', null, null, null, null);
INSERT INTO `s_user` VALUES ('asdsds', 'dhsd', 'hasdhasd', 'dh', 'hah', 'dh', 'dh', 'd', 'hd', '2017-10-12 07:38:50', 'asdh', 'asd', 'h', null, null, null, null);
INSERT INTO `s_user` VALUES ('b97a918c76fd4347870ff2426b1f73c2', '阿斯达', 'sfaasdfasdfsd', null, '阿斯达斯', 'asdf啊是短发', null, '阿斯达斯', '啊是短发', '2017-10-16 17:18:06', '啊是短发', '啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('c130859c740046adafc59fc3f3f44f35', '撒大声的', 'sfaasdfasdfsd', null, '阿斯达斯', 'asdfa阿斯达斯', null, '阿斯达斯', 'asdfa阿斯达斯', '2017-10-16 17:08:00', 'asdfasd', '阿斯达斯', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('c83c708f9ea5416ca719ec87e272314d', '啊是短发说的发生的', 'sfaasdfasdfsd', null, 'asdfasd啊是短发说的发生的', 'asdfasdf', null, 'asdfasdf', '啊是短发说的发生的', '2017-10-16 16:51:02', 'asdfasdf', '阿斯达斯', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('dc9f90fee93b44668bbe6304b3853414', '说的发生的', 'sfaasdfasdfsd', null, '啊是短发说的发生的', '啊是短发', null, 'asdfasdf', '啊是短发说的发生的', '2017-10-16 16:50:48', 'asdfasdf', 'asdfasd', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('ede3b19915b9456abc545f4ef06c3f6a', '发生大幅', 'sfaasdfasdfsd', null, '啊沙发上', '啊沙发上的', null, 'asdfasdf', '啊是短发说的发生的', '2017-10-16 17:11:23', '啊是短发', '啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('f7aaedf392324ed1977b2c7b774762ed', '二个地方', 'sfaasdfasdfsd', null, '啊是短发说的发生的', 'asdfasdf', null, '阿斯达斯', 'asdfa阿斯达斯', '2017-10-16 17:11:49', 'asdfasasdfasdf', 'asdfa啊是短发说的发生的', null, null, null, null, null);
INSERT INTO `s_user` VALUES ('fasdf', 'asdf', 'asdfa', 'sdfasdf', 'as', 'dfasdf', 'asdf', 'asdfasdf', 'asdfasd', '2017-10-02 15:16:15', 'asdfasd', 'fasd', 'fasdfasd', null, null, null, null);
INSERT INTO `s_user` VALUES ('gasd', 'gasdg', 'asdgas', 'dga', 'sdg', 'asdga', 'sdgasd', 'gasdg', 'asdg', '2017-10-17 15:35:45', 'asdga', 'sdgasdg', 'asdgasdg', null, null, null, null);
INSERT INTO `s_user` VALUES ('gasdg', 'asdh', 'asdh', 'asd', 'hasd', 'has', 'dhasd', 'has', 'dha', '2017-10-17 15:37:25', 'asdha', 'sdhas', 'dhasdh', null, null, null, null);

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
-- Records of s_user_drole
-- ----------------------------

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

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
