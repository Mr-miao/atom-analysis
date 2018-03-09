/*
Navicat MySQL Data Transfer

Source Server         : ATOM
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : analysis

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-09 13:53:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_branch
-- ----------------------------
DROP TABLE IF EXISTS `t_branch`;
CREATE TABLE `t_branch` (
  `BRANCH_ID` int(11) NOT NULL,
  `BRANCH_NO` varchar(10) DEFAULT NULL,
  `IF_LEAF` int(11) DEFAULT NULL,
  `BRANCH_NAME` varchar(200) DEFAULT NULL,
  `BRANCH_EN_NAME` varchar(200) DEFAULT NULL,
  `BRANCH_SHORT_NAME` varchar(200) DEFAULT NULL,
  `BRANCH_CONTACT_PEOPLE` varchar(20) DEFAULT NULL,
  `BRANCH_CONTACT_NUMBER` varchar(20) DEFAULT NULL,
  `BRANCH_PARENT_ID` int(10) DEFAULT NULL,
  `BRANCH_RELATIONSHIP` varchar(200) DEFAULT NULL,
  `BRANCH_ADDR` varchar(500) DEFAULT NULL,
  `DEL_STATE` int(11) DEFAULT NULL,
  `OLD_BRANCH_NO` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of t_branch
-- ----------------------------
INSERT INTO `t_branch` VALUES ('11', '00001', '0', '山东枣庄银行', null, null, null, '', '0', '00001', '山东省枣庄市', null, '00001');
INSERT INTO `t_branch` VALUES ('12', '91923', '1', '阿斯达四大', null, null, null, null, '11', '00001,91923', '阿斯达四大', null, null);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `ID` int(11) NOT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `MENU_NAME` varchar(100) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `SORT` varchar(10) DEFAULT NULL,
  `IMAGE_URL` varchar(100) DEFAULT NULL,
  `MENU_DESC` varchar(200) DEFAULT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `ID` int(11) DEFAULT NULL,
  `MODULE_PID` int(11) DEFAULT NULL,
  `MOD_CN_NAME` varchar(100) DEFAULT NULL,
  `MOD_EN_NAME` varchar(100) DEFAULT NULL,
  `MOD_ICON` varchar(200) DEFAULT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('10', '0', '基础管理', 'rolem', null, null, null);
INSERT INTO `t_module` VALUES ('11', '0', '设备管理', 'device', null, null, null);
INSERT INTO `t_module` VALUES ('12', '23', '软件管理', 'software', null, null, null);
INSERT INTO `t_module` VALUES ('15', '23', '媒体管理', 'media', null, null, null);
INSERT INTO `t_module` VALUES ('23', '0', '软件及媒体管理', 'softwareAndMedia', null, null, null);
INSERT INTO `t_module` VALUES ('24', '0', '系统管理', 'sysm', null, null, null);
INSERT INTO `t_module` VALUES ('25', '24', '二级维护器管理', 'secondMng', null, null, null);

-- ----------------------------
-- Table structure for t_opt
-- ----------------------------
DROP TABLE IF EXISTS `t_opt`;
CREATE TABLE `t_opt` (
  `ID` int(11) NOT NULL,
  `SERVER_ID` int(11) DEFAULT NULL,
  `PO_NAME` varchar(100) DEFAULT NULL,
  `OPT_NAME` varchar(100) DEFAULT NULL,
  `EXPRESSION` varchar(200) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `METHOD` varchar(50) DEFAULT NULL,
  `OPT_DESC` varchar(200) DEFAULT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(50) DEFAULT NULL,
  `REQ_URL` varchar(200) DEFAULT NULL,
  `RESP_URL` varchar(200) DEFAULT NULL COMMENT 'URL跳转时，填写跳转地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_opt
-- ----------------------------
INSERT INTO `t_opt` VALUES ('10', '33', 'role', '新增', null, '1', 'saveOrUpdateRole', null, null, null, '/role/addRole.html', null);
INSERT INTO `t_opt` VALUES ('11', '33', 'role', '删除', null, '1', 'deleteRole', null, null, null, '/role/deleRole.html', null);
INSERT INTO `t_opt` VALUES ('12', '33', 'role', '修改', null, '1', 'updateRole', null, null, null, '/role/updateRole.html', null);
INSERT INTO `t_opt` VALUES ('13', '33', 'role', '条件查询', null, '0', 'pagerRole', null, null, null, '/role/pagerRole.html', null);
INSERT INTO `t_opt` VALUES ('14', '33', 'role', '根据主键查询', null, '0', 'queryRoleById', null, null, null, '/role/queryRoleById.html', null);
INSERT INTO `t_opt` VALUES ('15', '33', 'role', '查看数据', null, '0', '', null, null, null, '/role/_visit.html', 'auth/rolemanager');
INSERT INTO `t_opt` VALUES ('16', '33', 'role', '根据id查询其角色及其下级角色', null, '0', 'queryRoleByIdAndfollow', null, null, null, '/role/queryRoleByIdAndfollow.html', null);
INSERT INTO `t_opt` VALUES ('17', '33', 'role', '查询所有对象', null, '0', 'findAllObj', null, null, null, '/role/_findAll.html', null);
INSERT INTO `t_opt` VALUES ('31', '34', 'branch', '新增', null, '1', 'saveBranch', null, null, null, '/rolem/branch/_add.html', null);
INSERT INTO `t_opt` VALUES ('32', '34', 'branch', '删除', null, '1', 'deleteBranch', null, null, null, '/rolem/branch/_deleteByBranchId.html', null);
INSERT INTO `t_opt` VALUES ('33', '34', 'branch', '修改', null, '1', 'updateBranch', null, null, null, '/rolem/branch/_update.html', null);
INSERT INTO `t_opt` VALUES ('34', '34', 'branch', '条件查询', null, '0', 'pagerListByPo', null, null, null, '/rolem/branch/_pager.html', null);
INSERT INTO `t_opt` VALUES ('35', '34', 'branch', '根据主键查询', null, '0', 'findById', null, null, null, '/rolem/branch/_findById.html', null);
INSERT INTO `t_opt` VALUES ('36', '34', 'branch', '查看数据', null, '0', '', null, null, null, '/rolem/branch/_visit.html', 'generated/rolem/Branch');
INSERT INTO `t_opt` VALUES ('37', '34', 'branch', '查询已分配二级服务器机构', null, '0', 'assignedBranch', null, null, null, '/rolem/branch/_assignBranch.html', null);
INSERT INTO `t_opt` VALUES ('38', '34', 'branch', '查询所有对象', null, '0', 'findAllAllocationBranch', null, null, null, '/rolem/branch/_findAll.html', null);
INSERT INTO `t_opt` VALUES ('39', '34', 'branch', '二级服务器显示所有机构对象', null, '0', 'findAllByConditionForSendServer', null, null, null, '/rolem/branch/_findAllForSendServer.html', null);
INSERT INTO `t_opt` VALUES ('40', '34', 'branch', '当前用户所属机构', null, '0', 'editSessionCheck', null, null, null, '/rolem/branch/_editSessionCheck.html', null);
INSERT INTO `t_opt` VALUES ('41', '34', 'branch', '查询当前机构树', null, '0', 'findAllEdit', null, null, null, '/rolem/branch/_findAllEdit.html', null);
INSERT INTO `t_opt` VALUES ('42', '34', 'branch', '验证当前机构是否叶子节点', null, '0', 'validateSessioBranch', null, null, null, '/rolem/branch/_validateSessionBranch.html', null);
INSERT INTO `t_opt` VALUES ('50', '35', 'user', '新增', null, '1', 'saveForPage', null, null, null, '/rolem/user/_add.html', null);
INSERT INTO `t_opt` VALUES ('51', '35', 'user', '删除', null, '1', 'deleteUser', null, null, null, '/rolem/user/_delete.html', null);
INSERT INTO `t_opt` VALUES ('52', '35', 'user', '修改', null, '1', 'updateUser', null, null, null, '/rolem/user/_update.html', null);
INSERT INTO `t_opt` VALUES ('53', '35', 'user', '条件查询', null, '0', 'pagerListByPo', null, null, null, '/rolem/user/_pager.html', null);
INSERT INTO `t_opt` VALUES ('54', '35', 'user', '角色分配', null, '1', 'allocateUserRole', null, null, null, '/userRole/allocateUserRole.html', null);
INSERT INTO `t_opt` VALUES ('55', '35', 'user', '根据主键查询', null, '0', 'findById', null, null, null, '/rolem/user/_findById.html', null);
INSERT INTO `t_opt` VALUES ('56', '35', 'user', '查看数据', null, '0', null, null, null, null, '/rolem/user/_visit.html', 'generated/rolem/User');
INSERT INTO `t_opt` VALUES ('57', '35', 'user', '重置密码', null, '1', 'resetPassword', null, null, null, '/rolem/user/_resetPassword.html', null);
INSERT INTO `t_opt` VALUES ('58', '35', 'user', '修改密码', null, '1', 'changePassword', null, null, null, '/rolem/user/_changePassword.html', null);
INSERT INTO `t_opt` VALUES ('59', '35', 'user', '查询所有对象', null, '0', 'findAll', null, null, null, '/rolem/user/_findAll.html', null);
INSERT INTO `t_opt` VALUES ('60', '70', 'user', '根据id查询用户角色', null, '0', 'queryUserRoleById', null, null, null, '/userRole/queryUserRoleById.html', null);
INSERT INTO `t_opt` VALUES ('61', '35', 'user', '根据机构ID查询用户信息', null, '0', 'findByBranchId', null, null, null, '/rolem/user/_findByBranchId.html', null);
INSERT INTO `t_opt` VALUES ('140', '55', 'parameterConf', '新增', null, '1', 'saveParameterConf', null, null, null, '/sysm/parameterConf/_add.html', null);
INSERT INTO `t_opt` VALUES ('141', '55', 'parameterConf', '删除', null, '1', 'deleteParameterConf', null, null, null, '/sysm/parameterConf/_delete.html', null);
INSERT INTO `t_opt` VALUES ('142', '55', 'parameterConf', '修改', null, '1', 'updateParameterConf', null, null, null, '/sysm/parameterConf/_update.html', null);
INSERT INTO `t_opt` VALUES ('143', '55', 'parameterConf', '查看数据', null, '0', null, null, null, null, '/sysm/parameterConf/_visit.html', 'generated/parameter/ParameterConf');
INSERT INTO `t_opt` VALUES ('144', '55', 'parameterConf', '主键查询参数配置', null, '0', 'findById', null, null, null, '/sysm/parameterConf/_findById.html', null);
INSERT INTO `t_opt` VALUES ('145', '55', 'parameterConf', '条件查询', null, '0', 'pagerListByPo', null, null, null, '/sysm/parameterConf/_pager.html', null);
INSERT INTO `t_opt` VALUES ('200', '66', 'userBranch', '新增', null, '1', 'saveForPage', null, null, null, '/userBranch/addUserBranch.html', null);
INSERT INTO `t_opt` VALUES ('201', '66', 'userBranch', '修改', null, '1', 'updateForPage', null, null, null, '/userBranch/updateUserBranch.html', null);
INSERT INTO `t_opt` VALUES ('202', '66', 'userBranch', '条件查询', null, '0', 'pagerListByPo', null, null, null, '/userBranch/pagerUserBranch.html', null);
INSERT INTO `t_opt` VALUES ('203', '66', 'userBranch', '删除', null, '1', 'deleteForPage', null, null, null, '/userBranch/deleUserBranch.html', null);
INSERT INTO `t_opt` VALUES ('204', '66', 'userBranch', '批量导入', null, '1', 'importData', null, null, null, '/userBranch', null);
INSERT INTO `t_opt` VALUES ('205', '66', 'userBranch', '查看数据', null, '0', null, null, null, null, '/userBranch/_visit.html', 'generated/software/UserBranch');
INSERT INTO `t_opt` VALUES ('206', '66', 'userBranch', '根据主键查询', null, '0', 'findById', null, null, null, '/userBranch/queryUserBranchById.html', null);
INSERT INTO `t_opt` VALUES ('250', '68', 'module', '自动生成代码使用的树', null, '0', 'pagerModual', null, null, null, '/module/pagerModual.html', null);
INSERT INTO `t_opt` VALUES ('252', '69', 'optRole', '查询角色拥有的所有功能', null, '0', 'listOptRole', null, null, null, '/optRole/listOptRole.html', null);

-- ----------------------------
-- Table structure for t_opt_role
-- ----------------------------
DROP TABLE IF EXISTS `t_opt_role`;
CREATE TABLE `t_opt_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPT_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(20) DEFAULT NULL,
  `BACK3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1053 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_opt_role
-- ----------------------------
INSERT INTO `t_opt_role` VALUES ('949', '10', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('950', '11', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('951', '12', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('952', '31', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('953', '32', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('954', '33', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('955', '50', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('956', '51', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('957', '52', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('958', '54', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('959', '57', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('960', '58', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('998', '140', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('999', '141', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('1000', '142', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('1001', '200', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('1002', '201', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('1003', '203', '122', null, null, null);
INSERT INTO `t_opt_role` VALUES ('1004', '204', '122', null, null, null);

-- ----------------------------
-- Table structure for t_pagedsn_function
-- ----------------------------
DROP TABLE IF EXISTS `t_pagedsn_function`;
CREATE TABLE `t_pagedsn_function` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` int(11) DEFAULT NULL,
  `MODULEID` int(11) DEFAULT NULL,
  `TABLE_NAME` varchar(100) DEFAULT NULL,
  `ID_COMN` varchar(100) DEFAULT NULL,
  `ID_STRATEGY` varchar(100) DEFAULT NULL,
  `ADD_FLG` int(11) DEFAULT NULL,
  `UPDATE_FLG` int(11) DEFAULT NULL,
  `MULIT_UPDATE_FLG` int(11) DEFAULT NULL,
  `DELETE_FLG` int(11) DEFAULT NULL,
  `CONDITION_FLG` int(11) DEFAULT NULL,
  `ADV_CONDITION_FLG` int(11) DEFAULT NULL,
  `IMPORT_FLG` int(11) DEFAULT NULL,
  `EXPORT_FLG` int(11) DEFAULT NULL,
  `IMPORT_TYPE` int(11) DEFAULT NULL,
  `DEFAULT_EXPORT_FILE_NAME` varchar(100) DEFAULT NULL,
  `TRAN_CN_NAME` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pagedsn_function
-- ----------------------------

-- ----------------------------
-- Table structure for t_pagedsn_table
-- ----------------------------
DROP TABLE IF EXISTS `t_pagedsn_table`;
CREATE TABLE `t_pagedsn_table` (
  `ID` int(11) NOT NULL,
  `BATCH_ID` int(11) NOT NULL,
  `CUMN_NAME` varchar(100) DEFAULT NULL,
  `CN_NAME` varchar(100) DEFAULT NULL,
  `CUMN_TYPE` varchar(100) DEFAULT NULL,
  `FIELD_TYPE` varchar(100) DEFAULT NULL,
  `INPUT_TYPE` int(11) DEFAULT NULL,
  `CHECK_TYPE` varchar(100) DEFAULT NULL,
  `IFREPEAT` int(11) DEFAULT NULL,
  `JSON_TYPE` varchar(100) DEFAULT NULL,
  `IFGRID` int(11) DEFAULT NULL,
  `IFTWICE` int(11) DEFAULT NULL,
  `IFIMPORT` int(11) DEFAULT NULL,
  `IMPORT_FORMAT` varchar(100) DEFAULT NULL,
  `EXPORT_FORMAT` varchar(100) DEFAULT NULL,
  `IFEXPORT` int(11) DEFAULT NULL,
  `IFQUERY` int(11) DEFAULT NULL,
  `IFEDIT` int(11) DEFAULT NULL,
  `IMPORT_SOURCE` int(11) DEFAULT NULL,
  `IFUNIQUE` int(11) DEFAULT NULL,
  `IF_FOREGIN_KEY` int(11) DEFAULT NULL,
  `FOREGIN_KEY_TABLE` varchar(552) DEFAULT NULL,
  `FOREGIN_KEY_TABLE_COLUMS` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pagedsn_table
-- ----------------------------

-- ----------------------------
-- Table structure for t_parameter_conf
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter_conf`;
CREATE TABLE `t_parameter_conf` (
  `ID` int(11) NOT NULL,
  `PARA_NAME` varchar(100) DEFAULT NULL,
  `PARA_VALUE` varchar(100) DEFAULT NULL,
  `PARA_DESC` varchar(200) DEFAULT NULL,
  `PARA_ENGLISH_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parameter_conf
-- ----------------------------
INSERT INTO `t_parameter_conf` VALUES ('22', '终端Agent端口', '55002', null, 'AgentPort');
INSERT INTO `t_parameter_conf` VALUES ('26', '文件上传路径', 'C:\\appServer\\atom_server\\upload', '用于存放版本文件和媒体文件', 'uploadPath');
INSERT INTO `t_parameter_conf` VALUES ('27', '软件更新任务Quartz执行周期', '*/20 * * * * ?', '如非必备不要修改此参数，修改后务必重启应用', 'softUpdateTaskCorn');
INSERT INTO `t_parameter_conf` VALUES ('28', '任务线程最大并发数', '10', '该参数越大，软件更新和媒体更新的通知效率就越高，服务器资源也占用越高，请不要轻易修改', 'concurrentNum');
INSERT INTO `t_parameter_conf` VALUES ('29', '二级服务器版本文件同步周期', '0 34 15 * * ?', '每天指定时间点执行同步，修改后务必重启应用', 'softSynCorn');
INSERT INTO `t_parameter_conf` VALUES ('30', '二级服务器广告文件同步周期', '0 28 11 * * ?', '每天指定时间点执行同步，修改后务必重启应用', 'mediaSynCorn');
INSERT INTO `t_parameter_conf` VALUES ('31', '广告更新Quartz执行周期', '0 40 10 * * ?', '如非必备不要修改此参数，修改后务必重启应用', 'mediaUpdateTaskCorn');
INSERT INTO `t_parameter_conf` VALUES ('32', '软件更新任务有效期', '3', '以天为单位', 'taskActiveDays');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(200) DEFAULT NULL,
  `BRANCH_ID` int(11) DEFAULT NULL COMMENT '角色所属机构',
  `ROLE_PID` varchar(200) DEFAULT NULL COMMENT '父角色,多个时以逗号分隔',
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK_T_ROLE_FK1` (`BRANCH_ID`),
  CONSTRAINT `FK_T_ROLE_FK1` FOREIGN KEY (`BRANCH_ID`) REFERENCES `t_branch` (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('122', '超级管理员', '拥有所有权限', null, '122');

-- ----------------------------
-- Table structure for t_role_prole
-- ----------------------------
DROP TABLE IF EXISTS `t_role_prole`;
CREATE TABLE `t_role_prole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `role_pid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_prole
-- ----------------------------

-- ----------------------------
-- Table structure for t_transaction
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction`;
CREATE TABLE `t_transaction` (
  `ID` int(11) DEFAULT NULL,
  `SER_EN_NAME` varchar(100) DEFAULT NULL,
  `SER_CN_NAME` varchar(100) DEFAULT NULL,
  `MODULE_ID` int(11) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  `CHKFLG` int(11) DEFAULT NULL,
  `TRN_DESC` varchar(100) DEFAULT NULL,
  `TRN_URL` varchar(200) DEFAULT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_transaction
-- ----------------------------
INSERT INTO `t_transaction` VALUES ('33', 'roleServiceImpl', '角色管理', '10', '1', '1', null, 'role/_visit.html', null, null);
INSERT INTO `t_transaction` VALUES ('34', 'branchServiceImpl', '机构管理', '10', '1', '1', null, 'rolem/branch/_visit.html', null, null);
INSERT INTO `t_transaction` VALUES ('35', 'userServiceImpl', '用户管理', '10', '1', '1', null, 'rolem/user/_visit.html', null, null);
INSERT INTO `t_transaction` VALUES ('68', 'moduleServiceImpl', '模块管理', null, '1', '1', null, null, null, null);
INSERT INTO `t_transaction` VALUES ('69', 'optRoleServiceImpl', '角色功能对照关系', null, '1', '1', null, null, null, null);
INSERT INTO `t_transaction` VALUES ('70', 'userRoleServiceImpl', '用户角色对照关系', null, '1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL,
  `USER_LOAD` varchar(30) DEFAULT NULL,
  `USER_PWD` varchar(200) DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `USER_PHONE` varchar(20) DEFAULT NULL,
  `USER_CALL` varchar(20) DEFAULT NULL,
  `USER_EMAIL` varchar(50) DEFAULT NULL,
  `USER_POSITION` varchar(10) DEFAULT NULL,
  `BRANCH_ID` int(11) DEFAULT NULL,
  `USER_SEX` int(11) DEFAULT NULL,
  `USER_ROLE` varchar(100) DEFAULT NULL,
  `DEL_STATE` int(11) DEFAULT NULL,
  `USER_NUMBER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK_T_USER_FK1` (`BRANCH_ID`),
  CONSTRAINT `FK_T_USER_FK1` FOREIGN KEY (`BRANCH_ID`) REFERENCES `t_branch` (`BRANCH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('82', 'admin', '000000', '唐颖', '18613659901', '18613659901', 'tangying228@163.com', null, '11', null, '122', null, null);

-- ----------------------------
-- Table structure for t_user_branch
-- ----------------------------
DROP TABLE IF EXISTS `t_user_branch`;
CREATE TABLE `t_user_branch` (
  `BRANCH_NO` varchar(20) DEFAULT NULL,
  `USER_NO` varchar(20) DEFAULT NULL,
  `USER_NAME` varchar(100) DEFAULT NULL,
  `BACK1` varchar(10) DEFAULT NULL,
  `BACK2` varchar(20) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_branch
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `BACK1` varchar(100) DEFAULT NULL,
  `BACK2` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('361', '82', '122', '系统管理员', null, null);

-- ----------------------------
-- Function structure for splitIp
-- ----------------------------
DROP FUNCTION IF EXISTS `splitIp`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `splitIp`(str  varchar(50),str_type varchar(10)) RETURNS varchar(255) CHARSET utf8
begin
  declare  tmpn  int;
   declare tmpn1 int(10);
   declare tmpn2 int(10);

 declare   tempS3 varchar(2048);
  declare  tempS4 varchar(2048);
  declare  tempS5 varchar(2048);
  declare  retStr varchar(2048);
   SET tmpn1=length(str_type);
   SET tempS3=str;
set retStr='';
    myloop:loop
       SET  tmpn2=instr(tempS3,str_type);  
				
         if tmpn2!=0 then
           SET tempS4=substring(tempS3,1,tmpn2-1);
						
           SET tempS3=substring(tempS3,tmpn2+tmpn1); 
						
         ELSEIF   tmpn2=0 then 
          SET  tempS4=tempS3;
						
         end if;
						
           if length(tempS4) =1 then
							
              SET tempS5=concat('00',tempS4);

           ELSEIF  length(tempS4) =2 then
              SET tempS5=concat('0',tempS4);
           else
             SET tempS5=tempS4;
           end if;
					
        

					set retStr=concat(retStr,tempS5,'.');
         
           if tmpn2=0 then
					 leave myloop;
					 end if;
     end loop myloop;

         return substring(retStr,1,length(retStr)-1);
end
;;
DELIMITER ;
