-- 数据库
create database my_learn character set utf8mb4;
use my_learn;

-- 表结构
create table sys_privilege(
    id bigint auto_increment comment '权限ID' primary key,
    privilege_name varchar(50) null comment '权限名称',
    privilege_url  varchar(50) null comment '权限url'
)comment '权限表' charset = utf8;

create table sys_role(
    id bigint auto_increment comment '角色ID' primary key,
    role_name   varchar(50) null comment '角色名',
    enabled     int         null comment '有效标志',
    create_by   bigint      null comment '创建人',
    create_time datetime    null comment '创建时间'
)comment '角色表' charset = utf8;

create table sys_role_privilege(
    role_id      bigint null comment '角色ID',
    privilege_id bigint null comment '权限ID'
)comment '角色权限关联表' charset = utf8;

create table sys_user(
    id bigint auto_increment comment '用户ID' primary key,
    user_name     varchar(50) null comment '用户名',
    user_password varchar(50) null comment '密码',
    user_email    varchar(50) null comment '邮箱',
    user_info     text        null comment '简介',
    head_img      blob        null comment '头像',
    create_time   datetime    null comment '创建时间'
) comment '用户表' charset = utf8;

create table sys_user_role(
    user_id bigint null comment '用户ID',
    role_id bigint null comment '角色ID'
)comment '用户角色关联表' charset = utf8;

-- 数据管理
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', 'admin@onlying.cn', '管理员', null, '2018-08-05 10:48:00');
INSERT INTO `sys_user` VALUES ('1001', 'test', '123456', 'test@onlying.cn', '测试用户', null, '2018-08-05 10:48:00');
-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '1', '1', '2018-08-05 10:50:00');
INSERT INTO `sys_role` VALUES ('2', '普通用户', '2', '2', '2018-08-05 10:51:00');
-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('1001', '2');
-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('1', '用户管理', '/users');
INSERT INTO `sys_privilege` VALUES ('2', '角色管理', '/rooles');
INSERT INTO `sys_privilege` VALUES ('3', '系统日志', '/logs');
INSERT INTO `sys_privilege` VALUES ('4', '人员维护', '/persons');
INSERT INTO `sys_privilege` VALUES ('5', '单位维护', '/companies');
-- ----------------------------
-- Records of sys_role_privilege
-- ----------------------------
INSERT INTO `sys_role_privilege` VALUES ('1', '1');
INSERT INTO `sys_role_privilege` VALUES ('1', '2');
INSERT INTO `sys_role_privilege` VALUES ('1', '3');
INSERT INTO `sys_role_privilege` VALUES ('2', '4');
INSERT INTO `sys_role_privilege` VALUES ('2', '5');