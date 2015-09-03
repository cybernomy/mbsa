/*
 * DocumentSpecSerialNumServiceLocal.java
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

import com.mg.merp.document.model.DocumentSpecSerialNum;

/**
 * Бизнес-компонент "Серийные номера спецификаций"
 * 
 * @author leonova
 * @version $Id: DocumentSpecSerialNumServiceLocal.java,v 1.2 2007/08/10 13:29:19 safonov Exp $
 */
public interface DocumentSpecSerialNumServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<DocumentSpecSerialNum, Integer> {
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/document/DocumentSpecSerialNum";

}
