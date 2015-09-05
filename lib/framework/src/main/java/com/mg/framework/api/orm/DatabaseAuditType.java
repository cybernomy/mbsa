/**
 * DatabaseAuditType.java
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
package com.mg.framework.api.orm;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditType.java,v 1.1 2007/10/19 06:33:10 safonov Exp $
 */
@DataItemName("Core.DatabaseAuditType")
public enum DatabaseAuditType {
	/**
	 * создание
	 */
	@EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#DatabaseAuditType.Create")
	CREATE,
	/**
	 * изменение
	 */
	@EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#DatabaseAuditType.Modify")
	MODIFY,
	/**
	 * удаление
	 */
	@EnumConstantText("resource://com.mg.merp.core.resources.dataitemlabels#DatabaseAuditType.Remove")
	REMOVE
}
