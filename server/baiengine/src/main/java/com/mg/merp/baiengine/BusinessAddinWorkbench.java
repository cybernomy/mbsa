/* BusinessAddinWorkbench.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine;

import com.mg.framework.api.orm.PersistentObject;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessAddinWorkbench.java,v 1.1 2006/12/29 14:46:33
 *          poroxnenko Exp $
 */
public interface BusinessAddinWorkbench {
	String SERVICE_NAME = "merp:baiengine=BusinessAddinWorkbenchService";

	//Используем PersistentObject вместо Repository, т.к. сервис живёт 
	//в mbaiengine.sar и ему не доступны модели из datawarehouse
	PersistentObject[] getBais(String query) throws Exception;

	PersistentObject editBai(PersistentObject repository) throws Exception;

	PersistentObject addBai(PersistentObject repository) throws Exception;

	void deleteBaiList(Integer[] ids) throws Exception;
}
