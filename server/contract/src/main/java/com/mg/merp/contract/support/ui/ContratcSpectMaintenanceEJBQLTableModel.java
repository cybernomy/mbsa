/*
 * ContratcSpectMaintenanceEJBQLTableModel.java
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
import com.mg.merp.contract.ContractSpecServiceLocal;
import com.mg.merp.contract.model.ContractSpec;

import java.util.Set;

/**
 * @author leonova
 * @version $Id: ContratcSpectMaintenanceEJBQLTableModel.java,v 1.1 2006/09/01 08:01:00 leonova Exp
 *          $
 */
public class ContratcSpectMaintenanceEJBQLTableModel extends
    DefaultMaintenanceEJBQLTableModel {

  private ContractSpecServiceLocal specService;

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
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Id", "cs.Id", true));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Catalog.Code", "cat.Code", "left join cs.Catalog as cat", true));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Catalog.FullName", "cat.FullName", false));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Catalog.Measure1", "meas.Code", "left join cat.Measure1 as meas", false));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Quantity", "cs.Quantity", false));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Price", "cs.Price", false));
    result.add(new TableEJBQLFieldDef(ContractSpec.class, "Summa", "cs.Summa", false));
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specService);
  }

}
