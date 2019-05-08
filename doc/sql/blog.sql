-- 文章
CREATE TABLE `article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '标题',
  `original` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否原创',
  `type_id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '类型',
  `image` varchar(200) DEFAULT NULL COMMENT '文章封面图片',
  `content` longtext COMMENT '文章内容',
  `state` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '1:发布,2:草稿,0:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 标签
CREATE TABLE `tag` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '标签名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

CREATE TABLE `article_tag` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int(11) unsigned NOT NULL COMMENT '文章id',
  `tag_id` int(11) unsigned NOT NULL COMMENT '标签id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章-标签表';

-- 类型
CREATE TABLE `type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '文章类型名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='类型表';

INSERT INTO `type` (`id`, `name`)
VALUES
	(1, '前端技术'),
	(2, '后端技术'),
	(3, '其他文章');

-- 角色
CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL DEFAULT 'guest' COMMENT '角色',
  `rolename` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `permission` varchar(100) NOT NULL DEFAULT '' COMMENT '权限',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:正常,2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `role_id` int(11) unsigned NOT NULL COMMENT 'roleId',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `qq` varchar(20) NOT NULL DEFAULT '' COMMENT 'QQ',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` enum('male','female') NOT NULL DEFAULT 'male' COMMENT '性别',
  `picture` varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
  `reg_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '注册IP',
  `last_login_ip` varchar(30) NOT NULL DEFAULT '' COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:正常,2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


