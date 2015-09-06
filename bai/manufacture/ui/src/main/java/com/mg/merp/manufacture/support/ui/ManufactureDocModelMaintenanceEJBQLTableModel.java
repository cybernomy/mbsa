/*
 * ManufactureDocModelMaintenanceEJBQLTableModel.java
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
package com.mg.merp.manufacture.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHeadModel;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка образцов производственных документов
 *
 * @author leonova
 * @version $Id: ManufactureDocModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 11:09:15
 *          leonova Exp $
 */
public class ManufactureDocModelMaintenanceEJBQLTableModel extends
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
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

  }

}
