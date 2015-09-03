/*
 * BillSpecOutServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.merp.warehouse.BillHeadOutServiceLocal;
import com.mg.merp.warehouse.BillSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocumentSpec;
import com.mg.merp.warehouse.model.BillSpec;

/**
 * Бизнес-компонент "Спецификация исходящих счетов" 
 * 
 * @author leonova
 * @version $Id: BillSpecOutServiceBean.java,v 1.6 2007/09/07 12:27:13 safonov Exp $
 */
@Stateless(name="merp/warehouse/BillSpecOutService")
public class BillSpecOutServiceBean extends AbstractWarehouseDocumentSpec<BillSpec> implements BillSpecOutServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return BillHeadOutServiceLocal.DOCSECTION;
	}

}
