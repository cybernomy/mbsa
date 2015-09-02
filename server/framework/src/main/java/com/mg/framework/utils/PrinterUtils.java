/**
 * PrinterUtils.java
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

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 * Утилиты сервиса печати
 * 
 * @author Oleg V. Safonov
 * @version $Id: PrinterUtils.java,v 1.1 2008/04/09 14:30:19 safonov Exp $
 */
public class PrinterUtils {
	private static PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	
	/**
	 * получить список принтеров доступных серверу приложения
	 * 
	 * @return	список имен принтеров
	 */
	public static String[] getPrinterNames() {
		String[] result = new String[printServices.length];
		for (int i = 0; i < printServices.length; i++)
			result[i] = printServices[i].getName();
		return result;
	}

}
