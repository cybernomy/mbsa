/*
 * VersionServiceBean.java
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

package com.mg.merp.paymentcontrol.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.paymentcontrol.VersionServiceLocal;
import com.mg.merp.paymentcontrol.model.Version;
import com.mg.merp.security.model.SecUser;

/**
 * Реализация бизнес-компонента "Версии планирования" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: VersionServiceBean.java,v 1.5 2007/05/22 12:45:02 sharapov Exp $
 */
@Stateless(name="merp/paymentcontrol/VersionService") //$NON-NLS-1$
public class VersionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Version, Integer> implements VersionServiceLocal {
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(Version entity) {
		super.onInitialize(entity);
		adjustEntity(entity);
	}

	private void adjustEntity(Version entity) {
		entity.setCreator(ServerUtils.getPersistentManager().find(SecUser.class, ServerUtils.getUserProfile().getIdentificator()));
	}
	
}
