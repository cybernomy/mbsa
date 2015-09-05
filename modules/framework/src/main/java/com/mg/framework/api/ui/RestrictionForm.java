/*
 * RestrictionForm.java
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
package com.mg.framework.api.ui;

/**
 * Форма ограничений (условий отбора)
 * 
 * @author Oleg V. Safonov
 * @version $Id: RestrictionForm.java,v 1.2 2007/03/13 13:25:18 safonov Exp $
 */
public interface RestrictionForm extends DialogForm {

	/**
	 * показывать при входе в раздел
	 * 
	 * @return	если <code>true</code> то форма будет открыта перед показом браузера
	 */
	boolean isShowOnEnter();

}
