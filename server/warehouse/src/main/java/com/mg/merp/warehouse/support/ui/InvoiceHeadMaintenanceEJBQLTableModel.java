/*
 * InvoiceHeadMaintenanceEJBQLTableModel.java
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.warehouse.model.InvoiceHead;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка накладных
 *
 * @author leonova
 * @version $Id: InvoiceHeadMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:29:13 safonov Exp $
 */
public class InvoiceHeadMaintenanceEJBQLTableModel extends
    WarehouseDocumentMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.support.ui.WarehouseDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(InvoiceHead.class, "Comment", "d.Comment", false));
    result.add(new TableEJBQLFieldDef(InvoiceHead.class, "ToPayDocNumber", "d.ToPayDocNumber", false));
    result.add(new TableEJBQLFieldDef(InvoiceHead.class, "ToPayDocDate", "d.ToPayDocDate", false));
    result.add(new TableEJBQLFieldDef(InvoiceHead.class, "Shipper", "sh.Code", "left join d.Shipper as sh", false));
    result.add(new TableEJBQLFieldDef(InvoiceHead.class, "Consignee", "con.Code", "left join d.Consignee as con", false));
    return result;
  }

}
