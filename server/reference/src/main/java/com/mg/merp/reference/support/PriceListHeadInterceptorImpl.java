/*
 * PriceListHeadInterceptorImpl.java
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
package com.mg.merp.reference.support;

import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.security.SecuritySystem;
import com.mg.framework.generic.AbstractEntityInterceptor;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.PriceListHead;

/**
 * Перехватчик для объекта-сущности "Прайс-лист", реализует инициализацию прав пользователей
 * после создания
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListHeadInterceptorImpl.java,v 1.1 2008/05/13 09:53:53 alikaev Exp $
 */
public class PriceListHeadInterceptorImpl extends AbstractEntityInterceptor {

	/**
	 * Выдать права на объект
	 * 
	 * @param priceListHeadId	
	 * 					- идентификатор прайс-листа
	 * @param groupId	
	 * 					- группа пользователя
	 */
	private void grantDocTypePermission(int priceListHeadId, int groupId) {
		JdbcTemplate.getInstance().update("INSERT INTO PRICELISTHEAD_RIGHTS (ID, REC_ID, GROUP_ID, RIGHTS) VALUES (?, ?, ?, 1)", new Object[] {DatabaseUtils.getSequenceNextValue("PRICELISTHEAD_RIGHTS_ID_GEN"), priceListHeadId, groupId}); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getHandledEntities()
	 */
	@Override
	public String[] getHandledEntities() {
		return new String[] {PriceListHead.class.getName()};
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#getName()
	 */
	@Override
	public String getName() {
		return "PriceListHeadInterceptor";
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractEntityInterceptor#onPostPersist(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	public void onPostPersist(PersistentObject entity) {
		int priceListHeadId = ((PriceListHead) entity).getId();
		grantDocTypePermission(priceListHeadId, SecuritySystem.ADMIN_GROUP);
		for (int group : ServerUtils.getUserProfile().getGroups()) {
			if (group != SecuritySystem.ADMIN_GROUP) //exclude admins duplicate
				grantDocTypePermission(priceListHeadId, group);
		}
	}
	
}
