/* CoreUtils.java
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
package com.mg.merp.wb.core.support;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.mg.merp.wb.core.CorePlugin;

/**
 * Набор утилит 
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CoreUtils.java,v 1.6 2007/07/10 10:26:07 poroxnenko Exp $ 
 */
public class CoreUtils {

	/**
	 * Сохранение строкового массива в конфигурационном файле плагина. Работает только у графических плагинов
	 * 
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ сохраняемого параметра
	 * @param arr
	 * 			сохраняемый массив
	 */
	public static final void storeStringArray(AbstractUIPlugin plugin, String key, String[] arr){
		IDialogSettings settings = plugin.getDialogSettings();
		settings.put(key, arr);
	}
	
	/**
	 * Загрузка строкового массива из конфигурационного файла плагина. Работает только у графических плагинов
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ загружаемого параметра
	 * @return
	 * 			массив строк
	 */
	public static final String[] loadStringArray(AbstractUIPlugin plugin, String key){
		IDialogSettings settings = plugin.getDialogSettings();
		return settings.getArray(key);
	}
	
	/**
	 * Сохранение строки в конфигурационном файле плагина
	 * 
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ сохраняемого параметра
	 * @param str
	 * 			сохраняемая строка
	 */
	public static final void storeString(Plugin plugin, String key, String str){
		Preferences prefs = plugin.getPluginPreferences();
		prefs.setValue(key, str);
		plugin.savePluginPreferences();
	}
	
	/**
	 * Загрузка строкового параметра из конфигурационного файла плагина
	 * 
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ загружаемого параметра
	 * 
	 * @return
	 * 			строка
	 */
	public static final String loadString(Plugin plugin, String key){
		Preferences prefs = plugin.getPluginPreferences();
		return prefs.getString(key);
	}
	
	/**
	 * Сохранение целого в конфигурационном файле плагина
	 * 
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ сохраняемого параметра
	 * @param value
	 * 			сохраняемое целое число
	 */
	public static final void storeInt(Plugin plugin, String key, Integer value){
		Preferences prefs = plugin.getPluginPreferences();
		prefs.setValue(key, value);
		plugin.savePluginPreferences();
	}
	
	/**
	 * Загрузка целочисленного параметра из конфигурационного файла плагина
	 * 
	 * @param plugin
	 * 			плагин
	 * @param key
	 * 			ключ загружаемого параметра
	 * 
	 * @return
	 * 			целое число
	 */
	public static final Integer loadInt(Plugin plugin, String key){
		Preferences prefs = plugin.getPluginPreferences();
		return prefs.getInt(key);
	}
	
	/**
	 * Преобразование строкового массива в строку. Может использоваться 
	 * для хранения строковых массивов в конфигурации НЕ графического плагина.
	 * 
	 * @param arr
	 * 			входной массив строк
	 * @param delim
	 * 			символ разделителя
	 * @return
	 * 			строка, созданная из массива
	 */
	public static final String stringArrToString(String[] arr, String delim){
		if (arr == null || delim == null)
			return null;
		StringBuilder result = new StringBuilder();
		for(int i = 0; i<arr.length; i++)
			result.append(arr[i]).append(delim);
		return new String(result);
	}
	
	/**
	 * Преобразование списка строк в строку. Может использоваться 
	 * для хранения строковых массивов в конфигурации НЕ графического плагина. 
	 * 
	 * @param list
	 * 			список строк
	 * @param delim
	 * 			символ разделителя
	 * @return
	 * 			строка, созданная из списка
	 */
	public static final String stringListToString(List<String> list, String delim){
		if (list == null || delim == null)
			return null;
		StringBuilder result = new StringBuilder();
		for(Iterator<String> it = list.listIterator();it.hasNext();)
			result.append(it.next()).append(delim);
		return new String(result);
	}
	
	/**
	 * Преобразование строки в массив строк
	 * 
	 * @param str
	 * 			исходная строка
	 * @param delim
	 * 			разделитель
	 * @return
	 * 			массив строк
	 */
	public static final String[] stringToStringArray(String str, String delim){
		if (str == null || delim == null)
			return null;
		StringTokenizer st = new StringTokenizer(str, delim);
		String[] result = new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens()){
			result[i] = st.nextToken().trim();
			i++;
		}
		return result;
	}
	
	/**
	 * Возвращает контроль текущего активного окна студии разработки
	 * 
	 * @return
	 */
	public static Shell getMainShell(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
	
	/**
	 * Сохранение отладочной информации в студии разработки
	 * 
	 * @param msg
	 * 			 строка-сообщение
	 * @param e
	 * 			исключительна ситуация
	 */
	public static void log(String msg, Throwable e){
		CorePlugin.getDefault().getLog().log(new Status(Status.ERROR, CorePlugin.getID(), 
                Status.OK, msg, e));
	}
	
}
