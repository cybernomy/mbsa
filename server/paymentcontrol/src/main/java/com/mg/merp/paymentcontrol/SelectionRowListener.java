/*
 * SelectionRowListener.java
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

import com.mg.merp.paymentcontrol.model.PlanPaymentItem;

/**
 * Слушатель изменения позиции курсора таблицы "Планирование платежей"
 * 
 * @author Artem V. Sharapov
 * @version $Id: SelectionRowListener.java,v 1.1 2007/05/14 04:59:59 sharapov Exp $
 */
public interface SelectionRowListener extends EventListener {

	/**
	 * Cобытие "Изменение позиции курсора таблицы"
	 * @param selectedItem - текущая позиция курсора таблицы
	 */
	public void selectedRowChange(PlanPaymentItem selectedItem);
	
}
