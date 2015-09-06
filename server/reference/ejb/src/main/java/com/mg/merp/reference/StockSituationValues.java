/* StockSituationValues.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.OrgUnit;

import java.math.BigDecimal;

/**
 * Инерфейс доступа к количеству запасов на складе
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: StockSituationValues.java,v 1.1 2007/04/05 12:25:41 poroxnenko Exp $
 */
public interface StockSituationValues {

  /**
   * @return склад, для которого рассчитано количество
   */
  OrgUnit getWarehouse();

  /**
   * @return фактическое количество в первой ЕИ
   */
  BigDecimal getLocated1();

  /**
   * @return фактическое количество во второй ЕИ
   */
  BigDecimal getLocated2();

  /**
   * @return планируемый приход в первой ЕИ
   */
  BigDecimal getPlanningReceipt1();

  /**
   * @return планируемый приход во второй ЕИ
   */
  BigDecimal getPlanningReceipt2();

  /**
   * @return планируемый расход в первой ЕИ
   */
  BigDecimal getPlanningIssue1();

  /**
   * @return планируемый расход во второй ЕИ
   */
  BigDecimal getPlanningIssue2();

  /**
   * @return зарезервированное количество в первой ЕИ
   */
  BigDecimal getReserved1();

  /**
   * @return зарезервированное количество во второй ЕИ
   */
  BigDecimal getReserved2();

  /**
   * @return доступное количество в первой ЕИ
   */
  BigDecimal getAvailable1();

  /**
   * @return доступное количество во второй ЕИ
   */
  BigDecimal getAvailable2();
}
