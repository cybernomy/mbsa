/*
 * CardHistDefaultMaintenanceEJBQLTableModel.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.settlement.CardHistServiceLocal;
import com.mg.merp.settlement.model.ContractorCardHist;

import java.util.Set;

/**
 * @author leonova
 * @version $Id: CardHistDefaultMaintenanceEJBQLTableModel.java,v 1.2 2006/10/27 07:18:45 leonova
 *          Exp $
 */
public class CardHistDefaultMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  protected CardHistServiceLocal docService;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "Id", "ccp.Id", true));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "ProcessDate", "ccp.ProcessDate", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocNumber", "d.DocNumber", "left join ccp.DocHead as d", true));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocType", "dt.Code", "left join d.DocType as dt", true));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocNumber", "d.DocNumber", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocType", "bdt.Code", "left join d.BaseDocType as bdt", true));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.BaseDocDate", "d.BaseDocDate", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.BaseDocNumber", "d.BaseDocNumber", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.DocType", "cdt.Code", "left join d.ContractType as cdt", true));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.ContractDate", "d.ContractDate", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.ContractNumber", "d.ContractNumber", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "SumNat", "ccp.SumNat", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "SumCur", "ccp.SumCur", false));
    result.add(new TableEJBQLFieldDef(ContractorCardHist.class, "DocHead.Currency", "cur.Code", "left join d.Currency as cur", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, docService);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
   */
  @Override
  protected int getPrimaryKeyFieldIndex() {
    return 0;
  }

}
