/*
 * WarehouseDocumentMaintenanceEJBQLTableModel.java
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
import com.mg.merp.document.generic.ui.GoodsDocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHead;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка складких документов
 *
 * @author leonova
 * @version $Id: WarehouseDocumentMaintenanceEJBQLTableModel.java,v 1.3 2009/02/10 14:29:13 safonov
 *          Exp $
 */
public class WarehouseDocumentMaintenanceEJBQLTableModel extends
    GoodsDocumentMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.GoodDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHead.class, "PriceList", "plh.PrName", "left join d.PriceList as plh", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "PriceType", "pt.Code", "left join d.PriceType as pt", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "Weight", "d.Weight", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "Volume", "d.Volume", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "Through", "t.Code", "left join d.Through as t", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "CalcTaxesKind", "ctk.Code", "left join d.CalcTaxesKind as ctk", false));
    return result;
  }

}
