/*
 * EnumField.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.FieldEditor;


/**
 * Элемент "Редактор перечислимого типа"
 *
 * @author Oleg V. Safonov
 * @version $Id: EnumField.java,v 1.2 2006/11/21 15:34:06 safonov Exp $
 */
public interface EnumField extends FieldEditor {
  final static String EDITOR_TYPE = "editorType";

  ;

  /**
   * тип редактора
   */
  enum EnumFieldType {
    /**
     * раскрывающийся список
     */
    COMBOBOX,
    /**
     * переключатель
     */
    RADIOBUTTON
  }

}
