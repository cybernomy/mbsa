/*
 * FromBankAccountSearchHelp.java
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
package com.mg.merp.reference.support.ui;


/**
 * SearchHelp для банка плательщика банковских документов
 * 
 * @author leonova
 * @version $Id: FromBankAccountSearchHelp.java,v 1.1 2006/10/07 10:11:00 leonova Exp $
 */
public class FromBankAccountSearchHelp extends BankAccountSearchHelp {

	/* (non-Javadoc)
	 * @see com.mg.merp.account.support.ui.BankAccountSearchHelp#getContractorContextName()
	 */
	@Override
	protected String getContractorContextName() {
		return "From";
	}

}
