/*
 * CustomerSelectTableModelItem.java
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
package com.mg.merp.retail.support.ui;

import com.mg.merp.reference.model.Contractor;

/**
 * Позиция модели таблицы "Выбор покупателя"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomerSelectTableModelItem.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class CustomerSelectTableModelItem {
	
	private Contractor contractor;
	private boolean isDisCardOwner;
	
	
	public CustomerSelectTableModelItem() {
	}

	public CustomerSelectTableModelItem(Contractor contractor, boolean isDisCardOwner) {
		this.contractor = contractor;
		this.isDisCardOwner = isDisCardOwner;
	}

	/**
	 * @return the contractor
	 */
	public Contractor getContractor() {
		return this.contractor;
	}

	/**
	 * @param contractor the contractor to set
	 */
	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	/**
	 * @return the isDisCardOwner
	 */
	public boolean isDisCardOwner() {
		return this.isDisCardOwner;
	}

	/**
	 * @param isDisCardOwner the isDisCardOwner to set
	 */
	public void setDisCardOwner(boolean isDisCardOwner) {
		this.isDisCardOwner = isDisCardOwner;
	}
	
}
