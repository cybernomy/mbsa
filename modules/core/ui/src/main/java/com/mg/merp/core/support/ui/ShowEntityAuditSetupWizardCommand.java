/**
 * ShowEntityAuditSetupWizardCommand.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.core.DatabaseAuditSetupServiceLocal;

import java.util.Map;

/**
 * Реализация команды показа настройки аудита хранилища данных
 *
 * @author Oleg V. Safonov
 * @version $Id: ShowEntityAuditSetupWizardCommand.java,v 1.2 2008/03/03 13:05:04 safonov Exp $
 */
public class ShowEntityAuditSetupWizardCommand implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    ((EntityAuditSetupWizard) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.core.EntityAuditSetupWizard")).execute();
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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(DatabaseAuditSetupServiceLocal.SERVICE_NAME, BusinessMethodPermission.BROWSE_METHOD));
  }

}
