/*
 * AlreadyCompletedException.java
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
import com.mg.merp.document.model.DocHead;

import java.util.Date;

/**
 * Класс ИС ДО выполнен
 *
 * @author Oleg V. Safonov
 * @version $Id: AlreadyCompletedException.java,v 1.4 2007/08/06 13:19:45 safonov Exp $
 */
@javax.ejb.ApplicationException(rollback = true)
public class AlreadyCompletedException extends BusinessException {
  private String docSectionName;
  private String docTypeCode;
  private String docNumber;
  private Date docDate;

  public AlreadyCompletedException(DocHead docHead) {
    super("already completed");
    this.docSectionName = docHead.getDocSection().getDSName().trim();
    this.docTypeCode = docHead.getDocType().getCode().trim();
    this.docNumber = docHead.getDocNumber();
    this.docDate = docHead.getDocDate();
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.ALREADY_COMPLETED_MESSAGE, new Object[]{docSectionName, docTypeCode, docNumber, docDate});
  }

}
