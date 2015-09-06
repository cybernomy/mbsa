/*
 * PaymentallocProcessorListener.java
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
package com.mg.merp.paymentalloc;

import java.util.EventListener;

/**
 * Слушатель процессора модуля "Журнал платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PaymentallocProcessorListener.java,v 1.1 2007/05/25 08:40:09 sharapov Exp $
 */
public interface PaymentallocProcessorListener extends EventListener {

  public void completed();

  public void canceled();

}
