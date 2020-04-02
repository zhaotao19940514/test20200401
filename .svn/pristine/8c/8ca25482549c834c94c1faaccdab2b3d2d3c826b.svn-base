--------------------------------------------------------
--  文件已创建 - 星期三-十一月-23-2016   
--------------------------------------------------------
DROP TABLE "CSS_CRON_EXCLUSIVE_TASK" cascade constraints;
DROP TABLE "CSS_RESOURCE" cascade constraints;
DROP TABLE "CSS_ROLE" cascade constraints;
DROP TABLE "CSS_ROLE_RESOURCE" cascade constraints;
DROP TABLE "CSS_SCHEDULE_LOG" cascade constraints;
DROP TABLE "CSS_SYSTEM_INFO" cascade constraints;
DROP TABLE "CSS_SYSTEM_LOG" cascade constraints;
DROP TABLE "CSS_USER" cascade constraints;
DROP TABLE "CSS_UNIT" cascade constraints;
--------------------------------------------------------
--  DDL for Table CSS_CRON_EXCLUSIVE_TASK
--------------------------------------------------------

  CREATE TABLE "CSS_CRON_EXCLUSIVE_TASK" 
   (	"ID" VARCHAR2(200 BYTE), 
	"RUNNING" NUMBER(1,0) DEFAULT 0, 
	"UPDATE_TIME" DATE, 
	"NODE_TAG" VARCHAR2(100 BYTE), 
	"MD5" VARCHAR2(50 BYTE)
   ) ;
 

   COMMENT ON COLUMN "CSS_CRON_EXCLUSIVE_TASK"."ID" IS '任务ID';
 
   COMMENT ON COLUMN "CSS_CRON_EXCLUSIVE_TASK"."RUNNING" IS '运行状态';
 
   COMMENT ON COLUMN "CSS_CRON_EXCLUSIVE_TASK"."UPDATE_TIME" IS '最后更新时间';
 
   COMMENT ON COLUMN "CSS_CRON_EXCLUSIVE_TASK"."NODE_TAG" IS '节点标识';
--------------------------------------------------------
--  DDL for Table CSS_RESOURCE
--------------------------------------------------------

  CREATE TABLE "CSS_RESOURCE" 
   (	"ID" VARCHAR2(32 BYTE), 
	"LIST" NUMBER(10,0), 
	"LOGO_PIC" VARCHAR2(100 BYTE) DEFAULT 'default.gif', 
	"MENU_ID" VARCHAR2(32 BYTE), 
	"MENU_TYPE" VARCHAR2(10 BYTE), 
	"NAME" VARCHAR2(200 BYTE), 
	"TYPE_CODE" VARCHAR2(20 BYTE), 
	"URL" VARCHAR2(300 BYTE), 
	"REQUEST_METHOD" VARCHAR2(10 BYTE) DEFAULT 'GET'
   ) ;
 

   COMMENT ON COLUMN "CSS_RESOURCE"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."LIST" IS '排序';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."MENU_ID" IS '父菜单ID';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."MENU_TYPE" IS '菜单类型';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."NAME" IS '名称';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."TYPE_CODE" IS '类型代号';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."URL" IS 'url';
 
   COMMENT ON COLUMN "CSS_RESOURCE"."REQUEST_METHOD" IS '请求方法';
--------------------------------------------------------
--  DDL for Table CSS_ROLE
--------------------------------------------------------

  CREATE TABLE "CSS_ROLE" 
   (	"ID" VARCHAR2(32 BYTE), 
	"INFO" VARCHAR2(300 BYTE), 
	"LIST" NUMBER(10,0), 
	"NAME" VARCHAR2(100 BYTE), 
	"SYSTEM" NUMBER(1,0), 
	"UNIT_ID" VARCHAR2(32 BYTE)
   ) ;
 

   COMMENT ON COLUMN "CSS_ROLE"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_ROLE"."INFO" IS '备注';
 
   COMMENT ON COLUMN "CSS_ROLE"."LIST" IS '排序';
 
   COMMENT ON COLUMN "CSS_ROLE"."NAME" IS '名字';
 
   COMMENT ON COLUMN "CSS_ROLE"."SYSTEM" IS '是否系统自带';
 
   COMMENT ON COLUMN "CSS_ROLE"."UNIT_ID" IS '单位';
