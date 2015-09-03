/*
 * PeriodBr.java
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
package com.mg.merp.account.support.ui;

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.PeriodServiceLocal;
import com.mg.merp.account.model.Period;

/**
 * Браузер бухгалтерских периодов
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PeriodBr.java,v 1.2 2006/12/26 10:10:10 sharapov Exp $ 
 */
public class PeriodBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Period p ";	 //$NON-NLS-1$

	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);		
		return String.format(INIT_QUERY_TEXT, fieldsList);				

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
				result.add(new TableEJBQLFieldDef(Period.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "DateFrom", "p.DateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "DateTo", "p.DateTo", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "DateClose", "p.DateClose", false));	 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Period.class, "WhoClosed", "p.WhoClosed", false));				 //$NON-NLS-1$ //$NON-NLS-2$
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
	public void onActionOpenAccPeriod(WidgetEvent event) {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		PeriodServiceLocal service = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/period"); //$NON-NLS-1$
		service.openPeriod(keys);
		table.refresh();
	}

	/**
	 * Обработка события контекстного меню: закрытие фин. периода
	 * @param event
	 */
	public void onActionCloseAccPeriod(WidgetEvent event) {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		PeriodServiceLocal service = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/period"); //$NON-NLS-1$
		service.closePeriod(keys);
		table.refresh();
	}

}
