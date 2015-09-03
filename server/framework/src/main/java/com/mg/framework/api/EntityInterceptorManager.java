/*
 * EntityInterceptorManager.java
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
package com.mg.framework.api;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Менеджер перехватчиков действий над объектами сущностями
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManager.java,v 1.4 2007/12/17 09:10:30 safonov Exp $
 */
public interface EntityInterceptorManager {
	
	/**
	 * регистрация перехватчика в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "перед добавлением" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPrePersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "перед добавлением" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPrePersistInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после добавления" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostPersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после добавления" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostPersistInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после добавления и фиксации транзакции" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostCommitPersistInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после добавления и фиксации транзакции" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostCommitPersistInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "перед изменением" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPreUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "перед изменением" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPreUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после изменения" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после изменения" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после изменения и фиксации транзакции" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostCommitUpdateInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после изменения и фиксации транзакции" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostCommitUpdateInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "перед удалением" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPreRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "перед удалением" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPreRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после удаления" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после удаления" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после удаления и фиксации транзакции" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostCommitRemoveInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после удаления и фиксации транзакции" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostCommitRemoveInterceptor(EntityInterceptor interceptor);

	/**
	 * регистрация перехватчика "после загрузки" в менеджере
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerPostLoadInterceptor(EntityInterceptor interceptor);
    
    /**
     * удаление перехватчика "после загрузки" из менеджера
     * 
     * @param interceptor	перехватчик
     */
    void unregisterPostLoadInterceptor(EntityInterceptor interceptor);
    
    /**
     * вызов перехватчика для сущности перед добавлением в хранилище
     * 
     * @param entity	сущность
     */
    void invokeOnPrePersistInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности после добавления в хранилище
     * 
     * @param entity	сущность
     */
    void invokeOnPostPersistInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности после добавления в хранилище и фиксации транзакции
     * 
     * @param entity	сущность
     */
    void invokeOnPostCommitPersistInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности перед удалением из хранилища
     * 
     * @param entity	сущность
     */
    void invokeOnPreRemoveInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности после удаления из хранилища
     * 
     * @param entity	сущность
     */
    void invokeOnPostRemoveInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности после удаления из хранилища и фиксации транзакции
     * 
     * @param entity	сущность
     */
    void invokeOnPostCommitRemoveInterceptor(PersistentObject entity);
    
    /**
     * вызов перехватчика для сущности перед изменением в хранилище
     * 
     * @param entity	сущность
     * @param oldState	предыдущее состояние объекта
     */
    void invokeOnPreUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * вызов перехватчика для сущности после изменения в хранилище
     * 
     * @param entity	сущность
     * @param oldState	предыдущее состояние объекта
     */
    void invokeOnPostUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * вызов перехватчика для сущности после изменения в хранилище и фиксации транзакции
     * 
     * @param entity	сущность
     * @param oldState	предыдущее состояние объекта
     */
    void invokeOnPostCommitUpdateInterceptor(PersistentObject entity, AttributeMap oldState);
    
    /**
     * вызов перехватчика для сущности после загрузки из хранилища
     * 
     * @param entity	сущность
     */
    void invokeOnPostLoadInterceptor(PersistentObject entity);
    
}
