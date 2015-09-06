/*
 * PriceListHeadBr.java
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
package com.mg.merp.reference.support.ui;

import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.PriceListHeadServiceLocal;
import com.mg.merp.reference.PriceListSpecServiceLocal;
import com.mg.merp.reference.model.PriceListHead;

import java.io.Serializable;
import java.util.Set;

/**
 * Контроллер браузера заголовков прайс-листов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListHeadBr.java,v 1.8 2009/02/11 14:38:53 sharapov Exp $
 */
public class PriceListHeadBr extends DefaultPlainBrowseForm {
  private final String INIT_QUERY_TEXT = "select %s from PriceListHead plh %s where exists (from PriceListHeadRights plhr where (plhr.PriceListHead = plh) and (plhr.Rights = 1) and (plhr.Groups.Id in (%s))) order by plh.PrName"; //$NON-NLS-1$
  private final String OVERESTIMATION = "overestimation";

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    super.doOnRun();
    doAdjustPopupMenu();
  }

  protected void doAdjustPopupMenu() {
    PopupMenu popupMenu = view.getWidget(TABLE_WIDGET).getPopupMenu();
    if (popupMenu != null)
      UIUtils.setVisibleEnabledProperty(popupMenu.getMenuItem(OVERESTIMATION), SecurityUtils.tryCheckPermission(new BusinessMethodPermission(PriceListHeadServiceLocal.SERVICE_NAME, OVERESTIMATION)));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList, ServerUtils.getUserProfile().getGroupsCommaText());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createModel()
   */
  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "Id", "plh.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "PrName", "plh.PrName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "CreateDate", "plh.CreateDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "Currency", "cur.Code", "left join plh.Currency as cur", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "Contractor", "c.Code", "left join plh.Contractor as c", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PriceListHead.class, "IsCurrent", "plh.IsCurrent", false)); //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText());
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }
    };
  }

  /**
   * Обработчик пункта КМ "Показать спецификацию прайс-листа"
   *
   * @param event - событие
   * @throws Exception - ИС
   */
  public void onActionShowSpecPriceList(WidgetEvent event) throws Exception {
    final PriceListSpecServiceLocal service = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PriceListSpec"); //$NON-NLS-1$
    PriceListSpecBr form = (PriceListSpecBr) ApplicationDictionaryLocator.locate().getBrowseForm(service, null);
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    for (Serializable key : keys) {
      form.setPrListHeadId(key);
      form.run();
      break;
    }
  }

  /**
   * Обработчик пункта КМ "Пересчитать цены"
   *
   * @param event - событие
   */
  public void onActionRecalcPrices(WidgetEvent event) {
    Serializable[] priceListHeadIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (priceListHeadIds != null && priceListHeadIds.length > 0)
      ((PriceListHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListHeadServiceLocal.SERVICE_NAME)).recalcPrices(priceListHeadIds);
  }

  /**
   * Обработчик пункта КМ "Изменить базовые цены"
   *
   * @param event - событие
   */
  public void onActionOverestimation(WidgetEvent event) {
    final Serializable[] priceListHeadIds = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (priceListHeadIds != null && priceListHeadIds.length > 0) {
      final OverestimationDlg overestimationDlg = (OverestimationDlg) ApplicationDictionaryLocator.locate().getWindow(OverestimationDlg.WINDOW_NAME);
      overestimationDlg.addOkActionListener(new FormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
         */
        public void actionPerformed(FormEvent event) {
          ((PriceListHeadServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListHeadServiceLocal.SERVICE_NAME))
              .overestimation(priceListHeadIds[0], overestimationDlg.getActualDate(), overestimationDlg.getPercent(), overestimationDlg.getPrecision());
        }
      });
      overestimationDlg.execute();
    }
  }

  /**
   * Обработчик события "Настройки прав доступа на прайс-листы"
   *
   * @param event - событие
   */
  public void onActionSetupPriceListPermissions(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    if (keys != null && keys.length > 0)
      ((PriceListPermissionsForm) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.reference.PriceListPermissionsForm")).execute((Integer) keys[0]);
  }

}