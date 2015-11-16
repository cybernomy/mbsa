/*
 * ReflectionMetadata.java
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
package com.mg.framework.api.metadata;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.SearchHelpName;

/**
 * Метаданные свойства модели
 *
 * @author Oleg V. Safonov
 * @version $Id: ReflectionMetadata.java,v 1.3 2008/03/13 07:30:00 safonov Exp $
 */
public class ReflectionMetadata {
  private String dataItemName;
  private String searchHelpName;
  private Class<?> propertyType;

  public ReflectionMetadata() {
    super();
  }

  public ReflectionMetadata(DataItemName dataItem, Class<?> propertyType) {
    this();
    this.dataItemName = dataItem != null ? dataItem.value() : null;
    this.propertyType = propertyType;
  }

  public ReflectionMetadata(String dataItemName, Class<?> propertyType) {
    this();
    this.dataItemName = dataItemName;
    this.propertyType = propertyType;
  }

  /**
   * Получить элемент данных
   *
   * @return Returns the dataItemName.
   */
  public String getDataItemName() {
    return dataItemName;
  }

  /**
   * Установить имя элемента данных
   */
  public void setDataItemName(String dataItemName) {
    this.dataItemName = dataItemName;
  }

  /**
   * Установить имя элемента данных
   *
   * @param dataItemName The dataItemName to set.
   */
  public void setDataItemName(DataItemName dataItem) {
    this.dataItemName = dataItem != null ? dataItem.value() : null;
  }

  /**
   * Получить тип свойства
   *
   * @return Returns the propertyType.
   */
  public Class<?> getPropertyType() {
    return propertyType;
  }

  /**
   * Установить тип свойства
   *
   * @param propertyType The propertyType to set.
   */
  public void setPropertyType(Class<?> propertyClass) {
    this.propertyType = propertyClass;
  }

  /**
   * Получить механизм поиска
   *
   * @return the searchHelpName
   */
  public String getSearchHelpName() {
    return searchHelpName;
  }

  /**
   * Установить механизм поиска
   *
   * @param searchHelpName имя механизма поиска
   */
  public void setSearchHelpName(String searchHelpName) {
    this.searchHelpName = searchHelpName;
  }

  /**
   * Установить механизм поиска
   *
   * @param searchHelpName the searchHelpName to set
   */
  public void setSearchHelpName(SearchHelpName searchHelpName) {
    this.searchHelpName = searchHelpName != null ? searchHelpName.value() : null;
  }

}
