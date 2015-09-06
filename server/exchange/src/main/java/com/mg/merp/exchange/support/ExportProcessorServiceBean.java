/*
 * Created on 20.01.2005
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 */
package com.mg.merp.exchange.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.exchange.ExportProcessorServiceLocal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: ExportProcessorServiceBean.java,v 1.3 2006/09/21 12:20:57 safonov Exp $
 */
@Stateless(name = "merp/exchange/ExportProcessorService")
public class ExportProcessorServiceBean extends
    com.mg.framework.generic.AbstractPOJOBusinessObjectStatefulServiceBean implements ExportProcessorServiceLocal {

  /**
   * @ejb.interface-method view-type = "local"
   */
  public void doExport(String siteCode) throws ApplicationException {
    //TODO
    //((ExportProcessorDomainImpl) getDomain()).doExport(siteCode);
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public void enqueue(String siteCode, String beanName, Object[] keys) throws ApplicationException {
    //TODO
    //((ExportProcessorDomainImpl) getDomain()).enqueue(siteCode, beanName, keys);
  }
}
