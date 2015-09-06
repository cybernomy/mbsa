/*
 * InputSerialNumberDialog.java
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

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.warehouse.support.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер диалога ввода серийных номеров
 *
 * @author Artem V. Sharapov
 * @version $Id: InputSerialNumberDlg.java,v 1.1 2008/05/30 13:03:56 sharapov Exp $
 */
public class InputSerialNumberDlg extends DefaultWizardDialog {

  @DataItemName("Warehouse.SerialNum.SerialNumber") //$NON-NLS-1$
  private String newSerialNumber;

  @DataItemName("Reference.Code") //$NON-NLS-1$
  private String catalogCode;

  @DataItemName("Reference.Name") //$NON-NLS-1$
  private String catalogName;

  @DataItemName("Warehouse.StockBatch.NumberLot") //$NON-NLS-1$
  private String numberLot;

  @DataItemName("Warehouse.StockBatch.VendorLot") //$NON-NLS-1$
  private String vendorLot;

  private Integer necessarySerialNumbersQuantity;
  private Integer newSerialNumbersQuantity;

  private DefaultTableController serialNumberTable;
  private String[] selectedItems;
  private List<String> serialNumberList = new ArrayList<String>();


  public InputSerialNumberDlg() {
    serialNumberTable = new DefaultTableController(new SerialNumberTableModel());
    necessarySerialNumbersQuantity = 0;
    newSerialNumbersQuantity = 0;
  }

  /**
   * Обработчик кнопки "Добавить"
   *
   * @param event - событие
   */
  public void onActionAddSerialNumber(WidgetEvent event) {
    if (!StringUtils.stringNullOrEmpty(newSerialNumber)) {
      serialNumberList.add(newSerialNumber);
      newSerialNumber = StringUtils.EMPTY_STRING;
      newSerialNumbersQuantity++;
      refreshTable();
    }
  }

  /**
   * Обработчик кнопки "Удалить"
   *
   * @param event - событие
   */
  public void onActionRemoveSerialNumber(WidgetEvent event) {
    if (selectedItems != null && selectedItems.length > 0) {
      for (String selectedItem : selectedItems) {
        newSerialNumbersQuantity--;
        serialNumberList.remove(selectedItem);
      }
      refreshTable();
    }
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultDialog#onActionOk(com.mg.framework.api.ui.WidgetEvent)
   */
  @Override
  public void onActionOk(WidgetEvent event) {
    if (newSerialNumbersQuantity >= necessarySerialNumbersQuantity)
      super.onActionOk(event);
  }

  /**
   * Обновить таблицу серийных номеров
   */
  private void refreshTable() {
    ((SerialNumberTableModel) serialNumberTable.getModel()).fireModelChange();
  }

  /**
   * Запустить диалог ввода серийных номеров
   *
   * @param necessarySerialNumbersQuantity - кол-во номеров для ввода
   * @param catalogCode                    - код позиции каталога
   * @param catalogName                    - наименование позиции каталога
   * @param numberLot                      - номер партии
   * @param vendorLot                      - номер партии поставщика
   */
  public void execute(Integer necessarySerialNumbersQuantity, String catalogCode, String catalogName, String numberLot, String vendorLot) {
    this.necessarySerialNumbersQuantity = necessarySerialNumbersQuantity;
    this.catalogCode = catalogCode;
    this.catalogName = catalogName;
    this.numberLot = numberLot;
    this.vendorLot = vendorLot;
    execute();
  }

  /**
   * Получить список серийных номеров
   *
   * @return список серийных номеров
   */
  public List<String> getSerialNumbers() {
    return serialNumberList;
  }

  /**
   * @return the catalogCode
   */
  public String getCatalogCode() {
    return this.catalogCode;
  }

  /**
   * @return the catalogName
   */
  public String getCatalogName() {
    return this.catalogName;
  }

  private class SerialNumberTableModel extends AbstractTableModel {
    private String[] columnsNames = null;

    public SerialNumberTableModel() {
      initializeColumnsNames();
    }

    private void initializeColumnsNames() {
      columnsNames = new String[]{Messages.getInstance().getMessage(Messages.SERIAL_NUMBER)};
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
      String item = serialNumberList.get(row);
      switch (column) {
        case 0:
          return item;
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
      return serialNumberList.size();
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
     */
    @Override
    public void setSelectedRows(int[] rows) {
      selectedItems = new String[rows.length];
      for (int i = 0; i < rows.length; i++)
        selectedItems[i] = serialNumberList.get(rows[i]);
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
      switch (columnIndex) {
        case 0:
          serialNumberList.set(rowIndex, (String) value);
          break;
      }
      setSelectedRows(new int[]{rowIndex});
    }
  }

}
