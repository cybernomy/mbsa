/*
 * OriginalDocumentServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.OriginalDocument;

/**
 * Сервис бизнес-компонента "Оригиналы документов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentServiceLocal.java,v 1.5 2008/05/19 14:39:38 safonov Exp $
 */
public interface OriginalDocumentServiceLocal extends com.mg.framework.api.DataBusinessObjectService<OriginalDocument, Integer>, AttachmentHandler {

	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/reference/OriginalDocument"; //$NON-NLS-1$
	
	/**
	 * тип папки для Оригиналов документов
	 */
	final static short FOLDER_PART = 2501;

}
