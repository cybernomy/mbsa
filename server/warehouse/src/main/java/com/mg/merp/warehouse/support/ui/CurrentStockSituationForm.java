/*
 * CurrentStockSituationDialog.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.warehouse.support.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.math.Constants;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.reference.CurrentStockSituationLocator;
import com.mg.merp.reference.StockSituationValues;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.Messages;
import com.mg.merp.warehouse.support.StockSituationValuesImpl;

/**
 * Контроллер формы обзора количества на складах
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: CurrentStockSituationForm.java,v 1.3 2008/01/14 15:04:52 safonov Exp $
 */
public class CurrentStockSituationForm extends AbstractForm {

	public static final String FORM_PATH = "com/mg/merp/warehouse/resources/CurrentStockSituationForm.mfd.xml";

	protected DefaultTableController stockSituation1;
	
	protected DefaultTableController stockSituation2;

	private List<StockSituationValues> values;
	
	private Catalog catalog;

	/**
	 * Модель таблицы, отображающей количество на складах
	 */
	private class AvailableInfoModel extends AbstractTableModel {

		private String[] columnsName = new String[] {
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_WAREHOUSE),
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_AVAILABLE),
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_LOCATED),
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_PLAN_RECEIPT),
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_PLAN_ISSUE),
					Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_RESERVED)
					
		};

		private boolean isBase;

		AvailableInfoModel(boolean isBase) {
			this.isBase = isBase;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
		 */
		public String getColumnName(int column) {
			return columnsName[column];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int,
		 *      int)
		 */
		public Object getValueAt(int row, int column) {
			StockSituationValues value = values.get(row);
			switch (column) {
			case 0:
				return value.getWarehouse().getCode().trim();
			case 1:
				return isBase ? value.getAvailable1() : value.getAvailable2();
			case 2:
				return isBase ? value.getLocated1() : value.getLocated2();
			case 3:
				return isBase ? value.getPlanningReceipt1() : value.getPlanningReceipt2();
			case 4:
				return isBase ? value.getPlanningIssue1() : value.getPlanningIssue2();
			case 5:
				return isBase ? value.getReserved1() : value.getReserved2();
			default:
				return null;
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
		 */
		public int getColumnCount() {
			return columnsName.length;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
		 */
		public int getRowCount() {
			return values.size();
		}

	}
	
	public void execute(Catalog catalog) {
		this.catalog = catalog;
		setTitle(Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_TITLE, new Object[] {catalog.getCode().trim(), catalog.getFullName().trim()}));
		loadStockSituation();
		stockSituation1 = new DefaultTableController(new AvailableInfoModel(true));
		stockSituation2 = new DefaultTableController(new AvailableInfoModel(false));
		super.run(UIUtils.isModalMode());
	}
	
	private void loadStockSituation() {
		values = calculateTotal(CurrentStockSituationLocator.locate().getSituation(catalog));		
	}
	
	private List<StockSituationValues> calculateTotal(List<StockSituationValues> values){
		if (values == null)
			values = new ArrayList<StockSituationValues>(0);
		StockSituationValuesImpl total = new StockSituationValuesImpl();
		Warehouse whTotal = new Warehouse();
		whTotal.setCode(Messages.getInstance().getMessage(Messages.STOCK_SITUATION_FORM_WAREHOUSE_TOTAL));
		total.setWarehouse(whTotal);
		
		total.setAvailable1(BigDecimal.ZERO);
		total.setAvailable2(BigDecimal.ZERO);
		
		total.setLocated1(BigDecimal.ZERO);
		total.setLocated2(BigDecimal.ZERO);
		
		total.setPlanningIssue1(BigDecimal.ZERO);
		total.setPlanningIssue2(BigDecimal.ZERO);
		
		total.setPlanningReceipt1(BigDecimal.ZERO);
		total.setPlanningReceipt2(BigDecimal.ZERO);
		
		total.setReserved1(BigDecimal.ZERO);
		total.setReserved2(BigDecimal.ZERO);
		
		for(StockSituationValues value : values){
			if (value.getAvailable1() != null)
				total.setAvailable1(MathUtils.add(total.getAvailable1(), value.getAvailable1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			if (value.getAvailable2() != null)
				total.setAvailable2(MathUtils.add(total.getAvailable2(), value.getAvailable2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			
			if (value.getLocated1() != null)
				total.setLocated1(MathUtils.add(total.getLocated1(), value.getLocated1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			if (value.getLocated2() != null)
				total.setLocated2(MathUtils.add(total.getLocated2(), value.getLocated2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			
			if (value.getPlanningIssue1() != null)
				total.setPlanningIssue1(MathUtils.add(total.getPlanningIssue1(), value.getPlanningIssue1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			if (value.getPlanningIssue2() != null)
				total.setPlanningIssue2(MathUtils.add(total.getPlanningIssue2(), value.getPlanningIssue2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			
			if (value.getPlanningReceipt1() != null)
				total.setPlanningReceipt1(MathUtils.add(total.getPlanningReceipt1(), value.getPlanningReceipt1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			if (value.getPlanningReceipt2() != null)
				total.setPlanningReceipt2(MathUtils.add(total.getPlanningReceipt2(), value.getPlanningReceipt2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			
			if (value.getReserved1() != null)
				total.setReserved1(MathUtils.add(total.getReserved1(), value.getReserved1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
			if (value.getReserved2() != null)
				total.setReserved2(MathUtils.add(total.getReserved2(), value.getReserved2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
		}
		
		values.add(total);
		return values;
	}
	
	/**
	 * обработчик обновления текущей ситуации на складе
	 * 
	 * @param event
	 */
	public void onActionRefreshSituation(WidgetEvent event) {
		loadStockSituation();
		((AvailableInfoModel) stockSituation1.getModel()).fireModelChange();
		((AvailableInfoModel) stockSituation2.getModel()).fireModelChange();
	}

}
