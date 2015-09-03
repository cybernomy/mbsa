/*
 * BuyBookRest.java
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


/**
 * Контроллер формы условий отбора книги покупок
 * 
 * @author leonova
 * @version $Id: BuyBookRest.java,v 1.2 2006/08/23 10:26:15 leonova Exp $ 
 */
public class BuyBookRest extends AccountBookRest {
	
	private int approved = 0;

	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.approved = 0;
	}

	/**
	 * @return Returns the approved.
	 */
	protected int getApproved() {
		return approved;
	}

}