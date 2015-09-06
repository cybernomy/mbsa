/*
 * WarehouseSecurityServiceBean.java
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

package com.mg.merp.warehouse.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.merp.warehouse.WarehouseSecurityServiceLocal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: WarehouseSecurityServiceBean.java,v 1.5 2006/09/20 12:31:27 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseSecurityService")
public class WarehouseSecurityServiceBean extends com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean implements WarehouseSecurityServiceLocal {

  /**
   * @ejb.interface-method view-type = "local"
   */
  public AttributeMap load(int groupId) throws ApplicationException {
    //TODO
    //return ((WarehouseSecurityDomainImpl) getDomain()).load(groupId);
    return null;
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public AttributeMap loadCurrent() throws ApplicationException {
    //TODO
    //return ((WarehouseSecurityDomainImpl) getDomain()).loadCurrent();
    return null;
  }

  /**
   * @ejb.interface-method view-type = "local"
   */
  public void store(int groupId, AttributeMap item) throws ApplicationException {
    //TODO
    //((WarehouseSecurityDomainImpl) getDomain()).store(groupId, item);
  }
}
