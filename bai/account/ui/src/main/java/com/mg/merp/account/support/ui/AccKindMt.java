/*
 * AccKindMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.account.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.AccGroupServiceLocal;
import com.mg.merp.account.model.AccGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: AccKindMt.java,v 1.4 2006/10/02 06:24:57 leonova Exp $
 */
public class AccKindMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap accGroupProperties = new LocalDataTransferObject();
  private MaintenanceTableController accGroup;
  private AccGroupServiceLocal accGroupService;

  public AccKindMt() throws Exception {
    addMasterModelListener(this);

    accGroupService = (AccGroupServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AccGroup");
    accGroup = new MaintenanceTableController(accGroupProperties);
    accGroup.initController(accGroupService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from AccGroup ag where ag.AccKind = :acckind";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("acckind");
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
        result.add(new TableEJBQLFieldDef(AccGroup.class, "Id", "ag.Id", true));
        result.add(new TableEJBQLFieldDef(AccGroup.class, "GCode", "ag.GCode", false));
        result.add(new TableEJBQLFieldDef(AccGroup.class, "GName", "ag.GName", false));
        result.add(new TableEJBQLFieldDef(AccGroup.class, "MinUsagePeriod", "ag.MinUsagePeriod", false));
        result.add(new TableEJBQLFieldDef(AccGroup.class, "MaxUsagePeriod", "ag.MaxUsagePeriod", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, accGroupService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(accGroup);

  }

  public void masterChange(ModelChangeEvent event) {
    accGroupProperties.put("AccKind", event.getEntity());
  }

}
