/*
 * EconomicOperEntityInterceptorImpl.java
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
package com.mg.merp.account.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.merp.account.model.EconomicOper;

/**
 * Реализация перехватчика сущностей ХО
 * 
 * @author Oleg V. Safonov
 * @version $Id: EconomicOperEntityInterceptorImpl.java,v 1.2 2007/12/17 09:13:52 safonov Exp $
 */
public class EconomicOperEntityInterceptorImpl extends AbstractEntityInterceptor {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
	 */
	@Override
	public String[] getHandledEntities() {
		return new String[] {EconomicOper.class.getName()};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
	 */
	@Override
	public String getName() {
		return "merp/account/EconomicOperEntityInterceptor";
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostRemove(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPreRemove(PersistentObject entity) {
		//отражение изменений в оборотках
		AccountTurnoverUpdater.execute((EconomicOper) entity, true);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostUpdate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPreUpdate(PersistentObject entity, AttributeMap oldState) {
		//отражение изменений в оборотках
		AccountTurnoverUpdater.execute((EconomicOper) entity, false);
	}

}
