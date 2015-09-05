/**
 * CustomActionManagerServiceMBean.java
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

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.ui.CustomActionManager;

/**
 * JMX сервис менеджера настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManagerServiceMBean.java,v 1.1 2007/11/15 09:20:16 safonov Exp $
 */
public interface CustomActionManagerServiceMBean extends ServiceMBean,
		CustomActionManager {

}
