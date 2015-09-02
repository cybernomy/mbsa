/*
 * CustomerSelectDlg.java
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
import com.mg.merp.reference.model.Contractor;

/**
 * Контроллер диалога "Выбор покупателя"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomerSelectDlg.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class CustomerSelectDlg extends DefaultDialog {
	
	private DefaultTableController table;
	
	public CustomerSelectDlg() {
		table = new DefaultTableController(new CustomerSelectTableModel());
	}
	
	public void executeDlg(List<CustomerSelectTableModelItem> tableModelItems) {
		((CustomerSelectTableModel) table.getModel()).setTableList(tableModelItems);
		this.execute();
	}
	
	public Contractor getSelectedCustomer() {
		return ((CustomerSelectTableModel) table.getModel()).getSelectedCustomer();
	}
	
}
