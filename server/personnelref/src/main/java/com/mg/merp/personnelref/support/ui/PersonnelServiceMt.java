/*
 * PersonnelServiceMt.java
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
package com.mg.merp.personnelref.support.ui;

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
import com.mg.merp.personnelref.PFCodeInServiceServiceLocal;
import com.mg.merp.personnelref.model.PfCodeInService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: PersonnelServiceMt.java,v 1.6 2006/09/29 11:04:27 leonova Exp $
 */
public class PersonnelServiceMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap codePFProperties = new LocalDataTransferObject();
  private MaintenanceTableController codePF;
  private PFCodeInServiceServiceLocal codePFService;

  public PersonnelServiceMt() throws Exception {
    addMasterModelListener(this);

    codePFService = (PFCodeInServiceServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PFCodeInService");
    codePF = new MaintenanceTableController(codePFProperties);
    codePF.initController(codePFService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from PfCodeInService cs where cs.PersonnelService = :personnelService";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personnelService");
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
        result.add(new TableEJBQLFieldDef(PfCodeInService.class, "Id", "cs.Id", true));
        result.add(new TableEJBQLFieldDef(PfCodeInService.class, "PfCode", "cs.PfCode.Code", false));
        result.add(new TableEJBQLFieldDef(PfCodeInService.class, "Comment", "cs.Comment", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, codePFService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(codePF);


  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    codePFProperties.put("PersonnelService", event.getEntity());
  }

}
