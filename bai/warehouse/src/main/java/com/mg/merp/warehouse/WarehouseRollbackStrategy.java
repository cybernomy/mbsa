/* WarehouseRollbackStrategy.java
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
package com.mg.merp.warehouse;


/**
 * Интерфейс стратегий отката складских этапов ДО
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: WarehouseRollbackStrategy.java,v 1.2 2008/04/18 15:15:53 safonov Exp $
 */
public interface WarehouseRollbackStrategy {

  /**
   * Выполнение отката
   *
   * @param docSpec спецификация
   */
  void rollback(WarehouseProcessDocumentLineData docLineData);

}
