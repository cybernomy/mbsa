/*
 * AccConfig.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Бух.учет>"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: AccConfig.java,v 1.4 2007/01/13 13:11:17 sharapov Exp $
 */
public class AccConfig extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private com.mg.merp.paymentalloc.model.DocGroup SaleBookDelivDocGroup;

	private com.mg.merp.paymentalloc.model.DocGroup SaleBookInvoiceDocGroup;

	private com.mg.merp.reference.model.Currency BaseCurrency;

	private com.mg.merp.paymentalloc.model.DocGroup SaleBookAvaninvDocGroup;

	private int sysClientId;

	private com.mg.merp.paymentalloc.model.DocGroup BuyBookDelivDocGroup;

	private com.mg.merp.paymentalloc.model.DocGroup BuyBookInvoiceDocGroup;

	private com.mg.merp.reference.model.Currency NatCurrency;

	private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

	private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

	private java.lang.Integer CurrencyPrec;

	private java.lang.Short SaleBookKind;

	public AccConfig() {
	}

	/** constructor with id */
	public AccConfig(int sysClientId) {
		this.sysClientId = sysClientId;
	}

	public com.mg.merp.reference.model.Currency getBaseCurrency() {
		return BaseCurrency;
	}

	public void setBaseCurrency(
			com.mg.merp.reference.model.Currency baseCurrency) {
		BaseCurrency = baseCurrency;
	}

	@DataItemName("AccountConfig.DocGroup.BuyBookDeliv")
	public com.mg.merp.paymentalloc.model.DocGroup getBuyBookDelivDocGroup() {
		return BuyBookDelivDocGroup;
	}

	public void setBuyBookDelivDocGroup(
			com.mg.merp.paymentalloc.model.DocGroup buyBookDelivDocGroup) {
		BuyBookDelivDocGroup = buyBookDelivDocGroup;
	}
	
	@DataItemName("AccountConfig.DocGroup.BuyBookInvoice")
	public com.mg.merp.paymentalloc.model.DocGroup getBuyBookInvoiceDocGroup() {
		return BuyBookInvoiceDocGroup;
	}

	public void setBuyBookInvoiceDocGroup(
			com.mg.merp.paymentalloc.model.DocGroup buyBookInvoiceDocGroup) {
		BuyBookInvoiceDocGroup = buyBookInvoiceDocGroup;
	}

	public java.lang.Integer getCurrencyPrec() {
		return CurrencyPrec;
	}

	public void setCurrencyPrec(java.lang.Integer currencyPrec) {
		CurrencyPrec = currencyPrec;
	}

	public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
		return CurrencyRateAuthority;
	}

	public void setCurrencyRateAuthority(
			com.mg.merp.reference.model.CurrencyRateAuthority currencyRateAuthority) {
		CurrencyRateAuthority = currencyRateAuthority;
	}

	public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
		return CurrencyRateType;
	}

	public void setCurrencyRateType(
			com.mg.merp.reference.model.CurrencyRateType currencyRateType) {
		CurrencyRateType = currencyRateType;
	}

	public com.mg.merp.reference.model.Currency getNatCurrency() {
		return NatCurrency;
	}

	public void setNatCurrency(com.mg.merp.reference.model.Currency natCurrency) {
		NatCurrency = natCurrency;
	}
	
	@DataItemName("AccountConfig.DocGroup.SaleBookAvaninv")
	public com.mg.merp.paymentalloc.model.DocGroup getSaleBookAvaninvDocGroup() {
		return SaleBookAvaninvDocGroup;
	}

	public void setSaleBookAvaninvDocGroup(
			com.mg.merp.paymentalloc.model.DocGroup saleBookAvaninvDocGroup) {
		SaleBookAvaninvDocGroup = saleBookAvaninvDocGroup;
	}
	
	@DataItemName("AccountConfig.DocGroup.SaleBookDeliv")
	public com.mg.merp.paymentalloc.model.DocGroup getSaleBookDelivDocGroup() {
		return SaleBookDelivDocGroup;
	}

	public void setSaleBookDelivDocGroup(
			com.mg.merp.paymentalloc.model.DocGroup saleBookDelivDocGroup) {
		SaleBookDelivDocGroup = saleBookDelivDocGroup;
	}

	@DataItemName("AccountConfig.DocGroup.SaleBookInvoice")
	public com.mg.merp.paymentalloc.model.DocGroup getSaleBookInvoiceDocGroup() {
		return SaleBookInvoiceDocGroup;
	}

	public void setSaleBookInvoiceDocGroup(
			com.mg.merp.paymentalloc.model.DocGroup saleBookInvoiceDocGroup) {
		SaleBookInvoiceDocGroup = saleBookInvoiceDocGroup;
	}

	public java.lang.Short getSaleBookKind() {
		return SaleBookKind;
	}

	public void setSaleBookKind(java.lang.Short saleBookKind) {
		SaleBookKind = saleBookKind;
	}

	public int getSysClientId() {
		return sysClientId;
	}

	public void setSysClientId(int sysClientId) {
		this.sysClientId = sysClientId;
	}

}