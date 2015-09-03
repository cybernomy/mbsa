/*
 * PeriodicNormKind.java
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
package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Периодичность выдачи
 * 
 * @author leonova
 * @version $Id: PeriodicNormKind.java,v 1.2 2008/06/30 04:15:16 alikaev Exp $
 */
@DataItemName ("Overall.Spec.PeriodicNormKind")
public enum PeriodicNormKind {
	
	/**
	 * периодическая
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#PeriodicNormKind.Periodic")
	PERIODIC,
	
	/**
	 * разовая
	 */
	@EnumConstantText ("resource://com.mg.merp.overall.resources.dataitemlabels#PeriodicNormKind.Single")
	SINGLE
	
}
