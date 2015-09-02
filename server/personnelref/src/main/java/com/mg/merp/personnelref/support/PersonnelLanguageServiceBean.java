/*
 * PersonnelLanguageServiceBean.java
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
import com.mg.merp.personnelref.PersonnelLanguageServiceLocal;
import com.mg.merp.personnelref.model.PersonnelLanguage;

/**
 * Реализация бизнес-компонента "Знание иностранных языков" 
 * 
 * @author Artem V.Sharapov
 * @author leonova
 * @version $Id: PersonnelLanguageServiceBean.java,v 1.4 2007/01/16 08:01:39 sharapov Exp $
 */
@Stateless(name="merp/personnelref/PersonnelLanguageService") //$NON-NLS-1$
public class PersonnelLanguageServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelLanguage, Integer> implements PersonnelLanguageServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, PersonnelLanguage entity) {
		context.addRule(new MandatoryStringAttribute(entity,"ForeignLanguage")); //$NON-NLS-1$
	}

}
