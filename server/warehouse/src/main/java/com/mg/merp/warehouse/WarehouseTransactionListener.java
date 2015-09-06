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
 * Слушатель отработки по складу
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseTransactionListener.java,v 1.1 2007/02/22 09:44:57 poroxnenko Exp $
 */
public interface WarehouseTransactionListener extends EventListener {

  public void completed();

  public void aborted();
}
