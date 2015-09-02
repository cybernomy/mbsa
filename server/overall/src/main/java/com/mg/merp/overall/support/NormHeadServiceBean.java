/*
 * NormHeadServiceBean.java
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

package com.mg.merp.overall.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.overall.NormHeadServiceLocal;
import com.mg.merp.overall.model.NormHead;

/**
 * Реализация бизнес-компонента "Нормы выдачи спецодежды"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: NormHeadServiceBean.java,v 1.1 2008/06/30 04:19:36 alikaev Exp $
 */
@Stateless(name="merp/overall/NormHeadService") //$NON-NLS-1$
public class NormHeadServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<NormHead, Integer> implements NormHeadServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, NormHead entity) {
		context.addRule(new MandatoryStringAttribute(entity, "OvrNormName")); //$NON-NLS-1$
	}

}
