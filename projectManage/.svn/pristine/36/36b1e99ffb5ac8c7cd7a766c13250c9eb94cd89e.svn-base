/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/11 13:31:05                           */
/*==============================================================*/


drop table if exists customer;

drop table if exists customer_pic;

drop table if exists customer_shops;

drop table if exists default_step;

drop table if exists login_log;

drop table if exists project_contacts;

drop table if exists project_info;

drop table if exists project_pic;

drop table if exists project_step;

drop table if exists project_step_logs;

drop table if exists project_step_logs_pic;

drop table if exists sys_log;

drop table if exists sys_permission;

drop table if exists sys_permission_role;

drop table if exists sys_role;

drop table if exists sys_role_user;

drop table if exists sys_user;

drop table if exists user_relation;

drop table if exists project_handover_detail;


/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   customerId           int(11) not null auto_increment,
   companyName          varchar(100) comment '公司名称',
   province             varchar(50) comment '省',
   city                 varchar(50) comment '市',
   area                 varchar(50) comment '区',
   addressDetail        varchar(255) comment '详细地址',
   mobile               varchar(50) comment '手机号码/联系方式',
   imageUrls            varchar(1000) comment '公司图片（多个逗号分隔）',
   isUploadPaper        tinyint default 1 comment '是否上传图纸原图（默认是）',
   isShortNote          tinyint default 1 comment '是否短信通知客户（默认否）',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (customerId)
);

alter table customer comment '客户（公司）';

/*==============================================================*/
/* Table: customer_pic                                          */
/*==============================================================*/
create table customer_pic
(
   customerPicId        int(11) not null auto_increment,
   customerId           int(11) comment '客户（公司）ID',
   smallImageUrls       varchar(255) comment '缩略图',
   imgUrls              varchar(255) comment '原图片',
   imgOrder             int(11) comment '图片顺序',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (customerPicId)
);

alter table customer_pic comment '客户（公司图片）';

/*==============================================================*/
/* Table: customer_shops                                        */
/*==============================================================*/
create table customer_shops
(
   customerShopId       int(11) not null auto_increment comment '公司门店id',
   customerId           int(11) comment '所属公司',
   shopName             varchar(100) comment '门店名称',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (customerShopId)
);

alter table customer_shops comment '公司门店';

/*==============================================================*/
/* Table: default_step                                          */
/*==============================================================*/
create table default_step
(
   stepId               int(11) not null auto_increment,
   customerId           int(11) comment '所属公司默认配置（为空为平台默认配置）',
   sysRoleId            int(11) comment '对应操作角色ID',
   projectType          char(1) comment '工程类型（1.中央空调，2.新风系统，3.供暖系统）',
   stepName             varchar(100) comment '步骤名称',
   stepOrder            int(11) comment '步骤顺序',
   stepPercent          decimal(19,2) comment '步骤百分比',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (stepId)
);

alter table default_step comment '步骤设置（默认）';

/*==============================================================*/
/* Table: login_log                                             */
/*==============================================================*/
create table login_log
(
   loginLogId           int(11) not null auto_increment,
   sysUserId            int(11) comment '用户ID',
   uniqueDeviceIdentifier varchar(100) comment '登录设备唯一标识',
   loginAddress         varchar(50) comment '登录地',
   loginIp              varchar(32) comment '登录IP',
   loginTime            datetime default CURRENT_TIMESTAMP comment '登录时间',
   primary key (loginLogId)
);

alter table login_log comment '系统用户登录日志';

/*==============================================================*/
/* Table: project_contacts                                      */
/*==============================================================*/
create table project_contacts
(
   customerContactId    int(11) not null auto_increment comment '主键',
   projectInfoId        int(11) comment '所属工程',
   name                 varchar(100) comment '姓名',
   telphone             varchar(50) comment '联系电话',
   typeOfWork           char(1) comment '工种（1 ：项目经理 2 ： 装修设计 3： 装修监工 4 木工 5：电工 6：其他）',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   createTime           datetime default CURRENT_TIMESTAMP,
   createUserId         int(11),
   updateTime           datetime default CURRENT_TIMESTAMP,
   updateUserId         int(11),
   primary key (customerContactId)
);

alter table project_contacts comment '工程对应联系人';

/*==============================================================*/
/* Table: project_info                                          */
/*==============================================================*/
create table project_info
(
   projectInfoId        int(11) not null auto_increment,
   projectType          char(1) comment '工程项目类型（1.中央空调，2.新风系统，3.供暖系统）',
   customerShopId       int(11) comment '所属门店ID',
   buildingName         varchar(200) comment '楼盘名称',
   addressDetail        varchar(255) comment '项目地址',
   customerName         varchar(20) comment '业主姓名',
   customerMobile       varchar(50) comment '业主手机',
   customerOpenId       varchar(50) comment '业主微信openid',
   contractNumber       varchar(50) comment '合同编号',
   contractShortImageUrls varchar(1000) comment '合同图片缩略图（多个逗号分隔）',
   contractImageUrls    varchar(1000) comment '合同图片（多个逗号分隔）',
   constructionShortImageUrls varchar(1000) comment '施工图纸缩略图（多个逗号分隔）',
   constructionImageUrls varchar(1000) comment '施工图纸（多个逗号分隔）',
   saleMan              int(11) comment '销售人员（对应SysUser中SYSUserID）',
   designMan            int(11) comment '设计人员（对应SYSUser中sysUserId）',
   constructLeader      int(11) comment '施工队长（对应sysUser中sysUserId）',
   supervisor           int(11) comment '监理人员（对应sysUser中sysUserId）',
   currentProgress      decimal(19,2) comment '当前进度',
   score                varchar(10) comment '评分',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   deleted              tinyint default 0 comment '是否删除 1是  0否',
   status               char(1) comment '当前最佳节点状态优先级2>1>3（1 待审批 2 待整改 3  已完成）',
   projectStatus        char(1) default '1' comment '工程状态（1 代派工 2  进行中 3 已完成）',
   primary key (projectInfoId)
);

