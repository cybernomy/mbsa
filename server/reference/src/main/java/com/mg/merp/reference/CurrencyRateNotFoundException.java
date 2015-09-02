/*
 * CurrencyRateNotFoundException.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mg.framework.api.BusinessException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.support.Messages;

/**
 * ИС курс валюты не найден
 * 
 * @author Oleg V. Safonov
 * @version $Id: CurrencyRateNotFoundException.java,v 1.2 2007/03/23 12:58:24 safonov Exp $
 */
@javax.ejb.ApplicationException
public class CurrencyRateNotFoundException extends BusinessException {

	public CurrencyRateNotFoundException(String currency1, String currency2, String currencyRateType, String currencyRateAuthority, Date effectiveDate) {
		super(Messages.getInstance().getMessage(Messages.CURRENCY_RATE_NOT_FOUND, new Object[] {currency1, currency2, SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, ServerUtils.getUserLocale()).format(effectiveDate)}));
	}
	
}
