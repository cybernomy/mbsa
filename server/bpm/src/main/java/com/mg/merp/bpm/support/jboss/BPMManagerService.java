/*
 * BPMManagerService.java
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
package com.mg.merp.bpm.support.jboss;

import com.mg.merp.bpm.support.BPMManagerServiceImpl;

import org.jboss.system.ServiceMBeanSupport;
import org.jbpm.JbpmContext;

/**
 * @author Oleg V. Safonov
 * @version $Id: BPMManagerService.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public class BPMManagerService extends ServiceMBeanSupport implements
    BPMManagerServiceMBean {
  private BPMManagerServiceImpl delegate;

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#createService()
   */
  @Override
  protected void createService() throws Exception {
    delegate = new BPMManagerServiceImpl();
  }

  @Override
  protected void startService() throws Exception {
    delegate.startService();
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
    delegate.stopService();
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#destroyService()
   */
  @Override
  protected void destroyService() throws Exception {
    delegate = null;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.bpm.BPMManager#getCurrentBpmContext()
   */
  public JbpmContext getCurrentBpmContext() {
    return delegate.getCurrentBpmContext();
  }

}
