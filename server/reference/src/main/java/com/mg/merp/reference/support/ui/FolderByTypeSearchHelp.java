/*
 * FolderByTypeSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Абстрактный класс, отбирает папки по типу папок
 *
 * @author leonova
 * @version $Id: FolderByTypeSearchHelp.java,v 1.1 2006/10/10 06:28:13 leonova Exp $
 */
public abstract class FolderByTypeSearchHelp extends AbstractSearchHelp {

  protected abstract short getFolderType();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    FolderSearchForm form = (FolderSearchForm) UIProducer.produceForm("com/mg/merp/reference/resources/FolderSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    if (getFolderType() != 0) {
      form.setFolderType(getFolderType());
      form.run(UIUtils.isModalMode());
    }
  }

}
