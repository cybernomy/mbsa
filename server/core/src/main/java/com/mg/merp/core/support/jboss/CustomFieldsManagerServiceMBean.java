/*
 * CustomFieldsManagerServiceMBean.java
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
package com.mg.merp.core.support.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.metadata.CustomFieldsManager;

/**
 * Сервис менеджера управления пользовательскими полями
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManagerServiceMBean.java,v 1.1 2007/01/25 15:44:35 safonov Exp $
 */
public interface CustomFieldsManagerServiceMBean extends CustomFieldsManager,
		ServiceMBean {
}
