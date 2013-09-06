/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50611
 Source Host           : 127.0.0.1
 Source Database       : tase_db

 Target Server Type    : MySQL
 Target Server Version : 50611
 File Encoding         : utf-8

 Date: 05/27/2013 10:18:03 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tase_app_android_official_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_advertisement`;
CREATE TABLE `tase_app_android_official_advertisement` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `advertiser_name` varchar(32) DEFAULT '' COMMENT '广告商名称',
  `advertiser_website` varchar(256) DEFAULT '' COMMENT '广告商链接',
  `advertiser_email` varchar(64) DEFAULT '' COMMENT '广告商邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_advertisement` (`app_id`,`advertiser_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_antivirus`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_antivirus`;
CREATE TABLE `tase_app_android_official_antivirus` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `antivirus_vendor` varchar(32) DEFAULT NULL COMMENT '反病毒厂商名',
  `antivirus_result` varchar(512) DEFAULT NULL COMMENT '反病毒检测结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_antivirus` (`app_id`,`antivirus_vendor`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_developer`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_developer`;
CREATE TABLE `tase_app_android_official_developer` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `developer_name` varchar(128) DEFAULT '' COMMENT '开发者名称',
  `developer_website` varchar(512) DEFAULT '' COMMENT '开发者网站',
  `developer_email` varchar(64) DEFAULT '' COMMENT '开发者邮箱',
  `privacy_policy` text COMMENT '隐私保护政策',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_developer` (`developer_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_external`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_external`;
CREATE TABLE `tase_app_android_official_external` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `external_store_name` varchar(32) DEFAULT '' COMMENT '外链商店名称',
  `external_url` varchar(512) DEFAULT '' COMMENT '外部链接',
  `is_app_downloaded` int(4) DEFAULT '0' COMMENT '应用是否已下载',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_external` (`app_id`,`external_store_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_internal`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_internal`;
CREATE TABLE `tase_app_android_official_internal` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `inner_id` varchar(128) DEFAULT '' COMMENT '商店内部ID',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_internal` (`inner_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_label`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_label`;
CREATE TABLE `tase_app_android_official_label` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `has_keyword` int(4) DEFAULT '0' COMMENT '是否包含关键字',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_label` (`has_keyword`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_misc`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_misc`;
CREATE TABLE `tase_app_android_official_misc` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `country` varchar(16) DEFAULT '' COMMENT '国家地区',
  `language` varchar(32) DEFAULT '' COMMENT '语言',
  `description` text COMMENT '应用描述',
  `size` varchar(16) DEFAULT '' COMMENT '应用大小',
  `currency_unit` char(4) DEFAULT '' COMMENT '货币单位',
  `price` float(16,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_permission`;
CREATE TABLE `tase_app_android_official_permission` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `permission_group` varchar(128) DEFAULT '' COMMENT '权限类别',
  `permission_desc` varchar(256) DEFAULT '' COMMENT '权限描述',
  `permission_desc_full` text COMMENT '权限详细内容',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_permission` (`permission_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_platform`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_platform`;
CREATE TABLE `tase_app_android_official_platform` (
  `app_id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `platform_name` varchar(32) DEFAULT '' COMMENT '平台名称',
  `os_type` varchar(32) DEFAULT '' COMMENT '操作系统类型',
  `os_version` varchar(32) DEFAULT '' COMMENT '操作系统版本',
  `device` varchar(128) DEFAULT '' COMMENT '设备名称',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_platform` (`os_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_resource`;
CREATE TABLE `tase_app_android_official_resource` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `url` varchar(512) DEFAULT '' COMMENT '应用信息页链接',
  `download_url` varchar(512) DEFAULT '' COMMENT '应用下载URL',
  `logo_url` varchar(512) DEFAULT '' COMMENT 'LOGO链接',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_snapshot`;
CREATE TABLE `tase_app_android_official_snapshot` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `snapshot_url` varchar(512) DEFAULT '' COMMENT '图片地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_snapshot` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_statistic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_statistic`;
CREATE TABLE `tase_app_android_official_statistic` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `download_floor` bigint(10) DEFAULT '0' COMMENT '下载次数下限（默认存储精确下载次数）',
  `download_ceiling` bigint(10) DEFAULT '0' COMMENT '下载次数上限',
  `rating` float(8,2) DEFAULT '0.00' COMMENT '评分',
  `rating_count` int(10) DEFAULT '0' COMMENT '评分次数',
  `review` varchar(512) DEFAULT '' COMMENT '评论',
  `content_rating` varchar(64) DEFAULT '' COMMENT '内容分级',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_statistic` (`download_floor`,`download_ceiling`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_status`;
CREATE TABLE `tase_app_android_official_status` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `is_deprecated` tinyint(1) DEFAULT '0' COMMENT '是否已废弃',
  `is_file_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载应用',
  `is_image_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载图片',
  `is_logo_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载Logo',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_status` (`is_file_downloaded`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_version`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_version`;
CREATE TABLE `tase_app_android_official_version` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `version` varchar(32) DEFAULT '' COMMENT '版本号',
  `updated_time` bigint(16) DEFAULT '0' COMMENT '更新日期',
  `collected_time` bigint(16) DEFAULT '0' COMMENT '收集日期',
  `update_history` text COMMENT '更新历史信息',
  `major_version` bigint(16) DEFAULT '0' COMMENT '主版本号',
  `minor_version` bigint(16) DEFAULT '0' COMMENT '次版本号',
  `revision_version` bigint(16) DEFAULT '0' COMMENT '修正版本号',
  `build_version` bigint(16) DEFAULT '0' COMMENT '编译版本号',
  `extra_version` bigint(16) DEFAULT '0' COMMENT '其他版本信息',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_version` (`version`,`updated_time`) USING BTREE,
  KEY `idx_app_parsed_version` (`major_version`,`minor_version`,`revision_version`,`build_version`,`extra_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_official_visualization`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_official_visualization`;
CREATE TABLE `tase_app_android_official_visualization` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `store_display_name` varchar(32) DEFAULT '' COMMENT '商店名称',
  `app_name` varchar(64) DEFAULT '' COMMENT '应用名称',
  `version` varchar(32) DEFAULT '' COMMENT '应用版本',
  `size` varchar(32) DEFAULT '' COMMENT '应用大小',
  `url` varchar(512) DEFAULT '' COMMENT '应用页面链接',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_visualization` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_advertisement`;
CREATE TABLE `tase_app_android_unofficial_advertisement` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `advertiser_name` varchar(32) DEFAULT '' COMMENT '广告商名称',
  `advertiser_website` varchar(256) DEFAULT '' COMMENT '广告商链接',
  `advertiser_email` varchar(64) DEFAULT '' COMMENT '广告商邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_advertisement` (`app_id`,`advertiser_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_antivirus`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_antivirus`;
CREATE TABLE `tase_app_android_unofficial_antivirus` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `antivirus_vendor` varchar(32) DEFAULT NULL COMMENT '反病毒厂商名',
  `antivirus_result` varchar(512) DEFAULT NULL COMMENT '反病毒检测结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_antivirus` (`app_id`,`antivirus_vendor`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_developer`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_developer`;
CREATE TABLE `tase_app_android_unofficial_developer` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `developer_name` varchar(128) DEFAULT '' COMMENT '开发者名称',
  `developer_website` varchar(512) DEFAULT '' COMMENT '开发者网站',
  `developer_email` varchar(64) DEFAULT '' COMMENT '开发者邮箱',
  `privacy_policy` text COMMENT '隐私保护政策',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_developer` (`developer_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_external`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_external`;
CREATE TABLE `tase_app_android_unofficial_external` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `external_store_name` varchar(32) DEFAULT '' COMMENT '外链商店名称',
  `external_url` varchar(512) DEFAULT '' COMMENT '外部链接',
  `is_app_downloaded` int(4) DEFAULT '0' COMMENT '应用是否已下载',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_external` (`app_id`,`external_store_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_internal`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_internal`;
CREATE TABLE `tase_app_android_unofficial_internal` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `inner_id` varchar(128) DEFAULT '' COMMENT '商店内部ID',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_internal` (`inner_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_label`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_label`;
CREATE TABLE `tase_app_android_unofficial_label` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `has_keyword` int(4) DEFAULT '0' COMMENT '是否包含关键字',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_label` (`has_keyword`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_misc`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_misc`;
CREATE TABLE `tase_app_android_unofficial_misc` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `country` varchar(16) DEFAULT '' COMMENT '国家地区',
  `language` varchar(32) DEFAULT '' COMMENT '语言',
  `description` text COMMENT '应用描述',
  `size` varchar(16) DEFAULT '' COMMENT '应用大小',
  `currency_unit` char(4) DEFAULT '' COMMENT '货币单位',
  `price` float(16,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_permission`;
CREATE TABLE `tase_app_android_unofficial_permission` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `permission_group` varchar(128) DEFAULT '' COMMENT '权限类别',
  `permission_desc` varchar(256) DEFAULT '' COMMENT '权限描述',
  `permission_desc_full` text COMMENT '权限详细内容',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_permission` (`permission_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_platform`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_platform`;
CREATE TABLE `tase_app_android_unofficial_platform` (
  `app_id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `platform_name` varchar(32) DEFAULT '' COMMENT '平台名称',
  `os_type` varchar(32) DEFAULT '' COMMENT '操作系统类型',
  `os_version` varchar(32) DEFAULT '' COMMENT '操作系统版本',
  `device` varchar(128) DEFAULT '' COMMENT '设备名称',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_platform` (`os_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_resource`;
CREATE TABLE `tase_app_android_unofficial_resource` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `url` varchar(512) DEFAULT '' COMMENT '应用信息页链接',
  `download_url` varchar(512) DEFAULT '' COMMENT '应用下载URL',
  `logo_url` varchar(512) DEFAULT '' COMMENT 'LOGO链接',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_snapshot`;
CREATE TABLE `tase_app_android_unofficial_snapshot` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `snapshot_url` varchar(512) DEFAULT '' COMMENT '图片地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_snapshot` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_statistic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_statistic`;
CREATE TABLE `tase_app_android_unofficial_statistic` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `download_floor` bigint(10) DEFAULT '0' COMMENT '下载次数下限（默认存储精确下载次数）',
  `download_ceiling` bigint(10) DEFAULT '0' COMMENT '下载次数上限',
  `rating` float(8,2) DEFAULT '0.00' COMMENT '评分',
  `rating_count` int(10) DEFAULT '0' COMMENT '评分次数',
  `review` varchar(512) DEFAULT '' COMMENT '评论',
  `content_rating` varchar(64) DEFAULT '' COMMENT '内容分级',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_statistic` (`download_floor`,`download_ceiling`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_status`;
CREATE TABLE `tase_app_android_unofficial_status` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `is_deprecated` tinyint(1) DEFAULT '0' COMMENT '是否已废弃',
  `is_file_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载应用',
  `is_image_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载图片',
  `is_logo_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载Logo',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_status` (`is_file_downloaded`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_version`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_version`;
CREATE TABLE `tase_app_android_unofficial_version` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `version` varchar(32) DEFAULT '' COMMENT '版本号',
  `updated_time` bigint(16) DEFAULT '0' COMMENT '更新日期',
  `collected_time` bigint(16) DEFAULT '0' COMMENT '收集日期',
  `update_history` text COMMENT '更新历史信息',
  `major_version` bigint(16) DEFAULT '0' COMMENT '主版本号',
  `minor_version` bigint(16) DEFAULT '0' COMMENT '次版本号',
  `revision_version` bigint(16) DEFAULT '0' COMMENT '修正版本号',
  `build_version` bigint(16) DEFAULT '0' COMMENT '编译版本号',
  `extra_version` bigint(16) DEFAULT '0' COMMENT '其他版本信息',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_version` (`version`,`updated_time`) USING BTREE,
  KEY `idx_app_parsed_version` (`major_version`,`minor_version`,`revision_version`,`build_version`,`extra_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_android_unofficial_visualization`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_android_unofficial_visualization`;
CREATE TABLE `tase_app_android_unofficial_visualization` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `store_display_name` varchar(32) DEFAULT '' COMMENT '商店名称',
  `app_name` varchar(64) DEFAULT '' COMMENT '应用名称',
  `version` varchar(32) DEFAULT '' COMMENT '应用版本',
  `size` varchar(32) DEFAULT '' COMMENT '应用大小',
  `url` varchar(512) DEFAULT '' COMMENT '应用页面链接',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_visualization` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_common_category`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_common_category`;
CREATE TABLE `tase_app_common_category` (
  `category_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '分类自增ID',
  `store_name` varchar(32) NOT NULL DEFAULT '' COMMENT '商店名称',
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '分类名称',
  `master_id` int(4) NOT NULL DEFAULT '0' COMMENT '主节点ID',
  `collected_count` bigint(8) DEFAULT '0' COMMENT '爬取计数',
  `downloaded_count` bigint(8) DEFAULT '0' COMMENT '下载计数',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `idx_app_category` (`category_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_common_checksum`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_common_checksum`;
CREATE TABLE `tase_app_common_checksum` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `app_checksum` char(32) DEFAULT '' COMMENT 'MD5(StoreName + URL + Version)',
  `loaded_time` bigint(16) DEFAULT '0' COMMENT '加载时间',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_checksum` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_common_store`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_common_store`;
CREATE TABLE `tase_app_common_store` (
  `store_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `store_name` varchar(32) DEFAULT '' COMMENT '商店名称',
  `store_display_name` varchar(32) DEFAULT '' COMMENT '商店显示名称',
  `store_url` varchar(256) DEFAULT '' COMMENT '商店URL',
  `store_type` int(4) DEFAULT '0' COMMENT '商店类型',
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `idx_app_store` (`store_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_advertisement`;
CREATE TABLE `tase_app_ios_official_advertisement` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `advertiser_name` varchar(32) DEFAULT '' COMMENT '广告商名称',
  `advertiser_website` varchar(256) DEFAULT '' COMMENT '广告商链接',
  `advertiser_email` varchar(64) DEFAULT '' COMMENT '广告商邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_advertisement` (`app_id`,`advertiser_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_antivirus`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_antivirus`;
CREATE TABLE `tase_app_ios_official_antivirus` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `antivirus_vendor` varchar(32) DEFAULT NULL COMMENT '反病毒厂商名',
  `antivirus_result` varchar(512) DEFAULT NULL COMMENT '反病毒检测结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_antivirus` (`app_id`,`antivirus_vendor`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_developer`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_developer`;
CREATE TABLE `tase_app_ios_official_developer` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `developer_name` varchar(128) DEFAULT '' COMMENT '开发者名称',
  `developer_website` varchar(512) DEFAULT '' COMMENT '开发者网站',
  `developer_email` varchar(64) DEFAULT '' COMMENT '开发者邮箱',
  `privacy_policy` text COMMENT '隐私保护政策',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_developer` (`developer_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_external`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_external`;
CREATE TABLE `tase_app_ios_official_external` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `external_store_name` varchar(32) DEFAULT '' COMMENT '外链商店名称',
  `external_url` varchar(512) DEFAULT '' COMMENT '外部链接',
  `is_app_downloaded` int(4) DEFAULT '0' COMMENT '应用是否已下载',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_external` (`app_id`,`external_store_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_internal`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_internal`;
CREATE TABLE `tase_app_ios_official_internal` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `inner_id` varchar(128) DEFAULT '' COMMENT '商店内部ID',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_internal` (`inner_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_label`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_label`;
CREATE TABLE `tase_app_ios_official_label` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `has_keyword` int(4) DEFAULT '0' COMMENT '是否包含关键字',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_label` (`has_keyword`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_misc`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_misc`;
CREATE TABLE `tase_app_ios_official_misc` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `country` varchar(16) DEFAULT '' COMMENT '国家地区',
  `language` varchar(32) DEFAULT '' COMMENT '语言',
  `description` text COMMENT '应用描述',
  `size` varchar(16) DEFAULT '' COMMENT '应用大小',
  `currency_unit` char(4) DEFAULT '' COMMENT '货币单位',
  `price` float(16,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_permission`;
CREATE TABLE `tase_app_ios_official_permission` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `permission_group` varchar(128) DEFAULT '' COMMENT '权限类别',
  `permission_desc` varchar(256) DEFAULT '' COMMENT '权限描述',
  `permission_desc_full` text COMMENT '权限详细内容',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_permission` (`permission_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_platform`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_platform`;
CREATE TABLE `tase_app_ios_official_platform` (
  `app_id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `platform_name` varchar(32) DEFAULT '' COMMENT '平台名称',
  `os_type` varchar(32) DEFAULT '' COMMENT '操作系统类型',
  `os_version` varchar(32) DEFAULT '' COMMENT '操作系统版本',
  `device` varchar(128) DEFAULT '' COMMENT '设备名称',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_platform` (`os_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_resource`;
CREATE TABLE `tase_app_ios_official_resource` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `url` varchar(512) DEFAULT '' COMMENT '应用信息页链接',
  `download_url` varchar(512) DEFAULT '' COMMENT '应用下载URL',
  `logo_url` varchar(512) DEFAULT '' COMMENT 'LOGO链接',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_snapshot`;
CREATE TABLE `tase_app_ios_official_snapshot` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `snapshot_url` varchar(512) DEFAULT '' COMMENT '图片地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_snapshot` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_statistic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_statistic`;
CREATE TABLE `tase_app_ios_official_statistic` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `download_floor` bigint(10) DEFAULT '0' COMMENT '下载次数下限（默认存储精确下载次数）',
  `download_ceiling` bigint(10) DEFAULT '0' COMMENT '下载次数上限',
  `rating` float(8,2) DEFAULT '0.00' COMMENT '评分',
  `rating_count` int(10) DEFAULT '0' COMMENT '评分次数',
  `review` varchar(512) DEFAULT '' COMMENT '评论',
  `content_rating` varchar(64) DEFAULT '' COMMENT '内容分级',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_statistic` (`download_floor`,`download_ceiling`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_status`;
CREATE TABLE `tase_app_ios_official_status` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `is_deprecated` tinyint(1) DEFAULT '0' COMMENT '是否已废弃',
  `is_file_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载应用',
  `is_image_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载图片',
  `is_logo_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载Logo',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_status` (`is_file_downloaded`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_version`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_version`;
CREATE TABLE `tase_app_ios_official_version` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `version` varchar(32) DEFAULT '' COMMENT '版本号',
  `updated_time` bigint(16) DEFAULT '0' COMMENT '更新日期',
  `collected_time` bigint(16) DEFAULT '0' COMMENT '收集日期',
  `update_history` text COMMENT '更新历史信息',
  `major_version` bigint(16) DEFAULT '0' COMMENT '主版本号',
  `minor_version` bigint(16) DEFAULT '0' COMMENT '次版本号',
  `revision_version` bigint(16) DEFAULT '0' COMMENT '修正版本号',
  `build_version` bigint(16) DEFAULT '0' COMMENT '编译版本号',
  `extra_version` bigint(16) DEFAULT '0' COMMENT '其他版本信息',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_version` (`version`,`updated_time`) USING BTREE,
  KEY `idx_app_parsed_version` (`major_version`,`minor_version`,`revision_version`,`build_version`,`extra_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_official_visualization`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_official_visualization`;
CREATE TABLE `tase_app_ios_official_visualization` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `store_display_name` varchar(32) DEFAULT '' COMMENT '商店名称',
  `app_name` varchar(64) DEFAULT '' COMMENT '应用名称',
  `version` varchar(32) DEFAULT '' COMMENT '应用版本',
  `size` varchar(32) DEFAULT '' COMMENT '应用大小',
  `url` varchar(512) DEFAULT '' COMMENT '应用页面链接',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_visualization` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_advertisement`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_advertisement`;
CREATE TABLE `tase_app_ios_unofficial_advertisement` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `advertiser_name` varchar(32) DEFAULT '' COMMENT '广告商名称',
  `advertiser_website` varchar(256) DEFAULT '' COMMENT '广告商链接',
  `advertiser_email` varchar(64) DEFAULT '' COMMENT '广告商邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_advertisement` (`app_id`,`advertiser_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_antivirus`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_antivirus`;
CREATE TABLE `tase_app_ios_unofficial_antivirus` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `antivirus_vendor` varchar(32) DEFAULT NULL COMMENT '反病毒厂商名',
  `antivirus_result` varchar(512) DEFAULT NULL COMMENT '反病毒检测结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_antivirus` (`app_id`,`antivirus_vendor`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_developer`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_developer`;
CREATE TABLE `tase_app_ios_unofficial_developer` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `developer_name` varchar(128) DEFAULT '' COMMENT '开发者名称',
  `developer_website` varchar(512) DEFAULT '' COMMENT '开发者网站',
  `developer_email` varchar(64) DEFAULT '' COMMENT '开发者邮箱',
  `privacy_policy` text COMMENT '隐私保护政策',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_developer` (`developer_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_external`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_external`;
CREATE TABLE `tase_app_ios_unofficial_external` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `external_store_name` varchar(32) DEFAULT '' COMMENT '外链商店名称',
  `external_url` varchar(512) DEFAULT '' COMMENT '外部链接',
  `is_app_downloaded` int(4) DEFAULT '0' COMMENT '应用是否已下载',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_external` (`app_id`,`external_store_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_internal`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_internal`;
CREATE TABLE `tase_app_ios_unofficial_internal` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `inner_id` varchar(128) DEFAULT '' COMMENT '商店内部ID',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_internal` (`inner_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_label`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_label`;
CREATE TABLE `tase_app_ios_unofficial_label` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `has_keyword` int(4) DEFAULT '0' COMMENT '是否包含关键字',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_label` (`has_keyword`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_misc`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_misc`;
CREATE TABLE `tase_app_ios_unofficial_misc` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `country` varchar(16) DEFAULT '' COMMENT '国家地区',
  `language` varchar(32) DEFAULT '' COMMENT '语言',
  `description` text COMMENT '应用描述',
  `size` varchar(16) DEFAULT '' COMMENT '应用大小',
  `currency_unit` char(4) DEFAULT '' COMMENT '货币单位',
  `price` float(16,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_permission`;
CREATE TABLE `tase_app_ios_unofficial_permission` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `permission_group` varchar(128) DEFAULT '' COMMENT '权限类别',
  `permission_desc` varchar(256) DEFAULT '' COMMENT '权限描述',
  `permission_desc_full` text COMMENT '权限详细内容',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_permission` (`permission_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_platform`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_platform`;
CREATE TABLE `tase_app_ios_unofficial_platform` (
  `app_id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `platform_name` varchar(32) DEFAULT '' COMMENT '平台名称',
  `os_type` varchar(32) DEFAULT '' COMMENT '操作系统类型',
  `os_version` varchar(32) DEFAULT '' COMMENT '操作系统版本',
  `device` varchar(128) DEFAULT '' COMMENT '设备名称',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_platform` (`os_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_resource`;
CREATE TABLE `tase_app_ios_unofficial_resource` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `url` varchar(512) DEFAULT '' COMMENT '应用信息页链接',
  `download_url` varchar(512) DEFAULT '' COMMENT '应用下载URL',
  `logo_url` varchar(512) DEFAULT '' COMMENT 'LOGO链接',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_snapshot`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_snapshot`;
CREATE TABLE `tase_app_ios_unofficial_snapshot` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `snapshot_url` varchar(512) DEFAULT '' COMMENT '图片地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_snapshot` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_statistic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_statistic`;
CREATE TABLE `tase_app_ios_unofficial_statistic` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `download_floor` bigint(10) DEFAULT '0' COMMENT '下载次数下限（默认存储精确下载次数）',
  `download_ceiling` bigint(10) DEFAULT '0' COMMENT '下载次数上限',
  `rating` float(8,2) DEFAULT '0.00' COMMENT '评分',
  `rating_count` int(10) DEFAULT '0' COMMENT '评分次数',
  `review` varchar(512) DEFAULT '' COMMENT '评论',
  `content_rating` varchar(64) DEFAULT '' COMMENT '内容分级',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_statistic` (`download_floor`,`download_ceiling`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_status`;
CREATE TABLE `tase_app_ios_unofficial_status` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `is_deprecated` tinyint(1) DEFAULT '0' COMMENT '是否已废弃',
  `is_file_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载应用',
  `is_image_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载图片',
  `is_logo_downloaded` tinyint(1) DEFAULT '0' COMMENT '是否已下载Logo',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_status` (`is_file_downloaded`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_version`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_version`;
CREATE TABLE `tase_app_ios_unofficial_version` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `version` varchar(32) DEFAULT '' COMMENT '版本号',
  `updated_time` bigint(16) DEFAULT '0' COMMENT '更新日期',
  `collected_time` bigint(16) DEFAULT '0' COMMENT '收集日期',
  `update_history` text COMMENT '更新历史信息',
  `major_version` bigint(16) DEFAULT '0' COMMENT '主版本号',
  `minor_version` bigint(16) DEFAULT '0' COMMENT '次版本号',
  `revision_version` bigint(16) DEFAULT '0' COMMENT '修正版本号',
  `build_version` bigint(16) DEFAULT '0' COMMENT '编译版本号',
  `extra_version` bigint(16) DEFAULT '0' COMMENT '其他版本信息',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_version` (`version`,`updated_time`) USING BTREE,
  KEY `idx_app_parsed_version` (`major_version`,`minor_version`,`revision_version`,`build_version`,`extra_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_app_ios_unofficial_visualization`
-- ----------------------------
DROP TABLE IF EXISTS `tase_app_ios_unofficial_visualization`;
CREATE TABLE `tase_app_ios_unofficial_visualization` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `store_display_name` varchar(32) DEFAULT '' COMMENT '商店名称',
  `app_name` varchar(64) DEFAULT '' COMMENT '应用名称',
  `version` varchar(32) DEFAULT '' COMMENT '应用版本',
  `size` varchar(32) DEFAULT '' COMMENT '应用大小',
  `url` varchar(512) DEFAULT '' COMMENT '应用页面链接',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_app_visualization` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_bundle_checksum`
-- ----------------------------
DROP TABLE IF EXISTS `tase_bundle_checksum`;
CREATE TABLE `tase_bundle_checksum` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `bundle_checksum` char(32) DEFAULT '' COMMENT '应用包校验和',
  `bundle_magic_number` char(16) DEFAULT '' COMMENT '应用包幻数',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_bundle_checksum` (`bundle_checksum`,`bundle_magic_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_bundle_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_bundle_resource`;
CREATE TABLE `tase_bundle_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `bundle_checksum` char(32) DEFAULT '' COMMENT '软件包校验和',
  `file_path` varchar(128) DEFAULT '' COMMENT '应用存储路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_bundle_resource` (`bundle_checksum`,`file_path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_bundle_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_bundle_status`;
CREATE TABLE `tase_bundle_status` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `bundle_checksum` char(32) DEFAULT '' COMMENT '软件包校验和',
  `static_status` int(4) DEFAULT '0' COMMENT '静态检测状态',
  `dynamic_status` int(4) DEFAULT '0' COMMENT '动态检测状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_bundle_status` (`bundle_checksum`,`static_status`,`dynamic_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_antivirus`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_antivirus`;
CREATE TABLE `tase_file_antivirus` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `antivirus_vendor` varchar(32) DEFAULT '' COMMENT '反病毒厂商名',
  `antivirus_url` varchar(256) DEFAULT '' COMMENT '反病毒厂商链接',
  `antivirus_email` varchar(64) DEFAULT '' COMMENT '反病毒厂商邮箱',
  `scanning_time` bigint(16) DEFAULT '0' COMMENT '扫描时间',
  `malicious_type` varchar(64) DEFAULT '' COMMENT '恶意类型',
  `antivirus_result` text COMMENT '详细检测结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_antivirus` (`file_checksum`,`antivirus_vendor`,`malicious_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest`;
CREATE TABLE `tase_file_apk_manifest` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `xmlns` varchar(64) DEFAULT '' COMMENT '命名空间',
  `package_name` varchar(128) DEFAULT '' COMMENT '开发包名称',
  `shared_user_id` varchar(32) DEFAULT '' COMMENT 'Linux用户ID',
  `shared_user_label` varchar(32) DEFAULT '' COMMENT '用户标签',
  `version_code` bigint(16) DEFAULT '0' COMMENT '版本号',
  `version_name` varchar(64) DEFAULT '' COMMENT '版本名称',
  `install_location` varchar(32) DEFAULT '' COMMENT '默认安装路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest` (`file_checksum`,`package_name`,`version_code`,`version_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_activity`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_activity`;
CREATE TABLE `tase_file_apk_manifest_activity` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `activity_name` varchar(128) DEFAULT '' COMMENT 'Activity实现类名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_activity` (`file_checksum`,`activity_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_intent_filter_action`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_intent_filter_action`;
CREATE TABLE `tase_file_apk_manifest_intent_filter_action` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `action_name` varchar(128) DEFAULT '' COMMENT 'Action名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_intent_filter_action` (`file_checksum`,`action_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_permission`;
CREATE TABLE `tase_file_apk_manifest_permission` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `permission_name` varchar(128) DEFAULT '' COMMENT '权限名称',
  `protection_level` varchar(32) DEFAULT '' COMMENT '权限允许区域',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_permission` (`file_checksum`,`permission_name`,`protection_level`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_sdk`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_sdk`;
CREATE TABLE `tase_file_apk_manifest_sdk` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `min_sdk_version` int(4) DEFAULT '0' COMMENT '最小SDK版本',
  `target_sdk_version` int(4) DEFAULT '0' COMMENT '目标SDK版本',
  `max_sdk_version` int(4) DEFAULT '0' COMMENT '最大SDK版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_sdk` (`file_checksum`,`min_sdk_version`,`target_sdk_version`,`max_sdk_version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_uses_feature`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_uses_feature`;
CREATE TABLE `tase_file_apk_manifest_uses_feature` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `feature_name` varchar(128) DEFAULT '' COMMENT '特性名称',
  `required` int(4) DEFAULT '0' COMMENT '是否必须',
  `gl_es_version` varchar(32) DEFAULT '' COMMENT '图形界面版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_uses_feature` (`file_checksum`,`feature_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_uses_library`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_uses_library`;
CREATE TABLE `tase_file_apk_manifest_uses_library` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `library_name` varchar(128) DEFAULT '' COMMENT '库名称',
  `required` int(4) DEFAULT '0' COMMENT '是否必须',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_uses_library` (`file_checksum`,`library_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_manifest_uses_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_manifest_uses_permission`;
CREATE TABLE `tase_file_apk_manifest_uses_permission` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `uses_permission_name` varchar(128) DEFAULT '' COMMENT '请求权限名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_manifest_uses_permission` (`file_checksum`,`uses_permission_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_apk_signature`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_apk_signature`;
CREATE TABLE `tase_file_apk_signature` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `certificate_type` varchar(32) DEFAULT '' COMMENT '证书类型',
  `public_key_modulus` text COMMENT '公钥大整数',
  `public_key_exponent` bigint(20) DEFAULT '0' COMMENT '公钥大素数',
  `algorithm` varchar(32) DEFAULT '' COMMENT '加密算法',
  `certificate_hashcode` bigint(20) DEFAULT '0' COMMENT '证书Hash码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_apk_signature` (`file_checksum`,`public_key_exponent`,`certificate_hashcode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_checksum`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_checksum`;
CREATE TABLE `tase_file_checksum` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_file_checksum` (`file_checksum`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_label`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_label`;
CREATE TABLE `tase_file_label` (
  `id` bigint(12) NOT NULL COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `has_virus` int(4) DEFAULT '0' COMMENT '是否存在病毒',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_label` (`file_checksum`,`has_virus`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_logo_checksum`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_logo_checksum`;
CREATE TABLE `tase_file_logo_checksum` (
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `logo_checksum` char(32) DEFAULT '' COMMENT 'Logo校验和',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `idx_file_logo_checksum` (`logo_checksum`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_logo_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_logo_resource`;
CREATE TABLE `tase_file_logo_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `logo_checksum` char(32) DEFAULT '' COMMENT 'Logo校验和',
  `logo_path` varchar(128) DEFAULT '' COMMENT 'Logo存储路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_logo_resource` (`logo_checksum`,`logo_path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_metadata`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_metadata`;
CREATE TABLE `tase_file_metadata` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `bundle_checksum` char(32) DEFAULT '' COMMENT '软件包校验和',
  `file_name` varchar(512) DEFAULT '' COMMENT '文件名称',
  `extension` varchar(8) DEFAULT '' COMMENT '文件扩展名',
  `length` bigint(16) DEFAULT '0' COMMENT '文件大小',
  `created_time` bigint(16) DEFAULT '0' COMMENT '文件创建时间',
  `modified_time` bigint(16) DEFAULT '0' COMMENT '文件修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_metadata` (`file_checksum`,`bundle_checksum`,`extension`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_resource`;
CREATE TABLE `tase_file_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `file_path` varchar(128) DEFAULT '' COMMENT '应用存储路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_resource` (`file_checksum`,`file_path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_snapshot_checksum`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_snapshot_checksum`;
CREATE TABLE `tase_file_snapshot_checksum` (
  `id` bigint(12) NOT NULL,
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `snapshot_checksum` char(32) DEFAULT '' COMMENT '截图校验和',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_snapshot_checksum` (`app_id`,`snapshot_checksum`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_snapshot_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_snapshot_resource`;
CREATE TABLE `tase_file_snapshot_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `snapshot_checksum` char(32) DEFAULT '' COMMENT '截图校验和',
  `snapshot_path` varchar(128) DEFAULT '' COMMENT '截图存储路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_snapshot_resource` (`snapshot_checksum`,`snapshot_path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_file_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_file_status`;
CREATE TABLE `tase_file_status` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `file_checksum` char(32) DEFAULT '' COMMENT '文件校验和',
  `static_status` int(4) DEFAULT '0' COMMENT '静态检测状态',
  `dynamic_status` int(4) DEFAULT '0' COMMENT '动态检测状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_file_status` (`file_checksum`,`static_status`,`dynamic_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_basic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_basic`;
CREATE TABLE `tase_job_basic` (
  `job_id` bigint(16) NOT NULL COMMENT '作业ID',
  `job_priority` enum('LOWER','LOW','MEDIUM','HIGH','HIGHER') DEFAULT 'MEDIUM' COMMENT '作业优先级',
  `job_distribution_mode` enum('PARALLEL','SERIAL') DEFAULT 'SERIAL' COMMENT '作业分发模式',
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `idx_job_basic` (`job_priority`,`job_distribution_mode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_operation`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_operation`;
CREATE TABLE `tase_job_operation` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `job_id` bigint(16) NOT NULL DEFAULT '0' COMMENT '作业ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT 'INITIALIZE' COMMENT '作业阶段',
  `job_execution_mode` enum('EXCLUSIVE','CONCURRENT') DEFAULT 'CONCURRENT' COMMENT '作业执行模式',
  `job_return_mode` enum('ACTIVE','PASSIVE') DEFAULT 'PASSIVE' COMMENT '作业返回模式',
  `timeout` bigint(16) DEFAULT '0' COMMENT '作业时限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_operation` (`job_id`,`job_phase`,`job_execution_mode`,`job_return_mode`,`timeout`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_parameter`;
CREATE TABLE `tase_job_parameter` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `job_id` bigint(16) DEFAULT '0' COMMENT '作业ID',
  `parameter` text COMMENT '作业参数',
  `additional` text COMMENT '作业附加参数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_parameter` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_possession`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_possession`;
CREATE TABLE `tase_job_possession` (
  `job_id` bigint(16) NOT NULL COMMENT '作业ID',
  `submitter_id` int(4) DEFAULT '0' COMMENT '作业提交者ID',
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `idx_job_possession` (`submitter_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_resource`;
CREATE TABLE `tase_job_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `job_id` bigint(16) DEFAULT '0' COMMENT '作业ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT NULL COMMENT '作业阶段',
  `program_id` bigint(10) DEFAULT '0' COMMENT '程序ID',
  `executor_id` char(21) DEFAULT '' COMMENT '执行者ID',
  `cluster_type` enum('GENERAL','DEDICATED') DEFAULT 'GENERAL' COMMENT '集群类型',
  `node_type` enum('IOS_ACCESSIBLE','ANDROID_ACCESSIBLE','ANTIVIRUS','INTERNET_ACCESSIBLE','BASIC') DEFAULT 'BASIC' COMMENT '节点类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_resource` (`job_id`,`job_phase`,`program_id`,`executor_id`,`cluster_type`,`node_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_job_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_status`;
CREATE TABLE `tase_job_status` (
  `job_id` bigint(16) NOT NULL COMMENT '作业ID',
  `impatience_time` bigint(16) DEFAULT '0' COMMENT '忍耐时间',
  `committed_time` bigint(16) DEFAULT '0' COMMENT '提交时间',
  `job_status` int(4) DEFAULT '0' COMMENT '作业状态',
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `idx_job_status` (`job_status`,`impatience_time`,`committed_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `tase_job_type`
-- ----------------------------
DROP TABLE IF EXISTS `tase_job_type`;
CREATE TABLE `tase_job_type` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `job_type` bigint(8) DEFAULT '0' COMMENT '作业类型',
  `master_id` int(2) DEFAULT '0' COMMENT '主节点编号ID',
  `job_count` bigint(12) DEFAULT '0' COMMENT '作业计数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_type` (`job_type`,`master_id`,`job_count`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_aggregration`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_aggregration`;
CREATE TABLE `tase_node_aggregration` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `config_updated` bigint(16) NOT NULL DEFAULT '0' COMMENT '配置更新时间',
  `payload_updated` bigint(16) NOT NULL DEFAULT '0' COMMENT '负载更新时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_hardware`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_hardware`;
CREATE TABLE `tase_node_hardware` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `cpu_vendor` varchar(32) DEFAULT NULL COMMENT 'CPU制造商',
  `cpu_model` varchar(64) DEFAULT NULL COMMENT 'CPU型号',
  `cpu_cores` int(4) DEFAULT NULL COMMENT 'CPU核心数',
  `cpu_mhz` bigint(8) DEFAULT NULL COMMENT 'CPU主频',
  `mem_size` bigint(8) DEFAULT NULL COMMENT '内存大小',
  `fs_name` varchar(64) DEFAULT NULL COMMENT '文件系统名称',
  `fs_type` varchar(16) DEFAULT NULL COMMENT '文件系统类型',
  `fs_format` varchar(8) DEFAULT NULL COMMENT '文件系统格式',
  `fs_size` bigint(8) DEFAULT NULL COMMENT '文件系统大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_master`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_master`;
CREATE TABLE `tase_node_master` (
  `master_id` int(2) NOT NULL AUTO_INCREMENT COMMENT '主节点编号ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  PRIMARY KEY (`master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_network`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_network`;
CREATE TABLE `tase_node_network` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `domain_name` varchar(32) DEFAULT NULL COMMENT '域名',
  `mac_address` char(17) DEFAULT NULL COMMENT '网卡硬件地址',
  `network_connectivity` int(4) NOT NULL COMMENT '网络连通性',
  `ip_address` char(15) NOT NULL COMMENT 'IP地址',
  `rpc_port` int(5) NOT NULL COMMENT 'RPC端口号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_payload`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_payload`;
CREATE TABLE `tase_node_payload` (
  `id` bigint(12) NOT NULL COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `cpu_used_perc` float(8,2) DEFAULT NULL COMMENT 'CPU占用率',
  `mem_used` bigint(8) DEFAULT NULL COMMENT '内存使用量',
  `jvm_alloc_mem` bigint(8) DEFAULT NULL COMMENT 'JVM分配内存大小',
  `jvm_free_mem` bigint(8) DEFAULT NULL COMMENT 'JVM空闲内存大小',
  `fs_avail` int(8) DEFAULT NULL COMMENT '文件系统空余大小',
  `fs_used` int(8) DEFAULT NULL COMMENT '文件系统使用量',
  `fs_used_perc` float(8,2) DEFAULT NULL COMMENT '文件系统使用率',
  `queue_num` int(4) DEFAULT NULL COMMENT '任务队列大小',
  `grade` int(4) DEFAULT NULL COMMENT '资源评分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_process`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_process`;
CREATE TABLE `tase_node_process` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `pid` int(8) DEFAULT NULL COMMENT '进程ID',
  `user` varchar(32) DEFAULT NULL COMMENT '系统登录用户',
  `start_time` char(5) DEFAULT NULL COMMENT '开始时间',
  `proc_name` varchar(64) DEFAULT NULL COMMENT '进程名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_node_software`
-- ----------------------------
DROP TABLE IF EXISTS `tase_node_software`;
CREATE TABLE `tase_node_software` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `node_id` char(21) NOT NULL COMMENT '节点ID',
  `os_arch` varchar(16) DEFAULT NULL COMMENT 'OS架构',
  `os_name` varchar(32) DEFAULT NULL COMMENT 'OS名称',
  `os_desc` varchar(32) DEFAULT NULL COMMENT 'OS描述',
  `os_version` varchar(32) DEFAULT NULL COMMENT 'OS版本',
  `jvm_name` varchar(64) DEFAULT NULL COMMENT 'JVM名称',
  `jvm_version` varchar(16) DEFAULT NULL COMMENT 'JVM版本',
  `jvm_max_mem` bigint(8) DEFAULT NULL COMMENT 'JVM最大内存数',
  `node_type` int(4) DEFAULT NULL COMMENT '节点类型',
  `queue_limit` int(4) DEFAULT NULL COMMENT '任务队列大小限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_program_basic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_program_basic`;
CREATE TABLE `tase_program_basic` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `program_id` bigint(10) NOT NULL COMMENT '程序ID',
  `program_name` varchar(32) NOT NULL DEFAULT '' COMMENT '程序名称',
  `node_type` int(4) NOT NULL DEFAULT '0' COMMENT '节点类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_program_possession`
-- ----------------------------
DROP TABLE IF EXISTS `tase_program_possession`;
CREATE TABLE `tase_program_possession` (
  `program_id` bigint(10) NOT NULL COMMENT '程序ID',
  `committer_id` int(4) NOT NULL COMMENT '提交者ID',
  `register_time` bigint(16) NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_program_storage`
-- ----------------------------
DROP TABLE IF EXISTS `tase_program_storage`;
CREATE TABLE `tase_program_storage` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `program_id` bigint(8) NOT NULL COMMENT '程序ID',
  `script_name` varchar(128) DEFAULT '' COMMENT '脚本名称',
  `script_path` varchar(512) DEFAULT '' COMMENT '脚本文件路径',
  `script_md5` char(32) DEFAULT NULL COMMENT '脚本文件MD5',
  `executable_name` varchar(128) DEFAULT NULL COMMENT '可执行文件名称',
  `executable_path` varchar(512) DEFAULT NULL COMMENT '可执行文件路径',
  `executable_md5` char(32) DEFAULT NULL COMMENT '可执行文件MD5',
  `env_variables` text COMMENT '环境变量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_program_type`
-- ----------------------------
DROP TABLE IF EXISTS `tase_program_type`;
CREATE TABLE `tase_program_type` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `program_type` bigint(8) NOT NULL COMMENT '程序类型ID',
  `program_count` int(2) NOT NULL DEFAULT '0' COMMENT '程序计数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_supervision_basic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_supervision_basic`;
CREATE TABLE `tase_supervision_basic` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `store_id` int(4) NOT NULL DEFAULT '0' COMMENT '商店ID',
  `url` varchar(512) DEFAULT '' COMMENT '应用信息页链接',
  `version` varchar(32) NOT NULL DEFAULT '' COMMENT '版本号',
  `updated_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `collected_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '爬取时间',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '信息页是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_supervision_history`
-- ----------------------------
DROP TABLE IF EXISTS `tase_supervision_history`;
CREATE TABLE `tase_supervision_history` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `prev_id` bigint(16) NOT NULL COMMENT '前向应用ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_supervision_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tase_supervision_resource`;
CREATE TABLE `tase_supervision_resource` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app_id` bigint(16) NOT NULL COMMENT '应用ID',
  `md5` char(32) NOT NULL DEFAULT '' COMMENT '应用MD5',
  `downloaded_time` bigint(16) NOT NULL DEFAULT '0' COMMENT '下载时间',
  `check_value` int(4) NOT NULL DEFAULT '0' COMMENT '检测标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_task_basic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_task_basic`;
CREATE TABLE `tase_task_basic` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(18) DEFAULT '0' COMMENT '任务ID',
  `task_priority` enum('LOWER','LOW','MEDIUM','HIGH','HIGHER') DEFAULT 'MEDIUM' COMMENT '任务优先级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_basic` (`task_id`,`task_priority`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_task_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `tase_task_parameter`;
CREATE TABLE `tase_task_parameter` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(18) DEFAULT '0' COMMENT '任务ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT 'INITIALIZE' COMMENT '作业阶段',
  `parameter` text COMMENT '任务参数',
  `additional` text COMMENT '任务附加参数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_parameter` (`task_id`,`job_phase`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_task_scheduling`
-- ----------------------------
DROP TABLE IF EXISTS `tase_task_scheduling`;
CREATE TABLE `tase_task_scheduling` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(18) DEFAULT '0' COMMENT '任务ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT 'INITIALIZE' COMMENT '作业阶段',
  `program_id` bigint(10) DEFAULT '0' COMMENT '程序ID',
  `executor_id` char(21) DEFAULT '' COMMENT '执行者ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_scheduling` (`task_id`,`job_phase`,`program_id`,`executor_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_task_status`
-- ----------------------------
DROP TABLE IF EXISTS `tase_task_status`;
CREATE TABLE `tase_task_status` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(18) DEFAULT '0' COMMENT '任务ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT 'INITIALIZE' COMMENT '作业阶段',
  `task_status` enum('FINISHED','FAILURE','TIMEOUT','KILLED','INTERRUPTED','RUNNING','ISSUED','ISSUE_FAILED','SCHEDULED','COMMITTED') DEFAULT 'COMMITTED' COMMENT '任务状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_status` (`task_id`,`job_phase`,`task_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_task_timekeeping`
-- ----------------------------
DROP TABLE IF EXISTS `tase_task_timekeeping`;
CREATE TABLE `tase_task_timekeeping` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `task_id` bigint(18) DEFAULT '0' COMMENT '任务ID',
  `job_phase` enum('DEPLOY','PACKAGE','VERIFY','INTEGRATE','POST_DYNAMIC','DYNAMIC','PRE_DYNAMIC','EVALUATE','TEST','POST_STATIC','STATIC','PRE_STATIC','PREPROCESS','VALIDATE','GENERATE','PREPARE','INITIALIZE') DEFAULT 'STATIC' COMMENT '任务阶段',
  `loaded_time` bigint(16) DEFAULT '0' COMMENT '任务加载时间',
  `issued_time` bigint(16) DEFAULT '0' COMMENT '任务下发时间',
  `started_time` bigint(16) DEFAULT '0' COMMENT '任务开始时间',
  `finished_time` bigint(16) DEFAULT '0' COMMENT '任务退出时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_task_timekeeping` (`task_id`,`job_phase`,`loaded_time`,`issued_time`,`started_time`,`finished_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tase_user_basic`
-- ----------------------------
DROP TABLE IF EXISTS `tase_user_basic`;
CREATE TABLE `tase_user_basic` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '用户自增ID',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `auth_token` char(32) NOT NULL DEFAULT '' COMMENT '鉴权Token',
  `app_count` bigint(8) NOT NULL DEFAULT '0' COMMENT '应用计数',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
