/*
 * ResourceFolderSearchHelp.java
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

import com.mg.merp.paymentcontrol.ResourceServiceLocal;


/**
 * Поисковик сущностей "Папки для средства платежа"
 *
 * @author Artem V. Sharapov
 * @version $Id: ResourceFolderSearchHelp.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class ResourceFolderSearchHelp extends PmcFolderSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.paymentcontrol.support.ui.PmcFolderSearchHelp#getFolderPart()
   */
  @Override
  public short getFolderPart() {
    return ResourceServiceLocal.FOLDER_PART;
  }

}
