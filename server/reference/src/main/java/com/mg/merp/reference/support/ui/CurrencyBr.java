/*
 * CurrencyBr.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultMDBrowseForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.reference.CurrencyRateServiceLocal;
import com.mg.merp.reference.model.Currency;
import com.mg.merp.reference.model.CurrencyRate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Браузер курсов валют
 *
 * @author Oleg V. Safonov
 * @version $Id: CurrencyBr.java,v 1.4 2006/09/29 05:37:06 leonova Exp $
 */
public class CurrencyBr extends DefaultMDBrowseForm implements MasterModelListener {
  private final String INIT_QUERY_TEXT = "select %s from Currency c";
  private final String INIT_QUERY_TEXT_DETAIL = "select %s from CurrencyRate cr %s where cr.Currency1.Id = :currencyId";
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  public CurrencyBr() throws Exception {
    super();
    this.detailService = (CurrencyRateServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/CurrencyRate");
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
        paramsName.add("currencyId");
        paramsValue.add(getMasterKey());
        return String.format(INIT_QUERY_TEXT_DETAIL, fieldsList, fromList);
      }

      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "Id", "cr.Id", true));
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "Currency2.Code", "cr.Currency2.Code", false));
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "CurrencyRateAuthority", "cra.Code", "left join cr.CurrencyRateAuthority as cra", false));
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "CurrencyRateType", "crt.Code", "left join cr.CurrencyRateType as crt", false));
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "Rate", "cr.Rate", false));
        result.add(new TableEJBQLFieldDef(CurrencyRate.class, "EffectiveDate", "cr.EffectiveDate", false));
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
        result.add(new TableEJBQLFieldDef(Currency.class, "Id", "c.Id", true));
        result.add(new TableEJBQLFieldDef(Currency.class, "Code", "c.Code", false));
        result.add(new TableEJBQLFieldDef(Currency.class, "FullName", "c.FullName", false));
        result.add(new TableEJBQLFieldDef(Currency.class, "Iso", "c.Iso", false));
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
    detailUIProperties.put("Currency1.Id", event.getModelKey()); //$NON-NLS-1$
  }

}
