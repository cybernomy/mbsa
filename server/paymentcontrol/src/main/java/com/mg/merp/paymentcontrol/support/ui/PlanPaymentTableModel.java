/*
 * PlanPaymentTableModel.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.merp.paymentcontrol.SelectionRowListener;
import com.mg.merp.paymentcontrol.model.PlanPaymentItem;
import com.mg.merp.paymentcontrol.support.Messages;

import java.util.List;

/**
 * Модель таблицы "Планирование платежей"
 *
 * @author Artem V. Sharapov
 * @version $Id: PlanPaymentTableModel.java,v 1.1 2007/05/14 05:23:52 sharapov Exp $
 */
public class PlanPaymentTableModel extends AbstractTableModel {

  // Fields

  private final String DATE_FORMAT = "%1$td.%1tm.%1$tY"; //$NON-NLS-1$
  private String[] columnsNames;
  private List<PlanPaymentItem> rowList;
  private SelectionRowListener selectionRowListener;
  private Integer selectedIndex;

  // Constructor
  public PlanPaymentTableModel(SelectionRowListener selectionRowListener) {
    columnsNames = getColumnNames();
    this.selectionRowListener = selectionRowListener;
  }

  // Methods

  private String[] getColumnNames() {
    Messages msg = Messages.getInstance();
    return new String[]{
        msg.getMessage(Messages.COLUMN_NAME_ID),
        msg.getMessage(Messages.COLUMN_NAME_PERIOD),
        msg.getMessage(Messages.COLUMN_NAME_DATE_FROM),
        msg.getMessage(Messages.COLUMN_NAME_DATE_TILL),
        msg.getMessage(Messages.COLUMN_NAME_CURRENCY),
        msg.getMessage(Messages.COLUMN_NAME_RESOURCE),
        msg.getMessage(Messages.COLUMN_NAME_BEGIN_SALDO),
        msg.getMessage(Messages.COLUMN_NAME_INCOME),
        msg.getMessage(Messages.COLUMN_NAME_EXPENSE),
        msg.getMessage(Messages.COLUMN_NAME_END_SALDO)};
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
   */
  public int getColumnCount() {
    return columnsNames.length;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
   */
  public String getColumnName(int column) {
    return columnsNames[column];
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
   */
  public int getRowCount() {
    return rowList.size();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int column) {
    PlanPaymentItem item = rowList.get(row);

    switch (column) {
      case 0:
        return item.getId();
      case 1:
        return item.getPeriod();
      case 2:
        return String.format(DATE_FORMAT, item.getDateFrom());
      case 3:
        return String.format(DATE_FORMAT, item.getDateTill());
      case 4:
        return item.getCurrencyCode();
      case 5:
        return item.getResource();
      case 6:
        return item.getBeginSaldo();
      case 7:
        return item.getIncome();
      case 8:
        return item.getExpense();
      case 9:
        return item.getEndSaldo();
      default:
        return ""; //$NON-NLS-1$
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
    if (rows.length == 0)
      selectedIndex = null;
    else {
      selectedIndex = rows[0];
      selectionRowListener.selectedRowChange(rowList.get(selectedIndex));
    }
  }

  /**
   * @return the rowList
   */
  public List<PlanPaymentItem> getRowList() {
    return rowList;
  }

  /**
   * @param rowList the rowList to set
   */
  public void setRowList(List<PlanPaymentItem> rowList) {
    this.rowList = rowList;
  }

}
