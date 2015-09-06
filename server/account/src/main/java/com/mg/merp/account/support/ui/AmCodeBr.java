/*
 * AmCodeBr.java
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

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.AmRateServiceLocal;
import com.mg.merp.account.model.AmCode;
import com.mg.merp.account.model.AmRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: AmCodeBr.java,v 1.4 2006/09/29 07:11:39 leonova Exp $
 */
public class AmCodeBr extends DefaultMDBrowseForm implements MasterModelListener {
  private final String INIT_QUERY_TEXT = "select %s from AmCode ac";
  private final String INIT_QUERY_TEXT_DETAIL = "select %s from AmRate ar %s where ar.AmCode.Id = :amcodeId";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public AmCodeBr() throws Exception {
    super();
    this.detailService = (AmRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/AmRate");
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
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("amcodeId");
        paramsValue.add(getMasterKey());
        return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList, fromList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(AmRate.class, "Id", "ar.Id", true));
        result.add(new TableEJBQLFieldDef(AmRate.class, "ActMonth", "ar.ActMonth", false));
        result.add(new TableEJBQLFieldDef(AmRate.class, "AmRate", "ar.AmRate", false));
        result.add(new TableEJBQLFieldDef(AmRate.class, "AmRate1000", "ar.AmRate1000", false));
        result.add(new TableEJBQLFieldDef(AmRate.class, "PeriodYear", "ar.PeriodYear", false));
        result.add(new TableEJBQLFieldDef(AmRate.class, "VolumeProd", "ar.VolumeProd", false));
        result.add(new TableEJBQLFieldDef(AmRate.class, "PeriodMonth", "ar.PeriodMonth", false));
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
        result.add(new TableEJBQLFieldDef(AmCode.class, "Id", "ac.Id", true));
        result.add(new TableEJBQLFieldDef(AmCode.class, "Code", "ac.Code", false));
        result.add(new TableEJBQLFieldDef(AmCode.class, "CName", "ac.CName", false));
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
    detailUIProperties.put("AmCode.Id", event.getModelKey()); //$NON-NLS-1$
  }

}
