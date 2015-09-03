/*
 * ModifyDocumentServiceLocal.java
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
package com.mg.merp.contract;

import com.mg.merp.contract.model.ModifyDocument;
import com.mg.merp.reference.AttachmentHandler;

/**
 * Сервис бизнес-компонента "Изменения контракта"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ModifyDocumentServiceLocal.java,v 1.2 2007/03/29 09:47:05 sharapov Exp $
 */
public interface ModifyDocumentServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ModifyDocument, Integer>, AttachmentHandler {

	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/contract/ModifyDocument"; //$NON-NLS-1$

}