alter table project_info comment '工程项目信息';

/*==============================================================*/
/* Table: project_pic                                           */
/*==============================================================*/
create table project_pic
(
   projectPicId         int(11) not null auto_increment,
   projectInfoId        int(11) comment '工程信息Id',
   type                 char(1) comment '1 合同图片 2 施工图片 ',
   smallImageUrls       varchar(255) comment '操作缩略图',
   imgUrls              varchar(255) comment '操作原图片',
   imgOrder             int(11) comment '图片顺序',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (projectPicId)
);

alter table project_pic comment '工程合同与施工图片';

/*==============================================================*/
/* Table: project_step                                          */
/*==============================================================*/
create table project_step
(
   stepId               int(11) not null auto_increment,
   projectType          char(1) comment '工程类型（1.中央空调，2.新风系统，3.供暖系统）',
   sysRoleId            int(11) comment '对应操作角色ID',
   projectInfoId        int(11),
   stepName             varchar(100) comment '步骤名称',
   stepOrder            int(11) comment '步骤顺序',
   stepPercent          decimal(19,2) comment '步骤百分比',
   projectStatus        char(1) comment '工程节点状态（1 待审批 2 待整改 3 已完成）',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (stepId)
);

alter table project_step comment '工程对应步骤设置';

/*==============================================================*/
/* Table: project_step_logs                                     */
/*==============================================================*/
create table project_step_logs
(
   projectStepLogId     int(11) not null auto_increment,
   stepId               int(11) comment '步骤ID',
   operateTitle         varchar(50) comment '操作标题',
   operateContent       varchar(500) comment '操作内容',
   smallImageUrls       varchar(1000) comment '操作缩略图（多个逗号分隔）',
   imgUrls              varchar(1000) comment '操作原图片（多个逗号分隔和缩略图对应）',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   rectifyDate          date comment '整改日期',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (projectStepLogId)
);

alter table project_step_logs comment '工程步骤对应日志';

/*==============================================================*/
/* Table: project_step_logs_pic                                 */
/*==============================================================*/
create table project_step_logs_pic
(
   projectStepLogPicId  int(11) not null auto_increment,
   projectStepLogId     int(11) comment '工程对应日志节点ID',
   smallImageUrls       varchar(255) comment '操作缩略图',
   imgUrls              varchar(255) comment '操作原图片',
   imgOrder             int(11) comment '图片顺序',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (projectStepLogPicId)
);

alter table project_step_logs_pic comment '工程图片';

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log
(
   id                   int(11) not null auto_increment,
   moudleName           varchar(255) comment '模块名称',
   eventDesc            varchar(255) comment '事件描述',
   operator             int(11) comment '操作人',
   requestIp            varchar(255) comment '操作人ip',
   requestUrl           varchar(255) comment '请求路径',
   request              varchar(500) comment '请求内容',
   response             longtext comment '相应内容',
   createTime           datetime default CURRENT_TIMESTAMP comment '操作时间',
   primary key (id)
);

alter table sys_log comment '系统操作日志';

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   permissionId         int(11) not null auto_increment,
   name                 varchar(255),
   description          varchar(255),
   url                  varchar(255),
   pid                  int(11),
   hasChildren          tinyint(1) default 0,
   primary key (permissionId)
);

alter table sys_permission comment '权限信息表';

/*==============================================================*/
/* Table: sys_permission_role                                   */
/*==============================================================*/
create table sys_permission_role
(
   id                   int(11) not null auto_increment,
   permissionId         int(11),
   sysRoleId            int(11),
   primary key (id)
);

alter table sys_permission_role comment '角色和权限关系表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   sysRoleId            int(11) not null auto_increment,
   name                 varchar(50) comment '角色名称',
   description          varchar(50) comment '角色描述',
   status               char(1) default '1' comment '1 有效 2 无效',
   primary key (sysRoleId)
);

alter table sys_role comment '角色信息表';

/*==============================================================*/
/* Table: sys_role_user                                         */
/*==============================================================*/
create table sys_role_user
(
   id                   int(11) not null auto_increment,
   sysUserId            int(11),
   sysRoleId            int(11),
   primary key (id)
);

