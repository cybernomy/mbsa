/*
 * CashDocumentModelOutServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.account;

import com.mg.merp.account.model.CashDocumentModel;
import com.mg.merp.document.DocumentPattern;

/**
 * @author leonova
 * @version $Id: CashDocumentModelOutServiceLocal.java,v 1.3 2006/09/20 10:43:40 safonov Exp $
 */
public interface CashDocumentModelOutServiceLocal
    extends DocumentPattern<CashDocumentModel, Integer> {
  /**
   * тип папки для образцов расходных кассовых ордеров
   */
  final static short FOLDER_PART = 17;
}
