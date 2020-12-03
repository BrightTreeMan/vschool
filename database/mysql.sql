-- --------------------------------------------------------
-- 主机:                           47.105.187.153
-- 服务器版本:                        8.0.18 - MySQL Community Server - GPL
-- 服务器OS:                        linux-glibc2.12
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for school
CREATE DATABASE IF NOT EXISTS `school` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school`;

-- Dumping structure for table school.msh_course
CREATE TABLE IF NOT EXISTS `msh_course` (
  `course_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `course_name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_type` varchar(1) DEFAULT NULL COMMENT '课程类别',
  `charge_type` varchar(1) DEFAULT NULL COMMENT '收费模式',
  `standard_fee` varchar(50) DEFAULT NULL COMMENT '学费标准',
  `teach_type` varchar(1) DEFAULT NULL COMMENT '授课模式',
  `class_num` int(2) DEFAULT NULL COMMENT '开班数',
  `campus` varchar(1) DEFAULT NULL COMMENT '所属校区',
  `add_user_name` varchar(20) DEFAULT NULL,
  `add_date` bigint(14) DEFAULT NULL,
  `mod_user_name` varchar(20) DEFAULT NULL,
  `mod_date` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='课程表';

-- Dumping data for table school.msh_course: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_course` DISABLE KEYS */;
INSERT INTO `msh_course` (`course_id`, `course_name`, `course_type`, `charge_type`, `standard_fee`, `teach_type`, `class_num`, `campus`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES
	(3, '2019秋季五年级语文', '1', '1', '2625元/期(37.5课时)', '1', 2, '1', 'admin', 1579656782477, 'admin', 1579656782477);
/*!40000 ALTER TABLE `msh_course` ENABLE KEYS */;

-- Dumping structure for table school.msh_dept
CREATE TABLE IF NOT EXISTS `msh_dept` (
  `dept_id` int(20) NOT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `parent_dept_id` varchar(20) DEFAULT NULL COMMENT '父级部门ID',
  `list_order` int(1) DEFAULT NULL COMMENT '排序',
  `state` varchar(1) DEFAULT '0' COMMENT '状态 0正常',
  `add_user_name` varchar(200) DEFAULT NULL,
  `add_date` bigint(14) DEFAULT NULL,
  `mod_user_name` varchar(200) DEFAULT NULL,
  `mod_date` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- Dumping data for table school.msh_dept: ~3 rows (大约)
/*!40000 ALTER TABLE `msh_dept` DISABLE KEYS */;
INSERT INTO `msh_dept` (`dept_id`, `dept_name`, `parent_dept_id`, `list_order`, `state`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES
	(0, '总部', NULL, 1, '0', NULL, 1577954484504, 'admin', 1579591523986),
	(100, '财务部', '0', 1, '0', 'admin', 1577954376650, 'admin', 1579591531978),
	(200, '人事部', '0', 2, '0', 'admin', 1577954393340, 'admin', 1579591535605);
/*!40000 ALTER TABLE `msh_dept` ENABLE KEYS */;

-- Dumping structure for table school.msh_login_log
CREATE TABLE IF NOT EXISTS `msh_login_log` (
  `login_log_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login_date` bigint(14) DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(16) DEFAULT NULL COMMENT '登录IP',
  `login_desc` varchar(200) DEFAULT NULL COMMENT '登录详细信息',
  `login_result` varchar(50) DEFAULT NULL COMMENT '登录结果',
  `user_name` varchar(100) DEFAULT NULL COMMENT '登录人',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='登录日志表';


-- Dumping structure for table school.msh_menu
CREATE TABLE IF NOT EXISTS `msh_menu` (
  `menu_id` int(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_level` varchar(1) DEFAULT NULL COMMENT '菜单等级',
  `parent_menu_id` int(20) DEFAULT NULL COMMENT '父级菜单ID',
  `icon_type` varchar(100) DEFAULT NULL COMMENT '字体图标',
  `state` varchar(1) DEFAULT NULL COMMENT '状态 0正常',
  `list_order` varchar(1) DEFAULT NULL COMMENT '排序',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- Dumping data for table school.msh_menu: ~14 rows (大约)
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

