/*
 * BillHeadModelInServiceBean.java
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
import com.mg.merp.warehouse.BillHeadInServiceLocal;
import com.mg.merp.warehouse.BillHeadModelInServiceLocal;
import com.mg.merp.warehouse.model.BillHeadModel;

/**
 * Бизнес-компонент "Образцы входящих счетов" 
 * 
 * @author leonova
 * @version $Id: BillHeadModelInServiceBean.java,v 1.3 2006/09/12 10:48:36 leonova Exp $
 */
@Stateless(name="merp/warehouse/BillHeadModelInService")
public class BillHeadModelInServiceBean extends DocumentModelServiceBean<BillHeadModel, Integer> implements BillHeadModelInServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return BillHeadInServiceLocal.DOCSECTION;
	}



}
