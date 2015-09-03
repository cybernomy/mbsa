/*
 * FinOperEntityInterceptorImpl.java
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
package com.mg.merp.finance.support;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.merp.finance.model.FinOperation;

/**
 * Реализация перехватчика для модели финансовой операции
 * 
 * @author Oleg V. Safonov
 * @version $Id: FinOperEntityInterceptorImpl.java,v 1.2 2007/12/17 09:14:41 safonov Exp $
 */
public class FinOperEntityInterceptorImpl extends AbstractEntityInterceptor {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
	 */
	@Override
	public String[] getHandledEntities() {
		// TODO Auto-generated method stub
		return new String[] {FinOperation.class.getName()};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
	 */
	@Override
	public String getName() {
		return "merp/finance/FinOperEntityInterceptor";
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostUpdate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPostUpdate(PersistentObject entity, AttributeMap oldState) {
		FinanceTurnoverUpdater.execute(((FinOperation) entity).getId());
	}

}
