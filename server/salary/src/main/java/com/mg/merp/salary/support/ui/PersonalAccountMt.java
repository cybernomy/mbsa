/*
 * StaffListPersonalAccountMt.java
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
package com.mg.merp.salary.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.StaffListPosition;
import com.mg.merp.salary.FeeModelServiceLocal;
import com.mg.merp.salary.PositionFillServiceLocal;
import com.mg.merp.salary.model.FeeModel;

/**
 * Контроллер формы поддерки лицевых счетов сотрудников
 * 
 * @author leonova
 * @version $Id: PersonalAccountMt.java,v 1.8 2007/07/09 13:58:45 sharapov Exp $
 */
public class PersonalAccountMt extends DefaultMaintenanceForm implements MasterModelListener {
	
	private MaintenanceTableController positionFill;
	private PositionFillServiceLocal positionFillService;
	protected AttributeMap positionFillProperties = new LocalDataTransferObject();

	private MaintenanceTableController fee;
	private FeeModelServiceLocal feeService;	
	protected AttributeMap feeProperties = new LocalDataTransferObject();

	
	public PersonalAccountMt() throws Exception{
		setMasterDetail(true);
		
		positionFillService = (PositionFillServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/PositionFill");
		positionFill = new MaintenanceTableController(positionFillProperties);
		positionFill.initController(positionFillService, new DefaultMaintenanceEJBQLTableModel() {
			
			private final String INIT_QUERY_TEXT = "select %s from PositionFill pf %s where pf.PersonalAccount = :personalAccount";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("personalAccount");
				paramsValue.add(getEntity());					
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
				result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.Id", true));
				//result.add(new TableEJBQLFieldDef(PositionFill.class, "Position", "p.Name", "left join pf.Position as p", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "SlPositionUnique", "posUniq.SlPositionUniqueId", "left join pf.SlPositionUnique posUniq", false));
				result.add(new TableEJBQLFieldDef(StaffListPosition.class, "Position", "pos.Name", "left join posUniq.Position pos", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "PositionFillKind", "pfk.KCode", "left join pf.PositionFillKind as pfk", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "BeginDate", "pf.BeginDate", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "EndDate", "pf.EndDate", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "RateNumber", "pf.RateNumber", false));
				result.add(new TableEJBQLFieldDef(PositionFill.class, "IsBasic", "pf.IsBasic", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, positionFillService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(positionFill);

		feeService = (FeeModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/FeeModel");
		fee = new MaintenanceTableController(feeProperties);
		fee.initController(feeService, new DefaultMaintenanceEJBQLTableModel() {		
			
			private final String INIT_QUERY_TEXT = "select %s from FeeModel fm %s where fm.PersonalAccount = :personalAccount";
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("personalAccount");
				paramsValue.add(getEntity());					
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
				result.add(new TableEJBQLFieldDef(FeeModel.class, "Id", "fm.Id", true));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef", "fr.FCode", "left join fm.FeeRef as fr", true));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "BeginDate", "fm.BeginDate", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "EndDate", "fm.EndDate", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "Summa", "fm.Summa", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "RollKind", "rk.Name", "left join fm.RollKind as rk", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.SumSign", "fr.SumSign", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.FeeType", "fr.FeeType", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "PositionFill", "pos.Name", "left join fm.PositionFill as pf left join pf.SlPositionUnique posUniq left join posUniq.Position pos", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.CalcAlg", "alg.Code", "left join fr.CalcAlg as alg", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.PeriodiCity", "fr.PeriodiCity", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl1", "anl1.ACode", "left join fm.CostsAnl1 as anl1", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl2", "anl2.ACode", "left join fm.CostsAnl2 as anl2", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl3", "anl3.ACode", "left join fm.CostsAnl3 as anl3", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl4", "anl4.ACode", "left join fm.CostsAnl4 as anl4", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl5", "anl5.ACode", "left join fm.CostsAnl5 as anl5", false));
				result.add(new TableEJBQLFieldDef(FeeModel.class, "CalcPeriod", "cp.PName", "left join fm.CalcPeriod as cp", false));				
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, feeService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(fee);
		addMasterModelListener(this);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		positionFillProperties.put("PersonalAccount", event.getEntity());
		feeProperties.put("PersonalAccount", event.getEntity());		
	}

}
