/*
 * WarehouseDocModelMaintenanceEJBQLTableModel.java
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
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHeadModel;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка образцов складких документов
 *
 * @author leonova
 * @version $Id: WarehouseDocModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 10:52:39 leonova
 *          Exp $
 */
public class WarehouseDocModelMaintenanceEJBQLTableModel extends
    DocModelMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.GoodDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "ContractDate", "dhm.ContractDate", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "ContractType", "ct.Code", "left join dhm.ContractType as ct", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "ContractNumber", "dhm.ContractNumber", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "DstStock", "dst.Code", "left join dhm.DstStock as dst", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "DstMol", "dm.Code", "left join dhm.DstMol as dm", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "PriceList", "plh.PrName", "left join dhm.PriceList as plh", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "PriceType", "pt.Code", "left join dhm.PriceType as pt", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "Weight", "dhm.Weight", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "Volume", "dhm.Volume", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "Through", "t.Code", "left join dhm.Through as t", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "CalcTaxesKind", "ctk.Code", "left join dhm.CalcTaxesKind as ctk", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

  }

}
