/*
 * FieldEditor.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import com.mg.framework.api.metadata.ui.FieldMetadata;

/**
 * Редактор поля модели связанный с контроллером формы
 *
 * @author Oleg V. Safonov
 * @version $Id: FieldEditor.java,v 1.3 2008/03/13 07:47:16 safonov Exp $
 */
public interface FieldEditor {

  /**
   * атрибут признак наличия текстовой метки для редактора, имеет значение <code>Boolean</code>, по
   * умолчанию метка показывается
   */
  final static String SHOW_LABEL = "showLabel";

  /**
   * атрибут ссылка на элемент данных, должен содержать имя элемента данных, позволяет
   * переопределить установленный элемент данных в модели
   */
  final static String DATA_ITEM = "dataItem";

  /**
   * получить значение редактора
   *
   * @return значение редактора
   */
  public Object getEditorValue();

  /**
   * установить значение редактора
   *
   * @param value значение модели
   */
  public void setEditorValue(Object value);

  /**
   * установить метаданные редактора
   *
   * @param metadata метаданные
   */
  public void setFieldMetadata(FieldMetadata metadata);
}
