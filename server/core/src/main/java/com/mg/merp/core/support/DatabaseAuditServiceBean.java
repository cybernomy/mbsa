/**
 * DatabaseAuditServiceBean.java
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
import com.mg.merp.core.DatabaseAuditServiceLocal;
import com.mg.merp.core.model.DatabaseAudit;

/**
 * Реализация бизнес-компонента аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditServiceBean.java,v 1.1 2007/10/19 06:45:53 safonov Exp $
 */
@Stateless(name="merp/core/DatabaseAuditService")
public class DatabaseAuditServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<DatabaseAudit, Long> implements
		DatabaseAuditServiceLocal {

}
