/*
 * BAiImplementationInstantiationException.java
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
package com.mg.merp.baiengine;

import com.mg.merp.baiengine.support.Messages;

/**
 * Класс ИС указывающий о проблемах создания реализации BAi
 *
 * @author Oleg V. Safonov
 * @version $Id: BAiImplementationInstantiationException.java,v 1.2 2008/10/28 13:13:57 safonov Exp
 *          $
 */
@javax.ejb.ApplicationException
public class BAiImplementationInstantiationException extends BusinessAddinException {

  public BAiImplementationInstantiationException(Throwable cause) {
    super(Messages.getInstance().getMessage(Messages.BAI_IMPLEMENTATION_INSTANTIATIAN_ERROR), cause);
  }

}
