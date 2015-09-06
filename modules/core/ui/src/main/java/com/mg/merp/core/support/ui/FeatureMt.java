/*
 * FeatureMt.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.core.support.ui;

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
import com.mg.merp.core.FeatureLinkServiceLocal;
import com.mg.merp.core.FeatureValServiceLocal;
import com.mg.merp.core.model.FeatureLink;
import com.mg.merp.core.model.FeatureVal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Контроллер формы поддержки дополнительных признаков
 *
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: FeatureMt.java,v 1.8 2008/02/12 09:08:15 safonov Exp $
 */
public class FeatureMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
  protected AttributeMap valueProperties = new LocalDataTransferObject();
  protected AttributeMap featLinkProperties = new LocalDataTransferObject();
  private MaintenanceTableController value;
  private FeatureValServiceLocal valueService;
  private MaintenanceTableController featLink;
  private FeatureLinkServiceLocal featLinkService;

  public FeatureMt() throws Exception {
    addMasterModelListener(this);

    valueService = (FeatureValServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/core/FeatureVal");
    value = new MaintenanceTableController(valueProperties);
    value.initController(valueService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from FeatureVal fv where fv.Feature = :feature";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("feature");
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
        result.add(new TableEJBQLFieldDef(FeatureVal.class, "Id", "fv.Id", true));
        result.add(new TableEJBQLFieldDef(FeatureVal.class, "Val", "fv.Val", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, valueService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(value);

    featLinkService = (FeatureLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/core/FeatureLink");
    featLink = new MaintenanceTableController(featLinkProperties);
    featLink.initController(featLinkService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from FeatureLink fl %s where fl.Feature = :feature and fl.RecId = null";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("feature");
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
        result.add(new TableEJBQLFieldDef(FeatureLink.class, "Id", "fl.Id", true));
        result.add(new TableEJBQLFieldDef(FeatureLink.class, "SysClass", "sc.BeanName", "left join fl.SysClass as sc", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, featLinkService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(featLink);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    valueProperties.put("Feature", event.getEntity());
    featLinkProperties.put("Feature", event.getEntity());
  }
}