/*
 * AuditLogServiceBean.java
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

import com.mg.merp.core.model.SysAudit;
import com.mg.merp.security.AuditLogServiceLocal;

import java.math.BigDecimal;

import javax.ejb.Stateless;

/**
 * @author Oleg V. Safonov
 * @version $Id: AuditLogServiceBean.java,v 1.3 2007/02/24 14:20:52 safonov Exp $
 */
@Stateless(name = "merp/security/AuditLogService")
public class AuditLogServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<SysAudit, BigDecimal> implements AuditLogServiceLocal {

}
