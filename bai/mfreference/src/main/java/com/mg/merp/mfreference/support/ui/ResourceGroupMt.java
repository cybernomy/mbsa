/*
 * ResourceGroupMt.java
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
import com.mg.merp.mfreference.ResourceGroupCapacityServiceLocal;
import com.mg.merp.mfreference.model.ResourceGroupCapacity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки групп ресурсов
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ResourceGroupMt.java,v 1.5 2008/03/04 09:15:15 alikaev Exp $
 */
public class ResourceGroupMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap resGroupCapacityProperties = new LocalDataTransferObject();
  private MaintenanceTableController resGroupCapacity;
  private ResourceGroupCapacityServiceLocal resGroupCapacityService;

  public ResourceGroupMt() throws Exception {
    super();
    addMasterModelListener(this);

    resGroupCapacityService = (ResourceGroupCapacityServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/ResourceGroupCapacity");
    resGroupCapacity = new MaintenanceTableController(resGroupCapacityProperties);
    resGroupCapacity.initController(resGroupCapacityService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from ResourceGroupCapacity rgc where rgc.ResourceGroup = :resourcegroup";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("resourcegroup");
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
        result.add(new TableEJBQLFieldDef(ResourceGroupCapacity.class, "Id", "rgc.Id", true));
        result.add(new TableEJBQLFieldDef(ResourceGroupCapacity.class, "PlanningCapacity", "rgc.PlanningCapacity", false));
        result.add(new TableEJBQLFieldDef(ResourceGroupCapacity.class, "MaximumCapacity", "rgc.MaximumCapacity", false));
        result.add(new TableEJBQLFieldDef(ResourceGroupCapacity.class, "EffOnDate", "rgc.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(ResourceGroupCapacity.class, "EffOffDate", "rgc.EffOffDate", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, resGroupCapacityService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(resGroupCapacity);

  }

  public void masterChange(ModelChangeEvent event) {
    resGroupCapacityProperties.put("ResourceGroup", event.getEntity());
  }
}
