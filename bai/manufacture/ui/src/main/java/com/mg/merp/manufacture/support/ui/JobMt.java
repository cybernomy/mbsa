/*
 * JobMt.java
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
package com.mg.merp.manufacture.support.ui;

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
import com.mg.merp.manufacture.JobLaborServiceLocal;
import com.mg.merp.manufacture.JobMachineServiceLocal;
import com.mg.merp.manufacture.JobMaterialServiceLocal;
import com.mg.merp.manufacture.JobRouteServiceLocal;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.manufacture.model.JobMachine;
import com.mg.merp.manufacture.model.JobMaterial;
import com.mg.merp.manufacture.model.JobRoute;
import com.mg.merp.mfreference.support.ui.CostDetailTableController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки заказ-нарядов на производство
 *
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: JobMt.java,v 1.8 2009/03/06 07:20:57 safonov Exp $
 */
public class JobMt extends DefaultCompoundMaintenanceForm implements MasterModelListener {
  protected AttributeMap jobRouteProperties = new LocalDataTransferObject();
  protected AttributeMap stdDetailLineProperties = new LocalDataTransferObject();
  protected AttributeMap actDetailLineProperties = new LocalDataTransferObject();
  protected AttributeMap jobMaterialProperties = new LocalDataTransferObject();
  protected AttributeMap jobMachineProperties = new LocalDataTransferObject();
  protected AttributeMap jobLaborProperties = new LocalDataTransferObject();
  private MaintenanceTableController jobRoute;
  private JobRouteServiceLocal jobRouteService;
  private DefaultTableController stdDetailLine;
  private DefaultTableController actDetailLine;
  private JobRoute currentJobRoute;
  private MaintenanceTableController jobMaterial;
  private JobMaterialServiceLocal jobMaterialService;
  private MaintenanceTableController jobMachine;
  private JobMachineServiceLocal jobMachineService;
  private MaintenanceTableController jobLabor;
  private JobLaborServiceLocal jobLaborService;

