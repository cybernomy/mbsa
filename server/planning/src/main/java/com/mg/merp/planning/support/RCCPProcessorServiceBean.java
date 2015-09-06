/*
 * RCCPProcessorServiceBean.java
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
 *
 */

package com.mg.merp.planning.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.planning.RCCPProcessorServiceLocal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: RCCPProcessorServiceBean.java,v 1.4 2006/09/21 11:21:52 safonov Exp $
 */
@Stateless(name = "merp/planning/RCCPProcessorService")
public class RCCPProcessorServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements RCCPProcessorServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public void generateRccp(int rccpVersionId) throws ApplicationException {
    //TODO
    //((RCCPProcessorDomainImpl) getDomain()).generateRccp(rccpVersionId);
  }
}