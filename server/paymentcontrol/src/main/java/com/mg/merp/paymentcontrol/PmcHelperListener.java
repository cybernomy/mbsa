/*
 * PmcHelperListener.java
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
package com.mg.merp.paymentcontrol;

import java.util.EventListener;

/**
 * Слушатель класса помощника для работы с обязательствами
 * 
 * @author Artem V. Sharapov
 * @version $Id: PmcHelperListener.java,v 1.1 2007/05/14 04:59:59 sharapov Exp $
 */
public interface PmcHelperListener extends EventListener {

	void complete();
		
}
