/*
 * DocumentSpecPackageServiceLocal.java
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
package com.mg.merp.document;

import com.mg.merp.document.model.DocumentSpecPackage;

/**
 * Сервис бизнес-компонента "Упаковки товара позиции спецификации документа"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocumentSpecPackageServiceLocal.java,v 1.3 2007/08/10 13:29:19 safonov Exp $
 */
public interface DocumentSpecPackageServiceLocal extends com.mg.framework.api.DataBusinessObjectService<DocumentSpecPackage, Integer> {

	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/document/DocumentSpecPackage";
	
	/**
	 * Рассчитать вес и объем упаковки
	 * @param documentSpecPackage - упаковка товара позиции спецификации документа
	 */
	void computeWeightAndVolume(DocumentSpecPackage documentSpecPackage);
	
}
