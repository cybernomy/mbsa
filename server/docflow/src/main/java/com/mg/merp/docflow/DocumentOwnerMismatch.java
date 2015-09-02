/*
 * DocumentOwnerMismatch.java
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

import com.mg.framework.api.BusinessException;
import com.mg.merp.docflow.support.Messages;

/**
 * Класс ИС не соответствия владельца документа с текущим инициатором
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentOwnerMismatch.java,v 1.2 2006/08/25 11:44:01 safonov Exp $
 */
@javax.ejb.ApplicationException
public class DocumentOwnerMismatch extends BusinessException {

	public DocumentOwnerMismatch() {
		super(Messages.getInstance().getMessage(Messages.DOCUMENT_OWNER_MISMATCH_MESSAGE));
	}
}
