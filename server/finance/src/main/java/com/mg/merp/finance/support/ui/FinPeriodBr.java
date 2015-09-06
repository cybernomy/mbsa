/*
 * FinPeriodBr.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.finance.PeriodServiceLocal;
import com.mg.merp.finance.model.FinPeriod;

import java.io.Serializable;
import java.util.Set;


/**
 * Браузер периодов финансовых периодов
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FinPeriodBr.java,v 1.2 2006/12/26 12:41:32 sharapov Exp $
 */
public class FinPeriodBr extends DefaultPlainBrowseForm {
  private final String LOAD_BROWSE_EJBQL = "select %s from FinPeriod p ";     //$NON-NLS-1$

  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    return String.format(LOAD_BROWSE_EJBQL, fieldsList);
  }

  @Override
  protected MaintenanceTableModel createModel() {
    return new DefaultMaintenanceEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "PName", "p.PName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "DateFrom", "p.DateFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "DateTo", "p.DateTo", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "DateClose", "p.DateClose", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(FinPeriod.class, "WhoClosed", "p.WhoClosed", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
   * Обработка события контекстного меню: открытие фин. периода
   */
  public void onActionOpenFinPeriod(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    PeriodServiceLocal service = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/period"); //$NON-NLS-1$
    service.openPeriod(keys);
    table.refresh();
  }

  /**
   * Обработка события контекстного меню: закрытие фин. периода
   */
  public void onActionCloseFinPeriod(WidgetEvent event) {
    Serializable[] keys = ((MaintenanceTableModel) table.getModel()).getSelectedPrimaryKeys();
    PeriodServiceLocal service = (PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/period"); //$NON-NLS-1$
    service.closePeriod(keys);
    table.refresh();
  }
}
