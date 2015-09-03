/*
 * CatalogExpDate.java
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
 * Тип расчета даты истечения срока годности
 * 
 * @author leonova
 * @version $Id: CatalogExpDate.java,v 1.1 2006/03/29 13:06:23 safonov Exp $ 
 *
 */
@DataItemName ("Reference.Catalog.ExpDate")
public enum CatalogExpDate {

	/**
	 * Относительно даты изготовления
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#ExpDate.Type.Edckproduction")
	EDCKPRODUCTION,
	
	/**
	 * Относительно даты документа
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#ExpDate.Type.Edckdocument")
	EDCKDOCUMENT,
	
	/**
	 * Относительно даты прихода на склад
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#ExpDate.Type.Edckstrock")
	EDCKSTOCK	
}
