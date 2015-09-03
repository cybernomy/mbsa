/* Dialogs.java
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
package com.mg.merp.wb.core.ui.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.MessageBox;

import com.mg.merp.wb.core.support.CoreUtils;
import com.mg.merp.wb.core.ui.UiPlugin;

/**
 * Утилитарный класс вызова стандартных диалоговых окон
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: Dialogs.java,v 1.6 2007/11/04 14:37:47 safonov Exp $ 
 */
public class Dialogs {

	/**
	 * Вызов окна сообщений
	 * 
	 * @param message
	 * 			текст сообщения
	 * @param flags
	 * 			флаги (ОК, CANCEL...)
	 * 
	 * @return
	 * 			код выбранной кнопки диалога
	 */
	public static int showMessage(String message, int flags){
		MessageBox messageBox = new MessageBox(CoreUtils.getMainShell(), flags);
		messageBox.setMessage(message);
		return messageBox.open();
	}
	
	/**
	 * Диалог для вывода сообщения об исключительной ситуации
	 * 
	 * @param title
	 * 			заголовок окна диалога
	 * @param message
	 * 			текст сообщения
	 * @param pluginId
	 * 			идентификатор плагин, из которого вызывается диалог
	 * @param ex
	 * 			исключительная ситуация
	 * 
	 * @return
	 * 			код выбранной кнопки диалога
	 */
	public static int openError(String title, String message, String pluginId, Throwable ex){
		CoreUtils.log(message, ex);
		return ErrorDialog.openError(CoreUtils.getMainShell(), UiPlugin.getDefault().getString("dialogs.error.title")
				, title, new Status(IStatus.ERROR, pluginId, 1, message, ex));
	}
}
