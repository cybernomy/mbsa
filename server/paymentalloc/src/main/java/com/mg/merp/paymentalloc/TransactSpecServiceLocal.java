/*
 * TransactSpecServiceLocal.java
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
package com.mg.merp.paymentalloc;

import com.mg.merp.paymentalloc.model.TransactSpec;

/**
 * Сервис бизнес-компонента "Спецификации связанных документов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TransactSpecServiceLocal.java,v 1.2 2007/05/31 14:08:57 sharapov Exp $
 */
public interface TransactSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TransactSpec, Integer> {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentalloc/TransactSpec"; //$NON-NLS-1$
	
}
