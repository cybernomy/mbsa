/*
 * InputModelSearchHelp.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocSection;

import java.util.HashMap;
import java.util.Map;

/**
 * Базовый поисковик "Образцов актов"
 *
 * @author Artem V. Sharapov
 * @version $Id: ModelSearchHelp.java,v 1.1 2007/01/13 13:23:13 sharapov Exp $
 */
public abstract class ModelSearchHelp extends AbstractSearchHelp {

  private static final String DOC_SECTION = "DocSection"; //$NON-NLS-1$
  private static final String SEARCH_HELP = "com.mg.merp.document.support.ui.UniversalDocModelSearchHelp"; //$NON-NLS-1$
  private Map<String, Object> context = new HashMap<String, Object>();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    SearchHelp searchHelp = SearchHelpProcessor.createSearch(SEARCH_HELP);
    searchHelp.addSearchHelpListener(this);
    context.put(DOC_SECTION, getDocSection());
    searchHelp.setImportContext(context);
    searchHelp.search();
  }

  /**
   * Получить DocSection
   *
   * @return DocSection
   */
  private DocSection getDocSection() {
    return ServerUtils.getPersistentManager().find(DocSection.class, new java.lang.Integer(getDocSectionPrimaryKey()));
  }

  /**
   * Получить идентификатор DocSection'a. Должен быть реализован в классе-наследнике
   *
   * @return идентификатор DocSection'a
   */
  abstract protected short getDocSectionPrimaryKey();

}
