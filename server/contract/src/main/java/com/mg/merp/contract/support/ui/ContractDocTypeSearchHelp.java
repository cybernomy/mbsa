/*
 * ContractDocTypeSearchHelp.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.UIUtils;
import com.mg.merp.contract.ContractServiceLocal;
import com.mg.merp.document.support.ui.ContractDocTypeByDocSectionSearchHelp;
import com.mg.merp.document.support.ui.DocTypeSearchForm;

/**
 * Поисковик сущностей "Тип контракта"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractDocTypeSearchHelp.java,v 1.1 2007/11/30 14:29:04 sharapov Exp $
 */
public class ContractDocTypeSearchHelp extends ContractDocTypeByDocSectionSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.AbstractDocTypeSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    DocTypeSearchForm form = (DocTypeSearchForm) UIProducer.produceForm("com/mg/merp/document/resources/DocTypeSearchForm.mfd.xml"); //$NON-NLS-1$
    form.addSearchHelpListener(this);
    if (isDocument()) {
      form.setParam(new Integer(ContractServiceLocal.DOCSECTION), getDocumentKind());
      form.run(UIUtils.isModalMode());
    }
  }

}
