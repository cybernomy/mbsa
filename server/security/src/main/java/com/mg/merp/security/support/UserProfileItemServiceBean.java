/*
 * UserProfileItemServiceBean.java
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

package com.mg.merp.security.support;

import com.mg.merp.security.UserProfileItemServiceLocal;
import com.mg.merp.security.model.SecUserProfileItem;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserProfileItemServiceBean.java,v 1.3 2007/02/24 14:20:52 safonov Exp $
 */
@Stateless(name = "merp/security/UserProfileItemService")
public class UserProfileItemServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<SecUserProfileItem, Integer> implements UserProfileItemServiceLocal {

}
