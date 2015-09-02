/*
 * WeekCalendarBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.mfreference.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.WeekCalendarChangeServiceLocal;
import com.mg.merp.mfreference.model.WeekCalendar;
import com.mg.merp.mfreference.model.WeekCalendarChange;

/**
 * Браузер недельного календаря
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WeekCalendarBr.java,v 1.4 2006/10/07 07:56:30 leonova Exp $
 */
public class WeekCalendarBr extends DefaultMDBrowseForm implements MasterModelListener {
	private final String INIT_QUERY_TEXT = "select %s from WeekCalendar wc";
	private final String INIT_QUERY_TEXT_DETAIL = "select %s from WeekCalendarChange wcc %s where wcc.WeekCal.Id = :weekCal";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public WeekCalendarBr() throws Exception {
		super();
		this.detailService = (WeekCalendarChangeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/WeekCalendarChange");
		master.addMasterModelListener(this);
	}

	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createDetailModel()
	 */
	@Override
	protected MaintenanceTableModel createDetailModel() {
		return new DefaultMaintenanceEJBQLTableModel() {
			
			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("weekCal");
				paramsValue.add(getMasterKey());					
				return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList, fromList);		
			}
			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "Id", "wcc.Id", true));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "EffOnDate", "wcc.EffOnDate", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "EffOffDate", "wcc.EffOffDate", false));				
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalMon", "d1.Code", "left join wcc.DayCalMon as d1", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalTue", "d2.Code", "left join wcc.DayCalTue as d2", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalWed", "d3.Code", "left join wcc.DayCalWed as d3", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalThu", "d4.Code", "left join wcc.DayCalThu as d4", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalFri", "d5.Code", "left join wcc.DayCalFri as d5", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalSat", "d6.Code", "left join wcc.DayCalSat as d6", false));
				result.add(new TableEJBQLFieldDef(WeekCalendarChange.class, "DayCalSun", "d7.Code", "left join wcc.DayCalSun as d7", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, detailService);
	
			}
	
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
	
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createMasterModel()
	 */
	@Override
	protected MaintenanceTableModel createMasterModel() {
		return new DefaultMaintenanceEJBQLTableModel() {
	
			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
								
				return String.format(INIT_QUERY_TEXT, fieldsList);		
			}
			
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(WeekCalendar.class, "Id", "wc.Id", true));
				result.add(new TableEJBQLFieldDef(WeekCalendar.class, "Code", "wc.Code", false));
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, masterService);
	
			}
	
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}
	
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
			
		};
	}
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		detailUIProperties.put("WeekCal.Id", event.getModelKey()); //$NON-NLS-1$
	}
}