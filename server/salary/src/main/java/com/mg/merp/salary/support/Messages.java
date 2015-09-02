/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.salary.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 * 
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.3 2007/08/27 06:19:40 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	
	private static final String BUNDLE_NAME = "com.mg.merp.salary.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String PAY_SHEET_SPEC_BROWSE_FORM_TITLE = "PaySheetSpecBrowseFormTitle"; //$NON-NLS-1$
	
	public static final String SERVICE_KIND_CODE_NOT_FOUND = "ServiceKindCodeNotFound"; //$NON-NLS-1$
	public static final String BEGIN_DATE_OF_SERVICE_LENGTH_NOT_FOUND = "BeginDateOfServiceLengthNotFound"; //$NON-NLS-1$
	
	public static final String PARAM_IS_NOT_BELONGS_TO_CALC_LIST_FEE = "ParamIsNotBelongsToCalcListFee"; //$NON-NLS-1$
	public static final String PARAM_IS_NOT_BELONGS_TO_CALC_LIST_FEE_CODE = "ParamIsNotBelongsToCalcListFeeCode"; //$NON-NLS-1$
	
	public static final String DAYS_NORM_NOT_FOUND = "DaysNormNotFound"; //$NON-NLS-1$
	public static final String HOURS_NORM_NOT_FOUND = "HoursNormNotFound"; //$NON-NLS-1$
	
	public static final String BASIC_POSITION_IN_PAYROLL_NOT_FOUND = "BasicPositionInPayrollNotFound"; //$NON-NLS-1$
	public static final String PERSONEL_CONFIG_COSTS_ANL_NOT_FOUND = "PersonelConfigCostsAnlNotFound"; //$NON-NLS-1$
	
	
	public static Messages getInstance() {
		return instance;
	}

	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
}
