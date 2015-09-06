/*
 * TransactHeadTableModel.java
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

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.paymentalloc.SelectionRowListener;
import com.mg.merp.paymentalloc.model.DocGroup;
import com.mg.merp.paymentalloc.model.Payment;
import com.mg.merp.paymentalloc.model.TransactHead;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Модель таблицы "Заголовков, связанных документов"
 *
 * @author Artem V. Sharapov
 * @version $Id: TransactHeadTableModel.java,v 1.1 2007/06/05 12:50:57 sharapov Exp $
 */
public class TransactHeadTableModel extends DefaultEJBQLTableModel {

  private final String INIT_QUERY_TEXT = "select distinct %s from TransactHead th %s where th.Payment = :payment %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private SelectionRowListener selectionRowListener;
  private Integer selectedPrimaryKey;

  private Payment payment;
  private DocGroup docGroup;

  public TransactHeadTableModel(SelectionRowListener selectionRowListener) {
    this.selectionRowListener = selectionRowListener;
  }

  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("payment"); //$NON-NLS-1$
    paramsValue.add(payment);
    StringBuilder whereText = new StringBuilder();
    if (docGroup != null) {
      fromList = fromList.concat(" left join th.DocHead dh, DocGroupLink dgl "); //$NON-NLS-1$
      paramsName.add("docGroup"); //$NON-NLS-1$
      paramsValue.add(docGroup);
      whereText.append(" and dgl.DocGroup = :docGroup and dgl.DocType = dh.DocType"); //$NON-NLS-1$
    }
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(TransactHead.class, "Id", "th.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "TotalSumCur", "th.TotalSumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "TotalSumNat", "th.TotalSumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "AllocSumCur", "th.AllocSumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "AllocSumNat", "th.AllocSumNat", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "DocHead.DocNumber", "d.DocNumber", "left join th.DocHead as d", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "DocHead.DocDate", "d.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "DocHead.DocType", "d.DocType", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactHead.class, "DocHead.Currency", "d.Currency.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
    if (rows.length != 0) {
      selectedPrimaryKey = (Integer) getRowList().get(rows[0])[0];
      if (selectionRowListener != null)
        selectionRowListener.selectedRowChange(selectedPrimaryKey);
    }
  }

  public void refershTable(Payment payment, DocGroup docGroup) {
    this.payment = payment;
    this.docGroup = docGroup;
    load();
  }

  public SelectionRowListener getSelectionRowListener() {
    return this.selectionRowListener;
  }

  public void setSelectionRowListener(SelectionRowListener selectionRowListener) {
    this.selectionRowListener = selectionRowListener;
  }

  public Integer getSelectedPrimaryKey() {
    return this.selectedPrimaryKey;
  }

}
