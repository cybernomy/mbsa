/*
 * CashDocumentOutSearchHelp.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Контроллер формы поиска бизнес-компонента "Расходные кассовые документы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: CashDocumentOutSearchHelp.java,v 1.1 2008/03/12 11:22:38 alikaev Exp $
 */
public class CashDocumentOutSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/account/CashDocumentOut";
  }

}
