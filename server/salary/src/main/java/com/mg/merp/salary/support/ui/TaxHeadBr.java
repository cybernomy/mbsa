/*
 * TaxHeadBr.java
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

import java.io.Serializable;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.TaxScaleServiceLocal;
import com.mg.merp.salary.model.TaxHead;

/**
 * Контроллер браузера налоговых сеток
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TaxHeadBr.java,v 1.4 2007/07/17 08:36:20 sharapov Exp $ 
 */
public class TaxHeadBr extends DefaultPlainBrowseForm {

	private final String INIT_QUERY_TEXT = "select %s from TaxHead th"; //$NON-NLS-1$


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
				result.add(new TableEJBQLFieldDef(TaxHead.class, "Id", "th.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TaxHead.class, "TCode", "th.TCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TaxHead.class, "TName", "th.TName", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
	 * Обработчик пункта КМ "Налоговые шкалы"
	 * @param event - событие
	 */
	public void onActionShowTaxScale(WidgetEvent event) {
		Serializable[] selectedPrimaryKeys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if(selectedPrimaryKeys != null && selectedPrimaryKeys.length > 0) {
			TaxScaleBr browseForm = (TaxScaleBr) ApplicationDictionaryLocator.locate().getBrowseForm((TaxScaleServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(TaxScaleServiceLocal.LOCAL_SERVICE_NAME), null);
			browseForm.setTaxHeadId(selectedPrimaryKeys[0]);
			browseForm.run();
		}
	}

}

