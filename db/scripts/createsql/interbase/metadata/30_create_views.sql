CREATE VIEW BANKREQUIS(
    ID,
    PARTNER_ID,
    BANK,
    BANK_ACC,
    CORR_ACC,
    BANK_ADDR,
    BANK_CITY,
    BANK_IDENT,
    IS_DEFAULT,
    BANK_BRANCH,
    UNID)
AS
select
  a.id,
  a.contractor_id,
  b.name,
  a.account,
  b.corr_acc,
  b.address,
  b.city,
  b.bik,
  a.is_default,
  b.branch,
  a.unid
from ref_bank_account a left join ref_bank b on b.id = a.bank_id
;



CREATE VIEW V_CONTRACT(
    ID,
    FOLDER_ID,
    DOCTYPE,
    DOCNUMBER,
    DOCDATE,
    BASEDOC_ID,
    BASEDOCTYPE,
    BASEDOCNUMBER,
    BASEDOCDATE,
    PARTNERFROM,
    PARTNERTO,
    CURRENCY_CODE,
    CURCOURCE,
    SUMMACUR,
    SUMMANAT,
    INCOMING_NUMBER,
    REGISTRATION_DATE,
    STATUS,
    RATIFICATION_DATE,
    COMPLETED_DATE,
    BEGINACTION_DATE,
    ENDACTION_DATE,
    DESCRIPTION,
    COMMENT,
    CONTRACTORBANKREQ_ID,
    CONTRACTOR_RESPONSIBLE,
    BANKREQ_ID,
    RESPONSIBLE,
    SHIPPER,
    SHIPPERBANKREQ_ID,
    CONSIGNEE,
    CONSIGNEEBANKREQ_ID,
    CALCSUMKIND,
    CONTRACTORBANKDAYS,
    BANKDAYS,
    CONTRACTORPENNY_RATE,
    PENNY_RATE,
    SHIPPEDPAYMENT,
    RECEIVEDPAYMENT,
    SHIPPEDGOOD,
    RECEIVEDGOOD,
    FACTSHIPPEDPAYMENT,
    FACTRECEIVEDPAYMENT,
    FACTSHIPPEDGOOD,
    FACTRECEIVEDGOOD,
    PHASESHIPPEDPAYMENT,
    PHASERECEIVEDPAYMENT,
    PHASESHIPPEDGOOD,
    PHASERECEIVEDGOOD)
AS
select
d.id,
d.folder_id,
d.doctype,
d.docnumber,
d.docdate,
d.basedoc_id,
d.basedoctype,
d.basedocnumber,
d.basedocdate,
d.from_id,
d.to_id,
d.currency_code,
d.curcource,
d.summacur,
d.summanat,
  c.incoming_number,
  c.registration_date,
  c.status,
  c.ratification_date,
  c.completed_date,
  c.beginaction_date,
  c.endaction_date,
  c.description,
  c.comment,
  c.contractorbankreq_id,
  c.contractor_responsible,
  c.bankreq_id,
  c.responsible,
  c.shipper,
  c.shipperbankreq_id,
  c.consignee,
  c.consigneebankreq_id,
  c.calcsumkind,
  C.CONTRACTORBANKDAYS,
  C.BANKDAYS,
  C.CONTRACTORPENNY_RATE,
  C.PENNY_RATE,
  c.shippedpayment,
  c.receivedpayment,
  c.shippedgood,
  c.receivedgood,
  c.factshippedpayment,
  c.factreceivedpayment,
  c.factshippedgood,
  c.factreceivedgood,
  c.phaseshippedpayment,
  c.phasereceivedpayment,
  c.phaseshippedgood,
  c.phasereceivedgood
from dochead d left join contract c on d.id = c.dochead_id
;



