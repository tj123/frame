/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-01-06 15:41:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lscrm_function_privilege
-- ----------------------------
DROP TABLE IF EXISTS `lscrm_function_privilege`;
CREATE TABLE `lscrm_function_privilege` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_id` varchar(30) NOT NULL DEFAULT 'sys',
  `update_id` varchar(30) NOT NULL DEFAULT 'sys',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `validity` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '有效性 1.有效 0. 无效',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '编码',
  `function_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `parent_id` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '父节点',
  `is_leaf_node` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否叶子节点(叶子结点 就是度为0的结点 就是没有子结点的结点),在添加子节点时,需要将parent_id is_leaf_node 设置成0',
  `sub_system_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属子系统',
  `is_hidden` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'UI是否隐藏,ui上不展示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='子code 按照父code 来创建\r\n如: customer.add';

-- ----------------------------
-- Records of lscrm_function_privilege
-- ----------------------------
INSERT INTO `lscrm_function_privilege` VALUES ('1', 'sys', 'sys', '2016-09-07 15:20:40', '2016-09-07 15:21:17', '1', 'WEB.PERMISSION', '权限管理', '0', '0', '1', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('2', 'sys', 'sys', '2016-09-07 15:21:12', '2016-09-07 15:21:40', '1', 'WEB.PERMISSION.USER-MGMT', '用户管理', '1', '0', '1', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('3', 'sys', 'sys', '2016-09-07 15:22:16', '2016-09-07 15:22:35', '1', 'WEB.PERMISSION.USER-MGMT.ADD', '新增用户', '2', '1', '1', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('4', 'sys', 'sys', '2016-09-07 15:23:17', '2016-09-07 15:23:17', '1', 'WEB.PERMISSION.USER-MGMT.MODIFY', '修改用户', '2', '1', '1', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('5', 'sys', 'sys', '2016-09-07 16:10:37', '2016-09-07 16:10:42', '1', 'APP.CUSTOMER', '客户管理', '0', '0', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('6', 'sys', 'sys', '2016-09-07 16:11:06', '2016-09-07 16:11:20', '1', 'APP.CUSTOMER.ADD', '添加客户', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('7', 'sys', 'sys', '2016-09-07 16:11:06', '2016-09-07 16:11:20', '1', 'APP.CUSTOMER.MODIFY', '修改客户', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('8', 'sys', 'sys', '2016-09-07 16:12:33', '2016-09-07 16:14:10', '1', 'APP.CUSTOMER.VIEWDETAIL', '查看客户详情', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('9', 'sys', 'sys', '2016-09-07 16:12:33', '2016-09-07 16:12:33', '1', 'APP.CUSTOMER.ADDVISIT', '添加拜访', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('10', 'sys', 'sys', '2016-09-07 16:13:59', '2016-09-07 16:14:17', '1', 'APP.CUSTOMER.VIEWDEMAND', '查看客户采购需求', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('11', 'sys', 'sys', '2016-09-07 16:13:59', '2016-09-07 16:14:17', '1', 'APP.CUSTOMER.VIEWORDER', '查看客户订单', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('12', 'sys', 'sys', '2016-09-07 16:13:59', '2016-09-07 16:14:17', '1', 'APP.CUSTOMER.VIEWSHOP', '查看客户订单', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('13', 'sys', 'sys', '2016-09-07 16:17:28', '2016-09-07 16:18:39', '1', 'APP.CUSTOMER.VIEWREPLY', '查看抢单列表', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('14', 'sys', 'sys', '2016-09-07 16:19:32', '2016-09-07 16:23:51', '1', 'APP.CUSTOMER.FASTOPENSHOP', '快捷开店', '5', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('15', 'sys', 'sys', '2016-09-07 16:22:33', '2016-09-07 16:22:33', '1', 'APP.DEMAND', '需求管理', '0', '0', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('17', 'sys', 'sys', '2016-09-07 16:23:21', '2016-09-07 16:23:21', '1', 'APP.DEMAND.PRIVATE', '私海需求', '15', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('18', 'sys', 'sys', '2016-09-07 16:23:58', '2016-09-07 16:23:58', '1', 'APP.DEMAND.FEEDBACK', '找版反馈', '15', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('19', 'sys', 'sys', '2016-09-07 16:24:47', '2016-09-07 16:24:47', '1', 'APP.DEMAND.PUSHSEARCH', '推送卖家搜索', '15', '1', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('20', 'sys', 'sys', '2016-09-07 16:25:56', '2016-09-07 16:25:56', '1', 'APP.KPI', '绩效管理', '0', '0', '2', '0');
INSERT INTO `lscrm_function_privilege` VALUES ('21', 'sys', 'sys', '2016-09-07 16:27:02', '2016-09-07 16:27:02', '1', 'APP.KPI.VIEWDATA', '查看绩效数据', '20', '1', '2', '0');

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
INSERT INTO `tsys_function` VALUES ('1125da2d0bc14e1686882909ab919ce5', '3', null, 'com.shundian.frame.common.function.sys.UserFunction', '用户管理', 'sys.user', '5', null, null, '1', 'FRAME');
INSERT INTO `tsys_function` VALUES ('19d95a58157f4010b58166cb9144e00b', '1', '8352648b03224023b57429aa08c1764d', 'com.shundian.frame.common.function.sys.FuncFunction', '功能管理', 'sys.func', '8', null, null, '1', 'FRAME');
INSERT INTO `tsys_function` VALUES ('53a07081a6214c2db37e9e2529f0a84a', '5', '8352648b03224023b57429aa08c1764d', 'com.shundian.frame.common.function.sys.ConfigFunction', '参数配置管理', 'sys.cfg', '16', null, null, '1', 'FRAME');
INSERT INTO `tsys_function` VALUES ('8352648b03224023b57429aa08c1764d', '2', '', 'com.shundian.frame.common.function.sys.DepartmentFunction', '部门管理', 'sys.dep', '3', null, null, '1', 'FRAME');
INSERT INTO `tsys_function` VALUES ('87e2c953e3184cbeb8788195154bf63e', '7', null, 'com.shundian.frame.common.function.sys.RoleFunction', '角色管理', 'sys.role', '8', null, null, '1', 'FRAME');
INSERT INTO `tsys_function` VALUES ('e93e4969138f49348f05e2f582ab7f8a', '6', null, 'com.shundian.frame.common.function.sys.LoginLogFunction', '登录日志管理', 'sys.log', '0', null, null, '1', 'FRAME');

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
INSERT INTO `tsys_function_module` VALUES ('1915b1af76634c0da3818b448c1aef22', 'e93e4969138f49348f05e2f582ab7f8a', '6', 'com.shundian.frame.common.function.module.ScanModule', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('72e8a816e2174d518b66d3a26724b4cf', '8352648b03224023b57429aa08c1764d', '6', 'com.shundian.frame.common.function.module.ScanModule', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('82dc5f1d0590479da345dc596cc82666', '87e2c953e3184cbeb8788195154bf63e', '1', 'com.shundian.lib.function.DefaultModule', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('8e3723e031e04906bd011284ca726c31', '8352648b03224023b57429aa08c1764d', '5', 'com.shundian.frame.common.function.module.SubmitModule', 'SBMT', '提交');
INSERT INTO `tsys_function_module` VALUES ('8fa262d873544d0684f7c9a8eefb03a7', '53a07081a6214c2db37e9e2529f0a84a', '1', 'com.shundian.lib.function.DefaultModule', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('95f5e48f218e4764bd222af2f4fe1b13', '8352648b03224023b57429aa08c1764d', '1', 'com.shundian.lib.function.DefaultModule', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('ccc8ea63e79f47d89f326586418f5d60', '1125da2d0bc14e1686882909ab919ce5', '1', 'com.shundian.lib.function.DefaultModule', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('d1c9218f73a447fab749723a522e728d', '19d95a58157f4010b58166cb9144e00b', '1', 'com.shundian.lib.function.DefaultModule', 'DFT', '默认');
INSERT INTO `tsys_function_module` VALUES ('d4366a0aac5f481e83fe92376f6d2743', '19d95a58157f4010b58166cb9144e00b', '6', 'com.shundian.frame.common.function.module.ScanModule', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('e5978560125a4536a13e3b24aa580edc', '53a07081a6214c2db37e9e2529f0a84a', '6', 'com.shundian.frame.common.function.module.ScanModule', 'SCAN', '扫描');
INSERT INTO `tsys_function_module` VALUES ('f4ecbcb038004c3d8c265d552b703473', '1125da2d0bc14e1686882909ab919ce5', '6', 'com.shundian.frame.common.function.module.ScanModule', 'SCAN', '扫描');

-- ----------------------------
-- Table structure for tsys_role
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `ui_sref` varchar(255) DEFAULT NULL,
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_role
-- ----------------------------
INSERT INTO `tsys_role` VALUES ('0d72170653c14274b72a7a851ae3da29', 'wewfae', null, '2016-12-08 10:03:02');
INSERT INTO `tsys_role` VALUES ('19b8a50b6613423b9c0dd1e4965e1980', 'sslll', '123', '2016-12-08 10:11:15');
INSERT INTO `tsys_role` VALUES ('2fc09c7f57874fe183e2a5f8c8bd629f', '3344fsdfssss23223', '123sssasaa', '2016-12-08 10:11:38');
INSERT INTO `tsys_role` VALUES ('37f10d86f57e4fe0a6dbfc12d0e29173', '1211212312', '123', '2016-12-08 10:10:57');
INSERT INTO `tsys_role` VALUES ('52b46853170b4727b5f2e6f9b4b0dea3', '12112', '123', '2016-12-08 10:10:54');
INSERT INTO `tsys_role` VALUES ('5c44c20d56354c0eb32c5abf7c7e036b', 'wewfae234234', '234234', '2016-12-08 10:10:46');
INSERT INTO `tsys_role` VALUES ('5d0e0b0fbf774f90850ee67475206303', 'wewfae234234234', '234234', '2016-12-08 10:10:49');
INSERT INTO `tsys_role` VALUES ('5e8ca4d0f1444e39a5c23d3438737a9b', 'ssd12112123121wdssssd', '123', '2016-12-08 10:11:10');
INSERT INTO `tsys_role` VALUES ('68e447395b6c4007954acd55e4e42e59', '234', '345234', '2016-12-08 09:32:15');
INSERT INTO `tsys_role` VALUES ('706549c4301c4190a53088759db70c48', '223ssssd', '123', '2016-12-08 10:11:20');
INSERT INTO `tsys_role` VALUES ('73c360775802418e93911f819d50ffdb', 'ssd12112123121wdss', '123', '2016-12-08 10:11:07');
INSERT INTO `tsys_role` VALUES ('8721d84c3b6442d4a7d85c33da95a72e', 'ssd12112123121wd', '123', '2016-12-08 10:11:04');
INSERT INTO `tsys_role` VALUES ('8b85d80192f4417ea40ef25ee6548f74', '3344fsdf', '123', '2016-12-08 10:11:23');
INSERT INTO `tsys_role` VALUES ('9b7cd908f3714f10b3d41c24a5d9c9e2', '3344fsdfssss', '123sss', '2016-12-08 10:11:30');
INSERT INTO `tsys_role` VALUES ('9c4590a5a0ac431880b04446623418c2', 'wer5', null, '2016-12-08 09:41:48');
INSERT INTO `tsys_role` VALUES ('a126917ba59947a5988f45fee0263b90', '12112123121wd', '123', '2016-12-08 10:11:00');
INSERT INTO `tsys_role` VALUES ('a4a627ae25ba49f1ab134b3ed8ef5804', '23', null, '2016-12-08 09:35:39');
INSERT INTO `tsys_role` VALUES ('ff8a22e2ea7d4f0688e9ea60ce8f1d65', 'wer', null, '2016-12-08 00:00:00');

-- ----------------------------
-- Table structure for tsys_role_function_module
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role_function_module`;
CREATE TABLE `tsys_role_function_module` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `function_module_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`function_module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tsys_role_function_module
-- ----------------------------
INSERT INTO `tsys_role_function_module` VALUES ('ee9b7b204cf5453da0ef66315ca4242d', '0d72170653c14274b72a7a851ae3da29', '1915b1af76634c0da3818b448c1aef22');
INSERT INTO `tsys_role_function_module` VALUES ('26ec9b595eb84135b753c687a4d2d7c0', '0d72170653c14274b72a7a851ae3da29', '82dc5f1d0590479da345dc596cc82666');
INSERT INTO `tsys_role_function_module` VALUES ('bf19715eda974d539d6917542a3c6b49', '0d72170653c14274b72a7a851ae3da29', '8fa262d873544d0684f7c9a8eefb03a7');
INSERT INTO `tsys_role_function_module` VALUES ('492541cd57674b77a117ef8866c5d72e', '0d72170653c14274b72a7a851ae3da29', 'ccc8ea63e79f47d89f326586418f5d60');
INSERT INTO `tsys_role_function_module` VALUES ('1726a457ce0640c2a3efcc817ba50082', '0d72170653c14274b72a7a851ae3da29', 'd1c9218f73a447fab749723a522e728d');
INSERT INTO `tsys_role_function_module` VALUES ('5ba27df0c0514fd89273efa9fc2207c7', '0d72170653c14274b72a7a851ae3da29', 'd4366a0aac5f481e83fe92376f6d2743');
INSERT INTO `tsys_role_function_module` VALUES ('c56389ec287c448486a6bce317841bd9', '0d72170653c14274b72a7a851ae3da29', 'e5978560125a4536a13e3b24aa580edc');
INSERT INTO `tsys_role_function_module` VALUES ('383d100867774a99a4ecac5b7ccf99c3', '0d72170653c14274b72a7a851ae3da29', 'f4ecbcb038004c3d8c265d552b703473');
INSERT INTO `tsys_role_function_module` VALUES ('c97f0aebcce84dedb893883307950e63', '2fc09c7f57874fe183e2a5f8c8bd629f', '1915b1af76634c0da3818b448c1aef22');
INSERT INTO `tsys_role_function_module` VALUES ('e0052563fe084845bc23ad9d3c6a4316', '2fc09c7f57874fe183e2a5f8c8bd629f', 'd1c9218f73a447fab749723a522e728d');
INSERT INTO `tsys_role_function_module` VALUES ('aa392efbf32b4acf943bbbafb6513fcb', '2fc09c7f57874fe183e2a5f8c8bd629f', 'd4366a0aac5f481e83fe92376f6d2743');
INSERT INTO `tsys_role_function_module` VALUES ('a87ef3006e944406b29e9f86aee8779e', '52b46853170b4727b5f2e6f9b4b0dea3', '1915b1af76634c0da3818b448c1aef22');
INSERT INTO `tsys_role_function_module` VALUES ('22619c3f989d4a19924fb3bc8c248cb8', '5e8ca4d0f1444e39a5c23d3438737a9b', '1915b1af76634c0da3818b448c1aef22');
INSERT INTO `tsys_role_function_module` VALUES ('8eaaa8d9ec3444949c3c39ead2b25e65', '5e8ca4d0f1444e39a5c23d3438737a9b', '8fa262d873544d0684f7c9a8eefb03a7');
INSERT INTO `tsys_role_function_module` VALUES ('a9bd5e065b5648419cd2f608ca291e70', '5e8ca4d0f1444e39a5c23d3438737a9b', 'd1c9218f73a447fab749723a522e728d');
INSERT INTO `tsys_role_function_module` VALUES ('4b757bd5e9c045529aa3c23114e68113', '5e8ca4d0f1444e39a5c23d3438737a9b', 'd4366a0aac5f481e83fe92376f6d2743');
INSERT INTO `tsys_role_function_module` VALUES ('e9ce2a750a1043469dbaf3e8de46e7fc', '5e8ca4d0f1444e39a5c23d3438737a9b', 'e5978560125a4536a13e3b24aa580edc');

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