-- Dumping structure for table school.msh_priv
CREATE TABLE IF NOT EXISTS `msh_priv` (
  `priv_id` int(20) NOT NULL COMMENT '权限ID',
  `priv_name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(100) DEFAULT NULL COMMENT '路径',
  `state` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`priv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息表';

-- Dumping data for table school.msh_priv: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_priv` DISABLE KEYS */;
INSERT INTO `msh_priv` (`priv_id`, `priv_name`, `url`, `state`) VALUES
	(1, '用户管理', '/user', '0');
/*!40000 ALTER TABLE `msh_priv` ENABLE KEYS */;

-- Dumping structure for table school.msh_role
CREATE TABLE IF NOT EXISTS `msh_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `add_user_name` varchar(20) DEFAULT NULL COMMENT '新增人',
  `add_date` bigint(14) DEFAULT NULL COMMENT '新增时间',
  `mod_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `mod_date` bigint(14) DEFAULT NULL COMMENT '修改时间',
  `state` varchar(1) DEFAULT NULL COMMENT '状态 0正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Dumping data for table school.msh_role: ~1 rows (大约)
/*!40000 ALTER TABLE `msh_role` DISABLE KEYS */;
INSERT INTO `msh_role` (`role_id`, `role_name`, `role_desc`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`, `state`) VALUES
	(1, '管理员', '开发使用，拥有全部权限', 'admin', 20190806, 'admin', 1579591544799, '0');
/*!40000 ALTER TABLE `msh_role` ENABLE KEYS */;

