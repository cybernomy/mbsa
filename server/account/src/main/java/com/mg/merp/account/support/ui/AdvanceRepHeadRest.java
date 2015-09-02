/*
 * AdvanceRepHeadRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.document.generic.ui.GoodsDocumentRest;
import com.mg.merp.reference.model.Contractor;
import com.mg.merp.reference.support.ui.ContractorSearchForm;

/**
 * Контроллер формы условий отбора авансовых отчетов
 * 
 * @author leonova
 * @version $Id: AdvanceRepHeadRest.java,v 1.4 2006/12/19 12:43:33 leonova Exp $
 *
 */
public class AdvanceRepHeadRest extends GoodsDocumentRest {

	public AdvanceRepHeadRest() {
		contractorFromKinds = new String[] {ContractorSearchForm.CONTRACTOR_EMPLOYEE};
		contractorToKinds = new String[] {ContractorSearchForm.CONTRACTOR_PARTNER, ContractorSearchForm.CONTRACTOR_ORGUNIT};

	}

	@DataItemName("Account.Adv.Company")
	protected Contractor companyCode = null;
		
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.companyCode = null;
	}

	/**
	 * @return Returns the companyCode.
	 */
	protected Contractor getCompanyCode() {
		return companyCode;
	}


}
