/*
 * BadiDataSetWizard.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.wb.report.birt.data.oda.badi.ui.wizards;

import com.mg.merp.wb.report.birt.data.oda.badi.ui.OdaUiPlugin;

import org.eclipse.birt.report.designer.ui.odadatasource.wizards.DefaultExtendedDataSetWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BadiDataSetWizard.java,v 1.6 2007/01/23 08:31:21 poroxnenko Exp $
 */
public class BadiDataSetWizard extends DefaultExtendedDataSetWizard {

  ColumnMappingPage merpBadiDataSetPage = null;

  /* (non-Javadoc)
   * @see org.eclipse.jface.wizard.Wizard#addPages()
   */
  public void addPages() {
    super.addPages();
    merpBadiDataSetPage = new ColumnMappingPage(OdaUiPlugin.getDefault()
        .getString("wizard.title.selectMerpBadi")); //$NON-NLS-1$
    merpBadiDataSetPage.setDataSet(getDataSet());
    addPage(merpBadiDataSetPage);
  }

  public boolean doCancel() {
    return true;
  }

  public boolean doFinish() {
    return merpBadiDataSetPage.performOk();
  }

  /* (non-Javadoc)
   * @see org.eclipse.jface.wizard.Wizard#getStartingPage()
   */
  public IWizardPage getStartingPage() {
    String wizardTitle = OdaUiPlugin.getDefault().getString("dataset.new");//$NON-NLS-1$
    this.setWindowTitle(wizardTitle);

    merpBadiDataSetPage.setDataSet(getDataSet());
    return super.getStartingPage();
  }
}
