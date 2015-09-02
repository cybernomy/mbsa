/*
 * MaintenanceBrowseForm.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;

/**
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceBrowseForm.java,v 1.3 2006/04/24 08:41:33 safonov Exp $
 */
public interface MaintenanceBrowseForm extends Form {
	//public void setService(String serviceName) throws NamingException, ApplicationException;
	public void setService(DataBusinessObjectService<PersistentObject, Serializable> service);
}
