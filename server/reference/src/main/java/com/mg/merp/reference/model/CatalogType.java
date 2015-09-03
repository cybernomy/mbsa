/*
 * CatalogType.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип позиции каталога
 * 
 * @author Oleg V. Safonov
 * @version $Id: CatalogType.java,v 1.1 2006/03/29 13:06:23 safonov Exp $
 */
@DataItemName("Reference.Catalog.Type")
public enum CatalogType {
	
	/**
	 * Товар
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Goods")
	GOODS,
	
	/**
	 * Комплект товаров
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.SetOfGoods")
	SET_OF_GOODS,
	
	/**
	 * Тара
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Package")
	PACKAGE,
	
	/**
	 * Услуга нормированная
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.ServiceFixed")
	SERVICE_FIXED,
	
	/**
	 * Услуга не нормированная
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.ServiceNotFixed")
	SERVICE_NOT_FIXED,
	
	/**
	 * Налог
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Tax")
	TAX,
	
	/**
	 * Тариф
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Tariff")
	TARIFF,
	
	/**
	 * Вексель
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.BillOfCredit")
	BILL_OF_CREDIT,
	
	/**
	 * Акция
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Share")
	SHARE,
	
	/**
	 * Облигация
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#Catalog.Type.Bond")
	BOND
}
