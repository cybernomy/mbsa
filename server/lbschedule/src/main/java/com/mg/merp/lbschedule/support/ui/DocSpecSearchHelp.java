/*
 * DocSpecSearchHelp.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.document.model.DocHead;

/**
 * Поисковик объектов-сущностей "Позиций спецификации документа"
 *
 * @author Artem V. Sharapov
 * @version $Id: DocSpecSearchHelp.java,v 1.1 2007/04/17 12:51:41 sharapov Exp $
 */
public class DocSpecSearchHelp extends AbstractSearchHelp {

  private DocHead docHead;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    DocSpecSearchForm form = (DocSpecSearchForm) UIProducer.produceForm("com/mg/merp/lbschedule/resources/DocSpecSearchForm.mfd.xml"); //$NON-NLS-1$
    form.addSearchHelpListener(this);
    form.setSearchParams(docHead);
    form.run(UIUtils.isModalMode());
  }

  public void setSearchParams(DocHead docHead) {
    this.docHead = docHead;
  }

}
