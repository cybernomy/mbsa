/*
 * DefaultMaintenancePane.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.Container;

/**
 * Элемент "Менеджер расположения форм поддержки бизнес-компонентов"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultMaintenancePane.java,v 1.1 2006/11/21 15:34:06 safonov Exp $
 */
public interface DefaultMaintenancePane extends Container {
	/**
	 * атрибут указывающий на положение элемента на той же строке, что и предыдущий,
	 * имеет тип <code>Boolean</code>
	 */
	final static String SAME_LINE = "sameLine";
	
	/**
	 * атрибут указывающий, что данное поле будет растянуто на всю длинну формы,
	 * имеет тип <code>Boolean</code>
	 */
	final static String LONG_FIELD = "longField";
	
	/**
	 * атрибут группы полей, все поля начиная с текущего будут отделены от предыдущих полей,
	 * имеет тип <code>String</code>
	 */
	final static String FIELD_GROUP = "fieldGroup";

}
