/*
 * AbstractFolderSearchHelp.java
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
package com.mg.merp.reference.generic.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.reference.support.ui.FolderSearchForm;

/**
 * Абстрактный класс поиска папок, отбирает папки по типу папок, классы наследники должны загружать
 * в контекст импорта тип папки и указать имя контекста
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractFolderSearchHelp.java,v 1.2 2006/09/20 13:05:37 safonov Exp $
 */
public abstract class AbstractFolderSearchHelp extends AbstractSearchHelp {

  /**
   * возвращает имя контекста в котором хранится тип папки
   *
   * @return имя контекста
   */
  protected abstract String getFolderTypeContextName();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    FolderSearchForm form = (FolderSearchForm) UIProducer.produceForm("com/mg/merp/reference/resources/FolderSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    Integer folderType = (Integer) getImportContextValue(getFolderTypeContextName());
    if (folderType != null) {
      form.setFolderType(folderType.shortValue());
      form.run(UIUtils.isModalMode());
    }
  }

}
