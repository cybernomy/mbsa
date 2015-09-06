/*
 * MERPJBossServiceMBean.java
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.ApplicationException;

/**
 * @author Oleg V. Safonov
 * @version $Id: MERPJBossServiceMBean.java,v 1.4 2005/04/05 15:46:29 safonov Exp $
 */
public interface MERPJBossServiceMBean extends org.jboss.system.ServiceMBean {
  /**
   * getMERPLocation
   *
   * @return String
   */
  public String getMERPLocation();

  /**
   * setMERPLocation
   *
   * @param systemLocation String
   */
  public void setMERPLocation(String systemLocation);

  public boolean isUseBiDirGIOP();

  public void setUseBiDirGIOP(boolean useBiDirGIOP);

  public void setBusinessComponent(String component);

  public void convertDataSource() throws ApplicationException;

  public void delpoyLocalFile(String name) throws ApplicationException;

  public void delpoyRemoteFile(String name, byte[] file) throws ApplicationException;
}