  public JobMt() throws Exception {
    super();
    jobRouteService = (JobRouteServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/JobRoute");
    jobRoute = new MaintenanceTableController(jobRouteProperties);
    jobRoute.initController(jobRouteService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from JobRoute j %s where j.Job = :job";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("job");
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
        result.add(new TableEJBQLFieldDef(JobRoute.class, "Id", "j.Id", true));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "OperNum", "j.OperNum", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "Description", "j.Description", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "EffOnDate", "j.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "EffOffDate", "j.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "EfficiencyFactor", "j.EfficiencyFactor", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "StartDate", "j.StartDate", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "EndDate", "j.EndDate", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "StartTick", "j.StartTick", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "EndTick", "j.EndTick", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "MoveTicks", "j.MoveTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SetupTicks", "j.SetupTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "RunTicks", "j.RunTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SchedTicks", "j.SchedTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SchedOffsetTicks", "j.SchedOffsetTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QueueTicks", "j.QueueTicks", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SchedOffSetTimeUM", "j.SchedOffSetTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SetupTimeUM", "j.SetupTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "MoveTimeUM", "j.MoveTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "SchedTimeUM", "j.SchedTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "RunTimeUM", "j.RunTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QueueTimeUM", "j.QueueTimeUM", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "WorkCenter", "wc.WcCode", "left join j.WorkCenter as wc", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "FreezeScheduleFlag", "j.FreezeScheduleFlag", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyReceived", "j.QtyReceived", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyComplete", "j.QtyComplete", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyScrapped", "j.QtyScrapped", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "QtyMoved", "j.QtyMoved", false));
        result.add(new TableEJBQLFieldDef(JobRoute.class, "ControlPointFlag", "j.ControlPointFlag", false));

        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, jobRouteService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    jobRoute.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        currentJobRoute = event.getModelKey() == null ? null : jobRouteService.load((Integer) event.getModelKey());
        jobMaterialProperties.put("Oper", currentJobRoute);
        jobMachineProperties.put("Oper", currentJobRoute);
        jobLaborProperties.put("Oper", currentJobRoute);
        jobMaterial.refresh();
        jobMachine.refresh();
        jobLabor.refresh();
      }

    });
    addMasterModelListener(jobRoute);

    stdDetailLine = new CostDetailTableController("StdCostDetail");
    addMasterModelListener(stdDetailLine);

    actDetailLine = new CostDetailTableController("ActWipCostDetail");
    addMasterModelListener(actDetailLine);


    jobMaterialService = (JobMaterialServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/JobMaterial");
    jobMaterial = new MaintenanceTableController(jobMaterialProperties);
    jobMaterial.initController(jobMaterialService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from JobMaterial jm %s where jm.Oper = :jobroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("jobroute");
        paramsValue.add(currentJobRoute);
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
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Id", "jm.Id", true));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "TimeSequence", "jm.TimeSequence", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "EffOnDate", "jm.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "EffOffDate", "jm.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Comment", "jm.Comment", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "ResourceGroup", "gr.ResourceGroupCode", "left join jm.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Revision", "jm.Revision", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "ViewSequence", "jm.ViewSequence", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "ReportSequence", "jm.ReportSequence", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlQty", "jm.MtlQty", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "QuantityRateFlag", "jm.QuantityRateFlag", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlBackflushFlag", "jm.MtlBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlOhAllocationFlag", "jm.MtlOhAllocationFlag", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlOhRate", "jm.MtlOhRate", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlOhRatio", "jm.MtlOhRatio", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Currency", "jm.Currency.Code", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlOhBackflushFlag", "jm.MtlOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Catalog.Code", "jm.Catalog.Code", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Catalog.FullName", "jm.Catalog.FullName", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Catalog.Measure1", "meas.Code", "left join jm.Catalog.Measure1 as meas", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "Measure", "jm.Measure.Code", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlOhCostCategory", "cost1.Code", "left join jm.MtlOhCostCategory as cost1", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "MtlCostCategory", "cost2.Code", "left join jm.MtlCostCategory as cost2", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "ScrapFactor", "jm.ScrapFactor", false));
        result.add(new TableEJBQLFieldDef(JobMaterial.class, "BackflushZone", "z.Code", "left join jm.BackflushZone as z", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, jobMaterialService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentJobRoute == null) {
          getRowList().clear();
          fireModelChange();
        } else
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    jobMachineService = (JobMachineServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/JobMachine");
    jobMachine = new MaintenanceTableController(jobMachineProperties);
    jobMachine.initController(jobMachineService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from JobMachine jm %s where jm.Oper = :jobroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("jobroute");
        paramsValue.add(currentJobRoute);
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
        result.add(new TableEJBQLFieldDef(JobMachine.class, "Id", "jm.Id", true));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "TimeSequence", "jm.TimeSequence", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "EffOnDate", "jm.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "EffOffDate", "jm.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "Comment", "jm.Comment", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "ResourceGroup", "gr.ResourceGroupCode", "left join jm.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "TimeRateFlag", "jm.TimeRateFlag", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchNumber", "jm.MchNumber", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "RunTicksMch", "jm.RunTicksMch", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchRecoveryFlag", "jm.RunTicksMch", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchRate", "jm.MchRate", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchBackflushFlag", "jm.MchBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhRateCurrency", "currate.Code", "left join jm.MchOhRateCurrency as currate", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhAllocationFlag", "jm.MchOhAllocationFlag", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhRate", "jm.MchOhRate", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchRateCurrency", "jm.MchRateCurrency.Code", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhRatio", "jm.MchOhRatio", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhBackflushFlag", "jm.MchOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchCostCategory", "cost1.Code", "left join jm.MchCostCategory as cost1", false));
        result.add(new TableEJBQLFieldDef(JobMachine.class, "MchOhCostCategory", "cost2.Code", "left join jm.MchOhCostCategory as cost2", false));
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, jobMachineService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentJobRoute == null) {
          getRowList().clear();
          fireModelChange();
        } else
          setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    jobRoute.addMasterModelListener(jobMachine);

    jobLaborService = (JobLaborServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/manufacture/JobLabor");
    jobLabor = new MaintenanceTableController(jobLaborProperties);
    jobLabor.initController(jobLaborService, new DefaultMaintenanceEJBQLTableModel() {
      private final String INIT_QUERY_TEXT = "select %s from JobLabor jl %s where jl.Oper = :jobroute";
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("jobroute");
        paramsValue.add(currentJobRoute);
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
        result.add(new TableEJBQLFieldDef(JobLabor.class, "Id", "jl.Id", true));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "EffOnDate", "jl.EffOnDate", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "EffOffDate", "jl.EffOffDate", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "Comment", "jl.Comment", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "ResourceGroup", "gr.ResourceGroupCode", "left join jl.ResourceGroup as gr", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "TimeRateFlag", "jl.TimeRateFlag", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrNumber", "jl.LbrNumber", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrRate", "jl.LbrRate", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "RunTicksLbr", "jl.RunTicksLbr", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrBackflushFlag", "jl.LbrBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhAllocationFlag", "jl.LbrOhAllocationFlag", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhRate", "jl.LbrOhRate", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhRatio", "jl.LbrOhRatio", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhBackflushFlag", "jl.LbrOhBackflushFlag", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhCostCategory", "cost1.Code", "left join jl.LbrOhCostCategory as cost1", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrCostCategory", "cost2.Code", "left join jl.LbrCostCategory as cost2", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "RunTimeLbrUm", "run.Code", "left join jl.RunTimeLbrUm as run", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrOhRateCurrency", "cur1.Code", "left join jl.LbrOhRateCurrency as cur1", false));
        result.add(new TableEJBQLFieldDef(JobLabor.class, "LbrRateCurrency", "cur2.Code", "left join jl.LbrRateCurrency as cur2", false));


        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, jobLaborService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        if (currentJobRoute == null) {
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
    jobRouteProperties.put("Job", event.getEntity());
    stdDetailLineProperties.put("Job", event.getEntity());
    actDetailLineProperties.put("Job", event.getEntity());
  }

}
