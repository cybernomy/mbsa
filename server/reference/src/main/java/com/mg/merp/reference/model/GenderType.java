/*
 * GenderType.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Пол
 * 
 * @author leonova
 * @version $Id: GenderType.java,v 1.2 2006/05/02 12:31:38 safonov Exp $
 */
@DataItemName ("Reference.Gender")
public enum GenderType {
	/**
	 * мужской
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#Gender.Type.Male")
	MALE,
	
	/**
	 * женский
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#Gender.Type.Female")
	FEMALE
}
