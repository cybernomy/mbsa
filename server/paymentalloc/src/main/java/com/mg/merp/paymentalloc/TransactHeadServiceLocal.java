/*
 * TransactHeadServiceLocal.java
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

import com.mg.merp.paymentalloc.model.TransactHead;

/**
 * Сервис бизнес-компонент "Связанные документы" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TransactHeadServiceLocal.java,v 1.2 2007/05/31 14:08:57 sharapov Exp $
 */
public interface TransactHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TransactHead, Integer> {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentalloc/TransactHead"; //$NON-NLS-1$
	
}
