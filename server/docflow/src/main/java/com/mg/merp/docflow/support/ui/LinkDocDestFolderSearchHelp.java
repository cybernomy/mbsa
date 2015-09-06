/*
 * LinkDocDestFolderSearchHelp.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.merp.reference.generic.ui.AbstractFolderSearchHelp;

/**
 * @author Oleg V. Safonov
 * @version $Id: LinkDocDestFolderSearchHelp.java,v 1.1 2006/08/25 11:48:17 safonov Exp $
 */
public class LinkDocDestFolderSearchHelp extends AbstractFolderSearchHelp {
  private static final String FOLDER_TYPE_CONTEXT_NAME = "LinkDocSection.FolderType";

  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.FolderSearchHelp#getFolderTypeContextName()
   */
  @Override
  protected String getFolderTypeContextName() {
    return FOLDER_TYPE_CONTEXT_NAME;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{FOLDER_TYPE_CONTEXT_NAME};
  }

}
