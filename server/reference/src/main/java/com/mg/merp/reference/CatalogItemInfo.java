/*
 * Created on 18.01.2005
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 */
package com.mg.merp.reference;

import java.io.Serializable;

/**
 * @author krivopoustov
 */
public class CatalogItemInfo implements Serializable {
	public int catalogId;
	public int pricelistspecId;
	double price;

	public CatalogItemInfo() {
		super();
	}
	
	public CatalogItemInfo(int catalogId, int pricelistspecId, double price) {
		super();
		this.catalogId = catalogId;
		this.pricelistspecId = pricelistspecId;
		this.price = price;
	}
}
