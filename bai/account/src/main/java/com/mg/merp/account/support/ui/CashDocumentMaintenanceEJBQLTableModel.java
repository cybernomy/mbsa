/*
 * CashDocumentMaintenanceEJBQLTableModel.java
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
import com.mg.merp.account.model.CashDocument;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHead;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка кассовых документов
 *
 * @author leonova
 * @version $Id: CashDocumentMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:19:51 safonov Exp
 *          $
 */
public class CashDocumentMaintenanceEJBQLTableModel extends
    DocumentMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Company", "d.Company.Code", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Acc", "a.Acc", "left join d.Acc as a", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "AnlCode", "d.AnlCode", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "TargetCode", "d.TargetCode", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Base", "d.Base", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Comment", "d.Comment", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Cashier", "cash.Code", "left join d.Cashier as cash", false));
    result.add(new TableEJBQLFieldDef(CashDocument.class, "Bookkeeper", "b.Code", "left join d.Bookkeeper as b", false));
    return result;
  }

}
