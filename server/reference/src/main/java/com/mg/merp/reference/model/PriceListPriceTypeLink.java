/*
 * PriceListPriceTypeLink.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 *Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Связи с типами цен"
 * 
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: PriceListPriceTypeLink.java,v 1.2 2005/07/13 04:55:16
 */
@DataItemName("Reference.PriceListPriceTypeLink") //$NON-NLS-1$
public class PriceListPriceTypeLink extends com.mg.framework.service.PersistentObjectHibernate implements
java.io.Serializable {

	// Fields

	private com.mg.merp.baiengine.model.Repository AlgRepository;

	private com.mg.merp.reference.model.PriceType PriceType;

	private com.mg.merp.core.model.SysClient SysClient;

	private com.mg.merp.reference.model.PriceListHead PriceListHead;

	private java.lang.Short Priority;

	private java.math.BigDecimal RelativePercent;

	private java.lang.Integer Id;

	// Constructors

	/** default constructor */
	public PriceListPriceTypeLink() {
	}

	// Property accessors
	/**
	 * 
	 */	
	@DataItemName("Reference.PrList.Repository") //$NON-NLS-1$
	public com.mg.merp.baiengine.model.Repository getAlgRepository() {
		return this.AlgRepository;
	}

	public void setAlgRepository(
			com.mg.merp.baiengine.model.Repository AlgRepository) {
		this.AlgRepository = AlgRepository;
	}

	/**
	 * 
	 */	
	public com.mg.merp.reference.model.PriceType getPriceType() {
		return this.PriceType;
	}

	public void setPriceType(com.mg.merp.reference.model.PriceType PriceType) {
		this.PriceType = PriceType;
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
	public com.mg.merp.reference.model.PriceListHead getPriceListHead() {
		return this.PriceListHead;
	}

	public void setPriceListHead(
			com.mg.merp.reference.model.PriceListHead PriceListHead) {
		this.PriceListHead = PriceListHead;
	}

	/**
	 * 
	 */
	@DataItemName("Reference.PrList.Priority") //$NON-NLS-1$
	public java.lang.Short getPriority() {
		return this.Priority;
	}

	public void setPriority(java.lang.Short Priority) {
		this.Priority = Priority;
	}

	/**
	 * 
	 */
	public java.math.BigDecimal getRelativePercent() {
		return this.RelativePercent;
	}

	public void setRelativePercent(java.math.BigDecimal RelativePercent) {
		this.RelativePercent = RelativePercent;
	}

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PriceListPriceTypeLink))
			return false;
		PriceListPriceTypeLink castOther = (PriceListPriceTypeLink) other;

		return (this.getAlgRepository() == castOther.getAlgRepository())
		|| (this.getAlgRepository() == null ? false : (castOther
				.getAlgRepository() == null ? false : this.getAlgRepository()
				.equals(castOther.getAlgRepository())))
		&& (this.getPriceType() == castOther.getPriceType())
		|| (this.getPriceType() == null ? false : (castOther
				.getPriceType() == null ? false : this.getPriceType()
				.equals(castOther.getPriceType())))
		&& (this.getSysClient() == castOther.getSysClient())
		|| (this.getSysClient() == null ? false : (castOther
				.getSysClient() == null ? false : this.getSysClient()
				.equals(castOther.getSysClient())))
		&& (this.getPriceListHead() == castOther.getPriceListHead())
		|| (this.getPriceListHead() == null ? false : (castOther
				.getPriceListHead() == null ? false : this.getPriceListHead()
				.equals(castOther.getPriceListHead())))
		&& (this.getPriority() == castOther.getPriority())
		|| (this.getPriority() == null ? false : (castOther
				.getPriority() == null ? false : this.getPriority()
				.equals(castOther.getPriority())))
		&& (this.getRelativePercent() == castOther.getRelativePercent())
		|| (this.getRelativePercent() == null ? false : (castOther
				.getRelativePercent() == null ? false : this.getRelativePercent().equals(castOther.getRelativePercent())))
		&& (this.getId() == castOther.getId())
		|| (this.getId() == null ? false : (castOther.getId() == null ? false : this.getId()
				.equals(castOther.getId())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + this.getAlgRepository().hashCode();
		result = 37 * result + this.getPriceType().hashCode();
		result = 37 * result + this.getSysClient().hashCode();
		result = 37 * result + this.getPriceListHead().hashCode();
		result = 37 * result + this.getPriority().hashCode();
		result = 37 * result + this.getRelativePercent().hashCode();
		result = 37 * result + this.getId().hashCode();
		return result;
	}

}