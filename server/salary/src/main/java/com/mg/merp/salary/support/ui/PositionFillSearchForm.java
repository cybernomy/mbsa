/*
 * PositionFillSearchForm.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.model.PositionFill;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Занимаемые должности"
 *
 * @author Artem V. Sharapov
 * @version $Id: PositionFillSearchForm.java,v 1.1 2007/07/09 08:33:47 sharapov Exp $
 */
public class PositionFillSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from PositionFill pf %s where pf.PersonalAccount = :personalAccount"; //$NON-NLS-1$
  private DefaultTableController table;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();

  private Integer selectedId;


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {

    table = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
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
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        if (rows.length == 0)
          selectedId = null;
        else {
          Object[] row = getRowList().get(rows[0]);
          selectedId = (Integer) row[0];
        }
      }
    });
    super.doOnRun();
    table.getModel().load();
  }


  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    if (selectedId == null)
      return new PersistentObject[0];
    else
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(PositionFill.class, selectedId)};
  }

  /**
   * Установить лицевой счет сотрудника для поиска
   */
  public void setPersonalAccount(PersonalAccount personalAccount) {
    paramsName.add("personalAccount"); //$NON-NLS-1$
    paramsValue.add(personalAccount);
  }

}
