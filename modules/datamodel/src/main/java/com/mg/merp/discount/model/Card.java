/*
 * OvrCard.java
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
package com.mg.merp.discount.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Card.java,v 1.7 2007/09/07 12:01:50 safonov Exp $
 */
public class Card extends com.mg.framework.service.PersistentObjectHibernate
		implements java.io.Serializable {

	// Fields

	private java.lang.Integer Id;

	private com.mg.merp.core.model.Folder Folder;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.Contractor Owner;

	private java.lang.String CardNum;

	private java.math.BigDecimal Discount;

	private java.math.BigDecimal CreditLimit;

	private java.lang.Integer CreditDepth;

	private java.lang.String Comments;

	private boolean IsActive;

	private java.util.Set<ExtraDiscount> extraDiscounts;

	private java.util.Set<CardHist> history;

	private java.util.Set<CardUser> users;

	private java.util.Set<Coefficient> coefficients;

	// Constructors

	/** default constructor */
	public Card() {
	}

	/** constructor with id */
	public Card(java.lang.Integer Id) {
		this.Id = Id;
	}

	// Property accessors
	/**
	 * 
	 */
	@DataItemName("ID")
	public java.lang.Integer getId() {
		return this.Id;
	}

	public void setId(java.lang.Integer Id) {
		this.Id = Id;
	}

	/**
	 * 
	 */

	public com.mg.merp.core.model.Folder getFolder() {
		return this.Folder;
	}

	public void setFolder(com.mg.merp.core.model.Folder Folder) {
		this.Folder = Folder;
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
	@DataItemName("Discount.Car.Owner")
	public com.mg.merp.reference.model.Contractor getOwner() {
		return this.Owner;
	}

	public void setOwner(com.mg.merp.reference.model.Contractor Contractor) {
		this.Owner = Contractor;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.CardNum")
	public java.lang.String getCardNum() {
		return this.CardNum;
	}

	public void setCardNum(java.lang.String Cardnum) {
		this.CardNum = Cardnum;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.Discount")
	public java.math.BigDecimal getDiscount() {
		return this.Discount;
	}

	public void setDiscount(java.math.BigDecimal Discount) {
		this.Discount = Discount;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.CreditLimit")
	public java.math.BigDecimal getCreditLimit() {
		return this.CreditLimit;
	}

	public void setCreditLimit(java.math.BigDecimal Creditlimit) {
		this.CreditLimit = Creditlimit;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.CreditDepth")
	public java.lang.Integer getCreditDepth() {
		return this.CreditDepth;
	}

	public void setCreditDepth(java.lang.Integer Creditdepth) {
		this.CreditDepth = Creditdepth;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.Comments")
	public java.lang.String getComments() {
		return this.Comments;
	}

	public void setComments(java.lang.String Comments) {
		this.Comments = Comments;
	}

	/**
	 * 
	 */
	@DataItemName("Discount.Card.IsActive")
	public boolean getIsActive() {
		return this.IsActive;
	}

	public void setIsActive(boolean IsActive) {
		this.IsActive = IsActive;
	}

	/**
	 * 
	 */

	public java.util.Set<ExtraDiscount> getExtraDiscounts() {
		return this.extraDiscounts;
	}

	public void setExtraDiscounts(java.util.Set<ExtraDiscount> extraDiscounts) {
		this.extraDiscounts = extraDiscounts;
	}

	/**
	 * 
	 */

	public java.util.Set<CardHist> getHistory() {
		return this.history;
	}

	public void setHistory(java.util.Set<CardHist> history) {
		this.history = history;
	}

	/**
	 * 
	 */

	public java.util.Set<CardUser> getUsers() {
		return this.users;
	}

	public void setUsers(java.util.Set<CardUser> users) {
		this.users = users;
	}

	/**
	 * 
	 */

	public java.util.Set<Coefficient> getCoefficients() {
		return this.coefficients;
	}

	public void setCoefficients(java.util.Set<Coefficient> coefficients) {
		this.coefficients = coefficients;
	}

}