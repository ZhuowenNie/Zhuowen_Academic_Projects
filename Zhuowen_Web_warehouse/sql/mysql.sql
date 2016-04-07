 /**
 * 删除表
 */
drop table if exists sys_user;

/**
 * 用户表
 */
create table sys_user
(
  userid      int(11) NOT NULL AUTO_INCREMENT,
  username    varchar(32) NOT NULL COMMENT '用户名',
  password    varchar(32) NOT NULL COMMENT '密码',
  realname    varchar(32) DEFAULT NULL COMMENT '真实姓名',
  state       varchar(32) DEFAULT '10' comment '状态',
  email       varchar(64) DEFAULT NULL COMMENT 'email',
  tel         varchar(32) DEFAULT NULL COMMENT '手机号',
  create_id   int(11) default 0,
  create_time varchar(32),
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if exists sys_dict_detail;
drop table if exists sys_dict;

/**
 * 数据字典主表
 */
CREATE TABLE sys_dict
(
  dict_id int(11) NOT NULL AUTO_INCREMENT, 
  dict_name   VARCHAR(256) not null,
  dict_type  VARCHAR(64) not null,
  dict_remark VARCHAR(256),
  PRIMARY KEY (dict_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table sys_dict add unique UK_SYS_DICT_TYPE (dict_type);

/**
 * 数据字典明细表
 */
create table sys_dict_detail
(
  detail_id      int(11) NOT NULL AUTO_INCREMENT,
  dict_type      varchar(64) NOT NULL,
  detail_name    varchar(256),
  detail_code    varchar(32),
  detail_sort    varchar(32),
  detail_type    varchar(32),
  detail_state   varchar(32),
  detail_content varchar(256),
  detail_remark  varchar(256),
  create_time    varchar(32),
  create_id      int(11),
  PRIMARY KEY (detail_id)
  -- ,foreign key (dict_type) references sys_dict (dict_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 * 物资
 */ 
-- Table: tb_goods

-- DROP TABLE tb_goods;
drop table if exists tb_goods;

CREATE TABLE tb_goods (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(64) DEFAULT NULL COMMENT '名称',
  type int(11) DEFAULT NULL COMMENT '种类',
  content varchar(200) DEFAULT NULL COMMENT '说明',
  status int(11) DEFAULT 11 COMMENT '状态',
  goods_count_in int(11) DEFAULT 0 COMMENT '入库数量',
  goods_count_out int(11) DEFAULT 0 COMMENT '出库数量',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资';

/**
 * 物资出库入库明细
 */ 
-- Table: tb_goods_detail

-- DROP TABLE tb_goods_detail;
drop table if exists tb_goods_detail;

CREATE TABLE tb_goods_detail (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  goods_id int(11) DEFAULT NULL COMMENT '物资ID',
  type int(11) DEFAULT NULL COMMENT '类型：出库2、入库1',
  company int(11) DEFAULT NULL COMMENT '出库、入库单位',
  content varchar(200) DEFAULT NULL COMMENT '说明',
  oper_count int(11) DEFAULT 0 COMMENT '出库、入库数量',
  oper_id  int(11) DEFAULT 0 COMMENT '经手人',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物资出库入库明细';

delete from sys_user;
commit;

INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', '系统管理员', '1', null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('5', 'test', '123456', '测试', null, 'test@sina.com', '15812345678', '1', '2015-04-20 17:17:28');

delete from sys_dict_detail;
delete from sys_dict;
commit;

INSERT INTO `sys_dict` VALUES ('1', '单位', 'company', null);
INSERT INTO `sys_dict` VALUES ('2', '经手人', 'handlerPerson', null);
INSERT INTO `sys_dict` VALUES ('3', '物资种类', 'objectType', null);

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