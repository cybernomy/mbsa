/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.warehouse.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.16 2008/08/27 09:41:21 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
  //message keys
  public static final String MANDATORY_VALIDATOR_SRCSTOCK = "MandatoryValidatorSrcStock"; //$NON-NLS-1$
  public static final String PROCESS_WH_TRANSACT_ERROR = "ProcessWHTransactError"; //$NON-NLS-1$
  public static final String NOTENOUGH_DISPOSAL_POSITIONS = "NotEnoughDisposalPositions"; //$NON-NLS-1$
  public static final String HISTORY_NOT_FOUND = "HistoryNotFound"; //$NON-NLS-1$
  public static final String BATCH_NOT_ALIVE = "BatchNotAlive"; //$NON-NLS-1$
  public static final String ID_CAPTION = "IdCaption"; //$NON-NLS-1$
  public static final String BEGIN_QUAN1_CAPTION = "BeginQuan1Caption"; //$NON-NLS-1$
  public static final String BEGIN_QUAN2_CAPTION = "BeginQuan2Caption"; //$NON-NLS-1$
  public static final String END_QUAN1_CAPTION = "EndQuan1Caption"; //$NON-NLS-1$
  public static final String END_QUAN2_CAPTION = "EndQuan2Caption"; //$NON-NLS-1$
  public static final String MARK_QUAN1_CAPTION = "MarkQuan1Caption"; //$NON-NLS-1$
  public static final String MARK_QUAN2_CAPTION = "MarkQuan2Caption"; //$NON-NLS-1$
  public static final String CONTRACTOR_CAPTION = "ContractorCaption"; //$NON-NLS-1$
  public static final String PRICE_NAT_CAPTION = "PriceNatCaption"; //$NON-NLS-1$
  public static final String PRICE_CUR_CAPTION = "PriceCurCaption"; //$NON-NLS-1$
  public static final String CUR_CODE_CAPTION = "CurCodeCaption"; //$NON-NLS-1$
  public static final String DOC_CAPTION = "DocCaption"; //$NON-NLS-1$
  public static final String CREATE_DATE_CAPTION = "CreateDateCaption"; //$NON-NLS-1$
  public static final String BESTBEFORE_CAPTION = "BestBeforeCaption"; //$NON-NLS-1$
  public static final String VENDOR_LOT_CAPTION = "VendorLotCaption"; //$NON-NLS-1$
  public static final String NUMBER_LOT_CAPTION = "NumberLotCaption"; //$NON-NLS-1$
  public static final String DOC_PROCESS_DATE_CAPTION = "Document.ProcessDateCaption"; //$NON-NLS-1$
  public static final String DOC_QUAN1_CAPTION = "Document.Quan1Caption"; //$NON-NLS-1$
  public static final String DOC_QUAN2_CAPTION = "Document.Quan2Caption"; //$NON-NLS-1$
  public static final String COUNTRY_OF_ORIGIN_CAPTION = "CountryOfOriginCaption"; //$NON-NLS-1$
  public static final String CUSTOMS_DECLARATION_CAPTION = "CustomsDeclarationCaption"; //$NON-NLS-1$
  public static final String DOC_TO = "Document.To"; //$NON-NLS-1$
  public static final String DOC_FROM = "Document.From"; //$NON-NLS-1$
  public static final String DOC_TYPE_HEADER = "Document.TypeHeader"; //$NON-NLS-1$
  public static final String DOC_DATE_HEADER = "Document.DateHeader"; //$NON-NLS-1$
  public static final String DOC_NUMBER_HEADER = "Document.NumberHeader"; //$NON-NLS-1$
  public static final String DOC_TYPE_BASE_HEADER = "Document.TypeBaseHeader"; //$NON-NLS-1$
  public static final String DOC_DATE_BASE_HEADER = "Document.DateBaseHeader"; //$NON-NLS-1$
  public static final String DOC_NUMBER_BASE_HEADER = "Document.NumberBaseHeader"; //$NON-NLS-1$
  public static final String DOC_DATE = "Document.Date"; //$NON-NLS-1$
  public static final String BATCH_OWNER = "Batch.Owner"; //$NON-NLS-1$
  public static final String BATCH_KIND = "Batch.Kind"; //$NON-NLS-1$
  public static final String BINLOCATION_BR_TITLE = "BinLocation.FormBr.Title"; //$NON-NLS-1$
  public static final String WARECARD_BR_TITLE = "WareCard.FormBr.Title"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_WAREHOUSE = "CurrentStockSituationForm.Title.Warehouse"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_AVAILABLE = "CurrentStockSituationForm.Title.Available"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_LOCATED = "CurrentStockSituationForm.Title.Located"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_PLAN_RECEIPT = "CurrentStockSituationForm.Title.PlanningReceipt"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_PLAN_ISSUE = "CurrentStockSituationForm.Title.PlanningIssue"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_RESERVED = "CurrentStockSituationForm.Title.Reserved"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_TITLE = "CurrentStockSituationForm.Title"; //$NON-NLS-1$
  public static final String STOCK_SITUATION_FORM_WAREHOUSE_TOTAL = "CurrentStockSituationForm.Title.Total"; //$NON-NLS-1$
  public static final String STOCK_INVENTORY_DATE_MISSING = "StocInventoryDateMissing"; //$NON-NLS-1$
  public static final String BROWS_TITLE_VOL_MEAS = "VolumeMeasure"; //$NON-NLS-1$
  public static final String BROWS_TITLE_WEIGHT_MEAS = "WeightMeasure"; //$NON-NLS-1$
  public static final String BROWS_TITLE_QUAN_MEAS = "QuanMeasure"; //$NON-NLS-1$
  public static final String BROWS_TITLE_VOL_INFINITE = "InfiniteVolume"; //$NON-NLS-1$
  public static final String BROWS_TITLE_WEIGHT_INFINITE = "InfiniteWeight"; //$NON-NLS-1$
  public static final String BROWS_TITLE_QUAN_INFINITE = "InfiniteQuan"; //$NON-NLS-1$
  public static final String BROWS_TITLE_VOL_MAX = "MaximumVolume"; //$NON-NLS-1$
  public static final String BROWS_TITLE_WEIGHT_MAX = "MaximumWeight"; //$NON-NLS-1$
  public static final String BROWS_TITLE_QUAN_MAX = "MaximumQuan"; //$NON-NLS-1$
  public static final String SRC_OR_DST_STOCK_NOT_FIND = "SrcOrDstStockNotFound"; //$NON-NLS-1$
  public static final String STOCK_CLOSED = "StockClosed"; //$NON-NLS-1$
  public static final String STOCK_CLOSED_DATE_TILL = "StockClosedDateTill"; //$NON-NLS-1$
  public static final String WH_TRANSACTION_DAY_BR_TITLE = "WhTransactionDay.FormBr.Title"; //$NON-NLS-1$
  public static final String SYSTEM_AUDIT_MESSAGE_OPEN_TR_DAY = "SystemAuditMessageOpenTrDay"; //$NON-NLS-1$
  public static final String SYSTEM_AUDIT_MESSAGE_CLOSE_TR_DAY = "SystemAuditMessageCloseTrDay"; //$NON-NLS-1$
  public static final String SYSTEM_AUDIT_MESSAGE_CLOSE_WAREHOUSE = "SystemAuditMessageCloseWarehouse"; //$NON-NLS-1$
  public static final String SYSTEM_AUDIT_MESSAGE_OPEN_WAREHOUSE = "SystemAuditMessageOpenWarehouse"; //$NON-NLS-1$
  public static final String CHOOSE_CLOSE_DATE_TILL = "ChooseCloseDateTill"; //$NON-NLS-1$
  public static final String READ_DATE = "ReadDate"; //$NON-NLS-1$
  public static final String SERIAL_NUMBER = "SerialNumber"; //$NON-NLS-1$
  public static final String SERIAL_NUMBER_SELECTED = "SerialNumberSelected"; //$NON-NLS-1$
  public static final String BIN_LOCATION_PRODUCT_MISSMATCH = "BinLocationProductMissmatch"; //$NON-NLS-1$
  public static final String BIN_LOCATION_NOT_FOUND = "BinLocationNotFound"; //$NON-NLS-1$
  public static final String BIN_LOCATION_CODE = "BinLocationCode"; //$NON-NLS-1$
  public static final String BIN_LOCATION_QUANTITY_IN_SECTION = "BinLocationQuantityInSection"; //$NON-NLS-1$
  public static final String BIN_LOCATION_QUANTITY_TO_PERFORM = "BinLocationQuantityToPerform"; //$NON-NLS-1$
  public static final String CUSTOMS_DECLARATION_NOT_FOUND = "CustomsDeclarationNotFound"; //$NON-NLS-1$
  public static final String COUNTRY_OF_ORIGIN_NOT_FOUND = "CountryOfOriginNotFound"; //$NON-NLS-1$
  public static final String GOOD_IS_NOT_DISPOSED = "GoodIsNotDisposed"; //$NON-NLS-1$
  private static final String BUNDLE_NAME = "com.mg.merp.warehouse.resources.messages"; //$NON-NLS-1$
  private static Messages instance;

  static {
    MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  public static Messages getInstance() {
    return instance;
  }
}
