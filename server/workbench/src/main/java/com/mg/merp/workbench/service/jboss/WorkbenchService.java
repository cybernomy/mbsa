/* WorkbenchService.java
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

import com.mg.merp.security.model.Groups;
import com.mg.merp.workbench.support.WorkbenchImpl;

import org.jboss.system.ServiceMBeanSupport;

import java.util.List;

import javax.swing.tree.TreeModel;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: WorkbenchService.java,v 1.4 2007/04/25 08:40:26 poroxnenko Exp $
 */
public class WorkbenchService extends ServiceMBeanSupport implements WorkbenchServiceMBean {

  private WorkbenchImpl delegate = new WorkbenchImpl();

  /* (non-Javadoc)
   * @see com.mg.merp.workbench.service.Workbench#testConnectionOK()
   */
  public void testConnectionOK() {
    delegate.testConnectionOK();
  }

  public TreeModel getSysClasses() throws Exception {
    return delegate.getSysClasses();
  }

  public List<Groups> getSecGroups() throws Exception {
    return delegate.getSecGroups();
  }

}
