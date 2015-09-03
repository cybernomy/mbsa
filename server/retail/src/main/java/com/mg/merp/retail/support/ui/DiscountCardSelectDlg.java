/*
 * DiscountCardSelectDlg.java
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
package com.mg.merp.retail.support.ui;

import java.util.List;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.discount.model.Card;

/**
 * Контроллер диалога "Выбор дисконтной карты"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DiscountCardSelectDlg.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class DiscountCardSelectDlg extends DefaultDialog {
	
	private DefaultTableController table;
	
	
	public DiscountCardSelectDlg() {
		table = new DefaultTableController(new DisCardSelectTableModel());
	}
		
	public void executeDlg(List<Card> cardsList) {
		((DisCardSelectTableModel) table.getModel()).setTableList(cardsList);
		this.execute();
	}
	
	public Card getSelectedItem() {
		return ((DisCardSelectTableModel) table.getModel()).getSelectedItem();
	}
	
}
