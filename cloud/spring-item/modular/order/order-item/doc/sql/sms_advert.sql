/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : mall_sms

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 11/03/2022 16:53:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_advert
-- ----------------------------
DROP TABLE IF EXISTS `sms_advert`;
CREATE TABLE `sms_advert`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `begin_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态(1:开启；0:关闭)',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sms_advert
-- ----------------------------
INSERT INTO `sms_advert` VALUES (12, '测试', 'http://a.youlai.tech:9000/default/0b6d7d2dfc334c88aa9e6eb5d6f4c090.jpg', '2021-12-26 00:00:00', '2021-08-02 00:00:00', 1, 1, '111', '111', NULL, '2021-12-26 22:36:45');
INSERT INTO `sms_advert` VALUES (13, '123', 'http://a.youlai.tech:9000/default/3fe04a5074ae48a385072619a6798a6a.jpg', '2021-12-07 00:00:00', '2021-09-13 00:00:00', 1, 1, '11', '1', NULL, '2021-12-26 22:37:57');
INSERT INTO `sms_advert` VALUES (18, '123213', 'http://a.youlai.tech:9000/default/3fdf5afdd1da4b42a340709c8f1c7000.jpg', NULL, '2021-09-07 00:00:00', 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sms_advert` VALUES (22, '123', 'http://a.youlai.tech:9000/default/4fda488a5ffa4268a441e838d0ec5c34.png', '2021-12-07 00:00:00', '2021-12-08 00:00:00', 1, 1, NULL, NULL, '2021-12-08 13:46:26', '2021-12-08 13:46:26');
INSERT INTO `sms_advert` VALUES (23, '123', 'http://a.youlai.tech:9000/default/51ddcae192894fe690d24d7a4dc0e8ae.jpg', '2021-12-14 00:00:00', '2021-12-09 00:00:00', 1, 11, NULL, NULL, '2021-12-08 13:46:52', '2021-12-08 13:46:52');
INSERT INTO `sms_advert` VALUES (27, '测试广告', 'http://a.youlai.tech:9000/default/9b53df224c094204a335b8552e9ec69c.jpg', '2021-12-13 00:00:00', '2021-12-31 00:00:00', 1, 1, NULL, NULL, '2021-12-24 11:04:26', '2021-12-24 11:04:26');

SET FOREIGN_KEY_CHECKS = 1;
