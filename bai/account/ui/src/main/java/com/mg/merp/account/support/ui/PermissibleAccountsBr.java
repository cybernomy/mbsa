/*
 * PermissibleAccountsBr.java
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
package com.mg.merp.account.support.ui;

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultPlainBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.MaintenanceTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.account.PermissibleAccountsServiceLocal;
import com.mg.merp.account.model.PermissibleAccounts;

import java.util.Set;

/**
 * Браузер допустимой корреспонденции счетов
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PermissibleAccountsBr.java,v 1.4 2008/07/15 08:15:09 safonov Exp $
 */
public class PermissibleAccountsBr extends DefaultPlainBrowseForm {

  private final String INIT_QUERY_TEXT = "select %s from PermissibleAccounts pa %s";

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultPlainBrowseForm#createQueryText()
   */
  @Override
  protected String createQueryText() {
    Set<TableEJBQLFieldDef> fieldDefs = ((DefaultMaintenanceEJBQLTableModel) table.getModel()).getFieldDefsSet();
    String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
    String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
    return String.format(INIT_QUERY_TEXT, fieldsList, fromList);

  }

  /*
   * (non-Javadoc)
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
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "Id", "pa.Id", true));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AccDb", "pa.AccDb.Acc", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlDb1", "db1.Code", "left join pa.AnlDb1 as db1", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlDb2", "db2.Code", "left join pa.AnlDb2 as db2", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlDb3", "db3.Code", "left join pa.AnlDb3 as db3", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlDb4", "db4.Code", "left join pa.AnlDb4 as db4", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlDb5", "db5.Code", "left join pa.AnlDb5 as db5", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AccKt", "pa.AccKt.Acc", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlKt1", "kt1.Code", "left join pa.AnlKt1 as kt1", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlKt2", "kt2.Code", "left join pa.AnlKt2 as kt2", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlKt3", "kt3.Code", "left join pa.AnlKt3 as kt3", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlKt4", "kt4.Code", "left join pa.AnlKt4 as kt4", false));
        result.add(new TableEJBQLFieldDef(PermissibleAccounts.class, "AnlKt5", "kt5.Code", "left join pa.AnlKt5 as kt5", false));
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
   * Обработчик пункта контекстного меню "Создать на основе хоз. операций"
   */
  public void onActionCreateFromEconomicOper(WidgetEvent event) throws Exception {
    final QueryDateRangeDlg dialog = (QueryDateRangeDlg) ApplicationDictionaryLocator.locate().getWindow("com.mg.merp.account.QueryDateRangeDlg");
    dialog.addOkActionListener(new FormActionListener() {

      /* (non-Javadoc)
       * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
       */
      public void actionPerformed(FormEvent event) {
        PermissibleAccountsServiceLocal service = (PermissibleAccountsServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/PermissibleAccounts");
        service.createFromEconomicOper(dialog.getBeginDate(), dialog.getEndDate());
        table.refresh();
      }
    });
    dialog.execute();
  }

}
