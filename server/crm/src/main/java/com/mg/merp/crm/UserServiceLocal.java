/*
 * UserServiceLocal.java
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.User;

/**
 * @author leonova
 * @version $Id: UserServiceLocal.java,v 1.1 2006/03/14 11:49:47 safonov Exp $
 */
public interface UserServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<User, Integer> {

  public com.mg.framework.api.AttributeMap getCurrent() throws com.mg.framework.api.ApplicationException;

  public com.mg.framework.api.AttributeMap getSecurityUser(int key) throws com.mg.framework.api.ApplicationException;

}
