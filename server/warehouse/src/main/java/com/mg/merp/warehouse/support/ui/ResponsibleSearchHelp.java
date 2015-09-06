/*
 * ResponsibleSearchHelp.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.merp.reference.support.ui.UniversalContractorSearchHelp;

/**
 * @author leonova
 * @version $Id: ResponsibleSearchHelp.java,v 1.1 2006/12/20 06:17:43 leonova Exp $
 */
public class ResponsibleSearchHelp extends UniversalContractorSearchHelp {
  private static final String CONTRACTOR_KIND = "contractorResponsibleKinds"; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineImportContext()
   */
  @Override
  protected String[] defineImportContext() {
    return new String[]{CONTRACTOR_KIND};
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.UniversalContractorSearchHelp#getContractorKinds()
   */
  @Override
  protected String[] getContractorKinds() {
    return (String[]) getImportContextValue(CONTRACTOR_KIND);
  }

}
