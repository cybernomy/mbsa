/*
 * PersonElectronicAddressServiceBean.java
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.PersonElectronicAddressServiceLocal;
import com.mg.merp.reference.model.PersonElectronicAddress;

/**
 * Бизнес-копмонент "Электронные адреса физических лиц"
 * 
 * @author leonova
 * @version $Id: PersonElectronicAddressServiceBean.java,v 1.3 2006/09/13 07:00:34 leonova Exp $
 */
@Stateless(name="merp/reference/PersonElectronicAddressService")
public class PersonElectronicAddressServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonElectronicAddress, Integer> implements PersonElectronicAddressServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PersonElectronicAddress entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Address"));
	}



}
