/*
 * UserActionInterceptor.java
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
 * Перехватчик действий интерактивной поддержки (добавление, изменение, копирование, просмотр)
 * сервиса бизнес-компонентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptor.java,v 1.4 2007/02/24 13:39:11 safonov Exp $
 */
public interface UserActionInterceptor {

	/**
	 * получить имя перехватчика
	 * 
	 * @return	имя
	 */
	String getName();

	/**
	 * получить список обрабатываемых сервисов бизнес-компонентов
	 * 
	 * @return	список обрабатываемых сервисов бизнес-компонентов
	 */
	String[] getHandledServices();

	/**
	 * событие "После ввода", происходит после сброса значений формы поддержки
     * в модель
	 * 
	 * @param dialogSession		пользовательская сессия процесса поддержки
	 * @param validationContext	контекст контроля данных
	 * @param isSaveAction		если <code>true</code> то событие "Сохранить" формы поддержки
	 * @param isCloseAction		если <code>true</code> то событие "Закрыть" формы поддержки
	 */
	void afterInput(MaintenanceConversationSession dialogSession, ValidationContext validationContext, boolean isSaveAction, boolean isCloseAction);

	/**
	 * событие "Перед выводом", происходит перед выводом модели в форму поддержки
	 * 
	 * @param dialogSession		пользовательская сессия процесса поддержки
	 */
	void beforeOutput(MaintenanceConversationSession dialogSession);

}
