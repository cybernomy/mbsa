/*
 * CreateFeeSummaryDlg.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;

/**
 * Контроллер диалога "Создание свода н/у по аналитике"
 *
 * @author Artem V. Sharapov
 * @version $Id: CreateFeeSummaryDlg.java,v 1.1 2007/08/27 06:21:48 sharapov Exp $
 */
public class CreateFeeSummaryDlg extends DefaultDialog {

  @DataItemName("Salary.FeeSummaryPattern") //$NON-NLS-1$
  private DocHeadModel feeSummaryPattern;

  private boolean isShowCreatedDocument;

  private DocSection DocSection;

  // Default constructor
  public CreateFeeSummaryDlg() {
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    if (feeSummaryPattern != null)
      super.onActionOk(event);
  }

  // Property accessors

  /**
   * @return the feeSummaryPattern
   */
  public DocHeadModel getFeeSummaryPattern() {
    return this.feeSummaryPattern;
  }

  /**
   * @return the isShowCreatedDocument
   */
  public boolean isShowCreatedDocument() {
    return this.isShowCreatedDocument;
  }

  /**
   * @return the docSection
   */
  public DocSection getDocSection() {
    return this.DocSection;
  }

  /**
   * @param docSection the docSection to set
   */
  public void setDocSection(DocSection docSection) {
    this.DocSection = docSection;
  }

}
