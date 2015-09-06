/*
 * PromotionTypeSearchForm.java
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
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.discount.model.PromotionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Тип рекламного мероприятия"
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionTypeSearchForm.java,v 1.2 2007/11/12 13:24:55 sharapov Exp $
 */
public class PromotionTypeSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from PromotionType pt %s"; //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(PromotionType.class, "Id", "pt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PromotionType.class, "Name", "pt.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PromotionType.class, "Code", "pt.Code", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PromotionType.class, "Bai", "bai.Code", "left join pt.Bai bai", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(PromotionType.class, selectedId)};
  }

}
