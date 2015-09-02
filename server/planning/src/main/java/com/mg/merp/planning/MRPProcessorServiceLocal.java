/*
 * MRPProcessorServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.planning;

/**
 * Бизнес-компонент "Процессор MRP"
 * 
 * @author leonova
 * @version $Id: MRPProcessorServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface MRPProcessorServiceLocal extends com.mg.framework.api.BusinessObjectService
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/planning/MRPProcessor";
	
	/**
	 * генерация ППМ
	 * 
	 * @param mrpVersionId	версия ППМ
	 */
	void generateMrp(int mrpVersionId);

}
