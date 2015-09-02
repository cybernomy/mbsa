/*
 * PromotionLine.java
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
package com.mg.merp.discount.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.CatalogFolder;
import com.mg.merp.reference.model.Currency;

/**
 * Модель бизнес-компонента "Позиция рекламного мероприятия"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PromotionLine.java,v 1.1 2007/10/30 14:02:22 sharapov Exp $
 */
public class PromotionLine extends PersistentObjectHibernate implements Serializable {
	
	// Fields
	
	private Integer id;
	
	private Promotion promotion;
	
	private CatalogFolder catalogFolder;
	
	private Catalog catalog;
	
	private PromotionType promotionType;
	
	private BigDecimal discount;
	
	private BigDecimal price;
	
	private Currency currency;
	
	private boolean isActive;
	
	private boolean isApplyDiscountGroup;
	
	private boolean isApplyDiscountOnDoc;
			
	private SysClient sysClient;
	
	
	// Default constructor
	public PromotionLine() {
	}
	
	// Constructor with id
	public PromotionLine(Integer id) {
		this.id = id;
	}

	
	// Property accessors
	
	/**
	 * @return the id
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return this.promotion;
	}

	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	/**
	 * @return the catalogFolder
	 */
	public CatalogFolder getCatalogFolder() {
		return this.catalogFolder;
	}

	/**
	 * @param catalogFolder the catalogFolder to set
	 */
	public void setCatalogFolder(CatalogFolder catalogFolder) {
		this.catalogFolder = catalogFolder;
	}

	/**
	 * @return the catalog
	 */
	public Catalog getCatalog() {
		return this.catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return the discount
	 */
	@DataItemName("Discount.PromotionLine.Discount") //$NON-NLS-1$
	public BigDecimal getDiscount() {
		return this.discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * @return the price
	 */
	@DataItemName("Discount.PromotionLine.Price") //$NON-NLS-1$
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the sysClient
	 */
	public SysClient getSysClient() {
		return this.sysClient;
	}

	/**
	 * @param sysClient the sysClient to set
	 */
	public void setSysClient(SysClient sysClient) {
		this.sysClient = sysClient;
	}

	/**
	 * @return the isActive
	 */
	@DataItemName("Discount.PromotionLine.IsActive") //$NON-NLS-1$
	public boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isApplyDiscountGroup
	 */
	@DataItemName("Discount.PromotionLine.IsApplyDiscountGroup") //$NON-NLS-1$
	public boolean getIsApplyDiscountGroup() {
		return this.isApplyDiscountGroup;
	}

	/**
	 * @param isApplyDiscountGroup the isApplyDiscountGroup to set
	 */
	public void setIsApplyDiscountGroup(boolean isApplyDiscountGroup) {
		this.isApplyDiscountGroup = isApplyDiscountGroup;
	}

	/**
	 * @return the isApplyDiscountOnDoc
	 */
	@DataItemName("Discount.PromotionLine.IsApplyDiscountOnDoc") //$NON-NLS-1$
	public boolean getIsApplyDiscountOnDoc() {
		return this.isApplyDiscountOnDoc;
	}

	/**
	 * @param isApplyDiscountOnDoc the isApplyDiscountOnDoc to set
	 */
	public void setIsApplyDiscountOnDoc(boolean isApplyDiscountOnDoc) {
		this.isApplyDiscountOnDoc = isApplyDiscountOnDoc;
	}

	/**
	 * @return the promotionType
	 */
	public PromotionType getPromotionType() {
		return this.promotionType;
	}

	/**
	 * @param promotionType the promotionType to set
	 */
	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