alter table sys_role_user comment '用户角色关系表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   sysUserId            int(11) not null auto_increment,
   username             varchar(50) comment '用户名（唯一性校验）',
   mobile               varchar(20) comment '手机号',
   realName             varchar(64) comment '姓名',
   password             varchar(32),
   locked               char(1) default '1' comment '1 有效 2 锁定',
   isAllowMultiAccess   tinyint default 0 comment '是否允许多点访问',
   createTime           datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   updateTime           datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   updateUserId         int(11) comment '更新人',
   primary key (sysUserId)
);

alter table sys_user comment '系统用户表';

/*==============================================================*/
/* Table: user_relation                                         */
/*==============================================================*/
create table user_relation
(
   id                   int(11) not null auto_increment,
   sysUserId            int(11) comment '登录用户',
   customerId           int(11) comment '客户（公司）ID',
   customerShopId       int(11) comment '用户所属公司门店ID',
   primary key (id)
);

alter table user_relation comment '用户所属公司';

/*==============================================================*/
/* Table: project_handover_detail                               */
/*==============================================================*/
create table project_handover_detail
(
   projectHandoverDetailId int(11) not null auto_increment,
   projectInfoId        int(11) comment '工程信息Id',
   oldSaleMan           int(11) comment '原来销售人员',
   oldDesignMan         int(11) comment '原来设计人员',
   oldConstructLeader   int(11) comment '原来施工队长',
   oldSupervisor        int(11) comment '原来监理',
   newSaleMan           int(11) comment '交接后销售人员',
   newDesignMan         int(11) comment '交接后设计人员',
   newConstructLeader   int(11) comment '交接后施工队长',
   newSupervisor        int(11) comment '交接后监理',
   createTime           datetime default CURRENT_TIMESTAMP comment '创建时间',
   createUserId         int(11) comment '创建人',
   status               char(1) default '1' comment '状态 1 有效 2 无效',
   primary key (projectHandoverDetailId)
);

alter table project_handover_detail comment '工程交接详细信息';


alter table customer_pic add constraint FK_Reference_23 foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table customer_shops add constraint FK_Reference_7 foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table default_step add constraint FK_Reference_17 foreign key (sysRoleId)
      references sys_role (sysRoleId) on delete restrict on update restrict;

alter table default_step add constraint FK_Reference_19 foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table login_log add constraint FK_Reference_24 foreign key (sysUserId)
      references sys_user (sysUserId) on delete restrict on update restrict;

alter table project_contacts add constraint FK_Reference_20 foreign key (projectInfoId)
      references project_info (projectInfoId) on delete restrict on update restrict;

alter table project_info add constraint FK_Reference_13 foreign key (customerShopId)
      references customer_shops (customerShopId) on delete restrict on update restrict;

alter table project_pic add constraint FK_Reference_22 foreign key (projectInfoId)
      references project_info (projectInfoId) on delete restrict on update restrict;

alter table project_step add constraint FK_Reference_14 foreign key (projectInfoId)
      references project_info (projectInfoId) on delete restrict on update restrict;

alter table project_step add constraint FK_Reference_16 foreign key (sysRoleId)
      references sys_role (sysRoleId) on delete restrict on update restrict;

alter table project_step_logs add constraint FK_Reference_15 foreign key (stepId)
      references project_step (stepId) on delete restrict on update restrict;

alter table project_step_logs_pic add constraint FK_Reference_21 foreign key (projectStepLogId)
      references project_step_logs (projectStepLogId) on delete restrict on update restrict;

alter table sys_permission_role add constraint FK_Reference_10 foreign key (permissionId)
      references sys_permission (permissionId) on delete restrict on update restrict;

alter table sys_permission_role add constraint FK_Reference_11 foreign key (sysRoleId)
      references sys_role (sysRoleId) on delete restrict on update restrict;

alter table sys_role_user add constraint FK_Reference_8 foreign key (sysUserId)
      references sys_user (sysUserId) on delete restrict on update restrict;

alter table sys_role_user add constraint FK_Reference_9 foreign key (sysRoleId)
      references sys_role (sysRoleId) on delete restrict on update restrict;

alter table user_relation add constraint FK_Reference_1 foreign key (sysUserId)
      references sys_user (sysUserId) on delete restrict on update restrict;

alter table user_relation add constraint FK_Reference_12 foreign key (customerShopId)
      references customer_shops (customerShopId) on delete restrict on update restrict;

alter table user_relation add constraint FK_Reference_2 foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table project_handover_detail add constraint FK_Reference_25 foreign key (projectInfoId)
      references project_info (projectInfoId) on delete restrict on update restrict;

/*
	初始化角色信息
*/	  
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('1', 'ROLE_ADMIN', '管理员', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('2', 'ROLE_SHOPMANAGER', '店长', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('3', 'ROLE_TEAMLEADER', '施工队长', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('4', 'ROLE_DESIGN', '设计', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('5', 'ROLE_SUPERVISOR', '监理', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('6', 'ROLE _SELLER', '销售', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('7', 'ROLE_PMDIRECTOR', '总监', '1');
INSERT INTO `sys_role` (`sysRoleId`, `name`, `description`, `status`) VALUES ('8', 'ROLE_CFSSME', '财务', '1');
