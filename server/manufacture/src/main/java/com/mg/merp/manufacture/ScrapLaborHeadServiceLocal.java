/*
 * ScrapLaborHeadServiceLocal.java
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

import com.mg.merp.manufacture.model.ScrapDocumentHead;

/**
 * Бизнес-компонент "Акт на списание потерь времени, отработанного РС"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ScrapLaborHeadServiceLocal.java,v 1.5 2007/08/06 12:46:24 safonov Exp $
 */
public interface ScrapLaborHeadServiceLocal
   extends com.mg.merp.document.GoodsDocument<ScrapDocumentHead, Integer, ScrapLaborModelServiceLocal, ScrapLaborSpecServiceLocal>
{
	/**
	 * имя сервиса
	 */
	static final String SERVICE_NAME = "merp/manufacture/ScrapLaborHead";
	
	/**
	 * тип папки для актов на списание потерь времени, отработанного РС	 */
	final static short FOLDER_PART = 12508;
	
	/**
	 * docsection для актов на списание потерь времени, отработанного РС
	 */
	final static short DOCSECTION = 12007;
}
