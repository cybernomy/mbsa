/*
 * CashDocumentModelMaintenanceEJBQLTableModel.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.model.CashDocumentModel;
import com.mg.merp.document.generic.ui.DocModelMaintenanceEJBQLTableModel;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка образцов кассовых документов
 *
 * @author leonova
 * @version $Id: CashDocumentModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 11:29:11 leonova
 *          Exp $
 */
public class CashDocumentModelMaintenanceEJBQLTableModel extends
    DocModelMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.GoodDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Company", "comp.Code", "left join dhm.Company as comp", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Base", "dhm.Base", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "GetDate", "dhm.GetDate", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "GetDocument", "dhm.GetDocument", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Acc", "a.Acc", "left join dhm.Acc as a", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "AnlCode", "dhm.AnlCode", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "TargetCode", "dhm.TargetCode", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Comment1", "dhm.Comment1", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Chief", "ch.Code", "left join dhm.Chief as ch", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Bookkeeper", "b.Code", "left join dhm.Bookkeeper as b", false));
    result.add(new TableEJBQLFieldDef(CashDocumentModel.class, "Cashier", "ca.Code", "left join dhm.Cashier as ca", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

  }

}
