/*
 * SearchHelp.java
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
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.util.Map;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Интерфейс помощника поиска значений сущностей
 * 
 * @author Oleg V. Safonov
 * @version $Id: SearchHelp.java,v 1.4 2008/05/23 14:18:45 safonov Exp $
 */
public interface SearchHelp extends Serializable {
	
	/**
	 * добавление слушателя на событие поиска
	 * 
	 * @param listener	слушатель
	 */
	void addSearchHelpListener(SearchHelpListener listener);
	
	/**
	 * удаляет слушателя на событие поиска
	 * 
	 * @param listener	слушатель
	 */
	void removeSearchHelpListener(SearchHelpListener listener);
	
	/**
	 * запуск механизма поиска
	 *
	 */
	void search();

	/**
	 * запуск механизма поиска, данный метод можно использовать если реализованы средства
	 * поиска сущностей по указанной, например реализовать форму поиска в которой происходит
	 * позиционирование на строку отображающую переданную сущность
	 * 
	 * @param entity	сущность
	 */
	void search(PersistentObject entity);
	
	/**
	 * признак поддержки интерактивного просмотра сущности
	 * 
	 * @return	<code>true</code> если поддерживается просмотр
	 */
	boolean isSupportView();
	
	/**
	 * запуск механизма просмотра сущности
	 * 
	 * @param entity	сущность
	 */
	void view(PersistentObject entity);
	
	/**
	 * получить описание контекста импорта
	 * 
	 * @return	список наименование атрибутов для импорта из модели
	 * 
	 * @see #setImportContext(Map)
	 */
	String[] getImportContextDefinition();
	
	/**
	 * получить описание контекста экспорта
	 * 
	 * @return	список наименование атрибутов для экспорта в модель
	 * 
	 * @see #getExportContext()
	 */
	String[] getExportContextDefinition();

	/**
	 * устанавливает контекст импорта, значения импортируются из модели контроллера
	 * формы вызвавшей данный поиск
	 * 
	 * @param context	контекст
	 */
	void setImportContext(Map<String, Object> context);
	
	/**
	 * устанавливает контекст экспорта, значения экспортируются в модель контроллера
	 * формы вызвавшей данный поиск
	 * 
	 * @return
	 */
	Map<String, Object> getExportContext();
}
