/**
 * CoSUtils.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.mg.framework.api.Logger;

/**
 * Утилиты класса обслуживания (Class of Service)
 * 
 * @author Oleg V. Safonov
 * @version $Id: CoSUtils.java,v 1.2 2008/07/14 13:37:11 safonov Exp $
 */
public class CoSUtils {
	private static final String COS_PROPERTIES_FILE_PATH = "/conf/cos.properties";
	private static Logger logger = ServerUtils.getLogger(CoSUtils.class);

	private static Properties loadProperties() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(ServerUtils.MBSA_CUSTOM_LOCATION.concat(COS_PROPERTIES_FILE_PATH)));
		return prop;
	}
	
	/**
	 * получить максимальный размер файла загружаемый со стороны клиента на сервер
	 * 
	 * @return	максимальный размер файла в байтах
	 */
	public static long getMaxUploadFileSize() {
		long fileSize = 0;
		try {
			Properties prop = loadProperties();
			fileSize = Integer.parseInt(prop.getProperty("core.maxuploadfilesize", "0"));
		} catch (Exception e) {
			logger.error("getMaxUploadFileSize failed", e);
		}
		return fileSize;
	}

	/**
	 * получить максимальный размер файла загружаемый со стороны сервера на клиент
	 * 
	 * @return	максимальный размер файла в байтах
	 */
	public static long getMaxDownloadFileSize() {
		long fileSize = 0;
		try {
			Properties prop = loadProperties();
			fileSize = Integer.parseInt(prop.getProperty("core.maxdownloadfilesize", "0"));
		} catch (Exception e) {
			logger.error("getMaxUploadFileSize failed", e);
		}
		return fileSize;
	}

	/**
	 * получить временной интервал опроса клиентом сервера для реализации модели client poll
	 * 
	 * @return	временной интервал в мс, если значение меньше или равно 0, то опрос не производится
	 */
	public static int getPollingInterval() {
		int pollingInterval = 0;
		try {
			Properties prop = loadProperties();
			pollingInterval = Integer.parseInt(prop.getProperty("core.pollinginterval", "0"));
			logger.info("load polling interval: " + pollingInterval);
		} catch (Exception e) {
			logger.error("getPollingInterval failed", e);
		}
		return pollingInterval;
	}

}
