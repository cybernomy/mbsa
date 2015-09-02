/*
 * BomStatus.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Статус Состава изделия
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: BomStatus.java,v 1.1 2006/04/13 10:20:42 safonov Exp $
 */
@DataItemName ("MfReference.BomStatus")
public enum BomStatus {
	/**
	 * Создаётся
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#BomStatus.IsCreating")
	ISCREATING,
	
	/**
	 * Создан
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#BomStatus.Done")
	DONE
}
