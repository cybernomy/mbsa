/*
 * OvrNormSpecMt.java
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
package com.mg.merp.overall.support.ui;

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
import com.mg.merp.overall.NormSpecLinkServiceLocal;
import com.mg.merp.overall.model.NormSpecLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес компонента "Спецификации норм выдачи спецодежды"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: OvrNormSpecMt.java,v 1.5 2008/10/22 13:18:10 safonov Exp $
 */
public class OvrNormSpecMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap specLinkProperties = new LocalDataTransferObject();
  private NormSpecLinkServiceLocal specLinkService;
  private MaintenanceTableController specLink;

  public OvrNormSpecMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    specLinkService = (NormSpecLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/overall/NormSpecLink");

    specLink = new MaintenanceTableController(specLinkProperties);
    specLink.initController(specLinkService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from NormSpecLink ns %s where ns.OvrNormSpec = :ovrNormSpec";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormSpec");
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
        result.add(new TableEJBQLFieldDef(NormSpecLink.class, "Id", "ns.Id", true));
        result.add(new TableEJBQLFieldDef(NormSpecLink.class, "Catalog.Code", "ns.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(NormSpecLink.class, "Catalog.FullName", "ns.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(NormSpecLink.class, "Catalog.Measure1", "meas.Code", "left join ns.Catalog.Measure1 as meas", false));
        result.add(new TableEJBQLFieldDef(NormSpecLink.class, "Catalog.Measure2", "meas2.Code", "left join ns.Catalog.Measure2 as meas2", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, specLinkService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(specLink);

  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    specLinkProperties.put("OvrNormSpec", event.getEntity());
  }

}
