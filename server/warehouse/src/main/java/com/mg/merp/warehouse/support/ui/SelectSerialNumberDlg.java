/*
 * SelectSerialNumberDlg.java
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

import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.warehouse.support.Messages;

/**
 * Контроллер диалога выбора серийных номеров
 * 
 * @author Artem V. Sharapov
 * @version $Id: SelectSerialNumberDlg.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class SelectSerialNumberDlg extends DefaultWizardDialog {

	@DataItemName("Reference.Code") //$NON-NLS-1$
	private String catalogCode;

	@DataItemName("Reference.Name") //$NON-NLS-1$
	private String catalogName;

	@DataItemName("Warehouse.StockBatch.NumberLot") //$NON-NLS-1$
	private String numberLot;

	@DataItemName("Warehouse.StockBatch.VendorLot") //$NON-NLS-1$
	private String vendorLot;

	private Integer necessarySerialNumbersQuantity;
	protected DefaultTableController serialNumberTable;
	private List<SerialNumberModelItem> serialNumberItemList = new ArrayList<SerialNumberModelItem>();
	private List<String> selectedSerialNumbers = new ArrayList<String>();


	public SelectSerialNumberDlg() {
		serialNumberTable = new DefaultTableController(new SerialNumberTableModel());
		necessarySerialNumbersQuantity = 0;
	}

	private class SerialNumberTableModel extends AbstractTableModel {
		private String[] columnsNames = null;

		public SerialNumberTableModel() {
			initializeColumnsNames();
		}

		private void initializeColumnsNames() {
			Messages msg = Messages.getInstance();
			columnsNames = new String[] {msg.getMessage(Messages.SERIAL_NUMBER_SELECTED), msg.getMessage(Messages.SERIAL_NUMBER)};
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
			SerialNumberModelItem item = serialNumberItemList.get(row);
			switch (column) {
			case 0: return item.getIsChecked();
			case 1: return item.getNumber();
			default: return StringUtils.EMPTY_STRING;
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
			return serialNumberItemList.size();
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#isCellEditable(int, int)
		 */
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 0;
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setValueAt(java.lang.Object, int, int)
		 */
		public void setValueAt(Object value, int rowIndex, int columnIndex) {
			SerialNumberModelItem item = serialNumberItemList.get(rowIndex);
			switch (columnIndex) {
			case 0:
				item.setChecked((Boolean) value);
				break;
			}
		}

		/* (non-Javadoc)
		 * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int column) {
			if (column == 0)
				return Boolean.class;
			else
				return null;
		}
	}

	/**
	 * Запустить диалог ввода серийных номеров
	 * @param necessarySerialNumbersQuantity - кол-во номеров для выбора
	 * @param catalogCode - код позиции каталога
	 * @param catalogName - наименование позиции каталога
	 * @param numberLot - номер партии
	 * @param vendorLot - номер партии поставщика
	 */
	public void execute(Integer necessarySerialNumbersQuantity, List<SerialNumberModelItem> serialNumberItemList, String catalogCode, String catalogName, String numberLot, String vendorLot) {
		this.necessarySerialNumbersQuantity = necessarySerialNumbersQuantity;
		this.catalogCode = catalogCode;
		this.catalogName = catalogName;
		this.serialNumberItemList = serialNumberItemList;
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

	/**
	 * Выполнить проверку корректности кол-ва выбранных серийных номеров
	 * @return <code>true</code> - если кол-во выбранных номеров равно требуемому кол-ву номеров для выбора<br>
	 * <code>false</code> - во всех остальных случаях
	 */
	private boolean isSelectionValid() {
		return getSelectionResult() == necessarySerialNumbersQuantity;
	}

	/**
	 * Получить кол-во выбранных серийных номеров
	 * @return кол-во выбранных серийных номеров
	 */
	private int getSelectionResult() {
		selectedSerialNumbers.clear();
		for (SerialNumberModelItem item : serialNumberItemList) {
			if(item.getIsChecked())
				selectedSerialNumbers.add(item.getNumber());
		}
		return selectedSerialNumbers.size();
	}

	/**
	 * Получить список выбранных серийных номеров
	 * @return список серийных номеров
	 */
	public List<String> getSelectedSerialNumbers() {
		return selectedSerialNumbers;
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
	 * @return the necessarySerialNumbersQuantity
	 */
	public Integer getNecessarySerialNumbersQuantity() {
		return this.necessarySerialNumbersQuantity;
	}

	/**
	 * @param necessarySerialNumbersQuantity the necessarySerialNumbersQuantity to set
	 */
	public void setNecessarySerialNumbersQuantity(Integer necessarySerialNumbersQuantity) {
		this.necessarySerialNumbersQuantity = necessarySerialNumbersQuantity;
	}

	/**
	 * @return the serialNumberItemList
	 */
	public List<SerialNumberModelItem> getSerialNumberItemList() {
		return this.serialNumberItemList;
	}

	/**
	 * @param serialNumberItemList the serialNumberItemList to set
	 */
	public void setSerialNumberItemList(List<SerialNumberModelItem> serialNumberItemList) {
		this.serialNumberItemList = serialNumberItemList;
	}

	/**
	 * @param selectedSerialNumbers the selectedSerialNumbers to set
	 */
	public void setSelectedSerialNumbers(List<String> selectedSerialNumbers) {
		this.selectedSerialNumbers = selectedSerialNumbers;
	}

}
