/*
 * WarehouseBr.java
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
package com.mg.merp.warehouse.support.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehouseServiceLocal;
import com.mg.merp.warehouse.WarehouseTransactionDayServiceLocal;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.Messages;

/**
 * Браузер складов
 * 
 * @author leonova
 * @version $Id: WarehouseBr.java,v 1.10 2008/10/15 11:58:41 safonov Exp $ 
 */
public class WarehouseBr extends DefaultPlainBrowseForm {
	private final String INIT_QUERY_TEXT = "select %s from Warehouse w %s where %s order by w.UpCode";	 //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);			
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, DatabaseUtils.generateFlatBrowseWhereEJBQL("w.Id", 4)); //$NON-NLS-1$
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
				result.add(new TableEJBQLFieldDef(Warehouse.class, "Id", "w.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "Code", "w.Code", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "FullName", "w.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "WarehouseType", "wt", "left join w.WarehouseType as wt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "StockPolicy", "w.StockPolicy", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "CheckTransactionDay", "w.CheckTransactionDay", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "WarehouseTransactionClosed", "w.WarehouseTransactionClosed", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "ClosedDateTill", "w.ClosedDateTill", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "UserStockClosed", "w.UserStockClosed", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Warehouse.class, "OperationDate", "w.OperationDate", false));				 //$NON-NLS-1$ //$NON-NLS-2$
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
	
	public void onActionShowWarehouseCard(WidgetEvent event) throws Exception {
		final WareCardServiceLocal service = (WareCardServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
		WareCardBr form = (WareCardBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys != null)
			form.execute(keys[0]);
	
	}
	
	/**
	 * Обработчик события показать операционные дни склада
	 * @param event
	 * @throws Exception
	 */
	public void onActionShowWarehouseTransactionDay(WidgetEvent event) throws Exception {
		WarehouseTransactionDayServiceLocal wtdService = (WarehouseTransactionDayServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseTransactionDayServiceLocal.SERVICE_NAME);
		WarehouseTransactionDayBr form =(WarehouseTransactionDayBr) ApplicationDictionaryLocator.locate().getBrowseForm(wtdService, null);
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys != null)
			form.execute(keys[0]);
	}
	
	public void onActionShowBinLocation(WidgetEvent event) throws Exception {
		final BinLocationServiceLocal service = (BinLocationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(BinLocationServiceLocal.LOCAL_SERVICE_NAME);
		BinLocationBr form = (BinLocationBr)ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		if (keys != null)
			form.execute(keys[0]);
	}
	
	/**
	 * Обработчик события "Закрыть склад"
	 * @param event
	 * @throws Exception
	 */
	public void onActionCloseWarehouse(WidgetEvent event) throws Exception {
		Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.READ_DATE), Messages.getInstance().getMessage(Messages.CHOOSE_CLOSE_DATE_TILL)
				, DateTimeUtils.nowDate(), new InputQueryDialogListener<Date>() {

			public void inputCanceled() {			
			}

			public void inputPerformed(Date date) {
				Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
				WarehouseServiceLocal whService = (WarehouseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);
				whService.closeWarehouse(keys, date);
				table.refresh();
			}
		});
	}
	
	/**
	 * Обработчик события "Открыть склад"
	 * @param event
	 * @throws Exception
	 */
	public void onActionOpenWarehouse(WidgetEvent event) throws Exception {
		Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
		WarehouseServiceLocal whService = (WarehouseServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseServiceLocal.LOCAL_SERVICE_NAME);
		whService.openWarehouse(keys);
		table.refresh();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createTableController()
	 */
	@Override
	protected MaintenanceTableController createTableController() {
		MaintenanceTableController result = new WarehouseMaintenanceTableController(this.uiProperties);
		result.addMasterModelListener(this);
		return result;
	}

}
