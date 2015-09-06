/*
 * TaxScaleBr.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.TaxRateServiceLocal;
import com.mg.merp.salary.model.TaxRate;
import com.mg.merp.salary.model.TaxScale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер браузера Налоговых шкал
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TaxScaleBr.java,v 1.6 2007/07/17 08:36:20 sharapov Exp $
 */
public class TaxScaleBr extends DefaultMDBrowseForm implements MasterModelListener {

  private final String INIT_QUERY_TEXT = "select %s from TaxScale ts where ts.TaxHead.Id = :taxHeadId"; //$NON-NLS-1$
  private final String INIT_QUERY_TEXT_DETAIL = "select %s from TaxRate tr where tr.TaxScale.Id = :taxScaleId"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Serializable taxHeadId;


  public TaxScaleBr() throws Exception {
    super();
    this.detailService = (TaxRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/TaxRate"); //$NON-NLS-1$
    master.addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMDBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    masterUIProperties.put("TaxHead.Id", (Integer) taxHeadId); //$NON-NLS-1$
    super.doOnRun();
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
        paramsName.add("taxScaleId"); //$NON-NLS-1$
        paramsValue.add(getMasterKey());
        return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(TaxRate.class, "Id", "tr.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "RNumber", "tr.RNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "MinIncome", "tr.MinIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "MaxIncome", "tr.MaxIncome", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "TaxPercent", "tr.TaxPercent", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "ConstValue", "tr.ConstValue", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxRate.class, "Privilegeratio", "tr.Privilegeratio", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, detailService);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
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
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("taxHeadId"); //$NON-NLS-1$
        paramsValue.add((Integer) taxHeadId);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(TaxScale.class, "Id", "ts.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxScale.class, "SNumber", "ts.SNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxScale.class, "BeginDate", "ts.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxScale.class, "TaxPayer", "ts.TaxPayer", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(TaxScale.class, "SName", "ts.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, masterService);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    };
  }

  public void masterChange(ModelChangeEvent event) {
    detailUIProperties.put("TaxScale.Id", event.getModelKey()); //$NON-NLS-1$
  }

  /**
   * Установить идентификатор налоговой сетки
   *
   * @param taxHeadId - идентификатор налоговой сетки
   */
  public void setTaxHeadId(Serializable taxHeadId) {
    this.taxHeadId = taxHeadId;
  }

}
