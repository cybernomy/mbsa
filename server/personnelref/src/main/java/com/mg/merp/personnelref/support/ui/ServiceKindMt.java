/*
 * ServiceKindMt.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
import com.mg.merp.personnelref.PFCodeKindInServiceKindServiceLocal;
import com.mg.merp.personnelref.model.PfCodeKindInServiceKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки "Виды стажа"
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ServiceKindMt.java,v 1.5 2007/07/18 12:12:57 sharapov Exp $
 */
public class ServiceKindMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap pFCodeKindInServiceKindProperties = new LocalDataTransferObject();
  private MaintenanceTableController pFCodeKindInServiceKind;
  private PFCodeKindInServiceKindServiceLocal pFCodeKindInServiceKindService;


  public ServiceKindMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    pFCodeKindInServiceKindService = (PFCodeKindInServiceKindServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/PFCodeKindInServiceKind"); //$NON-NLS-1$
    pFCodeKindInServiceKind = new MaintenanceTableController(pFCodeKindInServiceKindProperties);
    pFCodeKindInServiceKind.initController(pFCodeKindInServiceKindService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from PfCodeKindInServiceKind ck %s where ck.ServiceKind = :serviceKind"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("serviceKind"); //$NON-NLS-1$
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
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
        result.add(new TableEJBQLFieldDef(PfCodeKindInServiceKind.class, "Id", "ck.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PfCodeKindInServiceKind.class, "PfCodeKind", "ck.PfCodeKind.FName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PfCodeKindInServiceKind.class, "Ratio", "ck.Ratio", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, pFCodeKindInServiceKindService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(pFCodeKindInServiceKind);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    pFCodeKindInServiceKindProperties.put("ServiceKind", event.getEntity()); //$NON-NLS-1$
  }

}
