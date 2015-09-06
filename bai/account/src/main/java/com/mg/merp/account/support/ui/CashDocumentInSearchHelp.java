/*
 * CashDocumentInSearchHelp.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.generic.ui.DefaultLegacySearchHelp;

/**
 * Контроллер формы поиска бизнес-компонента "Приходные кассовые документы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: CashDocumentInSearchHelp.java,v 1.1 2008/03/12 11:22:38 alikaev Exp $
 */
public class CashDocumentInSearchHelp extends DefaultLegacySearchHelp {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultLegacySearchHelp#getServiceName()
   */
  @Override
  protected String getServiceName() {
    return "merp/account/CashDocumentIn";
  }

}
