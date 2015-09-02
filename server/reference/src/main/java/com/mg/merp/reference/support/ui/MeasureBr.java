/*
 * MeasureBr.java
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
package com.mg.merp.reference.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.model.Measure;

/**
 * Браузер единиц измерений
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: MeasureBr.java,v 1.4 2008/05/12 07:23:22 alikaev Exp $ 
 */
public class MeasureBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Measure m order by m.Code ";	
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);		
		return String.format(INIT_QUERY_TEXT, fieldsList);				

	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
	 */
	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Measure.class, "Id", "m.Id", true));
				result.add(new TableEJBQLFieldDef(Measure.class, "Code", "m.Code", false));
				result.add(new TableEJBQLFieldDef(Measure.class, "FullName", "m.FullName", false));				
				result.add(new TableEJBQLFieldDef(Measure.class, "Dividing", "m.Dividing", false));
				result.add(new TableEJBQLFieldDef(Measure.class, "UniversalCode", "m.UniversalCode", false));				
				result.add(new TableEJBQLFieldDef(Measure.class, "InternalCode", "m.InternalCode", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};

	}

}
