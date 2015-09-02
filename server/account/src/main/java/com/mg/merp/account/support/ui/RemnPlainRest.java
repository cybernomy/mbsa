/*
 * RemnRest.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.reference.model.Currency;

/**
 * @author leonova
 * @version $Id: RemnPlainRest.java,v 1.3 2008/02/21 10:21:04 alikaev Exp $ 
 */
public class RemnPlainRest extends DefaultRestrictionForm {
	
	@DataItemName("Account.Cond.RemnAcc.Period1")
	private Period periodId1 = null;
	@DataItemName("Account.Cond.RemnAcc.Period2")
	private Period periodId2 = null;
	private Currency currencyCode = null;
	@DataItemName("Reference.Cond.DateFrom")
	private Date date1 = null;
	@DataItemName("Reference.Cond.DateTill")
	private Date date2 = null;
	@DataItemName("Account.Cobd.RemnAcc.ByDates")
	boolean byDates = false;
	private AccPlan AccPlan = null;
	

	@Override
	protected void doClearRestrictionItem() {		
		this.periodId1 = null;
		this.periodId2 = null;
		this.currencyCode = null;
		this.date1 = null;
		this.date2 = null;
		this.byDates = false;
		this.AccPlan = null;
	}


	public AccPlan getAccCode() {
		return AccPlan;
	}


	public boolean isByDates() {
		return byDates;
	}


	public Currency getCurrencyCode() {
		return currencyCode;
	}


	public Date getDate1() {
		return date1;
	}


	public Date getDate2() {
		return date2;
	}


	public Period getPeriodId1() {
		return periodId1;
	}


	public Period getPeriodId2() {
		return periodId2;
	}

}
