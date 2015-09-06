/* system tenant */

INSERT INTO SYS_CLIENT (ID, CODE, NAME, DESCRIPTION, IS_ACTIVE, LANGUAGE) VALUES (1, '00001', '00001', NULL, TRUE, 'ru_RU');

INSERT INTO SYSTEMCONFIG (MAJOR_VERSION, MINOR_VERSION, "RELEASE") VALUES (4, 35, 0);

/* security */

INSERT INTO JG_APP_PRINCIPAL (ID, NAME) VALUES (setval('jg_app_principal_seq', 1), 'guest');
INSERT INTO JG_APP_PRINCIPAL (ID, NAME) VALUES (setval('jg_app_principal_seq', 2), 'admin');
INSERT INTO JG_APP_PRINCIPAL (ID, NAME) VALUES (setval('jg_app_principal_seq', 3), 'standartuser');
INSERT INTO JG_APP_PRINCIPAL (ID, NAME) VALUES (setval('jg_app_principal_seq', 4), 'systemuser');

INSERT INTO JG_USER (ID) VALUES (setval('jg_user_seq', 1));
INSERT INTO JG_USER (ID) VALUES (setval('jg_user_seq', 2));
INSERT INTO JG_USER (ID) VALUES (setval('jg_user_seq', 3));
INSERT INTO JG_USER (ID) VALUES (setval('jg_user_seq', 4));

INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 1, false, 'login', 'guest');
INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 1, false, 'password', 'b95400adf3b5fd5610e67a4ecf6de002d6975e3d16429fcfbf8f676bd081eb59');
INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 2, false, 'login', 'admin');
INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 2, false, 'password', '8580d8291d9c3923b45d680efcee30f9ab9ca5a378f06e76c03fe3a5a2512048');
INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 3, false, 'login', 'scheduler');
INSERT INTO JG_CREDENTIAL (ID, USER_ID, PUBLIC_VISIBILITY, CRED_NAME, CRED_VALUE) VALUES (nextval('jg_credential_seq'), 3, false, 'password', '5d4b51e0217503ff1f9186040ee1de15dbf620b2a0ffc99f5b87e7f7c0e2acfb');

INSERT INTO JG_DOMAIN (ID, NAME) VALUES (setval('jg_domain_seq', 1), 'public');
INSERT INTO JG_DOMAIN (ID, NAME) VALUES (setval('jg_domain_seq', 2), 'superuser');
INSERT INTO JG_DOMAIN (ID, NAME) VALUES (setval('jg_domain_seq', 3), 'standartuser');
INSERT INTO JG_DOMAIN (ID, NAME) VALUES (setval('jg_domain_seq', 4), 'systemuser');

INSERT INTO JG_PERMISSION (ID, CLASS, NAME, ACTIONS, DOMAIN_ID) VALUES (setval('jg_permission_seq', 1), 'com.mg.framework.api.security.StartApplicationPermission', 'guest', NULL, 1);
INSERT INTO JG_PERMISSION (ID, CLASS, NAME, ACTIONS, DOMAIN_ID) VALUES (setval('jg_permission_seq', 2), 'java.security.AllPermission', 'allAccess', NULL, 2);
INSERT INTO JG_PERMISSION (ID, CLASS, NAME, ACTIONS, DOMAIN_ID) VALUES (setval('jg_permission_seq', 3), 'com.mg.framework.api.security.StartApplicationPermission', 'standartuser', NULL, 3);

INSERT INTO JG_PRINCIPAL (ID, NAME, APPLICATION_NAME, CLASS_NAME) VALUES (setval('jg_principal_seq', 1), 'guest', 'MBSASystem', 'com.mg.merp.security.Principal');
INSERT INTO JG_PRINCIPAL (ID, NAME, APPLICATION_NAME, CLASS_NAME) VALUES (setval('jg_principal_seq', 2), 'admin', 'MBSASystem', 'com.mg.merp.security.Principal');
INSERT INTO JG_PRINCIPAL (ID, NAME, APPLICATION_NAME, CLASS_NAME) VALUES (setval('jg_principal_seq', 3), 'standartuser', 'MBSASystem', 'com.mg.merp.security.Principal');
INSERT INTO JG_PRINCIPAL (ID, NAME, APPLICATION_NAME, CLASS_NAME) VALUES (setval('jg_principal_seq', 4), 'systemuser', 'MBSASystem', 'com.mg.merp.security.Principal');

