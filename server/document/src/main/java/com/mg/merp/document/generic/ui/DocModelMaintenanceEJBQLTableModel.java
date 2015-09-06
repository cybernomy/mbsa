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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.model.DocHeadModel;

import java.io.Serializable;
import java.util.Set;

/**
 * Базовый класс для отобрадения формы списка образцов документов
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: DocModelMaintenanceEJBQLTableModel.java,v 1.3 2008/02/13 14:40:48 alikaev Exp $
 */
public class DocModelMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  protected Set<TableEJBQLFieldDef> result;
  protected DataBusinessObjectService<PersistentObject, Serializable> service;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "Id", "dhm.Id", true));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "ModelDestFolder", "mf.FName", "left join dhm.ModelDestFolder as mf", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "ModelName", "dhm.ModelName", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "DocType", "dt.Code", "left join dhm.DocType as dt", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "DocNumber", "dhm.DocNumber", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "DocDate", "dhm.DocDate", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "BaseDocType", "bdt.Code", "left join dhm.BaseDocType as bdt", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "BaseDocNumber", "dhm.BaseDocNumber", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "BaseDocDate", "dhm.BaseDocDate", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "Currency", "cur.Code", "left join dhm.Currency as cur", false));
//		result.add(new TableEJBQLFieldDef(DocHeadModel.class, "CurCource", "dhm.CurCource", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "SumNat", "dhm.SumNat", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "SumCur", "dhm.SumCur", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "From", "f.Code", "left join dhm.From as f", false));
    result.add(new TableEJBQLFieldDef(DocHeadModel.class, "To", "tcode.Code", "left join dhm.To as tcode", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
   */
  @Override
  protected int getPrimaryKeyFieldIndex() {
    return 0;
  }

}
