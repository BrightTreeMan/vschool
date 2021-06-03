-- --------------------------------------------------------
-- 主机:                           10.0.251.142
-- 服务器版本:                        8.0.25 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 导出  表 school.msh_course 结构
CREATE TABLE IF NOT EXISTS `msh_course` (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `course_name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_type` varchar(1) DEFAULT NULL COMMENT '课程类别',
  `charge_type` varchar(1) DEFAULT NULL COMMENT '收费模式',
  `standard_fee` varchar(50) DEFAULT NULL COMMENT '学费标准',
  `teach_type` varchar(1) DEFAULT NULL COMMENT '授课模式',
  `class_num` int DEFAULT NULL COMMENT '开班数',
  `campus` varchar(1) DEFAULT NULL COMMENT '所属校区',
  `add_user_name` varchar(20) DEFAULT NULL,
  `add_date` bigint DEFAULT NULL,
  `mod_user_name` varchar(20) DEFAULT NULL,
  `mod_date` bigint DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='课程表';

-- 正在导出表  school.msh_course 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_course` ENABLE KEYS */;

-- 导出  表 school.msh_dept 结构
CREATE TABLE IF NOT EXISTS `msh_dept` (
  `dept_id` int NOT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `parent_dept_id` varchar(20) DEFAULT NULL COMMENT '父级部门ID',
  `list_order` int DEFAULT NULL COMMENT '排序',
  `state` varchar(1) DEFAULT '0' COMMENT '状态 0正常',
  `add_user_name` varchar(200) DEFAULT NULL,
  `add_date` bigint DEFAULT NULL,
  `mod_user_name` varchar(200) DEFAULT NULL,
  `mod_date` bigint DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='部门表';

-- 正在导出表  school.msh_dept 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `msh_dept` DISABLE KEYS */;
INSERT INTO `msh_dept` (`dept_id`, `dept_name`, `parent_dept_id`, `list_order`, `state`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES
	(0, '总部', NULL, 1, '0', NULL, 1577954484504, 'admin', 1579591523986),
	(100, '财务部', '0', 1, '0', 'admin', 1577954376650, 'admin', 1579591531978),
	(200, '人事部', '0', 2, '0', 'admin', 1577954393340, 'admin', 1579591535605);
/*!40000 ALTER TABLE `msh_dept` ENABLE KEYS */;

-- 导出  表 school.msh_login_log 结构
CREATE TABLE IF NOT EXISTS `msh_login_log` (
  `login_log_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login_date` bigint DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(16) DEFAULT NULL COMMENT '登录IP',
  `login_desc` varchar(200) DEFAULT NULL COMMENT '登录详细信息',
  `login_result` varchar(50) DEFAULT NULL COMMENT '登录结果',
  `user_name` varchar(100) DEFAULT NULL COMMENT '登录人',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb3 COMMENT='登录日志表';

-- 正在导出表  school.msh_login_log 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `msh_login_log` DISABLE KEYS */;
INSERT INTO `msh_login_log` (`login_log_id`, `login_date`, `login_ip`, `login_desc`, `login_result`, `user_name`) VALUES
	(88, 1622706995533, '10.0.70.221', '登录成功', '成功', 'admin'),
	(89, 1622707374837, '10.0.70.221', '登录成功', '成功', 'admin'),
	(90, 1622707388259, '10.0.70.221', '登录成功', '成功', 'admin'),
	(91, 1622707479418, '10.0.70.221', '登录成功', '成功', 'admin'),
	(92, 1622707501447, '10.0.70.221', '登录成功', '成功', 'admin'),
	(93, 1622707557292, '10.0.70.221', '登录成功', '成功', 'admin'),
	(94, 1622707573517, '10.0.70.221', '登录成功', '成功', 'admin'),
	(95, 1622707739344, '10.0.70.221', '登录成功', '成功', 'admin'),
	(96, 1622707794075, '10.0.70.221', '登录成功', '成功', 'admin'),
	(97, 1622707817120, '10.0.70.221', '登录成功', '成功', 'admin');
/*!40000 ALTER TABLE `msh_login_log` ENABLE KEYS */;

-- 导出  表 school.msh_menu 结构
CREATE TABLE IF NOT EXISTS `msh_menu` (
  `menu_id` int NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_level` varchar(1) DEFAULT NULL COMMENT '菜单等级',
  `parent_menu_id` int DEFAULT NULL COMMENT '父级菜单ID',
  `icon_type` varchar(100) DEFAULT NULL COMMENT '字体图标',
  `state` varchar(1) DEFAULT NULL COMMENT '状态 0正常',
  `list_order` varchar(1) DEFAULT NULL COMMENT '排序',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='菜单表';

-- 正在导出表  school.msh_menu 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `msh_menu` DISABLE KEYS */;
INSERT INTO `msh_menu` (`menu_id`, `menu_name`, `menu_level`, `parent_menu_id`, `icon_type`, `state`, `list_order`, `url`) VALUES
	(0, '根节点', '1', NULL, NULL, '0', '1', NULL),
	(100, '系统管理', '2', 0, 'el-icon-setting', '0', '1', ''),
	(200, '日志管理', '2', 0, 'el-icon-document', '0', '2', NULL),
	(300, '功能管理', '2', 0, 'el-icon-s-tools', '0', '3', ''),
	(400, '教务中心', '2', 0, 'el-icon-shopping-bag-1', '0', '4', NULL),
	(500, '办理中心', '2', 0, 'el-icon-bank-card', '0', '5', NULL),
	(100100, '用户管理', '3', 100, 'el-icon-s-custom', '0', '1', '/user'),
	(100101, '角色管理', '3', 100, 'el-icon-user', '0', '2', '/role'),
	(100102, '部门管理', '3', 100, 'el-icon-menu', '0', '3', '/dept'),
	(200100, '登录日志', '3', 200, 'el-icon-s-unfold', '0', '1', '/loginLog'),
	(200101, '系统日志', '3', 200, 'el-icon-s-fold', '0', '2', '/sysLog'),
	(300100, '课程管理', '3', 300, 'el-icon-s-order', '0', '1', '/course'),
	(300101, '首页图管理', '3', 300, 'el-icon-picture', '0', '2', '/welcomeManage'),
	(500100, '报名/续费', '3', 500, 'el-icon-shopping-bag-1', '0', '1', '/apply');
/*!40000 ALTER TABLE `msh_menu` ENABLE KEYS */;

-- 导出  表 school.msh_priv 结构
CREATE TABLE IF NOT EXISTS `msh_priv` (
  `priv_id` int NOT NULL COMMENT '权限ID',
  `priv_name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`priv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='权限信息表';

-- 正在导出表  school.msh_priv 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_priv` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_priv` ENABLE KEYS */;

-- 导出  表 school.msh_role 结构
CREATE TABLE IF NOT EXISTS `msh_role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `add_user_name` varchar(20) DEFAULT NULL COMMENT '新增人',
  `add_date` bigint DEFAULT NULL COMMENT '新增时间',
  `mod_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `mod_date` bigint DEFAULT NULL COMMENT '修改时间',
  `state` varchar(1) DEFAULT NULL COMMENT '状态 0正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';

-- 正在导出表  school.msh_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_role` DISABLE KEYS */;
INSERT INTO `msh_role` (`role_id`, `role_name`, `role_desc`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`, `state`) VALUES
	(1, '管理员', '全部权限', 'admin', 1579591544799, 'admin', 1622707848611, '0');
/*!40000 ALTER TABLE `msh_role` ENABLE KEYS */;

-- 导出  表 school.msh_role_menu 结构
CREATE TABLE IF NOT EXISTS `msh_role_menu` (
  `role_menu_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `menu_id` int DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_menu_id`),
  KEY `FK_Reference_6` (`role_id`),
  KEY `FK_Reference_7` (`menu_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`menu_id`) REFERENCES `msh_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3 COMMENT='角色用户对应表';

-- 正在导出表  school.msh_role_menu 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `msh_role_menu` DISABLE KEYS */;
INSERT INTO `msh_role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES
	(49, 1, 100100),
	(50, 1, 200100),
	(51, 1, 200101),
	(52, 1, 100101),
	(53, 1, 100102);
/*!40000 ALTER TABLE `msh_role_menu` ENABLE KEYS */;

-- 导出  表 school.msh_role_priv 结构
CREATE TABLE IF NOT EXISTS `msh_role_priv` (
  `role_priv_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `priv_id` int DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_priv_id`),
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`priv_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`priv_id`) REFERENCES `msh_priv` (`priv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='角色权限表';

-- 正在导出表  school.msh_role_priv 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_role_priv` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_role_priv` ENABLE KEYS */;

-- 导出  表 school.msh_stu 结构
CREATE TABLE IF NOT EXISTS `msh_stu` (
  `stu_id` bigint NOT NULL DEFAULT '0' COMMENT 'ID',
  `stu_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别 0男 1女 2 未识别',
  `class_lvel` int DEFAULT NULL COMMENT '年级 1-6 小学 7-9初中 10-12 高中',
  `source` varchar(1) DEFAULT NULL COMMENT '来源',
  `card_num` bigint DEFAULT NULL COMMENT '磁卡卡号',
  `state` varchar(1) DEFAULT NULL COMMENT '跟进状态',
  `wechat_id` varchar(20) DEFAULT NULL COMMENT '微信号',
  `birthday` bigint DEFAULT NULL COMMENT '生日',
  `public_school` varchar(100) DEFAULT NULL COMMENT '公立学校',
  `public_school_class` varchar(100) DEFAULT NULL COMMENT '公立学校班级',
  `high_school` varchar(100) DEFAULT NULL COMMENT '高中学校',
  `middle_school` varchar(100) DEFAULT NULL COMMENT '初中学校',
  `primary_school` varchar(100) DEFAULT NULL COMMENT '小学学校',
  `add_user_name` varchar(100) DEFAULT NULL,
  `add_date` bigint DEFAULT NULL,
  `mod_user_name` varchar(100) DEFAULT NULL,
  `mod_date` bigint DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生信息表';

-- 正在导出表  school.msh_stu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu` ENABLE KEYS */;

-- 导出  表 school.msh_stu_course 结构
CREATE TABLE IF NOT EXISTS `msh_stu_course` (
  `stu_course_id` int NOT NULL AUTO_INCREMENT,
  `stu_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`stu_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生-课程';

-- 正在导出表  school.msh_stu_course 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu_course` ENABLE KEYS */;

-- 导出  表 school.msh_stu_linkman 结构
CREATE TABLE IF NOT EXISTS `msh_stu_linkman` (
  `stu_id` int DEFAULT NULL COMMENT '学生ID',
  `linkman_type` int DEFAULT NULL COMMENT '联系人 0父亲 1 母亲 2其他',
  `linkman_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `linkman_desc` varchar(100) DEFAULT NULL COMMENT '其他联系人称呼',
  `lingman_id` int NOT NULL AUTO_INCREMENT COMMENT '联系人ID 主键',
  PRIMARY KEY (`lingman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生联系人表';

-- 正在导出表  school.msh_stu_linkman 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu_linkman` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu_linkman` ENABLE KEYS */;

-- 导出  表 school.msh_sys_log 结构
CREATE TABLE IF NOT EXISTS `msh_sys_log` (
  `sys_log_id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(16) DEFAULT NULL COMMENT '操作IP',
  `user_name` varchar(100) DEFAULT NULL COMMENT '操作人ID',
  `operation` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `operation_desc` varchar(200) DEFAULT NULL COMMENT '具体描述',
  `operation_result` varchar(50) DEFAULT NULL COMMENT '操作结果',
  `date` bigint DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`sys_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3 COMMENT='系统日志表';

-- 正在导出表  school.msh_sys_log 的数据：~34 rows (大约)
/*!40000 ALTER TABLE `msh_sys_log` DISABLE KEYS */;
INSERT INTO `msh_sys_log` (`sys_log_id`, `ip`, `user_name`, `operation`, `operation_desc`, `operation_result`, `date`) VALUES
	(35, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户成功,被操作用户[admin]', '成功', 1622706781345),
	(36, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户失败,被操作用户[admin]', '失败', 1622706781475),
	(37, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户成功,被操作用户[admin]', '成功', 1622706813050),
	(38, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户失败,被操作用户[admin]', '失败', 1622706815892),
	(39, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户成功,被操作用户[admin]', '成功', 1622706822134),
	(40, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户失败,被操作用户[admin]', '失败', 1622706822291),
	(41, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户成功,被操作用户[admin]', '成功', 1622706826787),
	(42, '10.0.70.221', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1622707848636);
/*!40000 ALTER TABLE `msh_sys_log` ENABLE KEYS */;

-- 导出  表 school.msh_sys_param 结构
CREATE TABLE IF NOT EXISTS `msh_sys_param` (
  `param_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(100) DEFAULT NULL COMMENT '码值代码',
  `param_val` varchar(100) DEFAULT NULL COMMENT '码值名称',
  `param_item` varchar(100) DEFAULT NULL COMMENT '码值对应的字段',
  `list_order` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COMMENT='系统码表';

-- 正在导出表  school.msh_sys_param 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_sys_param` DISABLE KEYS */;
INSERT INTO `msh_sys_param` (`param_id`, `param_name`, `param_val`, `param_item`, `list_order`) VALUES
	(1, '0', '正常', 'user_state', 1),
	(2, '1', '冻结', 'user_state', 2),
	(3, '1', '河西下瓦房校区', 'course_campus', 1),
	(4, '2', '河西小白楼校区', 'course_campus', 2),
	(5, '3', '河西梅江', 'course_campus', 3),
	(6, '4', '武清前进道校区', 'course_campus', 4),
	(7, '5', '南开海光寺校区', 'course_campus', 5),
	(8, '6', '和平八里台校区', 'course_campus', 6),
	(9, '1', '班课', 'course_teach', 1),
	(10, '2', '一对一', 'course_teach', 2),
	(11, '1', '按期', 'course_charge', 1),
	(12, '1', '班课', 'course_course', 1),
	(13, '2', '一对一', 'course_course', 2);
/*!40000 ALTER TABLE `msh_sys_param` ENABLE KEYS */;

-- 导出  表 school.msh_user 结构
CREATE TABLE IF NOT EXISTS `msh_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '账户ID',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码 密文',
  `salt` varchar(100) DEFAULT NULL COMMENT '加密信息',
  `state` varchar(1) DEFAULT '0' COMMENT '状态 0 正常 1冻结',
  `phone` varchar(13) DEFAULT NULL COMMENT '联系方式',
  `add_user_name` varchar(20) DEFAULT NULL COMMENT '增加人',
  `add_date` bigint DEFAULT NULL COMMENT '增加日期',
  `mod_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `mod_date` bigint DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_5` (`dept_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`dept_id`) REFERENCES `msh_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';

-- 正在导出表  school.msh_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_user` DISABLE KEYS */;
INSERT INTO `msh_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `password`, `salt`, `state`, `phone`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES
	(3, 0, 'admin', '管理员', '1042991cdaca720384c98ce8a1ec54de6021126437a4610bb8292ae732388527', 'B8TnM84z1qqrQ3fUoRkz1Q==', '0', '13212011897', 'admin', 1622702455827, 'admin', 1622702455827);
/*!40000 ALTER TABLE `msh_user` ENABLE KEYS */;

-- 导出  表 school.msh_user_role 结构
CREATE TABLE IF NOT EXISTS `msh_user_role` (
  `user_role_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int DEFAULT NULL COMMENT '角色ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`user_role_id`),
  KEY `FK_Reference_1` (`role_id`),
  KEY `FK_Reference_2` (`user_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `msh_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='用户角色对应表';

-- 正在导出表  school.msh_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `msh_user_role` DISABLE KEYS */;
INSERT INTO `msh_user_role` (`user_role_id`, `role_id`, `user_id`) VALUES
	(1, 1, 3);
/*!40000 ALTER TABLE `msh_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
