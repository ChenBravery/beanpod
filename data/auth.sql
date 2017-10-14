CREATE DATABASE IF NOT EXISTS auth DEFAULT CHARACTER SET = utf8;

Use auth;

drop table if exists t_sys_access_token;

drop table if exists t_sys_app;

drop table if exists t_sys_login_alias;

drop table if exists t_sys_menu;

drop table if exists t_sys_menu_permission;

drop table if exists t_sys_o2user;

drop table if exists t_sys_permission;

drop table if exists t_sys_role;

drop table if exists t_sys_role_menu;

drop table if exists t_sys_role_permission;

drop table if exists t_sys_token_permission;

drop table if exists t_sys_token_role;

drop table if exists t_sys_user;

drop table if exists t_sys_user_role;

/*==============================================================*/
/* Table: t_sys_access_token                                    */
/*==============================================================*/
create table t_sys_access_token
(
   id                   varchar(32) not null comment '令牌唯一标识',
   type                 int,
   user_id              varchar(32) comment '授权用户',
   app_id               varchar(32) comment '应用ID',
   access_token         varchar(256) not null comment '访问令牌',
   refresh_token        varchar(256) not null comment '刷新令牌',
   create_time          time comment '创建时间',
   refresh_time         time,
   expired_time         time comment '访问令牌过期时间',
   refresh_expired_time time comment '刷新令牌过期时间',
   available            int,
   deteted              int default 0,
   primary key (id)
);

alter table t_sys_access_token comment '访问令牌表';

/*==============================================================*/
/* Table: t_sys_app                                             */
/*==============================================================*/
create table t_sys_app
(
   id                   varchar(32) not null comment '应用唯一标识',
   ower_id              varchar(32) comment '应用所属用户ID',
   available            int,
   deteted              int default 0,
   primary key (id)
);

alter table t_sys_app comment '应用表';

/*==============================================================*/
/* Table: t_sys_login_alias                                     */
/*==============================================================*/
create table t_sys_login_alias
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '用户唯一标识',
   login_type           int comment '登录名类型
            0 account_name 帐户名称
            1 Email 邮件地址
            2 mobile 手机号
            
            ',
   login_name           varchar(128) comment '登录名称',
   verified             int comment '确认标志 0 验证 1 已验证',
   create_time          datetime comment '登录名创建时间',
   primary key (id)
);

alter table t_sys_login_alias comment '用户登录名称表';

/*==============================================================*/
/* Table: t_sys_menu                                            */
/*==============================================================*/
create table t_sys_menu
(
   id                   varchar(32) not null comment '菜单唯一标识',
   name                 varchar(32) comment '名称',
   display_name         varchar(32) comment '显示名称',
   type                 int comment '菜单类型',
   is_sys               int comment '是否系统菜单',
   usable               int comment '是否可用',
   `show`               int comment '是否显示',
   parent_id            varchar(32) comment '父权限',
   level                int comment '菜单层级 0,1,2,3,...',
   order_number         int comment '显示顺序号',
   href                 varchar(256),
   target               varchar(32),
   icon                 varchar(256),
   description          varchar(256),
   creator_id           varchar(32) comment '创建者',
   create_time          datetime comment '创建时间',
   updator_id           varchar(32) comment '更新者',
   update_time          datetime comment '更新时间',
   deleted              int default 0 comment '删除标记',
   primary key (id)
);

alter table t_sys_menu comment '菜单表';

/*==============================================================*/
/* Table: t_sys_menu_permission                                 */
/*==============================================================*/
create table t_sys_menu_permission
(
   id                   varchar(32) not null,
   menu_id              varchar(32),
   permission_id        varchar(32),
   primary key (id)
);

alter table t_sys_menu_permission comment '菜单权限关系表';

