/*
 * TransactHeadSpecTaxTableModel.java
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
import com.mg.merp.paymentalloc.model.TransactSpec;
import com.mg.merp.paymentalloc.model.TransactTax;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Модель таблицы "Налоги позиции спецификации заголовка, связанного документа"
 *
 * @author Artem V. Sharapov
 * @version $Id: TransactHeadSpecTaxTableModel.java,v 1.1 2007/06/05 12:50:57 sharapov Exp $
 */
public class TransactHeadSpecTaxTableModel extends DefaultEJBQLTableModel {

  private final String INIT_QUERY_TEXT = "select %s from TransactTax tt %s where tt.TrSpec = :trspec"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private TransactSpec transactSpec;

  public TransactHeadSpecTaxTableModel() {
  }

  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("trspec"); //$NON-NLS-1$
    paramsValue.add(transactSpec);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(TransactTax.class, "Tax", "t.Code", "left join tt.Tax as t", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(TransactTax.class, "TotalSum", "tt.TotalSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(TransactTax.class, "AllocSum", "tt.AllocSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
  }

  public void refreshTable(TransactSpec transactSpec) {
    this.transactSpec = transactSpec;
    load();
  }

  public void resetTable() {
    rowList.clear();
    fireModelChange();
  }

}
