/*
 * WorkCenterMt.java
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.WorkCenterRatesServiceLocal;
import com.mg.merp.mfreference.model.WorkCenterRates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Рабочий центр"
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: WorkCenterMt.java,v 1.5 2008/03/04 11:45:10 alikaev Exp $
 */
public class WorkCenterMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
  protected AttributeMap workCenterRatesProperties = new LocalDataTransferObject();
  private MaintenanceTableController workCenterRates;
  private WorkCenterRatesServiceLocal workCenterRatesService;

  public WorkCenterMt() throws Exception {
    super();
    addMasterModelListener(this);
    workCenterRatesService = (WorkCenterRatesServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/WorkCenterRates"); //$NON-NLS-1$
    workCenterRates = new MaintenanceTableController(workCenterRatesProperties);
    workCenterRates.initController(workCenterRatesService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from WorkCenterRates wcr where wcr.WorkCenter = :workcenter"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("workcenter"); //$NON-NLS-1$
        paramsValue.add(getEntity());
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
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "Id", "wcr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "EffOnDate", "wcr.EffOnDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "EffOffDate", "wcr.EffOffDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "MchFohRate", "wcr.MchFohRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "MchVohRate", "wcr.MchVohRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "LbrRunRate", "wcr.LbrRunRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "LbrSetupRate", "wcr.LbrSetupRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "LbrFohRate", "wcr.LbrFohRate", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(WorkCenterRates.class, "LbrVohRate", "wcr.LbrVohRate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, workCenterRatesService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(workCenterRates);

    addMasterModelListener(this);
  }

  public void masterChange(ModelChangeEvent event) {
    workCenterRatesProperties.put("WorkCenter", event.getEntity()); //$NON-NLS-1$
  }
}
