/*
 * FinTurnAccFlatRest.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.model.Analytics;


/**
 * Контроллер формы условия отбора бизнес-компонента "Остатки и обороты по признакам"
 * 
 * @author leonova
 * @version $Id: FinTurnFeatFlatRest.java,v 1.6 2007/09/17 12:04:24 alikaev Exp $ 
 */
public class FinTurnFeatFlatRest extends FinTurnFlatRest {

	@DataItemName("Finance.Cond.FeatureAccount") //$NON-NLS-1$
	private Account featAccCode = null;
	@DataItemName("Finance.TurnAccount.Feature1")	 //$NON-NLS-1$
	private Analytics featAnl1Code = null;
	@DataItemName("Finance.TurnAccount.Feature2")	 //$NON-NLS-1$
	private Analytics featAnl2Code = null;
	@DataItemName("Finance.TurnAccount.Feature3")	 //$NON-NLS-1$
	private Analytics featAnl3Code = null;
	@DataItemName("Finance.TurnAccount.Feature4")	 //$NON-NLS-1$
	private Analytics featAnl4Code = null;
	@DataItemName("Finance.TurnAccount.Feature5")	 //$NON-NLS-1$
	private Analytics featAnl5Code = null;
	@DataItemName("Finance.Cond.TurnFlat.Period1") //$NON-NLS-1$
	private com.mg.merp.finance.model.FinPeriod periodBegin;
	@DataItemName("Finance.Cond.TurnFlat.Period2") //$NON-NLS-1$
	private com.mg.merp.finance.model.FinPeriod periodEnd;

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.finance.support.ui.FinRest#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.featAnl1Code = null;
		this.featAnl2Code = null;
		this.featAnl3Code = null;
		this.featAnl4Code = null;
		this.featAnl5Code = null;
		this.featAccCode = null;
		this.periodBegin = null;
		this.periodEnd = null;
	}

	public Account getFeatAccCode() {
		return featAccCode;
	}

	public Analytics getFeatAnl1Code() {
		return featAnl1Code;
	}

	public Analytics getFeatAnl2Code() {
		return featAnl2Code;
	}

	public Analytics getFeatAnl3Code() {
		return featAnl3Code;
	}

	public Analytics getFeatAnl4Code() {
		return featAnl4Code;
	}

	public Analytics getFeatAnl5Code() {
		return featAnl5Code;
	}

	public com.mg.merp.finance.model.FinPeriod getPeriodBegin() {
		return periodBegin;
	}

	public com.mg.merp.finance.model.FinPeriod getPeriodEnd() {
		return periodEnd;
	}

}
