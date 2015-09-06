/*
 * UserActionControllerImpl.java
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
package com.mg.framework.service;

import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;

import java.io.Serializable;

/**
 * @author Oleg V. Safonov
 * @version $Id: UserActionControllerImpl.java,v 1.12 2006/09/28 12:45:55 safonov Exp $
 */
@Deprecated
@SuppressWarnings("unchecked")
public class UserActionControllerImpl implements Serializable {
  private Logger log = ServerUtils.getLogger(getClass());
}
