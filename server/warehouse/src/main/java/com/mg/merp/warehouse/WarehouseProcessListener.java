/*
 * WarehouseTransactionListener.java
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
package com.mg.merp.warehouse;

import java.util.EventListener;

/**
 * Слушатель складского процессора
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WarehouseProcessListener.java,v 1.1 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseProcessListener extends EventListener {

	/**
	 * завершение складской операции
	 */
	void completed();

	/**
	 * отмена складской операции
	 */
	void aborted();
	
}