CREATE VIEW V_DOCHEAD_ITEM(
    ID,
    FOLDER_ID,
    DOCTYPE,
    DOCNUMBER,
    DOCDATE,
    BASEDOCSECTION,
    BASEDOC_ID,
    BASEDOCTYPE,
    BASEDOCNUMBER,
    BASEDOCDATE,
    CONTRACTSECTION,
    CONTRACT_ID,
    CONTRACTTYPE,
    CONTRACTNUMBER,
    CONTRACTDATE,
    FROM_KIND,
    FROM_ID,
    FROM_CODE,
    FROM_FOLDER_ID,
    TO_KIND,
    TO_ID,
    TO_CODE,
    TO_FOLDER_ID,
    THROUGH_KIND,
    THROUGH_ID,
    THROUGH_CODE,
    THROUGH_FOLDER_ID,
    PRICELIST_ID,
    PRICELISTNAME,
    PRICETYPE_ID,
    PRICETYPECODE,
    CURRENCYCODE,
    CURCOURCE,
    CURRATETYPE_ID,
    CURRATEAUTHORITY_ID,
    CURRATETYPECODE,
    CURRATEAUTHORITYCODE,
    SUMMANAT,
    SUMMACUR,
    WEIGHT,
    VOLUME,
    CALCTAXESKIND_ID,
    CALCTAXESKINDCODE,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLSRC_FOLDER_ID,
    MOLDEST_CODE,
    MOLDEST_ID,
    MOLDEST_FOLDER_ID,
    DISCOUNT_FOLDER_ID,
    DISCOUNT_FOLDER,
    SCHEDULE_ID)
AS
select d.id, d.folder_id, t.code doctype, d.docnumber, d.docdate,
  d1.docsection, d.basedoc_id, t1.code, d.basedocnumber, d.basedocdate,
  d2.docsection, d.contract_id, t2.code, d.contractnumber, d.contractdate,
  c1.kind, d.from_id, c1.code, c1.folder_id,
  c2.kind, d.to_id, c2.code, c2.folder_id,
  c3.kind, d.through_id, c3.code, c3.folder_id,
  d.pricelist_id, pr.prname, d.pricetype_id, pt.code,
  c.code, d.curcource,
  d.curratetype_id, d.currateauthority_id, crt.code, cra.code,
  d.summanat, d.summacur, d.weight, d.volume,
  d.calctaxeskind_id, ctk.code,
  c4.code, c4.id, c5.code, c5.id,
  c6.code, c6.id, c6.folder_id, c7.code, c7.id, c7.folder_id,
  d.discount_folder_id, df.fname, d.schedule_id
from dochead d left join typedoc t on t.upcode = d.doctype
     left join typedoc t1 on t1.upcode = d.basedoctype
     left join typedoc t2 on t2.upcode = d.contracttype
     left join dochead d1 on d1.id = d.basedoc_id
     left join dochead d2 on d2.id = d.contract_id
     left join contractor c1 on c1.id = d.from_id
     left join contractor c2 on c2.id = d.to_id
     left join contractor c3 on c3.id = d.through_id
     left join currency c on c.upcode = d.currency_code
     left join pricelisthead pr on pr.id = d.pricelist_id
     left join pricetype pt on pt.id = d.pricetype_id
     left join contractor c4 on c4.id = d.stocksrc
     left join contractor c5 on c5.id = d.stockdest
     left join contractor c6 on c6.id = d.molsrc
     left join contractor c7 on c7.id = d.moldest
     left join calctaxeskind ctk on ctk.id = d.calctaxeskind_id
     left join folder df on df.id = d.discount_folder_id
     left join ref_currency_rate_type crt on crt.id = d.curratetype_id
     left join ref_currency_rate_authority cra on cra.id = d.currateauthority_id
;



