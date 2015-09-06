/*
 * DefaultTableTransferHandler.java
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
package com.mg.framework.support.ui.dnd;

import com.mg.framework.api.ui.Widget;
import com.mg.framework.api.ui.dnd.DropAction;
import com.mg.framework.api.ui.dnd.TransferHandler;
import com.mg.framework.api.ui.dnd.Transferable;

import java.util.EnumSet;

/**
 * Стандартная реализация обработчика DnD для таблицы, таблица работает только в качестве источника
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultTableTransferHandler.java,v 1.1 2007/08/16 13:59:03 safonov Exp $
 */
public class DefaultTableTransferHandler implements TransferHandler {

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.dnd.TransferHandler#exportDone(com.mg.framework.api.ui.Widget, com.mg.framework.api.ui.dnd.Transferable, com.mg.framework.api.ui.dnd.DropAction)
   */
  public void exportDone(Widget sourceComponent, Transferable transferable,
                         DropAction dropAction) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.dnd.TransferHandler#getSourceActions()
   */
  public EnumSet<DropAction> getSourceActions() {
    return EnumSet.of(DropAction.ACTION_MOVE);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.dnd.TransferHandler#getTargetActions()
   */
  public EnumSet<DropAction> getTargetActions() {
    return EnumSet.of(DropAction.ACTION_NONE);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.dnd.TransferHandler#importData(com.mg.framework.api.ui.Widget, com.mg.framework.api.ui.dnd.Transferable)
   */
  public boolean importData(Widget targetComponent, Transferable transferable) {
    return false;
  }

}
