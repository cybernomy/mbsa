/**
 * SystemAuditServiceBean.java
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
package com.mg.merp.core.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.core.SystemAuditServiceLocal;
import com.mg.merp.core.model.SysAudit;

/**
 * Реализация бизнес-компонента аудита системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: SystemAuditServiceBean.java,v 1.1 2007/10/19 06:45:53 safonov Exp $
 */
@Stateless(name="merp/core/SystemAuditService")
public class SystemAuditServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<SysAudit, Long> implements
		SystemAuditServiceLocal {

}
