/*
 * FacturaMaintenanceEJBQLTableModel.java
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
package com.mg.merp.factura.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.factura.model.FacturaHead;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка счет - фактур
 *
 * @author leonova
 * @version $Id: FacturaMaintenanceEJBQLTableModel.java,v 1.4 2009/02/10 14:23:44 safonov Exp $
 */
public class FacturaMaintenanceEJBQLTableModel extends
    DocumentMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHead.class, "From", "d.From.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "To", "d.To.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "InDate", "d.InDate", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "StockDate", "d.StockDate", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "PayDate", "d.PayDate", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "ToPayDocNumber", "d.ToPayDocNumber", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "ToPayDocDate", "d.ToPayDocDate", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "Comment", "d.Comment", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "Shipper", "sh.Code", "left join d.Shipper as sh", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "Consignee", "con.Code", "left join d.Consignee as con", false));
    result.add(new TableEJBQLFieldDef(FacturaHead.class, "CalcTaxesKind", "ctk.Code", "left join d.CalcTaxesKind as ctk", false));
    return result;
  }

}
