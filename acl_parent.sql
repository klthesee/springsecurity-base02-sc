CREATE TABLE acl_user(
	id VARCHAR(32) NOT NULL comment '会员id',
	username VARCHAR(32) NOT NULL comment '微信openid',
	password VARCHAR(32) NOT NULL comment '密码',
	nick_name VARCHAR(64) NOT NULL comment '昵称',
	salt VARCHAR(256) default '' comment '用户头像',
	token VARCHAR(64) NOT NULL comment '用户签名',
	is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

CREATE TABLE acl_user_role(
	id VARCHAR(32) not null,
	role_id varchar(32) NOT NULL,
	user_id VARCHAR(32) NOT NULL,
		is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=innodb default charset=utf8 comment='用户角色表';

CREATE TABLE role(
	id VARCHAR(32) NOT NULL ,
	role_name VARCHAR(20) NOT NULL,
	role_code VARCHAR(32) NOT NULL,
	remark VARCHAR(64) default null,
		is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=INNODB default charset=utf8 comment='用户角色表';

CREATE TABLE acl_role_permission(
	id VARCHAR(32) not NULL,
	role_id VARCHAR(32) NOT NULL,
	permission_id VARCHAR(32) NOT NULL,
		is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=INNODB default charset=utf8 COMMENT='角色权限';


CREATE TABLE acl_permission(
	id VARCHAR(32) not NULL comment '编号',
	pid VARCHAR(32) NOT NULL COMMENT '所属上级',
	name VARCHAR(20) NOT NULL COMMENT '名称',
	type TINYINT	NOT NULL COMMENT '类型(1:菜单,2:按钮)',
	permission_value VARCHAR(30) NOT NULL comment '权限值',
	path VARCHAR(200) not NULL COMMENT '访问路径',
	component VARCHAR(200) NOT NULL COMMENT '组件路径',
	icon VARCHAR(500) DEFAULT NULL COMMENT '图标',
	status TINYINT(1) default '1' COMMENT '状态',
			is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=INNODB default charset=utf8 comment='权限';

CREATE TABLE acl_role(
	id VARCHAR(32) not NULL comment 'ID',
	role_name VARCHAR(32) NOT NULL COMMENT '角色名',
	role_code VARCHAR(20) NOT NULL COMMENT '角色代码',
	remark VARCHAR(50) default NULL COMMENT '备注',
			is_deleted TINYINT(1) default '0' comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间',
	PRIMARY KEY(id)
)ENGINE=INNODB default charset=utf8 comment='角色表';