/*
 * DocGroupServiceBean.java
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

package com.mg.merp.paymentalloc.support;

import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.paymentalloc.DocGroupLinkServiceLocal;
import com.mg.merp.paymentalloc.DocGroupServiceLocal;
import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.paymentalloc.model.DocGroupLink;

/**
 * Реализация бизнес-компонента "Группы документов" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocGroupServiceBean.java,v 1.4 2007/11/08 06:49:12 sharapov Exp $
 */
@Stateless(name="merp/paymentalloc/DocGroupService") //$NON-NLS-1$
public class DocGroupServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocGroup, Integer> implements DocGroupServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, DocGroup entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(DocGroup entity, DocGroup entityClone) {
		final String DOC_GROUP_ATTRIBUTE_NAME = "DocGroup"; //$NON-NLS-1$
		DocGroupLinkServiceLocal docGroupLinkService = (DocGroupLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DocGroupLinkServiceLocal.SERVICE_NAME);
		AttributeMap initAttr = new LocalDataTransferObject();
		initAttr.put(DOC_GROUP_ATTRIBUTE_NAME, entityClone);
		for (DocGroupLink docGroupLink : docGroupLinkService.findByCriteria(Restrictions.eq(DOC_GROUP_ATTRIBUTE_NAME, entity)))
			docGroupLinkService.clone(docGroupLink, true, initAttr);
	}

}
