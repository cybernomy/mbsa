/*
 * StaffListServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.StaffList;

/**
 * 
 * @author leonova
 * @version $Id: StaffListServiceLocal.java,v 1.2 2006/09/04 13:02:51 leonova Exp $
 */
public interface StaffListServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<StaffList, Integer>
{
	/**
	 * тип папки для вариантов штатного расписания
	 */
	final static short FOLDER_PART = 3002;
}
