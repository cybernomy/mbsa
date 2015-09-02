/*
 * CreatePhaseFactItemDlg.java
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
package com.mg.merp.contract.support.ui;

import java.util.Date;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Button;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.contract.model.Contract;
import com.mg.merp.contract.model.FactItemContractorSource;
import com.mg.merp.contract.model.ItemKind;
import com.mg.merp.document.model.DocHead;

/**
 * Êîíòğîëëåğ äèàëîãà "Ñîçäàíèå ôàêòè÷åñêîãî ïóíêòà êîíòğàêòà"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CreatePhaseFactItemDlg.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
public class CreatePhaseFactItemDlg extends DefaultDialog {

	// Fields

	private ItemKind itemKind;
	private FactItemContractorSource contractorSource;
	private boolean isCreateSpec;
	private com.mg.merp.document.model.DocType ñontractType;
	private String ñontractNumber;
	private Date contractDate;
	private DocHead contract;
	private final String CHOOSE_CONTRACT_BUTTON_WIDGET_NAME = "chooseContract";  //$NON-NLS-1$


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		if(contract != null)
			((Button) view.getWidget(CHOOSE_CONTRACT_BUTTON_WIDGET_NAME)).setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		view.flushForm();
		if((itemKind != null) && (contractorSource != null) && (contract != null))
			super.onActionOk(event);
	}

	/**
	 * Îáğàáîòêà ñîáûòèÿ âûáîğà êîíòğàêòà
	 * @param event - ñîáûòèå
	 * @throws Exception
	 */
	public void onActionChooseContract(WidgetEvent event) throws Exception {

		SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.contract.support.ui.ContractSearchHelp"); //$NON-NLS-1$
		searchHelp.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				contract = (Contract) event.getItems()[0];
				ñontractType = contract.getDocType();
				ñontractNumber = contract.getDocNumber();
				contractDate = contract.getDocDate();
				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		searchHelp.search();
	}


	// Property accessors

	/**
	 * @return the isCreateSpec
	 */
	public boolean isCreateSpec() {
		return isCreateSpec;
	}

	/**
	 * @param isCreateSpec the isCreateSpec to set
	 */
	public void setCreateSpec(boolean isCreateSpec) {
		this.isCreateSpec = isCreateSpec;
	}

	/**
	 * @return the itemKind
	 */
	public ItemKind getItemKind() {
		return itemKind;
	}

	/**
	 * @param itemKind the itemKind to set
	 */
	public void setItemKind(ItemKind itemKind) {
		this.itemKind = itemKind;
	}

	/**
	 * @return the contractorSource
	 */
	public FactItemContractorSource getContractorSource() {
		return contractorSource;
	}

	/**
	 * @param contractorSource the contractorSource to set
	 */
	public void setContractorSource(FactItemContractorSource contractorSource) {
		this.contractorSource = contractorSource;
	}

	/**
	 * @return the ñontractType
	 */
	public com.mg.merp.document.model.DocType getñontractType() {
		return ñontractType;
	}

	/**
	 * @param ñontractType the ñontractType to set
	 */
	public void setñontractType(com.mg.merp.document.model.DocType ñontractType) {
		this.ñontractType = ñontractType;
	}

	/**
	 * @return the contractDate
	 */
	public Date getContractDate() {
		return contractDate;
	}

	/**
	 * @param contractDate the contractDate to set
	 */
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	/**
	 * @return the ñontractNumber
	 */
	public String getñontractNumber() {
		return ñontractNumber;
	}

	/**
	 * @param ñontractNumber the ñontractNumber to set
	 */
	public void setñontractNumber(String ñontractNumber) {
		this.ñontractNumber = ñontractNumber;
	}

	/**
	 * @return the contract
	 */
	public DocHead getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(DocHead contract) {
		this.contract = contract;
	}

}
