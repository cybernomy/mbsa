/*
 * FinCarryForwardDialog.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Label;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.finance.model.Account;
import com.mg.merp.finance.support.Messages;

/**
 * Контроллер диалога "Перенос остатков" финансового учета
 * @author Artem V. Sharapov
 * @version $Id: FinCarryForwardDialog.java,v 1.1 2006/12/26 13:18:13 sharapov Exp $
 */
public class FinCarryForwardDialog extends DefaultDialog {

	/* Fields */
	private boolean allAcc;
	private com.mg.merp.finance.model.FinPeriod finPeriodFrom;
	private com.mg.merp.finance.model.FinPeriod finPeriodTill;
	private com.mg.merp.finance.model.Account[] accounts;
	
	/* Methods */
	/**
	 * Обработка события нажатия кнопки "Выбрать счета" 
	 * @param event - событие
	 * @throws Exception
	 */
	public void onActionChooseAccounts(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.finance.support.ui.FinAccountSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				PersistentObject[] entitys = event.getItems();
				accounts = new Account[entitys.length];
				for(int i = 0; i < entitys.length; i++)
					accounts[i] = (Account) entitys[i];
				setSelectedAccountsAmount(String.format(Messages.getInstance().getMessage(Messages.TURNACC_SELECTED_ACCOUNTS), accounts.length));
				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}

	/* Property Accessors */
	/**
	 * @return список выбранных счетов
	 */
	public com.mg.merp.finance.model.Account[] getAccounts() {
		return accounts;
	}

	/**
	 * @return the allAcc
	 */
	public boolean isAllAcc() {
		return allAcc;
	}

	/**
	 * @param allAcc the allAcc to set
	 */
	public void setAllAcc(boolean allAcc) {
		this.allAcc = allAcc;

	}

	/**
	 * @return the finPeriodFrom
	 */
	public com.mg.merp.finance.model.FinPeriod getFinPeriodFrom() {
		return finPeriodFrom;
	}

	/**
	 * @param finPeriodFrom the finPeriodFrom to set
	 */
	public void setFinPeriodFrom(com.mg.merp.finance.model.FinPeriod finPeriodFrom) {
		this.finPeriodFrom = finPeriodFrom;
	}

	/**
	 * @return the finPeriodTill
	 */
	public com.mg.merp.finance.model.FinPeriod getFinPeriodTill() {
		return finPeriodTill;
	}

	/**
	 * @param finPeriodTill the finPeriodTill to set
	 */
	public void setFinPeriodTill(com.mg.merp.finance.model.FinPeriod finPeriodTill) {
		this.finPeriodTill = finPeriodTill;
	}

	/**
	 * @param selectedAccountsAmount the selectedAccountsAmount to set
	 */
	public void setSelectedAccountsAmount(String selectedAccountsAmount) {
		((Label) view.getWidget("selectedAmountLabel")).setText(selectedAccountsAmount);
	}


}
