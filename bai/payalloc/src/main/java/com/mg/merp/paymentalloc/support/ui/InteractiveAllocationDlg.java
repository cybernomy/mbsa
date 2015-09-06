/*
 * InteractiveAllocationDlg.java
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
package com.mg.merp.paymentalloc.support.ui;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.merp.paymentalloc.model.SpecInfo;
import com.mg.merp.paymentalloc.support.Messages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер диалога "Интерактивное распределение платежа по спецификации документа"
 *
 * @author Artem V. Sharapov
 * @version $Id: InteractiveAllocationDlg.java,v 1.1 2007/05/31 14:13:30 sharapov Exp $
 */
public class InteractiveAllocationDlg extends DefaultDialog {

  private DefaultTableController table;
  private String[] columnsName;
  private List<SpecInfo> allocationList = new ArrayList<SpecInfo>();

  private SpecInfo selectedItem;

  private BigDecimal sumToAllocate = BigDecimal.ZERO;
  private BigDecimal allocatedSum = BigDecimal.ZERO;

  public InteractiveAllocationDlg() {
    columnsName = getColumnNames();
    table = new DefaultTableController(new AbstractTableModel() {

      public int getColumnCount() {
        return columnsName.length;
      }

      public String getColumnName(int column) {
        return columnsName[column];
      }

      public int getRowCount() {
        if (allocationList != null)
          return allocationList.size();
        else
          return 0;
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
       */
      public Object getValueAt(int row, int column) {
        SpecInfo item = allocationList.get(row);
        switch (column) {
          case 0:
            return item.getCode();
          case 1:
            return item.getCName();
          case 2:
            return item.getMCode();
          case 3:
            return item.getCQty();
          case 4:
            return item.getAQty();
          case 5:
            return item.getCSumma();
          case 6:
            return item.getASumma();
        }
        return null;
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#isCellEditable(int, int)
       */
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          selectedItem = null;
        else
          selectedItem = allocationList.get(rows[0]);
      }
    });
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    refreshTable();
  }

  private String[] getColumnNames() {
    Messages msg = Messages.getInstance();
    return new String[]{
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_CAT_CODE),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_CAT_NAME),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_MESURE),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_QTY),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_ALLOC_QTY),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_SUM),
        msg.getMessage(Messages.INTERACTIVE_ALLOC_COLUMN_NAME_ALLOC_SUM)};
  }

  private void refreshTable() {
    ((AbstractTableModel) table.getModel()).fireModelChange();
  }

  /**
   * Установить параметры запуска диалога
   *
   * @param allocationList - список позиций спецификации документа
   * @param sumToAllocate  - сумма к распределению
   */
  public void setParams(List<SpecInfo> allocationList, BigDecimal sumToAllocate) {
    this.allocationList = allocationList;
    this.sumToAllocate = sumToAllocate;
    this.allocatedSum = sumToAllocate;
  }

  /**
   * Обработчик кнопки "Распределить"
   *
   * @param event - событие
   */
  public void onActionAllocate(WidgetEvent event) {
    if (selectedItem != null) {
      final AllocationParamsDlg dialog = (AllocationParamsDlg) UIProducer.produceForm("com/mg/merp/paymentalloc/resources/AllocationParamsDlg.mfd.xml"); //$NON-NLS-1$;
      dialog.addOkActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          selectedItem.setAQty(dialog.getAllocationQty());
          selectedItem.setASumma(dialog.getAllocationSum());
          refreshTable();
          allocatedSum = getTotalAllocatedSum();
          view.flushModel();
        }
      });
      dialog.addCancelActionListener(new FormActionListener() {

        public void actionPerformed(FormEvent event) {
          // do nothing
        }
      });
      dialog.setAllocationQty(selectedItem.getAQty());
      dialog.setAllocationSum(selectedItem.getASumma());
      dialog.setPrice(selectedItem.getDocSpec().getPrice());
      dialog.execute();
    }
  }

  /**
   * Обработчик кнопки "Отменить распределение"
   *
   * @param event - событие
   */
  public void onActionDeAllocate(WidgetEvent event) {
    if (selectedItem != null) {
      allocatedSum = allocatedSum.subtract(selectedItem.getASumma());
      selectedItem.setAQty(BigDecimal.ZERO);
      selectedItem.setASumma(BigDecimal.ZERO);
      refreshTable();
    }
  }

  /**
   * Получить общую распределенную сумму
   *
   * @return общая распределенная сумма
   */
  private BigDecimal getTotalAllocatedSum() {
    BigDecimal totalAllocationSum = BigDecimal.ZERO;
    for (SpecInfo info : allocationList)
      totalAllocationSum = totalAllocationSum.add(info.getASumma());
    return totalAllocationSum;
  }

  public List<SpecInfo> getAllocationList() {
    return this.allocationList;
  }

  public void setAllocationList(List<SpecInfo> allocationList) {
    this.allocationList = allocationList;
  }

  public BigDecimal getAllocatedSum() {
    return this.allocatedSum;
  }

  public void setAllocatedSum(BigDecimal allocatedSum) {
    this.allocatedSum = allocatedSum;
  }

  public BigDecimal getSumToAllocate() {
    return this.sumToAllocate;
  }

  public void setSumToAllocate(BigDecimal sumToAllocate) {
    this.sumToAllocate = sumToAllocate;
  }

}
