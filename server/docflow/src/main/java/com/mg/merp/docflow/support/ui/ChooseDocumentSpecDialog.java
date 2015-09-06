/*
 * ChooseDocumentSpecDialog.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.api.math.Constants;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.support.Messages;
import com.mg.merp.document.model.DocSpec;

import java.math.BigDecimal;
import java.util.List;

/**
 * Контроллер формы диалога вызываемого при частичной отработке ЭДО
 *
 * @author Oleg V. Safonov
 * @author Konstantin S. Alikaev
 * @version $Id: ChooseDocumentSpecDialog.java,v 1.10 2009/02/04 14:23:01 safonov Exp $
 */
public class ChooseDocumentSpecDialog extends DefaultWizardDialog {
  private static final int COLUMN_SUM = 6;
  private static final int COLUMN_QUAN1 = 7;
  private static final int COLUMN_QUAN2 = 8;
  private DefaultTableController specList;
  private BigDecimal userTotalSum = null;
  private int[] selectedRows = null;

  public ChooseDocumentSpecDialog() {
    this.specList = new DefaultTableController(new DocSpecListModel());
  }

  public void setDocumentSpecList(List<DocumentSpecItem> specList) {
    ((DocSpecListModel) this.specList.getModel()).specListModel = specList;
  }

  /**
   * Обработчик пункта КМ "Отметить все позиции к отработке"
   *
   * @param event - событие
   */
  public void onActionChooseAll(WidgetEvent event) {
    DocSpecListModel tableModel = (DocSpecListModel) this.specList.getModel();
    List<DocumentSpecItem> specListModel = tableModel.specListModel;
    for (DocumentSpecItem item : specListModel) {
      item.setPerformedQuantity1(item.getFreeQuantity1());
      item.setPerformedQuantity2(item.getFreeQuantity2());
      item.setPerformedSum(item.getFreeSum());
    }
    tableModel.fireModelChange();
  }

  /**
   * Обработчик пункта КМ "Отменить все позиции к отработке"
   *
   * @param event - событие
   */
  public void onActionClearAll(WidgetEvent event) {
    DocSpecListModel tableModel = (DocSpecListModel) this.specList.getModel();
    List<DocumentSpecItem> specListModel = tableModel.specListModel;
    for (DocumentSpecItem item : specListModel) {
      item.setPerformedQuantity1(BigDecimal.ZERO);
      item.setPerformedQuantity2(BigDecimal.ZERO);
      item.setPerformedSum(BigDecimal.ZERO);
    }
    tableModel.fireModelChange();
  }

  /**
   * обработчик пункта "Отметить к отработке выделенные"
   */
  public void onActionChooseSelected(WidgetEvent event) {
    if (selectedRows != null && selectedRows.length > 0) {
      DocSpecListModel tableModel = (DocSpecListModel) this.specList.getModel();
      List<DocumentSpecItem> specListModel = tableModel.specListModel;
      for (int row : selectedRows) {
        DocumentSpecItem item = specListModel.get(row);
        item.setPerformedQuantity1(item.getFreeQuantity1());
        item.setPerformedQuantity2(item.getFreeQuantity2());
        item.setPerformedSum(item.getFreeSum());

        tableModel.fireTableCellUpdated(row, COLUMN_QUAN1);
        tableModel.fireTableCellUpdated(row, COLUMN_QUAN2);
        tableModel.fireTableCellUpdated(row, COLUMN_SUM);
      }
    }
  }

  /**
   * обработчик распределения суммы
   */
  public void onActionAllotSum(WidgetEvent event) {
    if (MathUtils.compareToZeroOrNull(userTotalSum) == 0)
      return;

    DocSpecListModel tableModel = (DocSpecListModel) this.specList.getModel();
    List<DocumentSpecItem> specListModel = tableModel.specListModel;
    BigDecimal totalSum = BigDecimal.ZERO;
    for (DocumentSpecItem item : specListModel) {
      if (item.getFreeSum() != null)
        totalSum = totalSum.add(item.getFreeSum());
    }

    BigDecimal allotedSum = BigDecimal.ZERO;
    for (DocumentSpecItem item : specListModel) {
      double ratio = 1.0;
      if (MathUtils.compareToZero(totalSum) != 0 && item.getFreeSum() != null)
        ratio = item.getFreeSum().doubleValue() / totalSum.doubleValue();

      BigDecimal price = item.getDocSpec().getPrice();
      BigDecimal quantity1 = BigDecimal.ZERO;
      BigDecimal sum = userTotalSum != null ? userTotalSum.multiply(new BigDecimal(ratio)) : BigDecimal.ZERO;
      if (MathUtils.compareToZeroOrNull(price) != 0)
        quantity1 = MathUtils.divide(sum, price, Constants.QUANTITY_ROUND_CONTEXT);

      item.setPerformedQuantity1(quantity1);
      item.setPerformedSum(MathUtils.round(sum, Constants.GENERAL_MONETARY_AMOUNT_ROUND_CONTEXT));

      allotedSum = allotedSum.add(item.getPerformedSum());
    }

    //могла появиться погрешность
    if (allotedSum.compareTo(userTotalSum) != 0) {
      BigDecimal maxSum = BigDecimal.ZERO;
      int maxIdx = -1;
      //погрешность распределения скинем на спецификацию с макс. суммой
      for (int i = 0; i < specListModel.size(); i++) {
        BigDecimal sum = specListModel.get(i).getPerformedSum();
        if (maxSum.compareTo(sum) == -1) {
          maxSum = sum;
          maxIdx = i;
        }
      }
      if (maxIdx != -1)
        specListModel.get(maxIdx).setPerformedSum(MathUtils.round(maxSum.add(userTotalSum).subtract(allotedSum), Constants.GENERAL_MONETARY_AMOUNT_ROUND_CONTEXT));
    }

    tableModel.fireModelChange();
  }

