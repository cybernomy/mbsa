/*
 * CashDocumentOutServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.account.model.CashDocument;

/**
 * @author leonova
 * @version $Id: CashDocumentOutServiceLocal.java,v 1.3 2006/09/20 10:43:40 safonov Exp $
 */
public interface CashDocumentOutServiceLocal
    extends com.mg.merp.document.Document<CashDocument, Integer, CashDocumentModelOutServiceLocal> {
  /**
   * тип папки для расходных кассовых документов
   */
  final static short FOLDER_PART = 12;

  /**
   * docsection для расходных кассовых документов
   */
  final static short DOCSECTION = 6;
}
