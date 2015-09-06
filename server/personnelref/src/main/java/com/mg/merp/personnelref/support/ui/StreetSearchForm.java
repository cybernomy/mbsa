/*
 * StreetSearchForm.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.personnelref.KladrServiceLocal;
import com.mg.merp.personnelref.model.Street;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска сущностей "Улицы" (из КЛАДР)
 *
 * @author Artem V. Sharapov
 * @version $Id: StreetSearchForm.java,v 1.1 2007/07/16 13:22:45 sharapov Exp $
 */
public class StreetSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from Street s %s"; //$NON-NLS-1$
  private final String STREET_WHERE_TEXT = "where (s.KCode like :streetMask) and (s.KType not like 'ДОМ')"; //$NON-NLS-1$
  private DefaultTableController table;
  private String whereText = ""; //$NON-NLS-1$
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
        result.add(new TableEJBQLFieldDef(Street.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "KCode", "s.KCode", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "SName", "s.SName", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "KType", "s.KType", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "PostIndex", "s.PostIndex", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "GninMb", "s.GninMb", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(Street.class, "OcaTd", "s.OcaTd", false)); //$NON-NLS-1$ //$NON-NLS-2$
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
        return String.format(INIT_QUERY_TEXT, fieldsList, whereText);
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
      return new PersistentObject[]{ServerUtils.getPersistentManager().find(Street.class, selectedId)};
  }

  public void setSearchParams(Integer kladrLevel, String regionCode, String districtCode, String cityCode, String areaCode) {
    if (KladrServiceLocal.STREET_LEVEL == kladrLevel) {
      if (KladrServiceLocal.INIT_REGION_CODE.compareTo(regionCode) == 0)
        regionCode = KladrServiceLocal.INIT_REGION_CODE_MASK;

      if (KladrServiceLocal.INIT_DISTRICT_CODE.compareTo(districtCode) == 0)
        districtCode = KladrServiceLocal.INIT_DISTRICT_CODE_MASK;

      if (KladrServiceLocal.INIT_CITY_CODE.compareTo(cityCode) == 0)
        cityCode = KladrServiceLocal.INIT_CITY_CODE_MASK;

      if (KladrServiceLocal.INIT_AREA_CODE.compareTo(areaCode) == 0)
        areaCode = KladrServiceLocal.INIT_AREA_CODE_MASK;

      paramsName.add("streetMask"); //$NON-NLS-1$
      paramsValue.add(new StringBuilder().append(regionCode).append(districtCode).append(cityCode).append(areaCode).append(KladrServiceLocal.STREET_SUB_MASK).toString());
      whereText = STREET_WHERE_TEXT;
    }
  }

}
