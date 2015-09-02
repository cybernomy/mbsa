/**
 * SystemAuditServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.core;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.SysAudit;

/**
 * ������-��������� ������ �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemAuditServiceLocal.java,v 1.1 2007/10/19 06:40:17 safonov Exp $
 */
public interface SystemAuditServiceLocal extends DataBusinessObjectService<SysAudit, Long> {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/core/SystemAudit";
}
