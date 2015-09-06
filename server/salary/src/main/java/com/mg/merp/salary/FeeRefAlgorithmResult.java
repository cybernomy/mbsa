/*
 * FeeRefAlgorithmResult.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.salary;

import java.util.Date;

/**
 * Результат выполнения расчета
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class FeeRefAlgorithmResult {
  /**
   * сумма н/у
   */
  public double value;

  /**
   * начислено с
   */
  public Date beginDate;

  /**
   * начислено по
   */
  public Date endDate;

  /**
   * за период с
   */
  public Date periodBeginDate;

  /**
   * за период по
   */
  public Date periodEndDate;

  /**
   * идентификатор аналитики состава затрат 1-го уровня
   */
  public int costsAnl1Id;

  /**
   * идентификатор аналитики состава затрат 2-го уровня
   */
  public int costsAnl2Id;

  /**
   * идентификатор аналитики состава затрат 3-го уровня
   */
  public int costsAnl3Id;

  /**
   * идентификатор аналитики состава затрат 4-го уровня
   */
  public int costsAnl4Id;

  /**
   * идентификатор аналитики состава затрат 5-го уровня
   */
  public int costsAnl5Id;
}
