/*
 * DocModelThroughDocSectionSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.generic.ui.AbstractSearchHelp;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.support.DocumentUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для SearchHelp, реализующий поиск по DocSection. После выбора DocSection осущесвляется
 * поиск по образцам документов выбранного типа
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocModelThroughDocSectionSearchHelp.java,v 1.4 2009/02/10 14:05:49 safonov Exp $
 */
public class DocModelThroughDocSectionSearchHelp extends AbstractSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doSearch()
   */
  @Override
  protected void doSearch() throws Exception {
    SearchHelp searchHelp = new DocSectionSearchHelp();
    final DocModelThroughDocSectionSearchHelp thisRef = this;
    searchHelp.addSearchHelpListener(new SearchHelpListener() {

      public void searchCanceled(SearchHelpEvent event) {
        fireSearchCanceled(event);
      }

      public void searchPerformed(SearchHelpEvent event) {
        SearchHelp searchHelp = new UniversalDocModelSearchHelp();
        searchHelp.addSearchHelpListener(thisRef);
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("DocSection", event.getItems()[0]); //$NON-NLS-1$
        searchHelp.setImportContext(context);
        searchHelp.search();
      }

    });
    searchHelp.search();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.SearchHelp#isSupportView()
   */
  @Override
  public boolean isSupportView() {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doView(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  protected void doView(PersistentObject entity) {
    MaintenanceHelper.view(DocumentUtils.getDocumentService(((DocHeadModel) entity).getDocSection()).getPatternService(), (Integer) entity.getPrimaryKey(), null, null);
  }

}
