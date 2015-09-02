/*
 * ResourcegroupServiceBean.java
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

package com.mg.merp.mfreference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.ResourceGroupServiceLocal;
import com.mg.merp.mfreference.model.ResourceGroup;

/**
 * Бизнес-компонент "Группы ресурсов" 
 * 
 * @author leonova
 * @version $Id: ResourceGroupServiceBean.java,v 1.3 2006/08/24 12:31:25 leonova Exp $
 */
@Stateless(name="merp/mfreference/ResourceGroupService")
public class ResourceGroupServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ResourceGroup, Integer> implements ResourceGroupServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ResourceGroup entity) {
		context.addRule(new MandatoryStringAttribute(entity, "ResourceGroupCode"));
		context.addRule(new MandatoryStringAttribute(entity, "Description"));
		context.addRule(new MandatoryAttribute(entity, "ResourceType"));
		context.addRule(new MandatoryAttribute(entity, "Measure"));
		context.addRule(new MandatoryAttribute(entity, "PlanningLevel"));		
	}



}
