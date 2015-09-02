/*
 * CashDocumentBr.java
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
 *  онтроллер формы списка кассовых документов
 * 
 * @author leonova
 * @version $Id: CashDocumentBr.java,v 1.5 2008/02/21 12:19:40 alikaev Exp $ 
 */
public class CashDocumentBr extends DocumentBrowseForm {
	protected final String INIT_QUERY_TEXT = "select %s from CashDocument d %s %s order by d.DocDate, d.Id "; //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.DocumentBr#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();
		CashDocumentRest restDocument = (CashDocumentRest) getRestrictionForm();
		whereText.append(DatabaseUtils.formatEJBQLStringRestriction("d.ContractNumber", restDocument.getContractNumber(), "contractDocNumber", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractType", restDocument.getContractType(), "contractDocType", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.ContractDate", restDocument.getContractDate(), "contractDocDate", paramsName, paramsValue, false)). //$NON-NLS-1$ //$NON-NLS-2$
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.Acc", restDocument.getAccount(), "account", paramsName, paramsValue, false)); //$NON-NLS-1$ //$NON-NLS-2$
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText.toString());
	}
}
