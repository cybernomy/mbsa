/*
 * ZipCodePartnerSearchHelp.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.merp.reference.model.ZipCode;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: ZipCodePartnerSearchHelp.java,v 1.1 2007/09/05 14:13:36 alikaev Exp $
 */
public class ZipCodePartnerSearchHelp extends ZipCodeSearchHelp {

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.ZipCodeSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    ZipCode zipCode = (ZipCode) event.getItems()[0];
    setExportContextValue("Country1", zipCode.getCountry()); //$NON-NLS-1$
    setExportContextValue("Region1", zipCode.getRegion()); //$NON-NLS-1$
    setExportContextValue("Place1", zipCode.getPlace()); //$NON-NLS-1$
    setExportContextValue("District1", zipCode.getDistrict()); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.support.ui.ZipCodeSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{"Country1", "Region1", "Place1", "District1"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

}
