/*
 * EntityInterceptor.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Перехватчик действий над объектами сущностями, реализации данного интерфейса используются
 * для обработки событий происходящих с объектами сущностями (создание, модификация, удаление, загрузка).
 * Реализации методов перехватчика могут генерировать неконтролируемые исключительные ситуации,
 * вызывать методы объектов JNDI, JDBC, JMS, EJB.
 * <strong>Внимание, в реализациях методов данного интерфейса запрещено выполнять операции с UI, выполнять объектные запросы,
 * вызывать метододы менеджера перманентного хранилища или модифицировать отношения объектов-сущностей</strong>
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptor.java,v 1.6 2007/12/17 09:10:30 safonov Exp $
 */
public interface EntityInterceptor {
	
	/**
	 * используется для указания обработки всех сущностей перехватчиком
	 */
	final static String HANDLED_ALL_ENTITIES = "*";
	
	/**
	 * получить имя перехватчика
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * возвращает список имен обрабатываемых сущностей
	 * 
	 * @return
	 */
	String[] getHandledEntities();
	
    /**
     * действие над сущностью перед добавлением в хранилище
     * 
     * @param entity	объект-сущность
     */
    void onPrePersist(PersistentObject entity);

    /**
     * действие над сущностью после добавления в хранилище
     * 
     * @param entity	объект-сущность
     */
    void onPostPersist(PersistentObject entity);

    /**
     * действие над сущностью после добавления в хранилище и фиксации транзакции
     * 
     * @param entity	объект-сущность
     */
    void onPostCommitPersist(PersistentObject entity);
    
    /**
     * действие над сущностью перед удалением из хранилища
     * 
     * @param entity	объект-сущность
     */
    void onPreRemove(PersistentObject entity);

    /**
     * действие над сущностью после удаления из хранилища
     * 
     * @param entity	объект-сущность
     */
    void onPostRemove(PersistentObject entity);
    
    /**
     * действие над сущностью после удаления из хранилища и фиксации транзакции
     * 
     * @param entity	объект-сущность
     */
    void onPostCommitRemove(PersistentObject entity);

    /**
     * действие над сущностью перед изменением в хранилище
     * 
     * @param entity	объект-сущность
     * @param oldState	предыдущее состояние объекта
     */
    void onPreUpdate(PersistentObject entity, AttributeMap oldState);
    
    /**
     * действие над сущностью после изменения в хранилище
     * 
     * @param entity	объект-сущность
     * @param oldState	предыдущее состояние объекта
     */
    void onPostUpdate(PersistentObject entity, AttributeMap oldState);
    
    /**
     * действие над сущностью после изменения в хранилище и фиксации транзакции
     * 
     * @param entity	объект-сущность
     * @param oldState	предыдущее состояние объекта
     */
    void onPostCommitUpdate(PersistentObject entity, AttributeMap oldState);

    /**
     * действие над сущностью после загрузки из хранилища
     * 
     * @param entity	объект-сущность
     */
    void onPostLoad(PersistentObject entity);
}
