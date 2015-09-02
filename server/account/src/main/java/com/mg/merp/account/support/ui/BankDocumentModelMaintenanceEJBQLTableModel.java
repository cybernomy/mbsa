/*
 * BankDocumentModelMaintenanceEJBQLTableModel.java
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
package com.mg.merp.account.support.ui;

import java.util.Set;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.model.BankDocumentModel;
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;

/**
 * ¬спомогательный класс дл€ отображени€ формы списка образцов банковских документов
 * 
 * @author leonova
 * @version $Id: BankDocumentModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 11:28:01 leonova Exp $ 
 */
public class BankDocumentModelMaintenanceEJBQLTableModel extends
		DocModelMaintenanceEJBQLTableModel {	
	
	/* (non-Javadoc)
	 * @see com.mg.merp.document.support.ui.GoodDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		super.getDefaultFieldDefsSet();		
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "Nds1Rate", "dhm.Nds1Rate", false));
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "Nds2Rate", "dhm.Nds2Rate", false));		
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "Nds1Summa", "dhm.Nds1Summa", false));
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "Nds2Summa", "dhm.Nds2Summa", false));
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "SummaWithNds1", "dhm.SummaWithNds1", false));			
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "SummaWithNds2", "dhm.SummaWithNds2", false));	
		result.add(new TableEJBQLFieldDef(BankDocumentModel.class, "Comment", "dhm.Comment", false));		
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

	}

}
