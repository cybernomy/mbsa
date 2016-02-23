INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (1, 'Хозяйственные операции', 6, 18, false, false, 2, true, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/OPERATION'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/OPERATIONMODEL'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/ECONOMICSPEC'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/ECONOMICSPECMODEL'), NULL, 'com.mg.merp.account.support.ui.EconomicModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (2, 'Входящие банковские документы', 9, 14, false, false, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTIN'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTMODELIN'), NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (3, 'Исходящие банковские документы', 10, 15, false, false, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTMODELOUT'), NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (4, 'Прочие банковские документы', 8, 13, false, false, 2, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTOTHER'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BANKDOCUMENTMODELOTHER'), NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (5, 'Приходные кассовые документы', 11, 16, false, false, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/CASHDOCUMENTIN'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/CASHDOCUMENTMODELIN'), NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (6, 'Расходные кассовые документы', 12, 17, false, false, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/CASHDOCUMENTOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/CASHDOCUMENTMODELOUT'), NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (7, 'Внутренние документы (накладные)', 19, 20, false, true, 2, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALINVOICEHEAD'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALINVOICEHEADMODEL'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALINVOICESPEC'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALINVOICESPECMODEL'), NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (8, 'Внутренние акты', 28, 29, false, true, 2, false, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALACTHEAD'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALACTHEADMODEL'), (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/INTERNALACTSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (9, 'Зарезервировано1', 0, 0, false, false, 0, true, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (10, 'Зарезервировано2', 0, 0, false, false, 0, true, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (11, 'Зарезервировано3', 0, 0, false, false, 0, true, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12, 'Входящие счета - фактуры', 21, 22, true, true, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURAHEADIN'), (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURAHEADMODELIN'), (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURASPECIN'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (13, 'Исходящие счета - фактуры', 23, 24, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURAHEADOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURAHEADMODELOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/FACTURA/FACTURASPECOUT'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (14, 'Книга покупок', 26, NULL, false, false, 2, true, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/BUYBOOK'), NULL, NULL, NULL, NULL, NULL);
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (15, 'Книга продаж', 27, NULL, false, false, 2, true, (select c.id from sys_class c where c.bean_name = 'MERP/ACCOUNT/SALEBOOK'), NULL, NULL, NULL, NULL, NULL);
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (16, 'Приходный ордер', 30, 32, true, true, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTHEADIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTHEADMODELIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTSPECIN'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (17, 'Расходный ордер', 31, 33, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTHEADOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTHEADMODELOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/WAREHOUSEDOCUMENTSPECOUT'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (18, 'Инвентаризационный акт', 34, NULL, false, true, 2, false, (select c.id from sys_class c where c.bean_name='WAREHOUSE/INVENTORYACTHEAD'), NULL, (select c.id from sys_class c where c.bean_name='WAREHOUSE/INVENTORYACTSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (19, 'Контракт', 35, 36, false, false, 2, false, (select c.id from sys_class c where c.bean_name = 'MERP/CONTRACT/CONTRACT') , NULL, NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (20, 'Финансовые операции', 40, 53, false, false, 2, true, (select c.id from sys_class c where c.bean_name = 'MERP/FINANCE/OPERATION'), (select c.id from sys_class c where c.bean_name = 'MERP/FINANCE/OPERATIONMODEL'), (select c.id from sys_class c where c.bean_name = 'MERP/FINANCE/SPECIFICATION'), (select c.id from sys_class c where c.bean_name = 'MERP/FINANCE/SPECIFICATIONMODEL'), NULL, 'com.mg.merp.finance.support.ui.FinOperModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (21, 'Заказы поставщикам', 41, 43, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERHEADSUP'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERHEADMODELSUP'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERSPECSUP'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERSPECMODELSUP'), NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (22, 'Заказы покупателей', 42, 44, true, true, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERHEADCUS'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERHEADMODELCUS'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERSPECCUS'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/ORDERSPECMODELCUS'), NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (23, 'Входящие накладные', 45, 47, true, true, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICEHEADIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICEHEADMODELIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICESPECIN'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (24, 'Исходящие накладные', 46, 48, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICEHEADOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICEHEADMODELOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/INVOICESPECOUT'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (25, 'Входящие счета', 49, 51, true, true, 0, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLHEADIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLHEADMODELIN'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLSPECIN'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (26, 'Исходящие счета', 50, 52, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLHEADOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLHEADMODELOUT'), (select c.id from sys_class c where c.bean_name = 'MERP/WAREHOUSE/BILLSPECOUT'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (6001, 'Авансовые отчеты', 6001, 6002, true, true, 0, false, (select id from sys_class where bean_name='ACCOUNT/ADVANCEREPHEAD'), (select id from sys_class where bean_name='ACCOUNT/ADVANCEREPHEADMODEL'), (select id from sys_class where bean_name='ACCOUNT/ADVANCEREPSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (10501, 'Своды начислений/удержаний по аналитике', 10501, 10502, true, true, 2, false, (select c1.id from sys_class c1 where c1.bean_name = 'SALARY/FEESUMMARYHEAD'), NULL, NULL, NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12001, 'Акт выпуска продукции', 12502, 12510, true, true, 1, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/OUTPUTPRODUCTHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/OUTPUTPRODUCTMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/OUTPUTPRODUCTSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12002, 'Акт на списание материалов в НЗП', 12503, 12511, true, true, 1, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTMATERIALHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTMATERIALMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/INPUTMATERIALSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12003, 'Акт на списание времени, отработанного РС в НЗП', 12504, 12512, true, true, 1, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTLABORHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTLABORMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/INPUTLABORSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12004, 'Акт на списание времени, отработанного оборудованием в НЗП', 12505, 12513, true, true, 0, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTMACHINEHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/INPUTMACHINEMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/INPUTMACHINESPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12005, 'Акт на списание потерь с операции', 12506, 12514, true, true, 0, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPPRODUCTHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPPRODUCTMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/SCRAPPRODUCTSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12006, 'Акт на списание потерь материалов', 12507, 12515, true, true, 0, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPMATERIALHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPMATERIALMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/SCRAPMATERIALSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12007, 'Акт на списание потерь времени, отработанного РС', 12508, 12516, true, true, 0, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPLABORHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPLABORMODEL') , (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/SCRAPLABORSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12008, 'Акт на списание потерь времени, отработанного оборудованием', 12509, 12517, true, true, 0, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPMACHINEHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/SCRAPMACHINEMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/SCRAPMACHINESPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12009, 'Документ по отклонения', 12518, 12519, false, true, 1, false, (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/VARIANCEDOCUMENTHEAD'), (select c1.id from sys_class c1 where c1.bean_name = 'MANUFACTURE/VARIANCEDOCUMENTMODEL'), (select c2.id from sys_class c2 where c2.bean_name = 'MANUFACTURE/VARIANCEDOCUMENTSPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (12500, 'Розничная торговля / Документы на отпуск', 13200, 13201, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/RETAIL/INVOICEHEAD'), (select c.id from sys_class c where c.bean_name = 'MERP/RETAIL/INVOICEHEADMODEL'), (select c.id from sys_class c where c.bean_name = 'MERP/RETAIL/INVOICESPEC'), NULL, NULL, 'com.mg.merp.document.support.ui.StandartDocModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (13000,  'Реестр Обязательств', 13401, 13402, true, true, 1, false, (select c.id from sys_class c where c.bean_name = 'MERP/PAYMENTCONTROL/LIABILITY'), (select c.id from sys_class c where c.bean_name = 'MERP/PAYMENTCONTROL/LIABILITYMODEL'), NULL, NULL, NULL, 'com.mg.merp.paymentcontrol.support.ui.LiabilityModelSearchHelp');
INSERT INTO DOCSECTION (ID, DSNAME, FOLDERTYPE, MODEL_FOLDERTYPE, WITH_TAXES, WITH_SPEC, DIRECTION, DUMMY, CLASS_ID, MODEL_CLASS_ID, SPEC_CLASS_ID, MODEL_SPEC_CLASS_ID, DOC_SEARCH_HELP, DOCMODEL_SEARCH_HELP) VALUES (13500,  'Журнал платежей', 13300, 13301, false, false, 2, true, (select c.id from sys_class c where c.bean_name = 'MERP/PAYMENTCONTROL/PAYMENT'), (select c.id from sys_class c where c.bean_name = 'MERP/PAYMENTCONTROL/PAYMENTMODEL'), NULL, NULL, NULL, 'com.mg.merp.paymentalloc.support.ui.PaymentModelSearchHelp');