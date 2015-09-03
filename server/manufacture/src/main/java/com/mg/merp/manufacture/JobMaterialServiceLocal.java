/*
 * JobMaterialServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.JobMaterial;

/**
 * Бизнес-компонент "Материалы ЗНП"
 * 
 * @author Oleg V. Safonov
 * @version $Id: JobMaterialServiceLocal.java,v 1.3 2007/07/30 10:28:17 safonov Exp $
 */
public interface JobMaterialServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<JobMaterial, Integer>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/JobMaterial";
}
