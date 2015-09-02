/*
 * FileLoadHandler.java
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
package com.mg.framework.api.ui;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Обработчик загрузки файла со стороны клиента
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileLoadHandler.java,v 1.2 2008/02/29 10:16:15 safonov Exp $
 */
public interface FileLoadHandler extends Serializable {
	/**
	 * пользователь отменил действие
	 */
	final static int CANCELLED = 1;
	/**
	 * действие завершено аварийно
	 */
	final static int FAILED = 2;
	/**
	 * превышен допустимый размер файла
	 */
	final static int FILE_SIZE_EXCEEDED = 3;

	/**
	 * загрузка успешно завершена
	 * 
	 * @param ins		потоки ввода
	 * @param filePaths	полные локальные пути к загружаемым файлам, или <code>null</code> если среда клиента не предоставляет информацию о путях
	 * @param fileNames	имена загружаемых файлов, или <code>null</code> если среда клиента не предоставляет информацию об именах
	 */
	void onSuccess(InputStream[] ins, String[] filePaths, String[] fileNames);

	/**
	 * загрузка прервана
	 * 
	 * @param reason		причина прерывания, может принимать следующие значения: <ul> <li>{@link #CANCELLED}: пользователь отменил действие <li>{@link #FAILED}: действие завершено аварийно <li>{@link #FILE_SIZE_EXCEEDED}: превышен допустимый размер файла </ul>
	 * @param description	описание
	 */
	void onFailure(int reason, String description);
	
}
