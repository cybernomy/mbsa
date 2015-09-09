/*
 * UIProfileManagerService.java
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

import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.api.ui.UIProfileManager;
import com.mg.merp.security.support.UIProfileManagerServiceImpl;

import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация поддержки MBean менеджера профиля пользовательского интерфейса
 *
 * @author Oleg V. Safonov
 * @version $Id: UIProfileManagerService.java,v 1.1 2007/03/13 13:47:30 safonov Exp $
 */
public class UIProfileManagerService extends ServiceMBeanSupport implements
    UIProfileManagerServiceMBean {
  private UIProfileManager delegate = null;

  protected void createService() throws Exception {
    delegate = new UIProfileManagerServiceImpl();
  }

  protected void destroyService() throws Exception {
    delegate = null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.UIProfileManager#load()
   */
  public UIProfile load(String name) {
    return delegate.load(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.UIProfileManager#store(com.mg.framework.api.ui.UIProfile)
   */
  public void store(UIProfile profile) {
    delegate.store(profile);
  }

}
