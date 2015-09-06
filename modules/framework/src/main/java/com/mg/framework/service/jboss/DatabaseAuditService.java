/**
 * DatabaseAuditService.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.service.jboss;

import com.mg.framework.service.DatabaseAuditServiceImpl;

import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.jboss.system.ServiceMBeanSupport;

/**
 * Реализация сервиса аудита хранилища данных
 *
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditService.java,v 1.2 2007/11/27 14:48:36 safonov Exp $
 */
public class DatabaseAuditService extends ServiceMBeanSupport implements
    DatabaseAuditServiceMBean {
  private DatabaseAuditServiceImpl delegate;
  private boolean isAuditActivated = true;
  private String jmsDestinationName = null;

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#createService()
   */
  @Override
  protected void createService() throws Exception {
    delegate = new DatabaseAuditServiceImpl(isAuditActivated);
    delegate.setJmsDestinationName(jmsDestinationName);
  }

  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#startService()
   */
  @Override
  protected void startService() throws Exception {
    delegate.loadAuditSetup();
  }


  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#stopService()
   */
  @Override
  protected void stopService() throws Exception {
    delegate.stop();
  }


  /* (non-Javadoc)
   * @see org.jboss.system.ServiceMBeanSupport#destroyService()
   */
  @Override
  protected void destroyService() throws Exception {
    delegate = null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#auditCreate(org.hibernate.event.PostInsertEvent)
   */
  public void auditCreate(PostInsertEvent createEvent) {
    delegate.auditCreate(createEvent);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#auditModify(org.hibernate.event.PostUpdateEvent)
   */
  public void auditModify(PostUpdateEvent modifyEvent) {
    delegate.auditModify(modifyEvent);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#auditRemove(org.hibernate.event.PostDeleteEvent)
   */
  public void auditRemove(PostDeleteEvent removeEvent) {
    delegate.auditRemove(removeEvent);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#applyAuditSetup()
   */
  public void applyAuditSetup() {
    delegate.loadAuditSetup();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#isAuditActivated()
   */
  public Boolean isAuditActivated() {
    return isAuditActivated;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#setAuditActivated(java.lang.Boolean)
   */
  public void setAuditActivated(Boolean value) {
    isAuditActivated = value == null ? true : value;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#getJmsDestinationName()
   */
  public String getJmsDestinationName() {
    return delegate.getJmsDestinationName();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.jboss.DatabaseAuditServiceMBean#setJmsDestinationName(java.lang.String)
   */
  public void setJmsDestinationName(String name) {
    jmsDestinationName = name;
  }

}
