/*
 * ServicePFCodeServiceLocal.java
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

import com.mg.merp.personnelref.model.ServicePfCode;

/**
 * 
 * @author leonova
 * @version $Id: ServicePFCodeServiceLocal.java,v 1.2 2006/09/06 12:50:50 leonova Exp $
 */
public interface ServicePFCodeServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<ServicePfCode, Integer>
{
	/**
	 * тип папки для видов кодов стажа для ПФ
	 */
	final static short FOLDER_PART = 3001;
}
