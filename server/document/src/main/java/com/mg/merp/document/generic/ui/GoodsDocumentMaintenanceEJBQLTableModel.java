/*
 * GoodsDocumentMaintenanceEJBQLTableModel.java
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
package com.mg.merp.document.generic.ui;

import java.util.Set;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.document.model.DocHead;

/**
 * Базовый класс контроллера формы списка товарных документов
 * 
 * @author leonova
 * @version $Id: GoodsDocumentMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:04:52 safonov Exp $ 
 */
public class GoodsDocumentMaintenanceEJBQLTableModel extends
		DocumentMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.support.ui.DocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(DocHead.class, "From", "d.From.Code", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "To", "d.To.Code", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "DstStock", "dst.Code", "left join d.DstStock as dst", false));
		result.add(new TableEJBQLFieldDef(DocHead.class, "DstMol", "dm.Code", "left join d.DstMol as dm", false));		
		return result;
	}

}
