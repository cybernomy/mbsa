/*
 * UIProfileManagerServiceMBean.java
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
package com.mg.merp.security.support.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.ui.UIProfileManager;

/**
 * MBean менеджера профиля пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfileManagerServiceMBean.java,v 1.1 2007/03/13 13:47:30 safonov Exp $
 */
public interface UIProfileManagerServiceMBean extends UIProfileManager,
		ServiceMBean {

}
