/**
 * DataBusinessServiceInterceptorManager.java
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
package com.mg.framework.api;

import java.io.Serializable;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;

/**
 * Менеджер перехватчиков действий бизнес-компонентов управляющих данными. 
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManager.java,v 1.1 2007/12/13 13:01:32 safonov Exp $
 */
public interface DataBusinessServiceInterceptorManager extends Serializable {

	/**
	 * зарегистрировать перехватчик в менеджере
	 * 
	 * @param interceptor
	 */
	void registerInterceptor(DataBusinessServiceInterceptor interceptor);
	
	/**
	 * удалить перехватчик из менеджера
	 * 
	 * @param interceptor
	 */
	void unregisterInterceptor(DataBusinessServiceInterceptor interceptor);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnClone(final DataBusinessObjectService<T, ID> service, final T entity);

	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnErase(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnInitialize(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnPostCreate(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnStore(final DataBusinessObjectService<T, ID> service, final T entity);
	
	/**
	 * вызов всех перехватчиков для бизнес-компонента на событие "копирование", стандартное действие не будет
	 * выполнено если хотя бы один перехватчик потребует отмены стандартного действия
	 * 
	 * @param service	бизнес-компонент
	 * @param context	контекст контроля данных
	 * @param entity	сущность
	 * @return	если <code>true</code> то стандартное действие бизнес-компонента не будет выполнено
	 */
	<T extends PersistentObject, ID extends Serializable> boolean invokeOnValidate(final DataBusinessObjectService<T, ID> service, final ValidationContext context, final T entity);
	
}
