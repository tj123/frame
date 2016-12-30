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


/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-12-30 17:47:25
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
