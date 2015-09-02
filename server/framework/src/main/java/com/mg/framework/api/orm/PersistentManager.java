/*
 * PersistentManager.java
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
package com.mg.framework.api.orm;

import com.mg.framework.api.*;

/**
 * Менеджер перманентного хранилища
 * 
 * @author Oleg V. Safonov
 * @version $Id: PersistentManager.java,v 1.8 2008/12/08 05:59:21 safonov Exp $
 */
public interface PersistentManager {
	
	static final String JNDI_NAME = "java:/com/mg/framework/PersistentManager";
	
	/**
	 * создание экземпляра
	 * 
	 * @param name
	 * @param attributes
	 * @return
	 * 
	 * @deprecated
	 */
	@Deprecated
	PersistentObject make(String name, AttributeMap attributes);
	
	/**
	 * сделать объект перманентным и управляемым
	 * 
	 * @param entity	сущность
	 */
	void persist(PersistentObject entity);
	
	/**
	 * объединить состояние объекта с текущим перманентным контекстом
	 * 
	 * @param <T>		тип сущности
	 * @param entity	сущность
	 * @return			экземпляр сущности состояние которого было объединено
	 */
	<T> T merge(T entity);
	
	/**
	 * сделать объект перманентным и управляемым
	 * 
	 * @param object
	 * @return
	 * 
	 * @deprecated	используйте {@link #persist(PersistentObject)}
	 */
	@Deprecated
	PersistentObject create(PersistentObject object);

	/**
	 * поиск по первичному ключу
	 * 
	 * @param name			название сущности
	 * @param primaryKey	первичный ключ
	 * @return				сущность или <code>null</code> если не найдена
	 */
	PersistentObject find(String name, Object primaryKey);
	
	/**
	 * поиск по первичному ключу
	 * 
	 * @param <T>			тип сущности
	 * @param entityClass	класс сущности
	 * @param primaryKey	первичный ключ
	 * @return				сущность или <code>null</code> если не найдена
	 */
	<T> T find(Class<T> entityClass, Object primaryKey);
	
	/**
	 * получить объект сущность по первичному ключу, состояние сущности может
	 * быть с отложенной загрузкой. Если экземпляр не найден в базе данных, то
	 * будет возбуждена ИС EntityNotFoundException при первом обращении к
	 * состоянию сущности
	 * 
	 * @param <T>			тип сущности
	 * @param entityClass	класс сущности
	 * @param primaryKey	первичный ключ
	 * @return				сущность
	 */
	<T> T getReference(Class<T> entityClass, Object primaryKey);
	
	/**
	 * объединить состояние объекта с текущим перманентным контекстом
	 * 
	 * @param object
	 * @return
	 * 
	 * @deprecated	используйте {@link #merge(Object)}
	 */
	@Deprecated
	PersistentObject store(PersistentObject object);
	
	/**
	 * удалить экземпляр сущности
	 * 
	 * @param name
	 * @param primaryKey
	 * 
	 * @deprecated	используйте {@link #remove(PersistentObject)}
	 */
	@Deprecated
	void remove(String name, Object primaryKey);
	
	/**
	 * удалить экземпляр сущности
	 * 
	 * @param entity	сущность
	 */
	void remove(PersistentObject entity);

	/**
	 * синхронизировать перманентный контекст с системой хранения
	 */
	void flush();

	/**
	 * очистить перманентный контекст, все управляемые сущности становятся отсоединенными от контекста.
	 * Изменения сделанные в сущностях которые не были синхронизированны с системой хранения не будут
	 * сохранены 
	 *
	 */
	void clear();
	
	/**
	 * обновить состояние экземпляра из системы хранения
	 * 
	 * @param entity	сущность
	 */
	void refresh(PersistentObject entity);
	
	/**
	 * проверки принадлежности экземпляра к текущему перманентному контексту
	 * 
	 * @param entity	экземпляр
	 * @return	<code>true</code> если экземпляр принадлежит текущему перманентному контексту
	 */
	boolean contains(Object entity);
	
	/**
	 * создание экземпляра кретериев для сущности
	 * 
	 * @param entityName	имя сущности
	 * @return	критерии
	 */
	Criteria createCriteria(String entityName);
	
	/**
	 * Return the underlying provider object for the EntityManager,
	 * if available. The result of this method is implementation
	 * specific.
	 * 
	 * @return delegate
	 */
	Object getDelegate();
	
}
