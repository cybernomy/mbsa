/*
 * DocTableModel.java
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
package com.mg.merp.settlement.support.ui;

import com.mg.framework.generic.ui.AbstractTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.settlement.SelectionRowListener;
import com.mg.merp.settlement.support.ui.ContractorCardMt.DocListItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Модель таблицы для отображения документов-оснований и контрактов в карточке расчетов с
 * партнерами
 *
 * @author Artem V. Sharapov
 * @version $Id: DocTableModel.java,v 1.1 2007/03/19 15:05:29 sharapov Exp $
 */
public class DocTableModel extends AbstractTableModel {

  private final String DATE_FORMAT = "%1$td.%1tm.%1$tY"; //$NON-NLS-1$
  private List<DocListItem> docList;
  private String[] columnsName;
  private Integer selectedIndex;
  private SelectionRowListener selectionRowListener;

  public DocTableModel(SelectionRowListener selectionRowListener) {
    columnsName = getColumnNames();
    this.selectionRowListener = selectionRowListener;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnCount()
   */
  public int getColumnCount() {
    return columnsName.length;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getColumnName(int)
   */
  public String getColumnName(int column) {
    return columnsName[column];
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getRowCount()
   */
  public int getRowCount() {
    return docList.size();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.ui.widget.TableModel#getValueAt(int, int)
   */
  public Object getValueAt(int row, int column) {
    DocListItem item = docList.get(row);
    switch (column) {
      case 0:
        return item.docType;
      case 1:
        return item.docNumber;
      case 2:
        return String.format(DATE_FORMAT, item.docDate);
      case 3:
        return item.curCode;
      case 4:
        return item.sumNat == null ? new BigDecimal(0) : item.sumNat;
      case 5:
        return item.sumCur == null ? new BigDecimal(0) : item.sumCur;
    }
    return null;
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
      selectionRowListener.selectedRowChange(docList.get(selectedIndex));
    }
  }

  private String[] getColumnNames() {
    String[] columnNames = new String[6];
    columnNames[0] = getHeaderName("Document.DocType"); //$NON-NLS-1$
    columnNames[1] = getHeaderName("Document.DocNumber"); //$NON-NLS-1$
    columnNames[2] = getHeaderName("Document.DocDate"); //$NON-NLS-1$
    columnNames[3] = getHeaderName("Reference.Currency"); //$NON-NLS-1$
    columnNames[4] = getHeaderName("Document.SumNat"); //$NON-NLS-1$
    columnNames[5] = getHeaderName("Document.SumCur"); //$NON-NLS-1$
    return columnNames;
  }

  private String getHeaderName(String dataItemName) {
    return ApplicationDictionaryLocator.locate().getDataItem(dataItemName).getHeader();
  }

  /**
   * @param docList the docList to set
   */
  public void setDocList(List<DocListItem> docList) {
    this.docList = docList;
  }

  public void setDocListAndRefresh(List<DocListItem> docList) {
    this.docList = docList;
    this.fireModelChange();
  }

}
