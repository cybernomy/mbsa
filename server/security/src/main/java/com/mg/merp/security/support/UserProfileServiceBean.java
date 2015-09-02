/*
 * UserProfileServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.merp.security.UserProfileServiceLocal;
import com.mg.merp.security.model.SecUserProfile;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserProfileServiceBean.java,v 1.3 2007/02/24 14:20:52 safonov Exp $
 */
@Stateless(name="merp/security/UserProfileService")
public class UserProfileServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<SecUserProfile, Integer> implements UserProfileServiceLocal {

}
