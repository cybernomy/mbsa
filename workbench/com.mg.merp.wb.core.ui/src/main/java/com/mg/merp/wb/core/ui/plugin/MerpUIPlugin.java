/* MerpUIPlugin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.wb.core.ui.plugin;

import java.util.ResourceBundle;

import javax.management.ObjectName;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.support.Messages;
import com.mg.merp.wb.core.support.connector.ServiceConnector;
import com.mg.merp.wb.core.ui.UiPlugin;

/**
 * Базовый класс для плагинов с поддержкой локализованных сообщений
 * и вызовом удалённых методов на сервере приложений.
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: MerpUIPlugin.java,v 1.6 2007/07/11 06:04:42 poroxnenko Exp $
 */
public class MerpUIPlugin extends AbstractUIPlugin {

	protected ResourceBundle resourceBundle;

	/**
	 * Получить локализованную строку
	 * 
	 * @param key
	 * 			ключ
	 * 
	 * @return
	 * 			строка
	 */
	public String getString(String key) {
		return Messages.getString(resourceBundle, key);
	}

	/**
	 * Получить форматированную локализованую строку
	 *
	 * @param key
	 * 			ключ
	 * @param arguments
	 * 			аргументы для вставки
	 * 
	 * @return
	 * 			строка
	 */
	public String getFormattedString(String key, Object... arguments) {
		return Messages.getFormattedString(resourceBundle, key, arguments);
	}

	 /**
     * Вызов удалённого метода на сервере приложений
     * 
     * @param name
     * 			имя сервиса, у которого вызывается метод
     * @param method
     * 			имя вызываемого метода
     * @param args
     * 			значения аргументов, передаваемых в метод
     * @param sig
     * 			сигнатура метода
     * 
     * @return
     * 			результат вызова операции
     * @throws Exception
     */
	public static Object invoke(String name, String method, Object[] args,
			String[] sig) throws Exception {
		return ServiceConnector.getServiceConnector().invoke(
				new ObjectName(name), method, args, sig);
	}

	/**
	 * Проверка соединения с сервером приложений
	 *  
	 * @return
	 */
	public static boolean testConnection() {
		try {
			ServiceConnector.getServiceConnector().invoke(new ObjectName("jboss:service=MERP"), "getMERPLocation", new Object[] {}, new String[] {}); //$NON-NLS-1$;
			return true;
		} catch (Exception ex) {
			CoreUtils.log(UiPlugin.getDefault().getString(
					"preferences.main.testconnection.failed"), ex);
			return false;
		}
	}
}