  private class DocSpecListModel extends AbstractTableModel {
    private ApplicationDictionary applicationDictionary = ApplicationDictionaryLocator.locate();
    private Messages messages = Messages.getInstance();
    private String[] columnsName = new String[]{
        messages.getMessage(Messages.DOCSPEC_CODE),
        messages.getMessage(Messages.DOCSPEC_NAME),
        messages.getMessage(Messages.DOCSPEC_PRICE),
        messages.getMessage(Messages.DOCSPEC_SUMMA),
        messages.getMessage(Messages.DOCSPEC_QUANTITY),
        messages.getMessage(Messages.DOCSPEC_QUANTITY1),
        messages.getMessage(Messages.PERFORMEDSUM),
        messages.getMessage(Messages.PERFORMED_QUANTITY),
        messages.getMessage(Messages.PERFORMED_QUANTITY1)};
    private List<DocumentSpecItem> specListModel;

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnName(int)
     */
    public String getColumnName(int column) {
      return columnsName[column];
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
      DocumentSpecItem item = specListModel.get(row);
      switch (column) {
        case 0:
          return item.getDocSpec().getCatalog().getCode();
        case 1:
          return item.getDocSpec().getCatalog().getFullName();
        case 2:
          return item.getDocSpec().getPrice();
        case 3:
          return item.getDocSpec().getSumma();
        case 4:
          return item.getFreeQuantity1();
        case 5:
          return item.getFreeQuantity2();
        case 6:
          return item.getPerformedSum();
        case 7:
          return item.getPerformedQuantity1();
        case 8:
          return item.getPerformedQuantity2();
        default:
          return null;
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getColumnCount()
     */
    public int getColumnCount() {
      return columnsName.length;
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getRowCount()
     */
    public int getRowCount() {
      return specListModel.size();
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
      return column >= 2 ? BigDecimal.class : super.getColumnClass(column);
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#getColumnMetadata(int)
     */
    @Override
    public FieldMetadata getColumnMetadata(int column) {
      switch (column) {
        case 2:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Price");
        case 3:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Summa");
        case 4:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Quantity");
        case 5:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Quantity2");
        case 6:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Summa");
        case 7:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Quantity");
        case 8:
          return applicationDictionary.getFieldMetadata(DocSpec.class, "Quantity2");
        default:
          return super.getColumnMetadata(column);
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return columnIndex == COLUMN_SUM || columnIndex == COLUMN_QUAN1 || columnIndex == COLUMN_QUAN2;
    }

    /* (non-Javadoc)
     * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
      DocumentSpecItem item = specListModel.get(rowIndex);
      switch (columnIndex) {
        case COLUMN_SUM: //sum
          item.setPerformedSum((BigDecimal) value);
          if (MathUtils.compareToZeroOrNull(item.getDocSpec().getPrice()) != 0
              && MathUtils.compareToZeroOrNull(item.getPerformedSum()) != 0)
            item.setPerformedQuantity1(item.getPerformedSum().divide(item.getDocSpec().getPrice()));
          else
            item.setPerformedQuantity1(BigDecimal.ZERO);
          fireTableCellUpdated(rowIndex, COLUMN_QUAN1);
          break;
        case COLUMN_QUAN1: //quan1
          item.setPerformedQuantity1((BigDecimal) value);
          if (MathUtils.compareToZeroOrNull(item.getDocSpec().getPrice()) != 0
              && MathUtils.compareToZeroOrNull(item.getPerformedQuantity1()) != 0) {
            item.setPerformedSum(item.getPerformedQuantity1().multiply(item.getDocSpec().getPrice()));
            fireTableCellUpdated(rowIndex, COLUMN_SUM);
          }
          break;
        case COLUMN_QUAN2: //quan2
          item.setPerformedQuantity2((BigDecimal) value);
          break;
      }
    }

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#setSelectedRows(int[])
     */
    @Override
    public void setSelectedRows(int[] rows) {
      selectedRows = rows;
    }

  }

}
