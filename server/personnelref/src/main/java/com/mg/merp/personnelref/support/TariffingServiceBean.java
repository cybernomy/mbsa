/*
 * TariffingServiceBean.java
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

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.personnelref.TariffingServiceLocal;
import com.mg.merp.personnelref.model.Tariffing;

/**
 * Реализация бизнес-компонента "Тарифы должности" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TariffingServiceBean.java,v 1.4 2007/08/22 07:02:52 sharapov Exp $
 */
@Stateless(name="merp/personnelref/TariffingService") //$NON-NLS-1$
public class TariffingServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Tariffing, Integer> implements TariffingServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Tariffing entity) {
		context.addRule(new MandatoryAttribute(entity, "Category")); //$NON-NLS-1$
		context.addRule(new DateIntervalRule(entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(Tariffing entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(Tariffing entity) {
		adjust(entity);
	}

	private void adjust(Tariffing entity) {
		PersonnelrefUtils.checkDateInterval(entity);
	}

}
