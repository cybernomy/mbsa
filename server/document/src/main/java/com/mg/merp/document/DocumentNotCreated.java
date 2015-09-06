/**
 * DocumentNotCreated.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document;

import com.mg.framework.api.BusinessException;
import com.mg.merp.document.support.Messages;

/**
 * Класс ИС генерируемый при попытке обращения к несозданному документооборотом документу
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentNotCreated.java,v 1.1 2008/12/29 09:55:32 safonov Exp $
 */
public class DocumentNotCreated extends BusinessException {

  /**
   *
   */
  public DocumentNotCreated() {
    super("Document is not created");
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return Messages.getInstance().getMessage(Messages.DOCUMENT_NOT_CREATED);
  }

}
