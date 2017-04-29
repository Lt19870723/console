DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(20) NOT NULL COMMENT '菜单名称',
  `URL` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `DESC` varchar(20) DEFAULT NULL COMMENT '描述',
  `ORDER` middleint(8) unsigned NOT NULL COMMENT '排序',
  `LEVEL` tinyint(3) unsigned NOT NULL COMMENT '菜单层级(1,2,3)',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:启用,1:禁用)',
  `PID` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY `index_pid` (`PID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

DROP TABLE IF EXISTS `t_system_element`;
CREATE TABLE `t_system_element` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(20) NOT NULL COMMENT '元素名称',
  `DESC` varchar(20) DEFAULT NULL COMMENT '描述',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:启用,1:禁用)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统页面元素表';

DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(20) NOT NULL COMMENT '权限名',
  `DESC` varchar(20) DEFAULT NULL COMMENT '描述',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:启用,1:禁用)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表';

DROP TABLE IF EXISTS `t_system_permission_resources`;
CREATE TABLE `t_system_permission_resources` (
  `PERMISSION_ID` int(10) unsigned NOT NULL COMMENT '权限ID',
  `RESOURCES_ID` int(10) unsigned NOT NULL COMMENT '资源ID',
  `TYPE` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '类型(0:菜单,1:页面元素)',
  KEY (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限资源关联表';

DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `NAME` varchar(20) NOT NULL COMMENT '角色名',
  `DESC` varchar(20) NOT NULL COMMENT '角色描述',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:启用,1:禁用)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

DROP TABLE IF EXISTS `t_system_role_permission`;
CREATE TABLE `t_system_role_permission` (
  `ROLE_ID` int(10) unsigned NOT NULL COMMENT '角色ID',
  `PERMISSION_ID` int(10) unsigned NOT NULL COMMENT '权限ID',
  KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限关联表';

DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USERNAME` varchar(30) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(32) NOT NULL COMMENT '登陆密码',
  `NAME` varchar(20) NOT NULL COMMENT '员工姓名',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT 'E-Mail',
  `MOBILE` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `TEL` varchar(15) DEFAULT NULL COMMENT '座机号码',
  `DEPT` varchar(15) NOT NULL COMMENT '部门',
  `POSITION` varchar(15) NOT NULL COMMENT '职位',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态(0:启用,1:禁用)',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_username` (`USERNAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';


DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role` (
  `USER_ID` int(10) unsigned NOT NULL COMMENT '用户ID',
  `ROLE_ID` int(10) unsigned NOT NULL COMMENT '角色ID',
  UNIQUE KEY (`USER_ID`, `ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色关联表';

DROP TABLE IF EXISTS `t_system_user_permission`;
CREATE TABLE `t_system_user_permission` (
  `USER_ID` int(10) unsigned NOT NULL COMMENT '用户ID',
  `PERMISSION_ID` int(10) unsigned NOT NULL COMMENT '权限ID',
  KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户权限关联表';

DROP TABLE IF EXISTS `t_system_rbac_operate_log`;
CREATE TABLE `t_system_rbac_operate_log` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SOURCE_ID` int(10) unsigned NOT NULL COMMENT '来源ID',
  `SOURCE_TYPE` tinyint(3) unsigned NOT NULL COMMENT '来源类型(1:用户,2:角色,3:权限,4:菜单,5:页面元素)',
  `OPERATE_TYPE` tinyint(3) unsigned NOT NULL COMMENT '操作类型(1:新增,2:修改,3:删除,4赋角色,5:赋权限)',
  `OPERATE_BY` int(10) unsigned NOT NULL COMMENT '操作人',
  `OPERATE_TIME` datetime NOT NULL COMMENT '操作时间',
  `OPTION_IP` varchar(16) NOT NULL COMMENT '操作IP',
  `OPTION_MAC` varchar(48) DEFAULT NULL COMMENT '操作MAC',
  `REMARK` varchar(255) NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  KEY `SOURCE_ID` (`SOURCE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限操作日志';

drop function if exists `getMenuTree`;
create function `getMenuTree`(rootId int)
returns varchar(2000)
begin
  declare ret varchar(2000);
  declare cid varchar(2000);

  set ret = '';
  set cid =cast(rootid as char);

  while cid is not null do
    set ret = concat(ret, ',', cid);
    select group_concat(id) into cid from t_system_menu where find_in_set(pid, cid) > 0;
  end while;
  return ret;
end;

-- 数据初始化
insert into t_system_user(
`ID`,
`USERNAME`,
`PASSWORD`,
`NAME`,
`EMAIL`,
`MOBILE`,
`TEL`,
`DEPT`,
`POSITION`,
`STATUS`
)
select ID, JOBNUM, PASSWD, `NAME`, EMAIL, MOBILE, TEL,
case JOBPOST
when 1 then '财务部'
when 2 then '风险控制'
when 3 then '运营团队'
when 4 then '贷后管理'
when 5 then '人事HR'
when 9 then '管理员'
end,
'普通员工',
case `STATUS`
when 1 then 0
when 0 then 1
end
from rocky_staff;

truncate t_system_menu;
INSERT INTO `t_system_menu` (`ID`, `NAME`, `URL`, `DESC`, `ORDER`, `LEVEL`, `STATUS`, `PID`, `REMARK`)
select 1, '系统管理', null, null, 1, 1, 0, 0, null union all
select 2, '用户管理', null, null, 1, 2, 0, 1, null union all
select 3, '部门管理', null, null, 2, 2, 0, 1, null union all
select 4, '角色管理', null, null, 3, 2, 0, 1, null union all
select 5, '权限管理', null, null, 4, 2, 0, 1, null union all
select 6, '菜单管理', null, null, 5, 2, 0, 1, null union all
select 7, '用户管理', '/system/user/main.html', null, 1, 3, 0, 2, null union all
select 8, '部门管理', '/system/dept/main.html', null, 2, 3, 0, 3, null union all
select 9, '角色管理', '/system/role/main.html', null, 3, 3, 0, 4, null union all
select 10, '权限管理', '/system/permission/main.html', null, 4, 3, 0, 5, null union all
select 11, '菜单管理', '/system/menu/main.html', null, 5, 3, 0, 6, null union all
select 12, '资金管理', null, null, 2, 1, 0, 0, null;

truncate t_system_permission;
insert into t_system_permission(`ID`, `NAME`, `DESC`, `STATUS`, `REMARK`)
values (1, '系统管理', '系统管理', 0, null);

truncate t_system_permission_resources;
insert into t_system_permission_resources(`PERMISSION_ID`, `RESOURCES_ID`, `TYPE`)
select p.ID, m.ID, 0 from t_system_menu m, t_system_permission p;

truncate t_system_role;
insert into t_system_role(`ID`, `NAME`, `DESC`, `STATUS`, `REMARK`)
values (1, '系统管理员', '系统管理员', 0, null);

truncate t_system_role_permission;
insert into t_system_role_permission(`ROLE_ID`, `PERMISSION_ID`)
select r.ID, p.ID from t_system_permission p, t_system_role r;

truncate t_system_user_role;
insert into t_system_user_role(`USER_ID`, `ROLE_ID`)
values (41, 1);

truncate t_system_user_permission;
insert into t_system_user_permission(`USER_ID`, `PERMISSION_ID`)
values (41, 1);