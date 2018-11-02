/*
Navicat MySQL Data Transfer

Source Server         : 10.101.26.118_3306
Source Server Version : 50635
Source Host           : 10.101.26.118:3306
Source Database       : vcard-dev

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-10-31 18:03:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(2) NOT NULL DEFAULT '' COMMENT '姓名',
  `tel` varchar(11) NOT NULL DEFAULT '' COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='顾客表';

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '小王', '18883276534');
INSERT INTO `t_customer` VALUES ('2', '天天', '34565462354');
INSERT INTO `t_customer` VALUES ('3', '阿大', '12334545566');

-- ----------------------------
-- Table structure for t_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(5) NOT NULL DEFAULT '' COMMENT '地址',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '价格',
  `cust_id` int(11) NOT NULL DEFAULT '0' COMMENT '顾客ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='车票表';

-- ----------------------------
-- Records of t_ticket
-- ----------------------------
INSERT INTO `t_ticket` VALUES ('1', '武汉到重庆', '100', '1');
INSERT INTO `t_ticket` VALUES ('2', '北京到上海', '200', '1');
INSERT INTO `t_ticket` VALUES ('3', '深圳到广州', '50', '2');
