/*
 * ProductionProfileBr.java
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
package com.mg.merp.planning.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.planning.model.ProductionProfile;

/**
 * Браузер профилей производства
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProductionProfileBr.java,v 1.3 2007/01/15 12:28:46 sharapov Exp $ 
 */
public class ProductionProfileBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from ProductionProfile pp %s";
	
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "Id", "pp.Id", true));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "StartBucketOffset", "pp.StartBucketOffset", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "EndBucketOffset", "pp.EndBucketOffset", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "BucketProductionRatio", "pp.BucketProductionRatio", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "ProductionRatio", "pp.ProductionRatio", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "PlanningLevel", "pl.PlanningLevelNum", "left join pp.PlanningLevel as pl", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "GenericItem.GenericItemCode", "gi.GenericItemCode", "left join pp.GenericItem as gi", true));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "GenericItem.GenericItemName", "gi.GenericItemName", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "StartBucketStartDate", "pp.StartBucketStartDate", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "EndBucketStartDate", "pp.EndBucketStartDate", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "StartBucketEndDate", "pp.StartBucketEndDate", false));
				result.add(new TableEJBQLFieldDef(ProductionProfile.class, "EndBucketEndDate", "pp.EndBucketEndDate", false));				
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
