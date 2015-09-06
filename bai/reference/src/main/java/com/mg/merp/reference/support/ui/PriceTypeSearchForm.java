/*
 * PriceTypeSearchForm.java
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
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.PricelistPricetypeLinkServiceLocal;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Контроллер формы поиска "Типов цен"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PriceTypeSearchForm.java,v 1.2 2008/09/09 12:19:25 sharapov Exp $
 */
public class PriceTypeSearchForm extends AbstractSearchForm {

  private final static String LOAD_PRICE_TYPE_EJBQL = "select %s from PriceListPriceTypeLink ptl, PriceType pt where ptl.PriceType = pt and ptl.PriceListHead = :priceListHead"; //$NON-NLS-1$
  protected PriceListHead priceListHead;
  private List<String> paramsName = new ArrayList<String>();
  private List<Object> paramsValue = new ArrayList<Object>();
  private Integer[] selectedIds;
  private DefaultTableController priceTypeList;

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#doOnRun()
   */
  @Override
  protected void doOnRun() {
    priceTypeList = new DefaultTableController(new DefaultMaintenanceEJBQLTableModel() {

      protected String createQueryText() {
        Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
        String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
        paramsName.clear();
        paramsValue.clear();
        paramsName.add("priceListHead"); //$NON-NLS-1$
        paramsValue.add(priceListHead);
        return String.format(LOAD_PRICE_TYPE_EJBQL, fieldsList);
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
       */
      @Override
      protected int getPrimaryKeyFieldIndex() {
        return 0;
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
       */
      @Override
      protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
        Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
        result.add(new TableEJBQLFieldDef(PriceType.class, "Id", "pt.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceType.class, "Code", "pt.Code", true)); //$NON-NLS-1$ //$NON-NLS-2$
        result.add(new TableEJBQLFieldDef(PriceType.class, "PName", "pt.PName", true));    //$NON-NLS-1$ //$NON-NLS-2$
        return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, (PricelistPricetypeLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PricelistPricetypeLink")); //$NON-NLS-1$
      }

      /* (non-Javadoc)
       * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
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
        selectedIds = new Integer[rows.length];
        for (int i = 0; i < rows.length; i++)
          selectedIds[i] = (Integer) getRowList().get(rows[i])[0];
      }
    });
    priceTypeList.getModel().load();
    super.doOnRun();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractSearchForm#getSearchedEntities()
   */
  @Override
  protected PersistentObject[] getSearchedEntities() {
    if (selectedIds == null)
      return new PersistentObject[0];

    PersistentManager persistentManager = ServerUtils.getPersistentManager();
    PersistentObject[] result = new PersistentObject[selectedIds.length];
    for (int i = 0; i < selectedIds.length; i++)
      result[i] = persistentManager.find(PriceType.class, selectedIds[i]);
    return result;
  }

  /**
   * Установить заголовок прайс-листа
   *
   * @param priceListHead - заголовок прайс-листа
   */
  public void setPriceListHead(PriceListHead priceListHead) {
    this.priceListHead = priceListHead;
  }

}