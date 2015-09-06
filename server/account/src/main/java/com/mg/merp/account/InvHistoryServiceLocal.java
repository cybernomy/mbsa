/*
 * InvHistoryServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.InvHistory;

/**
 * Бизнес-компонент "История инвентарной карточки"
 *
 * @author leonova
 * @version $Id: InvHistoryServiceLocal.java,v 1.2 2008/04/28 10:09:51 alikaev Exp $
 */
public interface InvHistoryServiceLocal extends com.mg.framework.api.DataBusinessObjectService<InvHistory, Integer> {

  /**
   * Откат истории инв.объекта
   *
   * @param invHistory - история инвентарной карточки
   */
  void rollbackInvHistory(InvHistory invHistory);

}
