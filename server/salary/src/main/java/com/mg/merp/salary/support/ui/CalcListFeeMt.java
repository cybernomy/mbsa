/*
 * CalcListFeeMt.java
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
package com.mg.merp.salary.support.ui;

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
import com.mg.merp.salary.CalcListSectionServiceLocal;
import com.mg.merp.salary.model.CalcListFeeParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author leonova
 * @version $Id: CalcListFeeMt.java,v 1.1 2006/09/15 11:13:48 leonova Exp $
 */
public class CalcListFeeMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap paramProperties = new LocalDataTransferObject();
  private MaintenanceTableController param;
  private CalcListSectionServiceLocal paramService;

  public CalcListFeeMt() throws Exception {
    paramService = (CalcListSectionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/CalcListSection");
    param = new MaintenanceTableController(paramProperties);
    param.initController(paramService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from CalcListFeeParam clp %s where clp.CalcListFee = :calcListFee";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("calcListFee");
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
        result.add(new TableEJBQLFieldDef(CalcListFeeParam.class, "Id", "clp.Id", true));
        result.add(new TableEJBQLFieldDef(CalcListFeeParam.class, "FeeRefParam", "fp.PCode", "left join clp.FeeRefParam as fp", false));
        result.add(new TableEJBQLFieldDef(CalcListFeeParam.class, "ParamValue", "clp.ParamValue", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, paramService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(param);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    paramProperties.put("CalcListFee", event.getEntity());
  }
}
