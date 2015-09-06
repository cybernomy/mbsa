/*
 * FinAccountSrcSearchHelp.java
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
 * @version $Id: FinAccountSrcSearchHelp.java,v 1.1 2008/05/22 13:33:50 alikaev Exp $
 */
public class FinAccountSrcSearchHelp extends FinAccountSearchHelp {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#defineExportContext()
   */
  @Override
  protected String[] defineExportContext() {
    return new String[]{"SrcAnl1", "SrcAnl2", "SrcAnl3", "SrcAnl4", "SrcAnl5", "anlLevel1SrcName", "anlLevel2SrcName", "anlLevel3SrcName", "anlLevel4SrcName", "anlLevel5SrcName"};
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchHelp#doOnSearchPerformed(com.mg.framework.api.ui.SearchHelpEvent)
   */
  @Override
  protected void doOnSearchPerformed(SearchHelpEvent event) {
    setExportContextValue("SrcAnl1", null);
    setExportContextValue("SrcAnl2", null);
    setExportContextValue("SrcAnl3", null);
    setExportContextValue("SrcAnl4", null);
    setExportContextValue("SrcAnl5", null);
    setExportContextValue("anlLevel1SrcName", null);
    setExportContextValue("anlLevel2SrcName", null);
    setExportContextValue("anlLevel3SrcName", null);
    setExportContextValue("anlLevel4SrcName", null);
    setExportContextValue("anlLevel5SrcName", null);
  }


}
