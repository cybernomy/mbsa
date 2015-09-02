/*
 * DocSpecSearchForm.java
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
package com.mg.merp.lbschedule.support.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;

/**
 * Контроллер формы поиска объектов-сущностей "Позиций cпецификации документа"
 * 
 * @author Artem V. Sharapov
 * @version $Id: DocSpecSearchForm.java,v 1.3 2009/02/09 12:08:32 safonov Exp $
 */
public class DocSpecSearchForm extends AbstractSearchForm {
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();
	private final String INIT_QUERY_TEXT = "select %s from DocSpec ds %s where ds.DocHead = :docHead"; //$NON-NLS-1$
	private DefaultTableController docSpecTable;
	private Serializable[] selectedIDs = null;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {

		docSpecTable = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Id", "ds.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.Code", "cat.Code", "left join ds.Catalog cat", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Catalog.FullName", "cat.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure1", "m1.Code", "left join ds.Measure1 m1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity", "ds.Quantity", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Measure2", "m2.Code", "left join ds.Measure2 m2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Quantity2", "ds.Quantity2", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(DocSpec.class, "Comment", "ds.Comment", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return result;
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) docSpecTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);				
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				selectedIDs = new Serializable[rows.length];
				for (int i = 0; i < rows.length; i++)
					selectedIDs[i] = (Serializable) getRowList().get(rows[i])[0];
			}

		});
		docSpecTable.getModel().load();
		showForm();	
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		int len = selectedIDs == null ? 0 : selectedIDs.length;
		DocSpec[] selectedEntities = new DocSpec[len];
		for (int i = 0; i < len; i++)
			selectedEntities[i] = ServerUtils.getPersistentManager().find(DocSpec.class, selectedIDs[i]);
		return selectedEntities;
	}

	public void setSearchParams(DocHead docHead) {
		paramsName.clear();
		paramsValue.clear();
		paramsName.add("docHead"); //$NON-NLS-1$
		paramsValue.add(docHead);			
	}

}
