/*
 * DocSectionSearchHelp.java
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

import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;

/**
 * Поиск объектов-сущностей {@link com.mg.merp.document.model.DocSection DocSection}
 *
 * @author Oleg V. Safonov
 * @version $Id: DocSectionSearchHelp.java,v 1.2 2006/09/18 13:12:06 safonov Exp $
 */
public class DocSectionSearchHelp extends AbstractSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    SearchHelpForm form = (SearchHelpForm) UIProducer.produceForm("com/mg/merp/document/resources/DocSectionSearchForm.mfd.xml");
    form.addSearchHelpListener(this);
    form.run(UIUtils.isModalMode());
  }

}
