/**
 * MonitorUserSessionMenuCommand.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.support.ui;

import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.SecurityUtils;

import java.util.Map;

/**
 * Обработчик показа монитора сессий для главного меню
 *
 * @author Oleg V. Safonov
 * @version $Id: MonitorUserSessionMenuCommand.java,v 1.1 2008/07/14 14:24:27 safonov Exp $
 */
public class MonitorUserSessionMenuCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.core.MonitorUserSessionForm").run();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#init(java.util.Map)
   */
  public void init(Map<String, String> params) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#isPermitted()
   */
  public boolean isPermitted() {
    return SecurityUtils.tryCheckPermission(new RuntimePermission("showMonitorUser"));
  }

}
