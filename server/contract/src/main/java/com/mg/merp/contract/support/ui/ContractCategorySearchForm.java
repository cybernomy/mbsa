/*
 * ContractCategorySearchForm.java
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
package com.mg.merp.contract.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.contract.model.ContractCategory;

/**
 * SearchForm бизнес-компонента "Категория договора"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ContractCategorySearchForm.java,v 1.1 2007/09/17 12:29:23 alikaev Exp $
 */
public class ContractCategorySearchForm extends AbstractSearchForm {

	private final String INIT_QUERY_TEXT = "select %s from ContractCategory cс %s "; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();	
	private DefaultTableController table;

	private Integer[] selectedIds;

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {

		table = new DefaultTableController( new DefaultMaintenanceEJBQLTableModel(){

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(ContractCategory.class, "Id", "cс.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractCategory.class, "Code", "cс.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(ContractCategory.class, "Name", "cс.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */		
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				super.setSelectedRows(rows);
				selectedIds = new Integer[rows.length];
				for(int i = 0; i < rows.length; i++)
					selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
			}

			/**
			 * формируем sql запрос
			 * 
			 * @return текст sql запроса
			 */
			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);				
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);	
			}

			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}			
		});
		super.doOnRun();
		table.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		if(selectedIds == null)
			return new PersistentObject[0];

		PersistentManager persistentManager = ServerUtils.getPersistentManager();
		PersistentObject[] selectedEntities = new PersistentObject[selectedIds.length];
		for(int i = 0; i < selectedIds.length; i++)
			selectedEntities[i] = persistentManager.find(ContractCategory.class, selectedIds[i]);

		return selectedEntities;
	}

}
