/*
 * BOMMt.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.generic.ui.DefaultCompoundMaintenanceForm;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.BOMLaborServiceLocal;
import com.mg.merp.mfreference.BOMMachineServiceLocal;
import com.mg.merp.mfreference.BOMMaterialServiceLocal;
import com.mg.merp.mfreference.BOMRouteServiceLocal;
import com.mg.merp.mfreference.model.BomLabor;
import com.mg.merp.mfreference.model.BomMachine;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.BomRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки состава изделия
 *
 * @author leonova
 * @version $Id: BOMMt.java,v 1.9 2009/03/06 07:21:44 safonov Exp $
 */
public class BOMMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
  protected AttributeMap bomRouteProperties = new LocalDataTransferObject();
  protected AttributeMap materialProperties = new LocalDataTransferObject();
  protected AttributeMap machineProperties = new LocalDataTransferObject();
  protected AttributeMap labourProperties = new LocalDataTransferObject();
  private DefaultTableController costDetailLine;
  private MaintenanceTableController bomRoute;
  private BOMRouteServiceLocal bomRouteService;
  private BomRoute currentBomRoute;
  private MaintenanceTableController material;
  private BOMMaterialServiceLocal materialService;
  private MaintenanceTableController machine;
  private BOMMachineServiceLocal machineService;
  private MaintenanceTableController labour;
  private BOMLaborServiceLocal labourService;

  public BOMMt() throws Exception {
    costDetailLine = new CostDetailTableController();
    addMasterModelListener(costDetailLine);

    bomRouteService = (BOMRouteServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/BOMRoute");
    bomRoute = new MaintenanceTableController(bomRouteProperties);
    bomRoute.initController(bomRouteService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from BomRoute br %s where br.Bom = :bom";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("bom");
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
        result.add(new TableEJBQLFieldDef(BomRoute.class, "Id", "br.Id", true));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "OperNum", "br.OperNum", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "Description", "br.Description", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "EffOnDate", "br.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "EffOffDate", "br.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "Efficiency", "br.Efficiency", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "MoveTicks", "br.MoveTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SetupTicks", "br.SetupTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "RunTicks", "br.RunTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SchedTicks", "br.SchedTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SchedOffsetTicks", "br.SchedOffsetTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "QueueTicks", "br.QueueTicks", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SchedOffSetTimeUM", "br.SchedOffSetTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SetupTimeUM", "br.SetupTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "MoveTimeUM", "br.MoveTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "SchedTimeUM", "br.SchedTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "RunTimeUM", "br.RunTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "QueueTimeUM", "br.QueueTimeUM", false));
        result.add(new TableEJBQLFieldDef(BomRoute.class, "WorkCenter", "wc.WcCode", "left join br.WorkCenter as wc", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, bomRouteService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    bomRoute.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        currentBomRoute = event.getModelKey() == null ? null : bomRouteService.load((Integer) event.getModelKey());
        materialProperties.put("BomRoute", currentBomRoute);
        machineProperties.put("BomRoute", currentBomRoute);
        labourProperties.put("BomRoute", currentBomRoute);
        material.refresh();
        machine.refresh();
        labour.refresh();
      }

    });
    addMasterModelListener(bomRoute);

    materialService = (BOMMaterialServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/BOMMaterial");
    material = new MaintenanceTableController(materialProperties);
    material.initController(materialService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from BomMaterial bm %s where bm.BomRoute = :bomroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("bomroute");
        paramsValue.add(currentBomRoute);
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
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Id", "bm.Id", true));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "TimeSequence", "bm.TimeSequence", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "EffOnDate", "bm.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "EffOffDate", "bm.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Comment", "bm.Comment", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "ResourceGroup", "gr.ResourceGroupCode", "left join bm.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Revision", "bm.Revision", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "ViewSequence", "bm.ViewSequence", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "ReportSequence", "bm.ReportSequence", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Probable", "bm.Probable", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "QuantityRateFlag", "bm.QuantityRateFlag", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "ScrapFactor", "bm.ScrapFactor", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlBackflushFlag", "bm.MtlBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlOhAllocationFlag", "bm.MtlOhAllocationFlag", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlOhRate", "bm.MtlOhRate", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Currency", "bm.Currency.Code", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlOhRatio", "bm.MtlOhRatio", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlOhBackflushFlag", "bm.MtlOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Catalog.Code", "bm.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Catalog.FullName", "bm.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "Catalog.Measure1", "meas.Code", "left join bm.Catalog.Measure1 as meas", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlCostCategory", "cost1.Code", "left join bm.MtlCostCategory as cost1", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "MtlOhCostCategory", "cost2.Code", "left join bm.MtlOhCostCategory as cost2", false));
        result.add(new TableEJBQLFieldDef(BomMaterial.class, "BackflushZone", "z.Code", "left join bm.BackflushZone as z", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, materialService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentBomRoute == null) {
          getRowList().clear();
          fireModelChange();
        } else
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    machineService = (BOMMachineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/BOMMachine");
    machine = new MaintenanceTableController(machineProperties);
    machine.initController(machineService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from BomMachine bm %s where bm.BomRoute = :bomroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("bomroute");
        paramsValue.add(currentBomRoute);
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
        result.add(new TableEJBQLFieldDef(BomMachine.class, "Id", "bm.Id", true));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "TimeSequence", "bm.TimeSequence", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "EffOnDate", "bm.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "EffOffDate", "bm.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "Comment", "bm.Comment", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "ResourceGroup", "gr.ResourceGroupCode", "left join bm.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "TimeRateFlag", "bm.TimeRateFlag", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "RunTicksMch", "bm.RunTicksMch", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchNumber", "bm.MchNumber", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchRecoveryFlag", "bm.MchRecoveryFlag", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchRate", "bm.MchRate", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchRateCurrency", "bm.MchRateCurrency.Code", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchBackflushFlag", "bm.MchBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhAllocationFlag", "bm.MchOhAllocationFlag", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhRate", "bm.MchOhRate", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhRateCurrency", "bm.MchOhRateCurrency.Code", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhRatio", "bm.MchOhRatio", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhBackflushFlag", "bm.MchOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "RunTimeMchUm", "bm.RunTimeMchUm.Code", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchCostCategory", "cost1.Code", "left join bm.MchCostCategory as cost1", false));
        result.add(new TableEJBQLFieldDef(BomMachine.class, "MchOhCostCategory", "cost2.Code", "left join bm.MchOhCostCategory as cost2", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, machineService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentBomRoute == null) {
          getRowList().clear();
          fireModelChange();
        } else
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    labourService = (BOMLaborServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/mfreference/BOMLabor");
    labour = new MaintenanceTableController(labourProperties);
    labour.initController(labourService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from BomLabor bl %s where bl.BomRoute = :bomroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("bomroute");
        paramsValue.add(currentBomRoute);
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
        result.add(new TableEJBQLFieldDef(BomLabor.class, "Id", "bl.Id", true));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "TimeSequence", "bl.TimeSequence", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "EffOnDate", "bl.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "EffOffDate", "bl.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "Comment", "bl.Comment", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "ResourceGroup", "gr.ResourceGroupCode", "left join bl.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "LaborClass", "bl.LaborClass.Description", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "RunTicksLbr", "bl.RunTicksLbr", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "LbrNumber", "bl.LbrNumber", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "LbrBackflushFlag", "bl.LbrBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "LbrOhBackflushFlag", "bl.LbrOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(BomLabor.class, "RunTimeLbrUm", "meas.Code", "left join bl.RunTimeLbrUm as meas", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, labourService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentBomRoute == null) {
          getRowList().clear();
          fireModelChange();
        } else
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    bomRouteProperties.put("Bom", event.getEntity());
  }

}
