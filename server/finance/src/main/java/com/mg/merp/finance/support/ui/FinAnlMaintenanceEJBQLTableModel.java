/*
 * AnlPlantMaintenanceEJBQLTableModel.java
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
package com.mg.merp.finance.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.finance.FeatAnalyticsServiceLocal;
import com.mg.merp.finance.model.Analytics;

/**
 * @author leonova
 * @version $Id: FinAnlMaintenanceEJBQLTableModel.java,v 1.3 2007/01/18 13:59:38 safonov Exp $ 
 */
public class FinAnlMaintenanceEJBQLTableModel extends
		DefaultMaintenanceEJBQLTableModel {
	protected final String INIT_QUERY_TEXT = "select %s from Analytics anl %s where anl.FinAcc = :finacc and anl.AnlLevel = :anlLevel";
	protected List<String> paramsName = new ArrayList<String>();
	protected List<Object> paramsValue = new ArrayList<Object>();
	protected FeatAnalyticsServiceLocal service;
	protected String fieldsList;
	protected String fromList;
	
	protected String createQueryText() {
			
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
	 */
	@Override
	protected int getPrimaryKeyFieldIndex() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(Analytics.class, "Id", "anl.Id", true));
		result.add(new TableEJBQLFieldDef(Analytics.class, "Code", "anl.Code", false));
		result.add(new TableEJBQLFieldDef(Analytics.class, "AnlName", "anl.AnlName", false));
		result.add(new TableEJBQLFieldDef(Analytics.class, "Parent", "par.Code", "left join anl.Parent as par", false));
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
	}
	
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected void doLoad() {
		setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
	}


}
