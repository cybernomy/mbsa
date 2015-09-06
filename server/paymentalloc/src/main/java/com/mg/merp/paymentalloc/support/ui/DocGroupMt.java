/*
 * DocGroupMt.java
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
package com.mg.merp.paymentalloc.support.ui;

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
import com.mg.merp.paymentalloc.DocGroupLinkServiceLocal;
import com.mg.merp.paymentalloc.model.DocGroupLink;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки групп документов
 *
 * @author leonova
 * @version $Id: DocGroupMt.java,v 1.5 2007/06/22 13:09:52 sharapov Exp $
 */
public class DocGroupMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap typeDocProperties = new LocalDataTransferObject();
  private MaintenanceTableController typeDoc;
  private DocGroupLinkServiceLocal typeDocService;

  public DocGroupMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    typeDocService = (DocGroupLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/paymentalloc/DocGroupLink");
    typeDoc = new MaintenanceTableController(typeDocProperties);
    typeDoc.initController(typeDocService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from DocGroupLink dgl %s where dgl.DocGroup = :docgroup";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("docgroup");
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
        result.add(new TableEJBQLFieldDef(DocGroupLink.class, "Id", "dgl.Id", true));
        result.add(new TableEJBQLFieldDef(DocGroupLink.class, "DocType", "dt.Code", "left join dgl.DocType as dt", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, typeDocService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(typeDoc);

  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    typeDocProperties.put("DocGroup", event.getEntity());
  }

}
