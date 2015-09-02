/**
 * DefaultCompoundMaintenanceForm.java
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
package com.mg.framework.generic.ui;

/**
 * Реализация сложной формы поддержки для бизнес-компонента, как правило применяется для форм содержащих
 * зависимые данные, например список спецификаций документа зависит от заголовка документа.
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultCompoundMaintenanceForm.java,v 1.1 2008/02/12 09:05:32 safonov Exp $
 */
public class DefaultCompoundMaintenanceForm extends DefaultMaintenanceForm {

	public DefaultCompoundMaintenanceForm() {
		super();
		setMasterDetail(true);
	}

}
