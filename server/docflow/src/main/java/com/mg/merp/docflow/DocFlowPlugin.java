/*
 * DocFlowPlugin.java
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
package com.mg.merp.docflow;

import com.mg.merp.docprocess.model.DocHeadState;


/**
 * Подключаемый модуль машины документооборота для выполнения этапов
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPlugin.java,v 1.2 2006/10/21 10:48:22 safonov Exp $
 */
public interface DocFlowPlugin {
	
	/**
	 * зарегистрировать слушателя на события подключаемого модуля
	 * 
	 * @param listener	слушатель
	 */
	void registerListener(DocFlowPluginListener listener);
	
	/**
	 * удалить слушателя на события подключаемого модуля
	 * 
	 * @param listener	слушатель
	 */
	void unregisterListener(DocFlowPluginListener listener);
	
	/**
	 * выполнить этап ДО
	 * 
	 * @param params		параметры ДО
	 * @throws Exception	при любых ИС
	 */
	void execute(DocFlowPluginInvokeParams params) throws Exception;
	
	/**
	 * выполнить откат этап ДО
	 * 
	 * @param params		параметры ДО
	 * @throws Exception	при любых ИС
	 */
	void rollback(DocFlowPluginInvokeParams params) throws Exception;
	
	/**
	 * получить текстовое представление о результате выполнения этапа ДО
	 * 
	 * @param docHeadState		состояние этапа ДО
	 * @return	текстовое	представление
	 */
	String getDocActionResultTextRepresentation(DocHeadState docHeadState);
	
	/**
	 * показать в пользовательском интерфейсе результат выполненного этапа ДО.
	 * <strong>Вызов данного метода возможен только если текущий пользователь
	 * является интерактивным.</strong>
	 * 
	 * @param docHeadState		состояние этапа ДО
	 */
	void showDocActionResult(DocHeadState docHeadState);
	
}
