/**
 * AbstractDocumentAggregatePropertiesStrategy.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document.generic;

import com.mg.merp.document.DocumentAggregatePropertiesStrategy;

/**
 * Базовая реализация стратегии изменения агрегирующих свойств документа
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractDocumentAggregatePropertiesStrategy.java,v 1.1 2007/09/26 09:39:01 safonov
 *          Exp $
 */
public abstract class AbstractDocumentAggregatePropertiesStrategy implements
    DocumentAggregatePropertiesStrategy {

  /**
   * реализация вычисления
   */
  protected abstract void doCalculate();

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocumentAggregatePropertiesStrategy#calculate()
   */
  public final void calculate() {
    doCalculate();
  }

}
