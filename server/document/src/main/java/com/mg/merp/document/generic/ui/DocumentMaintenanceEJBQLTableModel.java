/*
 * DocumentMaintenanceEJBQLTableModel.java
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
package com.mg.merp.document.generic.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.document.model.DocHead;

import java.util.Set;

/**
 * Базовый класс для отобрадения формы списка документов
 *
 * @author leonova
 * @version $Id: DocumentMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:04:52 safonov Exp $
 */
public class DocumentMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHead.class, "Id", "d.Id", true));
    result.add(new TableEJBQLFieldDef(DocHead.class, "DocType", "d.DocType.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "DocNumber", "d.DocNumber", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "DocDate", "d.DocDate", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "BaseDocType", "bdt.Code", "left join d.BaseDocType as bdt", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "BaseDocNumber", "d.BaseDocNumber", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "BaseDocDate", "d.BaseDocDate", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "Currency", "d.Currency.Code", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "CurCource", "d.CurCource", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "SumNat", "d.SumNat", false));
    result.add(new TableEJBQLFieldDef(DocHead.class, "SumCur", "d.SumCur", false));
    return result;
  }

}
