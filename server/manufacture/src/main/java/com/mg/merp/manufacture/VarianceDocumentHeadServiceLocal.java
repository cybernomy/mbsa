/*
 * VarianceDocumentHeadServiceLocal.java
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
package com.mg.merp.manufacture;

import com.mg.merp.manufacture.model.VarianceDocumentHead;

/**
 * Бизнес-компонент "Документ на отклонения"
 * 
 * @author Oleg V. Safonov
 * @version $Id: VarianceDocumentHeadServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface VarianceDocumentHeadServiceLocal
   extends com.mg.merp.document.GoodsDocument<VarianceDocumentHead, Integer, VarianceDocumentModelServiceLocal, VarianceDocumentSpecServiceLocal>
{
	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/VarianceDocumentHead";

	/**
	 * тип папки для Документов по отклонениям
	 */
	final static short FOLDER_PART = 12518;
	
	/**
	 * docsection для Документов по отклонениям
	 */
	final static short DOCSECTION = 12009;
}
