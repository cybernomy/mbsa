/*
 * CrmAddressSource.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: CrmAddressSource.java,v 1.2 2006/10/16 06:50:22 leonova Exp $
 */
@DataItemName("CRM.CrmAddressSource")
public enum CrmAddressSource {
	/**
	 * Физ.лицо
	 */
	@EnumConstantText ("resource://com.mg.merp.crm.resources.dataitemlabels#CrmAddressSource.NaturalPerson")
	NATURALPERSON,
	
	/**
	 * Компания
	 */
	@EnumConstantText ("resource://com.mg.merp.crm.resources.dataitemlabels#CrmAddressSource.Company")
	COMPANY

}
