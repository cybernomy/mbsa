/*
 * IdentDocKindServiceBean.java
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.IdentDocKindServiceLocal;
import com.mg.merp.reference.model.IdentDocKind;

/**
 * Ѕизнес-компонент "¬иды документов, удостовер€ющих личность" 
 * 
 * @author leonova
 * @version $Id: IdentDocKindServiceBean.java,v 1.4 2006/10/20 05:40:26 leonova Exp $
 */
@Stateless(name="merp/reference/IdentDocKindService")
public class IdentDocKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<IdentDocKind, Integer> implements IdentDocKindServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, IdentDocKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "KCode"));
		context.addRule(new MandatoryStringAttribute(entity, "KName"));		
	}

}
