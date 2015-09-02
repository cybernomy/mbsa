/*
 * InternalInvoiceHeadModelServiceBean.java
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.merp.account.InternalInvoiceHeadModelServiceLocal;
import com.mg.merp.account.InternalInvoiceHeadServiceLocal;
import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.document.model.DocHeadModel;

/**
 * Бизнес-компонент "Образцы внутренних накладных" 
 * 
 * @author leonova
 * @version $Id: InternalInvoiceHeadModelServiceBean.java,v 1.3 2006/09/12 11:16:43 leonova Exp $
 */
@Stateless(name="merp/account/InternalInvoiceHeadModelService")
public class InternalInvoiceHeadModelServiceBean extends DocumentModelServiceBean<DocHeadModel, Integer> implements InternalInvoiceHeadModelServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return InternalInvoiceHeadServiceLocal.DOCSECTION;
	}



}
