INSERT INTO SAMPLE_UNIT (ID, LIST, NAME, CODE, UNIT_LEVEL,PARENT_ID) values ('acl_unit1', '1', '顶级单位', '1', '0',null);
INSERT INTO SAMPLE_UNIT (ID, LIST, NAME, CODE, UNIT_LEVEL,PARENT_ID) values ('acl_unit2', '1', '一级单位', '1-1', '1','acl_unit1');

Insert into SAMPLE_ROLE (ID,INFO,LIST,NAME,SYSTEM,UNIT_ID) values ('acl_role1',null,98,'acl_role1',0,'acl_unit2');
Insert into SAMPLE_ROLE (ID,INFO,LIST,NAME,SYSTEM,UNIT_ID) values ('acl_role2',null,99,'acl_role2',0,'acl_unit2');

Insert into SAMPLE_USER (ID,EMAIL,FAX,LOGIN_NAME,MALE,MOBILE,NAME,NAME_PY,PASSWD,STATUS,TEL,ROLE_ID,UNIT_ID) values ('acl_user1','peream@gmail.com','51616113','acl_user1',1,'13691517931','测试用户1','csyh1','7c4a8d09ca3762af61e59520943dc26494f8941b','NORMAL','51616881','acl_role1','acl_unit2');

Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column1',1,'default.gif',null,'COLUMN','栏目1','A_XTGL','acl/column1.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column1_add',1,'default.gif','acl_column1','NOT_MENU','栏目1_add','A_XTGL','acl/column1/add.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column2',2,'default.gif',null,'COLUMN','栏目2','A_XTGL','acl/column2.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column2_tab1',1,'default.gif','acl_column2','BOX_TAB','标签1','A_XTGL','acl/boxtab1.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column2_tab1_add',1,'default.gif','acl_column2_tab1','NOT_MENU','标签1_add','A_XTGL','acl/boxtab1/mod.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column2_tab2',2,'default.gif','acl_column2','BOX_TAB','标签2','A_XTGL','acl/boxtab2.do','GET');
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('acl_column3',1,'default.gif',null,'COLUMN','栏目3','A_XTGL','acl/column3.do','GET');

Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column1','acl_column1','acl_role1');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2','acl_column2','acl_role1');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2_tab1','acl_column2_tab1','acl_role1');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2_tab2','acl_column2_tab2','acl_role1');

Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column1_role2','acl_column1','acl_role2');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2_role2','acl_column2','acl_role2');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2_tab1_role2','acl_column2_tab1','acl_role2');
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('acl_column2_tab2_role2','acl_column2_tab2','acl_role2');