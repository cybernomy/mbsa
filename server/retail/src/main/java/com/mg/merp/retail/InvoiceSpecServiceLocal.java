/*
 * InvoiceSpecServiceLocal.java
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
package com.mg.merp.retail;

import com.mg.merp.retail.model.RtlInvoiceSpec;

/**
 * Бизнес-компонент "Спецификация документа на отпуск"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: InvoiceSpecServiceLocal.java,v 1.3 2007/10/30 14:54:00 sharapov Exp $
 */
public interface InvoiceSpecServiceLocal extends com.mg.merp.document.GoodsDocumentSpecification<RtlInvoiceSpec, Integer> {
	
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/retail/InvoiceSpec"; //$NON-NLS-1$
	
}
