/*
 * SpecSource.java
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
package com.mg.merp.lbschedule.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: SpecSource.java,v 1.1 2006/03/30 11:27:50 safonov Exp $
 */
@DataItemName ("LbSchedule.Item.SpecSource")
public enum SpecSource {
	/**
	 * Документ
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#SpecSource.Document")
	DOCUMENT,

	/**
	 * Каталог
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#SpecSource.Catalog")
	CATALOG,
	
	/**
	 * Прайс-лист
	 */
	@EnumConstantText ("resource://com.mg.merp.lbschedule.resources.dataitemlabels#SpecSource.PriceList")
	PRICELIST	

}

  
  