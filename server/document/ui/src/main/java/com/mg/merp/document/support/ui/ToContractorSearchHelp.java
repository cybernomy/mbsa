/*
 * ToContractorSearchHelp.java
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

import com.mg.merp.reference.support.ui.UniversalContractorSearchHelp;

/**
 * Поиск контрагента для поля документа "Кому"
 *
 * @author Oleg V. Safonov
 * @version $Id: ToContractorSearchHelp.java,v 1.1 2006/12/02 12:16:31 safonov Exp $
 */
public class ToContractorSearchHelp extends UniversalContractorSearchHelp {
  private static final String CONTRACTOR_KIND = "contractorToKinds"; //$NON-NLS-1$

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
