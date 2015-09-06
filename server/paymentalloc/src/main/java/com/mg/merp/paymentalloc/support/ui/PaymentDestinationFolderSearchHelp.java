/*
 * PaymentDestinationFolderSearchHelp.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.merp.paymentalloc.PaymentServiceLocal;

/**
 * Поисковик сущностей "Папки-приемника записи журнала платежа"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentDestinationFolderSearchHelp.java,v 1.1 2007/05/25 08:42:43 sharapov Exp $
 */
public class PaymentDestinationFolderSearchHelp extends PmaFolderSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.paymentalloc.support.ui.PmaFolderSearchHelp#getFolderPart()
   */
  @Override
  public short getFolderPart() {
    return PaymentServiceLocal.FOLDER_PART;
  }

}
