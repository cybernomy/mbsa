/*
 * PatternHeadMt.java
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

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.Table;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.framework.support.ui.Dialogs;
import com.mg.framework.support.ui.Dialogs.InputQueryDialogListener;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.table.PatternSpecServiceLocal;
import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.TimeKind;
import com.mg.merp.table.support.Messages;
import com.mg.merp.table.support.TimeBoardHelper;

import java.math.BigDecimal;

/**
 * Контроллер формы поддержки "Шаблон графика"
 *
 * @author Artem V. Sharapov
 * @version $Id: PatternHeadMt.java,v 1.1 2008/08/12 14:38:08 sharapov Exp $
 */
public class PatternHeadMt extends DefaultMaintenanceForm implements MasterModelListener {

  public static String TABLE_WIDGET_NAME = "specTable"; //$NON-NLS-1$
  public static String REFRESH_PATTERN_GRID_WIDGET = "refreshPatternGrid"; //$NON-NLS-1$
  protected PatternSpecServiceLocal patternSpecService = (PatternSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PatternSpecServiceLocal.SERVICE_NAME);
  protected TimeBoardHelper timeBoardHelper = new TimeBoardHelper();
  private DefaultTableController specTable;

  public PatternHeadMt() {
    setMasterDetail(true);
    addMasterModelListener(this);
    specTable = new DefaultTableController(new PatternSpecTableModel());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
   */
  @Override
  protected void doSetDependentReadOnly(boolean readOnly) {
    super.doSetDependentReadOnly(readOnly);
    view.getWidget(REFRESH_PATTERN_GRID_WIDGET).setEnabled(!readOnly);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
   */
  public void masterChange(ModelChangeEvent event) {
    doFillSpecGrid((PatternHead) event.getEntity());
  }

  /**
   * Обработчик пункта КМ "Выбрать тип времени с учетом по дням"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionChooseDayliTimeKind(WidgetEvent event) throws Exception {
    final int[] columns = getSelectedColumns();
    if (columns.length > 0 && columns[0] > 0) {
      SearchHelp searchHelp = SearchHelpProcessor.createSearch("com.mg.merp.table.support.ui.TimeKindDayliSearchHelp"); //$NON-NLS-1$
      searchHelp.addSearchHelpListener(new SearchHelpListener() {

        public void searchPerformed(SearchHelpEvent event) {
          getTableModel().setTimeKind((TimeKind) event.getItems()[0], columns);
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
    final PatternSpecTableModel tableModel = getTableModel();
    if (columns.length > 0 && columns[0] > 0) {
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

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnSave()
   */
  @Override
  protected void doOnSave() {
    patternSpecService.updateSpecs(getTableModel().getTableModelItemList());
  }

  /**
   * Обработчик кнопки "Обновить шаблон"
   *
   * @param event - событие
   */
  public void onActionRefreshPatternGrid(WidgetEvent event) {
    doFillSpecGrid((PatternHead) getEntity());
  }

  /**
   * Обновить шаблон
   *
   * @param patternHead - заголовок шаблона
   */
  protected void doFillSpecGrid(PatternHead patternHead) {
    ((PatternSpecTableModel) specTable.getModel()).fillGrid(patternHead, patternSpecService.loadSpecs(patternHead), timeBoardHelper.getTimeKinds());
  }

  private PatternSpecTableModel getTableModel() {
    return (PatternSpecTableModel) specTable.getModel();
  }

  private int[] getSelectedColumns() {
    return ((Table) view.getWidget(TABLE_WIDGET_NAME)).getSelectedColumns();
  }

}
