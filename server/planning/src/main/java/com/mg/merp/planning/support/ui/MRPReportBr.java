/*
 * MRPReportBr.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.planning.model.MrpReport;
import com.mg.merp.planning.model.MrpVersionControl;
import com.mg.merp.planning.support.Messages;

/**
 * Контроллер формы итогов расчета ППМ
 * 
 * @author Oleg V. Safonov
 * @author leonova
 * @version $Id: MRPReportBr.java,v 1.2 2007/07/30 10:36:31 safonov Exp $ 
 */
public class MRPReportBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from MrpReport mr %s where mr.MrpVersionControl.Id = :mrpId order by mr.MrpVersionControl.MrpVersion, mr.Warehouse.Code, mr.Catalog.Code, mr.RequiredDate, mr.MrpInputOutputFlag, mr.Sequence";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private Integer mrpId;

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("mrpId");
		paramsValue.add(mrpId);
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
				result.add(new TableEJBQLFieldDef(MrpReport.class, "Id", "mr.Id", true));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "Warehouse", "mr.Warehouse.Code", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "Catalog", "mr.Catalog.Code", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "RequiredDate", "mr.RequiredDate", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "MrpOrderType", "mr.MrpOrderType", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "PpReference", "mr.PpReference", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "QtyAvailable", "mr.QtyAvailable", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "MrpQuantity", "mr.MrpQuantity", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "MrpSource", "mr.MrpSource", false));				
				result.add(new TableEJBQLFieldDef(MrpReport.class, "MrpInputOutputFlag", "mr.MrpInputOutputFlag", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "OrderDate", "mr.OrderDate", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "OriginalDate", "mr.OriginalDate", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "OriginalQuantity", "mr.OriginalQuantity", false));
				result.add(new TableEJBQLFieldDef(MrpReport.class, "MrpRescheduleFlag", "mr.MrpRescheduleFlag", false));				
				result.add(new TableEJBQLFieldDef(MrpReport.class, "Sequence", "mr.Sequence", false));
		
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
			
		};

	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		super.doOnRun();
		view.getWidget("table").getPopupMenu().getMenuItem(MaintenanceTable.VIEW_MENU_ITEM).setEnabled(false);
	}

	/**
	 * Показать форму итогов расчета ППМ
	 * 
	 * @param mrpId	версия ППМ
	 */
	public void execute(Serializable mrpId) {
		this.mrpId = (Integer) mrpId;
		
		MrpVersionControl mrpVersion = ServerUtils.getPersistentManager().find(MrpVersionControl.class, mrpId);
		setTitle(Messages.getInstance().getMessage(Messages.MRP_REPORT_TITLE, new Object[] {mrpVersion.getCode().trim()}));

		run();		
	}

}
