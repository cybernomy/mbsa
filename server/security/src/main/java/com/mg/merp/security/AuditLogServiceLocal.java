/*
 * AuditLogServiceLocal.java
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

import java.math.BigDecimal;

import com.mg.merp.core.model.SysAudit;

/**
 * 
 * @author leonova
 * @version $Id: AuditLogServiceLocal.java,v 1.1 2006/03/14 11:49:52 safonov Exp $
 */
public interface AuditLogServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<SysAudit, BigDecimal>
{

}
