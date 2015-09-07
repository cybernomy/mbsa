/*
 * RiseBr.java
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

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.personnelref.RiseScaleServiceLocal;
import com.mg.merp.personnelref.model.Rise;
import com.mg.merp.personnelref.model.RiseScale;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер надбавок
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: RiseBr.java,v 1.4 2006/09/29 05:38:06 leonova Exp $
 */
public class RiseBr extends DefaultMDBrowseForm implements MasterModelListener {
  private final String INIT_QUERY_TEXT = "select %s from Rise r";
  private final String INIT_QUERY_TEXT_DETAIL = "select %s from RiseScale rc where rc.Rise.Id = :riseId";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public RiseBr() throws Exception {
    super();
    this.detailService = (RiseScaleServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/RiseScale");
    master.addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createDetailModel()
   */
  @Override
  protected MaintenanceTableModel createDetailModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("riseId");
        paramsValue.add(getMasterKey());
        return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(RiseScale.class, "Id", "rc.Id", true));
        result.add(new TableEJBQLFieldDef(RiseScale.class, "ScaleNumber", "rc.ScaleNumber", false));
        result.add(new TableEJBQLFieldDef(RiseScale.class, "SName", "rc.SName", false));
        result.add(new TableEJBQLFieldDef(RiseScale.class, "BeginDate", "rc.BeginDate", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, detailService);

      }

      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#createMasterModel()
   */
  @Override
  protected MaintenanceTableModel createMasterModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);

        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Rise.class, "Id", "r.Id", true));
        result.add(new TableEJBQLFieldDef(Rise.class, "RCode", "r.RCode", false));
        result.add(new TableEJBQLFieldDef(Rise.class, "RName", "r.RName", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, masterService);

      }

      @Override
      protected void doLoad() {
        setQuery(createQueryText());
      }

      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

    };
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    detailUIProperties.put("Rise.Id", event.getModelKey()); //$NON-NLS-1$
  }
}

