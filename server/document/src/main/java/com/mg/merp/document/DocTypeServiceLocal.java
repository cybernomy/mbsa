/*
 * DocTypeServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.document;

import com.mg.merp.document.model.DocType;
import com.mg.merp.document.model.DocTypeDocSectionLink;

/**
 * Бизнес-компонент "Тип документа"
 * 
 * @author leonova
 * @version $Id: DocTypeServiceLocal.java,v 1.2 2006/08/31 09:09:30 safonov Exp $
 */
public interface DocTypeServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<DocType, Integer>
{

	/**
	 * загрузка связей типа документа с различными видами документов
	 * 
	 * @see com.mg.merp.document.model.DocumentKind
	 * 
	 * @param docType	тип документа
	 * @return	двумерный массив связей с видами документов, первое измерение - виды документов
	 */
	DocTypeDocSectionLink[][] loadDocSectionLinks(DocType docType);

	/**
	 * удаление связей типа документа с видами документов
	 * 
	 * @param links	связи типа документа
	 */
	void removeDocSectionLinks(DocTypeDocSectionLink[] links);
	
	/**
	 * создание связей типа документа с видами документов
	 * 
	 * @param links	links	связи типа документа
	 */
	void createDocSectionLinks(DocTypeDocSectionLink[] links);

}
