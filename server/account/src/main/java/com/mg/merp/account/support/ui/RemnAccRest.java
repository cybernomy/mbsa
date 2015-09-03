/*
 * RemnAccRest.java
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
package com.mg.merp.account.support.ui;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.Period;
import com.mg.merp.reference.model.Currency;


/**
 * Контроллер формы условий отбора бизнес-компонента "Остатки и обороты по счетам"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: RemnAccRest.java,v 1.3 2007/01/25 06:30:51 sharapov Exp $ 
 */
public class RemnAccRest extends DefaultHierarhyRestrictionForm {

	// Fields

	@DataItemName("Account.Cond.RemnAcc.Period1") //$NON-NLS-1$
	private Period periodFrom = null;

	@DataItemName("Account.Cond.RemnAcc.Period2") //$NON-NLS-1$
	private Period periodTo = null;

	private Currency currencyCode = null;

	@DataItemName("Reference.Cond.DateFrom") //$NON-NLS-1$
	private Date date1 = null;

	@DataItemName("Reference.Cond.DateTill") //$NON-NLS-1$
	private Date date2 = null;

	@DataItemName("Account.Cobd.RemnAcc.ByDates") //$NON-NLS-1$
	boolean byDates = false;

	private AccPlan accCode = null;

	// Methods

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultHierarhyRestrictionForm#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();		
		this.periodFrom = null;
		this.periodTo = null;
		this.currencyCode = null;
		this.date1 = null;
		this.date2 = null;
		this.byDates = false;
		this.accCode = null;
	}

	public AccPlan getAccCode() {
		return accCode;
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

	/**
	 * @return the periodFrom
	 */
	public Period getPeriodFrom() {
		return periodFrom;
	}

	/**
	 * @param periodFrom the periodFrom to set
	 */
	public void setPeriodFrom(Period periodFrom) {
		this.periodFrom = periodFrom;
	}

	/**
	 * @return the periodTo
	 */
	public Period getPeriodTo() {
		return periodTo;
	}

	/**
	 * @param periodTo the periodTo to set
	 */
	public void setPeriodTo(Period periodTo) {
		this.periodTo = periodTo;
	}

}
