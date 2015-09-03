/*
 * BinLocationBr.java
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
package com.mg.merp.warehouse.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.Messages;

/**
 * @author leonova
 * @version $Id: BinLocationBr.java,v 1.4 2007/07/02 15:15:35 poroxnenko Exp $ 
 */
public class BinLocationBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from BinLocation bl %s where bl.Warehouse.Id = :warehouseId";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Serializable warehouseId;

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("warehouseId");
		paramsValue.add(warehouseId);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				

	}

	@Override
	protected MaintenanceTableModel createModel() {
		return new DefaultMaintenanceEJBQLTableModel() {

			private TableEJBQLFieldDef[] fields;
			
			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(BinLocation.class, "Id", "bl.Id", true));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "Code", "bl.Code", false));				
				result.add(new TableEJBQLFieldDef(BinLocation.class, "Description", "bl.Description", false));	
				result.add(new TableEJBQLFieldDef(BinLocation.class, "Type", "t.Code", "left join bl.Type as t", false));					
				result.add(new TableEJBQLFieldDef(BinLocation.class, "Zone", "z.Code", "left join bl.Zone as z", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "VolumeMeasure", "vm.Code", "left join bl.VolumeMeasure as vm", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteVolume", "bl.InfiniteVolume", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumVolume", "bl.MaximumVolume", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "WeightMeasure", "wm.Code", "left join bl.WeightMeasure as wm", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteWeight", "bl.InfiniteWeight", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumWeight", "bl.MaximumWeight", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "QuanMeasure", "qm.Code", "left join bl.QuanMeasure as qm", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "InfiniteQuan", "bl.InfiniteQuan", false));
				result.add(new TableEJBQLFieldDef(BinLocation.class, "MaximumQuan", "bl.MaximumQuan", false));				

				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
			@Override
			protected void createColumnsModel() {
				super.createColumnsModel();
				fields = new TableEJBQLFieldDef[getFieldDefsSet().size()];
				getFieldDefsSet().toArray(fields);
			}
			
			@Override
			public String getColumnName(int column) {
				if ("VolumeMeasure".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_VOL_MEAS);
				else if ("WeightMeasure".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_WEIGHT_MEAS);
				else if ("QuanMeasure".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_QUAN_MEAS);
				else if ("InfiniteVolume".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_VOL_INFINITE);
				else if ("InfiniteWeight".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_WEIGHT_INFINITE);
				else if ("InfiniteQuan".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_QUAN_INFINITE);
				else if ("MaximumVolume".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_VOL_MAX);
				else if ("MaximumWeight".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_WEIGHT_MAX);
				else if ("MaximumQuan".equals(fields[column].getEntityPropertyName()))
					return Messages.getInstance().getMessage(Messages.BROWS_TITLE_QUAN_MAX);
				else return super.getColumnName(column);
			}
		};

	}

	/**
	 * показать форму
	 * 
	 * @param warehouseId
	 */
	public void execute(Serializable warehouseId) {
		this.warehouseId = warehouseId;
		Warehouse warehouse = ServerUtils.getPersistentManager().find(Warehouse.class, warehouseId);
		uiProperties.put("Warehouse.Id", (Integer)warehouseId); //$NON-NLS-1$;
		setTitle(Messages.getInstance().getMessage(Messages.BINLOCATION_BR_TITLE, new String[]{warehouse.getFullName().trim()}));
		run();	
	}
}
