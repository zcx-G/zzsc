/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : zscs

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2023-02-26 19:45:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `consignee` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '收货人',
  `sex` tinyint(4) NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `detail` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='地址管理';

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES ('1', '1', '小明', '1', '13812345678', '昌平区金燕龙办公楼', '公司', '1', '2021-07-20 17:26:33', '1417012167126876162');
INSERT INTO `address_book` VALUES ('2', '2', '小李', '1', '13512345678', '测试', '家', '0', '2021-07-20 17:23:47', '1417012167126876162');
INSERT INTO `address_book` VALUES ('3', '14', '赵信', '1', '13878787878', '4', '公司', '0', '2023-01-07 23:00:04', '14');
INSERT INTO `address_book` VALUES ('4', '14', '朱迟轩', '1', '13444445555', '金石镇', '家', '1', '2023-01-10 19:24:24', '14');
INSERT INTO `address_book` VALUES ('5', '19', '张三', '1', '13555555555', '湖南工程学院', '家', '1', '2023-02-24 15:26:41', '19');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1413386191767674888 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1413386191767674883', '肉类', '1', '2023-02-20 03:04:04', '1');
INSERT INTO `category` VALUES ('1413386191767674884', '星期一', '2', '2023-01-03 15:41:32', '1');
INSERT INTO `category` VALUES ('1413386191767674885', '蔬菜', '3', '2023-01-04 16:23:02', '1');
INSERT INTO `category` VALUES ('1413386191767674887', '水果', '5', '2023-02-20 10:39:00', '1');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `category_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品分类名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `image` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(400) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_user` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_good_name` (`name`) USING BTREE,
  KEY `idx_good_category` (`category_name`) USING BTREE,
  CONSTRAINT `good_category` FOREIGN KEY (`category_name`) REFERENCES `category` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=897 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品管理';

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('890', '土猪去皮五花', '肉类', '1290.00', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '新鲜猪肉500g', '0', '0', '2023-02-22 05:11:19', '1');
INSERT INTO `good` VALUES ('891', '巴沙鱼柳', '肉类', '899.00', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '去皮去刺免处理500g', '1', '0', '2023-02-21 09:06:54', '1');
INSERT INTO `good` VALUES ('892', '山地土鸡', '肉类', '2245.00', '05006286-47ea-4316-af41-c497eafc435a.jpg', '正宗土鸡1kg', '1', '0', '2023-02-21 09:07:03', '1');
INSERT INTO `good` VALUES ('893', '鲜活草鱼', '肉类', '1588.00', '576aed22-b88e-4581-b948-1567262eeff9.jpg', '1kg/条', '1', '0', '2023-02-21 09:07:12', '1');
INSERT INTO `good` VALUES ('894', '经典牛杂', '肉类', '1250.00', 'cedfe986-4025-4d27-8cf4-25c95d1d7c36.jpg', '500g/袋', '1', '0', '2023-02-21 09:07:21', '1');
INSERT INTO `good` VALUES ('895', '鸡翅根', '肉类', '699.00', '070b99f3-3f42-421b-9c45-b9298e3f26c0.jpg', '500g/袋', '1', '0', '2023-02-21 09:07:31', '1');
INSERT INTO `good` VALUES ('896', '鱿鱼花', '肉类', '1174.00', '065db486-a55c-4465-9831-0d9c40296c70.jpg', '1kg/袋', '1', '0', '2023-02-21 09:07:40', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `number` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户',
  `address_book_id` bigint(20) NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `pay_method` int(11) NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `consignee` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_address` (`address_book_id`),
  KEY `order_user` (`user_id`),
  CONSTRAINT `order_address` FOREIGN KEY (`address_book_id`) REFERENCES `address_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('2023011022393514', '2023011022393514', '4', '14', '4', '2023-01-10 22:39:36', '1', '57.00', '', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023011122113614', '2023011122113614', '4', '14', '4', '2023-01-11 22:11:36', '1', '12.00', '', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023020720463614', '2023020720463614', '3', '14', '4', '2023-02-07 20:46:36', '1', '34.00', '', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023020819552514', '2023020819552514', '2', '14', '4', '2023-02-08 19:55:26', '1', '12.00', '', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023022219571814', '2023022219571814', '2', '14', '4', '2023-02-22 07:57:18', '1', '20.00', '22', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023022221203814', '2023022221203814', '2', '14', '4', '2023-02-22 09:20:38', '1', '34.00', '22', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023022415283419', '2023022415283419', '2', '19', '5', '2023-02-24 03:28:34', '1', '81.00', '', '13555555555', '湖南工程学院', null, '张三');
INSERT INTO `orders` VALUES ('2023022516285814', '2023022516285814', '2', '14', '4', '2023-02-25 04:28:58', '1', '20.00', '', '13444445555', '金石镇', null, '朱迟轩');
INSERT INTO `orders` VALUES ('2023022516332714', '2023022516332714', '2', '14', '4', '2023-02-25 04:33:27', '1', '12.00', '', '13444445555', '金石镇', null, '朱迟轩');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `good_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_detail_order` (`order_id`),
  KEY `order_detail_good` (`good_id`),
  CONSTRAINT `order_detail_good` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`),
  CONSTRAINT `order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单明细表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('14', '巴沙鱼柳', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '2023011022393514', '891', '1', '8.99');
INSERT INTO `order_detail` VALUES ('15', '山地土鸡', '05006286-47ea-4316-af41-c497eafc435a.jpg', '2023011022393514', '892', '1', '22.45');
INSERT INTO `order_detail` VALUES ('16', '鲜活草鱼', '576aed22-b88e-4581-b948-1567262eeff9.jpg', '2023011022393514', '893', '1', '15.88');
INSERT INTO `order_detail` VALUES ('17', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023011022393514', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('18', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023011122113614', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('19', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023020720463614', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('20', '山地土鸡', '05006286-47ea-4316-af41-c497eafc435a.jpg', '2023020720463614', '892', '1', '22.45');
INSERT INTO `order_detail` VALUES ('21', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023020819552514', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('26', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023022219571814', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('27', '巴沙鱼柳', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '2023022219571814', '891', '1', '8.99');
INSERT INTO `order_detail` VALUES ('28', '山地土鸡', '05006286-47ea-4316-af41-c497eafc435a.jpg', '2023022221203814', '892', '1', '22.45');
INSERT INTO `order_detail` VALUES ('29', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023022221203814', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('30', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023022415283419', '890', '4', '12.90');
INSERT INTO `order_detail` VALUES ('31', '巴沙鱼柳', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '2023022415283419', '891', '1', '8.99');
INSERT INTO `order_detail` VALUES ('32', '山地土鸡', '05006286-47ea-4316-af41-c497eafc435a.jpg', '2023022415283419', '892', '1', '22.45');
INSERT INTO `order_detail` VALUES ('33', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023022516285814', '890', '1', '12.90');
INSERT INTO `order_detail` VALUES ('34', '巴沙鱼柳', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '2023022516285814', '891', '1', '8.99');
INSERT INTO `order_detail` VALUES ('35', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '2023022516332714', '890', '1', '12.90');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `image` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `good_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `shopping_good` (`good_id`),
  KEY `shopping_user` (`user_id`),
  CONSTRAINT `shopping_good` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`),
  CONSTRAINT `shopping_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='购物车';

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES ('1', null, null, '1', null, '1', '11.00', null);
INSERT INTO `shopping_cart` VALUES ('4', '巴沙鱼柳', '5482337f-5e5f-436c-83ad-b2dbba29cfa4.jpg', '15', '891', '2', '8.99', '2023-01-09 15:51:21');
INSERT INTO `shopping_cart` VALUES ('10', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '18', '890', '1', '12.90', '2023-02-22 21:10:35');
INSERT INTO `shopping_cart` VALUES ('14', '土猪去皮五花', '3859b428-ed8e-4e18-833c-14d31335a061.jpg', '20', '890', '1', '12.90', '2023-02-25 19:28:59');

