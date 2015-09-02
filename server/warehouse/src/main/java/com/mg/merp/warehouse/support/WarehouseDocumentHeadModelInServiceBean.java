/*
 * WarehouseDocumentHeadModelInServiceBean.java
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
 *
 */

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.warehouse.WarehouseDocumentHeadInServiceLocal;
import com.mg.merp.warehouse.WarehouseDocumentHeadModelInServiceLocal;
import com.mg.merp.warehouse.model.StockDocumentHeadModel;

/**
 * Бизнес-компонент "Образцы приходных ордеров" 
 * 
 * @author leonova
 * @version $Id: WarehouseDocumentHeadModelInServiceBean.java,v 1.3 2006/09/12 10:48:36 leonova Exp $
 */
@Stateless(name="merp/warehouse/WarehouseDocumentHeadModelInService")
public class WarehouseDocumentHeadModelInServiceBean extends DocumentModelServiceBean<StockDocumentHeadModel, Integer> implements WarehouseDocumentHeadModelInServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return WarehouseDocumentHeadInServiceLocal.DOCSECTION;
	}



}
