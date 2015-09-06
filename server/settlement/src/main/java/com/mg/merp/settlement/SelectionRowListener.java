/*
 * SelectionRowListener.java
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
package com.mg.merp.settlement;

import com.mg.merp.settlement.support.ui.ContractorCardMt.DocListItem;

import java.util.EventListener;

/**
 * Слушатель события "Изменения позиции курсора таблицы"
 *
 * @author Artem V. Sharapov
 * @version $Id: SelectionRowListener.java,v 1.1 2007/03/19 15:05:29 sharapov Exp $
 */
public interface SelectionRowListener extends EventListener {

  /**
   * Cобытие "Изменение позиции курсора таблицы"
   *
   * @param selectedItem - данные текущей позиции курсора таблицы
   */
  public void selectedRowChange(DocListItem selectedItem);

}
