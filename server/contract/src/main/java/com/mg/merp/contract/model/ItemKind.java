/*
 * ItemKind.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Виды пунктов 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemKind.java,v 1.2 2007/03/23 15:20:52 sharapov Exp $
 */
@DataItemName ("Contract.Item.ItemKind") //$NON-NLS-1$
public enum ItemKind {
	/**
	 * Сумма платежей контрагенту
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.Kind.Shipped") //$NON-NLS-1$
	SHIPPED,

	/**
	 * Сумма платежей от контрагента
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.Kind.Receive") //$NON-NLS-1$
	RECEIVE,

	/**
	 * Сумма ТМЦ и услуг контрагенту
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.Kind.ShippedGood") //$NON-NLS-1$
	SHIPPEDGOOD,

	/**
	 * Сумма ТМЦ и услуг от контрагента
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#Contract.Kind.ReceiveGood") //$NON-NLS-1$
	RECEIVEGOOD	
}