CREATE VIEW V_DOCHEADMODEL_ITEM(
    ID,
    FOLDER_ID,
    MODELNAME,
    MODELDESTFOLDER_ID,
    MODELDESTFOLDER,
    DOCTYPE,
    DOCNUMBER,
    DOCDATE,
    BASEDOCSECTION,
    BASEDOC_ID,
    BASEDOCTYPE,
    BASEDOCNUMBER,
    BASEDOCDATE,
    CONTRACTSECTION,
    CONTRACT_ID,
    CONTRACTTYPE,
    CONTRACTNUMBER,
    CONTRACTDATE,
    FROM_KIND,
    FROM_ID,
    FROM_CODE,
    FROM_FOLDER_ID,
    TO_KIND,
    TO_ID,
    TO_CODE,
    TO_FOLDER_ID,
    THROUGH_KIND,
    THROUGH_ID,
    THROUGH_CODE,
    THROUGH_FOLDER_ID,
    PRICELIST_ID,
    PRICELISTNAME,
    PRICETYPE_ID,
    PRICETYPECODE,
    CURRENCYCODE,
    CURCOURCE,
    CURRATETYPE_ID,
    CURRATEAUTHORITY_ID,
    CURRATETYPECODE,
    CURRATEAUTHORITYCODE,
    SUMMANAT,
    SUMMACUR,
    WEIGHT,
    VOLUME,
    CALCTAXESKIND_ID,
    CALCTAXESKINDCODE,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLSRC_FOLDER_ID,
    MOLDEST_CODE,
    MOLDEST_ID,
    MOLDEST_FOLDER_ID,
    DISCOUNT_FOLDER_ID,
    DISCOUNT_FOLDER)
AS
select d.id, d.folder_id, d.modelname,
  d.modeldestfolder_id, fold1.fname modeldestfolder,
  t.code doctype, d.docnumber, d.docdate,
  d1.docsection, d.basedoc_id, t1.code, d.basedocnumber, d.basedocdate,
  d2.docsection, d.contract_id, t2.code, d.contractnumber, d.contractdate,
  c1.kind, d.from_id, c1.code, c1.folder_id,
  c2.kind, d.to_id, c2.code, c2.folder_id,
  c3.kind, d.through_id, c3.code, c3.folder_id,
  d.pricelist_id, pr.prname, d.pricetype_id, pt.code,
  c.code, d.curcource,
  d.curratetype_id, d.currateauthority_id, crt.code, cra.code,
  d.summanat, d.summacur, d.weight, d.volume,
  d.calctaxeskind_id, ctk.code,
  c4.code, c4.id, c5.code, c5.id,
  c6.code, c6.id, c6.folder_id, c7.code, c7.id, c7.folder_id,
  d.discount_folder_id, df.fname
from docheadmodel d join folder fold on fold.id = d.folder_id
     left join typedoc t on t.upcode = d.doctype
     left join typedoc t1 on t1.upcode = d.basedoctype
     left join typedoc t2 on t2.upcode = d.contracttype
     left join dochead d1 on d1.id = d.basedoc_id
     left join dochead d2 on d2.id = d.contract_id
     left join contractor c1 on c1.id = d.from_id
     left join contractor c2 on c2.id = d.to_id
     left join contractor c3 on c3.id = d.through_id
     left join currency c on c.upcode = d.currency_code
     left join pricelisthead pr on pr.id = d.pricelist_id
     left join pricetype pt on pt.id = d.pricetype_id
     left join contractor c4 on c4.id = d.stocksrc
     left join contractor c5 on c5.id = d.stockdest
     left join contractor c6 on c6.id = d.molsrc
     left join contractor c7 on c7.id = d.moldest
     left join calctaxeskind ctk on ctk.id = d.calctaxeskind_id
     left join folder df on df.id = d.discount_folder_id
     left join folder fold1 on fold1.id = d.modeldestfolder_id
     left join ref_currency_rate_type crt on crt.id = d.curratetype_id
     left join ref_currency_rate_authority cra on cra.id = d.currateauthority_id
;



CREATE VIEW V_DOCSPEC_BROWSE(
    ID,
    DOCHEAD_ID,
    CATALOG_ID,
    CATCODE,
    CATNAME,
    MEASURE1_ID,
    MEASURE2_ID,
    MEASURE1_CODE,
    MEASURE2_CODE,
    MEASURE_CONTROL,
    CATFOLDERID,
    QUANTITY,
    QUANTITY2,
    PRICE,
    SUMMA1,
    PRICE1,
    SUMMA,
    BESTBEFORE,
    WEIGHT,
    VOLUME,
    TAXGROUP_ID,
    TGNAME,
    PRICELISTSPEC_ID,
    ARTICUL,
    COMMENT,
    CONTRACTOR_ID,
    CONTRACTOR,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLDEST_CODE,
    MOLDEST_ID)
