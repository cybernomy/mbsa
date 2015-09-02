/*
 * DocumentProcessorServiceLocal.java
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
package com.mg.merp.document;

import com.mg.framework.api.BusinessObjectService;

/**
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: DocumentProcessorServiceLocal.java,v 1.2 2007/06/14 10:58:02 poroxnenko Exp $
 */
public interface DocumentProcessorServiceLocal extends BusinessObjectService, DocumentProcessor{

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/document/DocumentProcessor";

}
