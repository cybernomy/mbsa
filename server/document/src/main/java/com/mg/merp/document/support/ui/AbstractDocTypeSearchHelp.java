/*
 * AbstractDocTypeSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocumentKind;

/**
 * Абстрактный класс для SearchHelp типов документов. Используется в формах поддержки документов и
 * образцов документов.
 *
 * @author leonova
 * @version $Id: AbstractDocTypeSearchHelp.java,v 1.4 2007/12/11 14:04:36 safonov Exp $
 */
public abstract class AbstractDocTypeSearchHelp extends AbstractSearchHelp {

  protected String docHeadOrDocHeadModel = "entity";

  protected abstract boolean isDocument();

  protected abstract boolean isDocModel();

  protected abstract DocumentKind getDocumentKind();

  @Override
  protected String[] defineImportContext() {
    return new String[]{docHeadOrDocHeadModel};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    DocTypeSearchForm form = (DocTypeSearchForm) UIProducer.produceForm("com/mg/merp/document/resources/DocTypeSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    if (isDocument()) {
      Integer docId = 0;
      if (isDocModel()) {
        docId = ((DocHeadModel) getImportContextValue(docHeadOrDocHeadModel)).getDocSection().getId();

      } else {
        docId = ((DocHead) getImportContextValue(docHeadOrDocHeadModel)).getDocSection().getId();
      }
      if (docId != null) {
        form.setParam(docId, getDocumentKind());
      }
    }
    form.run(UIUtils.isModalMode());
  }

}
