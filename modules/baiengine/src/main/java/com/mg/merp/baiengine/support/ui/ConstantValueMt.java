/*
 * ConstantValueMt.java
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
package com.mg.merp.baiengine.support.ui;

import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.merp.baiengine.model.ConstantValue;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Контроллер формы поддержки бизнес-компонента "Значение константы"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantValueMt.java,v 1.2 2007/11/15 09:20:46 safonov Exp $
 */
public class ConstantValueMt extends DefaultMaintenanceForm {
  private final static String VAL_ATRIBUTE_NAME = "Val";

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doGetFieldMetadata(java.lang.String)
   */
  @Override
  protected FieldMetadata doGetFieldMetadata(String name) {
    FieldMetadata fieldMetadata = super.doGetFieldMetadata(name);
    int numberOfDecimalPlaces = 0;
    //переопределяем метаданные в зависимости от типа константы для user friendly отображения значения константы
    if (VAL_ATRIBUTE_NAME.equals(name)) { //$NON-NLS-1$
      BuiltInType builtInType;
      Class<?> clazz;
      switch (((ConstantValue) getEntity()).getConst().getDataType()) {
        case DATE:
          builtInType = BuiltInType.DATE;
          clazz = Date.class;
          break;
        case FLOAT:
          builtInType = BuiltInType.NUMBER;
          clazz = BigDecimal.class;
          numberOfDecimalPlaces = 6;
          break;
        case INTEGER:
          builtInType = BuiltInType.INTEGER;
          clazz = Integer.class;
          break;
        case STRING:
          builtInType = BuiltInType.STRING;
          clazz = String.class;
          break;
        default:
          logger.debug("Unsupported type"); //$NON-NLS-1$
          return fieldMetadata;
      }
      fieldMetadata.setBuiltInType(builtInType);
      fieldMetadata.setJavaType(clazz);
      //Устанавливаем количество знаков после запятой для типа константы FLOAT
      if (numberOfDecimalPlaces != 0)
        fieldMetadata.setNumberOfDecimalPlaces(numberOfDecimalPlaces);
    }

    return fieldMetadata;
  }

}
