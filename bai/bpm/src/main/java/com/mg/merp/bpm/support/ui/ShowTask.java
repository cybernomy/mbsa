/*
 * ShowTask.java
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
package com.mg.merp.bpm.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.MenuCommand;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.SecurityUtils;
import com.mg.merp.bpm.BusinessProccessManagerServiceLocal;

import java.util.Map;

/**
 * Обработчик команды показа списка задач из меню
 *
 * @author Oleg V. Safonov
 * @version $Id: ShowTask.java,v 1.2 2008/03/11 08:28:02 sharapov Exp $
 */
public class ShowTask implements MenuCommand {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MenuCommand#execute()
   */
  public void execute() throws Exception {
    TaskBr form = (TaskBr) UIProducer.produceForm("com/mg/merp/bpm/resources/TaskBr.mfd.xml"); //$NON-NLS-1$
    form.execute();
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
    return SecurityUtils.tryCheckPermission(new BusinessMethodPermission(BusinessProccessManagerServiceLocal.SERVICE_NAME, BusinessProccessManagerServiceLocal.LOAD_TASKS_METHOD));
  }

}
