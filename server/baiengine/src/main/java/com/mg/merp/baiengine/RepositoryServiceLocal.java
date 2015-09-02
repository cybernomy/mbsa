/* RepositoryServiceLocal.java
*
* Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.merp.baiengine.model.Repository;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: RepositoryServiceLocal.java,v 1.1 2006/12/29 14:46:33 poroxnenko Exp $ 
 */
public interface RepositoryServiceLocal extends
		com.mg.framework.api.DataBusinessObjectService<Repository, Integer> {
	/**
	 * Локальное имя сервиса
	 */
	static final String SERVICE_NAME = "merp/algorithmengine/AlgorithmRepository";

}
