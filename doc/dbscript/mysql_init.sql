/*
 Navicat Premium Data Transfer

 Source Server         : 121.42.143.130
 Source Server Type    : MySQL
 Source Server Version : 100117
 Source Host           : 121.42.143.130:3306
 Source Schema         : etl

 Target Server Type    : MySQL
 Target Server Version : 100117
 File Encoding         : 65001

 Date: 13/04/2019 21:57:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dispatch_argument_define
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_argument_define`;
CREATE TABLE `dispatch_argument_define` (
  `arg_id` varchar(66) NOT NULL,
  `arg_type` char(1) DEFAULT NULL,
  `arg_value` varchar(100) DEFAULT NULL,
  `code_number` varchar(30) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `modify_user` varchar(30) DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  `arg_desc` varchar(100) DEFAULT NULL,
  `bind_as_of_date` char(1) DEFAULT '0',
  PRIMARY KEY (`arg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_argument_define
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_argument_define` VALUES ('YmlnZGF0YR53aXNyYx50YXNrX2lk', '2', '', 'task_id', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata', '任务ID', NULL);
INSERT INTO `dispatch_argument_define` VALUES ('YmlnZGF0YR53aXNyYx5hc19vZl9kYXRl', '4', '', 'as_of_date', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata', '批次日期', '1');
INSERT INTO `dispatch_argument_define` VALUES ('YmlnZGF0YR53aXNyYx5leGVjdXRlX2xldmVs', '1', '1', 'execute_level', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata', '执行级别', NULL);
INSERT INTO `dispatch_argument_define` VALUES ('YmlnZGF0YR53aXNyYx5ncm91cF9pZA==', '3', '', 'group_id', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata', '任务组ID', NULL);
INSERT INTO `dispatch_argument_define` VALUES ('YmlnZGF0YR53aXNyYx5wcm9qZWN0X2lk', '4', '', 'project_id', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata', '项目ID', '0');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_argument_type_attr
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_argument_type_attr`;
CREATE TABLE `dispatch_argument_type_attr` (
  `arg_type` char(10) NOT NULL,
  `arg_type_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`arg_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_argument_type_attr
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_argument_type_attr` VALUES ('1', '固定参数');
INSERT INTO `dispatch_argument_type_attr` VALUES ('2', '任务参数');
INSERT INTO `dispatch_argument_type_attr` VALUES ('3', '任务组参数');
INSERT INTO `dispatch_argument_type_attr` VALUES ('4', '批次参数');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_argument_rel
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_argument_rel`;
CREATE TABLE `dispatch_batch_argument_rel` (
  `uuid` varchar(66) NOT NULL,
  `batch_id` varchar(66) DEFAULT NULL,
  `arg_id` varchar(66) DEFAULT NULL,
  `arg_value` varchar(100) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_dispatch_batch_argument_001_idx` (`batch_id`),
  CONSTRAINT `fk_dispatch_batch_argument_001` FOREIGN KEY (`batch_id`) REFERENCES `dispatch_batch_define` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_batch_define
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_define`;
CREATE TABLE `dispatch_batch_define` (
  `batch_id` varchar(66) NOT NULL,
  `code_number` varchar(30) DEFAULT NULL,
  `batch_desc` varchar(200) DEFAULT NULL,
  `batch_status` varchar(20) DEFAULT NULL,
  `as_of_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `ret_msg` varchar(30) DEFAULT NULL,
  `complete_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  `pagging_freq` int(11) DEFAULT NULL,
  `pagging_freq_mult` char(5) DEFAULT NULL,
  PRIMARY KEY (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_define
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_define` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'B00001', '测试批次', '2', '2019-03-31 00:00:00', '2019-03-13 23:36:09', 'success', '2019-03-31 00:00:00', '2019-03-13 23:36:11', 'bigdata', 1, 'D');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_domain_config
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_domain_config`;
CREATE TABLE `dispatch_batch_domain_config` (
  `uuid` varchar(99) NOT NULL,
  `config_id` varchar(30) DEFAULT NULL,
  `config_value` varchar(200) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_batch_group_history
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_group_history`;
CREATE TABLE `dispatch_batch_group_history` (
  `sid` varchar(66) DEFAULT NULL,
  `suite_key` varchar(66) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  KEY `fk_dispatch_batch_group01_idx` (`sid`),
  CONSTRAINT `fk_dispatch_batch_group01` FOREIGN KEY (`sid`) REFERENCES `dispatch_batch_history` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_group_history
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_group_history` VALUES ('7932f8281464444ab32d39b08da1001e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:40', '2019-03-05 00:06:41');
INSERT INTO `dispatch_batch_group_history` VALUES ('e8eb97a25bba47678cb8d7541c867999', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:44', '2019-03-05 00:06:45');
INSERT INTO `dispatch_batch_group_history` VALUES ('1b7d6b62699c40ebb9d66326dc9901bd', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:48', '2019-03-05 00:06:49');
INSERT INTO `dispatch_batch_group_history` VALUES ('329698d23e0c4c138cf38b28c5b3e8ce', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:52', '2019-03-05 00:06:53');
INSERT INTO `dispatch_batch_group_history` VALUES ('2898bd35e2d44d7eaebd706b62a9257f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:55', '2019-03-05 00:06:56');
INSERT INTO `dispatch_batch_group_history` VALUES ('c3e9d80186834757abb011396cb66593', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:06:59', '2019-03-05 00:07:00');
INSERT INTO `dispatch_batch_group_history` VALUES ('6ab390ffd2ea4963ac9d013cf98c8ed3', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:03', '2019-03-05 00:07:04');
INSERT INTO `dispatch_batch_group_history` VALUES ('7c42a86a5e384da38a5cb4278b53831b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:07', '2019-03-05 00:07:08');
INSERT INTO `dispatch_batch_group_history` VALUES ('23b10cbaaeff403b8c75a379a2736d74', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:11', '2019-03-05 00:07:12');
INSERT INTO `dispatch_batch_group_history` VALUES ('9d2d1c80d5a74c8b8b429af696949b45', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:15', '2019-03-05 00:07:16');
INSERT INTO `dispatch_batch_group_history` VALUES ('9139614ff56c4d1b835be3fbf78b5077', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:19', '2019-03-05 00:07:20');
INSERT INTO `dispatch_batch_group_history` VALUES ('4aa4999245d04766b6d41c4fde9365b3', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:23', '2019-03-05 00:07:24');
INSERT INTO `dispatch_batch_group_history` VALUES ('6d6503987c2d43938d9d3a062337bff3', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:27', '2019-03-05 00:07:28');
INSERT INTO `dispatch_batch_group_history` VALUES ('44382eb469674d31a09ea2c0af1b28b7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:30', '2019-03-05 00:07:32');
INSERT INTO `dispatch_batch_group_history` VALUES ('0983d3d5c53c497f823faa3601bf39e8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:34', '2019-03-05 00:07:35');
INSERT INTO `dispatch_batch_group_history` VALUES ('0b86a6345db2417ea45129cddad5b0ea', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:38', '2019-03-05 00:07:39');
INSERT INTO `dispatch_batch_group_history` VALUES ('2661b3797cf54133a3968b5bc37cb4ec', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:42', '2019-03-05 00:07:43');
INSERT INTO `dispatch_batch_group_history` VALUES ('ad49e91649af42bba08a8115b0e14ce7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:46', '2019-03-05 00:07:47');
INSERT INTO `dispatch_batch_group_history` VALUES ('0714615b8f764b8ba57c7ccb15a64677', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:50', '2019-03-05 00:07:51');
INSERT INTO `dispatch_batch_group_history` VALUES ('51992a41672e4991bbca0014d5ff4bb5', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:54', '2019-03-05 00:07:55');
INSERT INTO `dispatch_batch_group_history` VALUES ('0e941c1c42ea410bb90674c267cb1920', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:07:58', '2019-03-05 00:07:59');
INSERT INTO `dispatch_batch_group_history` VALUES ('99ca287e784a498abddc4c781266a8a5', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:02', '2019-03-05 00:08:03');
INSERT INTO `dispatch_batch_group_history` VALUES ('7f87be93248c4bec922b4bac4b0b0275', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:06', '2019-03-05 00:08:07');
INSERT INTO `dispatch_batch_group_history` VALUES ('1cf2f296fb2e43fb855cdb2ff46148e9', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:09', '2019-03-05 00:08:11');
INSERT INTO `dispatch_batch_group_history` VALUES ('b8861c6887f9459999c10bc16e25dd2f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:13', '2019-03-05 00:08:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('ff522df593464f4898f8165a9f4a1dd1', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:17', '2019-03-05 00:08:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('0c8207a5f5d5418eb7f87142609b9320', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:21', '2019-03-05 00:08:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('687562cd94b8421b89b8bec964e920fa', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:25', '2019-03-05 00:08:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('87d32990ccdf4c0bb9fe9a0db72cd009', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:29', '2019-03-05 00:08:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('4ba35c108cd841e98f6ea87f499bc2b6', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:33', '2019-03-05 00:08:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('b77df67ddf034bd4ae0a3fe0d648cda2', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 00:08:37', '2019-03-05 00:08:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('a2ce46a525524e8ca970b4758152e4c4', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:37', '2019-03-05 22:05:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('d2f9e7dc295049328bdf05120abb5776', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:41', '2019-03-05 22:05:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('f09b58d739e2498bbb03298bb4642f3b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:45', '2019-03-05 22:05:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('86eddb8107db47cfa74761ffafb827ea', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:49', '2019-03-05 22:05:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('d9d9c60bfd6f457fa20af77b35abddf7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:53', '2019-03-05 22:05:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('1e7a974ea4ab4821929f295eaf092997', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:57', '2019-03-05 22:05:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('4fff06389871474f989d3ee0490ee732', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:01', '2019-03-05 22:06:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('bb085aa07a4648c1802f77ba5dfa482c', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:05', '2019-03-05 22:06:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('a16cc512b21c48ee8f1884bc58fda7a8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:09', '2019-03-05 22:06:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('9ac583ebd10948a8a2c25bd09ed78386', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:13', '2019-03-05 22:06:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('3197ef2fdf5e4f16b1a1fa0d6fcb6f07', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:17', '2019-03-05 22:06:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('51da88e0966c41d3bd142c9a4e4f6c6f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:21', '2019-03-05 22:06:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('42a0b5c10cfc439da5b497781494a08a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:25', '2019-03-05 22:06:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('899b7696afa843128f49c60e5ead192b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:29', '2019-03-05 22:06:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('5b3b884bc3f64199882cebd4b2dbc68b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:33', '2019-03-05 22:06:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('45993abdd7ba4cb29b8c28b60240a782', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:37', '2019-03-05 22:06:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('8c6f8208397046c79b2d357b580af1a6', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:41', '2019-03-05 22:06:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('b4be46e914344892a39674b66ef4b4f1', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:45', '2019-03-05 22:06:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('1e692ceac09e4bb3a3d1b05eba3452ec', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:49', '2019-03-05 22:06:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('8cbf0f132d834f60bb8fa050c205ac2d', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:53', '2019-03-05 22:06:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('f1c8d0bab46c4bb0a22f9c51e0ecb015', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:57', '2019-03-05 22:06:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('e62881a1d1d9403094e2c19a615d1fab', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:01', '2019-03-05 22:07:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('28c433e1a6a14806a98b8999d8897ed4', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:05', '2019-03-05 22:07:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('5bf698a478064e2ab9e2f421d9d0c47d', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:09', '2019-03-05 22:07:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('7010d33046ce430abfc0c997efc2d501', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:13', '2019-03-05 22:07:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('e6598a9df4184e4295de3c92734c32be', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:17', '2019-03-05 22:07:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('65303510222f49d3a073701d2aea7a51', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:21', '2019-03-05 22:07:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('12011787ee324f118400ca36ceee8ab8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:25', '2019-03-05 22:07:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('e6acc197939e4ad3a5a46c82a40ac68f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:29', '2019-03-05 22:07:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('45aa3b595b9a4ef888ac8d596306455c', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:33', '2019-03-05 22:07:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('1beefe34a45f4252890bcfa03b4aafe4', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:37', '2019-03-05 22:07:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('5097c2af553f40888d27a0ce73531c31', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:41', '2019-03-05 22:07:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('18d8e79038d845f1b42b48b5923e9f66', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:45', '2019-03-05 22:07:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('a96511bd08cf4721a2f63f75212e46f2', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:49', '2019-03-05 22:07:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('d2f812066f99420b81c46acabb84010b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:53', '2019-03-05 22:07:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('954846aea1d6474c82270a3133777d1d', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:57', '2019-03-05 22:07:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('42b522a637434fb8858d7b202e30ff62', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:01', '2019-03-05 22:08:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('61d83078e82f499a9d711932d933e640', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:05', '2019-03-05 22:08:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('c7312cc96f984e4690f915aa171de101', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:09', '2019-03-05 22:08:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('b8aaed2c01e442e5b9c365525c267cc1', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:13', '2019-03-05 22:08:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('70544946561048f091e222242f3046aa', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:17', '2019-03-05 22:08:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('9d14370559c64bee8a2c505e2c8f307f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:21', '2019-03-05 22:08:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('b2800177a207429ea59adee733f19a0d', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:25', '2019-03-05 22:08:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('4853b4efe470478981a110e18c86226b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:29', '2019-03-05 22:08:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('f526d008d91b4e5f8a067b20857533ab', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:33', '2019-03-05 22:08:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('aba934386ea84859816bd8b8d01d35b8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:37', '2019-03-05 22:08:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('750da7934ed44c90aab22c2ff03a7124', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:41', '2019-03-05 22:08:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('97dd4469ef6f49439579d2b65c6034a7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:45', '2019-03-05 22:08:47');
INSERT INTO `dispatch_batch_group_history` VALUES ('2e3b81e3a88d4cfa821656d6def3818b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:49', '2019-03-05 22:08:51');
INSERT INTO `dispatch_batch_group_history` VALUES ('3929086db207407db064198df3763e63', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:53', '2019-03-05 22:08:55');
INSERT INTO `dispatch_batch_group_history` VALUES ('fec6adb9c32646338b50baa56ccbb8bc', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:08:58', '2019-03-05 22:08:59');
INSERT INTO `dispatch_batch_group_history` VALUES ('4cf42f39b2194f7fba7b0caaa2c95136', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:02', '2019-03-05 22:09:03');
INSERT INTO `dispatch_batch_group_history` VALUES ('47f7148b7e8741cd8c6d2d41c4808b88', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:05', '2019-03-05 22:09:07');
INSERT INTO `dispatch_batch_group_history` VALUES ('50d4c824f8974458912c11012c35e6cc', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:09', '2019-03-05 22:09:11');
INSERT INTO `dispatch_batch_group_history` VALUES ('b5946fe2a8ee45a0b9ce3587d8752641', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:13', '2019-03-05 22:09:15');
INSERT INTO `dispatch_batch_group_history` VALUES ('c186cc0676134f0485d6d86df8d70b3b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:17', '2019-03-05 22:09:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('98a2ee5f5f5048d8bc297c6bc76d8c6a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:21', '2019-03-05 22:09:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('3183cf7ba0f74812a4b50076cb61f532', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:25', '2019-03-05 22:09:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('6635f958017a4bc892801468e368b50e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:09:29', '2019-03-05 22:09:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('6183854e7d2b4059be5dc56ffe93ef0e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:14', '2019-03-06 22:13:15');
INSERT INTO `dispatch_batch_group_history` VALUES ('75cc9f83a6be4e6589cc6cda2c591b18', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:43', '2019-03-06 22:13:44');
INSERT INTO `dispatch_batch_group_history` VALUES ('e0dc5f5abf494c468f3c100b8e34bf07', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:47', '2019-03-06 22:13:48');
INSERT INTO `dispatch_batch_group_history` VALUES ('c6cf5ca5dcb14bdb8f1b74eb5f508fd5', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:51', '2019-03-06 22:13:52');
INSERT INTO `dispatch_batch_group_history` VALUES ('f1477c6fb60847208e93a19a00af1326', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:55', '2019-03-06 22:13:56');
INSERT INTO `dispatch_batch_group_history` VALUES ('952c5cc5cb8e4350ab991e57e6feca45', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:13:59', '2019-03-06 22:14:00');
INSERT INTO `dispatch_batch_group_history` VALUES ('b6cc54cafa1e4785b27472d55b8b41da', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:03', '2019-03-06 22:14:04');
INSERT INTO `dispatch_batch_group_history` VALUES ('8fdde69d052248919e4754d1ef16c222', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:06', '2019-03-06 22:14:07');
INSERT INTO `dispatch_batch_group_history` VALUES ('93ba8c87a5b942dc8d568dd4a6f74688', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:10', '2019-03-06 22:14:11');
INSERT INTO `dispatch_batch_group_history` VALUES ('bb60c6172bb74cad8cc51ce1c51ad087', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:14', '2019-03-06 22:14:16');
INSERT INTO `dispatch_batch_group_history` VALUES ('04655ec358e4402293c2977dd7a86536', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:18', '2019-03-06 22:14:20');
INSERT INTO `dispatch_batch_group_history` VALUES ('ebb9848c790840ca872fb92f7e5c3260', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:22', '2019-03-06 22:14:23');
INSERT INTO `dispatch_batch_group_history` VALUES ('8e4e8b7f3b544282913e4f9ed378fa9a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:26', '2019-03-06 22:14:27');
INSERT INTO `dispatch_batch_group_history` VALUES ('843f6d32336942f8bf142f56923c7e82', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:30', '2019-03-06 22:14:31');
INSERT INTO `dispatch_batch_group_history` VALUES ('75a731c194a7481fa3edf4367e53485a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:34', '2019-03-06 22:14:35');
INSERT INTO `dispatch_batch_group_history` VALUES ('9d06df4e363f4096b5028885d82bbb13', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:38', '2019-03-06 22:14:39');
INSERT INTO `dispatch_batch_group_history` VALUES ('44d3c0c3c1434e6582efc9cf004d2e16', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:41', '2019-03-06 22:14:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('ac97e2309f8e4011ab86eb9121996365', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:45', '2019-03-06 22:14:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('08fb4e24a1cd4ffe8b770721f88960a9', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:49', '2019-03-06 22:14:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('e474a938a5df4f648b91d56ea7baeeed', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:53', '2019-03-06 22:14:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('3e78f9e7374046688424ed4edfcd41ed', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:14:57', '2019-03-06 22:14:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('721939bce5c34c82b47473345b50bfc7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:01', '2019-03-06 22:15:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('99fe4b76ffd54d2aab71b822ce464d51', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:04', '2019-03-06 22:15:05');
INSERT INTO `dispatch_batch_group_history` VALUES ('cdea855616fa4ff992209be9272d94c4', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:08', '2019-03-06 22:15:09');
INSERT INTO `dispatch_batch_group_history` VALUES ('13c3dc61142747fc877fcb7f637e594c', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:12', '2019-03-06 22:15:13');
INSERT INTO `dispatch_batch_group_history` VALUES ('c8842802e03b47ebb174392b00c7dfac', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:16', '2019-03-06 22:15:17');
INSERT INTO `dispatch_batch_group_history` VALUES ('2765b0ccbe3c4b5db3c7c13d0f395f04', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:20', '2019-03-06 22:15:21');
INSERT INTO `dispatch_batch_group_history` VALUES ('1c56df4402124d848e75d50d4051ff2b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:24', '2019-03-06 22:15:25');
INSERT INTO `dispatch_batch_group_history` VALUES ('906e7c00e5894028953f7a72b7393237', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:27', '2019-03-06 22:15:28');
INSERT INTO `dispatch_batch_group_history` VALUES ('ea910ff2251e4625a614963ac7a168cf', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:31', '2019-03-06 22:15:32');
INSERT INTO `dispatch_batch_group_history` VALUES ('e9ccaa52452c4960a026f2319c0b059b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:35', '2019-03-06 22:15:36');
INSERT INTO `dispatch_batch_group_history` VALUES ('8bfcb439583140e583d2d670b77a3dec', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:15:39', '2019-03-06 22:15:40');
INSERT INTO `dispatch_batch_group_history` VALUES ('3241fcf38b4948a483be3d97ad5b6662', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:24', '2019-03-06 22:30:25');
INSERT INTO `dispatch_batch_group_history` VALUES ('d8086911d6c648b0919ee9ecca46f120', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:28', '2019-03-06 22:30:29');
INSERT INTO `dispatch_batch_group_history` VALUES ('2b20a5178b0641d99d6f62c0a68f7ae9', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:33', '2019-03-06 22:30:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('079f0e7b0f254c98825ff23ed2ab4c1e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:37', '2019-03-06 22:30:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('0f282eeb63aa4d2e9aeb76cd6fc67a72', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:41', '2019-03-06 22:30:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('6e37436a567b400f84ee8c426ba96fe3', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:44', '2019-03-06 22:30:45');
INSERT INTO `dispatch_batch_group_history` VALUES ('275b799299014a9195c313ed4ae90ba8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:49', '2019-03-06 22:30:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('57e58fca66cd446e899b6626ed347c52', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:52', '2019-03-06 22:30:53');
INSERT INTO `dispatch_batch_group_history` VALUES ('3a6a3f259849454c8ca4849d7ec83972', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:30:56', '2019-03-06 22:30:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('4fde86af8b94498c85c01c9c45024856', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:01', '2019-03-06 22:31:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('6d891fb4c5364bc58f3ae2c467a01a5b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:05', '2019-03-06 22:31:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('8e7de592f386410b9a5914d2fae12fcd', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:09', '2019-03-06 22:31:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('8e8289ccda134f959973ffaba0122e08', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:13', '2019-03-06 22:31:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('93f6afb0c55b4ec6917df5cda7e16f62', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:17', '2019-03-06 22:31:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('66ab21fcc2f44de5a04624a22fda495b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:21', '2019-03-06 22:31:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('18cdf385d88747cfa1cc4ac3c62445bc', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:25', '2019-03-06 22:31:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('3fd716faaa6c40608225f4425cb501be', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:29', '2019-03-06 22:31:30');
INSERT INTO `dispatch_batch_group_history` VALUES ('125c4001c7f14410965883a65ce4ed2a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:33', '2019-03-06 22:31:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('4e1a7d2a918e491f8ef85e87eda25335', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:36', '2019-03-06 22:31:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('bce89e95a37041a29c5d607d761ef536', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:40', '2019-03-06 22:31:41');
INSERT INTO `dispatch_batch_group_history` VALUES ('1ee210336e3c4879bd42f478f3754641', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:44', '2019-03-06 22:31:45');
INSERT INTO `dispatch_batch_group_history` VALUES ('3297df304f3a47d682a16ec98038e1b0', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:49', '2019-03-06 22:31:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('93d4c788e4664edbb679e965861a22ba', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:53', '2019-03-06 22:31:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('843bfb8e335846fca2cb0c3f32920a6f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:31:57', '2019-03-06 22:31:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('3beca5f2e41b46908fbde97c33eaa831', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:01', '2019-03-06 22:32:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('bd16f823a8dc4bd38514f314649f8ef7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:05', '2019-03-06 22:32:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('af45b64ae0384a6f8c6e2b265dde611a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:09', '2019-03-06 22:32:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('ce76403bee24440e9cb903ae37a7b5cb', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:13', '2019-03-06 22:32:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('069abb47c91342bd985af3a5648f211b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:17', '2019-03-06 22:32:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('1c8f9b0bdc7e435eb47da29a8b2afc9c', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:22', '2019-03-06 22:32:23');
INSERT INTO `dispatch_batch_group_history` VALUES ('03ebcdf109b34f07ad38a0598378321d', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:32:26', '2019-03-06 22:32:27');
INSERT INTO `dispatch_batch_group_history` VALUES ('43e76ce17f5143d48c6801fa3b5c8dc1', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-06 22:42:12', '2019-03-06 22:42:13');
INSERT INTO `dispatch_batch_group_history` VALUES ('6b3f1b6c6dfb4580a368f4f3998a142c', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:54:30', '2019-03-12 17:54:31');
INSERT INTO `dispatch_batch_group_history` VALUES ('54ee2fb317dd493d96eaeba2769e64a5', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:55:35', '2019-03-12 17:55:36');
INSERT INTO `dispatch_batch_group_history` VALUES ('91b82d94c1e04113b857f90951b456e0', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:57:59', '2019-03-12 17:58:00');
INSERT INTO `dispatch_batch_group_history` VALUES ('026a163c862f4dac83165e90151f7c79', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:37', '2019-03-12 17:58:38');
INSERT INTO `dispatch_batch_group_history` VALUES ('11326c3fc06243098022d779f6c0d8a7', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:40', '2019-03-12 17:58:41');
INSERT INTO `dispatch_batch_group_history` VALUES ('c9becbd333384b67bafb5b56dbc9eab8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:42', '2019-03-12 17:58:43');
INSERT INTO `dispatch_batch_group_history` VALUES ('b83c1064219042c090ef40110ba2491e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:45', '2019-03-12 17:58:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('7059c2238fff47df8f16c75e3f97a789', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:47', '2019-03-12 17:58:49');
INSERT INTO `dispatch_batch_group_history` VALUES ('8ec69ced6f714aeca3b0ab9927d3f813', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:50', '2019-03-12 17:58:51');
INSERT INTO `dispatch_batch_group_history` VALUES ('1a9affbadbf64666a38e984225fce155', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:53', '2019-03-12 17:58:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('e6e030164ac64e15a6c257c071b53bda', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:55', '2019-03-12 17:58:56');
INSERT INTO `dispatch_batch_group_history` VALUES ('3336258cc07e4ca9ad6c115b58b9261a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:58', '2019-03-12 17:58:59');
INSERT INTO `dispatch_batch_group_history` VALUES ('a0c7aa47dd494ec1838ec4f3a1eba988', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:00', '2019-03-12 17:59:01');
INSERT INTO `dispatch_batch_group_history` VALUES ('f42471958f1c4c359d6783fb859aeab6', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:03', '2019-03-12 17:59:04');
INSERT INTO `dispatch_batch_group_history` VALUES ('b35612bcf1b5432c810628c0a0e1526e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:05', '2019-03-12 17:59:06');
INSERT INTO `dispatch_batch_group_history` VALUES ('e98d6f57875e48afbf0b375f4dc35315', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:08', '2019-03-12 17:59:09');
INSERT INTO `dispatch_batch_group_history` VALUES ('c0d6b7951b4f4100ac303f6f3affdc63', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:10', '2019-03-12 17:59:11');
INSERT INTO `dispatch_batch_group_history` VALUES ('49ab1ea395b7426bac8e51628dad58d4', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:13', '2019-03-12 17:59:14');
INSERT INTO `dispatch_batch_group_history` VALUES ('37f313622b5c49f4b5440bc72dbea5d6', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:16', '2019-03-12 17:59:17');
INSERT INTO `dispatch_batch_group_history` VALUES ('f831781f799b4b4eac333bb5643b2319', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:18', '2019-03-12 17:59:19');
INSERT INTO `dispatch_batch_group_history` VALUES ('00752a7a4a2c4791a750ab8978a1fa3b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:21', '2019-03-12 17:59:22');
INSERT INTO `dispatch_batch_group_history` VALUES ('9a0d604899d7457fb125d276bee2411e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:23', '2019-03-12 17:59:24');
INSERT INTO `dispatch_batch_group_history` VALUES ('cb56b368d00e43d8b997ab4f7216180e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:26', '2019-03-12 17:59:27');
INSERT INTO `dispatch_batch_group_history` VALUES ('29f4911d72584506ad4707e35420e558', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:28', '2019-03-12 17:59:29');
INSERT INTO `dispatch_batch_group_history` VALUES ('2b6e5d413f164b0ba0c0136359319990', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:31', '2019-03-12 17:59:32');
INSERT INTO `dispatch_batch_group_history` VALUES ('0471ca0d29ea43948775f8a5e6373c7f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:33', '2019-03-12 17:59:34');
INSERT INTO `dispatch_batch_group_history` VALUES ('02a4ba653449428b9ae524bc18f6b62f', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:36', '2019-03-12 17:59:37');
INSERT INTO `dispatch_batch_group_history` VALUES ('64807ce348194c1ea7c8c4f026246fea', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:38', '2019-03-12 17:59:40');
INSERT INTO `dispatch_batch_group_history` VALUES ('e27ea04e415545f7914ffc92904cc51a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:41', '2019-03-12 17:59:42');
INSERT INTO `dispatch_batch_group_history` VALUES ('47bbcc8db62342a3825c7947ed4352ad', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:44', '2019-03-12 17:59:45');
INSERT INTO `dispatch_batch_group_history` VALUES ('9e7f1189073f41209b82f1b1da3f118a', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:46', '2019-03-12 17:59:47');
INSERT INTO `dispatch_batch_group_history` VALUES ('5e2b224e6d9e4df9a94e7c8cd2a1f481', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:49', '2019-03-12 17:59:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('e4ede2a0379a4bf58093d36bfd5a0540', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:51', '2019-03-12 17:59:52');
INSERT INTO `dispatch_batch_group_history` VALUES ('c7869f7243b144528b0ab074914f4098', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:54', '2019-03-12 17:59:55');
INSERT INTO `dispatch_batch_group_history` VALUES ('6452d773517f4c33a8fe8ab11f6403e8', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 20:56:57', '2019-03-12 20:56:58');
INSERT INTO `dispatch_batch_group_history` VALUES ('0e96bb474ea94a0d9bb4b521171b35d0', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 21:13:49', '2019-03-12 21:13:50');
INSERT INTO `dispatch_batch_group_history` VALUES ('6684e3130b80480dbd3213c495a7d381', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 21:52:38', '2019-03-12 21:52:39');
INSERT INTO `dispatch_batch_group_history` VALUES ('bb1ec5f021954291b84fa5a210fd9fb9', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 21:52:45', '2019-03-12 21:52:46');
INSERT INTO `dispatch_batch_group_history` VALUES ('047c12d000664529bb009d3a82708471', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:11:08', '2019-03-12 22:11:09');
INSERT INTO `dispatch_batch_group_history` VALUES ('2e4de02e80b84302a398fd656a876d94', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:11:28', '2019-03-12 22:11:29');
INSERT INTO `dispatch_batch_group_history` VALUES ('f50efa7aa8644fe1a63e74aa22de35c6', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:18:23', '2019-03-12 22:18:24');
INSERT INTO `dispatch_batch_group_history` VALUES ('8ae601987ba44304bbbaeba719edb464', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:18:30', '2019-03-12 22:18:32');
INSERT INTO `dispatch_batch_group_history` VALUES ('3f7989e2221644e2a2684dedce47c0ef', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:21:24', '2019-03-12 22:21:25');
INSERT INTO `dispatch_batch_group_history` VALUES ('1c2617be4f5a4924a97d77da458bcb9e', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:21:34', '2019-03-12 22:21:35');
INSERT INTO `dispatch_batch_group_history` VALUES ('2433859a3a1549b188caf8ed1855f6b2', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:21:48', '2019-03-12 22:21:49');
INSERT INTO `dispatch_batch_group_history` VALUES ('1cf10c14d6574081b567737d195dd91b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 22:22:11', '2019-03-12 22:22:12');
INSERT INTO `dispatch_batch_group_history` VALUES ('cd86cafee74e4424a686748a0a93db32', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 23:20:09', '2019-03-12 23:20:10');
INSERT INTO `dispatch_batch_group_history` VALUES ('c4cf032127eb443ca9997f1b9c4bc348', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 23:20:15', '2019-03-12 23:20:16');
INSERT INTO `dispatch_batch_group_history` VALUES ('42762c3c382842cf87996bbc39106cfc', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 23:23:44', '2019-03-12 23:23:45');
INSERT INTO `dispatch_batch_group_history` VALUES ('5313cd6e8ed344b1ac4c64299de0e8bb', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 23:47:27', '2019-03-12 23:47:29');
INSERT INTO `dispatch_batch_group_history` VALUES ('af163f63a85147eca7ba5e3ebf53e192', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 23:47:34', '2019-03-12 23:47:35');
INSERT INTO `dispatch_batch_group_history` VALUES ('383ffecc95e74094a2c0dfe8069cc6bd', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 21:23:08', '2019-03-13 21:23:09');
INSERT INTO `dispatch_batch_group_history` VALUES ('a8ce92ade0e246468319eb6d3f8c0920', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:09:17', '2019-03-13 22:09:18');
INSERT INTO `dispatch_batch_group_history` VALUES ('d0e0820ca0c04004990f0fce444a3ae5', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:09:24', '2019-03-13 22:09:25');
INSERT INTO `dispatch_batch_group_history` VALUES ('f48659d234894b4c8133f6c7573ec4ed', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:14:01', '2019-03-13 22:14:02');
INSERT INTO `dispatch_batch_group_history` VALUES ('b1f60ec5fbf64c438537e803cab04304', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:36:35', '2019-03-13 22:36:36');
INSERT INTO `dispatch_batch_group_history` VALUES ('dfc498518fc144519a69b5f2f29bf4e1', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:36:40', '2019-03-13 22:36:41');
INSERT INTO `dispatch_batch_group_history` VALUES ('d35f52593bde46798ef3920633de775b', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:44:48', '2019-03-13 22:44:49');
INSERT INTO `dispatch_batch_group_history` VALUES ('d8e2b20e399a437f9fca33306b7ebb62', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:45:25', '2019-03-13 22:45:26');
INSERT INTO `dispatch_batch_group_history` VALUES ('06b5aa62265c46f1a523b133da63f342', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 22:47:52', '2019-03-13 22:47:54');
INSERT INTO `dispatch_batch_group_history` VALUES ('b2f532fd109c4d41982468cb04da7295', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:11');
INSERT INTO `dispatch_batch_group_history` VALUES ('75894e75d88545d18463f27a8234b598', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:11');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_group_relation`;
CREATE TABLE `dispatch_batch_group_relation` (
  `suite_key` varchar(66) NOT NULL,
  `batch_id` varchar(66) DEFAULT NULL,
  `group_id` varchar(66) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`suite_key`),
  KEY `fk_dispatch_batch_group_001_idx` (`batch_id`),
  KEY `fk_dispatch_batch_group_002_idx` (`group_id`),
  CONSTRAINT `fk_dispatch_batch_group_001` FOREIGN KEY (`batch_id`) REFERENCES `dispatch_batch_define` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dispatch_batch_group_002` FOREIGN KEY (`group_id`) REFERENCES `dispatch_group_define` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_group_relation
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_group_relation` VALUES ('4025e88b8c5b4eccab3a911a06cf37fa', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'YmlnZGF0YR53aXNyYx5HMDAwMDAx', 'bigdata');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_group_status
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_group_status`;
CREATE TABLE `dispatch_batch_group_status` (
  `batch_id` varchar(66) DEFAULT NULL,
  `suite_key` varchar(66) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `as_of_date` datetime DEFAULT NULL,
  KEY `fk_dispatch_batch_group_status01_idx` (`batch_id`),
  CONSTRAINT `fk_dispatch_batch_group_status01` FOREIGN KEY (`batch_id`) REFERENCES `dispatch_batch_define` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_group_status
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:37', '2019-03-05 22:05:38', '2019-02-01 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:41', '2019-03-05 22:05:42', '2019-02-02 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:45', '2019-03-05 22:05:46', '2019-02-03 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:49', '2019-03-05 22:05:50', '2019-02-04 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:53', '2019-03-05 22:05:54', '2019-02-05 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:05:57', '2019-03-05 22:05:58', '2019-02-06 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:01', '2019-03-05 22:06:02', '2019-02-07 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:05', '2019-03-05 22:06:06', '2019-02-08 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:09', '2019-03-05 22:06:10', '2019-02-09 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:13', '2019-03-05 22:06:14', '2019-02-10 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:17', '2019-03-05 22:06:18', '2019-02-11 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:21', '2019-03-05 22:06:22', '2019-02-12 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:25', '2019-03-05 22:06:26', '2019-02-13 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:29', '2019-03-05 22:06:30', '2019-02-14 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:33', '2019-03-05 22:06:34', '2019-02-15 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:37', '2019-03-05 22:06:38', '2019-02-16 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:41', '2019-03-05 22:06:42', '2019-02-17 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:45', '2019-03-05 22:06:46', '2019-02-18 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:49', '2019-03-05 22:06:50', '2019-02-19 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:53', '2019-03-05 22:06:54', '2019-02-20 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:06:57', '2019-03-05 22:06:58', '2019-02-21 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:01', '2019-03-05 22:07:02', '2019-02-22 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:05', '2019-03-05 22:07:06', '2019-02-23 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:09', '2019-03-05 22:07:10', '2019-02-24 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:13', '2019-03-05 22:07:14', '2019-02-25 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:17', '2019-03-05 22:07:18', '2019-02-26 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:21', '2019-03-05 22:07:22', '2019-02-27 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-05 22:07:25', '2019-03-05 22:07:26', '2019-02-28 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:37', '2019-03-12 17:58:38', '2019-03-01 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:40', '2019-03-12 17:58:41', '2019-03-02 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:42', '2019-03-12 17:58:43', '2019-03-03 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:45', '2019-03-12 17:58:46', '2019-03-04 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:47', '2019-03-12 17:58:49', '2019-03-05 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:50', '2019-03-12 17:58:51', '2019-03-06 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:53', '2019-03-12 17:58:54', '2019-03-07 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:55', '2019-03-12 17:58:56', '2019-03-08 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:58:58', '2019-03-12 17:58:59', '2019-03-09 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:00', '2019-03-12 17:59:01', '2019-03-10 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:03', '2019-03-12 17:59:04', '2019-03-11 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:05', '2019-03-12 17:59:06', '2019-03-12 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:08', '2019-03-12 17:59:09', '2019-03-13 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:10', '2019-03-12 17:59:11', '2019-03-14 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:13', '2019-03-12 17:59:14', '2019-03-15 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:16', '2019-03-12 17:59:17', '2019-03-16 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:18', '2019-03-12 17:59:19', '2019-03-17 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:21', '2019-03-12 17:59:22', '2019-03-18 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:23', '2019-03-12 17:59:24', '2019-03-19 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:26', '2019-03-12 17:59:27', '2019-03-20 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:28', '2019-03-12 17:59:29', '2019-03-21 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:31', '2019-03-12 17:59:32', '2019-03-22 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:33', '2019-03-12 17:59:34', '2019-03-23 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:36', '2019-03-12 17:59:37', '2019-03-24 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:38', '2019-03-12 17:59:40', '2019-03-25 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:41', '2019-03-12 17:59:42', '2019-03-26 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:44', '2019-03-12 17:59:45', '2019-03-27 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:46', '2019-03-12 17:59:47', '2019-03-28 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:49', '2019-03-12 17:59:50', '2019-03-29 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-12 17:59:51', '2019-03-12 17:59:52', '2019-03-30 00:00:00');
INSERT INTO `dispatch_batch_group_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', '4025e88b8c5b4eccab3a911a06cf37fa', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:11', '2019-03-31 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_history
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_history`;
CREATE TABLE `dispatch_batch_history` (
  `sid` varchar(66) NOT NULL,
  `batch_id` varchar(99) DEFAULT NULL,
  `batch_status` varchar(5) DEFAULT NULL,
  `as_of_date` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `ret_msg` varchar(2000) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  `batch_desc` varchar(200) DEFAULT NULL,
  `code_number` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_history
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_history` VALUES ('00752a7a4a2c4791a750ab8978a1fa3b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-18 00:00:00', '2019-03-12 17:59:20', '2019-03-12 17:59:22', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('026a163c862f4dac83165e90151f7c79', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-01 00:00:00', '2019-03-12 17:58:37', '2019-03-12 17:58:39', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('02a4ba653449428b9ae524bc18f6b62f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-24 00:00:00', '2019-03-12 17:59:35', '2019-03-12 17:59:37', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('03ebcdf109b34f07ad38a0598378321d', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-06 22:32:24', '2019-03-06 22:32:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('04655ec358e4402293c2977dd7a86536', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-10 00:00:00', '2019-03-06 22:14:17', '2019-03-06 22:14:20', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0471ca0d29ea43948775f8a5e6373c7f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-23 00:00:00', '2019-03-12 17:59:33', '2019-03-12 17:59:35', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('047c12d000664529bb009d3a82708471', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:11:07', '2019-03-12 22:11:09', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('069abb47c91342bd985af3a5648f211b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-29 00:00:00', '2019-03-06 22:32:16', '2019-03-06 22:32:19', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('06b5aa62265c46f1a523b133da63f342', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:47:52', '2019-03-13 22:47:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0714615b8f764b8ba57c7ccb15a64677', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-19 00:00:00', '2019-03-05 00:07:48', '2019-03-05 00:07:52', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('079f0e7b0f254c98825ff23ed2ab4c1e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-04 00:00:00', '2019-03-06 22:30:35', '2019-03-06 22:30:38', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('08fb4e24a1cd4ffe8b770721f88960a9', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-18 00:00:00', '2019-03-06 22:14:48', '2019-03-06 22:14:51', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0983d3d5c53c497f823faa3601bf39e8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-15 00:00:00', '2019-03-05 00:07:33', '2019-03-05 00:07:36', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0b86a6345db2417ea45129cddad5b0ea', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-16 00:00:00', '2019-03-05 00:07:37', '2019-03-05 00:07:40', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0c8207a5f5d5418eb7f87142609b9320', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-27 00:00:00', '2019-03-05 00:08:20', '2019-03-05 00:08:23', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0e941c1c42ea410bb90674c267cb1920', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-21 00:00:00', '2019-03-05 00:07:56', '2019-03-05 00:07:59', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0e96bb474ea94a0d9bb4b521171b35d0', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 21:13:48', '2019-03-12 21:13:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('0f282eeb63aa4d2e9aeb76cd6fc67a72', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-05 00:00:00', '2019-03-06 22:30:39', '2019-03-06 22:30:42', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('11326c3fc06243098022d779f6c0d8a7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-02 00:00:00', '2019-03-12 17:58:39', '2019-03-12 17:58:41', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('12011787ee324f118400ca36ceee8ab8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-28 00:00:00', '2019-03-05 22:07:23', '2019-03-05 22:07:26', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('125c4001c7f14410965883a65ce4ed2a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-18 00:00:00', '2019-03-06 22:31:31', '2019-03-06 22:31:34', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('13c3dc61142747fc877fcb7f637e594c', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-24 00:00:00', '2019-03-06 22:15:11', '2019-03-06 22:15:14', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('18cdf385d88747cfa1cc4ac3c62445bc', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-16 00:00:00', '2019-03-06 22:31:24', '2019-03-06 22:31:26', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('18d8e79038d845f1b42b48b5923e9f66', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-05 00:00:00', '2019-03-05 22:07:44', '2019-03-05 22:07:47', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1a9affbadbf64666a38e984225fce155', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-07 00:00:00', '2019-03-12 17:58:52', '2019-03-12 17:58:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1b7d6b62699c40ebb9d66326dc9901bd', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-03 00:00:00', '2019-03-05 00:06:46', '2019-03-05 00:06:49', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1beefe34a45f4252890bcfa03b4aafe4', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-03 00:00:00', '2019-03-05 22:07:35', '2019-03-05 22:07:39', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1c2617be4f5a4924a97d77da458bcb9e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:21:34', '2019-03-12 22:21:36', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1c56df4402124d848e75d50d4051ff2b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-27 00:00:00', '2019-03-06 22:15:22', '2019-03-06 22:15:25', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1c8f9b0bdc7e435eb47da29a8b2afc9c', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-30 00:00:00', '2019-03-06 22:32:20', '2019-03-06 22:32:23', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1cf10c14d6574081b567737d195dd91b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:22:10', '2019-03-12 22:22:12', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1cf2f296fb2e43fb855cdb2ff46148e9', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-24 00:00:00', '2019-03-05 00:08:08', '2019-03-05 00:08:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1e692ceac09e4bb3a3d1b05eba3452ec', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-19 00:00:00', '2019-03-05 22:06:47', '2019-03-05 22:06:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1e7a974ea4ab4821929f295eaf092997', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-06 00:00:00', '2019-03-05 22:05:55', '2019-03-05 22:05:58', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('1ee210336e3c4879bd42f478f3754641', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-21 00:00:00', '2019-03-06 22:31:43', '2019-03-06 22:31:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('23b10cbaaeff403b8c75a379a2736d74', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-09 00:00:00', '2019-03-05 00:07:09', '2019-03-05 00:07:12', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2433859a3a1549b188caf8ed1855f6b2', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:21:47', '2019-03-12 22:21:49', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2661b3797cf54133a3968b5bc37cb4ec', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-17 00:00:00', '2019-03-05 00:07:41', '2019-03-05 00:07:44', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('275b799299014a9195c313ed4ae90ba8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-07 00:00:00', '2019-03-06 22:30:47', '2019-03-06 22:30:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2765b0ccbe3c4b5db3c7c13d0f395f04', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-26 00:00:00', '2019-03-06 22:15:18', '2019-03-06 22:15:21', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2898bd35e2d44d7eaebd706b62a9257f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-05 00:00:00', '2019-03-05 00:06:54', '2019-03-05 00:06:57', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('28c433e1a6a14806a98b8999d8897ed4', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-23 00:00:00', '2019-03-05 22:07:03', '2019-03-05 22:07:06', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('29f4911d72584506ad4707e35420e558', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-21 00:00:00', '2019-03-12 17:59:28', '2019-03-12 17:59:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2b20a5178b0641d99d6f62c0a68f7ae9', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-03 00:00:00', '2019-03-06 22:30:31', '2019-03-06 22:30:34', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2b6e5d413f164b0ba0c0136359319990', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-22 00:00:00', '2019-03-12 17:59:30', '2019-03-12 17:59:32', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2e3b81e3a88d4cfa821656d6def3818b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-21 00:00:00', '2019-03-05 22:08:48', '2019-03-05 22:08:51', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('2e4de02e80b84302a398fd656a876d94', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:11:28', '2019-03-12 22:11:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3183cf7ba0f74812a4b50076cb61f532', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-30 00:00:00', '2019-03-05 22:09:24', '2019-03-05 22:09:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3197ef2fdf5e4f16b1a1fa0d6fcb6f07', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-11 00:00:00', '2019-03-05 22:06:15', '2019-03-05 22:06:18', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3241fcf38b4948a483be3d97ad5b6662', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-01 00:00:00', '2019-03-06 22:30:23', '2019-03-06 22:30:26', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('329698d23e0c4c138cf38b28c5b3e8ce', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-04 00:00:00', '2019-03-05 00:06:50', '2019-03-05 00:06:53', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3297df304f3a47d682a16ec98038e1b0', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-22 00:00:00', '2019-03-06 22:31:47', '2019-03-06 22:31:51', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3336258cc07e4ca9ad6c115b58b9261a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-09 00:00:00', '2019-03-12 17:58:57', '2019-03-12 17:58:59', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('37f313622b5c49f4b5440bc72dbea5d6', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-16 00:00:00', '2019-03-12 17:59:15', '2019-03-12 17:59:17', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('383ffecc95e74094a2c0dfe8069cc6bd', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 21:23:07', '2019-03-13 21:23:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3929086db207407db064198df3763e63', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-22 00:00:00', '2019-03-05 22:08:52', '2019-03-05 22:08:55', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3a6a3f259849454c8ca4849d7ec83972', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-09 00:00:00', '2019-03-06 22:30:55', '2019-03-06 22:30:58', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3beca5f2e41b46908fbde97c33eaa831', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-25 00:00:00', '2019-03-06 22:32:00', '2019-03-06 22:32:03', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3e78f9e7374046688424ed4edfcd41ed', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-20 00:00:00', '2019-03-06 22:14:55', '2019-03-06 22:14:58', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3f7989e2221644e2a2684dedce47c0ef', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:21:23', '2019-03-12 22:21:26', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('3fd716faaa6c40608225f4425cb501be', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-17 00:00:00', '2019-03-06 22:31:27', '2019-03-06 22:31:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('42762c3c382842cf87996bbc39106cfc', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 23:23:44', '2019-03-12 23:23:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('42a0b5c10cfc439da5b497781494a08a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-13 00:00:00', '2019-03-05 22:06:23', '2019-03-05 22:06:26', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('42b522a637434fb8858d7b202e30ff62', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-09 00:00:00', '2019-03-05 22:08:00', '2019-03-05 22:08:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('43e76ce17f5143d48c6801fa3b5c8dc1', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-06 22:42:11', '2019-03-06 22:42:13', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('44382eb469674d31a09ea2c0af1b28b7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-14 00:00:00', '2019-03-05 00:07:29', '2019-03-05 00:07:32', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('44d3c0c3c1434e6582efc9cf004d2e16', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-16 00:00:00', '2019-03-06 22:14:40', '2019-03-06 22:14:43', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('45993abdd7ba4cb29b8c28b60240a782', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-16 00:00:00', '2019-03-05 22:06:35', '2019-03-05 22:06:38', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('45aa3b595b9a4ef888ac8d596306455c', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-02 00:00:00', '2019-03-05 22:07:31', '2019-03-05 22:07:34', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('47bbcc8db62342a3825c7947ed4352ad', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-27 00:00:00', '2019-03-12 17:59:43', '2019-03-12 17:59:45', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('47f7148b7e8741cd8c6d2d41c4808b88', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-25 00:00:00', '2019-03-05 22:09:04', '2019-03-05 22:09:07', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4853b4efe470478981a110e18c86226b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-16 00:00:00', '2019-03-05 22:08:28', '2019-03-05 22:08:31', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('49ab1ea395b7426bac8e51628dad58d4', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-15 00:00:00', '2019-03-12 17:59:12', '2019-03-12 17:59:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4aa4999245d04766b6d41c4fde9365b3', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-12 00:00:00', '2019-03-05 00:07:21', '2019-03-05 00:07:24', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4ba35c108cd841e98f6ea87f499bc2b6', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-30 00:00:00', '2019-03-05 00:08:32', '2019-03-05 00:08:35', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4cf42f39b2194f7fba7b0caaa2c95136', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-24 00:00:00', '2019-03-05 22:09:00', '2019-03-05 22:09:03', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4e1a7d2a918e491f8ef85e87eda25335', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-19 00:00:00', '2019-03-06 22:31:35', '2019-03-06 22:31:38', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4fde86af8b94498c85c01c9c45024856', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-10 00:00:00', '2019-03-06 22:30:59', '2019-03-06 22:31:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('4fff06389871474f989d3ee0490ee732', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-07 00:00:00', '2019-03-05 22:05:59', '2019-03-05 22:06:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('5097c2af553f40888d27a0ce73531c31', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-04 00:00:00', '2019-03-05 22:07:40', '2019-03-05 22:07:43', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('50d4c824f8974458912c11012c35e6cc', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-26 00:00:00', '2019-03-05 22:09:08', '2019-03-05 22:09:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('51992a41672e4991bbca0014d5ff4bb5', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-20 00:00:00', '2019-03-05 00:07:53', '2019-03-05 00:07:56', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('51da88e0966c41d3bd142c9a4e4f6c6f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-12 00:00:00', '2019-03-05 22:06:19', '2019-03-05 22:06:22', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('5313cd6e8ed344b1ac4c64299de0e8bb', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 23:47:27', '2019-03-12 23:47:29', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('54ee2fb317dd493d96eaeba2769e64a5', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 17:55:34', '2019-03-12 17:55:36', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('57e58fca66cd446e899b6626ed347c52', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-08 00:00:00', '2019-03-06 22:30:51', '2019-03-06 22:30:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('5b3b884bc3f64199882cebd4b2dbc68b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-15 00:00:00', '2019-03-05 22:06:31', '2019-03-05 22:06:34', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('5bf698a478064e2ab9e2f421d9d0c47d', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-24 00:00:00', '2019-03-05 22:07:07', '2019-03-05 22:07:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('5e2b224e6d9e4df9a94e7c8cd2a1f481', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-29 00:00:00', '2019-03-12 17:59:48', '2019-03-12 17:59:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6183854e7d2b4059be5dc56ffe93ef0e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-06 22:13:13', '2019-03-06 22:13:16', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('61d83078e82f499a9d711932d933e640', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-10 00:00:00', '2019-03-05 22:08:03', '2019-03-05 22:08:06', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6452d773517f4c33a8fe8ab11f6403e8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 20:56:55', '2019-03-12 20:56:58', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('64807ce348194c1ea7c8c4f026246fea', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-25 00:00:00', '2019-03-12 17:59:38', '2019-03-12 17:59:40', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('65303510222f49d3a073701d2aea7a51', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-27 00:00:00', '2019-03-05 22:07:19', '2019-03-05 22:07:22', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6635f958017a4bc892801468e368b50e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-05 22:09:28', '2019-03-05 22:09:31', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6684e3130b80480dbd3213c495a7d381', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 21:52:37', '2019-03-12 21:52:40', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('66ab21fcc2f44de5a04624a22fda495b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-15 00:00:00', '2019-03-06 22:31:20', '2019-03-06 22:31:23', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('687562cd94b8421b89b8bec964e920fa', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-28 00:00:00', '2019-03-05 00:08:24', '2019-03-05 00:08:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6ab390ffd2ea4963ac9d013cf98c8ed3', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-07 00:00:00', '2019-03-05 00:07:02', '2019-03-05 00:07:05', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6b3f1b6c6dfb4580a368f4f3998a142c', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 17:54:30', '2019-03-12 17:54:32', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6d6503987c2d43938d9d3a062337bff3', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-13 00:00:00', '2019-03-05 00:07:25', '2019-03-05 00:07:28', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6d891fb4c5364bc58f3ae2c467a01a5b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-11 00:00:00', '2019-03-06 22:31:03', '2019-03-06 22:31:06', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('6e37436a567b400f84ee8c426ba96fe3', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-06 00:00:00', '2019-03-06 22:30:43', '2019-03-06 22:30:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('7010d33046ce430abfc0c997efc2d501', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-25 00:00:00', '2019-03-05 22:07:11', '2019-03-05 22:07:14', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('70544946561048f091e222242f3046aa', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-13 00:00:00', '2019-03-05 22:08:16', '2019-03-05 22:08:19', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('7059c2238fff47df8f16c75e3f97a789', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-05 00:00:00', '2019-03-12 17:58:47', '2019-03-12 17:58:49', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('721939bce5c34c82b47473345b50bfc7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-21 00:00:00', '2019-03-06 22:14:59', '2019-03-06 22:15:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('750da7934ed44c90aab22c2ff03a7124', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-19 00:00:00', '2019-03-05 22:08:40', '2019-03-05 22:08:43', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('75894e75d88545d18463f27a8234b598', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 23:36:09', '2019-03-13 23:36:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('75a731c194a7481fa3edf4367e53485a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-14 00:00:00', '2019-03-06 22:14:32', '2019-03-06 22:14:35', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('75cc9f83a6be4e6589cc6cda2c591b18', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-01 00:00:00', '2019-03-06 22:13:42', '2019-03-06 22:13:44', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('7932f8281464444ab32d39b08da1001e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-01 00:00:00', '2019-03-05 00:06:38', '2019-03-05 00:06:41', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('7c42a86a5e384da38a5cb4278b53831b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-08 00:00:00', '2019-03-05 00:07:06', '2019-03-05 00:07:08', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('7f87be93248c4bec922b4bac4b0b0275', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-23 00:00:00', '2019-03-05 00:08:04', '2019-03-05 00:08:07', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('843bfb8e335846fca2cb0c3f32920a6f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-24 00:00:00', '2019-03-06 22:31:56', '2019-03-06 22:31:59', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('843f6d32336942f8bf142f56923c7e82', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-13 00:00:00', '2019-03-06 22:14:29', '2019-03-06 22:14:31', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('86eddb8107db47cfa74761ffafb827ea', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-04 00:00:00', '2019-03-05 22:05:47', '2019-03-05 22:05:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('87d32990ccdf4c0bb9fe9a0db72cd009', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-29 00:00:00', '2019-03-05 00:08:28', '2019-03-05 00:08:31', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('899b7696afa843128f49c60e5ead192b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-14 00:00:00', '2019-03-05 22:06:27', '2019-03-05 22:06:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8ae601987ba44304bbbaeba719edb464', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:18:30', '2019-03-12 22:18:32', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8bfcb439583140e583d2d670b77a3dec', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-06 22:15:37', '2019-03-06 22:15:40', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8c6f8208397046c79b2d357b580af1a6', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-17 00:00:00', '2019-03-05 22:06:39', '2019-03-05 22:06:42', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8cbf0f132d834f60bb8fa050c205ac2d', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-20 00:00:00', '2019-03-05 22:06:51', '2019-03-05 22:06:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8e4e8b7f3b544282913e4f9ed378fa9a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-12 00:00:00', '2019-03-06 22:14:25', '2019-03-06 22:14:28', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8e7de592f386410b9a5914d2fae12fcd', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-12 00:00:00', '2019-03-06 22:31:08', '2019-03-06 22:31:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8e8289ccda134f959973ffaba0122e08', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-13 00:00:00', '2019-03-06 22:31:12', '2019-03-06 22:31:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8ec69ced6f714aeca3b0ab9927d3f813', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-06 00:00:00', '2019-03-12 17:58:49', '2019-03-12 17:58:52', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('8fdde69d052248919e4754d1ef16c222', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-07 00:00:00', '2019-03-06 22:14:05', '2019-03-06 22:14:08', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('906e7c00e5894028953f7a72b7393237', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-28 00:00:00', '2019-03-06 22:15:26', '2019-03-06 22:15:29', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9139614ff56c4d1b835be3fbf78b5077', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-11 00:00:00', '2019-03-05 00:07:18', '2019-03-05 00:07:20', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('91b82d94c1e04113b857f90951b456e0', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 17:57:59', '2019-03-12 17:58:01', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('93ba8c87a5b942dc8d568dd4a6f74688', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-08 00:00:00', '2019-03-06 22:14:09', '2019-03-06 22:14:12', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('93d4c788e4664edbb679e965861a22ba', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-23 00:00:00', '2019-03-06 22:31:52', '2019-03-06 22:31:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('93f6afb0c55b4ec6917df5cda7e16f62', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-14 00:00:00', '2019-03-06 22:31:16', '2019-03-06 22:31:19', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('952c5cc5cb8e4350ab991e57e6feca45', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-05 00:00:00', '2019-03-06 22:13:57', '2019-03-06 22:14:00', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('954846aea1d6474c82270a3133777d1d', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-08 00:00:00', '2019-03-05 22:07:55', '2019-03-05 22:07:59', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('97dd4469ef6f49439579d2b65c6034a7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-20 00:00:00', '2019-03-05 22:08:44', '2019-03-05 22:08:47', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('98a2ee5f5f5048d8bc297c6bc76d8c6a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-29 00:00:00', '2019-03-05 22:09:20', '2019-03-05 22:09:23', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('99ca287e784a498abddc4c781266a8a5', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-22 00:00:00', '2019-03-05 00:08:00', '2019-03-05 00:08:03', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('99fe4b76ffd54d2aab71b822ce464d51', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-22 00:00:00', '2019-03-06 22:15:03', '2019-03-06 22:15:06', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9a0d604899d7457fb125d276bee2411e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-19 00:00:00', '2019-03-12 17:59:23', '2019-03-12 17:59:25', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9ac583ebd10948a8a2c25bd09ed78386', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-10 00:00:00', '2019-03-05 22:06:11', '2019-03-05 22:06:14', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9d06df4e363f4096b5028885d82bbb13', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-15 00:00:00', '2019-03-06 22:14:36', '2019-03-06 22:14:39', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9d14370559c64bee8a2c505e2c8f307f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-14 00:00:00', '2019-03-05 22:08:20', '2019-03-05 22:08:23', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9d2d1c80d5a74c8b8b429af696949b45', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-10 00:00:00', '2019-03-05 00:07:13', '2019-03-05 00:07:16', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('9e7f1189073f41209b82f1b1da3f118a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-28 00:00:00', '2019-03-12 17:59:46', '2019-03-12 17:59:48', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('a0c7aa47dd494ec1838ec4f3a1eba988', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-10 00:00:00', '2019-03-12 17:59:00', '2019-03-12 17:59:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('a16cc512b21c48ee8f1884bc58fda7a8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-09 00:00:00', '2019-03-05 22:06:07', '2019-03-05 22:06:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('a2ce46a525524e8ca970b4758152e4c4', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-01 00:00:00', '2019-03-05 22:05:36', '2019-03-05 22:05:39', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('a8ce92ade0e246468319eb6d3f8c0920', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:09:16', '2019-03-13 22:09:18', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('a96511bd08cf4721a2f63f75212e46f2', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-06 00:00:00', '2019-03-05 22:07:48', '2019-03-05 22:07:51', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('aba934386ea84859816bd8b8d01d35b8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-18 00:00:00', '2019-03-05 22:08:36', '2019-03-05 22:08:39', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ac97e2309f8e4011ab86eb9121996365', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-17 00:00:00', '2019-03-06 22:14:44', '2019-03-06 22:14:47', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ad49e91649af42bba08a8115b0e14ce7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-18 00:00:00', '2019-03-05 00:07:45', '2019-03-05 00:07:48', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('af163f63a85147eca7ba5e3ebf53e192', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 23:47:33', '2019-03-12 23:47:35', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('af45b64ae0384a6f8c6e2b265dde611a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-27 00:00:00', '2019-03-06 22:32:08', '2019-03-06 22:32:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b1f60ec5fbf64c438537e803cab04304', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:36:34', '2019-03-13 22:36:36', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b2800177a207429ea59adee733f19a0d', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-15 00:00:00', '2019-03-05 22:08:24', '2019-03-05 22:08:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b2f532fd109c4d41982468cb04da7295', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 23:36:09', '2019-03-13 23:36:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b35612bcf1b5432c810628c0a0e1526e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-12 00:00:00', '2019-03-12 17:59:05', '2019-03-12 17:59:07', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b4be46e914344892a39674b66ef4b4f1', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-18 00:00:00', '2019-03-05 22:06:43', '2019-03-05 22:06:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b5946fe2a8ee45a0b9ce3587d8752641', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-27 00:00:00', '2019-03-05 22:09:12', '2019-03-05 22:09:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b6cc54cafa1e4785b27472d55b8b41da', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-06 00:00:00', '2019-03-06 22:14:01', '2019-03-06 22:14:04', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b77df67ddf034bd4ae0a3fe0d648cda2', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-05 00:08:36', '2019-03-05 00:08:38', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b83c1064219042c090ef40110ba2491e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-04 00:00:00', '2019-03-12 17:58:44', '2019-03-12 17:58:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b8861c6887f9459999c10bc16e25dd2f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-25 00:00:00', '2019-03-05 00:08:12', '2019-03-05 00:08:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('b8aaed2c01e442e5b9c365525c267cc1', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-12 00:00:00', '2019-03-05 22:08:12', '2019-03-05 22:08:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('bb085aa07a4648c1802f77ba5dfa482c', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-08 00:00:00', '2019-03-05 22:06:03', '2019-03-05 22:06:06', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('bb1ec5f021954291b84fa5a210fd9fb9', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 21:52:44', '2019-03-12 21:52:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('bb60c6172bb74cad8cc51ce1c51ad087', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-09 00:00:00', '2019-03-06 22:14:13', '2019-03-06 22:14:16', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('bce89e95a37041a29c5d607d761ef536', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-20 00:00:00', '2019-03-06 22:31:39', '2019-03-06 22:31:42', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('bd16f823a8dc4bd38514f314649f8ef7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-26 00:00:00', '2019-03-06 22:32:04', '2019-03-06 22:32:07', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c0d6b7951b4f4100ac303f6f3affdc63', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-14 00:00:00', '2019-03-12 17:59:10', '2019-03-12 17:59:12', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c186cc0676134f0485d6d86df8d70b3b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-28 00:00:00', '2019-03-05 22:09:16', '2019-03-05 22:09:19', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c3e9d80186834757abb011396cb66593', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-06 00:00:00', '2019-03-05 00:06:58', '2019-03-05 00:07:01', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c4cf032127eb443ca9997f1b9c4bc348', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 23:20:14', '2019-03-12 23:20:16', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c6cf5ca5dcb14bdb8f1b74eb5f508fd5', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-03 00:00:00', '2019-03-06 22:13:50', '2019-03-06 22:13:52', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c7312cc96f984e4690f915aa171de101', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-11 00:00:00', '2019-03-05 22:08:08', '2019-03-05 22:08:11', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c7869f7243b144528b0ab074914f4098', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 17:59:53', '2019-03-12 17:59:55', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c8842802e03b47ebb174392b00c7dfac', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-25 00:00:00', '2019-03-06 22:15:15', '2019-03-06 22:15:17', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('c9becbd333384b67bafb5b56dbc9eab8', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-03 00:00:00', '2019-03-12 17:58:42', '2019-03-12 17:58:44', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('cb56b368d00e43d8b997ab4f7216180e', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-20 00:00:00', '2019-03-12 17:59:25', '2019-03-12 17:59:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('cd86cafee74e4424a686748a0a93db32', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 23:20:08', '2019-03-12 23:20:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('cdea855616fa4ff992209be9272d94c4', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-23 00:00:00', '2019-03-06 22:15:07', '2019-03-06 22:15:10', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ce76403bee24440e9cb903ae37a7b5cb', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-28 00:00:00', '2019-03-06 22:32:12', '2019-03-06 22:32:15', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d0e0820ca0c04004990f0fce444a3ae5', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:09:23', '2019-03-13 22:09:25', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d2f812066f99420b81c46acabb84010b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-07 00:00:00', '2019-03-05 22:07:52', '2019-03-05 22:07:55', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d2f9e7dc295049328bdf05120abb5776', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-02 00:00:00', '2019-03-05 22:05:40', '2019-03-05 22:05:43', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d35f52593bde46798ef3920633de775b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:44:48', '2019-03-13 22:44:50', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d8086911d6c648b0919ee9ecca46f120', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-02 00:00:00', '2019-03-06 22:30:27', '2019-03-06 22:30:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d8e2b20e399a437f9fca33306b7ebb62', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:45:24', '2019-03-13 22:45:27', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('d9d9c60bfd6f457fa20af77b35abddf7', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-05 00:00:00', '2019-03-05 22:05:51', '2019-03-05 22:05:54', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('dfc498518fc144519a69b5f2f29bf4e1', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:36:39', '2019-03-13 22:36:42', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e0dc5f5abf494c468f3c100b8e34bf07', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-02 00:00:00', '2019-03-06 22:13:46', '2019-03-06 22:13:49', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e27ea04e415545f7914ffc92904cc51a', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-26 00:00:00', '2019-03-12 17:59:40', '2019-03-12 17:59:43', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e474a938a5df4f648b91d56ea7baeeed', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-19 00:00:00', '2019-03-06 22:14:52', '2019-03-06 22:14:55', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e4ede2a0379a4bf58093d36bfd5a0540', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-30 00:00:00', '2019-03-12 17:59:51', '2019-03-12 17:59:53', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e62881a1d1d9403094e2c19a615d1fab', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-22 00:00:00', '2019-03-05 22:06:59', '2019-03-05 22:07:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e6598a9df4184e4295de3c92734c32be', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-26 00:00:00', '2019-03-05 22:07:15', '2019-03-05 22:07:18', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e6acc197939e4ad3a5a46c82a40ac68f', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-01 00:00:00', '2019-03-05 22:07:27', '2019-03-05 22:07:30', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e6e030164ac64e15a6c257c071b53bda', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-08 00:00:00', '2019-03-12 17:58:54', '2019-03-12 17:58:57', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e8eb97a25bba47678cb8d7541c867999', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-02 00:00:00', '2019-03-05 00:06:42', '2019-03-05 00:06:45', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e98d6f57875e48afbf0b375f4dc35315', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-13 00:00:00', '2019-03-12 17:59:07', '2019-03-12 17:59:09', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('e9ccaa52452c4960a026f2319c0b059b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-30 00:00:00', '2019-03-06 22:15:34', '2019-03-06 22:15:36', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ea910ff2251e4625a614963ac7a168cf', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-29 00:00:00', '2019-03-06 22:15:30', '2019-03-06 22:15:33', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ebb9848c790840ca872fb92f7e5c3260', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-11 00:00:00', '2019-03-06 22:14:21', '2019-03-06 22:14:24', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f09b58d739e2498bbb03298bb4642f3b', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-03 00:00:00', '2019-03-05 22:05:44', '2019-03-05 22:05:46', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f1477c6fb60847208e93a19a00af1326', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-04 00:00:00', '2019-03-06 22:13:53', '2019-03-06 22:13:56', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f1c8d0bab46c4bb0a22f9c51e0ecb015', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-02-21 00:00:00', '2019-03-05 22:06:55', '2019-03-05 22:06:58', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f42471958f1c4c359d6783fb859aeab6', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-11 00:00:00', '2019-03-12 17:59:02', '2019-03-12 17:59:04', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f48659d234894b4c8133f6c7573ec4ed', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-13 22:14:00', '2019-03-13 22:14:02', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f50efa7aa8644fe1a63e74aa22de35c6', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-31 22:00:00', '2019-03-12 22:18:22', '2019-03-12 22:18:24', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f526d008d91b4e5f8a067b20857533ab', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-17 00:00:00', '2019-03-05 22:08:32', '2019-03-05 22:08:35', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('f831781f799b4b4eac333bb5643b2319', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-17 00:00:00', '2019-03-12 17:59:17', '2019-03-12 17:59:20', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('fec6adb9c32646338b50baa56ccbb8bc', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-23 00:00:00', '2019-03-05 22:08:56', '2019-03-05 22:08:59', 'success', 'bigdata', '测试批次', 'B00001');
INSERT INTO `dispatch_batch_history` VALUES ('ff522df593464f4898f8165a9f4a1dd1', 'YmlnZGF0YR53aXNyYx5CMDAwMDE=', '2', '2019-03-26 00:00:00', '2019-03-05 00:08:16', '2019-03-05 00:08:19', 'success', 'bigdata', '测试批次', 'B00001');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_job_history
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_job_history`;
CREATE TABLE `dispatch_batch_job_history` (
  `sid` varchar(66) DEFAULT NULL,
  `job_id` varchar(200) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `suite_key` varchar(99) DEFAULT NULL,
  `job_key` varchar(99) DEFAULT NULL,
  KEY `fk_dispatch_batch_job01_idx` (`sid`),
  CONSTRAINT `fk_dispatch_batch_job01` FOREIGN KEY (`sid`) REFERENCES `dispatch_batch_history` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_job_history
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_job_history` VALUES ('7932f8281464444ab32d39b08da1001e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:40', '2019-03-05 00:06:40', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e8eb97a25bba47678cb8d7541c867999', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:44', '2019-03-05 00:06:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1b7d6b62699c40ebb9d66326dc9901bd', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:48', '2019-03-05 00:06:48', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('329698d23e0c4c138cf38b28c5b3e8ce', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:52', '2019-03-05 00:06:52', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2898bd35e2d44d7eaebd706b62a9257f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:55', '2019-03-05 00:06:55', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c3e9d80186834757abb011396cb66593', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:06:59', '2019-03-05 00:06:59', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6ab390ffd2ea4963ac9d013cf98c8ed3', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:03', '2019-03-05 00:07:03', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('7c42a86a5e384da38a5cb4278b53831b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:07', '2019-03-05 00:07:07', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('23b10cbaaeff403b8c75a379a2736d74', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:11', '2019-03-05 00:07:11', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9d2d1c80d5a74c8b8b429af696949b45', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:15', '2019-03-05 00:07:15', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9139614ff56c4d1b835be3fbf78b5077', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:19', '2019-03-05 00:07:19', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4aa4999245d04766b6d41c4fde9365b3', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:23', '2019-03-05 00:07:23', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6d6503987c2d43938d9d3a062337bff3', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:27', '2019-03-05 00:07:27', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('44382eb469674d31a09ea2c0af1b28b7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:31', '2019-03-05 00:07:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0983d3d5c53c497f823faa3601bf39e8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:34', '2019-03-05 00:07:34', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0b86a6345db2417ea45129cddad5b0ea', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:38', '2019-03-05 00:07:38', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2661b3797cf54133a3968b5bc37cb4ec', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:42', '2019-03-05 00:07:42', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ad49e91649af42bba08a8115b0e14ce7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:46', '2019-03-05 00:07:46', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0714615b8f764b8ba57c7ccb15a64677', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:50', '2019-03-05 00:07:50', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('51992a41672e4991bbca0014d5ff4bb5', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:54', '2019-03-05 00:07:54', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0e941c1c42ea410bb90674c267cb1920', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:07:58', '2019-03-05 00:07:58', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('99ca287e784a498abddc4c781266a8a5', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:02', '2019-03-05 00:08:02', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('7f87be93248c4bec922b4bac4b0b0275', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:06', '2019-03-05 00:08:06', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1cf2f296fb2e43fb855cdb2ff46148e9', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:10', '2019-03-05 00:08:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b8861c6887f9459999c10bc16e25dd2f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:13', '2019-03-05 00:08:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ff522df593464f4898f8165a9f4a1dd1', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:17', '2019-03-05 00:08:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0c8207a5f5d5418eb7f87142609b9320', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:21', '2019-03-05 00:08:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('687562cd94b8421b89b8bec964e920fa', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:25', '2019-03-05 00:08:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('87d32990ccdf4c0bb9fe9a0db72cd009', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:29', '2019-03-05 00:08:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4ba35c108cd841e98f6ea87f499bc2b6', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:33', '2019-03-05 00:08:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b77df67ddf034bd4ae0a3fe0d648cda2', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 00:08:37', '2019-03-05 00:08:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('a2ce46a525524e8ca970b4758152e4c4', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:37', '2019-03-05 22:05:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d2f9e7dc295049328bdf05120abb5776', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:41', '2019-03-05 22:05:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f09b58d739e2498bbb03298bb4642f3b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:45', '2019-03-05 22:05:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('86eddb8107db47cfa74761ffafb827ea', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:49', '2019-03-05 22:05:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d9d9c60bfd6f457fa20af77b35abddf7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:53', '2019-03-05 22:05:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1e7a974ea4ab4821929f295eaf092997', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:57', '2019-03-05 22:05:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4fff06389871474f989d3ee0490ee732', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:01', '2019-03-05 22:06:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('bb085aa07a4648c1802f77ba5dfa482c', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:05', '2019-03-05 22:06:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('a16cc512b21c48ee8f1884bc58fda7a8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:09', '2019-03-05 22:06:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9ac583ebd10948a8a2c25bd09ed78386', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:13', '2019-03-05 22:06:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3197ef2fdf5e4f16b1a1fa0d6fcb6f07', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:17', '2019-03-05 22:06:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('51da88e0966c41d3bd142c9a4e4f6c6f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:21', '2019-03-05 22:06:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('42a0b5c10cfc439da5b497781494a08a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:25', '2019-03-05 22:06:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('899b7696afa843128f49c60e5ead192b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:29', '2019-03-05 22:06:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('5b3b884bc3f64199882cebd4b2dbc68b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:33', '2019-03-05 22:06:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('45993abdd7ba4cb29b8c28b60240a782', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:37', '2019-03-05 22:06:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8c6f8208397046c79b2d357b580af1a6', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:41', '2019-03-05 22:06:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b4be46e914344892a39674b66ef4b4f1', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:45', '2019-03-05 22:06:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1e692ceac09e4bb3a3d1b05eba3452ec', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:49', '2019-03-05 22:06:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8cbf0f132d834f60bb8fa050c205ac2d', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:53', '2019-03-05 22:06:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f1c8d0bab46c4bb0a22f9c51e0ecb015', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:57', '2019-03-05 22:06:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e62881a1d1d9403094e2c19a615d1fab', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:01', '2019-03-05 22:07:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('28c433e1a6a14806a98b8999d8897ed4', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:05', '2019-03-05 22:07:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('5bf698a478064e2ab9e2f421d9d0c47d', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:09', '2019-03-05 22:07:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('7010d33046ce430abfc0c997efc2d501', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:13', '2019-03-05 22:07:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e6598a9df4184e4295de3c92734c32be', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:17', '2019-03-05 22:07:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('65303510222f49d3a073701d2aea7a51', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:21', '2019-03-05 22:07:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('12011787ee324f118400ca36ceee8ab8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:25', '2019-03-05 22:07:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e6acc197939e4ad3a5a46c82a40ac68f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:29', '2019-03-05 22:07:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('45aa3b595b9a4ef888ac8d596306455c', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:33', '2019-03-05 22:07:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1beefe34a45f4252890bcfa03b4aafe4', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:37', '2019-03-05 22:07:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('5097c2af553f40888d27a0ce73531c31', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:41', '2019-03-05 22:07:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('18d8e79038d845f1b42b48b5923e9f66', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:45', '2019-03-05 22:07:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('a96511bd08cf4721a2f63f75212e46f2', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:49', '2019-03-05 22:07:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d2f812066f99420b81c46acabb84010b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:53', '2019-03-05 22:07:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('954846aea1d6474c82270a3133777d1d', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:57', '2019-03-05 22:07:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('42b522a637434fb8858d7b202e30ff62', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:01', '2019-03-05 22:08:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('61d83078e82f499a9d711932d933e640', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:05', '2019-03-05 22:08:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c7312cc96f984e4690f915aa171de101', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:09', '2019-03-05 22:08:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b8aaed2c01e442e5b9c365525c267cc1', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:13', '2019-03-05 22:08:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('70544946561048f091e222242f3046aa', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:17', '2019-03-05 22:08:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9d14370559c64bee8a2c505e2c8f307f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:21', '2019-03-05 22:08:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b2800177a207429ea59adee733f19a0d', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:25', '2019-03-05 22:08:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4853b4efe470478981a110e18c86226b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:29', '2019-03-05 22:08:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f526d008d91b4e5f8a067b20857533ab', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:33', '2019-03-05 22:08:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('aba934386ea84859816bd8b8d01d35b8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:37', '2019-03-05 22:08:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('750da7934ed44c90aab22c2ff03a7124', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:41', '2019-03-05 22:08:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('97dd4469ef6f49439579d2b65c6034a7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:45', '2019-03-05 22:08:46', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2e3b81e3a88d4cfa821656d6def3818b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:50', '2019-03-05 22:08:50', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3929086db207407db064198df3763e63', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:53', '2019-03-05 22:08:54', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('fec6adb9c32646338b50baa56ccbb8bc', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:08:58', '2019-03-05 22:08:58', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4cf42f39b2194f7fba7b0caaa2c95136', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:02', '2019-03-05 22:09:02', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('47f7148b7e8741cd8c6d2d41c4808b88', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:06', '2019-03-05 22:09:06', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('50d4c824f8974458912c11012c35e6cc', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:10', '2019-03-05 22:09:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b5946fe2a8ee45a0b9ce3587d8752641', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:13', '2019-03-05 22:09:14', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c186cc0676134f0485d6d86df8d70b3b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:17', '2019-03-05 22:09:18', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('98a2ee5f5f5048d8bc297c6bc76d8c6a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:21', '2019-03-05 22:09:22', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3183cf7ba0f74812a4b50076cb61f532', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:25', '2019-03-05 22:09:26', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6635f958017a4bc892801468e368b50e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:09:29', '2019-03-05 22:09:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6183854e7d2b4059be5dc56ffe93ef0e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:14', '2019-03-06 22:13:14', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('75cc9f83a6be4e6589cc6cda2c591b18', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:43', '2019-03-06 22:13:43', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e0dc5f5abf494c468f3c100b8e34bf07', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:47', '2019-03-06 22:13:47', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c6cf5ca5dcb14bdb8f1b74eb5f508fd5', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:51', '2019-03-06 22:13:51', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f1477c6fb60847208e93a19a00af1326', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:55', '2019-03-06 22:13:55', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('952c5cc5cb8e4350ab991e57e6feca45', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:13:59', '2019-03-06 22:13:59', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b6cc54cafa1e4785b27472d55b8b41da', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:03', '2019-03-06 22:14:03', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8fdde69d052248919e4754d1ef16c222', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:06', '2019-03-06 22:14:06', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('93ba8c87a5b942dc8d568dd4a6f74688', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:10', '2019-03-06 22:14:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('bb60c6172bb74cad8cc51ce1c51ad087', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:15', '2019-03-06 22:14:15', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('04655ec358e4402293c2977dd7a86536', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:18', '2019-03-06 22:14:19', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ebb9848c790840ca872fb92f7e5c3260', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:22', '2019-03-06 22:14:22', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8e4e8b7f3b544282913e4f9ed378fa9a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:26', '2019-03-06 22:14:26', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('843f6d32336942f8bf142f56923c7e82', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:30', '2019-03-06 22:14:30', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('75a731c194a7481fa3edf4367e53485a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:34', '2019-03-06 22:14:34', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9d06df4e363f4096b5028885d82bbb13', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:38', '2019-03-06 22:14:38', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('44d3c0c3c1434e6582efc9cf004d2e16', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:41', '2019-03-06 22:14:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ac97e2309f8e4011ab86eb9121996365', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:45', '2019-03-06 22:14:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('08fb4e24a1cd4ffe8b770721f88960a9', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:49', '2019-03-06 22:14:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e474a938a5df4f648b91d56ea7baeeed', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:53', '2019-03-06 22:14:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3e78f9e7374046688424ed4edfcd41ed', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:14:57', '2019-03-06 22:14:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('721939bce5c34c82b47473345b50bfc7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:01', '2019-03-06 22:15:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('99fe4b76ffd54d2aab71b822ce464d51', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:04', '2019-03-06 22:15:04', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('cdea855616fa4ff992209be9272d94c4', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:08', '2019-03-06 22:15:08', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('13c3dc61142747fc877fcb7f637e594c', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:12', '2019-03-06 22:15:12', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c8842802e03b47ebb174392b00c7dfac', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:16', '2019-03-06 22:15:16', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2765b0ccbe3c4b5db3c7c13d0f395f04', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:20', '2019-03-06 22:15:20', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1c56df4402124d848e75d50d4051ff2b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:24', '2019-03-06 22:15:24', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('906e7c00e5894028953f7a72b7393237', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:27', '2019-03-06 22:15:27', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ea910ff2251e4625a614963ac7a168cf', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:31', '2019-03-06 22:15:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e9ccaa52452c4960a026f2319c0b059b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:35', '2019-03-06 22:15:35', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8bfcb439583140e583d2d670b77a3dec', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:15:39', '2019-03-06 22:15:39', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3241fcf38b4948a483be3d97ad5b6662', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:24', '2019-03-06 22:30:24', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d8086911d6c648b0919ee9ecca46f120', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:28', '2019-03-06 22:30:28', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2b20a5178b0641d99d6f62c0a68f7ae9', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:33', '2019-03-06 22:30:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('079f0e7b0f254c98825ff23ed2ab4c1e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:37', '2019-03-06 22:30:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0f282eeb63aa4d2e9aeb76cd6fc67a72', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:41', '2019-03-06 22:30:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6e37436a567b400f84ee8c426ba96fe3', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:44', '2019-03-06 22:30:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('275b799299014a9195c313ed4ae90ba8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:49', '2019-03-06 22:30:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('57e58fca66cd446e899b6626ed347c52', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:52', '2019-03-06 22:30:52', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3a6a3f259849454c8ca4849d7ec83972', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:30:57', '2019-03-06 22:30:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4fde86af8b94498c85c01c9c45024856', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:01', '2019-03-06 22:31:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6d891fb4c5364bc58f3ae2c467a01a5b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:05', '2019-03-06 22:31:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8e7de592f386410b9a5914d2fae12fcd', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:09', '2019-03-06 22:31:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8e8289ccda134f959973ffaba0122e08', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:13', '2019-03-06 22:31:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('93f6afb0c55b4ec6917df5cda7e16f62', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:17', '2019-03-06 22:31:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('66ab21fcc2f44de5a04624a22fda495b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:21', '2019-03-06 22:31:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('18cdf385d88747cfa1cc4ac3c62445bc', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:25', '2019-03-06 22:31:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3fd716faaa6c40608225f4425cb501be', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:29', '2019-03-06 22:31:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('125c4001c7f14410965883a65ce4ed2a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:33', '2019-03-06 22:31:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('4e1a7d2a918e491f8ef85e87eda25335', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:37', '2019-03-06 22:31:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('bce89e95a37041a29c5d607d761ef536', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:40', '2019-03-06 22:31:40', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1ee210336e3c4879bd42f478f3754641', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:44', '2019-03-06 22:31:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3297df304f3a47d682a16ec98038e1b0', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:49', '2019-03-06 22:31:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('93d4c788e4664edbb679e965861a22ba', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:53', '2019-03-06 22:31:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('843bfb8e335846fca2cb0c3f32920a6f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:31:57', '2019-03-06 22:31:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3beca5f2e41b46908fbde97c33eaa831', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:01', '2019-03-06 22:32:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('bd16f823a8dc4bd38514f314649f8ef7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:05', '2019-03-06 22:32:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('af45b64ae0384a6f8c6e2b265dde611a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:09', '2019-03-06 22:32:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('ce76403bee24440e9cb903ae37a7b5cb', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:13', '2019-03-06 22:32:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('069abb47c91342bd985af3a5648f211b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:17', '2019-03-06 22:32:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1c8f9b0bdc7e435eb47da29a8b2afc9c', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:22', '2019-03-06 22:32:22', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('03ebcdf109b34f07ad38a0598378321d', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:32:26', '2019-03-06 22:32:26', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('43e76ce17f5143d48c6801fa3b5c8dc1', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-06 22:42:12', '2019-03-06 22:42:12', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6b3f1b6c6dfb4580a368f4f3998a142c', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:54:30', '2019-03-12 17:54:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('54ee2fb317dd493d96eaeba2769e64a5', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:55:35', '2019-03-12 17:55:35', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('91b82d94c1e04113b857f90951b456e0', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:57:59', '2019-03-12 17:57:59', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('026a163c862f4dac83165e90151f7c79', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:37', '2019-03-12 17:58:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('11326c3fc06243098022d779f6c0d8a7', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:40', '2019-03-12 17:58:40', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c9becbd333384b67bafb5b56dbc9eab8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:42', '2019-03-12 17:58:42', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b83c1064219042c090ef40110ba2491e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:45', '2019-03-12 17:58:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('7059c2238fff47df8f16c75e3f97a789', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:47', '2019-03-12 17:58:48', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8ec69ced6f714aeca3b0ab9927d3f813', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:50', '2019-03-12 17:58:50', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1a9affbadbf64666a38e984225fce155', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:53', '2019-03-12 17:58:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e6e030164ac64e15a6c257c071b53bda', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:55', '2019-03-12 17:58:55', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3336258cc07e4ca9ad6c115b58b9261a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:58', '2019-03-12 17:58:58', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('a0c7aa47dd494ec1838ec4f3a1eba988', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:00', '2019-03-12 17:59:00', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f42471958f1c4c359d6783fb859aeab6', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:03', '2019-03-12 17:59:03', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b35612bcf1b5432c810628c0a0e1526e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:05', '2019-03-12 17:59:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e98d6f57875e48afbf0b375f4dc35315', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:08', '2019-03-12 17:59:08', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c0d6b7951b4f4100ac303f6f3affdc63', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:10', '2019-03-12 17:59:11', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('49ab1ea395b7426bac8e51628dad58d4', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:13', '2019-03-12 17:59:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('37f313622b5c49f4b5440bc72dbea5d6', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:16', '2019-03-12 17:59:16', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f831781f799b4b4eac333bb5643b2319', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:18', '2019-03-12 17:59:18', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('00752a7a4a2c4791a750ab8978a1fa3b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:21', '2019-03-12 17:59:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9a0d604899d7457fb125d276bee2411e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:23', '2019-03-12 17:59:23', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('cb56b368d00e43d8b997ab4f7216180e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:26', '2019-03-12 17:59:26', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('29f4911d72584506ad4707e35420e558', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:28', '2019-03-12 17:59:28', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2b6e5d413f164b0ba0c0136359319990', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:31', '2019-03-12 17:59:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0471ca0d29ea43948775f8a5e6373c7f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:33', '2019-03-12 17:59:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('02a4ba653449428b9ae524bc18f6b62f', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:36', '2019-03-12 17:59:36', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('64807ce348194c1ea7c8c4f026246fea', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:38', '2019-03-12 17:59:39', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e27ea04e415545f7914ffc92904cc51a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:41', '2019-03-12 17:59:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('47bbcc8db62342a3825c7947ed4352ad', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:44', '2019-03-12 17:59:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('9e7f1189073f41209b82f1b1da3f118a', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:46', '2019-03-12 17:59:46', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('5e2b224e6d9e4df9a94e7c8cd2a1f481', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:49', '2019-03-12 17:59:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('e4ede2a0379a4bf58093d36bfd5a0540', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:51', '2019-03-12 17:59:51', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c7869f7243b144528b0ab074914f4098', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:54', '2019-03-12 17:59:54', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6452d773517f4c33a8fe8ab11f6403e8', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 20:56:57', '2019-03-12 20:56:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('0e96bb474ea94a0d9bb4b521171b35d0', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 21:13:49', '2019-03-12 21:13:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('6684e3130b80480dbd3213c495a7d381', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 21:52:38', '2019-03-12 21:52:38', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('bb1ec5f021954291b84fa5a210fd9fb9', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 21:52:45', '2019-03-12 21:52:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('047c12d000664529bb009d3a82708471', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:11:08', '2019-03-12 22:11:08', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2e4de02e80b84302a398fd656a876d94', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:11:28', '2019-03-12 22:11:28', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f50efa7aa8644fe1a63e74aa22de35c6', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:18:23', '2019-03-12 22:18:23', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('8ae601987ba44304bbbaeba719edb464', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:18:30', '2019-03-12 22:18:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('3f7989e2221644e2a2684dedce47c0ef', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:21:24', '2019-03-12 22:21:24', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1c2617be4f5a4924a97d77da458bcb9e', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:21:34', '2019-03-12 22:21:34', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('2433859a3a1549b188caf8ed1855f6b2', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:21:48', '2019-03-12 22:21:48', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('1cf10c14d6574081b567737d195dd91b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 22:22:11', '2019-03-12 22:22:11', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('cd86cafee74e4424a686748a0a93db32', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 23:20:09', '2019-03-12 23:20:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('c4cf032127eb443ca9997f1b9c4bc348', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 23:20:15', '2019-03-12 23:20:15', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('42762c3c382842cf87996bbc39106cfc', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 23:23:44', '2019-03-12 23:23:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('5313cd6e8ed344b1ac4c64299de0e8bb', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 23:47:27', '2019-03-12 23:47:28', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('af163f63a85147eca7ba5e3ebf53e192', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 23:47:34', '2019-03-12 23:47:34', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('383ffecc95e74094a2c0dfe8069cc6bd', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 21:23:08', '2019-03-13 21:23:08', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('a8ce92ade0e246468319eb6d3f8c0920', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:09:17', '2019-03-13 22:09:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d0e0820ca0c04004990f0fce444a3ae5', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:09:24', '2019-03-13 22:09:24', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('f48659d234894b4c8133f6c7573ec4ed', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:14:01', '2019-03-13 22:14:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b1f60ec5fbf64c438537e803cab04304', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:36:35', '2019-03-13 22:36:35', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('dfc498518fc144519a69b5f2f29bf4e1', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:36:40', '2019-03-13 22:36:40', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d35f52593bde46798ef3920633de775b', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:44:48', '2019-03-13 22:44:48', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('d8e2b20e399a437f9fca33306b7ebb62', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:45:25', '2019-03-13 22:45:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('06b5aa62265c46f1a523b133da63f342', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 22:47:53', '2019-03-13 22:47:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('b2f532fd109c4d41982468cb04da7295', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
INSERT INTO `dispatch_batch_job_history` VALUES ('75894e75d88545d18463f27a8234b598', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_job_status
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_job_status`;
CREATE TABLE `dispatch_batch_job_status` (
  `batch_id` varchar(66) DEFAULT NULL,
  `job_id` varchar(200) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `suite_key` varchar(99) DEFAULT NULL,
  `job_key` varchar(99) DEFAULT NULL,
  `as_of_date` datetime DEFAULT NULL,
  KEY `fk_dispatch_batch_job_status01_idx` (`batch_id`),
  CONSTRAINT `fk_dispatch_batch_job_status01` FOREIGN KEY (`batch_id`) REFERENCES `dispatch_batch_define` (`batch_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_job_status
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:37', '2019-03-05 22:05:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-01 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:41', '2019-03-05 22:05:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-02 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:45', '2019-03-05 22:05:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-03 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:49', '2019-03-05 22:05:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-04 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:53', '2019-03-05 22:05:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-05 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:05:57', '2019-03-05 22:05:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-06 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:01', '2019-03-05 22:06:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-07 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:05', '2019-03-05 22:06:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-08 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:09', '2019-03-05 22:06:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-09 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:13', '2019-03-05 22:06:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-10 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:17', '2019-03-05 22:06:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-11 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:21', '2019-03-05 22:06:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-12 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:25', '2019-03-05 22:06:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-13 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:29', '2019-03-05 22:06:29', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-14 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:33', '2019-03-05 22:06:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-15 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:37', '2019-03-05 22:06:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-16 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:41', '2019-03-05 22:06:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-17 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:45', '2019-03-05 22:06:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-18 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:49', '2019-03-05 22:06:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-19 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:53', '2019-03-05 22:06:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-20 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:06:57', '2019-03-05 22:06:57', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-21 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:01', '2019-03-05 22:07:01', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-22 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:05', '2019-03-05 22:07:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-23 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:09', '2019-03-05 22:07:09', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-24 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:13', '2019-03-05 22:07:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-25 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:17', '2019-03-05 22:07:17', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-26 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:21', '2019-03-05 22:07:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-27 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-05 22:07:25', '2019-03-05 22:07:25', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-02-28 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:37', '2019-03-12 17:58:37', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-01 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:40', '2019-03-12 17:58:40', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-02 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:42', '2019-03-12 17:58:42', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-03 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:45', '2019-03-12 17:58:45', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-04 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:47', '2019-03-12 17:58:48', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-05 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:50', '2019-03-12 17:58:50', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-06 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:53', '2019-03-12 17:58:53', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-07 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:55', '2019-03-12 17:58:55', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-08 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:58:58', '2019-03-12 17:58:58', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-09 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:00', '2019-03-12 17:59:00', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-10 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:03', '2019-03-12 17:59:03', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-11 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:05', '2019-03-12 17:59:05', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-12 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:08', '2019-03-12 17:59:08', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-13 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:10', '2019-03-12 17:59:11', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-14 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:13', '2019-03-12 17:59:13', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-15 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:16', '2019-03-12 17:59:16', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-16 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:18', '2019-03-12 17:59:18', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-17 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:21', '2019-03-12 17:59:21', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-18 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:23', '2019-03-12 17:59:23', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-19 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:26', '2019-03-12 17:59:26', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-20 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:28', '2019-03-12 17:59:28', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-21 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:31', '2019-03-12 17:59:31', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-22 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:33', '2019-03-12 17:59:33', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-23 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:36', '2019-03-12 17:59:36', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-24 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:38', '2019-03-12 17:59:39', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-25 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:41', '2019-03-12 17:59:41', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-26 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:44', '2019-03-12 17:59:44', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-27 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:46', '2019-03-12 17:59:46', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-28 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:49', '2019-03-12 17:59:49', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-29 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-12 17:59:51', '2019-03-12 17:59:51', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-30 00:00:00');
INSERT INTO `dispatch_batch_job_status` VALUES ('YmlnZGF0YR53aXNyYx5CMDAwMDE=', 'NDAyNWU4OGI4YzViNGVjY2FiM2E5MTFhMDZjZjM3ZmEed2lzcmMeYjYxNmU1MjdjMWZkNGVmYmE4MGYxOTJiMzNjNzQ1YTA=', '2', '2019-03-13 23:36:10', '2019-03-13 23:36:10', '4025e88b8c5b4eccab3a911a06cf37fa', 'b616e527c1fd4efba80f192b33c745a0', '2019-03-31 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_pagging_attr
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_pagging_attr`;
CREATE TABLE `dispatch_batch_pagging_attr` (
  `pagging_freq_mult` char(5) NOT NULL,
  `pagging_freq_mult_desc` char(2) DEFAULT NULL,
  PRIMARY KEY (`pagging_freq_mult`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_pagging_attr
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('D', '日');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('H', '小时');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('M', '月');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('MI', '分钟');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('Q', '季');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('W', '周');
INSERT INTO `dispatch_batch_pagging_attr` VALUES ('Y', '年');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_status_attr
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_status_attr`;
CREATE TABLE `dispatch_batch_status_attr` (
  `batch_status` char(1) NOT NULL,
  `batch_status_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`batch_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_status_attr
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_status_attr` VALUES ('0', '初始化');
INSERT INTO `dispatch_batch_status_attr` VALUES ('1', '运行中');
INSERT INTO `dispatch_batch_status_attr` VALUES ('2', '已完成');
INSERT INTO `dispatch_batch_status_attr` VALUES ('3', '错误');
INSERT INTO `dispatch_batch_status_attr` VALUES ('4', '停止');
INSERT INTO `dispatch_batch_status_attr` VALUES ('5', '未定义');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_batch_system_config
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_batch_system_config`;
CREATE TABLE `dispatch_batch_system_config` (
  `config_id` varchar(30) NOT NULL,
  `config_desc` varchar(200) DEFAULT NULL,
  `config_value` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_batch_system_config
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_batch_system_config` VALUES ('CONF0001', '脚本路径', 'C:\\Users\\hzwy23\\Desktop\\output', '/images/dispatch_icon/icon_script.png', '脚本的基准地址，所有的脚本文件必须放在这个基础目录下面，\n如将脚本基准路径设置为/opt/asofdate/etl/script，那么etl调度\n系统所有的脚本必须放在这个目录中，在任务配置中，填写脚本名称时，不需要\n填写脚本基准路径，只需要填写相对于基础路径的地址。');
INSERT INTO `dispatch_batch_system_config` VALUES ('CONF0002', 'Redis开关', 'true', '/images/dispatch_icon/redis_switch.png', 'Redis开关参数，用来开启或关闭系统中Redis服务。批次调度中，默认将任务状态存储在内存中，在集群模式中，需要将任务状态保存在Redis中，所以，单机环境中，可以不使用Redis，批次调度系统也能正常工作，但是集群环境中必须开启Redis');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_group_argument_rel
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_group_argument_rel`;
CREATE TABLE `dispatch_group_argument_rel` (
  `uuid` varchar(66) NOT NULL,
  `job_key` varchar(66) DEFAULT NULL,
  `arg_id` varchar(66) DEFAULT NULL,
  `arg_value` varchar(100) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_dispatch_group_argument_01_idx` (`job_key`),
  CONSTRAINT `fk_dispatch_group_argument_01` FOREIGN KEY (`job_key`) REFERENCES `dispatch_group_task_relation` (`job_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_group_define
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_group_define`;
CREATE TABLE `dispatch_group_define` (
  `group_id` varchar(66) NOT NULL,
  `code_number` varchar(30) DEFAULT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `modify_user` varchar(30) DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_group_define
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_group_define` VALUES ('YmlnZGF0YR53aXNyYx5HMDAwMDAx', 'G000001', '测试任务组', 'admin', '2019-03-03', 'admin', '2019-03-03', 'bigdata');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_group_dependency
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_group_dependency`;
CREATE TABLE `dispatch_group_dependency` (
  `uuid` varchar(66) NOT NULL,
  `suite_key` varchar(66) DEFAULT NULL,
  `up_suite_key` varchar(66) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_dispatch_group_dependency_01_idx` (`suite_key`),
  KEY `fk_dispatch_group_dependency_02_idx` (`up_suite_key`),
  CONSTRAINT `fk_dispatch_group_dependency_01` FOREIGN KEY (`suite_key`) REFERENCES `dispatch_batch_group_relation` (`suite_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dispatch_group_dependency_02` FOREIGN KEY (`up_suite_key`) REFERENCES `dispatch_batch_group_relation` (`suite_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_group_task_relation
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_group_task_relation`;
CREATE TABLE `dispatch_group_task_relation` (
  `job_key` varchar(66) NOT NULL,
  `group_id` varchar(66) DEFAULT NULL,
  `task_id` varchar(66) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`job_key`),
  KEY `fk_dispatch_group_task_001_idx` (`group_id`),
  KEY `fk_dispatch_group_task_002_idx` (`task_id`),
  CONSTRAINT `fk_dispatch_group_task_001` FOREIGN KEY (`group_id`) REFERENCES `dispatch_group_define` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dispatch_group_task_002` FOREIGN KEY (`task_id`) REFERENCES `dispatch_task_define` (`task_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_group_task_relation
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_group_task_relation` VALUES ('b616e527c1fd4efba80f192b33c745a0', 'YmlnZGF0YR53aXNyYx5HMDAwMDAx', 'YmlnZGF0YR53aXNyYx5UMDAwMDAx', 'bigdata');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_job_execute_history
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_job_execute_history`;
CREATE TABLE `dispatch_job_execute_history` (
  `job_id` varchar(160) DEFAULT NULL,
  `message` text,
  `exec_time` datetime DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL,
  `sid` varchar(66) DEFAULT NULL,
  `batch_id` varchar(66) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_job_execute_log
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_job_execute_log`;
CREATE TABLE `dispatch_job_execute_log` (
  `job_id` varchar(500) DEFAULT NULL,
  `message` text,
  `exec_time` datetime DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL,
  `batch_id` varchar(66) DEFAULT NULL,
  `as_of_date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_task_argument_rel
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_task_argument_rel`;
CREATE TABLE `dispatch_task_argument_rel` (
  `uuid` varchar(66) NOT NULL,
  `task_id` varchar(66) DEFAULT NULL,
  `arg_id` varchar(66) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  `arg_value` varchar(100) DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_dispatch_task_argument_001_idx` (`task_id`),
  KEY `fk_dispatch_task_argument_002_idx` (`arg_id`),
  CONSTRAINT `fk_dispatch_task_argument_001` FOREIGN KEY (`task_id`) REFERENCES `dispatch_task_define` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dispatch_task_argument_002` FOREIGN KEY (`arg_id`) REFERENCES `dispatch_argument_define` (`arg_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_task_argument_rel
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_task_argument_rel` VALUES ('2104ce633db711e99eb900163e06afad', 'YmlnZGF0YR53aXNyYx5UMDAwMDAx', 'YmlnZGF0YR53aXNyYx5hc19vZl9kYXRl', 'bigdata', '', 1);
INSERT INTO `dispatch_task_argument_rel` VALUES ('263ee31c3db711e99eb900163e06afad', 'YmlnZGF0YR53aXNyYx5UMDAwMDAx', 'YmlnZGF0YR53aXNyYx50YXNrX2lk', 'bigdata', '123', 2);
COMMIT;

-- ----------------------------
-- Table structure for dispatch_task_define
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_task_define`;
CREATE TABLE `dispatch_task_define` (
  `task_id` varchar(66) NOT NULL,
  `code_number` varchar(30) DEFAULT NULL,
  `task_desc` varchar(200) DEFAULT NULL,
  `task_type` char(1) DEFAULT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `modify_user` varchar(30) DEFAULT NULL,
  `domain_id` varchar(30) DEFAULT NULL,
  `script_file` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_task_define
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_task_define` VALUES ('YmlnZGF0YR53aXNyYx5UMDAwMDAx', 'T000001', '数据写入测试', '2', 'admin', '2019-03-03', '2019-03-03', 'admin', 'bigdata', 'etl.test_demo');
COMMIT;

-- ----------------------------
-- Table structure for dispatch_task_dependency
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_task_dependency`;
CREATE TABLE `dispatch_task_dependency` (
  `uuid` varchar(66) NOT NULL,
  `job_key` varchar(66) DEFAULT NULL,
  `up_job_key` varchar(66) DEFAULT NULL,
  `domain_id` varchar(66) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_dispatch_group_task_01_idx` (`job_key`),
  KEY `fk_dispatch_group_task_02_idx` (`up_job_key`),
  CONSTRAINT `fk_dispatch_group_task_01` FOREIGN KEY (`job_key`) REFERENCES `dispatch_group_task_relation` (`job_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dispatch_group_task_02` FOREIGN KEY (`up_job_key`) REFERENCES `dispatch_group_task_relation` (`job_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dispatch_task_type_attr
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_task_type_attr`;
CREATE TABLE `dispatch_task_type_attr` (
  `task_type` char(1) NOT NULL,
  `task_type_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`task_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch_task_type_attr
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_task_type_attr` VALUES ('1', 'shell 脚本');
INSERT INTO `dispatch_task_type_attr` VALUES ('2', '存储过程');
INSERT INTO `dispatch_task_type_attr` VALUES ('3', 'CMD 脚本');
INSERT INTO `dispatch_task_type_attr` VALUES ('4', 'Jar 包');
INSERT INTO `dispatch_task_type_attr` VALUES ('5', '二进制程序');
COMMIT;

-- ----------------------------
-- Table structure for sys_domain_authorization
-- ----------------------------
DROP TABLE IF EXISTS `sys_domain_authorization`;
CREATE TABLE `sys_domain_authorization` (
  `uuid` varchar(66) NOT NULL,
  `domain_id` varchar(30) NOT NULL,
  `user_id` varchar(30) NOT NULL,
  `authorization_level` char(1) NOT NULL,
  `create_user` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `modify_user` varchar(30) DEFAULT NULL,
  `default_domain` int(11) DEFAULT NULL COMMENT '1: 默认项目\\n0：非默认项目',
  PRIMARY KEY (`uuid`),
  KEY `fk_sys_domain_share_info_01_idx` (`domain_id`),
  KEY `fk_sys_user_001` (`user_id`),
  CONSTRAINT `fk_sys_domain_share_info_01` FOREIGN KEY (`domain_id`) REFERENCES `sys_domain_info` (`domain_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_user_001` FOREIGN KEY (`user_id`) REFERENCES `sys_user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_domain_authorization
-- ----------------------------
BEGIN;
INSERT INTO `sys_domain_authorization` VALUES ('599677553ae148bc9897023613aadead', 'bigdata', 'demo', '2', 'demo', '2019-03-05', '2019-03-05', 'demo', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_domain_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_domain_info`;
CREATE TABLE `sys_domain_info` (
  `domain_id` varchar(30) NOT NULL,
  `domain_name` varchar(300) NOT NULL,
  `domain_status_id` char(1) NOT NULL,
  `domain_create_date` datetime NOT NULL,
  `domain_maintance_date` datetime DEFAULT NULL,
  `domain_maintance_user` varchar(30) DEFAULT NULL,
  `domain_owner` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`domain_id`),
  KEY `fk_sys_idx_05` (`domain_status_id`),
  CONSTRAINT `fk_sys_idx_05` FOREIGN KEY (`domain_status_id`) REFERENCES `sys_domain_status_attr` (`domain_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='域管理';

-- ----------------------------
-- Records of sys_domain_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_domain_info` VALUES ('bigdata', '大数据仓库项目', '0', '2019-03-02 21:25:23', '2019-03-02 21:31:40', 'admin', 'admin');
INSERT INTO `sys_domain_info` VALUES ('demoProject', '演示项目', '0', '2019-03-05 23:17:15', '2019-03-05 23:17:15', 'admin', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_domain_status_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_domain_status_attr`;
CREATE TABLE `sys_domain_status_attr` (
  `domain_status_id` char(1) NOT NULL,
  `domain_status_name` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`domain_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_domain_status_attr
-- ----------------------------
BEGIN;
INSERT INTO `sys_domain_status_attr` VALUES ('0', '正常');
INSERT INTO `sys_domain_status_attr` VALUES ('1', '锁定');
INSERT INTO `sys_domain_status_attr` VALUES ('2', '失效');
COMMIT;

-- ----------------------------
-- Table structure for sys_handle_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_handle_logs`;
CREATE TABLE `sys_handle_logs` (
  `uuid` varchar(60) NOT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  `handle_time` datetime DEFAULT NULL,
  `client_ip` varchar(30) DEFAULT NULL,
  `status_code` varchar(10) DEFAULT NULL,
  `method` varchar(45) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `data` longtext,
  `domain_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_index_page
-- ----------------------------
DROP TABLE IF EXISTS `sys_index_page`;
CREATE TABLE `sys_index_page` (
  `theme_id` varchar(30) NOT NULL,
  `res_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`theme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_index_page
-- ----------------------------
BEGIN;
INSERT INTO `sys_index_page` VALUES ('1001', './views/hauth/theme/default/index.tpl');
INSERT INTO `sys_index_page` VALUES ('1002', './views/hauth/theme/blue/index.tpl');
INSERT INTO `sys_index_page` VALUES ('1003', './views/hauth/theme/apple/index.tpl');
INSERT INTO `sys_index_page` VALUES ('1004', './views/hauth/theme/cyan/index.tpl');
COMMIT;

-- ----------------------------
-- Table structure for sys_org_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_info`;
CREATE TABLE `sys_org_info` (
  `org_unit_id` varchar(66) NOT NULL,
  `org_unit_desc` varchar(300) NOT NULL,
  `up_org_id` varchar(66) NOT NULL,
  `create_date` date NOT NULL,
  `maintance_date` date NOT NULL,
  `create_user` varchar(30) NOT NULL,
  `maintance_user` varchar(30) NOT NULL,
  `code_number` varchar(66) NOT NULL,
  PRIMARY KEY (`org_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_org_info` VALUES ('100000', '中国工商银行', 'root_vertex_system', '2019-03-02', '2019-03-02', 'admin', 'admin', '100000');
INSERT INTO `sys_org_info` VALUES ('101000', '中国工商银行湖北省分行', '100000', '2019-03-02', '2019-03-02', 'admin', 'admin', '101000');
INSERT INTO `sys_org_info` VALUES ('102000', '中国工商银行上海市分行', '100000', '2019-03-02', '2019-03-02', 'admin', 'admin', '102000');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_info`;
CREATE TABLE `sys_resource_info` (
  `res_id` varchar(30) NOT NULL,
  `res_name` varchar(300) DEFAULT NULL,
  `res_attr` char(1) DEFAULT NULL,
  `res_up_id` varchar(30) DEFAULT NULL,
  `res_type` char(1) DEFAULT NULL,
  `sys_flag` char(1) DEFAULT NULL,
  PRIMARY KEY (`res_id`),
  KEY `fk_sys_idx_13` (`res_type`),
  KEY `fk_sys_idx_14` (`res_attr`),
  CONSTRAINT `fk_sys_idx_13` FOREIGN KEY (`res_type`) REFERENCES `sys_resource_type_attr` (`res_type`),
  CONSTRAINT `fk_sys_idx_14` FOREIGN KEY (`res_attr`) REFERENCES `sys_resource_info_attr` (`res_attr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource_info` VALUES ('0100000000', '系统管理', '0', '-1', '0', '0');
INSERT INTO `sys_resource_info` VALUES ('0101000000', '系统审计', '0', '0100000000', '4', '0');
INSERT INTO `sys_resource_info` VALUES ('0101010000', '操作查询', '1', '0101000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0101010100', '查看操作日志权限', '1', '0101010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0101010200', '下载操作日志按钮', '1', '0101010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0101010300', '搜索日志信息按钮', '1', '0101010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103000000', '资源管理', '0', '0100000000', '4', '0');
INSERT INTO `sys_resource_info` VALUES ('0103010000', '菜单', '1', '0103000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0103010100', '查询资源信息', '1', '0103010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103010200', '新增资源信息按钮', '1', '0103010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103010300', '编辑资源信息按钮', '1', '0103010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103010400', '删除资源信息按钮', '1', '0103010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('01030104001', '删除资源信息按钮', '1', '0101010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103010500', '配置主题信息按钮', '1', '0103010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103020000', '组织', '1', '0103000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0103020100', '查询组织架构信息', '1', '0103020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103020200', '新增组织架构信息按钮', '1', '0103020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103020300', '更新组织架构信息按钮', '1', '0103020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103020400', '删除组织架构信息按钮', '1', '0103020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103020500', '导出组织架构信息按钮', '1', '0103020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103030100', '查询共享域信息', '1', '0104010200', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103030200', '新增共享域信息按钮', '1', '0104010200', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103030300', '删除共享域信息按钮', '1', '0104010200', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0103030400', '更新共享域信息按钮', '1', '0104010200', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0104010000', '项目定义', '1', '1801000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0104010100', '查询域信息', '1', '0104010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0104010200', '共享域管理', '1', '0104010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0104010300', '编辑域信息按钮', '1', '0104010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0104010400', '删除域信息按钮', '1', '0104010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0104010500', '新增域信息按钮', '1', '0104010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105000000', '用户与安全管理', '0', '0100000000', '4', '0');
INSERT INTO `sys_resource_info` VALUES ('0105010000', '用户', '1', '0105000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0105010100', '查询用户信息', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105010200', '新增用户信息按钮', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105010300', '编辑用户信息按钮', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105010400', '删除用户信息按钮', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105010500', '修改用户密码按钮', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105010600', '修改用户状态按钮', '1', '0105010000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020000', '角色', '1', '0105000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0105020100', '查询角色信息', '1', '0105020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020200', '新增角色信息按钮', '1', '0105020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020300', '更新角色信息按钮', '1', '0105020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020400', '删除角色信息按钮', '1', '0105020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020500', '角色资源管理', '1', '0105020000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020510', '查询角色资源信息', '1', '0105020500', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105020520', '修改角色资源信息', '1', '0105020500', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105040000', '角色授权', '1', '0105000000', '1', '0');
INSERT INTO `sys_resource_info` VALUES ('0105040100', '授予权限按钮', '1', '0105040000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0105040200', '移除权限', '1', '0105040000', '2', NULL);
INSERT INTO `sys_resource_info` VALUES ('0500000000', '批次调度系统', '0', '-1', '0', NULL);
INSERT INTO `sys_resource_info` VALUES ('0501000000', '调度参数配置', '0', '0500000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('0501010000', '任务参数定义', '1', '0501000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0501020000', '调度核心参数管理', '1', '0501000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0502000000', '任务与任务组配置', '0', '0500000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('0502010000', '任务定义', '1', '0502000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0502020000', '任务组定义', '1', '0502000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0503000000', '批次配置管理', '0', '0500000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('0503010000', '批次定义', '1', '0503000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0503020000', '批次监控', '1', '0503000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('0503030000', '批次历史信息', '1', '0503000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('1100000000', '系统帮助', '0', '-1', '0', NULL);
INSERT INTO `sys_resource_info` VALUES ('1101000000', '系统管理帮助', '0', '1100000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('1101010000', '系统维护帮助信息', '1', '1101000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('1101020000', 'API文档', '1', '1101000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('1102000000', '管理会计帮助文档', '0', '1100000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('1600000000', 'DataX管理', '0', '-1', '0', NULL);
INSERT INTO `sys_resource_info` VALUES ('1800000000', '项目管理', '0', '-1', '0', NULL);
INSERT INTO `sys_resource_info` VALUES ('1801000000', '项目管理', '0', '1800000000', '4', NULL);
INSERT INTO `sys_resource_info` VALUES ('1801010000', '项目授权', '1', '1802000000', '1', NULL);
INSERT INTO `sys_resource_info` VALUES ('1802000000', '授权管理', '0', '1800000000', '4', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource_info_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_info_attr`;
CREATE TABLE `sys_resource_info_attr` (
  `res_attr` char(1) NOT NULL,
  `res_attr_desc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`res_attr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_info_attr
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource_info_attr` VALUES ('0', '目录');
INSERT INTO `sys_resource_info_attr` VALUES ('1', '叶子');
COMMIT;

-- ----------------------------
-- Table structure for sys_resource_type_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_type_attr`;
CREATE TABLE `sys_resource_type_attr` (
  `res_type` char(1) NOT NULL,
  `res_type_desc` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`res_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource_type_attr
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource_type_attr` VALUES ('0', '首页菜单');
INSERT INTO `sys_resource_type_attr` VALUES ('1', '子系统菜单');
INSERT INTO `sys_resource_type_attr` VALUES ('2', '功能按钮');
INSERT INTO `sys_resource_type_attr` VALUES ('4', '虚拟节点');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_info`;
CREATE TABLE `sys_role_info` (
  `role_id` varchar(66) NOT NULL,
  `role_name` varchar(300) NOT NULL,
  `role_owner` varchar(30) NOT NULL,
  `role_create_date` datetime NOT NULL,
  `role_status_id` char(1) NOT NULL,
  `role_maintance_date` datetime NOT NULL,
  `role_maintance_user` varchar(30) NOT NULL,
  `code_number` varchar(66) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `fk_sys_idx_11` (`role_status_id`),
  CONSTRAINT `fk_sys_idx_11` FOREIGN KEY (`role_status_id`) REFERENCES `sys_role_status_attr` (`role_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_info` VALUES ('R0001', '总行科技中心系统管理员', 'admin', '2019-03-02 21:35:23', '0', '2019-03-02 21:36:27', 'admin', 'R0001');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `uuid` varchar(160) NOT NULL DEFAULT 'uuid()',
  `role_id` varchar(66) DEFAULT NULL,
  `res_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_sys_idx_06` (`res_id`),
  KEY `fk_sys_role_res_01_idx` (`role_id`),
  CONSTRAINT `fk_sys_idx_06` FOREIGN KEY (`res_id`) REFERENCES `sys_resource_info` (`res_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_role_res_01` FOREIGN KEY (`role_id`) REFERENCES `sys_role_info` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMDAwMDAwMA==', 'R0001', '0100000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMTAwMDAwMA==', 'R0001', '0101000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMTAxMDAwMA==', 'R0001', '0101010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMTAxMDEwMA==', 'R0001', '0101010100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMTAxMDIwMA==', 'R0001', '0101010200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMTAxMDMwMA==', 'R0001', '0101010300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAwMDAwMA==', 'R0001', '0103000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDAwMA==', 'R0001', '0103010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDEwMA==', 'R0001', '0103010100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDIwMA==', 'R0001', '0103010200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDMwMA==', 'R0001', '0103010300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDQwMA==', 'R0001', '0103010400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDQwMDE=', 'R0001', '01030104001');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAxMDUwMA==', 'R0001', '0103010500');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDAwMA==', 'R0001', '0103020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDEwMA==', 'R0001', '0103020100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDIwMA==', 'R0001', '0103020200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDMwMA==', 'R0001', '0103020300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDQwMA==', 'R0001', '0103020400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAyMDUwMA==', 'R0001', '0103020500');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAzMDEwMA==', 'R0001', '0103030100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAzMDIwMA==', 'R0001', '0103030200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAzMDMwMA==', 'R0001', '0103030300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwMzAzMDQwMA==', 'R0001', '0103030400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDAwMA==', 'R0001', '0104010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDEwMA==', 'R0001', '0104010100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDIwMA==', 'R0001', '0104010200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDMwMA==', 'R0001', '0104010300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDQwMA==', 'R0001', '0104010400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNDAxMDUwMA==', 'R0001', '0104010500');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTA0MDAwMA==', 'R0001', '0105040000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTA0MDEwMA==', 'R0001', '0105040100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTA0MDIwMA==', 'R0001', '0105040200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAwMDAwMA==', 'R0001', '0105000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDAwMA==', 'R0001', '0105010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDEwMA==', 'R0001', '0105010100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDIwMA==', 'R0001', '0105010200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDMwMA==', 'R0001', '0105010300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDQwMA==', 'R0001', '0105010400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDUwMA==', 'R0001', '0105010500');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAxMDYwMA==', 'R0001', '0105010600');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDAwMA==', 'R0001', '0105020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDEwMA==', 'R0001', '0105020100');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDIwMA==', 'R0001', '0105020200');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDMwMA==', 'R0001', '0105020300');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDQwMA==', 'R0001', '0105020400');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDUwMA==', 'R0001', '0105020500');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDUxMA==', 'R0001', '0105020510');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDEwNTAyMDUyMA==', 'R0001', '0105020520');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMDAwMDAwMA==', 'R0001', '0500000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMjAwMDAwMA==', 'R0001', '0502000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMjAxMDAwMA==', 'R0001', '0502010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMjAyMDAwMA==', 'R0001', '0502020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMTAwMDAwMA==', 'R0001', '0501000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMTAxMDAwMA==', 'R0001', '0501010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMTAyMDAwMA==', 'R0001', '0501020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMzAwMDAwMA==', 'R0001', '0503000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMzAxMDAwMA==', 'R0001', '0503010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMzAyMDAwMA==', 'R0001', '0503020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMDUwMzAzMDAwMA==', 'R0001', '0503030000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTEwMDAwMDAwMA==', 'R0001', '1100000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTEwMjAwMDAwMA==', 'R0001', '1102000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTEwMTAwMDAwMA==', 'R0001', '1101000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTEwMTAxMDAwMA==', 'R0001', '1101010000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTEwMTAyMDAwMA==', 'R0001', '1101020000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTgwMDAwMDAwMA==', 'R0001', '1800000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTgwMjAwMDAwMA==', 'R0001', '1802000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTgwMTAwMDAwMA==', 'R0001', '1801000000');
INSERT INTO `sys_role_resource` VALUES ('UjAwMDEed2lzcmMeMTgwMTAxMDAwMA==', 'R0001', '1801010000');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_status_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_status_attr`;
CREATE TABLE `sys_role_status_attr` (
  `role_status_id` char(1) NOT NULL,
  `role_status_desc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`role_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_status_attr
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_status_attr` VALUES ('0', '正常');
INSERT INTO `sys_role_status_attr` VALUES ('1', '锁定');
INSERT INTO `sys_role_status_attr` VALUES ('2', '失效');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `uuid` varchar(60) NOT NULL,
  `role_id` varchar(66) DEFAULT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  `maintance_date` date DEFAULT NULL,
  `maintance_user` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `fk_sys_idx_03` (`user_id`),
  KEY `fk_sys_role_user_01_idx` (`role_id`),
  CONSTRAINT `fk_sys_idx_03` FOREIGN KEY (`user_id`) REFERENCES `sys_user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_role_user_01` FOREIGN KEY (`role_id`) REFERENCES `sys_role_info` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES ('ZGVtbx53aXNyYx5SMDAwMQ==', 'R0001', 'demo', '2019-03-05', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_sec_user`;
CREATE TABLE `sys_sec_user` (
  `user_id` varchar(30) NOT NULL,
  `user_passwd` varchar(30) DEFAULT NULL,
  `status_id` char(1) DEFAULT NULL,
  `continue_error_cnt` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_sys_idx_02` (`status_id`),
  CONSTRAINT `fk_sys_idx_01` FOREIGN KEY (`user_id`) REFERENCES `sys_user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_idx_02` FOREIGN KEY (`status_id`) REFERENCES `sys_user_status_attr` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_sec_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_sec_user` VALUES ('admin', 'CguSVgQY2Df4LxG0UT/xwA==', '0', 0);
INSERT INTO `sys_sec_user` VALUES ('demo', 'CguSVgQY2Df4LxG0UT/xwA==', '0', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_theme_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_theme_info`;
CREATE TABLE `sys_theme_info` (
  `theme_id` varchar(30) NOT NULL,
  `theme_desc` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`theme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_theme_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_theme_info` VALUES ('1001', '绿色主题');
INSERT INTO `sys_theme_info` VALUES ('1002', '深蓝主题');
INSERT INTO `sys_theme_info` VALUES ('1003', '粉色主题');
INSERT INTO `sys_theme_info` VALUES ('1004', '青色主题');
COMMIT;

-- ----------------------------
-- Table structure for sys_theme_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_theme_value`;
CREATE TABLE `sys_theme_value` (
  `uuid` varchar(60) NOT NULL,
  `theme_id` varchar(30) DEFAULT NULL,
  `res_id` varchar(30) DEFAULT NULL,
  `res_url` varchar(120) DEFAULT NULL,
  `res_type` varchar(5) DEFAULT NULL,
  `res_bg_color` varchar(30) DEFAULT NULL,
  `res_class` varchar(90) DEFAULT NULL,
  `group_id` char(1) DEFAULT NULL,
  `res_img` varchar(200) DEFAULT NULL,
  `sort_id` decimal(10,0) DEFAULT NULL,
  `new_iframe` varchar(5) DEFAULT NULL,
  KEY `pk_sys_theme_value_01` (`uuid`),
  KEY `fk_sys_theme_value01_idx` (`res_id`),
  CONSTRAINT `fk_sys_theme_value01` FOREIGN KEY (`res_id`) REFERENCES `sys_resource_info` (`res_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_theme_value
-- ----------------------------
BEGIN;
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0101010000', '/v1/auth/HandleLogsPage', '0', '#336699', 'tile tile-large', '3', '/static/images/hauth/logs_shen.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010000', '/v1/auth/resource/page', '0', '#666699', 'tile', '1', '/static/images/hauth/menus.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010000', '/v1/auth/domain/page', '0', '#0099CC', 'tile tile-wide', '1', '/static/images/hauth/domain.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010000', '/v1/auth/user/page', '0', '#CC6600', 'tile tile-wide', '2', '/static/images/hauth/user_manager.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020000', '/v1/auth/role/page', '0', '#FFCC33', 'tile', '2', '/static/images/hauth/role_manager.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0100000000', './views/hauth/theme/default/sysconfig.tpl', '0', '#FF6600', 'tile tile-wide', '1', '/static/images/hauth/system.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105040000', '/v1/auth/batch/page', '0', '#339999', 'tile', '2', '/static/images/hauth/grant.png', 4, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020000', '/v1/auth/resource/org/page', '0', '#FF6666', 'tile', '1', '/static/images/hauth/org.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '1100000000', './views/help/default/syshelp.tpl', '0', '#0099CC', 'tile tile-wide', '3', '/static/images/hauth/help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0101010000', '/v1/auth/HandleLogsPage', '0', '#336699', 'tile tile-large', '3', '/static/images/hauth/logs_shen.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010100', '/v1/auth/domain/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010200', '/v1/auth/domain/share/page', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010300', '/v1/auth/domain/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010400', '/v1/auth/domain/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0104010500', '/v1/auth/domain/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103030100', '/v1/auth/domain/share/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103030200', '/v1/auth/domain/share/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103030300', '/v1/auth/domain/share/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103030400', '/v1/auth/domain/share/put', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0101010200', '/v1/auth/handle/logs/download', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0101010300', '/v1/auth/handle/logs/search', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020100', '/v1/auth/resource/org/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020200', '/v1/auth/resource/org/insert', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020300', '/v1/auth/resource/org/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020400', '/v1/auth/resource/org/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103020500', '/v1/auth/resource/org/download', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010100', '/v1/auth/resource/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010200', '/v1/auth/resource/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010300', '/v1/auth/resource/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010400', '/v1/auth/resource/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0103010500', '/v1/auth/resource/config/theme', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010100', '/v1/auth/user/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010200', '/v1/auth/user/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010300', '/v1/auth/user/put', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010400', '/v1/auth/user/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010500', '/v1/auth/user/modify/passwd', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105010600', '/v1/auth/user/modify/status', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020100', '/v1/auth/role/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020200', '/v1/auth/role/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020300', '/v1/auth/role/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020400', '/v1/auth/role/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020500', '/v1/auth/role/resource/details', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020510', '/v1/auth/role/resource/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105020520', '/v1/auth/role/resource/rights', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105040100', '/v1/auth/user/roles/auth', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0105040200', '/v1/auth/user/roles/revoke', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '1101010000', '/v1/help/system/help', '0', '#339999', 'tile tile-wide', '1', '/static/images/hauth/sys_help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0101010000', '/v1/auth/HandleLogsPage', '0', '#336699', 'tile tile-large', '3', '/static/images/hauth/logs_shen.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010000', '/v1/auth/resource/page', '0', '#666699', 'tile tile-wide', '1', '/static/images/hauth/menus.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010000', '/v1/auth/domain/page', '0', '#0099CC', 'tile tile-wide', '1', '/static/images/hauth/domain.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010000', '/v1/auth/user/page', '0', '#CC6600', 'tile tile-wide', '2', '/static/images/hauth/user_manager.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020000', '/v1/auth/role/page', '0', '#FFCC33', 'tile', '2', '/static/images/hauth/role_manager.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0100000000', './views/hauth/theme/cyan/sysconfig.tpl', '0', '#FF6600', 'tile tile-wide', '1', '/static/images/hauth/system.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105040000', '/v1/auth/batch/page', '0', '#339999', 'tile', '2', '/static/images/hauth/grant.png', 4, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020000', '/v1/auth/resource/org/page', '0', '#FF6666', 'tile tile-wide', '1', '/static/images/hauth/org.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1100000000', './views/help/default/syshelp.tpl', '0', '#0099CC', 'tile tile-wide', '3', '/static/images/hauth/help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010100', '/v1/auth/domain/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010200', '/v1/auth/domain/share/page', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010300', '/v1/auth/domain/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010400', '/v1/auth/domain/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0104010500', '/v1/auth/domain/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103030100', '/v1/auth/domain/share/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103030200', '/v1/auth/domain/share/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103030300', '/v1/auth/domain/share/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103030400', '/v1/auth/domain/share/put', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0101010200', '/v1/auth/handle/logs/download', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0101010300', '/v1/auth/handle/logs/search', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020100', '/v1/auth/resource/org/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020200', '/v1/auth/resource/org/insert', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020300', '/v1/auth/resource/org/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020400', '/v1/auth/resource/org/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103020500', '/v1/auth/resource/org/download', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010100', '/v1/auth/resource/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010200', '/v1/auth/resource/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010300', '/v1/auth/resource/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010400', '/v1/auth/resource/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0103010500', '/v1/auth/resource/config/theme', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010100', '/v1/auth/user/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010200', '/v1/auth/user/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010300', '/v1/auth/user/put', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010400', '/v1/auth/user/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010500', '/v1/auth/user/modify/passwd', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105010600', '/v1/auth/user/modify/status', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020100', '/v1/auth/role/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020200', '/v1/auth/role/post', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020300', '/v1/auth/role/update', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020400', '/v1/auth/role/delete', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020500', '/v1/auth/role/resource/details', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020510', '/v1/auth/role/resource/get', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105020520', '/v1/auth/role/resource/rights', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105040100', '/v1/auth/user/roles/auth', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0105040200', '/v1/auth/user/roles/revoke', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1101010000', '/v1/help/system/help', '0', '#339999', 'tile tile-wide', '1', '/static/images/hauth/sys_help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0101010100', '/v1/auth/handle/logs', '0', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0101010100', '/v1/auth/handle/logs', '0', '', 'tile tile-large', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '1101020000', '/swagger-ui.html', '1', '#339999', 'tile tile-wide', '2', '/static/images/hauth/api.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1101020000', '/swagger-ui.html', '1', '#339999', 'tile tile-wide', '2', '/static/images/hauth/api.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '01030104001', '/v1/auth/resource/org/page', '', '', '', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0100000000', './views/hauth/theme/blue/sysconfig.tpl', '0', '#FF6600', 'tile tile-wide', '1', '/static/images/hauth/system.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0101010100', '/v1/auth/handle/logs', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0101010200', '/v1/auth/handle/logs/download', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0101010300', '/v1/auth/handle/logs/search', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '01030104001', '/v1/auth/resource/org/page', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010000', '/v1/auth/resource/page', '0', '#666699', 'tile', '1', '/static/images/hauth/menus.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010100', '/v1/auth/resource/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010200', '/v1/auth/resource/post', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010300', '/v1/auth/resource/update', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010400', '/v1/auth/resource/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103010500', '/v1/auth/resource/config/theme', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020100', '/v1/auth/resource/org/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020200', '/v1/auth/resource/org/insert', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020300', '/v1/auth/resource/org/update', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020400', '/v1/auth/resource/org/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020500', '/v1/auth/resource/org/download', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103020000', '/v1/auth/resource/org/page', '0', '#FF6666', 'tile', '1', '/static/images/hauth/org.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010000', '/v1/auth/domain/page', '0', '#0099CC', 'tile tile-wide', '1', '/static/images/hauth/domain.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010100', '/v1/auth/domain/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010200', '/v1/auth/domain/share/page', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103030100', '/v1/auth/domain/share/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103030200', '/v1/auth/domain/share/post', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103030300', '/v1/auth/domain/share/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0103030400', '/v1/auth/domain/share/put', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010300', '/v1/auth/domain/update', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010400', '/v1/auth/domain/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0104010500', '/v1/auth/domain/post', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010100', '/v1/auth/user/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010200', '/v1/auth/user/post', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010300', '/v1/auth/user/put', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010400', '/v1/auth/user/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010500', '/v1/auth/user/modify/passwd', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010600', '/v1/auth/user/modify/status', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020100', '/v1/auth/role/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020000', '/v1/auth/role/page', '0', '#FFCC33', 'tile', '2', '/static/images/hauth/role_manager.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020200', '/v1/auth/role/post', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020300', '/v1/auth/role/update', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020400', '/v1/auth/role/delete', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020500', '/v1/auth/role/resource/details', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020510', '/v1/auth/role/resource/get', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105020520', '/v1/auth/role/resource/rights', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105040000', '/v1/auth/batch/page', '0', '#339999', 'tile', '2', '/static/images/hauth/grant.png', 4, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105040100', '/v1/auth/user/roles/auth', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105040200', '/v1/auth/user/roles/revoke', '0', '', 'tile', '', '', 0, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0105010000', '/v1/auth/user/page', '0', '#CC6600', 'tile tile-wide', '2', '/static/images/hauth/user_manager.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '1100000000', './views/help/default/syshelp.tpl', '0', '#0099CC', 'tile tile-wide', '3', '/static/images/hauth/help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '1101010000', '/v1/help/system/help', '0', '#339999', 'tile tile-wide', '1', '/static/images/hauth/sys_help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '1101020000', '/swagger-ui.html', '1', '#339999', 'tile tile-wide', '2', '/static/images/hauth/api.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0100000000', './views/hauth/theme/apple/sysconfig.tpl', '0', '#FF6600', 'tile tile-wide', '1', '/static/images/hauth/system.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0101010000', '/v1/auth/HandleLogsPage', '0', '#336699', 'tile tile-large', '3', '/static/images/hauth/logs_shen.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0103010000', '/v1/auth/resource/page', '0', '#666699', 'tile', '1', '/static/images/hauth/menus.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0103020000', '/v1/auth/resource/org/page', '0', '#FF6666', 'tile', '1', '/static/images/hauth/org.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0104010000', '/v1/auth/domain/page', '0', '#0099CC', 'tile tile-wide', '1', '/static/images/hauth/domain.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0105010000', '/v1/auth/user/page', '0', '#CC6600', 'tile tile-wide', '2', '/static/images/hauth/user_manager.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0105020000', '/v1/auth/role/page', '0', '#FFCC33', 'tile', '2', '/static/images/hauth/role_manager.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0105040000', '/v1/auth/batch/page', '0', '#339999', 'tile', '2', '/static/images/hauth/grant.png', 4, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '1100000000', './views/help/default/syshelp.tpl', '0', '#0099CC', 'tile tile-wide', '3', '/static/images/hauth/help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '1101010000', '/v1/help/system/help', '0', '#339999', 'tile tile-wide', '1', '/static/images/hauth/sys_help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '1101020000', '/swagger-ui.html', '1', '#339999', 'tile tile-wide', '2', '/static/images/hauth/api.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0500000000', './views/dispatch/index.tpl', '0', '#009966', 'tile tile-wide', '2', '/static/images/dispatch_icon/etl.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0500000000', './views/dispatch/index.tpl', '0', '#009966', 'tile tile-wide', '2', '/static/images/dispatch_icon/etl.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0500000000', './views/dispatch/index.tpl', '0', '#009966', 'tile tile-wide', '2', '/static/images/dispatch_icon/etl.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0500000000', './views/dispatch/index.tpl', '0', '#009966', 'tile tile-wide', '2', '/static/images/dispatch_icon/etl.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0501010000', '/v1/dispatch/argument/page', '0', '#339999', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_define.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0502010000', '/v1/dispatch/task/page', '0', '#666699', 'tile tile-wide', '2', '/static/images/dispatch_icon/task.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0502020000', '/v1/dispatch/group/page', '0', '#CC6633', 'tile tile-wide', '2', '/static/images/dispatch_icon/group.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0503010000', '/v1/dispatch/batch/page', '0', '#009966', 'tile tile-wide', '3', '/static/images/dispatch_icon/batch.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0501010000', '/v1/dispatch/argument/page', '0', '#339999', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_define.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0501010000', '/v1/dispatch/argument/page', '0', '#339999', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_define.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0501010000', '/v1/dispatch/argument/page', '0', '#339999', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_define.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0502010000', '/v1/dispatch/task/page', '0', '#666699', 'tile tile-wide', '2', '/static/images/dispatch_icon/task.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0502010000', '/v1/dispatch/task/page', '0', '#666699', 'tile tile-wide', '2', '/static/images/dispatch_icon/task.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0502010000', '/v1/dispatch/task/page', '0', '#666699', 'tile tile-wide', '2', '/static/images/dispatch_icon/task.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0502020000', '/v1/dispatch/group/page', '0', '#CC6633', 'tile tile-wide', '2', '/static/images/dispatch_icon/group.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0502020000', '/v1/dispatch/group/page', '0', '#CC6633', 'tile tile-wide', '2', '/static/images/dispatch_icon/group.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0502020000', '/v1/dispatch/group/page', '0', '#CC6633', 'tile tile-wide', '2', '/static/images/dispatch_icon/group.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0503010000', '/v1/dispatch/batch/page', '0', '#009966', 'tile tile-wide', '3', '/static/images/dispatch_icon/batch.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0503010000', '/v1/dispatch/batch/page', '0', '#009966', 'tile tile-wide', '3', '/static/images/dispatch_icon/batch.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0503010000', '/v1/dispatch/batch/page', '0', '#009966', 'tile tile-wide', '3', '/static/images/dispatch_icon/batch.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0503020000', '/v1/dispatch/monitoring/page', '0', '#0099CC', 'tile', '3', '/static/images/dispatch_icon/monitoring.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0503020000', '/v1/dispatch/monitoring/page', '0', '#0099CC', 'tile', '3', '/static/images/dispatch_icon/monitoring.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0503020000', '/v1/dispatch/monitoring/page', '0', '#0099CC', 'tile', '3', '/static/images/dispatch_icon/monitoring.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0503020000', '/v1/dispatch/monitoring/page', '0', '#0099CC', 'tile', '3', '/static/images/dispatch_icon/monitoring.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0501020000', '/v1/dispatch/system/config/page', '0', '#336699', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_system.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0501020000', '/v1/dispatch/system/config/page', '0', '#336699', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_system.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0501020000', '/v1/dispatch/system/config/page', '0', '#336699', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_system.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0501020000', '/v1/dispatch/system/config/page', '0', '#336699', 'tile tile-wide', '1', '/static/images/dispatch_icon/arg_system.png', 2, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1001', '0503030000', '/v1/dispatch/batch/history', '0', '#FF6666', 'tile', '3', '/static/images/hauth/logs_shen.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1002', '0503030000', '/v1/dispatch/batch/history', '0', '#FF6666', 'tile', '3', '/static/images/hauth/logs_shen.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1003', '0503030000', '/v1/dispatch/batch/history', '0', '#FF6666', 'tile', '3', '/static/images/hauth/logs_shen.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '0503030000', '/v1/dispatch/batch/history', '0', '#FF6666', 'tile', '3', '/static/images/hauth/logs_shen.png', 3, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1600000000', './views/hauth/theme/default/sysconfig.tpl', '0', '#339999', 'tile tile-wide', '2', '/static/images/hauth/sys_help.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1800000000', './views/project/index.tpl', '0', '#339999', 'tile tile-wide', '1', '/static/images/dispatch_icon/group.png', 1, 'false');
INSERT INTO `sys_theme_value` VALUES ('975cd9523ce711e99eb900163e06afad', '1004', '1801010000', '/v1/auth/grant/domain/page', '0', '#339999', 'tile tile-wide', '2', '/static/images/hauth/menus.png', 1, 'false');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(300) DEFAULT NULL,
  `user_create_date` datetime DEFAULT NULL,
  `user_owner` varchar(30) DEFAULT NULL,
  `user_email` varchar(30) DEFAULT NULL,
  `user_phone` decimal(15,0) DEFAULT NULL,
  `org_unit_id` varchar(66) DEFAULT NULL,
  `user_maintance_date` datetime DEFAULT NULL,
  `user_maintance_user` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_sys_user_org_idx` (`org_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_info` VALUES ('admin', '超级管理员', '2016-01-01 00:00:00', 'sys', 'hzwy23@sina.com', 15684851256, '512345423', '2016-12-19 13:34:32', 'sys');
INSERT INTO `sys_user_info` VALUES ('demo', '演示用户', '2019-03-05 23:43:33', 'admin', 'hzwy23@sina.com', 18581530028, '101000', '2019-03-05 23:43:33', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_status_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_status_attr`;
CREATE TABLE `sys_user_status_attr` (
  `status_id` char(1) NOT NULL,
  `status_desc` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_status_attr
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_status_attr` VALUES ('0', '正常');
INSERT INTO `sys_user_status_attr` VALUES ('1', '锁定');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_theme
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_theme`;
CREATE TABLE `sys_user_theme` (
  `user_id` varchar(30) NOT NULL,
  `theme_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `pk_sys_user_theme_01` FOREIGN KEY (`user_id`) REFERENCES `sys_user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_theme
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_theme` VALUES ('admin', '1004');
INSERT INTO `sys_user_theme` VALUES ('demo', '1004');
COMMIT;

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table` (
  `code` varchar(30) DEFAULT NULL,
  `value` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of test_table
-- ----------------------------
BEGIN;
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-01 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-02 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-03 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-04 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-05 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-06 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-07 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-08 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-09 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-10 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-11 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-12 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-13 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-14 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-15 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-16 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-17 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-18 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-19 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-20 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-21 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-22 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-23 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-24 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-25 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-26 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-27 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-28 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-29 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-30 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
INSERT INTO `test_table` VALUES ('1', '2019-03-31 00:00:00');
INSERT INTO `test_table` VALUES ('1', '123');
COMMIT;

-- ----------------------------
-- Procedure structure for test_demo
-- ----------------------------
DROP PROCEDURE IF EXISTS `test_demo`;
delimiter ;;
CREATE PROCEDURE `etl`.`test_demo`(in asOfDate varchar(50), in val varchar(50))
BEGIN
	insert into test_table(code, value) values(1,asOfDate);
	insert into test_table(code, value) values(1,val);
    commit;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