/*==============================================================*/
/* Table: t_sys_o2user                                          */
/*==============================================================*/
create table t_sys_o2user
(
   id                   varchar(32) not null,
   user_id              varchar(32) comment '用户唯一标识',
   type                 varchar(16) comment '来源类型: qq, weixin, alipay, weibo',
   euid                 varchar(64) comment '外部用户标识',
   access_token         varchar(64),
   refresh_token        varchar(64),
   token_expired_time   int comment '令牌过期时间',
   create_time          datetime,
   primary key (id)
);

alter table t_sys_o2user comment '第三方来源用户表';

/*==============================================================*/
/* Table: t_sys_permission                                      */
/*==============================================================*/
create table t_sys_permission
(
   id                   varchar(32) not null,
   name                 varchar(32) comment '名称',
   display_name         varchar(32) comment '显示名称',
   type                 int comment '权限类型',
   is_sys               int comment '是否系统权限',
   usable               int comment '是否可用',
   parent_id            varchar(32) comment '父权限',
   permission           varchar(1024),
   description          varchar(256),
   creator_id           varchar(32) comment '创建者',
   create_time          datetime comment '创建时间',
   updator_id           varchar(32) comment '更新者',
   update_time          datetime comment '更新时间',
   deleted              int default 0 comment '删除标记',
   primary key (id)
);

alter table t_sys_permission comment '权限表';

/*==============================================================*/
/* Table: t_sys_role                                            */
/*==============================================================*/
create table t_sys_role
(
   id                   varchar(32) not null,
   name                 varchar(32) comment '角色名称',
   display_name         varchar(32) comment '角色显示名称',
   role_type            int comment '角色类型',
   is_sys               int comment '是否系统角色',
   usable               int comment '是否可用',
   parent_id            varchar(32) comment '父角色',
   description          varchar(256),
   creator_id           varchar(32) comment '创建者',
   create_time          datetime comment '创建时间',
   updator_id           varchar(32) comment '更新者',
   update_time          datetime comment '更新时间',
   deleted              int default 0 comment '删除标记',
   primary key (id)
);

/*==============================================================*/
/* Table: t_sys_role_menu                                       */
/*==============================================================*/
create table t_sys_role_menu
(
   id                   varchar(32) not null,
   role_id              varchar(32),
   menu_id              varchar(32),
   primary key (id)
);

alter table t_sys_role_menu comment '角色菜单关系表';

/*==============================================================*/
/* Table: t_sys_role_permission                                 */
/*==============================================================*/
create table t_sys_role_permission
(
   id                   varchar(32) not null,
   role_id              varchar(32),
   permission_id        varchar(32),
   primary key (id)
);

alter table t_sys_role_permission comment '角色权限关系表';

/*==============================================================*/
/* Table: t_sys_token_permission                                */
/*==============================================================*/
create table t_sys_token_permission
(
   id                   varchar(32) not null,
   token_id             varchar(32),
   permission_id        varchar(32),
   primary key (id)
);

alter table t_sys_token_permission comment '令牌权限关系表';

/*==============================================================*/
/* Table: t_sys_token_role                                      */
/*==============================================================*/
create table t_sys_token_role
(
   id                   varchar(32) not null,
   token_id             varchar(32),
   role_id              varchar(32),
   primary key (id)
);

alter table t_sys_token_role comment '令牌户角色关系表';

/*==============================================================*/
/* Table: t_sys_user                                            */
/*==============================================================*/
create table t_sys_user
(
   id                   varchar(32) not null comment '用户唯一表示',
   name                 varchar(32) comment '用户名称',
   password             varchar(64),
   creator_id           varchar(32),
   parent_id            varchar(32),
   create_time          datetime,
   update_time          datetime comment '用户更新时间',
   deleted              int default 0 comment '删除标记',
   primary key (id)
);

alter table t_sys_user comment '用户基本信息表';

/*==============================================================*/
/* Table: t_sys_user_role                                       */
/*==============================================================*/
create table t_sys_user_role
(
   id                   varchar(32) not null,
   user_id              varchar(32),
   role_id              varchar(32),
   primary key (id)
);

alter table t_sys_user_role comment '用户角色关系表';
