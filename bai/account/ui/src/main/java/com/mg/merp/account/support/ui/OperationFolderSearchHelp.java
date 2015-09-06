/*
 * OperationFolderSearchHelp.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.ConversationBeginner;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

/**
 * Механизм поиска папка-приемник хоз. операции
 *
 * @author Konstantin S. Alikaev
 * @version $Id: OperationFolderSearchHelp.java,v 1.1 2008/04/29 05:29:04 alikaev Exp $
 */
public class OperationFolderSearchHelp extends FolderByTypeSearchHelp implements ConversationBeginner {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.FolderByTypeSearchHelp#getFolderType()
   */
  @Override
  protected short getFolderType() {
    return OperationServiceLocal.FOLDER_PART;
  }

}
