/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : warehouse

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-04-20 17:17:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(256) NOT NULL,
  `dict_type` varchar(64) NOT NULL,
  `dict_remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `UK_SYS_DICT_TYPE` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '单位', 'company', null);
INSERT INTO `sys_dict` VALUES ('2', '经手人', 'handlerPerson', null);
INSERT INTO `sys_dict` VALUES ('3', '物资种类', 'objectType', null);

-- ----------------------------
-- Table structure for `sys_dict_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(64) NOT NULL,
  `detail_name` varchar(256) DEFAULT NULL,
  `detail_code` varchar(32) DEFAULT NULL,
  `detail_sort` varchar(32) DEFAULT NULL,
  `detail_type` varchar(32) DEFAULT NULL,
  `detail_state` varchar(32) DEFAULT NULL,
  `detail_content` varchar(256) DEFAULT NULL,
  `detail_remark` varchar(256) DEFAULT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', 'company', '物美超市', '', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('2', 'company', '安贞医院', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('3', 'company', '万达广场', '', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('4', 'company', '美廉美超市', '', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('5', 'company', '301医院', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('6', 'company', '沃尔玛超市', '', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('11', 'handlerPerson', '张三', '', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('12', 'handlerPerson', '李四', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('13', 'handlerPerson', '王五', '', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('14', 'handlerPerson', '赵六', '', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('31', 'objectType', '日常用品', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('32', 'objectType', '文具、图书', '', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('33', 'objectType', '服饰', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('34', 'objectType', '家用电器', '', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('35', 'objectType', '视频', '', '2', null, null, null, null, '2015-01-30', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `state` varchar(32) DEFAULT '10' COMMENT '状态',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
  `tel` varchar(32) DEFAULT NULL COMMENT '手机号',
  `create_id` int(11) DEFAULT '0',
  `create_time` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', '系统管理员', '1', null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('5', 'test', '123456', '测试', null, 'test@sina.com', '15812345678', '1', '2015-04-20 17:17:28');

-- ----------------------------
-- Table structure for `tb_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `type` int(11) DEFAULT NULL COMMENT '种类',
  `content` varchar(200) DEFAULT NULL COMMENT '说明',
  `status` int(11) DEFAULT '11' COMMENT '状态',
  `goods_count_in` int(11) DEFAULT '0' COMMENT '入库数量',
  `goods_count_out` int(11) DEFAULT '0' COMMENT '出库数量',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='物资';

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('1', '卫生纸', '31', '卫生纸大家都知道吧', '11', '66', '5', '2015-03-27 13:21:07', '1');
INSERT INTO `tb_goods` VALUES ('6', '电脑', '31', '笔记本', '11', '15', '5', '2015-03-28 21:34:32', '1');
INSERT INTO `tb_goods` VALUES ('11', '网线', '31', '电脑的网线', '11', '150', '120', '2015-03-29 20:15:12', '1');

-- ----------------------------
-- Table structure for `tb_goods_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods_detail`;
CREATE TABLE `tb_goods_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` int(11) DEFAULT NULL COMMENT '物资ID',
  `type` int(11) DEFAULT NULL COMMENT '类型：出库、入库',
  `company` int(11) DEFAULT NULL COMMENT '出库、入库单位',
  `content` varchar(200) DEFAULT NULL COMMENT '说明',
  `oper_count` int(11) DEFAULT '0' COMMENT '出库、入库数量',
  `oper_id` int(11) DEFAULT '0' COMMENT '经手人',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='物资出库入库明细';

-- ----------------------------
-- Records of tb_goods_detail
-- ----------------------------
INSERT INTO `tb_goods_detail` VALUES ('3', '1', '1', '1', null, '3', '11', '2015-03-27 14:31:12', '1');
INSERT INTO `tb_goods_detail` VALUES ('4', '1', '2', '5', '测试数据', '2', '12', '2015-03-27 14:31:43', '1');
INSERT INTO `tb_goods_detail` VALUES ('5', '1', '2', '5', null, '1', '13', '2015-03-27 14:53:22', '1');
INSERT INTO `tb_goods_detail` VALUES ('8', '1', '1', '1', null, '50', '11', '2015-03-27 16:13:09', '1');
INSERT INTO `tb_goods_detail` VALUES ('11', '1', '1', '1', null, '12', '11', '2015-03-27 16:25:11', '1');
INSERT INTO `tb_goods_detail` VALUES ('13', '1', '1', '1', null, '1', '11', '2015-03-27 16:31:42', '1');
INSERT INTO `tb_goods_detail` VALUES ('26', '1', '2', '4', null, '2', '11', '2015-03-28 21:32:29', '1');
INSERT INTO `tb_goods_detail` VALUES ('27', '6', '1', '1', null, '10', '11', '2015-03-28 21:35:03', '1');
INSERT INTO `tb_goods_detail` VALUES ('28', '6', '1', '1', null, '5', '12', '2015-03-28 21:35:24', '1');
INSERT INTO `tb_goods_detail` VALUES ('29', '6', '2', '4', null, '5', '11', '2015-03-28 21:35:47', '1');
INSERT INTO `tb_goods_detail` VALUES ('32', '11', '1', '1', '超市购买', '100', '12', '2015-03-29 20:15:59', '1');
INSERT INTO `tb_goods_detail` VALUES ('33', '11', '1', '3', null, '50', '13', '2015-03-29 20:16:21', '1');
INSERT INTO `tb_goods_detail` VALUES ('34', '11', '2', '6', '给他们啦', '50', '14', '2015-03-29 20:16:58', '1');
INSERT INTO `tb_goods_detail` VALUES ('36', '11', '2', '4', '再给他10根', '10', '14', '2015-03-29 20:17:48', '1');
INSERT INTO `tb_goods_detail` VALUES ('37', '11', '2', '4', '再给他们60~任性', '60', '11', '2015-03-29 20:18:05', '1');
INSERT INTO `tb_goods_detail` VALUES ('38', null, null, null, null, null, null, '2015-04-20 16:36:25', '1');
