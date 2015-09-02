/*
 * HolidaysBr.java
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
package com.mg.merp.reference.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.HolidaysServiceLocal;
import com.mg.merp.reference.model.Holidays;

/**
 * Браузер праздничных дней
 * 
 * @author leonova
 * @version $Id: HolidaysBr.java,v 1.4 2007/07/30 06:25:58 safonov Exp $ 
 */
public class HolidaysBr extends DefaultPlainBrowseForm{
	private final String INIT_QUERY_TEXT = "select %s from Holidays h %s";
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	
	public HolidaysBr() {
		super();
		restrictionFormName = "com/mg/merp/reference/resources/HolidaysRest.mfd.xml";
	}	
	
	@Override
	protected String createQueryText() {
		String whereText = "";
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);		
		paramsName.clear();
		paramsValue.clear();
		HolidaysRest restForm = (HolidaysRest) getRestrictionForm();
		whereText = " where 0=0 ".concat(DatabaseUtils.formatEJBQLObjectRestriction("h.HYear", restForm.getYear(), "year", paramsName, paramsValue, false)).
		concat(DatabaseUtils.formatEJBQLAddinFieldsRestriction(service, "h.Id", restForm.getAddinFieldsRestriction(), false));		
		return String.format(INIT_QUERY_TEXT, fieldsList, whereText);				
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
				result.add(new TableEJBQLFieldDef(Holidays.class, "Id", "h.Id", true));
				result.add(new TableEJBQLFieldDef(Holidays.class, "HName", "h.HName", false));				
				result.add(new TableEJBQLFieldDef(Holidays.class, "HDate", "h.HDate", false));
				result.add(new TableEJBQLFieldDef(Holidays.class, "HDay", "h.HDay", false));
				result.add(new TableEJBQLFieldDef(Holidays.class, "HMonth", "h.HMonth", false));
				result.add(new TableEJBQLFieldDef(Holidays.class, "HYear", "h.HYear", false));				
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
	
	/**
	 * обработчик копирования праздничных дней на следующий год
	 * 
	 * @param event
	 */
	public void onActionCopyHolidays(WidgetEvent event) {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		((HolidaysServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(HolidaysServiceLocal.SERVICE_NAME))
				.copyHolidays(keys);
		table.refresh();
	}

}
