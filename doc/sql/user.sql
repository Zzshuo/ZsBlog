CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `qq` varchar(20) NOT NULL DEFAULT '' COMMENT 'QQ',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` enum('male','female')  NOT NULL  DEFAULT 'male' COMMENT '性别',
  `picture` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `reg_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '注册IP',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:正常,2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL DEFAULT '' COMMENT '标题',
  `content` longtext COMMENT '文章内容',
  `original` tinyint(4)  NOT NULL DEFAULT '1' COMMENT '是否原创',
  `type_id` tinyint(4)  NOT NULL DEFAULT '1' COMMENT '类型',
  `code` tinyint(4)  NOT NULL  DEFAULT '1' COMMENT '状态',
  `description` varchar(300) DEFAULT NULL COMMENT '文章简介，最多200字',
  `keywords` varchar(200) DEFAULT NULL COMMENT '文章关键字，优化搜索',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:正常,2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


