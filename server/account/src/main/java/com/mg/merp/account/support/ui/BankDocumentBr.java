/*
 * BankDocumanrBr.java
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

import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.DocumentBrowseForm;

/**
 * Базовый класс контроллера браузера банковских документов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BankDocumentBr.java,v 1.6 2008/02/21 11:05:03 alikaev Exp $
 */
public class BankDocumentBr extends DocumentBrowseForm {

  protected final String INIT_QUERY_TEXT = "select %s from BankDocument d %s %s order by d.DocDate, d.Id "; //$NON-NLS-1$

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentBr#createQueryText()
   */
  @Override
  protected String createQueryText() {
    super.createQueryText();
    whereText.append(DatabaseUtils.formatEJBQLStringRestriction("d.ContractNumber", restDocument.getContractNumber(), "contractDocNumber", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractType", restDocument.getContractType(), "contractDocType", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractDate", restDocument.getContractDate(), "contractDocDate", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.PayerBankReq", ((BankDocumentRest) restDocument).getPayerBank(), "payerBankReq", paramsName, paramsValue, false)) //$NON-NLS-1$ //$NON-NLS-2$
        .append(DatabaseUtils.formatEJBQLObjectRestriction("d.RecipientBankReq", ((BankDocumentRest) restDocument).getRecipientBank(), "recipientBankReq", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
  }

}
