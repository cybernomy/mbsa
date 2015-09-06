/*
 * LiabiltyDestinationFolderSearchHelp.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.merp.paymentcontrol.LiabilityServiceLocal;


/**
 * Поисковик сущностей "Папки-приемника обязательства"
 *
 * @author Artem V. Sharapov
 * @version $Id: LiabiltyDestinationFolderSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class LiabiltyDestinationFolderSearchHelp extends PmcFolderSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.support.ui.PmcFolderSearchHelp#getFolderPart()
   */
  @Override
  public short getFolderPart() {
    return LiabilityServiceLocal.FOLDER_PART;
  }

}
