/*
 * Document.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import java.io.Serializable;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.Folder;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.reference.AttachmentHandler;

/**
 * Сервис бизнес-компонента "Документ"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: Document.java,v 1.8 2007/09/28 12:03:43 safonov Exp $
 */
public interface Document<T extends DocHead, ID extends Serializable, M extends DocumentPattern> extends DataBusinessObjectService<T, ID>, AttachmentHandler {

	/**
	 * получить раздел документа
	 * 
	 * @return	раздел документа
	 */
	DocSection getDocSection();

	/**
	 * получить конфигурацию модуля к которому принадлежит документ
	 * 
	 * @return	конфигурация
	 */
	Configuration getConfiguration();

	/**
	 * получить бизнес-компонент образца документа
	 * 
	 * @return
	 */
	M getPatternService();

	/**
	 * создать документ по образцу с использованием стандартной стратегии создания
	 * 
	 * @param patern	образец
	 * @param folder	папка-назначения документа
	 * @return	документ
	 */
	DocHead createByPattern(DocHeadModel patern, Folder folder);

	/**
	 * создать документ по образцу
	 * 
	 * @param patern	образец
	 * @param createStrategy	стратегия создания
	 * @return	документ
	 */
	DocHead createByPatternUseStrategy(DocHeadModel pattern, CreateDocumentByPatternStrategy createStrategy);
	
	/**
	 * расчет атрибутов документа, при использовании штатных методов создания и изменения
	 * вызов данного метода не требуется, если атрибут {@link com.mg.merp.document.model.DocHead#isAdjusted() Adjusted}
	 * установлен в <code>true</code>, то расчет производится не будет, т.о. возможно изменить
	 * стандартное поведение системы
	 * 
	 * @param entity сущность
	 */
	void adjust(T entity);

}
