/*
 * ScheduleConfigServiceBean.java
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

package com.mg.merp.lbschedule.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.lbschedule.ScheduleConfigServiceLocal;
import com.mg.merp.lbschedule.model.ScheduleConfig;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Графики исполнения обязательств>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ScheduleConfigServiceBean.java,v 1.5 2007/05/11 12:20:12 sharapov Exp $
 */
@Stateless(name="merp/lbschedule/ScheduleConfigService") //$NON-NLS-1$
public class ScheduleConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScheduleConfig, Integer> implements ScheduleConfigServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(ScheduleConfig entity) {
		SysClient sysClient = (SysClient) ServerUtils.getCurrentSession().getSystemTenant();
		entity.setSysClientId(sysClient.getId());
	}

}
