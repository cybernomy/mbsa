/**
 * AbstractBOMResource.java.java
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
package com.mg.merp.mfreference.generic;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.model.BomRouteResource;
import com.mg.merp.mfreference.support.MfUtils;

/**
 * Базовая реализация ресурсов состава изделия
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractBOMResource.java,v 1.1 2007/08/21 15:24:41 safonov Exp $
 */
public class AbstractBOMResource<T extends BomRouteResource> extends
		AbstractPOJODataBusinessObjectServiceBean<T, Integer> {

	protected void doAdjust(T entity) {
		MfUtils.adjustEffectiveDate(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(T entity) {
		entity.setStandartCostDetail(MfUtils.createCostDetail());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(T entity) {
		doAdjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(T entity) {
		doAdjust(entity);
	}

}
