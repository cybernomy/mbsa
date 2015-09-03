/*
 * UserActionInterceptorManager.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.validator.ValidationContext;

/**
 * Менеджер перехватчиков действий интерактивной поддержки (добавление, изменение, копирование, просмотр)
 * сервиса бизнес-компонентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManager.java,v 1.3 2006/10/26 13:14:33 safonov Exp $
 */
public interface UserActionInterceptorManager {
	
	/**
	 * регистрация перехватчика
	 * 
	 * @param interceptor	перехватчик
	 */
    void registerInterceptor(UserActionInterceptor interceptor);
    
    /**
     * удаление перехватчика
     * 
     * @param interceptor	перехватчик
     */
    void unregisterInterceptor(UserActionInterceptor interceptor);
    
    /**
     * вызов перехватчика для сервиса бизнес-компонента перед выводом модели в форму поддержки
     * 
     * @param dialogSession	пользовательская сессия процесса поддержки
     */
    void invokeBeforeOutputInterceptor(MaintenanceConversationSession dialogSession);
    
    /**
     * вызов перехватчика для сервиса бизнес-компонента после сброса значений формы поддержки
     * в модель
     * 
     * @param dialogSession		пользовательская сессия процесса поддержки
     * @param validationContext	контекст контроля данных
     * @param isSaveAction		если <code>true</code> то событие "Сохранить" формы поддержки
     * @param isCloseAction		если <code>true</code> то событие "Закрыть" формы поддержки
     */
    void invokeAfterInputInterceptor(MaintenanceConversationSession dialogSession, ValidationContext validationContext,
    		boolean isSaveAction, boolean isCloseAction);

}
