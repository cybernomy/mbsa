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
package com.mg.merp.security;

import com.mg.merp.security.model.SecUser;

/**
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: UserServiceLocal.java,v 1.2 2007/02/24 14:16:36 safonov Exp $
 */
public interface UserServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<SecUser, Integer>
{

   void addRole(int userId, int roleId);

   void removeRole(int userId, int roleId);

   SecUser findUser(String name);
   
}
