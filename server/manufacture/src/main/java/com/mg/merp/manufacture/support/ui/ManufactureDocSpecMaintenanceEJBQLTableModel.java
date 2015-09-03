/*
 * ImputDocSpecMaintenanceEJBQLTableModel.java
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
package com.mg.merp.manufacture.support.ui;

import java.util.Set;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocSpec;

/**
 * Вспомогательный класс для отображения формы спецификации производственных документов
 * 
 * @author leonova
 * @version $Id: ManufactureDocSpecMaintenanceEJBQLTableModel.java,v 1.2 2008/12/25 10:17:46 safonov Exp $ 
 */
public class ManufactureDocSpecMaintenanceEJBQLTableModel extends
		GoodsDocSpecMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Price1", "ds.Price1", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa1", "ds.Summa1", false));		
		result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcStock", "srcst.Code", "left join ds.SrcStock as srcst", false));	
		result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcMol", "srcm.Code", "left join ds.SrcMol as srcm", false));	
		result.add(new TableEJBQLFieldDef(DocSpec.class, "DstStock", "dstst.Code", "left join ds.DstStock as dstst", false));	
		result.add(new TableEJBQLFieldDef(DocSpec.class, "DstMol", "dstm.Code", "left join ds.DstMol as dstm", false));			
		return result;
	}

}
