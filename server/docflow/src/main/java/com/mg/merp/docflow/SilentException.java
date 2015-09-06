/*
 * SilentException.java
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
 * Класс ИС генерируемая при невозможности взаимодействия документооборота с пользовательским
 * интерфейсом
 *
 * @author Oleg V. Safonov
 * @version $Id: SilentException.java,v 1.2 2008/09/01 07:44:49 safonov Exp $
 */
public class SilentException extends BusinessException {

  public SilentException() {
    super();
  }

  public SilentException(String s) {
    super(s);
  }

  /* (non-Javadoc)
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    String message = super.getLocalizedMessage();
    return message != null ? message : Messages.getInstance().getMessage(Messages.SILENT_DOCFLOW_MESSAGE);
  }

}
