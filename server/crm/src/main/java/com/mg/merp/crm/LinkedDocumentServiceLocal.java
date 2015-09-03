/*
 * LinkedDocumentServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.LinkedDocument;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.reference.model.OriginalDocument;

/**
 * Бизнес-компонент "Связанные документы" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LinkedDocumentServiceLocal.java,v 1.2 2008/06/17 11:31:40 sharapov Exp $
 */
public interface LinkedDocumentServiceLocal extends com.mg.framework.api.DataBusinessObjectService<LinkedDocument, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/crm/LinkedDocument";  //$NON-NLS-1$
	
	/**
	 * Создать связанный документ с оригиналом для действия
	 * @param operation - действие
	 * @param originalDocument - оригинал
	 * @return связанный документ
	 */
	LinkedDocument createForOperationWithOriginal(Operation operation, OriginalDocument originalDocument);

}
