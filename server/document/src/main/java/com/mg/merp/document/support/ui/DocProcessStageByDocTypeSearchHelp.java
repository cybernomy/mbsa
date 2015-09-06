/*
 * DocProcessStageByDocTypeSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.document.model.DocType;

/**
 * Механизм поиска сущностей этапов ДО конкретного типа документа
 *
 * @author Artem V. Sharapov
 * @version $Id: DocProcessStageByDocTypeSearchHelp.java,v 1.1 2008/08/15 06:27:14 sharapov Exp $
 */
public class DocProcessStageByDocTypeSearchHelp extends AbstractSearchHelp {

  private final String DOC_TYPE_IMPORT = "docType"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    DocType docType = (DocType) getImportContextValue(DOC_TYPE_IMPORT);
    if (docType != null) {
      DocProcessStageByDocTypeSearchForm searchForm = (DocProcessStageByDocTypeSearchForm) ApplicationDictionaryLocator.locate().getWindow(DocProcessStageByDocTypeSearchForm.FORM_NAME);
      searchForm.addSearchHelpListener(this);
      searchForm.setSearchParams(docType);
      searchForm.run(UIUtils.isModalMode());
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{DOC_TYPE_IMPORT};
  }

}
