/*
 * PlanItemMaintenanceEJBQLTableModel.java
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
package com.mg.merp.contract.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.contract.PhasePlanItemServiceLocal;
import com.mg.merp.contract.model.PhasePlanItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: PlanItemMaintenanceEJBQLTableModel.java,v 1.3 2007/11/22 15:59:26 sharapov Exp $
 */
public class PlanItemMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  protected final String INIT_QUERY_TEXT = "select %s from Phase as p, PhasePlanItem ppi %s where p.DocHead = :dochead and ppi.ContractPhase = p.Id";
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected PhasePlanItemServiceLocal phaseItemPlanService;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
   */
  @Override
  protected int getPrimaryKeyFieldIndex() {
    return 0;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
   */
  @Override
  protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
    Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "Id", "ppi.Id", true));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "ItemNumber", "ppi.ItemNumber", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "Kind", "ppi.Kind", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "BeginActionDate", "ppi.BeginActionDate", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "EndActionDate", "ppi.EndActionDate", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "From", "f.Code", "left join ppi.From as f", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "To", "t.Code", "left join ppi.To as t", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "PlanSum", "ppi.PlanSum", false));
    result.add(new TableEJBQLFieldDef(PhasePlanItem.class, "FactSum", "ppi.FactSum", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, phaseItemPlanService);
  }

}