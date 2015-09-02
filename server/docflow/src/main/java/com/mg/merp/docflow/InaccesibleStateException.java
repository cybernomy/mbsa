/*
 * InaccesibleStateException.java
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
package com.mg.merp.docflow;

import java.util.Date;

import com.mg.framework.api.BusinessException;
import com.mg.merp.docflow.support.DocumentUtils;
import com.mg.merp.docflow.support.Messages;
import com.mg.merp.document.model.DocHead;

/**
 * Класс ИС этап ДО недоступен
 * 
 * @author Oleg V. Safonov
 * @version $Id: InaccesibleStateException.java,v 1.3 2007/10/23 15:21:09 safonov Exp $
 */
@javax.ejb.ApplicationException
public class InaccesibleStateException extends BusinessException {
	private String stageCode;
	private String docTypeCode;
	private String docNumber;
	private Date docDate;

	public InaccesibleStateException(String stageCode, DocHead docHead) {
		super("inaccesible state");
		this.stageCode = stageCode;
		this.docTypeCode = docHead.getDocType().getCode();
		this.docNumber = docHead.getDocNumber();
		this.docDate = docHead.getDocDate();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return String.format(Messages.getInstance().getMessage(Messages.INACCESIBLE_STATE_MESSAGE), stageCode, DocumentUtils.generateDocumentTitle(docTypeCode, docNumber, docDate));
	}
	
}
