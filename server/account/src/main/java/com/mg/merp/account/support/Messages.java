/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.account.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: Messages.java,v 1.18 2009/03/11 13:39:15 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.account.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String OPERATION_TITLE = "OperationTitle"; //$NON-NLS-1$
	public static final String MANDATORY_VALIDATOR_FROM = "MandatoryValidatorFrom"; //$NON-NLS-1$
	public static final String MANDATORY_VALIDATOR_TO = "MandatoryValidatorTo"; //$NON-NLS-1$
	public static final String ACC_INVALID_PERIOD_RANGE = "AccInvalidPeriodRange"; //$NON-NLS-1$
	public static final String ACC_INVALID_ACCOUNTS_LIST = "AccInvalidAccountsList"; //$NON-NLS-1$
	public static final String ACC_PERIOD_CLOSED = "AccPeriodClosed"; //$NON-NLS-1$
	public static final String ACC_INVALID_PERIOD = "AccInvalidPeriod"; //$NON-NLS-1$
	public static final String ACC_SELECTED_ACCOUNTS = "AccSelectedAcounts"; //$NON-NLS-1$
	public static final String ACC_SELECTED_ACCOUNTS_ALL = "AccSelectedAcountsAll"; //$NON-NLS-1$
	public static final String ACC_PERIOD_IS_CROSS = "AccPeriodIsCross"; //$NON-NLS-1$
	public static final String ACC_PERIOD_IS_IN_USE = "AccPeriodIsInUse"; //$NON-NLS-1$
	
	public static final String PERMISSIBLE_ACCOUNTS_UNIQUE = "PermissibleAccountsUnique"; //$NON-NLS-1$

	public static final String ACC_BATCH_ID = "AccBatchId"; //$NON-NLS-1$
	public static final String ACC_BATCH_INCOME_DATE = "AccBatchIncomeDate"; //$NON-NLS-1$
	public static final String ACC_BATCH_DOCTYPE = "AccBatchDocType"; //$NON-NLS-1$
	public static final String ACC_BATCH_DOCNUMBER = "AccBatchDocNumber"; //$NON-NLS-1$
	public static final String ACC_BATCH_DOCDATE = "AccBatchDocDate"; //$NON-NLS-1$
	public static final String ACC_BATCH_DEBIT_QUAN = "AccBatchDebitQuan"; //$NON-NLS-1$
	public static final String ACC_BATCH_CREDIT_QUAN = "AccBatchCreditQuan"; //$NON-NLS-1$
	public static final String ACC_BATCH_DIFF_QUAN = "AccBatchDiffQuan"; //$NON-NLS-1$
	public static final String ACC_BATCH_PROCESS_QUAN = "AccBatchProcessQuan"; //$NON-NLS-1$
	public static final String ACC_BATCH_PRICECUR = "AccBatchPriceCur"; //$NON-NLS-1$
	public static final String ACC_BATCH_PRICENAT = "AccBatchPriceNat"; //$NON-NLS-1$

	public static final String DOCUMENT_NOT_IS_WITH_SPEC = "DocumentNotIsWithSpec"; //$NON-NLS-1$

	public static final String NOT_UNIQUE_ATTRIBUTE = "NotUniqueAttribute"; //$NON-NLS-1$

	public static final String NOT_CHOOSE_ACCPLAN = "NotChooseAccPlan"; //$NON-NLS-1$

	public static final String CLOSE_DEBIT_COMMENT = "CloseDebitComment"; //$NON-NLS-1$
	public static final String NOT_CHOOSE_FOLDER = "NotChooseFolder"; //$NON-NLS-1$
	public static final String NOT_SEARCHED_ENTITIES = "NotSearchedEntities"; //$NON-NLS-1$
	public static final String NOT_CHOOSE_ENTITIES = "NotChooseEntities"; //$NON-NLS-1$

	public static final String INVALID_RANGE_DATE = "InvalidRangeDate"; //$NON-NLS-1$

	public static final String COMMENT_CALC_AMORTIZATION = "CommentCalcAmortization"; //$NON-NLS-1$
	public static final String AMORTIZATION_BR_TITLE = "AmortizationBrTitle"; //$NON-NLS-1$

	public static final String ACC_REVALUTE_DLG_TITLE_OVERVALUE = "AccRevaluateDlgTitleOvervalue"; //$NON-NLS-1$
	public static final String ACC_REVALUTE_DLG_TITLE_OVERESTIMATION = "AccRevaluateDlgTitleOverestimation"; //$NON-NLS-1$

	public static final String COMMENT_REVALUATE_OVERVALUE = "CommentRevaluateOvervalue"; //$NON-NLS-1$
	public static final String COMMENT_REVALUATE_OVERESTIMATION = "CommentRevaluateOverestimation"; //$NON-NLS-1$

	public static final String ACC_REVALUTE_NOT_CHOOSE_FIELDS = "AccRevaluateNotChooseFields"; //$NON-NLS-1$

	public static final String COMMON_OPER_MUST_BE_LAST = "CommonOperMustBeLast"; //$NON-NLS-1$
	public static final String ALL_ACCKINDS_REQUIRED = "AllAccKindsRequired"; //$NON-NLS-1$

	public static final String ACC_INVENTORY_MOVE_CHOOSE_DATE_CONTRACTOR = "AccInventoryMoveChooseDateContractor"; //$NON-NLS-1$
	public static final String ACC_INVENTORY_MOVE_DLG_TITLE = "AccInventoryMoveDlgTitle"; //$NON-NLS-1$
	
	public static final String ACC_INVENTORY_RETIRE_DLG_TITLE = "AccInventoryRetireDlgTitle"; //$NON-NLS-1$
	public static final String ACC_INVENTORY_RETIRE_DLG_CHECK = "AccInventoryRetireDlgCheck"; //$NON-NLS-1$
	public static final String RETIRE_COMMENT_ECONOMIC_OPER = "RetireCommentRconomicOper"; //$NON-NLS-1$
	public static final String CLOSED_MAKE_REMAINS_FOR_INVENTORY = "ClosedMakeRemainsForInventory"; //$NON-NLS-1$

	public static final String ACC_INVENTORY_FREEZE_DLG_TITLE = "AccInventoryFreezeDlgTitle"; //$NON-NLS-1$
	public static final String ACC_INVENTORY_FREEZE_DLG_PROMT = "AccInventoryFreezeDlgPromt"; //$NON-NLS-1$
	public static final String CLOSED_RETIRE_FOR_INVENTORY = "ClosedRetireForInventory"; //$NON-NLS-1$
	public static final String CLOSED_MOVE_FOR_INVENTORY = "ClosedMoveForInventory"; //$NON-NLS-1$
	public static final String CLOSED_REVALUATE_FOR_INVENTORY = "ClosedRevaluateForInventory"; //$NON-NLS-1$
	public static final String CLOSED_CALC_AMORTIZATION_FOR_INVENTORY = "ClosedCalcAmortizationForInventory"; //$NON-NLS-1$
	public static final String COMMENT_MOVE_INVENTORY = "CommentMoveInventory"; //$NON-NLS-1$
	public static final String INVALID_ROLLBACK_INVHISTORY = "InvalidRollbackInvHistory"; //$NON-NLS-1$

	public static final String 	NOT_FOUND_PERIOD_BY_DATE = "NotFoundPeriodByDate"; //$NON-NLS-1$
	
	public static final String LINE_SUM = "LineSum"; //$NON-NLS-1$
	public static final String LINE_QUAN = "LineQuan"; //$NON-NLS-1$
	
	public static final String ACC_ANL_FORMAT = "AccAnlFormat"; //$NON-NLS-1$
	public static final String ACC_ANL_DB_FORMAT = "AccAnlDbFormat"; //$NON-NLS-1$
	public static final String ACC_ANL_KT_FORMAT = "AccAnlKtFormat"; //$NON-NLS-1$
	public static final String ACC_WRONG_PERMISSIBLE = "AccWrongPermissible"; //$NON-NLS-1$
	
	public static final String IN_OPERATION_MSG_PATTERN = "InOperationMsgPattern"; //$NON-NLS-1$
	
	public static final String CONTRACTOR_FROM_EMPTY = "ContractorFromEmpty"; //$NON-NLS-1$
	public static final String CONTRACTOR_TO_EMPTY = "ContractorToEmpty"; //$NON-NLS-1$
	
	public static final String CODE = "Code"; //$NON-NLS-1$
	public static final String NAME = "Name"; //$NON-NLS-1$
	public static final String SELECTED = "Selected"; //$NON-NLS-1$

	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
}
