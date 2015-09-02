/*
 * PartnEmplLinkServiceBean.java
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.reference.PartnEmplLinkServiceLocal;
import com.mg.merp.reference.model.PartnEmplLink;

/**
 * Реализация бизнес-компонента "Связи с сотрудниками"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PartnEmplLinkServiceBean.java,v 1.4 2007/04/03 06:07:39 sharapov Exp $
 */
@Stateless(name="merp/reference/PartnEmplLinkService") //$NON-NLS-1$
public class PartnEmplLinkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PartnEmplLink, Integer> implements PartnEmplLinkServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, PartnEmplLink entity) {
		context.addRule(new MandatoryAttribute(entity, "Employees")); //$NON-NLS-1$
	}

}
