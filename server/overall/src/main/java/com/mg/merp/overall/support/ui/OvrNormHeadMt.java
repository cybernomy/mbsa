/*
 * OvrNormHeadMt.java
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
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.overall.NormSpecServiceLocal;
import com.mg.merp.overall.model.CatalogGroupsType;

import java.util.Set;

/**
 * Контроллер формы поддержки бизнес-компонента "Лицевые карточки сотрудников"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: OvrNormHeadMt.java,v 1.4 2008/06/30 04:22:00 alikaev Exp $
 */
public class OvrNormHeadMt extends DefaultMaintenanceForm implements MasterModelListener {
  protected AttributeMap overallProperties = new LocalDataTransferObject();
  protected AttributeMap formProperties = new LocalDataTransferObject();
  protected AttributeMap protectProperties = new LocalDataTransferObject();
  protected AttributeMap instrumentProperties = new LocalDataTransferObject();
  protected AttributeMap officeEqProperties = new LocalDataTransferObject();
  protected AttributeMap otherProperties = new LocalDataTransferObject();
  private MaintenanceTableController overall;
  private NormSpecServiceLocal overallService;
  private MaintenanceTableController form;
  private MaintenanceTableController protect;
  private MaintenanceTableController instrument;
  private MaintenanceTableController officeEq;
  private MaintenanceTableController other;

  public OvrNormHeadMt() throws Exception {
    setMasterDetail(true);
    addMasterModelListener(this);

    overallService = (NormSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/overall/NormSpec");

    overallProperties.put("CatalogGroupsTypeId", CatalogGroupsType.OVERALL);
    overall = new MaintenanceTableController(overallProperties);
    overall.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.OVERALL);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(overall);

    formProperties.put("CatalogGroupsTypeId", CatalogGroupsType.FORMCLOTHES);
    form = new MaintenanceTableController(formProperties);
    form.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.FORMCLOTHES);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(form);

    protectProperties.put("CatalogGroupsTypeId", CatalogGroupsType.PROTECTION);
    protect = new MaintenanceTableController(protectProperties);
    protect.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.PROTECTION);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(protect);

    instrumentProperties.put("CatalogGroupsTypeId", CatalogGroupsType.INSTRUMENT);
    instrument = new MaintenanceTableController(instrumentProperties);
    instrument.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.INSTRUMENT);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(instrument);

    officeEqProperties.put("CatalogGroupsTypeId", CatalogGroupsType.OFFICEEQUIP);
    officeEq = new MaintenanceTableController(officeEqProperties);
    officeEq.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.OFFICEEQUIP);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(officeEq);

    otherProperties.put("CatalogGroupsTypeId", CatalogGroupsType.OTHER);
    other = new MaintenanceTableController(otherProperties);
    other.initController(overallService, new OvrNormMaintenanceEJBQLTableModel() {
      protected String createQueryText() {
        super.createQueryText();
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("ovrNormHead");
        paramsValue.add(getEntity());
        paramsName.add("groupsType");
        paramsValue.add(CatalogGroupsType.OTHER);
        return String.format(INIT_QUERY_TEXT, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    });
    addMasterModelListener(other);

  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    overallProperties.put("OvrNormHead", event.getEntity());
    formProperties.put("OvrNormHead", event.getEntity());
    protectProperties.put("OvrNormHead", event.getEntity());
    instrumentProperties.put("OvrNormHead", event.getEntity());
    officeEqProperties.put("OvrNormHead", event.getEntity());
    otherProperties.put("OvrNormHead", event.getEntity());
  }

}
