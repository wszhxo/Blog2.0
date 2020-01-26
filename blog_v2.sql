/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : blog_v2

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-17 20:25:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `istop` bit(1) DEFAULT NULL,
  `top_time` datetime DEFAULT NULL,
  `lunbo` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('19', '', '![](/upload/2019/11/2/cat.jpeg)\r\n', '2019-11-02 16:04:29', '我玩1', 'http://localhost:8087/upload/2019/11/2/cat.jpeg', '', '', 'SpringBoot配置markdown上传图片返回url', '2019-11-16 15:58:05', '0', '2', '', null, '');
INSERT INTO `t_blog` VALUES ('21', '', '32\r\n', '2019-11-02 19:39:58', '搜索', 'http://localhost:8087/upload/2019/11/2/zhifubao.png', '', '', '23', '2019-11-16 13:08:09', '0', '2', '', null, '');
INSERT INTO `t_blog` VALUES ('22', '', '# 444 嘉禾2', '2019-11-16 14:13:07', '44再加个4', '44', '', '', '444', '2019-11-17 14:36:54', '0', '2', '', null, '');

-- ----------------------------
-- Table structure for `t_blog_tags`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`),
  CONSTRAINT `t_blog_tags_ibfk_1` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `t_blog_tags_ibfk_2` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('21', '2');
INSERT INTO `t_blog_tags` VALUES ('21', '3');
INSERT INTO `t_blog_tags` VALUES ('19', '2');
INSERT INTO `t_blog_tags` VALUES ('19', '3');
INSERT INTO `t_blog_tags` VALUES ('22', '3');


-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `t_comment_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
-- ----------------------------
-- Table structure for `t_link`
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isuse` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderno` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_link
-- ----------------------------
INSERT INTO `t_link` VALUES ('1', '1', '杨青博客', '2', '2019-07-26 14:01:25', 'https://www.yangqq.com/');
INSERT INTO `t_link` VALUES ('2', '1', '我Github搭建的博客', '1', '2019-07-18 14:03:00', 'https://cuberzhx.cn/');
INSERT INTO `t_link` VALUES ('3', '1', 'SpringBoot博客', '3', '2019-10-18 15:31:15', 'http://www.coderzhx.cn/');

-- ----------------------------
-- Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', '数据结构');
INSERT INTO `t_tag` VALUES ('2', '链表');
INSERT INTO `t_tag` VALUES ('3', '二叉树');


-- ----------------------------
-- Table structure for `t_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', 'java基础');
INSERT INTO `t_type` VALUES ('2', '前端');
INSERT INTO `t_type` VALUES ('3', '测试分类');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '/images/zhang.jpeg', null, 'cuber.zhx@qq.com', '张', '025caacda95a8a6874c7f78ca2c78b4a', null, null, 'admin', '# 博客2.0版本\r\n\r\n##### 因为1.0版本[https://coderzhx.cn](https://coderzhx.cn) 过于冗余,冗余点如下\r\n\r\n##### 1.由于**访问的人并不多(丢脸)**,资源分享、导航菜单管理和网站配置信息管理模块完全多此一举!\r\n\r\n##### 2.考虑到我的导航栏菜单和网站基本信息配置似乎几年都不会变.我完全把他在静态页面中写死就好了!\r\n\r\n##### 3.前后台采用一套ui界面足够了,因为这个项目还很小,用Thymeleaf模版即可!1.0版本后台用了layAdmin用于前后端分离用到了**ajax异步获取数据,这点算是优点**,不过对于这个项目来说我没有把他用到淋漓尽致,因为**我不会vue框架**,等我学了,我会把这个版本升级为前后端分离项目3.0版本.用回Layui,因为这个模板框架的界面数据交互都很不错.\r\n\r\n##### 4.\r\n\r\n> 隐藏的bug\r\n>\r\n> 1 全文搜索结果的分页功能可能失控\r\n\r\n\r\n\r\n');

-- ----------------------------
-- Table structure for `t_visitcount`
-- ----------------------------
DROP TABLE IF EXISTS `t_visitcount`;
CREATE TABLE `t_visitcount` (
  `ip` varchar(255) NOT NULL,
  `ipcount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_visitcount
-- ----------------------------
INSERT INTO `t_visitcount` VALUES ('172.23.249.145', '1');

-- ----------------------------
-- View structure for `comment_by_blog_id`
-- ----------------------------
DROP VIEW IF EXISTS `comment_by_blog_id`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `comment_by_blog_id` AS select `a`.`id` AS `id`,`a`.`avatar` AS `avatar`,`a`.`nickname` AS `nickname`,`a`.`content` AS `content`,`a`.`parent_comment_id` AS `parent_comment_id` from (`t_comment` `a` join `t_comment` `b` on((`a`.`parent_comment_id` = `b`.`id`))) where (`a`.`blog_id` = 19) ;
