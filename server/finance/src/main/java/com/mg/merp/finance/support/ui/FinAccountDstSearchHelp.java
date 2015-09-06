/*
 * FinAccountDstSearchHelp.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.ui.SearchHelpEvent;

/**
 * @author Konstantin S. Alikaev
 * @version $Id: FinAccountDstSearchHelp.java,v 1.1 2008/05/22 13:33:50 alikaev Exp $
 */
public class FinAccountDstSearchHelp extends FinAccountSearchHelp {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{"DstAnl1", "DstAnl2", "DstAnl3", "DstAnl4", "DstAnl5", "anlLevel1DstName", "anlLevel2DstName", "anlLevel3DstName", "anlLevel4DstName", "anlLevel5DstName"};
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    setExportContextValue("DstAnl1", null);
    setExportContextValue("DstAnl2", null);
    setExportContextValue("DstAnl3", null);
    setExportContextValue("DstAnl4", null);
    setExportContextValue("DstAnl5", null);
    setExportContextValue("anlLevel1DstName", null);
    setExportContextValue("anlLevel2DstName", null);
    setExportContextValue("anlLevel3DstName", null);
    setExportContextValue("anlLevel4DstName", null);
    setExportContextValue("anlLevel5DstName", null);
  }

}
