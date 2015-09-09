/*
 * SysMethodServiceBean.java
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

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.core.model.SysMethod;
import com.mg.merp.security.SysMethodServiceLocal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: SysMethodServiceBean.java,v 1.4 2007/02/24 14:20:52 safonov Exp $
 */
@Stateless(name = "merp/security/SysMethodService")
public class SysMethodServiceBean
    extends AbstractPOJODataBusinessObjectServiceBean<SysMethod, Integer> implements SysMethodServiceLocal {

}
