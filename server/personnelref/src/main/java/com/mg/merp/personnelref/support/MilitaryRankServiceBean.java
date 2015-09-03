/*
 * MilitaryRankServiceBean.java
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
import com.mg.merp.personnelref.MilitaryRankServiceLocal;
import com.mg.merp.personnelref.model.MilitaryRank;

/**
 * Реализация бизнес-компонента "Воинсвие звания" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MilitaryRankServiceBean.java,v 1.4 2007/01/16 09:27:07 sharapov Exp $
 */
@Stateless(name="merp/personnelref/MilitaryRankService") //$NON-NLS-1$
public class MilitaryRankServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MilitaryRank, Integer> implements MilitaryRankServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, MilitaryRank entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}

}