--------------------------------------------------------
--  DDL for Table CSS_ROLE_RESOURCE
--------------------------------------------------------

  CREATE TABLE "CSS_ROLE_RESOURCE" 
   (	"ID" VARCHAR2(32 BYTE), 
	"RESOURCE_ID" VARCHAR2(32 BYTE), 
	"ROLE_ID" VARCHAR2(32 BYTE)
   ) ;
 

   COMMENT ON COLUMN "CSS_ROLE_RESOURCE"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_ROLE_RESOURCE"."RESOURCE_ID" IS '资源id';
 
   COMMENT ON COLUMN "CSS_ROLE_RESOURCE"."ROLE_ID" IS '角色id';
--------------------------------------------------------
--  DDL for Table CSS_SCHEDULE_LOG
--------------------------------------------------------

  CREATE TABLE "CSS_SCHEDULE_LOG" 
   (	"ID" VARCHAR2(32 CHAR), 
	"END_TIME" DATE, 
	"TASK_NAME" VARCHAR2(100 CHAR), 
	"CURRENT_CRON" VARCHAR2(50 CHAR), 
	"BY_SYSTEM" NUMBER(1,0), 
	"START_TIME" DATE, 
	"EXEC_TIME" NUMBER(19,0) DEFAULT 0
   ) ;
 

   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."END_TIME" IS '结束时间';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."TASK_NAME" IS '任务名称';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."CURRENT_CRON" IS '表达式';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."BY_SYSTEM" IS '系统？';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."START_TIME" IS '启动时间';
 
   COMMENT ON COLUMN "CSS_SCHEDULE_LOG"."EXEC_TIME" IS '花费时间';
--------------------------------------------------------
--  DDL for Table CSS_SYSTEM_INFO
--------------------------------------------------------

  CREATE TABLE "CSS_SYSTEM_INFO" 
   (	"ID" VARCHAR2(32 BYTE), 
	"CONF_TYPE" VARCHAR2(20 BYTE), 
	"USER_ID" VARCHAR2(32 BYTE), 
	"JSON_STR" VARCHAR2(4000 BYTE), 
	"ZIPED" NUMBER(1,0) DEFAULT 0, 
	"UPDATE_TIME" DATE DEFAULT sysdate
   ) ;
 

   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."CONF_TYPE" IS '配置类型';
 
   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."USER_ID" IS '用户ID';
 
   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."JSON_STR" IS 'json字符串';
 
   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."ZIPED" IS '是否压缩';
 
   COMMENT ON COLUMN "CSS_SYSTEM_INFO"."UPDATE_TIME" IS '更新时间';
--------------------------------------------------------
--  DDL for Table CSS_SYSTEM_LOG
--------------------------------------------------------

  CREATE TABLE "CSS_SYSTEM_LOG" 
   (	"ID" VARCHAR2(32 BYTE), 
	"INFO" VARCHAR2(300 BYTE), 
	"IP" VARCHAR2(50 BYTE), 
	"LOG_TYPE" VARCHAR2(10 BYTE), 
	"OPTIME" DATE, 
	"USER_ID" VARCHAR2(32 BYTE)
   ) ;
 

   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."INFO" IS '信息';
 
   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."IP" IS 'ip';
 
   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."LOG_TYPE" IS '日志类型';
 
   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."OPTIME" IS '操作时间';
 
   COMMENT ON COLUMN "CSS_SYSTEM_LOG"."USER_ID" IS '操作用户id';
