/*
 * BusinessServiceImplKind.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Вид реализации безнес-компонента
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessServiceImplKind.java,v 1.2 2008/03/03 13:11:02 safonov Exp $
 */
@DataItemName ("Core.BusinessServiceImplKind")
public enum BusinessServiceImplKind {
	
	/**
	 * Бизнес-компонент реализован на EJB
	 */
	EJB
}
