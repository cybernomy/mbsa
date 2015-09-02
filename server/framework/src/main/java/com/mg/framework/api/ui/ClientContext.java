/*
 * ClientContext.java
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
package com.mg.framework.api.ui;

/**
 * Среда пользовательского интерфейса, дает доступ к операциям на стороне выполнения клиентского
 * приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ClientContext.java,v 1.4 2008/05/29 13:41:01 safonov Exp $
 */
public interface ClientContext {
	
	/**
	 * показать документ управляемый текущим сервером приложения, например если необходимо показать
	 * документ <code>http://localhost:8080/jmx-console</code>, то необходимо передать путь <code>jmx-console</code>
	 * 
	 * @param urlString	путь к документу не включающий адрес текущего сервера
	 */
	void showLocalDocument(String urlString);
	
	/**
	 * показать документ
	 * 
	 * @param urlString	полный путь к документу, например <code>"http://www.m-g.ru"</code>
	 */
	void showDocument(String urlString);

	/**
	 * показать документ принадлежащий текущему приложению
	 * 
	 * @param urlString	путь к документу не включающий адрес текущего приложения
	 */
	void showApplicationDocument(String urlString);

	/**
	 * загрузка файла на сервер приложения со стороны клиента 
	 * 
	 * @param handler	обработчик загрузки
	 */
	void loadFile(FileLoadHandler handler);
	
	/**
	 * загрузка файла на сервер приложения со стороны клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик загрузки
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	void loadFile(FileLoadHandler handler, long maximumFileSize);

	/**
	 * загрузка файла на сервер приложения со стороны клиента
	 * 
	 * @param handler	обработчик загрузки
	 * @param fileChooserConfig	кофигурация диалога загрузки
	 */
	void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig);
	
	/**
	 * загрузка файла на сервер приложения со стороны клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик загрузки
	 * @param fileChooserConfig	кофигурация диалога загрузки
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	void loadFile(FileLoadHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize);

	/**
	 * сохранение файла на стороне клиента
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileName	имя файла, может быть <code>null</code>
	 */
	void storeFile(FileStoreHandler handler, String fileName);

	/**
	 * сохранение файла на стороне клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileName	имя файла, может быть <code>null</code>
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	void storeFile(FileStoreHandler handler, String fileName, long maximumFileSize);

	/**
	 * сохранение файла на стороне клиента
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileChooserConfig	кофигурация диалога сохранения
	 */
	void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig);

	/**
	 * сохранение файла на стороне клиента с ограничением размера файла
	 * 
	 * @param handler	обработчик сохранения
	 * @param fileChooserConfig	кофигурация диалога сохранения
	 * @param maximumFileSize	максимальный размер файла в байтах
	 */
	void storeFile(FileStoreHandler handler, FileChooserConfig fileChooserConfig, long maximumFileSize);

	/**
	 * Returns the raw IP address string "%d&#46%d&#46%d&#46%d".
	 * 
	 * @return	the raw IP address string "%d.%d.%d.%d"
	 */
	String getAddress();
	
	/**
	 * Returns the host name.
	 * 
	 * @return	the host name
	 */
	String getHost();

	/**
	 * Emits an audio beep.
	 */
	void beep();

	/**
	 * Выбор пользователем одного или нескольких файлов
	 * 
	 * @param fileChooseHandler	обработчик выбора
	 * @param fileChooserConfig	кофигурация диалога выбора
	 */
	void chooseFile(FileChooseHandler fileChooseHandler, FileChooserConfig fileChooserConfig);
	
}
