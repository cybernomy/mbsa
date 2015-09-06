/*
 * WarehouseSecurityServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse;

/**
 * @author leonova
 * @version $Id: WarehouseSecurityServiceLocal.java,v 1.1 2006/03/14 11:49:53 safonov Exp $
 */
public interface WarehouseSecurityServiceLocal
    extends com.mg.framework.api.BusinessObjectService {

  public com.mg.framework.api.AttributeMap load(int groupId) throws com.mg.framework.api.ApplicationException;

  public com.mg.framework.api.AttributeMap loadCurrent() throws com.mg.framework.api.ApplicationException;

  public void store(int groupId, com.mg.framework.api.AttributeMap item) throws com.mg.framework.api.ApplicationException;

}
