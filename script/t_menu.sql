/*
Navicat MySQL Data Transfer

Source Server         : 10.101.26.118_3306
Source Server Version : 50635
Source Host           : 10.101.26.118:3306
Source Database       : vcard-dev

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-09-07 11:33:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父ID',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT 'URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '系统菜单', '0', '');
INSERT INTO `t_menu` VALUES ('2', '控制面板', '1', '');
INSERT INTO `t_menu` VALUES ('3', '权限管理', '1', '');
INSERT INTO `t_menu` VALUES ('4', '用户维护', '3', '');
INSERT INTO `t_menu` VALUES ('5', '角色维护', '3', '');
INSERT INTO `t_menu` VALUES ('6', '许可维护', '3', '');