AS
select s.id, s.dochead_id, s.catalog_id, c.code, raisespecname(c.cname, pls.sname), s.measure1_id, s.measure2_id, m1.code, m2.code, c.measure_control, c.folder_id,
  s.quantity, s.quantity2, s.price, s.summa1, s.price1,
  s.summa, s.bestbefore, s.weight, s.volume, s.taxgroup_id, t.tgname,
  pls.id, c.articul, s.comment, s.contractor_id, cn.code,
  c4.code, c4.id, c5.code, c5.id, c6.code, c6.id, c7.code, c7.id
from docspec s left join catalog c on c.id = s.catalog_id
     left join pricelistspec pls on pls.id = s.pricelistspec_id
     left join measure m1 on m1.id = s.measure1_id
     left join measure m2 on m2.id = s.measure2_id
     left join taxgroup t on t.id = s.taxgroup_id
     left join contractor cn on cn.id = s.contractor_id
     left join contractor c4 on c4.id = s.stocksrc
     left join contractor c5 on c5.id = s.stockdest
     left join contractor c6 on c6.id = s.molsrc
     left join contractor c7 on c7.id = s.moldest
;



CREATE VIEW V_DOCSPEC_ITEM(
    ID,
    DOCHEAD_ID,
    CATALOG_ID,
    CATCODE,
    CATNAME,
    MEASURE1_ID,
    MEASURE2_ID,
    MEASURE1_CODE,
    MEASURE2_CODE,
    MEASURE_CONTROL,
    PACKAGED,
    CATFOLDERID,
    QUANTITY,
    QUANTITY2,
    PRICE,
    SUMMA1,
    PRICE1,
    SUMMA,
    BESTBEFORE,
    WEIGHT,
    VOLUME,
    TAXGROUP_ID,
    TGNAME,
    PRICELISTSPEC_ID,
    SHELFLIFE,
    SHELFLIFE_MEAS,
    PRODUCTIONDATE,
    USE_SERIAL_NUM,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLSRC_FOLDER_ID,
    MOLDEST_CODE,
    MOLDEST_ID,
    MOLDEST_FOLDER_ID,
    COMMENT,
    CONTRACTOR_ID,
    CONTRACTOR_KIND,
    CONTRACTOR_CODE,
    CONTRACTOR_FOLDER_ID,
    ORDER_SPEC_ID)
AS
select s.id, s.dochead_id, s.catalog_id, c.code, raisespecname(c.cname, pls.sname), s.measure1_id, s.measure2_id, m1.code, m2.code, c.measure_control, c.packaged, c.folder_id,
  s.quantity, s.quantity2, s.price, s.summa1, s.price1,
  s.summa, s.bestbefore, s.weight, s.volume, s.taxgroup_id, t.tgname,
  pls.id, s.shelflife, s.shelflife_meas, s.productiondate, c.use_serial_num,
  c4.code, c4.id, c5.code, c5.id,
  c6.code, c6.id, c6.folder_id, c7.code, c7.id, c7.folder_id,
  s.comment, s.contractor_id, cn.kind, cn.code, cn.folder_id, s.order_spec_id
from docspec s left join catalog c on c.id = s.catalog_id
     left join pricelistspec pls on pls.id = s.pricelistspec_id
     left join measure m1 on m1.id = s.measure1_id
     left join measure m2 on m2.id = s.measure2_id
     left join taxgroup t on t.id = s.taxgroup_id
     left join contractor cn on cn.id = s.contractor_id
     left join contractor c4 on c4.id = s.stocksrc
     left join contractor c5 on c5.id = s.stockdest
     left join contractor c6 on c6.id = s.molsrc
     left join contractor c7 on c7.id = s.moldest
;



CREATE VIEW V_DOCSPECMODEL_BROWSE(
    ID,
    DOCHEAD_ID,
    CATALOG_ID,
    CATCODE,
    CATNAME,
    MEASURE1_ID,
    MEASURE2_ID,
    MEASURE1_CODE,
    MEASURE2_CODE,
    MEASURE_CONTROL,
    CATFOLDERID,
    QUANTITY,
    QUANTITY2,
    PRICE,
    SUMMA1,
    PRICE1,
    SUMMA,
    BESTBEFORE,
    WEIGHT,
    VOLUME,
    TAXGROUP_ID,
    TGNAME,
    PRICELISTSPEC_ID,
    ARTICUL,
    COMMENT,
    CONTRACTOR_ID,
    CONTRACTOR,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLDEST_CODE,
    MOLDEST_ID)
