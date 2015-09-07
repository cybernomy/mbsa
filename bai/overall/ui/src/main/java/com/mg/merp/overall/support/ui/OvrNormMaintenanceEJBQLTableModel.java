/*
 * OvrNormMaintenanceEJBQLTableModel.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.overall.support.ui;

import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.overall.NormSpecServiceLocal;
import com.mg.merp.overall.model.NormSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Класс для отображения списка спецификации норм
 *
 * @author leonova
 * @version $Id: OvrNormMaintenanceEJBQLTableModel.java,v 1.2 2008/06/30 04:22:00 alikaev Exp $
 */
public class OvrNormMaintenanceEJBQLTableModel extends DefaultMaintenanceEJBQLTableModel {

  protected final String INIT_QUERY_TEXT = "select %s from NormSpec ns where ns.OvrNormHead = :ovrNormHead and ns.CatalogGroupsTypeId = :groupsType"; //$NON-NLS-1$
  protected List<String> paramsName = new ArrayList<String>();
  protected List<Object> paramsValue = new ArrayList<Object>();
  protected NormSpecServiceLocal service;
  protected String fieldsList;

  protected String createQueryText() {
    return String.format(INIT_QUERY_TEXT, fieldsList);
  }

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
    result.add(new TableEJBQLFieldDef(NormSpec.class, "Id", "ns.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "OvrNormSpecName", "ns.OvrNormSpecName", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "MeasureUpCode", "ns.MeasureUpCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "ShelfLife", "ns.ShelfLife", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "ShelfLifeMeas", "ns.ShelfLifeMeas", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "IsDinch", "ns.IsDinch", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "IsPeriodic", "ns.IsPeriodic", false)); //$NON-NLS-1$ //$NON-NLS-2$
    result.add(new TableEJBQLFieldDef(NormSpec.class, "IsBasic", "ns.IsBasic", false));         //$NON-NLS-1$ //$NON-NLS-2$
    return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
   */
  @Override
  protected void doLoad() {
    setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
  }

}
