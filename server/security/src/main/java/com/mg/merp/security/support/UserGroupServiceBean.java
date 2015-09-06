/*
 * UserGroupServiceBean.java
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
package com.mg.merp.security.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.security.UserGroupServiceLocal;
import com.mg.merp.security.model.Groups;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Группы пользователей"
 *
 * @author leonova
 * @version $Id: UserGroupServiceBean.java,v 1.4 2008/01/28 13:11:39 safonov Exp $
 */
@Stateless(name = "merp/security/UserGroupService")
public class UserGroupServiceBean
    extends AbstractPOJODataBusinessObjectServiceBean<Groups, Integer> implements UserGroupServiceLocal {

}
