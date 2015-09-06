/*
 * PersonAddressSearchForm.java
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

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.generic.ui.AbstractSearchForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.PersonAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска бизнес-компонента "Адреса проживания"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: PersonAddressSearchForm.java,v 1.2 2008/03/27 13:28:31 alikaev Exp $
 */
public class PersonAddressSearchForm extends AbstractSearchForm {

  private final String INIT_QUERY_TEXT = "select %s from PersonAddress pa %s %s"; //$NON-NLS-1$
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private DefaultTableController table;

  private NaturalPerson person = null;

  private Integer[] selectedIds;

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {

    table = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Id", "pa.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "AddressKind", "pa.AddressKind", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "BeginDate", "pa.BeginDate", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "ZipCode", "zc.Code", "left join pa.ZipCode as zc", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Country", "c.CCode", "left join pa.Country as c", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Region", "r.Name", "left join pa.Region as r", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "District", "d.Name", "left join pa.District as d", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Place", "pl.Name", "left join pa.Place as pl", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "City", "pa.City", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "House", "pa.House", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Building", "pa.Building", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "Room", "pa.Room", false)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PersonAddress.class, "NaturalPerson", "np.Surname", "left join pa.NaturalPerson as np", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected void doLoad() {
        setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
       */
      @Override
      public void setSelectedRows(int[] rows) {
        super.setSelectedRows(rows);
        selectedIds = new Integer[rows.length];
        for (int i = 0; i < rows.length; i++)
          selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
      }

      /**
       * формируем sql запрос
       *
       * @return текст sql запроса
       */
      private String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = ((DefaultEJBQLTableModel) table.getModel()).getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        StringBuilder whereText = new StringBuilder(" where (0=0) ");
        if (person != null) {
          paramsName.add("person");
          paramsValue.add(person);
          whereText.append(" and pa.NaturalPerson = :person ");
        }
        return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
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
    if (selectedIds == null)
      return new PersistentObject[0];

    PersistentManager persistentManager = ServerUtils.getPersistentManager();
    PersistentObject[] selectedEntities = new PersistentObject[selectedIds.length];
    for (int i = 0; i < selectedIds.length; i++)
      selectedEntities[i] = persistentManager.find(PersonAddress.class, selectedIds[i]);

    return selectedEntities;
  }

  /**
   * @param person задаваемое person
   */
  public void setPerson(NaturalPerson person) {
    this.person = person;
  }

}
