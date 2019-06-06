/*
Navicat MySQL Data Transfer

Source Server         : 外销官网APP&外销FAQ
Source Server Version : 50635
Source Host           : 10.101.80.157:3306
Source Database       : vivopardiseforeign_dev

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-06-06 21:29:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '刘备', '语文', '92');
INSERT INTO `student` VALUES ('2', '关羽', '语文', '89');
INSERT INTO `student` VALUES ('3', '张飞', '语文', '90');
INSERT INTO `student` VALUES ('4', '刘备', '数学', '82');
INSERT INTO `student` VALUES ('5', '关羽', '数学', '94');
INSERT INTO `student` VALUES ('6', '张飞', '数学', '71');
INSERT INTO `student` VALUES ('7', '刘备', '英语', '88');
INSERT INTO `student` VALUES ('8', '关羽', '英语', '90');
INSERT INTO `student` VALUES ('9', '张飞', '英语', '97');

SELECT
    `name`,
    MAX(
        CASE
        WHEN  course='语文' THEN
            score
        END
    ) AS 语文,
    MAX(
        CASE
        WHEN course='数学' THEN
            score
        END
    ) AS 数学,
    MAX(
        CASE
        WHEN course='英语' THEN
            score
        END
    ) AS 英语
FROM
    student
GROUP BY `name`;

SELECT
    `name`,
    GROUP_CONCAT(course, ":", score SEPARATOR ',') AS 成绩
FROM
    student
GROUP BY
    `name`;