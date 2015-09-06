/*
 * BusinessAddinWorkbenchServiceMBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.baiengine.support.jboss;

import com.mg.merp.baiengine.BusinessAddinWorkbench;

import org.jboss.system.ServiceMBean;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessAddinWorkbenchServiceMBean.java,v 1.2 2007/05/03 12:43:36 poroxnenko Exp $
 */
public interface BusinessAddinWorkbenchServiceMBean extends ServiceMBean,
    BusinessAddinWorkbench {

}
