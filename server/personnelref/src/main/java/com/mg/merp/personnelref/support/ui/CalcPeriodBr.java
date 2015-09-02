/*
 * CalcPeriodBr.java
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
package com.mg.merp.personnelref.support.ui;

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.model.CalcPeriod;

/**
 * Контроллер браузера "Расчетные периоды"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcPeriodBr.java,v 1.2 2007/07/09 08:07:46 sharapov Exp $ 
 */
public class CalcPeriodBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from CalcPeriod c %s"; //$NON-NLS-1$


	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);					
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);	
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
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "Id", "c.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "PName", "c.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "BeginDate", "c.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "EndDate", "c.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(CalcPeriod.class, "StaffList", "sl.LName", "left join c.StaffList as sl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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

	/**
	 * Обработчик пункта КМ "Установить текущий расчетный период"
	 * @param event - событие
	 */
	public void onActionSetCurrentCalcPeriod(WidgetEvent event) {
		Serializable[] selectedPrimaryKeys = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getSelectedPrimaryKeys();
		if(selectedPrimaryKeys != null && selectedPrimaryKeys.length > 0)
			getCalcPeriodService().setCurrentCalcPeriod((Integer) selectedPrimaryKeys[0]);
	}
	
	private CalcPeriodServiceLocal getCalcPeriodService() {
		return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
	}

}

