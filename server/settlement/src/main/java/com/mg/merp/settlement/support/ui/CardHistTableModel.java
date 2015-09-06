/*
 * CardHistTableModel.java
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

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.settlement.model.ContractorCardHist;

import java.util.Set;

/**
 * Модель таблицы
 *
 * @author Artem V. Sharapov
 * @version $Id: CardHistTableModel.java,v 1.1 2007/03/19 15:05:29 sharapov Exp $
 */
public class CardHistTableModel extends DefaultEJBQLTableModel {

  private Integer selectedIndex;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "Id", "cch.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "ProcessDate", "cch.ProcessDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocType", "dt.Code", "left join cch.DocHead as d left join d.DocType as dt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocNumber", "d.DocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocDate", "d.DocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.BaseDocType", "bdt.Code", "left join d.BaseDocType as bdt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.BaseDocNumber", "d.BaseDocNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.BaseDocDate", "d.BaseDocDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.ContractType", "ct.Code", "left join d.ContractType as ct", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.ContractNumber", "d.ContractNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.ContractDate", "d.ContractDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "SumNat", "cch.SumNat", false));     //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "SumCur", "cch.SumCur", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.Currency", "cur.Code", "left join d.Currency as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
   */
  @Override
  public void setSelectedRows(int[] rows) {
    if (rows.length == 0)
      selectedIndex = null;
    else
      selectedIndex = rows[0];
  }

  public Integer getSelectedIdentifier() {
    if (selectedIndex != null)
      return (Integer) rowList.get(selectedIndex)[0];
    else
      return null;
  }

  public void clearTable() {
    rowList.clear();
    fireModelChange();
  }

}
