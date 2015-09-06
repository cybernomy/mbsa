/*
 * EntityCustomFieldsStorageImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.core.support;

import com.mg.framework.api.metadata.CustomFieldsManager;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * Реализация репозитария хранения пользовательских полей
 *
 * @author Oleg V. Safonov
 * @version $Id: EntityCustomFieldsStorageImpl.java,v 1.1 2007/01/25 15:40:57 safonov Exp $
 */
public class EntityCustomFieldsStorageImpl implements
    com.mg.framework.api.metadata.EntityCustomFieldsStorage {
  private final static int PREFIX_LENGTH = CustomFieldsManager.CUSTOM_FIELD_NAME_PREFIX.length() + 1;
  private Map<String, Object> customFieldsValues = null;

  public EntityCustomFieldsStorageImpl(Map<String, Object> customFieldsValues) {
    this.customFieldsValues = customFieldsValues;
  }

  private int getCustomFieldIndex(String name) {
    int result = name.indexOf(CustomFieldsManager.INDEX_DELIMITER, PREFIX_LENGTH);
    if (result != -1)
      result = Integer.parseInt(name.substring(++result));
    return result;
  }

  private String extractFieldName(String name) {
    return name.substring(0, name.indexOf(CustomFieldsManager.INDEX_DELIMITER, PREFIX_LENGTH));
  }

  private void doSetValue(String name, Object value) {
    if (customFieldsValues != null) {
      int index = getCustomFieldIndex(name);
      if (index != -1) {
        Object item = customFieldsValues.get(extractFieldName(name));
        if (item.getClass().isArray()) {
          Array.set(item, index, value);
        } else
          throw new IllegalStateException();
      } else
        customFieldsValues.put(name, value);
    }
  }

  private Object doGetValue(String name) {
    if (customFieldsValues != null) {
      int index = getCustomFieldIndex(name);
      if (index != -1) {
        Object item = customFieldsValues.get(extractFieldName(name));
        if (item == null)
          return null;
        else if (item.getClass().isArray())
          return Array.get(item, index);
        else
          throw new IllegalStateException();
      } else
        return customFieldsValues.get(name);
    } else
      return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.EntityCustomFieldsStorage#getValue(java.lang.String)
   */
  public Object getValue(String name) {
    return doGetValue(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.EntityCustomFieldsStorage#setValue(java.lang.String, java.lang.Object)
   */
  public void setValue(String name, Object value) {
    doSetValue(name, value);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.EntityCustomFieldsStorage#getValues()
   */
  public Map<String, Object> getValues() {
    return customFieldsValues;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.EntityCustomFieldsStorage#setValues(java.util.Map)
   */
  public void setValues(Map<String, Object> values) {
    this.customFieldsValues = values;
  }

}
