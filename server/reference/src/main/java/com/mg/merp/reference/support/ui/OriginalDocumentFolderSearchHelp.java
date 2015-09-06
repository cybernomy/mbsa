/*
 * OriginalDocumentFolderSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.merp.reference.OriginalDocumentServiceLocal;

/**
 * Механизм поиска сущностей "Папки оригиналов документов"
 *
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentFolderSearchHelp.java,v 1.1 2008/06/17 11:29:59 sharapov Exp $
 */
public class OriginalDocumentFolderSearchHelp extends FolderByTypeSearchHelp {

  /**
   * Имя механизма поиска сущностей "Папки оригиналов документов"
   */
  public static String SEARCH_HELP_NAME = "com.mg.merp.reference.support.ui.OriginalDocumentFolderSearchHelp"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.FolderByTypeSearchHelp#getFolderType()
   */
  @Override
  protected short getFolderType() {
    return OriginalDocumentServiceLocal.FOLDER_PART;
  }

}
