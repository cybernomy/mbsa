/*
 * OperationModelServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.EconomicOperModel;

/**
 * 
 * @author leonova
 * @version $Id: OperationModelServiceLocal.java,v 1.3 2008/04/29 08:19:34 safonov Exp $
 */
public interface OperationModelServiceLocal	extends com.mg.framework.api.DataBusinessObjectService<EconomicOperModel, Integer> {
	/**
	 * им€ сервиса
	 */
	final static String SERVICE_NAME = "merp/account/OperationModel";
	/**
	 * тип папки дл€ образцов хоз€йственных операций
	 */
	final static short FOLDER_PART = 18;
}
