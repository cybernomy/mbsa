/*
 * SelectBinLocationDlg.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.warehouse.support.Messages;

/**
 * Контроллер диалога выбора секций хранения
 * 
 * @author Artem V. Sharapov
 * @version $Id: SelectBinLocationDlg.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class SelectBinLocationDlg extends DefaultWizardDialog {

	@DataItemName("Reference.Code") //$NON-NLS-1$
	private String catalogCode;

	@DataItemName("Reference.Name") //$NON-NLS-1$
	private String catalogName;

	@DataItemName("Warehouse.StockBatch.NumberLot") //$NON-NLS-1$
	private String numberLot;

	@DataItemName("Warehouse.StockBatch.VendorLot") //$NON-NLS-1$
	private String vendorLot;

	private BigDecimal necessaryQuantity;
	protected DefaultTableController table;
	private List<BinLocationData> tableModelItemList = new ArrayList<BinLocationData>();
	private List<BinLocationData> selectedItemsList = new ArrayList<BinLocationData>();


	public SelectBinLocationDlg() {
		table = new DefaultTableController(new SerialNumberTableModel());
		necessaryQuantity = BigDecimal.ZERO;
	}

	private class SerialNumberTableModel extends AbstractTableModel {
		private String[] columnsNames = null;

		public SerialNumberTableModel() {
			initializeColumnsNames();
		}

		private void initializeColumnsNames() {
			Messages msg = Messages.getInstance();
			columnsNames = new String[] {
					msg.getMessage(Messages.BIN_LOCATION_CODE),
					msg.getMessage(Messages.BIN_LOCATION_QUANTITY_IN_SECTION),
					msg.getMessage(Messages.BIN_LOCATION_QUANTITY_TO_PERFORM)};
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
		 */
		public String getColumnName(int column) {
			return columnsNames[column];
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			BinLocationData item = tableModelItemList.get(row);
			switch (column) {
			case 0: 
				return item.getBinLocation().getCode().trim();
			case 1: 
				return item.getQuantityInSection();
			case 2: 
				return item.getQuantityToPerform();
			default: 
				return StringUtils.EMPTY_STRING;
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
		 */
		public int getColumnCount() {
			return columnsNames.length;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
		 */
		public int getRowCount() {
			return tableModelItemList.size();
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 2;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setValueAt(java.lang.Object, int, int)
		 */
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			BinLocationData item = tableModelItemList.get(rowIndex);
			switch (columnIndex) {
			case 2:
				item.setQuantityToPerform(new BigDecimal((String) value));
				break;
			}
		}
	}

	/**
	 * Запустить диалог на показ
	 * @param necessaryQuantity - 
	 * @param catalogCode - код позиции каталога
	 * @param catalogName - наименование позиции каталога
	 * @param numberLot - номер партии
	 * @param vendorLot - номер партии поставщика
	 */
	public void execute(BigDecimal necessaryQuantity, List<BinLocationData> binLocationTableModelItemList, String catalogCode, String catalogName, String numberLot, String vendorLot) {
		this.necessaryQuantity = necessaryQuantity;
		this.catalogCode = catalogCode;
		this.catalogName = catalogName;
		this.tableModelItemList = binLocationTableModelItemList;
		this.numberLot = numberLot;
		this.vendorLot = vendorLot;
		execute();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
	 */
	@Override
	public void onActionOk(WidgetEvent event) {
		if(isSelectionValid())
			super.onActionOk(event);
	}

	private boolean isSelectionValid() {
		return necessaryQuantity.compareTo(getSelectionResult()) == 0;
	}

	private BigDecimal getSelectionResult() {
		selectedItemsList.clear();
		BigDecimal result = BigDecimal.ZERO;
		for (BinLocationData binLocationTableModelItem : tableModelItemList) {
			BigDecimal quantityToPerform = binLocationTableModelItem.getQuantityToPerform() == null ? BigDecimal.ZERO : binLocationTableModelItem.getQuantityToPerform(); 
			if(MathUtils.compareToZero(quantityToPerform) > 0) {
				result = result.add(quantityToPerform);
				selectedItemsList.add(binLocationTableModelItem);
			}
		}
		return result;
	}

	/**
	 * @return the tableModelItemList
	 */
	public List<BinLocationData> getSelectedItemsList() {
		return this.selectedItemsList;
	}

	/**
	 * @param tableModelItemList the tableModelItemList to set
	 */
	public void setTableModelItemList(List<BinLocationData> tableModelItemList) {
		this.tableModelItemList = tableModelItemList;
	}

	/**
	 * @return the catalogCode
	 */
	public String getCatalogCode() {
		return this.catalogCode;
	}

	/**
	 * @param catalogCode the catalogCode to set
	 */
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	/**
	 * @return the catalogName
	 */
	public String getCatalogName() {
		return this.catalogName;
	}

	/**
	 * @param catalogName the catalogName to set
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	/**
	 * @return the numberLot
	 */
	public String getNumberLot() {
		return this.numberLot;
	}

	/**
	 * @param numberLot the numberLot to set
	 */
	public void setNumberLot(String numberLot) {
		this.numberLot = numberLot;
	}

	/**
	 * @return the vendorLot
	 */
	public String getVendorLot() {
		return this.vendorLot;
	}

	/**
	 * @param vendorLot the vendorLot to set
	 */
	public void setVendorLot(String vendorLot) {
		this.vendorLot = vendorLot;
	}

	/**
	 * @return the necessaryQuantity
	 */
	public BigDecimal getNecessaryQuantity() {
		return this.necessaryQuantity;
	}

	/**
	 * @param necessaryQuantity the necessaryQuantity to set
	 */
	public void setNecessaryQuantity(BigDecimal necessaryQuantity) {
		this.necessaryQuantity = necessaryQuantity;
	}

	/**
	 * @param selectedItemsList the selectedItemsList to set
	 */
	public void setSelectedItemsList(List<BinLocationData> selectedItemsList) {
		this.selectedItemsList = selectedItemsList;
	}

}
