/*
 * DocSpecPackingSearchForm.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Packing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "упаковки товара позиции спецификации документа"
 *
 * @author Artem V. Sharapov
 * @version $Id: DocSpecPackingSearchForm.java,v 1.1 2007/06/21 11:59:42 sharapov Exp $
 */
public class DocSpecPackingSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from Packing p %s where p.Catalog = :catalog"; //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(Packing.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Packing.class, "Measure", "m.Code", "left join p.Measure m", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Packing.class, "Priority", "p.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Packing.class, "Weight", "p.Weight", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Packing.class, "WeightMeasure", "wm.Code", "left join p.WeightMeasure wm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(Packing.class, "Volume", "p.Volume", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Packing.class, "VolumeMeasure", "vm.Code", "left join p.VolumeMeasure vm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
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
        else
          selectedId = (Integer) getRowList().get(rows[0])[0];

      }
    });
    table.getModel().load();
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    if (selectedId == null)
      return new PersistentObject[0];
    else
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(Packing.class, selectedId)};
  }

  /**
   * Запустиь форму поиска
   *
   * @param catalog - товар(упакованный), для которого будет осуществлен поиск упаковок
   */
  public void execute(Catalog catalog) {
    if (catalog != null && catalog.isPackaged()) {
      paramsName.add("catalog"); //$NON-NLS-1$
      paramsValue.add(catalog);
      run(UIUtils.isModalMode());
    }
  }

}
