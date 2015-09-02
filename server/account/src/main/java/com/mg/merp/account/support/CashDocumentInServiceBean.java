/*
 * CashDocumentInServiceBean.java
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
import com.mg.merp.account.CashDocumentInServiceLocal;
import com.mg.merp.account.CashDocumentModelInServiceLocal;
import com.mg.merp.account.model.CashDocument;
import com.mg.merp.document.Configuration;
import com.mg.merp.document.generic.DocumentServiceBean;

/**
 * Бизнес-компонент "Приходные кассовые документы" 
 * 
 * @author leonova
 * @version $Id: CashDocumentInServiceBean.java,v 1.7 2008/03/12 11:21:40 alikaev Exp $
 */
@Stateless(name="merp/account/CashDocumentInService")
public class CashDocumentInServiceBean extends DocumentServiceBean<CashDocument, Integer, CashDocumentModelInServiceLocal> implements CashDocumentInServiceLocal {

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
		context.addRule(new MandatoryAttribute(entity, "Company"));
		context.addRule(new MandatoryAttribute(entity, "From"));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return CashDocumentInServiceLocal.DOCSECTION;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(CashDocument entity) {
		super.doAdjust(entity);
		entity.setTo(entity.getCompany());
	}
	
}
