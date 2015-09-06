/*
 * UserGroupServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.security;

import com.mg.merp.security.model.Groups;

/**
 * Бизнес-компонент "Группы пользователей"
 *
 * @author leonova
 * @version $Id: UserGroupServiceLocal.java,v 1.2 2008/01/28 13:11:39 safonov Exp $
 */
public interface UserGroupServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Groups, Integer> {
}
