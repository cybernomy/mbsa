/**
 * CustomActionManagerService.java
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
package com.mg.merp.baiengine.support.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.ui.CustomActionDescriptor;
import com.mg.framework.api.ui.CustomActionExecutionContext;
import com.mg.framework.api.ui.CustomActionManager;
import com.mg.merp.baiengine.support.CustomActionManagerImpl;

/**
 * Реализация JMX сервиса менеджера настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManagerService.java,v 1.1 2007/11/15 09:20:16 safonov Exp $
 */
public class CustomActionManagerService extends ServiceMBeanSupport implements
		CustomActionManagerServiceMBean {
	private CustomActionManager delegate;

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		this.delegate = new CustomActionManagerImpl();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		this.delegate = null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#executeAction(com.mg.framework.api.ui.CustomActionExecutionContext)
	 */
	public void executeAction(CustomActionExecutionContext context) {
		this.delegate.executeAction(context);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#generateActionsArea(com.mg.framework.api.DataBusinessObjectService)
	 */
	public String generateActionsArea(BusinessObjectService service) {
		return this.delegate.generateActionsArea(service);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.CustomActionManager#getCustomActions(com.mg.framework.api.BusinessObjectService)
	 */
	public CustomActionDescriptor[] getCustomActions(
			BusinessObjectService service) {
		return this.delegate.getCustomActions(service);
	}

}
