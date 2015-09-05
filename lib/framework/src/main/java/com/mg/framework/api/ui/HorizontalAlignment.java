/**
 * HorizontalAlignment.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

/**
 * Горизонтальное выравнивание
 * 
 * @author Oleg V. Safonov
 * @version $Id: HorizontalAlignment.java,v 1.1 2008/07/24 15:12:25 safonov Exp $
 */
public enum HorizontalAlignment {
	LEFT,
	CENTER,
	RIGHT,
	/**
	 * Identifies the leading edge of text for use with left-to-right and
	 * right-to-left languages.
	 */
	LEADING,
	/**
	 * Identifies the trailing edge of text for use with left-to-right and
	 * right-to-left languages.
	 */
	TRAILING
}
