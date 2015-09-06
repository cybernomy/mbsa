/*
 * PfCodeKindInServiceKindSearchHelp.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.merp.personnelref.ServicePFCodeServiceLocal;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;

/**
 * @author leonova
 * @version $Id: PfCodeKindInServiceKindSearchHelp.java,v 1.1 2006/10/10 06:31:48 leonova Exp $
 */
public class PfCodeKindInServiceKindSearchHelp extends FolderByTypeSearchHelp {

  @Override
  protected short getFolderType() {
    return ServicePFCodeServiceLocal.FOLDER_PART;
  }


}
