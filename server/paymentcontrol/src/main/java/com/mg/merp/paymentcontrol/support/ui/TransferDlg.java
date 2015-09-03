/*
 * TransferDlg.java
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
package com.mg.merp.paymentcontrol.support.ui;

import java.math.BigDecimal;
import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.paymentcontrol.model.Liability;
import com.mg.merp.paymentcontrol.model.PmcConfig;
import com.mg.merp.paymentcontrol.model.PmcResource;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRateAuthority;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * Контроллер диалога "Внутреннее перемещение средств"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TransferDlg.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class TransferDlg extends DefaultDialog {

	@DataItemName("PmcResource.ResourceFolder") //$NON-NLS-1$
	private Folder resourceFolderExpense;
	private PmcResource resourceExpense;
	private Date dateExpense;
	private BigDecimal sumExpense;
	private Currency expenseCur;
	@DataItemName("Liability.LiabilityModel") //$NON-NLS-1$
	private Liability liabilityModelExpense;

	@DataItemName("PmcResource.ResourceFolder") //$NON-NLS-1$
	private Folder resourceFolderIncome;
	private PmcResource resourceIncome;
	private Date dateIncome;
	private BigDecimal sumIncome;
	private Currency incomeCur;
	@DataItemName("Liability.LiabilityModel") //$NON-NLS-1$
	private Liability liabilityModelIncome;


	public TransferDlg() {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		if(resourceExpense != null)
			expenseCur = resourceExpense.getCurCode();
		else
			expenseCur = getModuleConfiguration().getCurrency();
		super.doOnRun();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		if(dateExpense != null && dateIncome != null && liabilityModelExpense != null && liabilityModelIncome != null && (resourceIncome != null || this.resourceFolderIncome != null))
			super.onActionOk(event);
	}

	/**
	 * Обработчик кнопки "Расчитать сумму расхода" 
	 * @param event - событие
	 */
	public void onActionComputeSumExpense(WidgetEvent event) {
		if(sumIncome == null || incomeCur == null || (resourceExpense == null && resourceFolderExpense == null))
			return;

		CurrencyRateAuthority rateAuthority;
		CurrencyRateType rateType;

		if(resourceExpense != null) {
			rateAuthority = resourceExpense.getCurRateAuthority();
			rateType = resourceExpense.getCurRateType();
		}
		else {
			rateAuthority = getModuleConfiguration().getCurRateAuthority();
			rateType = getModuleConfiguration().getCurRateType();
		}

		sumExpense = getCurencyConverter().conversion(expenseCur, incomeCur, rateAuthority, rateType, dateExpense, sumIncome);
	}

	/**
	 * Обработчик кнопки "Расчитать сумму прихода"
	 * @param event - событие
	 */
	public void onActionComputeSumIncome(WidgetEvent event) {
		if(sumExpense == null || expenseCur == null || (resourceIncome == null && resourceFolderIncome == null))
			return;

		CurrencyRateAuthority rateAuthority;
		CurrencyRateType rateType;

		if(resourceIncome != null) {
			rateAuthority = resourceIncome.getCurRateAuthority();
			rateType = resourceIncome.getCurRateType();
		}
		else {
			rateAuthority = getModuleConfiguration().getCurRateAuthority();
			rateType = getModuleConfiguration().getCurRateType();
		}

		sumIncome = getCurencyConverter().conversion(incomeCur, expenseCur, rateAuthority, rateType, dateIncome, sumExpense);
	}

	private CurrencyServiceLocal getCurencyConverter() {
		return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
	}

	private PmcConfig getModuleConfiguration() {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		return ServerUtils.getPersistentManager().find(PmcConfig.class, sysClient.getId());
	}

	/**
	 * @return the dateExpense
	 */
	public Date getDateExpense() {
		return dateExpense;
	}

	/**
	 * @param dateExpense the dateExpense to set
	 */
	public void setDateExpense(Date dateExpense) {
		this.dateExpense = dateExpense;
	}

	/**
	 * @return the dateIncome
	 */
	public Date getDateIncome() {
		return dateIncome;
	}

	/**
	 * @param dateIncome the dateIncome to set
	 */
	public void setDateIncome(Date dateIncome) {
		this.dateIncome = dateIncome;
	}

	/**
	 * @return the liabilityModelExpense
	 */
	public Liability getLiabilityModelExpense() {
		return liabilityModelExpense;
	}

	/**
	 * @param liabilityModelExpense the liabilityModelExpense to set
	 */
	public void setLiabilityModelExpense(Liability liabilityModelExpense) {
		this.liabilityModelExpense = liabilityModelExpense;
	}

	/**
	 * @return the liabilityModelIncome
	 */
	public Liability getLiabilityModelIncome() {
		return liabilityModelIncome;
	}

	/**
	 * @param liabilityModelIncome the liabilityModelIncome to set
	 */
	public void setLiabilityModelIncome(Liability liabilityModelIncome) {
		this.liabilityModelIncome = liabilityModelIncome;
	}

	/**
	 * @return the resourceExpense
	 */
	public PmcResource getResourceExpense() {
		return resourceExpense;
	}

	/**
	 * @param resourceExpense the resourceExpense to set
	 */
	public void setResourceExpense(PmcResource resourceExpense) {
		this.resourceExpense = resourceExpense;
	}

	/**
	 * @return the resourceIncome
	 */
	public PmcResource getResourceIncome() {
		return resourceIncome;
	}

	/**
	 * @param resourceIncome the resourceIncome to set
	 */
	public void setResourceIncome(PmcResource resourceIncome) {
		this.resourceIncome = resourceIncome;
	}

	/**
	 * @return the sumExpense
	 */
	public BigDecimal getSumExpense() {
		return sumExpense;
	}

	/**
	 * @param sumExpense the sumExpense to set
	 */
	public void setSumExpense(BigDecimal sumExpense) {
		this.sumExpense = sumExpense;
	}

	/**
	 * @return the sumIncome
	 */
	public BigDecimal getSumIncome() {
		return sumIncome;
	}

	/**
	 * @param sumIncome the sumIncome to set
	 */
	public void setSumIncome(BigDecimal sumIncome) {
		this.sumIncome = sumIncome;
	}

	/**
	 * @return the expenseCur
	 */
	public Currency getExpenseCur() {
		return expenseCur;
	}

	/**
	 * @param expenseCur the expenseCur to set
	 */
	public void setExpenseCur(Currency expenseCur) {
		this.expenseCur = expenseCur;
	}

	/**
	 * @return the incomeCur
	 */
	public Currency getIncomeCur() {
		return incomeCur;
	}

	/**
	 * @param incomeCur the incomeCur to set
	 */
	public void setIncomeCur(Currency incomeCur) {
		this.incomeCur = incomeCur;
	}

	/**
	 * @return the resourceFolderExpense
	 */
	public Folder getResourceFolderExpense() {
		return resourceFolderExpense;
	}

	/**
	 * @param resourceFolderExpense the resourceFolderExpense to set
	 */
	public void setResourceFolderExpense(Folder resourceFolderExpense) {
		this.resourceFolderExpense = resourceFolderExpense;
	}

	/**
	 * @return the resourceFolderIncome
	 */
	public Folder getResourceFolderIncome() {
		return resourceFolderIncome;
	}

	/**
	 * @param resourceFolderIncome the resourceFolderIncome to set
	 */
	public void setResourceFolderIncome(Folder resourceFolderIncome) {
		this.resourceFolderIncome = resourceFolderIncome;
	}

}
