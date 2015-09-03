/*
 * DocTypeAccessServiceLocal.java
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
package com.mg.merp.document;

import java.util.List;

import com.mg.merp.document.model.DocTypeRights;

/**
 * Бизнес-компонент "Права на типы документов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocTypeAccessServiceLocal.java,v 1.2 2007/11/20 14:48:21 sharapov Exp $
 */
public interface DocTypeAccessServiceLocal extends com.mg.framework.api.DataBusinessObjectService<DocTypeRights, Integer> {
	
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp/document/DocTypeAccess"; //$NON-NLS-1$

	/**
	 * Загрузить список прав доступа для типа документа
	 * @param docTypeId - идентификатор типа документа
	 * @return список прав доступа для типа документа
	 */
	List<DocTypePermission> loadDocTypePermissions(Integer docTypeId);
	
	/**
	 * Установить право доступа для типа документа
	 * @param permission - право доступа
	 * @param docTypeId - идентификатор типа документа
	 */
	void grantPermission(DocTypePermission permission, Integer docTypeId);
	
	/**
	 * Отменить право доступа для типа документа
	 * @param permission - право доступа
	 */
	void revokePermission(DocTypePermission permission);

}
