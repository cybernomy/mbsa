/**
 * EntityAuditSetupWizard.java
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
package com.mg.merp.core.support.ui;

import java.util.List;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.DatabaseAuditServiceLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.merp.core.DatabaseAuditSetupServiceLocal;
import com.mg.merp.core.EntityAuditItem;
import com.mg.merp.core.PropertyAuditItem;

/**
 * Контроллер формы настройки аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityAuditSetupWizard.java,v 1.2 2008/05/29 08:38:35 safonov Exp $
 */
public class EntityAuditSetupWizard extends AbstractForm {
	@SuppressWarnings("unused")
	private DefaultTableController entities;
	private DefaultTableController properties;
	private List<EntityAuditItem> entityList;
	private List<PropertyAuditItem> propertyList;
	private EntityAuditItem currentEntityAuditItem = null;
	private DatabaseAuditSetupServiceLocal setupService;
	@DataItemName("Core.DatabaseAuditSetup.AuditCreate")
	private boolean auditCreate = false;
	@DataItemName("Core.DatabaseAuditSetup.AuditModify")
	private boolean auditModify = false;
	@DataItemName("Core.DatabaseAuditSetup.AuditRemove")
	private boolean auditRemove = false;
	
	public EntityAuditSetupWizard() {
		entities = new DefaultTableController(new EntityTableModel());
		properties = new DefaultTableController(new PropertyTableModel());
	}
	
	private DatabaseAuditSetupServiceLocal getSetupService() {
		if (setupService == null)
			setupService = (DatabaseAuditSetupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(DatabaseAuditSetupServiceLocal.SERVICE_NAME);
		return setupService;
	}
	
	private class EntityTableModel extends AbstractTableModel {
		private String[] columnsName;

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doSetColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
		 */
		@Override
		protected void doSetColumns(TableColumnModel[] columns) {
			this.columnsName = new String[columns.length];
			for (int i = 0; i < columns.length; i++)
				this.columnsName[i] = columns[i].getTitle();
		}

		public int getColumnCount() {
			return columnsName.length;
		}

		public String getColumnName(int column) {
			return columnsName[column];
		}

		public int getRowCount() {
			return entityList.size();
		}

		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return entityList.get(row).getEntityName();
			case 1:
				return entityList.get(row).getI18nName();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
		 */
		@Override
		public void setSelectedRows(int[] rows) {
			if (rows.length > 0) {
				currentEntityAuditItem = entityList.get(rows[0]);
				auditCreate = currentEntityAuditItem.getAuditCreate();
				auditModify = currentEntityAuditItem.getAuditModify();
				auditRemove = currentEntityAuditItem.getAuditRemove();
				view.flushModel();
				loadPropertySetup();
			}
		}
		
	}
	
	private class PropertyTableModel extends AbstractTableModel {
		private static final int AUDIT_COLUMN = 2;
		private String[] columnsName;

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doSetColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
		 */
		@Override
		protected void doSetColumns(TableColumnModel[] columns) {
			this.columnsName = new String[columns.length];
			for (int i = 0; i < columns.length; i++)
				this.columnsName[i] = columns[i].getTitle();
		}

		public int getColumnCount() {
			return columnsName.length;
		}

		public String getColumnName(int column) {
			return columnsName[column];
		}

		public int getRowCount() {
			return propertyList == null ? 0 : propertyList.size();
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int column) {
			if (column == AUDIT_COLUMN)
				return Boolean.class;
			else
				return null;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int column) {
			return column == AUDIT_COLUMN;
		}

		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return propertyList.get(row).getPropertyName();
			case 1:
				return propertyList.get(row).getI18nName();
			case 2:
				return propertyList.get(row).getAudit();
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#setValueAt(java.lang.Object, int, int)
		 */
		@Override
		public void setValueAt(Object value, int row, int column) {
			if (currentEntityAuditItem == null)
				return;
			if (column == AUDIT_COLUMN) {
				PropertyAuditItem item = propertyList.get(row);
				getSetupService().setAuditModify(currentEntityAuditItem.getEntityName(), item.getPropertyName(), (Boolean) value);
				//change for UI
				item.setAudit((Boolean) value);
				currentEntityAuditItem.setAuditModify(false);
				auditModify = false;
				view.flushModel();
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
		 */
		@Override
		protected void doLoad() {
			propertyList = getSetupService().loadPropertyAudit(currentEntityAuditItem.getEntityName());
			fireModelChange();
		}

	}
	
	private void loadPropertySetup() {
		properties.getModel().load();		
	}
	
	private void loadEntitySetup() {
		entityList = getSetupService().loadEntityAudit();
	}
	
	protected void onActionCheckCreate(WidgetEvent event) {
		if (currentEntityAuditItem == null)
			return;
		getSetupService().setAuditCreate(currentEntityAuditItem.getEntityName(), auditCreate);
		currentEntityAuditItem.setAuditCreate(auditCreate);
	}

	protected void onActionCheckModify(WidgetEvent event) {
		if (currentEntityAuditItem == null)
			return;
		getSetupService().setAuditModify(currentEntityAuditItem.getEntityName(), auditModify);
		currentEntityAuditItem.setAuditModify(auditModify);
		//загрузим аудит для свойств заново, т.к. они будут сброшены
		loadPropertySetup();
	}

	protected void onActionCheckRemove(WidgetEvent event) {
		if (currentEntityAuditItem == null)
			return;
		getSetupService().setAuditRemove(currentEntityAuditItem.getEntityName(), auditRemove);
		currentEntityAuditItem.setAuditRemove(auditRemove);
	}

	protected void onActionApply(WidgetEvent event) {
		DatabaseAuditServiceLocator.locate().applyAuditSetup();
	}

	protected void onActionOk(WidgetEvent event) {
		close();
	}

	/**
	 * запуск формы
	 */
	public void execute() {
		loadEntitySetup();
		run();
	}
	
}
