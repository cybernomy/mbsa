/* WorkbenchServiceMBean.java
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
package com.mg.merp.workbench.service.jboss;

import com.mg.merp.workbench.service.Workbench;

import org.jboss.system.ServiceMBean;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: WorkbenchServiceMBean.java,v 1.1 2006/10/09 06:20:20 poroxnenko Exp $
 */
public interface WorkbenchServiceMBean extends ServiceMBean, Workbench {

}