INSERT INTO JG_PRINCIPAL_DOMAIN (DOMAIN_ID, PRINCIPAL_ID) VALUES (1, 1);
INSERT INTO JG_PRINCIPAL_DOMAIN (DOMAIN_ID, PRINCIPAL_ID) VALUES (2, 2);
INSERT INTO JG_PRINCIPAL_DOMAIN (DOMAIN_ID, PRINCIPAL_ID) VALUES (3, 3);
INSERT INTO JG_PRINCIPAL_DOMAIN (DOMAIN_ID, PRINCIPAL_ID) VALUES (4, 4);

INSERT INTO JG_USER_PRINCIPAL (USER_ID, PRINCIPAL_ID, DEFINITION, "active") VALUES (1, 1, 'guest', true);
INSERT INTO JG_USER_PRINCIPAL (USER_ID, PRINCIPAL_ID, DEFINITION, "active") VALUES (2, 2, 'admin', true);
INSERT INTO JG_USER_PRINCIPAL (USER_ID, PRINCIPAL_ID, DEFINITION, "active") VALUES (3, 3, 'standartuser', true);
INSERT INTO JG_USER_PRINCIPAL (USER_ID, PRINCIPAL_ID, DEFINITION, "active") VALUES (4, 4, 'systemuser', true);

INSERT INTO SEC_GROUPS (ID,NAME,CLIENT_ID) VALUES (setval('sec_groups_id_gen', 1),'Администраторы',1);
INSERT INTO SEC_GROUPS (ID,NAME,CLIENT_ID) VALUES (setval('sec_groups_id_gen', 2),'scheduler',1);

INSERT INTO SEC_USERS (ID,NAME,FULLNAME,CLIENT_ID) VALUES (setval('sec_user_id_gen', 1),'admin','Системный администратор',1);
INSERT INTO SEC_USERS (ID,NAME,FULLNAME,CLIENT_ID) VALUES (setval('sec_user_id_gen', 2),'scheduler','scheduler',1);

INSERT INTO SEC_LINK_USERS_GROUPS (ID,USER_ID,GROUP_ID,CLIENT_ID) VALUES (setval('sec_link_users_groups_id_gen', 1),1,1,1);
INSERT INTO SEC_LINK_USERS_GROUPS (ID,USER_ID,GROUP_ID,CLIENT_ID) VALUES (setval('sec_link_users_groups_id_gen', 2),2,2,1);

INSERT INTO JG_ST_USER (ID, NAME) VALUES (setval('jg_st_user_seq', 1), 'default');

INSERT INTO JG_ST_CREDENTIAL (ID, CRED_NAME, PUBLIC_VISIBILITY, REQUIRED, IDTITY, ST_USER_ID) VALUES (setval('jg_st_credential_seq', 1), 'login', false, true, true, currval('jg_st_user_seq'));
INSERT INTO JG_ST_CREDENTIAL (ID, CRED_NAME, PUBLIC_VISIBILITY, REQUIRED, IDTITY, ST_USER_ID) VALUES (setval('jg_st_credential_seq', 2), 'password', false, true, false, currval('jg_st_user_seq'));

/* configurations */

INSERT INTO WH_CONFIG (CLIENT_ID) VALUES (1);

INSERT INTO STL_CONFIG (CLIENT_ID) VALUES (1);

INSERT INTO PMC_CONFIG (CLIENT_ID) VALUES (1);

INSERT INTO MEASURE (ID, UPCODE, CODE, MNAME, DIVIDE, UNIVERSAL_CODE, CLIENT_ID, INTERNAL_CODE) VALUES (nextval('measure_id_gen'), 'TICK', 'TICK', 'TICK', false, NULL, 1, NULL);

INSERT INTO MF_CONFIG (CLIENT_ID, TICK_UM, MAIN_TIME_UM) VALUES (1, currval('measure_id_gen'), currval('measure_id_gen'));

INSERT INTO FIN_CONFIG (CLIENT_ID) VALUES (1);

INSERT INTO ACC_CONFIG (CLIENT_ID) VALUES (1);

INSERT INTO PREF_CONFIG (ID, CLIENT_ID) VALUES (1, 1);

INSERT INTO TAB_CONFIG (CLIENT_ID) VALUES (1);
