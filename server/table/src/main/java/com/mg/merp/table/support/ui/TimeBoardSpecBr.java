/*
 * TimeBoardSpecBr.java
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
package com.mg.merp.table.support.ui;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.TreeChangeEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultTreeModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTreeController;
import com.mg.framework.support.ui.widget.TreeSelectionListener;
import com.mg.framework.support.ui.widget.tree.EntityTreeNode;
import com.mg.framework.support.ui.widget.tree.TreeNode;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.personnelref.CalcPeriodServiceLocal;
import com.mg.merp.personnelref.StaffListUnitServiceLocal;
import com.mg.merp.personnelref.model.CalcPeriod;
import com.mg.merp.personnelref.model.StaffListUnit;
import com.mg.merp.personnelref.support.ui.StaffListUnitTreeNode;
import com.mg.merp.table.PatternSpecServiceLocal;
import com.mg.merp.table.model.TimeBoardHead;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.Messages;
import com.mg.merp.table.support.TimeBoardHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Контроллер браузера "Табель"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardSpecBr.java,v 1.6 2008/08/12 14:38:38 sharapov Exp $
 */
public class TimeBoardSpecBr extends AbstractForm {

  private final static String TABLE_WIDGET_NAME = "table"; //$NON-NLS-1$
  public static String WINDOW_NAME = "com.mg.merp.table.TimeBoardSpecBr"; //$NON-NLS-1$
  protected Serializable timeBoardHeadId;
  protected TimeBoardHead timeBoardHead = null;
  protected PatternSpecServiceLocal patternSpecService = (PatternSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PatternSpecServiceLocal.SERVICE_NAME);
  protected TimeBoardHelper timeBoardHelper = new TimeBoardHelper();
  protected DefaultTableController table;
  private MaintenanceTreeController tree;
  private AttributeMap treeUIProperties = new LocalDataTransferObject();
  private StaffListUnitServiceLocal folderService = (StaffListUnitServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(StaffListUnitServiceLocal.LOCAL_SERVICE_NAME);


  public TimeBoardSpecBr() {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    tree = new MaintenanceTreeController(treeUIProperties, new DefaultTreeModel() {
      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultTreeModel#getRootNode()
       */
      @Override
      public TreeNode getRootNode() {
        List<StaffListUnit> list = MiscUtils.convertUncheckedList(StaffListUnit.class, OrmTemplate.getInstance().findByNamedParam(String.format("from StaffListUnit slu where %s and slu.StaffList = :staffList order by slu.Id", DatabaseUtils.generateFlatBrowseWhereEJBQL("slu.Id", 6)), "staffList", getCalcPeriodService().getCurrentCalcPeriod().getStaffList())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return StaffListUnitTreeNode.createTree(list);
      }
    });

    tree.initController(folderService);
    tree.setParentPropertyName("StaffListUnit.Id"); //$NON-NLS-1$
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeChangeEvent event) {
        doOnMasterChanged((StaffListUnit) ((EntityTreeNode) event.getNode()).getEntity());
      }
    });
    table = new DefaultTableController(new TimeBoardSpecTableModel());
    super.doOnRun();
  }

  protected void doOnMasterChanged(StaffListUnit staffListUnit) {
    CalcPeriod calcPeriod = timeBoardHead.getCalcPeriod();
    getTableModel().fillGrid(calcPeriod.getBeginDate(), calcPeriod.getEndDate(), timeBoardHelper.getTimeBoardSpecItems((Integer) timeBoardHeadId, staffListUnit.getId()), timeBoardHelper.getTimeKinds());
  }

  public void setTableHeadId(Serializable timeBoardHeadId) {
    this.timeBoardHeadId = timeBoardHeadId;
    if (timeBoardHeadId != null)
      timeBoardHead = ServerUtils.getPersistentManager().find(TimeBoardHead.class, timeBoardHeadId);
  }

  /**
   * Обработчик пункта КМ "Выбрать тип времени с учетом по дням"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionChooseDayliTimeKind(WidgetEvent event) throws Exception {
    final int[] columns = getSelectedColumns();
    final TimeBoardSpecTableModel timeBoardSpecTableModel = getTableModel();
    if (columns.length > 0 && timeBoardSpecTableModel.isGridCellEditable(columns[0])) {
      SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.table.support.ui.TimeKindDayliSearchHelp"); //$NON-NLS-1$
      searchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchPerformed(SearchHelpEvent event) {
          timeBoardSpecTableModel.setTimeKind((TimeKind) event.getItems()[0], columns);
        }

        public void searchCanceled(SearchHelpEvent event) {
          //do nothing
        }
      });
      searchHelp.search();
    }
  }

  /**
   * Обработчик пункта КМ "Установить кол-во часов"
   *
   * @param event - событие
   */
  public void onActionSetHours(WidgetEvent event) {
    final int[] columns = getSelectedColumns();
    final TimeBoardSpecTableModel tableModel = getTableModel();
    if (columns.length > 0 && tableModel.isGridCellEditable(columns[0])) {
      Dialogs.inputQuery(Messages.getInstance().getMessage(Messages.INPUT_HOURS_DIALOG_TITLE), StringUtils.BLANK_STRING, tableModel.getHours(columns), new InputQueryDialogListener<BigDecimal>() {

        /* (non-Javadoc)
         * @see com.mg.framework.support.ui.Dialogs.InputQueryDialogListener#inputCanceled()
         */
        public void inputCanceled() {
          // do nothing
        }

        /* (non-Javadoc)
         * @see com.mg.framework.support.ui.Dialogs.InputQueryDialogListener#inputPerformed(java.lang.Object)
         */
        public void inputPerformed(BigDecimal value) {
          tableModel.setHours(value, columns);
        }
      });
    }
  }

  private TimeBoardSpecTableModel getTableModel() {
    return (TimeBoardSpecTableModel) table.getModel();
  }

  private CalcPeriodServiceLocal getCalcPeriodService() {
    return (CalcPeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcPeriodServiceLocal.LOCAL_SERVICE_NAME);
  }

  private int[] getSelectedColumns() {
    return ((Table) view.getWidget(TABLE_WIDGET_NAME)).getSelectedColumns();
  }

}
