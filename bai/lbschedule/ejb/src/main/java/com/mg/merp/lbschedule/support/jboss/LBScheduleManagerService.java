/*
 * LBScheduleManagerService.java
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
package com.mg.merp.lbschedule.support.jboss;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.LBScheduleManager;
import com.mg.merp.document.model.DocHead;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса менеджера управления графиками исполнения обязательств
 *
 * @author Artem V. Sharapov
 * @version $Id: LBScheduleManagerService.java,v 1.1 2007/04/21 11:49:33 sharapov Exp $
 */
@Service(objectName = LBScheduleManagerServiceMBean.SERVICE_NAME, name = "merp/lbschedule/LBScheduleManagerService")
@Management(LBScheduleManagerServiceMBean.class)
public class LBScheduleManagerService extends ServiceMBeanSupport implements
    LBScheduleManagerServiceMBean {

  private LBScheduleManager delegate = null;

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#createService()
   */
  @Override
  protected void createService() throws Exception {
    delegate = (LBScheduleManager) ServerUtils.loadClass("com.mg.merp.lbschedule.support.LBScheduleManagerImpl").newInstance();
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#destroyService()
   */
  @Override
  protected void destroyService() throws Exception {
    delegate = null;
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#startService()
   */
  @Override
  protected void startService() throws Exception {
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.LBScheduleManager#createLBSchedule(com.mg.merp.document.model.DocHead)
   */
  public void createLBSchedule(DocHead docHead) {
    delegate.createLBSchedule(docHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.LBScheduleManager#openLBSchedule(com.mg.merp.document.model.DocHead)
   */
  public void openLBSchedule(DocHead docHead) {
    delegate.openLBSchedule(docHead);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.LBScheduleManager#removeLBSchedule(com.mg.merp.document.model.DocHead)
   */
  public void removeLBSchedule(DocHead docHead) {
    delegate.removeLBSchedule(docHead);
  }

}
