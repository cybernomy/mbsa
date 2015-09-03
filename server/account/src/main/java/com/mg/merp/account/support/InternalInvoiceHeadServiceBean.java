/*
 * InternalInvoiceHeadServiceBean.java
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.InternalInvoiceHeadModelServiceLocal;
import com.mg.merp.account.InternalInvoiceHeadServiceLocal;
import com.mg.merp.account.InternalInvoiceSpecServiceLocal;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.GoodsDocumentServiceBean;
import com.mg.merp.document.model.DocHead;

/**
 * Бизнес-компонент "Внутренние накладные" 
 * 
 * @author leonova
 * @version $Id: InternalInvoiceHeadServiceBean.java,v 1.6 2007/03/23 16:11:41 safonov Exp $
 */
@Stateless(name="merp/account/InternalInvoiceHeadService")
public class InternalInvoiceHeadServiceBean extends GoodsDocumentServiceBean<DocHead, Integer, InternalInvoiceHeadModelServiceLocal, InternalInvoiceSpecServiceLocal> implements InternalInvoiceHeadServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
	 */
	@Override
	protected Configuration doGetConfiguration() {
		return ConfigurationHelper.getDocumentConfiguration();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, DocHead entity) {
		super.onValidate(context, entity);

		context.addRule(new MandatoryAttribute(entity, "To"));
		context.addRule(new MandatoryAttribute(entity, "From"));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InternalInvoiceHeadServiceLocal.DOCSECTION;
	}

}
