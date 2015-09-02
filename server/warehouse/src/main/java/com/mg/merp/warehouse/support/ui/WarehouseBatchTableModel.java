/* WarehouseBatchTableModel.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.support.BatchQuanTransfer;
import com.mg.merp.warehouse.support.Messages;

/**
 * Модель таблицы складских партий для списания
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseBatchTableModel.java,v 1.3 2008/08/27 09:44:07 sharapov Exp $
 */
public class WarehouseBatchTableModel extends AbstractTableModel {

	private String[] columnsName;

	/**
	 * Список партий
	 */
	private List<StockBatch> batchesList;

	/**
	 * Список объектов, содержащих в себе количество, подлежащее списанию в двух
	 * ЕИ. Связан с batchesList по индексу элемента
	 */
	private List<BatchQuanTransfer> dispQuan;

	public WarehouseBatchTableModel() {
		Messages msg = Messages.getInstance();
		columnsName = new String[] {msg.getMessage(Messages.ID_CAPTION),
				msg.getMessage(Messages.BEGIN_QUAN1_CAPTION),
				msg.getMessage(Messages.MARK_QUAN1_CAPTION),
				msg.getMessage(Messages.END_QUAN1_CAPTION),
				msg.getMessage(Messages.BEGIN_QUAN2_CAPTION),
				msg.getMessage(Messages.MARK_QUAN2_CAPTION),
				msg.getMessage(Messages.END_QUAN2_CAPTION),
				msg.getMessage(Messages.CONTRACTOR_CAPTION),
				msg.getMessage(Messages.PRICE_NAT_CAPTION),
				msg.getMessage(Messages.PRICE_CUR_CAPTION),
				msg.getMessage(Messages.CUR_CODE_CAPTION),
				msg.getMessage(Messages.DOC_CAPTION),
				msg.getMessage(Messages.CREATE_DATE_CAPTION),
				msg.getMessage(Messages.BESTBEFORE_CAPTION),
				msg.getMessage(Messages.VENDOR_LOT_CAPTION),
				msg.getMessage(Messages.NUMBER_LOT_CAPTION),
				msg.getMessage(Messages.COUNTRY_OF_ORIGIN_CAPTION),
				msg.getMessage(Messages.CUSTOMS_DECLARATION_CAPTION)};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnsName.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return columnsName[column];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
	 */
	public int getRowCount() {
		if (batchesList != null)
			return batchesList.size();
		else
			return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int column) {
		if (batchesList == null)
			throw new IllegalStateException();
		StockBatch item = batchesList.get(row);
		switch (column) {
		case 0:
			return item.getId();
		case 1:
			return item.getBeginQuan();
		case 2:
			return dispQuan.get(row).quan1;
		case 3:
			return item.getEndQuan();
		case 4:
			return item.getBeginQuan2();
		case 5:
			return dispQuan.get(row).quan2;
		case 6:
			return item.getEndQuan2();
		case 7:
			return item.getContractor().getCode();
		case 8:
			return item.getPriceNat();
		case 9:
			return item.getPriceCur();
		case 10:
			return item.getCurrencyCode();
		case 11:
			return item.getDocNumber();
		case 12:
			return item.getCreateDate();
		case 13:
			return item.getBestBefore();
		case 14:
			return item.getVendorLot();
		case 15:
			return item.getNumberLot();
		
		case 16:
			return item.getCountryOfOrigin() == null ? null : item.getCountryOfOrigin().getCName(); 
		case 17:
			return item.getCustomsDeclaration() == null ? null : item.getCustomsDeclaration().getNumber();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 2 || column == 4;
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		switch (column) {
		case 2:
			dispQuan.get(row).quan1 = new BigDecimal((String) value);
			fireTableCellUpdated(row, column);
			break;
		case 4:
			dispQuan.get(row).quan2 = new BigDecimal((String) value);
			fireTableCellUpdated(row, column);
			break;
		}
	}

	public List<StockBatch> getBatchesList() {
		return batchesList;
	}

	/**
	 * Наполнение модели
	 * 
	 * @param batchesList
	 *            Список доступных складских партий
	 */
	public void setBatchesList(List<StockBatch> batchesList) {
		this.batchesList = batchesList;
		dispQuan = new ArrayList<BatchQuanTransfer>(batchesList.size());
		for (int i = 0; i < batchesList.size(); i++)
			dispQuan.add(new BatchQuanTransfer());
	}

	/**
	 * 
	 * @return список объектов, содержащий в себе количество списываемых позиций
	 *         в двух ЕИ
	 */
	public List<BatchQuanTransfer> getDispQuan() {
		return dispQuan;
	}

}