-- ----------------------------
-- Table structure for tb_root
-- ----------------------------
DROP TABLE IF EXISTS `tb_root`;
CREATE TABLE `tb_root` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='员工信息';

-- ----------------------------
-- Records of tb_root
-- ----------------------------
INSERT INTO `tb_root` VALUES ('1', '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `status` int(11) DEFAULT '0' COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '19974074351', null, null, '1');
INSERT INTO `user` VALUES ('5', null, '19999999999', null, null, '1');
INSERT INTO `user` VALUES ('8', null, '12233333333', null, null, '1');
INSERT INTO `user` VALUES ('10', null, '13444444444', null, null, '1');
INSERT INTO `user` VALUES ('11', null, '13899999999', null, null, '1');
INSERT INTO `user` VALUES ('12', null, '19899999999', null, null, '1');
INSERT INTO `user` VALUES ('13', null, '18777777777', null, null, '1');
INSERT INTO `user` VALUES ('14', null, '13444445555', null, null, '1');
INSERT INTO `user` VALUES ('15', null, '134444455555', null, null, '1');
INSERT INTO `user` VALUES ('16', null, '13454445555', null, null, '1');
INSERT INTO `user` VALUES ('17', null, '13444445655', null, null, '1');
INSERT INTO `user` VALUES ('18', null, '13444455544', null, null, '1');
INSERT INTO `user` VALUES ('19', null, '13555555555', null, null, '1');
INSERT INTO `user` VALUES ('20', null, '13444443333', null, null, '1');
