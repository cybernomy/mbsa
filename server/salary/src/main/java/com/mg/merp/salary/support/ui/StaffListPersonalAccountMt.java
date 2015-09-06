/*
 * StaffListPersonalAccountMt.java
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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.TariffingServiceLocal;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.model.Tariffing;
import com.mg.merp.salary.FeeModelServiceLocal;
import com.mg.merp.salary.PositionFillServiceLocal;
import com.mg.merp.salary.model.FeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержки "Лицевой счет сотрудника"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPersonalAccountMt.java,v 1.7 2009/03/06 07:25:38 safonov Exp $
 */
public class StaffListPersonalAccountMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap positionFillProperties = new LocalDataTransferObject();
  protected AttributeMap tariffProperties = new LocalDataTransferObject();
  protected AttributeMap feeProperties = new LocalDataTransferObject();
  private MaintenanceTableController positionFill;
  private PositionFillServiceLocal positionFillService;
  private MaintenanceTableController tariff;
  private TariffingServiceLocal tariffService;
  private MaintenanceTableController fee;
  private FeeModelServiceLocal feeService;
  private Integer staffListId;

  private PositionFill currentPositionFill;
  private String currentPositionUniqueId;


  public StaffListPersonalAccountMt() throws Exception {
    staffListId = getCalcPeriodService().getCurrentCalcPeriod().getStaffList().getId();

    positionFillService = (PositionFillServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/PositionFill"); //$NON-NLS-1$
    positionFill = new MaintenanceTableController(positionFillProperties);
    positionFill.initController(positionFillService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from PositionFill pf %s where pf.PersonalAccount = :personalAccount"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personalAccount"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Id", "pf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "SlPositionUnique", "pf.SlPositionUnique", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "Position", "p.Name", "left join pf.Position as p", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "PositionFillKind", "pfk.KCode", "left join pf.PositionFillKind as pfk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "BeginDate", "pf.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "EndDate", "pf.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "RateNumber", "pf.RateNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PositionFill.class, "IsBasic", "pf.IsBasic", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, positionFillService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(positionFill);

    tariffService = (TariffingServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/personnelref/Tariffing"); //$NON-NLS-1$
    tariff = new MaintenanceTableController(tariffProperties);
    tariff.initController(tariffService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from Tariffing tar %s where (tar.StaffList.Id = :staffListId and tar.SlPositionUniqueId = :positionUnique) or (tar.PositionFill = :positionFill)"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromsList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("staffListId"); //$NON-NLS-1$
        paramsValue.add(staffListId);
        paramsName.add("positionUnique"); //$NON-NLS-1$
        paramsValue.add(currentPositionUniqueId); //$NON-NLS-1$
        paramsName.add("positionFill"); //$NON-NLS-1$
        paramsValue.add(currentPositionFill); //$NON-NLS-1$
        return String.format(INIT_QUERY_TEXT, fieldsList, fromsList);
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
        result.add(new TableEJBQLFieldDef(Tariffing.class, "Id", "tar.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "Category", "tar.Category.CCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "BeginDate", "tar.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "EndDate", "tar.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "MinSalaryNumber", "tar.MinSalaryNumber", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "TariffScaleCode", "tar.TariffScaleCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "UseRiseReference", "tar.UseRiseReference", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "RiseValue", "tar.RiseValue", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "Rise", "r.RCode", "left join tar.Rise as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "RateOfSalary", "tar.RateOfSalary", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl1", "anl1.ACode", "left join tar.CostsAnl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl2", "anl2.ACode", "left join tar.CostsAnl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl3", "anl3.ACode", "left join tar.CostsAnl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl4", "anl4.ACode", "left join tar.CostsAnl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Tariffing.class, "CostsAnl5", "anl5.ACode", "left join tar.CostsAnl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, tariffService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(tariff);

    positionFill.addMasterModelListener(new MasterModelListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
       */
      public void masterChange(ModelChangeEvent event) {
        currentPositionFill = event.getModelKey() == null ? null : positionFillService.load((Integer) event.getModelKey());
        currentPositionUniqueId = currentPositionFill == null ? null : currentPositionFill.getSlPositionUnique().getSlPositionUniqueId();
        tariffProperties.put("PositionFill", currentPositionFill); //$NON-NLS-1$
        tariffProperties.put("SlPositionUniqueId", StringUtils.EMPTY_STRING); //$NON-NLS-1$
        tariff.refresh();
      }
    });


    feeService = (FeeModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/FeeModel"); //$NON-NLS-1$
    fee = new MaintenanceTableController(feeProperties);
    fee.initController(feeService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from FeeModel fm %s where fm.PersonalAccount = :personalAccount"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("personalAccount"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(FeeModel.class, "Id", "fm.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef", "fr.FCode", "left join fm.FeeRef as fr", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "BeginDate", "fm.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "EndDate", "fm.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "Summa", "fm.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "RollKind", "rk.Name", "left join fm.RollKind as rk", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.SumSign", "fr.SumSign", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.FeeType", "fr.FeeType", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "PositionFill", "pos.Name", "left join fm.PositionFill as pf left join pf.Position pos", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.CalcAlg", "alg.Code", "left join fr.CalcAlg as alg", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "FeeRef.PeriodiCity", "fr.PeriodiCity", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl1", "anl1.ACode", "left join fm.CostsAnl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl2", "anl2.ACode", "left join fm.CostsAnl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl3", "anl3.ACode", "left join fm.CostsAnl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl4", "anl4.ACode", "left join fm.CostsAnl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CostsAnl5", "anl5.ACode", "left join fm.CostsAnl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(FeeModel.class, "CalcPeriod", "cp.PName", "left join fm.CalcPeriod as cp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, feeService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(fee);
    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    positionFillProperties.put("PersonalAccount", event.getEntity()); //$NON-NLS-1$
    feeProperties.put("PersonalAccount", event.getEntity()); //$NON-NLS-1$
  }

  private CalcPeriodServiceLocal getCalcPeriodService() {
    return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
  }

}
