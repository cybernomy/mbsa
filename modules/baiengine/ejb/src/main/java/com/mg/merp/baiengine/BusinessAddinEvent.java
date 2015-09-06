/*
 * BusinessAddinEvent.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.baiengine;

import java.util.EventObject;

/**
 * Событие возвращаемое после завершения BAi
 *
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinEvent.java,v 1.2 2007/09/07 12:36:22 safonov Exp $
 */
public class BusinessAddinEvent<T> extends EventObject {
  private T result;

  public BusinessAddinEvent(BusinessAddin<T> addin, T result) {
    super(addin);
    this.result = result;
  }

  /**
   * Получить BAi
   */
  @SuppressWarnings("unchecked")
  public BusinessAddin<T> getAddin() {
    return (BusinessAddin) getSource();
  }

  /**
   * Получить результат выполнения
   *
   * @return результат, возможно значение <code>null</code>
   */
  public T getResult() {
    return result;
  }
}