--------------------------------------------------------
--  DDL for Table CSS_USER
--------------------------------------------------------

  CREATE TABLE "CSS_USER" 
   (	"ID" VARCHAR2(32 BYTE), 
	"EMAIL" VARCHAR2(200 BYTE), 
	"FAX" VARCHAR2(50 BYTE), 
	"LOGIN_NAME" VARCHAR2(200 BYTE), 
	"MALE" NUMBER(1,0), 
	"MOBILE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(200 BYTE), 
	"NAME_PY" VARCHAR2(200 BYTE), 
	"PASSWD" VARCHAR2(512 BYTE), 
	"STATUS" VARCHAR2(10 BYTE), 
	"TEL" VARCHAR2(50 BYTE), 
	"ROLE_ID" VARCHAR2(32 BYTE), 
	"UNIT_ID" VARCHAR2(32 BYTE)
   ) ;
 

   COMMENT ON COLUMN "CSS_USER"."ID" IS 'id';
 
   COMMENT ON COLUMN "CSS_USER"."EMAIL" IS '邮箱';
 
   COMMENT ON COLUMN "CSS_USER"."FAX" IS '传真';
 
   COMMENT ON COLUMN "CSS_USER"."LOGIN_NAME" IS '登录名';
 
   COMMENT ON COLUMN "CSS_USER"."MALE" IS '性别';
 
   COMMENT ON COLUMN "CSS_USER"."MOBILE" IS '手机';
 
   COMMENT ON COLUMN "CSS_USER"."NAME" IS '名字';
 
   COMMENT ON COLUMN "CSS_USER"."NAME_PY" IS '名字简拼';
 
   COMMENT ON COLUMN "CSS_USER"."PASSWD" IS '密码';
 
   COMMENT ON COLUMN "CSS_USER"."STATUS" IS '状态';
 
   COMMENT ON COLUMN "CSS_USER"."TEL" IS '电话';
 
   COMMENT ON COLUMN "CSS_USER"."ROLE_ID" IS '角色id';
 
   COMMENT ON COLUMN "CSS_USER"."UNIT_ID" IS '单位';
