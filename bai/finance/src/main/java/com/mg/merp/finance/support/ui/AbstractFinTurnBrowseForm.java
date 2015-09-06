/*
 * AbstractFinTurnBrowseForm.java
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
package com.mg.merp.finance.support.ui;

import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.StringUtils;

/**
 * Абстрактный класс контроллера браузера "Остатков и обротов"
 *
 * @author Artem V. Sharapov
 * @version $Id: AbstractFinTurnBrowseForm.java,v 1.1 2009/02/16 07:46:40 sharapov Exp $
 */
public abstract class AbstractFinTurnBrowseForm extends DefaultHierarchyBrowseForm {

  protected ApplicationDictionary appDictionary;
  private String[] analitycsColumnNames;

  public AbstractFinTurnBrowseForm() {
    super();
    appDictionary = ApplicationDictionaryLocator.locate();
    analitycsColumnNames = doGetAnalitycsColumnNames();
  }

  protected abstract String[] doGetAnalitycsColumnNames();

  protected String getColumnNameByDataItem(String dataItemName) {
    DataItem dataItem = appDictionary.getDataItem(dataItemName);
    String columnName = dataItem == null ? StringUtils.EMPTY_STRING : dataItem.getShortLabel();
    return columnName == null ? StringUtils.EMPTY_STRING : columnName;
  }

  protected boolean isAnalyticsColumn(String columnName) {
    boolean isAnalColumn = false;
    if (analitycsColumnNames != null) {
      for (String analitycsColumnName : analitycsColumnNames) {
        if (analitycsColumnName.equals(columnName)) {
          isAnalColumn = true;
          break;
        }
      }
    }
    return isAnalColumn;
  }

}
