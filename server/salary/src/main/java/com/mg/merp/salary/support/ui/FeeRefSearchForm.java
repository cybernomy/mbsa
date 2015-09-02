/*
 * FeeRefSearchForm.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.salary.model.FeeRef;

/**
 * Контроллер формы поиска сущностей "Начисление/удержание"
 * 
 * @author Artem V. Sharapov
 * @version $Id: FeeRefSearchForm.java,v 1.1 2007/07/09 08:33:47 sharapov Exp $
 */
public class FeeRefSearchForm extends AbstractSearchForm {

	private DefaultTableController table;
	private final String INIT_QUERY_TEXT = "select %s from FeeRef fr %s"; //$NON-NLS-1$

	private Integer selectedId;


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
				result.add(new TableEJBQLFieldDef(FeeRef.class, "Id", "fr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "FeeType", "fr.FeeType", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CalcListSectionRef", "csr.SName", "left join fr.CalcListSectionRef as csr", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "FCode", "fr.FCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "FName", "fr.FName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CalcAlg", "ca.Code", "left join fr.CalcAlg as ca", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "GnsCode", "gc.Code", "left join fr.GnsCode as gc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "Priority", "fr.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "SumSign", "fr.SumSign", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "BeginDate", "fr.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "EndDate", "fr.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "PeriodiCity", "fr.PeriodiCity", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl1", "anl1.ACode", "left join fr.CostsAnl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl2", "anl2.ACode", "left join fr.CostsAnl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl3", "anl3.ACode", "left join fr.CostsAnl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl4", "anl4.ACode", "left join fr.CostsAnl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "CostsAnl5", "anl5.ACode", "left join fr.CostsAnl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "RollKind", "rk.Name", "left join fr.RollKind as rk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "IncomeKind", "ik.ICode", "left join fr.IncomeKind as ik", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(FeeRef.class, "IsZeroIncluded", "fr.IsZeroIncluded", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					selectedId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					selectedId = (Integer) row[0];
				}
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
		if(selectedId == null)
			return new PersistentObject[0];
		else 
			return new PersistentObject[] {ServerUtils.getPersistentManager().find(FeeRef.class, selectedId)};
	}

}