--------------------------------------------------------
--  DDL for Table CSS_UNIT
--------------------------------------------------------

  CREATE TABLE "CSS_UNIT" 
   (	"ID" VARCHAR2(32 BYTE) NOT NULL ENABLE, 
	"LIST" NUMBER(10,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"CODE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"PARENT_ID" VARCHAR2(32 BYTE), 
	"UNIT_LEVEL" NUMBER(10,0) DEFAULT 0 NOT NULL ENABLE, 
	"GLOBAL_LIST" VARCHAR2(30 BYTE) NOT NULL ENABLE
   ) ;
 

   COMMENT ON COLUMN "CSS_UNIT"."LIST" IS '序号';
 
   COMMENT ON COLUMN "CSS_UNIT"."NAME" IS '单位名称';
 
   COMMENT ON COLUMN "CSS_UNIT"."CODE" IS '编号';
 
   COMMENT ON COLUMN "CSS_UNIT"."PARENT_ID" IS '父级ID';
 
   COMMENT ON COLUMN "CSS_UNIT"."UNIT_LEVEL" IS '级别';
   
   COMMENT ON COLUMN "CSS_UNIT"."GLOBAL_LIST" IS '全局排序号';
--------------------------------------------------------
--  DDL for Index CRON_EXCLUSIVE_TASK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_CRON_EXCLUSIVE_TASK_PK" ON "CSS_CRON_EXCLUSIVE_TASK" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_RESOURCE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_RESOURCE_UK1" ON "CSS_RESOURCE" ("URL", "REQUEST_METHOD") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_RESOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_RESOURCE_PK" ON "CSS_RESOURCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_ROLE_PK" ON "CSS_ROLE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_ROLE_UK1" ON "CSS_ROLE" ("NAME") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_RESOURCE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ROLE_RESOURCE_UK1" ON "CSS_ROLE_RESOURCE" ("RESOURCE_ID", "ROLE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_RESOURCE_INDEX1
--------------------------------------------------------

  CREATE INDEX "CSS_ROLE_RESOURCE_IND1" ON "CSS_ROLE_RESOURCE" ("RESOURCE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_RESOURCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ROLE_RESOURCE_PK" ON "CSS_ROLE_RESOURCE" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_ROLE_RESOURCE_INDEX2
--------------------------------------------------------

  CREATE INDEX "CSS_ROLE_RESOURCE_IND2" ON "CSS_ROLE_RESOURCE" ("ROLE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SCHEDULE_LOG_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_SCHEDULE_LOG_PK" ON "CSS_SCHEDULE_LOG" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SCHEDULE_LOG_INDEX1
--------------------------------------------------------

  CREATE INDEX "CSS_SCHEDULE_LOG_IND1" ON "CSS_SCHEDULE_LOG" ("START_TIME") 
  ;
--------------------------------------------------------
--  DDL for Index SCHEDULE_LOG_INDEX2
--------------------------------------------------------

  CREATE INDEX "CSS_SCHEDULE_LOG_IND2" ON "CSS_SCHEDULE_LOG" ("BY_SYSTEM") 
  ;
--------------------------------------------------------
--  DDL for Index SYSTEM_INFO_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_SYSTEM_INFO_UK1" ON "CSS_SYSTEM_INFO" ("CONF_TYPE", "USER_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYSTEM_INFO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_SYSTEM_INFO_PK" ON "CSS_SYSTEM_INFO" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYSTEM_LOG_INDEX1
--------------------------------------------------------

  CREATE INDEX "CSS_SYSTEM_LOG_INDEX1" ON "CSS_SYSTEM_LOG" ("USER_ID") 
  ;
--------------------------------------------------------
--  DDL for Index SYS_C0014752
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_SYSTEM_LOG_PK" ON "CSS_SYSTEM_LOG" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_USER_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_USER_UK1" ON "CSS_USER" ("LOGIN_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_USER_INDEX1
--------------------------------------------------------

  CREATE INDEX "CSS_ACL_USER_INDEX1" ON "CSS_USER" ("ROLE_ID") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_USER_INDEX2
--------------------------------------------------------

  CREATE INDEX "CSS_ACL_USER_INDEX2" ON "CSS_USER" ("NAME_PY") 
  ;
--------------------------------------------------------
--  DDL for Index ACL_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_ACL_USER_PK" ON "CSS_USER" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index CSS_UNIT_INDEX1
--------------------------------------------------------

  CREATE INDEX "CSS_UNIT_INDEX1" ON "CSS_UNIT" ("PARENT_ID") 
  ;
--------------------------------------------------------
--  DDL for Index CSS_UNIT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CSS_UNIT_PK" ON "CSS_UNIT" ("ID") 
  ;
--------------------------------------------------------
--  Constraints for Table CSS_CRON_EXCLUSIVE_TASK
--------------------------------------------------------

  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" ADD CONSTRAINT "CSS_CRON_EXCLUSIVE_TASK_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" MODIFY ("RUNNING" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" MODIFY ("UPDATE_TIME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" MODIFY ("NODE_TAG" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_CRON_EXCLUSIVE_TASK" MODIFY ("MD5" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_RESOURCE
--------------------------------------------------------

  ALTER TABLE "CSS_RESOURCE" ADD CONSTRAINT "CSS_ACL_RESOURCE_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_RESOURCE" ADD CONSTRAINT "CSS_ACL_RESOURCE_UK1" UNIQUE ("URL", "REQUEST_METHOD") ENABLE;
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("LIST" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("MENU_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("NAME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("TYPE_CODE" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("URL" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_RESOURCE" MODIFY ("REQUEST_METHOD" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_ROLE
--------------------------------------------------------

  ALTER TABLE "CSS_ROLE" ADD CONSTRAINT "CSS_ACL_ROLE_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_ROLE" ADD CONSTRAINT "CSS_ACL_ROLE_UK1" UNIQUE ("NAME") ENABLE;
 
  ALTER TABLE "CSS_ROLE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_ROLE" MODIFY ("LIST" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_ROLE" MODIFY ("SYSTEM" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_ROLE" MODIFY ("NAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_ROLE_RESOURCE
--------------------------------------------------------

  ALTER TABLE "CSS_ROLE_RESOURCE" ADD CONSTRAINT "CSS_ROLE_RESOURCE_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_ROLE_RESOURCE" ADD CONSTRAINT "CSS_ROLE_RESOURCE_UK1" UNIQUE ("RESOURCE_ID", "ROLE_ID") ENABLE;
 
  ALTER TABLE "CSS_ROLE_RESOURCE" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_ROLE_RESOURCE" MODIFY ("RESOURCE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_ROLE_RESOURCE" MODIFY ("ROLE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_SCHEDULE_LOG
--------------------------------------------------------

  ALTER TABLE "CSS_SCHEDULE_LOG" ADD CONSTRAINT "CSS_SCHEDULE_LOG_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("END_TIME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("TASK_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("CURRENT_CRON" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("BY_SYSTEM" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("START_TIME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SCHEDULE_LOG" MODIFY ("EXEC_TIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_SYSTEM_INFO
--------------------------------------------------------

  ALTER TABLE "CSS_SYSTEM_INFO" ADD CONSTRAINT "CSS_SYSTEM_INFO_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_SYSTEM_INFO" ADD CONSTRAINT "CSS_SYSTEM_INFO_UK1" UNIQUE ("CONF_TYPE", "USER_ID") ENABLE;
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("CONF_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("USER_ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("JSON_STR" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("ZIPED" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_INFO" MODIFY ("UPDATE_TIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_SYSTEM_LOG
--------------------------------------------------------

  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_LOG" ADD PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("INFO" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("IP" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("LOG_TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("OPTIME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_SYSTEM_LOG" MODIFY ("USER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_USER
--------------------------------------------------------

  ALTER TABLE "CSS_USER" ADD CONSTRAINT "CSS_ACL_USER_PK" PRIMARY KEY ("ID") ENABLE;
 
  ALTER TABLE "CSS_USER" ADD CONSTRAINT "CSS_ACL_USER_UK1" UNIQUE ("LOGIN_NAME") ENABLE;
 
  ALTER TABLE "CSS_USER" MODIFY ("ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("LOGIN_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("MALE" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("NAME" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("NAME_PY" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("PASSWD" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("STATUS" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("ROLE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "CSS_USER" MODIFY ("UNIT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CSS_UNIT
--------------------------------------------------------

  ALTER TABLE "CSS_UNIT" ADD CONSTRAINT "CSS_UNIT_PK" PRIMARY KEY ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CSS_ROLE_RESOURCE
--------------------------------------------------------

  ALTER TABLE "CSS_ROLE_RESOURCE" ADD CONSTRAINT "CSS_ROLE_RES_RES_FK1" FOREIGN KEY ("RESOURCE_ID")
	  REFERENCES "CSS_RESOURCE" ("ID") ENABLE;
 
  ALTER TABLE "CSS_ROLE_RESOURCE" ADD CONSTRAINT "CSS_ROLE_RES_ROL_FK1" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "CSS_ROLE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CSS_SYSTEM_LOG
--------------------------------------------------------

  ALTER TABLE "CSS_SYSTEM_LOG" ADD CONSTRAINT "CSS_SYSTEM_LOG_USER_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "CSS_USER" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CSS_USER
--------------------------------------------------------

  ALTER TABLE "CSS_USER" ADD CONSTRAINT "CSS_ACL_USER_ROLE_FK1" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "CSS_ROLE" ("ID") ENABLE;
