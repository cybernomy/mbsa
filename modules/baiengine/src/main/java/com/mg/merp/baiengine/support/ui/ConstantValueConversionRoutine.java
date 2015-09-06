/*
 * ConstantValueConversionRoutine.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.generic.ui.AbstractConversionRoutine;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.model.Constant;
import com.mg.merp.baiengine.model.ConstantDataType;
import com.mg.merp.baiengine.support.BusinessAddinUtils;

/**
 * Конвертация атрибута Val объекта ConstantValue
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantValueConversionRoutine.java,v 1.1 2007/08/21 12:56:51 alikaev Exp $
 */
public class ConstantValueConversionRoutine extends AbstractConversionRoutine<String, Object> {
  private final static String CONST_ATRIBUTE_NAME = "Const";
  private Logger logger = ServerUtils.getLogger(ConstantValueConversionRoutine.class);

  @Override
  protected String[] defineImportContext() {
    return new String[]{CONST_ATRIBUTE_NAME};
  }

  /**
   * возвращает тип константы
   *
   * @return тип константы или {@link com.mg.merp.baiengine.model.ConstantDataType#STRING
   * ConstantDataType.STRING} если не удается импортировать атрибут значения константы
   */
  private ConstantDataType getConstantDataType() {
    return getImportContextValue(CONST_ATRIBUTE_NAME) != null ? ((Constant) getImportContextValue(CONST_ATRIBUTE_NAME)).getDataType() : ConstantDataType.STRING;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractConversionRoutine#doInputConverse(java.lang.Object)
   */
  @Override
  protected String doInputConverse(Object value) {
    return BusinessAddinUtils.convertConstantValue(value, getConstantDataType());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractConversionRoutine#doOutputConverse(java.lang.Object)
   */
  @Override
  protected Object doOutputConverse(String value) {
    return BusinessAddinUtils.convertConstantValue(value, getConstantDataType());
  }
}
