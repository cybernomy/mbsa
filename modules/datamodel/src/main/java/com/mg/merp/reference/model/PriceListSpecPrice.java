/*
 * PriceListSpecPrice.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: PriceListSpecPrice.java,v 1.2 2006/12/27 06:01:30 sharapov Exp $
 */
public class PriceListSpecPrice extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

	// Fields    
	private com.mg.merp.reference.model.PriceListSpecPriceId id;
	private java.math.BigDecimal price;

	// Constructors
	@DataItemName("Reference.PriceListSpec.Price") //$NON-NLS-1$
	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	/** default constructor */
	public PriceListSpecPrice() {
	}

	/** constructor with id */
	public PriceListSpecPrice(com.mg.merp.reference.model.PriceListSpecPriceId id) {
		this.id = id;
	}


	// Property accessors
	/**

	 */
	public com.mg.merp.reference.model.PriceListSpecPriceId getId () {
		return this.id;
	}

	public void setId (com.mg.merp.reference.model.PriceListSpecPriceId id) {
		this.id = id;
	}

}