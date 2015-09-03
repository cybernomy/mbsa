/*
 * ContractSpecMt.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.contract.ContractSpecServiceLocal;
import com.mg.merp.contract.model.ContractSpec;

/**
 * Контроллер формы поддержки "Спецификации контракта"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ContractSpecMt.java,v 1.1 2008/03/11 09:53:15 sharapov Exp $
 */
public class ContractSpecMt extends DefaultMaintenanceForm {
	
	public ContractSpecMt() {
		super();
	}
	
	/**
	 * Обработчик кнопки "Рассчитать"
	 * @param event - событие
	 */
	public void onActionAdjust(WidgetEvent event) {
		((ContractSpecServiceLocal) getService()).adjust((ContractSpec) getEntity());
	}
	
}
