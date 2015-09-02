/*
 * JobServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.JobServiceLocal;
import com.mg.merp.personnelref.model.PrefJob;

/**
 * Реализация бизнес-компонента "Профессии на предприятии" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: JobServiceBean.java,v 1.4 2007/01/24 12:07:21 sharapov Exp $
 */
@Stateless(name="merp/personnelref/JobService") //$NON-NLS-1$
public class JobServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PrefJob, Integer> implements JobServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, PrefJob entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

}