-- Dumping structure for table school.msh_role_menu
CREATE TABLE IF NOT EXISTS `msh_role_menu` (
  `role_menu_id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_menu_id`),
  KEY `FK_Reference_6` (`role_id`),
  KEY `FK_Reference_7` (`menu_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`menu_id`) REFERENCES `msh_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='角色用户对应表';

-- Dumping data for table school.msh_role_menu: ~8 rows (大约)
/*!40000 ALTER TABLE `msh_role_menu` DISABLE KEYS */;
INSERT INTO `msh_role_menu` (`role_menu_id`, `role_id`, `menu_id`) VALUES
	(41, 1, 100100),
	(42, 1, 100101),
	(43, 1, 100102),
	(44, 1, 200100),
	(45, 1, 200101),
	(46, 1, 300100),
	(47, 1, 500100),
	(48, 1, 300101);
/*!40000 ALTER TABLE `msh_role_menu` ENABLE KEYS */;

-- Dumping structure for table school.msh_role_priv
CREATE TABLE IF NOT EXISTS `msh_role_priv` (
  `role_priv_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `priv_id` int(20) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_priv_id`),
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`priv_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`priv_id`) REFERENCES `msh_priv` (`priv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- Dumping data for table school.msh_role_priv: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_role_priv` DISABLE KEYS */;
INSERT INTO `msh_role_priv` (`role_priv_id`, `role_id`, `priv_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `msh_role_priv` ENABLE KEYS */;

-- Dumping structure for table school.msh_stu
CREATE TABLE IF NOT EXISTS `msh_stu` (
  `stu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'ID',
  `stu_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别 0男 1女 2 未识别',
  `class_lvel` int(2) DEFAULT NULL COMMENT '年级 1-6 小学 7-9初中 10-12 高中',
  `source` varchar(1) DEFAULT NULL COMMENT '来源',
  `card_num` bigint(20) DEFAULT NULL COMMENT '磁卡卡号',
  `state` varchar(1) DEFAULT NULL COMMENT '跟进状态',
  `wechat_id` varchar(20) DEFAULT NULL COMMENT '微信号',
  `birthday` bigint(14) DEFAULT NULL COMMENT '生日',
  `public_school` varchar(100) DEFAULT NULL COMMENT '公立学校',
  `public_school_class` varchar(100) DEFAULT NULL COMMENT '公立学校班级',
  `high_school` varchar(100) DEFAULT NULL COMMENT '高中学校',
  `middle_school` varchar(100) DEFAULT NULL COMMENT '初中学校',
  `primary_school` varchar(100) DEFAULT NULL COMMENT '小学学校',
  `add_user_name` varchar(100) DEFAULT NULL,
  `add_date` bigint(14) DEFAULT NULL,
  `mod_user_name` varchar(100) DEFAULT NULL,
  `mod_date` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

-- Dumping data for table school.msh_stu: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu` ENABLE KEYS */;

-- Dumping structure for table school.msh_stu_course
CREATE TABLE IF NOT EXISTS `msh_stu_course` (
  `stu_course_id` int(20) NOT NULL AUTO_INCREMENT,
  `stu_id` int(20) DEFAULT NULL,
  `course_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`stu_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生-课程';

-- Dumping data for table school.msh_stu_course: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu_course` ENABLE KEYS */;

-- Dumping structure for table school.msh_stu_linkman
CREATE TABLE IF NOT EXISTS `msh_stu_linkman` (
  `stu_id` int(20) DEFAULT NULL COMMENT '学生ID',
  `linkman_type` int(1) DEFAULT NULL COMMENT '联系人 0父亲 1 母亲 2其他',
  `linkman_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `linkman_desc` varchar(100) DEFAULT NULL COMMENT '其他联系人称呼',
  `lingman_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '联系人ID 主键',
  PRIMARY KEY (`lingman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生联系人表';

-- Dumping data for table school.msh_stu_linkman: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_stu_linkman` DISABLE KEYS */;
/*!40000 ALTER TABLE `msh_stu_linkman` ENABLE KEYS */;

-- Dumping structure for table school.msh_sys_log
CREATE TABLE IF NOT EXISTS `msh_sys_log` (
  `sys_log_id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(16) DEFAULT NULL COMMENT '操作IP',
  `user_name` varchar(100) DEFAULT NULL COMMENT '操作人ID',
  `operation` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `operation_desc` varchar(200) DEFAULT NULL COMMENT '具体描述',
  `operation_result` varchar(50) DEFAULT NULL COMMENT '操作结果',
  `date` bigint(14) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`sys_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- Dumping data for table school.msh_sys_log: ~14 rows (大约)
/*!40000 ALTER TABLE `msh_sys_log` DISABLE KEYS */;
INSERT INTO `msh_sys_log` (`sys_log_id`, `ip`, `user_name`, `operation`, `operation_desc`, `operation_result`, `date`) VALUES
	(1, '10.0.70.221', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1577954214156),
	(2, '10.0.70.221', 'admin', '新增部门', '用户[admin],新增部门成功,被操作部门ID[100]', '成功', 1577954376681),
	(3, '10.0.70.221', 'admin', '新增部门', '用户[admin],新增部门成功,被操作部门ID[200]', '成功', 1577954393371),
	(4, '10.0.70.221', 'admin', '新增用户', '用户[admin],新增用户成功,被操作用户[root]', '成功', 1577954484566),
	(5, '10.0.70.221', 'admin', '分配角色', '用户[admin],分配角色成功,被操作用户[root]', '成功', 1577954507674),
	(6, '10.0.70.221', 'admin', '冻结用户', '用户[admin],冻结用户成功,被操作用户[root]', '成功', 1578033667045),
	(7, '10.0.70.221', 'admin', '解冻用户', '用户[admin],解冻用户成功,被操作用户[root]', '成功', 1578033740959),
	(8, '10.0.70.221', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1578034975683),
	(9, '0:0:0:0:0:0:0:1', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1579227763000),
	(10, '0:0:0:0:0:0:0:1', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1579229671200),
	(11, '0:0:0:0:0:0:0:1', 'admin', '编辑部门', '用户[admin],编辑部门成功,被操作部门ID[200]', '成功', 1579418787714),
	(12, '0:0:0:0:0:0:0:1', 'admin', '编辑部门', '用户[admin],编辑部门成功,被操作部门ID[200]', '成功', 1579418792771),
	(13, '0:0:0:0:0:0:0:1', 'admin', '修改用户', '用户[admin],修改用户成功,被操作用户[root]', '成功', 1579421942765),
	(14, '10.0.70.221', 'admin', '修改用户', '用户[admin],修改用户成功,被操作用户[admin]', '成功', 1579422341421),
	(15, '10.0.70.221', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1579591216495),
	(16, '10.0.70.221', 'admin', '编辑部门', '用户[admin],编辑部门成功,被操作部门ID[0]', '成功', 1579591524029),
	(17, '10.0.70.221', 'admin', '编辑部门', '用户[admin],编辑部门成功,被操作部门ID[100]', '成功', 1579591532026),
	(18, '10.0.70.221', 'admin', '编辑部门', '用户[admin],编辑部门成功,被操作部门ID[200]', '成功', 1579591535630),
	(19, '10.0.70.221', 'admin', '修改角色', '用户[admin],修改角色成功,被操作角色ID[1]', '成功', 1579591545111),
	(20, '10.0.70.221', 'admin', '修改用户', '用户[admin],修改用户成功,被操作用户[admin]', '成功', 1579591550536),
	(21, '10.0.70.221', 'admin', '修改用户', '用户[admin],修改用户成功,被操作用户[root]', '成功', 1579591553076),
	(22, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579597302786),
	(23, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579653947943),
	(24, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579653994903),
	(25, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579654159065),
	(26, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579654168698),
	(27, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579654272850),
	(28, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579654283233),
	(29, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程失败]', '失败', 1579654290455),
	(30, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程成功,被操作课程ID[1]', '成功', 1579654506195),
	(31, '10.0.70.221', 'admin', '删除课程', '用户[admin],删除课程成功', '成功', 1579656249393),
	(32, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程成功,被操作课程ID[2]', '成功', 1579656259834),
	(33, '10.0.70.221', 'admin', '删除课程', '用户[admin],删除课程成功', '成功', 1579656737453),
	(34, '10.0.70.221', 'admin', '新增课程', '用户[admin],新增课程成功,被操作课程ID[3]', '成功', 1579656782532);
/*!40000 ALTER TABLE `msh_sys_log` ENABLE KEYS */;

-- Dumping structure for table school.msh_sys_param
CREATE TABLE IF NOT EXISTS `msh_sys_param` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_name` varchar(100) DEFAULT NULL COMMENT '码值代码',
  `param_val` varchar(100) DEFAULT NULL COMMENT '码值名称',
  `param_item` varchar(100) DEFAULT NULL COMMENT '码值对应的字段',
  `list_order` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`param_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统码表';

-- Dumping data for table school.msh_sys_param: ~2 rows (大约)
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

-- Dumping structure for table school.msh_user
CREATE TABLE IF NOT EXISTS `msh_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(100) DEFAULT NULL COMMENT '账户ID',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码 密文',
  `salt` varchar(100) DEFAULT NULL COMMENT '加密信息',
  `state` varchar(1) DEFAULT '0' COMMENT '状态 0 正常 1冻结',
  `phone` varchar(13) DEFAULT NULL COMMENT '联系方式',
  `add_user_name` varchar(20) DEFAULT NULL COMMENT '增加人',
  `add_date` bigint(14) DEFAULT NULL COMMENT '增加日期',
  `mod_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `mod_date` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `FK_Reference_5` (`dept_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`dept_id`) REFERENCES `msh_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table school.msh_user: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_user` DISABLE KEYS */;
INSERT INTO `msh_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `password`, `salt`, `state`, `phone`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES (1, 0, 'admin', '管理员', '7b85c8d5d3dbc2c236063dacfb82e1e009d4b9626b7a02f630f9d400b70ec30f', 'LNSLTz3GaqxTUZfdvyYmZA==', '0', '13212011897', 'admin', 1577954484504, 'admin', 1579591550478);
INSERT INTO `msh_user` (`user_id`, `dept_id`, `user_name`, `nick_name`, `password`, `salt`, `state`, `phone`, `add_user_name`, `add_date`, `mod_user_name`, `mod_date`) VALUES (2, 0, 'root', '开发者', '7d253fdf395c2fb68d9943f10291b490392d52d7860e3600684d7829ca5c3708', '5CLBLmdouy77s3z5pXbJew==', '0', '13212011897', 'admin', 1577954484504, 'admin', 1579591553005);
/*!40000 ALTER TABLE `msh_user` ENABLE KEYS */;

-- Dumping structure for table school.msh_user_role
CREATE TABLE IF NOT EXISTS `msh_user_role` (
  `user_role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` int(20) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`user_role_id`),
  KEY `FK_Reference_1` (`role_id`),
  KEY `FK_Reference_2` (`user_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`role_id`) REFERENCES `msh_role` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `msh_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色对应表';

-- Dumping data for table school.msh_user_role: ~0 rows (大约)
/*!40000 ALTER TABLE `msh_user_role` DISABLE KEYS */;
INSERT INTO `msh_user_role` (`user_role_id`, `role_id`, `user_id`) VALUES
	(1, 1, 1),
	(2, 1, 2);
/*!40000 ALTER TABLE `msh_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
