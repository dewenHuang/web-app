/*
Navicat MySQL Data Transfer

Source Server         : 10.101.26.118_3306
Source Server Version : 50635
Source Host           : 10.101.26.118:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-11-05 21:20:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '名称',
  `price` float(10,1) NOT NULL DEFAULT '0.0' COMMENT '价格',
  `detail` text NOT NULL COMMENT '描述',
  `pic` varchar(64) NOT NULL DEFAULT '' COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '笔记本', '5250.0', '华硕坠机堡垒', '1.jpg', '2018-11-05 16:34:12');
INSERT INTO `item` VALUES ('2', '玫瑰', '9.9', '红玫瑰', '2.jpg', '2018-11-05 16:35:03');
INSERT INTO `item` VALUES ('3', '羽扇', '35.0', '诸葛牌羽扇', '3.jpg', '2018-11-05 16:36:52');
INSERT INTO `item` VALUES ('4', '玩具手枪', '17.0', '黄河手枪', '4.jpg', '2018-11-05 16:37:15');
INSERT INTO `item` VALUES ('5', '战国策', '27.8', '人民出版社', '5.jpg', '2018-11-05 16:37:51');
INSERT INTO `item` VALUES ('6', '忘情水', '99999.0', '遗忘河水', '6.jpg', '2018-11-05 16:38:36');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `number` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `note` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '1', '000001', '笔记本', '2018-11-05 16:39:55');
INSERT INTO `order` VALUES ('2', '2', '000002', '玫瑰', '2018-11-05 16:40:30');
INSERT INTO `order` VALUES ('3', '4', '000003', '忘情水', '2018-11-05 16:40:47');
INSERT INTO `order` VALUES ('4', '2', '000005', '玩具手枪', '2018-11-05 16:41:18');
INSERT INTO `order` VALUES ('5', '3', '000006', '战国策', '2018-11-05 16:41:40');
INSERT INTO `order` VALUES ('6', '1', '000004', '羽扇', '2018-11-05 16:42:14');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` int(11) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `item_id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `item_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) USING BTREE,
  KEY `idx_item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '1', '1', '1');
INSERT INTO `order_detail` VALUES ('2', '2', '2', '999');
INSERT INTO `order_detail` VALUES ('3', '3', '6', '1');
INSERT INTO `order_detail` VALUES ('4', '4', '4', '1');
INSERT INTO `order_detail` VALUES ('5', '5', '5', '2');
INSERT INTO `order_detail` VALUES ('6', '6', '3', '100');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `birthday` date NOT NULL DEFAULT '0000-00-00' COMMENT '生日',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别: 0-男 1-女',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '武小天', '2017-08-13', '1', '山西吕梁');
INSERT INTO `user` VALUES ('2', '龙小云', '2017-08-02', '1', '江西南昌');
INSERT INTO `user` VALUES ('3', '杜小甫', '2017-04-25', '0', '河南巩义');
INSERT INTO `user` VALUES ('4', '屈小原', '2017-08-01', '0', '湖北宜昌');
