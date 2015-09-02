/*
 * ExtraDiscount.java
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

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Дополнительные скидки"
 * 
 * @author Artem V. Sharapov 
 * @version $Id: ExtraDiscount.java,v 1.6 2007/10/30 13:58:58 sharapov Exp $
 */
public class ExtraDiscount extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.discount.model.Card Card;

	private com.mg.merp.core.model.SysClient SysClient;

	private java.lang.String Name;

	private java.math.BigDecimal Discount;

	private java.math.BigDecimal PlanSale;

	//private java.util.Date PlanDate;

	private Date dateFrom;

	private Date dateTill;
	
	private boolean isActive;
	
	private com.mg.merp.security.model.SecUser user;

	private java.math.BigDecimal FactSale;

	private java.lang.String Comments;


	// Constructors

	/** default constructor */
	public ExtraDiscount() {
	}

	/** constructor with id */
	public ExtraDiscount(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	
	/**
	 * 
	 */
	@DataItemName("ID") //$NON-NLS-1$
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */
	public com.mg.merp.discount.model.Card getCard() {
		return this.Card;
	}

	public void setCard(com.mg.merp.discount.model.Card DisCard) {
		this.Card = DisCard;
	}

	/**
	 * 
	 */
	public com.mg.merp.core.model.SysClient getSysClient() {
		return this.SysClient;
	}

	public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
		this.SysClient = SysClient;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Name") //$NON-NLS-1$
	public java.lang.String getName() {
		return this.Name;
	}

	public void setName(java.lang.String Name) {
		this.Name = Name;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.ExtraDisc.Discount") //$NON-NLS-1$
	public java.math.BigDecimal getDiscount() {
		return this.Discount;
	}

	public void setDiscount(java.math.BigDecimal Discount) {
		this.Discount = Discount;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.ExtraDisc.PlanSale") //$NON-NLS-1$
	public java.math.BigDecimal getPlanSale() {
		return this.PlanSale;
	}

	public void setPlanSale(java.math.BigDecimal Plansale) {
		this.PlanSale = Plansale;
	}

//	/**
//	* 
//	*/
//	@DataItemName("Discount.ExtraDisc.PlanDate")
//	public java.util.Date getPlanDate() {
//	return this.PlanDate;
//	}

//	public void setPlanDate(java.util.Date Plandate) {
//	this.PlanDate = Plandate;
//	}

	/**
	 * 
	 */
	@DataItemName("Discount.ExtraDisc.Factsale") //$NON-NLS-1$
	public java.math.BigDecimal getFactSale() {
		return this.FactSale;
	}

	public void setFactSale(java.math.BigDecimal Factsale) {
		this.FactSale = Factsale;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.ExtraDisc.Comments") //$NON-NLS-1$
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * @return the dateFrom
	 */
	@DataItemName("Discount.ExtraDisc.DateFrom") //$NON-NLS-1$
	public Date getDateFrom() {
		return this.dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTill
	 */
	@DataItemName("Discount.ExtraDisc.DateTill") //$NON-NLS-1$
	public Date getDateTill() {
		return this.dateTill;
	}

	/**
	 * @param dateTill the dateTill to set
	 */
	public void setDateTill(Date dateTill) {
		this.dateTill = dateTill;
	}

	/**
	 * @return the isActive
	 */
	@DataItemName("Discount.ExtraDiscount.IsActive")
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
	 * @return the user
	 */
	@DataItemName("Discount.CardHist.User")
	public com.mg.merp.security.model.SecUser getUser() {
		return this.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(com.mg.merp.security.model.SecUser user) {
		this.user = user;
	}

}