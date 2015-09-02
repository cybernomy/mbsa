/*
 * RemnValRest.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.Contractor;

/**
 * @author leonova
 * @version $Id: RemnValRest.java,v 1.3 2006/10/18 06:12:11 leonova Exp $ 
 */
public class RemnValRest extends RemnPlainRest {
	@DataItemName("Account.RemnAnl.AnlPlan1")
	private AnlPlan anlCode1 = null;
	@DataItemName("Account.RemnAnl.AnlPlan2")	
	private AnlPlan anlCode2 = null;
	@DataItemName("Account.RemnAnl.AnlPlan3")	
	private AnlPlan anlCode3 = null;
	@DataItemName("Account.RemnAnl.AnlPlan4")	
	private AnlPlan anlCode4 = null;
	@DataItemName("Account.RemnAnl.AnlPlan5")
	private AnlPlan anlCode5 = null;
	private Catalog catalogName = null;
	private int balanceKind = 0;
	private CatalogFolder catalogFolder = null;	
	private Contractor contractorCode = null;
	
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.anlCode1 = null;
		this.anlCode2 = null;
		this.anlCode3 = null;
		this.anlCode4 = null;
		this.anlCode5 = null;	
		this.catalogName = null;
		this.catalogFolder = null;
		this.contractorCode = null;
		this.balanceKind = 0;
	}

	public AnlPlan getAnlCode1() {
		return anlCode1;
	}

	public AnlPlan getAnlCode2() {
		return anlCode2;
	}

	public AnlPlan getAnlCode3() {
		return anlCode3;
	}

	public AnlPlan getAnlCode4() {
		return anlCode4;
	}

	public AnlPlan getAnlCode5() {
		return anlCode5;
	}

	public int getBalanceKind() {
		return balanceKind;
	}

	public CatalogFolder getCatalogFolder() {
		return catalogFolder;
	}

	public Catalog getCatalogName() {
		return catalogName;
	}

	public Contractor getContractorCode() {
		return contractorCode;
	}


}
