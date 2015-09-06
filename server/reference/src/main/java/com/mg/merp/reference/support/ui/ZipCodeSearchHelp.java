/*
 * ZipCode.java
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
import com.mg.framework.generic.ui.DefaultLegacySearchHelp;
import com.mg.merp.reference.model.ZipCode;

/**
 * @author leonova
 * @version $Id: ZipCodeSearchHelp.java,v 1.3 2007/09/05 11:07:23 alikaev Exp $
 */
public class ZipCodeSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/reference/ZipCode"; //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    ZipCode zipCode = (ZipCode) event.getItems()[0];
    setExportContextValue("Country", zipCode.getCountry()); //$NON-NLS-1$
    setExportContextValue("Region", zipCode.getRegion()); //$NON-NLS-1$
    setExportContextValue("Place", zipCode.getPlace()); //$NON-NLS-1$
    setExportContextValue("District", zipCode.getDistrict()); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{"Country", "Region", "Place", "District"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

}
