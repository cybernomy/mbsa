/*
 * ProductCatalogWarehouseNotFoundException.java
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
package com.mg.merp.planning;

import javax.ejb.ApplicationException;

import com.mg.framework.api.BusinessException;
import com.mg.merp.planning.support.Messages;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.model.Warehouse;

/**
 * �� ������������ ���� �� ������ ����� �������� ���������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ProductCatalogWarehouseNotFoundException.java,v 1.1 2007/07/30 10:37:51 safonov Exp $
 */
@ApplicationException
public class ProductCatalogWarehouseNotFoundException extends BusinessException {
	private String catalog;
	private String warehouse;

	public ProductCatalogWarehouseNotFoundException(Catalog catalog, Warehouse warehouse) {
		super("product catalog warehouse not found");
		this.catalog = catalog.getCode().trim();
		this.warehouse = warehouse.getCode().trim();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return Messages.getInstance().getMessage(Messages.PRODUCT_CATALOG_WAREHOUSE_NOT_FOUND, new Object[] {catalog, warehouse});
	}

}
