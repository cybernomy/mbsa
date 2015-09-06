/*
 * CalcListMt.java
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
package com.mg.merp.salary.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.salary.CalcListFeeServiceLocal;
import com.mg.merp.salary.CalcListSectionServiceLocal;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.CalcListSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поддержи "расчетных листков"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListMt.java,v 1.7 2009/03/06 07:23:09 safonov Exp $
 */
public class CalcListMt extends DefaultMaintenanceForm implements MasterModelListener {

  protected AttributeMap sectionProperties = new LocalDataTransferObject();
  protected AttributeMap calcListFeeProperties = new LocalDataTransferObject();
  private MaintenanceTableController section;
  private CalcListSectionServiceLocal sectionService;
  private CalcListSection currentSection;
  private MaintenanceTableController calcListFee;
  private CalcListFeeServiceLocal calcListFeeService;


  public CalcListMt() throws Exception {
    sectionService = (CalcListSectionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/CalcListSection"); //$NON-NLS-1$
    section = new MaintenanceTableController(sectionProperties);
    section.initController(sectionService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from CalcListSection cls where cls.CalcList = :calcList"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("calcList"); //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(CalcListSection.class, "Id", "cls.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListSection.class, "CalcListSectionRef.SName", "cls.CalcListSectionRef.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListSection.class, "CalcListSectionRef.SumSign", "cls.CalcListSectionRef.SumSign", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListSection.class, "TotalSumma", "cls.TotalSumma", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, sectionService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });
    addMasterModelListener(section);

    calcListFeeService = (CalcListFeeServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/salary/CalcListFee"); //$NON-NLS-1$
    calcListFee = new MaintenanceTableController(calcListFeeProperties);
    calcListFee.initController(calcListFeeService, new DefaultMaintenanceEJBQLTableModel() {

      private final String INIT_QUERY_TEXT = "select %s from CalcListFee clf  %s where clf.CalcListSection = :calcListSection"; //$NON-NLS-1$
      private List<String> paramsName = new ArrayList<String>();
      private List<Object> paramsValue = new ArrayList<Object>();

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("calcListSection"); //$NON-NLS-1$
        paramsValue.add(currentSection);
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
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "Id", "clf.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "Summa", "clf.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.FeeRef.FCode", "fr.FCode", "left join clf.FeeModel.FeeRef as fr", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.FeeRef.FName", "fr.FName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.BeginDate", "clf.FeeModel.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.EndDate", "clf.FeeModel.EndDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.CalcPeriod", "(cp.BeginDate||'-'||cp.EndDate) as calcPeriod", "left join clf.FeeModel.CalcPeriod as cp", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "NeedParams", "clf.NeedParams", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "CostsAnl1", "anl1.ACode", "left join clf.CostsAnl1 as anl1", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "CostsAnl2", "anl2.ACode", "left join clf.CostsAnl2 as anl2", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "CostsAnl3", "anl3.ACode", "left join clf.CostsAnl3 as anl3", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "CostsAnl4", "anl4.ACode", "left join clf.CostsAnl4 as anl4", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "CostsAnl5", "anl5.ACode", "left join clf.CostsAnl5 as anl5", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(CalcListFee.class, "FeeModel.FeeRef.SumSign", "fr.SumSign", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, calcListFeeService);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }
    });

    section.addMasterModelListener(new MasterModelListener() {

      public void masterChange(ModelChangeEvent event) {
        currentSection = event.getModelKey() == null ? null : sectionService.load((Integer) event.getModelKey());
        calcListFee.refresh();
      }

    });
    addMasterModelListener(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    sectionProperties.put("CalcList", event.getEntity()); //$NON-NLS-1$
    calcListFeeProperties.put("CalcListSection", event.getEntity()); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    PopupMenu sectionPopupMenu = view.getWidget("section").getPopupMenu(); //$NON-NLS-1$
    sectionPopupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
    sectionPopupMenu.getMenuItem(MaintenanceTable.EDIT_MENU_ITEM).setEnabled(false);
    sectionPopupMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);

    PopupMenu calcListFeePopupMenu = view.getWidget("calcListFee").getPopupMenu(); //$NON-NLS-1$
    calcListFeePopupMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
  }

  /**
   * Обработчик кнопки "Лицевой счет"
   *
   * @param event - событие
   */
  public void onActionEditPersonalAccount(WidgetEvent event) {

  }

  /**
   * Обработчик кнопки "Основные сведения"
   *
   * @param event - событие
   */
  public void onActionEditPersonel(WidgetEvent event) {

  }

  /**
   * Обработчик кнопки "Другие ведомости"
   *
   * @param event - событие
   */
  public void onActionChangeCalcList(WidgetEvent event) {

  }

  /**
   * Обработчик кнопки "Предыдущий расчетный период"
   *
   * @param event - событие
   */
  public void onActionPreviousCalcPeriod(WidgetEvent event) {

  }

  /**
   * Обработчик кнопки "Следующий расчетный период"
   *
   * @param event - событие
   */
  public void onActionNextCalcPeriod(WidgetEvent event) {

  }

}
