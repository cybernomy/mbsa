/*
 * TransactTaxServiceLocal.java
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

import com.mg.merp.paymentalloc.model.TransactTax;

/**
 * Сервис бизнес-компонента "Налоги в спецификации связанных документов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TransactTaxServiceLocal.java,v 1.2 2007/05/31 14:08:57 sharapov Exp $
 */
public interface TransactTaxServiceLocal extends com.mg.framework.api.DataBusinessObjectService<TransactTax, Integer> {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/paymentalloc/TransactTax"; //$NON-NLS-1$
	
}
