/*
 * BankDocumentRest.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.generic.ui.DocumentRest;
import com.mg.merp.reference.model.BankAccount;

/**
 * Базовый класс контроллера формы условий отбора "Банковских документов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentRest.java,v 1.1 2007/11/12 06:51:28 sharapov Exp $
 */
public class BankDocumentRest extends DocumentRest {

	@DataItemName("Account.BankIn.PayerBankReq") //$NON-NLS-1$
	private BankAccount payerBank;
	private String payerBankAccount;
	
	@DataItemName("Account.BankIn.RecipientBankReq") //$NON-NLS-1$
	private BankAccount recipientBank;
	private String recipientBankAccount;
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.DocumentRest#doClearRestrictionItem()
	 */
	@Override
	protected void doClearRestrictionItem() {
		super.doClearRestrictionItem();
		this.payerBank = null;
		this.payerBankAccount = StringUtils.EMPTY_STRING;
		this.recipientBank = null;
		this.recipientBankAccount = StringUtils.EMPTY_STRING;
	}

	/**
	 * @return the payerBankReq
	 */
	public BankAccount getPayerBank() {
		return this.payerBank;
	}

	/**
	 * @param payerBankReq the payerBankReq to set
	 */
	public void setPayerBank(BankAccount payerBank) {
		this.payerBank = payerBank;
	}

	/**
	 * @return the payerBankAccount
	 */
	public String getPayerBankAccount() {
		return this.payerBankAccount;
	}

	/**
	 * @param payerBankAccount the payerBankAccount to set
	 */
	public void setPayerBankAccount(String payerBankAccount) {
		this.payerBankAccount = payerBankAccount;
	}

	/**
	 * @return the recipientBank
	 */
	public BankAccount getRecipientBank() {
		return this.recipientBank;
	}

	/**
	 * @param recipientBank the recipientBank to set
	 */
	public void setRecipientBank(BankAccount recipientBank) {
		this.recipientBank = recipientBank;
	}

	/**
	 * @return the recipientBankAccount
	 */
	public String getRecipientBankAccount() {
		return this.recipientBankAccount;
	}

	/**
	 * @param recipientBankAccount the recipientBankAccount to set
	 */
	public void setRecipientBankAccount(String recipientBankAccount) {
		this.recipientBankAccount = recipientBankAccount;
	}

}
