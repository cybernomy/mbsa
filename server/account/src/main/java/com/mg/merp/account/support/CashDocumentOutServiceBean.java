/*
 * CashDocumentOutServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.merp.account.CashDocumentModelOutServiceLocal;
import com.mg.merp.account.CashDocumentOutServiceLocal;
import com.mg.merp.account.model.CashDocument;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.DocumentServiceBean;

/**
 * Бизнес-компонент "Приходные кассовые документы" 
 * 
 * @author leonova
 * @version $Id: CashDocumentOutServiceBean.java,v 1.8 2008/03/12 11:21:40 alikaev Exp $
 */
@Stateless(name="merp/account/CashDocumentOutService") //$NON-NLS-1$
public class CashDocumentOutServiceBean extends DocumentServiceBean<CashDocument, Integer, CashDocumentModelOutServiceLocal> implements CashDocumentOutServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doGetConfiguration()
	 */
	@Override
	protected Configuration doGetConfiguration() {
		return ConfigurationHelper.getDocumentConfiguration();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CashDocument entity) {
		super.onValidate(context, entity);
		context.addRule(new MandatoryAttribute(entity, "Company")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "To")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return CashDocumentOutServiceLocal.DOCSECTION;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(CashDocument entity) {
		super.doAdjust(entity);
		entity.setFrom(entity.getCompany());
	}
		
}
