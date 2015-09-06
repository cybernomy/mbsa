/*
 * OvrCardHistMt.java
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
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.overall.CancellationServiceLocal;
import com.mg.merp.overall.model.Cancellation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "История выдачи"
 *
 * @author leonova
 * @version $Id: OvrCardHistMt.java,v 1.2 2008/06/30 04:22:00 alikaev Exp $
 */
public class OvrCardHistMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {

  protected AttributeMap cancellationProperties = new LocalDataTransferObject();
  protected MaintenanceTableController cancellation;
  private CancellationServiceLocal cancellationService;


  public OvrCardHistMt() {
    super();
    addMasterModelListener(this);
    cancellationService = (CancellationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/overall/Cancellation");
    cancellation = new MaintenanceTableController(cancellationProperties);
    cancellation.initController(cancellationService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from Cancellation c %s where c.OvrCardHist = :ovrCardHist";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrCardHist");
        paramsValue.add(getEntity());
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Cancellation.class, "Id", "c.Id", true));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "CancellationDate", "c.CancellationDate", false));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "CurrentCancellationSumma", "c.CurrentCancellationSumma", false));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "DocType", "dt.UpCode", "left join c.DocType as dt", false));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "DocNumber", "c.DocNumber", false));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "DocDate", "c.DocDate", false));
        result.add(new TableEJBQLFieldDef(Cancellation.class, "CancellationReason", "c.CancellationReason", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, cancellationService);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

    });
    addMasterModelListener(cancellation);
    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    cancellationProperties.put("OvrCardHist", event.getEntity());
  }

}
