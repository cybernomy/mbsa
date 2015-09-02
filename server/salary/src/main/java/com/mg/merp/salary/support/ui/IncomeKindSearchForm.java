/*
 * IncomeKindSearchForm.java
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

import java.util.Set;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.salary.model.IncomeKind;

/**
 * Контроллер формы поиска сущностей "Вид дохода"
 * 
 * @author Artem V. Sharapov
 * @version $Id: IncomeKindSearchForm.java,v 1.1 2007/07/18 08:04:40 sharapov Exp $
 */
public class IncomeKindSearchForm extends AbstractSearchForm {

	private DefaultTableController table;
	private final String INIT_QUERY_TEXT = "select %s from IncomeKind ik"; //$NON-NLS-1$

	private Integer[] selectedIds;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		table = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(IncomeKind.class, "Id", "ik.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncomeKind.class, "ICode", "ik.ICode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncomeKind.class, "IName", "ik.IName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(IncomeKind.class, "BeginDate", "ik.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}

			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);					
				return String.format(INIT_QUERY_TEXT, fieldsList);	
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				selectedIds = new Integer[rows.length];
				for(int i = 0; i < rows.length; i++)
					selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
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
			selectedEntities[i] = persistentManager.find(IncomeKind.class, selectedIds[i]);

		return selectedEntities;
	}

}
