/*
 * FileStoreHandler.java
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

import java.io.OutputStream;
import java.io.Serializable;

/**
 * Обработчик сохранения файла на стороне клиента
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileStoreHandler.java,v 1.3 2008/05/29 13:41:01 safonov Exp $
 */
public interface FileStoreHandler extends Serializable {
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
	 * подготовка файла к отправке
	 * 
	 * @param data
	 * @throws Exception
	 */
	void prepareFile(OutputStream data) throws Exception;

	/**
	 * сохранения успешно завершено
	 * 
	 * @param filePath	полный локальный путь к файлу, или <code>null</code> если среда клиента не предоставляет информацию о путях
	 * @param fileName	имя файла, или <code>null</code> если среда клиента не предоставляет информацию об именах
	 */
	void onSuccess(String filePath, String fileName);
	
	/**
	 * сохранение прервано
	 * 
	 * @param reason		причина прерывания, может принимать следующие значения: <ul> <li>{@link #CANCELLED}: пользователь отменил действие <li>{@link #FAILED}: действие завершено аварийно </ul>
	 * @param description	описание
	 */
	void onFailure(int reason, String description);
	
}
