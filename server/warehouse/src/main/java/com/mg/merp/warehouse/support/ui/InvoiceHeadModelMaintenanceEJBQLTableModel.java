/*
 * InvoiceHeadModelMaintenanceEJBQLTableModel.java
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
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.warehouse.model.InvoiceHeadModel;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка образцов накладных
 *
 * @author leonova
 * @version $Id: InvoiceHeadModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 11:00:10 leonova
 *          Exp $
 */
public class InvoiceHeadModelMaintenanceEJBQLTableModel extends
    WarehouseDocModelMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.support.ui.WarehouseDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(InvoiceHeadModel.class, "SummaCurWithDiscount", "dhm.SummaCurWithDiscount", false));
    result.add(new TableEJBQLFieldDef(InvoiceHeadModel.class, "SummaNatWithDiscount", "dhm.SummaNatWithDiscount", false));
    result.add(new TableEJBQLFieldDef(InvoiceHeadModel.class, "DiscountOnDoc", "dhm.DiscountOnDoc", false));
    result.add(new TableEJBQLFieldDef(InvoiceHeadModel.class, "DiscountOnLine", "dhm.DiscountOnLine", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

  }

}