AS
select s.id, s.dochead_id, s.catalog_id, c.code, raisespecname(c.cname, pls.sname), s.measure1_id, s.measure2_id, m1.code, m2.code, c.measure_control, c.folder_id,
  s.quantity, s.quantity2, s.price, s.summa1, s.price1,
  s.summa, s.bestbefore, s.weight, s.volume, s.taxgroup_id, t.tgname,
  pls.id, c.articul, s.comment, s.contractor_id, cn.code,
  c4.code, c4.id, c5.code, c5.id, c6.code, c6.id, c7.code, c7.id
from docspecmodel s join catalog c on c.id = s.catalog_id
     left join pricelistspec pls on pls.id = s.pricelistspec_id
     left join measure m1 on m1.id = s.measure1_id
     left join measure m2 on m2.id = s.measure2_id
     left join taxgroup t on t.id = s.taxgroup_id
     left join contractor cn on cn.id = s.contractor_id
     left join contractor c4 on c4.id = s.stocksrc
     left join contractor c5 on c5.id = s.stockdest
     left join contractor c6 on c6.id = s.molsrc
     left join contractor c7 on c7.id = s.moldest
;



CREATE VIEW V_DOCSPECMODEL_ITEM(
    ID,
    DOCHEAD_ID,
    CATALOG_ID,
    CATCODE,
    CATNAME,
    MEASURE1_ID,
    MEASURE2_ID,
    MEASURE1_CODE,
    MEASURE2_CODE,
    MEASURE_CONTROL,
    PACKAGED,
    CATFOLDERID,
    QUANTITY,
    QUANTITY2,
    PRICE,
    SUMMA1,
    PRICE1,
    SUMMA,
    BESTBEFORE,
    WEIGHT,
    VOLUME,
    TAXGROUP_ID,
    TGNAME,
    PRICELISTSPEC_ID,
    SHELFLIFE,
    SHELFLIFE_MEAS,
    PRODUCTIONDATE,
    STOCKSRC_CODE,
    STOCKSRC_ID,
    STOCKDEST_CODE,
    STOCKDEST_ID,
    MOLSRC_CODE,
    MOLSRC_ID,
    MOLSRC_FOLDER_ID,
    MOLDEST_CODE,
    MOLDEST_ID,
    MOLDEST_FOLDER_ID,
    COMMENT,
    CONTRACTOR_ID,
    CONTRACTOR_KIND,
    CONTRACTOR_CODE,
    CONTRACTOR_FOLDER_ID)
AS
select s.id, s.dochead_id, s.catalog_id, c.code, raisespecname(c.cname, pls.sname), s.measure1_id, s.measure2_id, m1.code, m2.code, c.measure_control, c.packaged, c.folder_id,
  s.quantity, s.quantity2, s.price,
  s.summa1, s.price1,
  s.summa, s.bestbefore, s.weight, s.volume, s.taxgroup_id, t.tgname,
  pls.id, s.shelflife, s.shelflife_meas, s.productiondate,
  c4.code, c4.id, c5.code, c5.id,
  c6.code, c6.id, c6.folder_id, c7.code, c7.id, c7.folder_id,
  s.comment, s.contractor_id, cn.kind, cn.code, cn.folder_id
from docspecmodel s join catalog c on c.id = s.catalog_id
     left join pricelistspec pls on pls.id = s.pricelistspec_id
     left join measure m1 on m1.id = s.measure1_id
     left join measure m2 on m2.id = s.measure2_id
     left join taxgroup t on t.id = s.taxgroup_id
     left join contractor cn on cn.id = s.contractor_id
     left join contractor c4 on c4.id = s.stocksrc
     left join contractor c5 on c5.id = s.stockdest
     left join contractor c6 on c6.id = s.molsrc
     left join contractor c7 on c7.id = s.moldest
;
