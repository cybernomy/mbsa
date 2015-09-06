/*
 * BankDocumentMaintenanceEJBQLTableModel.java
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
import com.mg.merp.account.model.BankDocument;
import com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocHead;

import java.util.Set;

/**
 * Вспомогательный класс для отображения формы списка банкоских документов
 *
 * @author leonova
 * @version $Id: BankDocumentMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:19:51 safonov Exp
 *          $
 */
public class BankDocumentMaintenanceEJBQLTableModel extends
    DocumentMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.ui.DocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHead.class, "From", "d.From.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "To", "d.To.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractDate", "d.ContractDate", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractType", "ct.Code", "left join d.ContractType as ct", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "ContractNumber", "d.ContractNumber", false));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "Comment", "d.Comment", false));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "PayerBankReq.Account", "pbr.Account", "left join d.PayerBankReq as pbr", true));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "PayerBankReq.Bank.Name", "pb.Name", "left join pbr.Bank as pb", true));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "PayerBankReq.Bank.BIK", "pb.BIK", false));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "PayerBankReq.Bank.CorrAcc", "pb.CorrAcc", false));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "RecipientBankReq.Account", "rbr.Account", "left join d.RecipientBankReq as rbr", true));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "RecipientBankReq.Bank.Name", "rb.Name", "left join rbr.Bank as rb", true));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "RecipientBankReq.Bank.BIK", "rb.BIK", false));
    result.add(new TableEJBQLFieldDef(BankDocument.class, "RecipientBankReq.Bank.CorrAcc", "rb.CorrAcc", false));

    return result;
  }

}
