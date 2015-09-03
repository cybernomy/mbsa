/*
 * PersonnelLabourContractServiceBean.java
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
import com.mg.merp.personnelref.PersonnelLabourContractServiceLocal;
import com.mg.merp.personnelref.model.PersonnelLabourContract;

/**
 * Реализация бизнес-компонента "Трудовой договор" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonnelLabourContractServiceBean.java,v 1.4 2007/01/16 07:19:54 sharapov Exp $
 */
@Stateless(name="merp/personnelref/PersonnelLabourContractService") //$NON-NLS-1$
public class PersonnelLabourContractServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelLabourContract, Integer> implements PersonnelLabourContractServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, PersonnelLabourContract entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}


}
