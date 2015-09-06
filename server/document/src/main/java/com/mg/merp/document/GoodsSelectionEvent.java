/*
 * GoodsSelectionEvent.java
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
package com.mg.merp.document;

import java.util.EventObject;

/**
 * Событие о выборе номенклатуры
 *
 * @author Oleg V. Safonov
 * @version $Id: GoodsSelectionEvent.java,v 1.1 2006/12/02 12:35:32 safonov Exp $
 */
public class GoodsSelectionEvent extends EventObject {
  private CreateSpecificationInfo[] specInfo;

  /**
   * создать событие
   *
   * @param source   источник
   * @param specInfo информация о подобранной номенклатуре
   */
  public GoodsSelectionEvent(Object source, CreateSpecificationInfo[] specInfo) {
    super(source);
    this.specInfo = specInfo;
  }

  /**
   * получить информацию о подобранной номенклатуре
   *
   * @return the specInfo
   */
  public CreateSpecificationInfo[] getSpecInfo() {
    return specInfo;
  }

}
