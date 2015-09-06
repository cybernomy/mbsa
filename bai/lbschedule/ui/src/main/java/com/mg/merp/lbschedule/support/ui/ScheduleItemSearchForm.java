/*
 * ScheduleItemSearchForm.java
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
package com.mg.merp.lbschedule.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контролер формы поиска объектов-сущностей "Пунктов графика исполнения обязательств"
 *
 * @author Artem V. Sharapov
 * @version $Id: ScheduleItemSearchForm.java,v 1.3 2009/02/09 12:08:32 safonov Exp $
 */
public class ScheduleItemSearchForm extends AbstractSearchForm {
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController scheduleItemsTable;
  private StringBuilder queryText;
  private Serializable[] selectedIDs = null;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
   */
  @Override
  protected void doOnRun() {

    scheduleItemsTable = new DefaultTableController(new DefaultEJBQLTableModel() {

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(Item.class, "Id", "i.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "Comments", "i.Comments", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "Num", "i.Num", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "ItemKind", "ik.Code", "left join i.ItemKind as ik", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "Status", "i.Status", false));                     //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "From", "f.Code", "left join i.From as f", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "To", "t.Code", "left join i.To as t", false));                 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResourceFrom", "rf.Name", "left join i.ResourceFrom as rf", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResourceTo", "rt.Name", "left join i.ResourceTo as rt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "HasSpec", "i.HasSpec", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "PriceListHead", "plh.PrName", "left join i.PriceListHead as plh", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "PriceType", "pt.Code", "left join i.PriceType as pt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "ResultDate", "i.ResultDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "ResultDateEnd", "i.ResultDateEnd", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Item.class, "CurCode", "cc.Code", "left join i.CurCode as cc", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "CurRateType", "crt.Code", "left join i.CurRateType as crt", false));     //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "CurRateAuthority", "cra.Code", "left join i.CurRateAuthority as cra", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Item.class, "FactSum", "i.FactSum", false));                 //$NON-NLS-1$ //$NON-NLS-2$
        return result;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        selectedIDs = new Serializable[rows.length];
        for (int i = 0; i < rows.length; i++)
          selectedIDs[i] = (Serializable) getRowList().get(rows[i])[0];
      }

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) scheduleItemsTable.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        return String.format(queryText.toString(), fieldsList, fromList);
      }

    });
    scheduleItemsTable.getModel().load();
    showForm();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    int len = selectedIDs == null ? 0 : selectedIDs.length;
    Item[] selectedEntities = new Item[len];
    for (int i = 0; i < len; i++)
      selectedEntities[i] = ServerUtils.getPersistentManager().find(Item.class, selectedIDs[i]);
    return selectedEntities;
  }

  /**
   * Установить параметры поиска пунктов графика
   *
   * @param itemId   - пункт графика
   * @param schedule - график исполнения обязательств
   */
  public void setSearchParams(Integer itemId, Schedule schedule) {
    queryText = new StringBuilder("select %s from Item i %s where i.Schedule = :schedule"); //$NON-NLS-1$
    paramsName.clear();
    paramsValue.clear();
    paramsName.add("schedule"); //$NON-NLS-1$
    paramsValue.add(schedule);

    // если поиск осуществляется для созданного пункта, то исключим его из списка
    if (itemId != null) {
      paramsName.add("itemId"); //$NON-NLS-1$
      paramsValue.add(itemId);
      queryText.append(" and i.Id <> :itemId"); //$NON-NLS-1$
    }
  }

}
