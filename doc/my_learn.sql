create database my_learn character set utf8mb4;
use my_learn;

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

