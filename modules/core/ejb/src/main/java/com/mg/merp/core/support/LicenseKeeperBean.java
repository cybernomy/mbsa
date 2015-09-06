/*
 * LicenseKeeperBean.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.core.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Session;
import com.mg.framework.generic.AbstractStatefulSessionBean;
import com.mg.framework.service.LicenseControllerLocator;
import com.mg.framework.utils.ServerUtils;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

/**
 * @author Oleg V. Safonov
 * @version $Id: LicenseKeeperBean.java,v 1.3 2006/03/01 15:12:21 safonov Exp $
 * @ejb.bean name = "LicenseKeeper" type = "Stateful" view-type = "local" local-jndi-name =
 * "merp/core/LicenseKeeperLocal"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.service.SessionPinger"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @jboss.container-configuration name = "MERP Session"
 */
public class LicenseKeeperBean extends AbstractStatefulSessionBean {
  private boolean licensed = true;
  private Session session;

  public void ping() throws ApplicationException {
    if (!licensed) {
      occupyLicense();
      licensed = true;
    }
  }

  public void setSession(Session session) {
    this.session = session;
  }

  private void occupyLicense() throws ApplicationException {
    session = ServerUtils.getCurrentSession();
    LicenseControllerLocator.locate().occupyLicense(session);
  }

  private void freeLicense() throws ApplicationException {
    if (licensed && (session != null)) {
      LicenseControllerLocator.locate().freeLicense(session);
      licensed = false;
    }
  }

  protected void doPassivate() throws EJBException {
    try {
      freeLicense();
    } catch (ApplicationException e) {
    }
    session = null;
  }

  protected void doDestroy() {
    try {
      freeLicense();
    } catch (Exception e) {
    }
    session = null;
  }

  /*
   * EJB specification
   * @see com.mg.merp.generic.AbstractStatefulSessionBean#ejbCreate()
   */
  public void ejbCreate() throws CreateException {
  }
}
