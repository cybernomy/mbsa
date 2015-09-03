/*
 * TariffScaleClassSearchForm.java
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.TariffScale;
import com.mg.merp.personnelref.model.TariffScaleClass;

/**
 * Контроллер формы поиска сущностей "Разряд тарифной сетки"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TariffScaleClassSearchForm.java,v 1.1 2007/07/18 11:12:13 sharapov Exp $
 */
public class TariffScaleClassSearchForm extends AbstractSearchForm {

	private DefaultTableController masterTable;
	private final String MASTER_INIT_QUERY_TEXT = "select %s from TariffScale ts %s"; //$NON-NLS-1$

	private DefaultTableController detailTable;
	private final String DETAIL_INIT_QUERY_TEXT = "select %s from TariffScaleClass tsc where tsc.TariffScale.Id = :tariffScaleId"; //$NON-NLS-1$
	private List<String> detailParamsName = new ArrayList<String>();
	private List<Object> detailParamsValue = new ArrayList<Object>();

	private Integer[] detailSelectedIds;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		masterTable = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TariffScale.class, "Id", "ts.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SCode", "ts.SCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SName", "ts.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScale.class, "SType", "ts.SType", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScale.class, "BeginDate", "ts.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScale.class, "FirstClassAlg", "alg.Code", "left join ts.FirstClassAlg as alg", false));				 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) masterTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(MASTER_INIT_QUERY_TEXT, fieldsList, fromList);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length != 0) {
					detailParamsName.clear();
					detailParamsValue.clear();
					detailParamsName.add("tariffScaleId"); //$NON-NLS-1$
					detailParamsValue.add(getRowList().get(rows[0])[0]);
					detailTable.getModel().load();
				}
			}
		});

		detailTable = new DefaultTableController(new DefaultEJBQLTableModel() {

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Id", "tsc.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "ClassNumber", "tsc.ClassNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Factor", "tsc.Factor", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(TariffScaleClass.class, "Rate", "tsc.Rate", false)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), detailParamsName.toArray(new String[detailParamsName.size()]), detailParamsValue.toArray(new Object[detailParamsValue.size()]));
			}

			private String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) detailTable.getModel()).getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				return String.format(DETAIL_INIT_QUERY_TEXT, fieldsList, fromList);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				detailSelectedIds = new Integer[rows.length];
				for(int i = 0; i < rows.length; i++)
					detailSelectedIds[i] = (Integer) getRowList().get(rows[i])[0];
			}
		}); 
		super.doOnRun();
		masterTable.getModel().load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
	 */
	@Override
	protected PersistentObject[] getSearchedEntities() {
		if(detailSelectedIds == null)
			return new PersistentObject[0];

		PersistentManager persistentManager = ServerUtils.getPersistentManager();
		PersistentObject[] selectedEntities = new PersistentObject[detailSelectedIds.length];
		for(int i = 0; i < detailSelectedIds.length; i++)
			selectedEntities[i] = persistentManager.find(TariffScaleClass.class, detailSelectedIds[i]);

		return selectedEntities;
	}

}
