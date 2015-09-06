/*
 * TimeTickConversion.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.mfreference.support.ui;

import com.mg.framework.generic.ui.AbstractConversionRoutine;
import com.mg.merp.mfreference.support.MfUtils;
import com.mg.merp.reference.model.Measure;

import java.math.BigDecimal;

/**
 * Конвертация тиков производства в установленные ЕИ, используется как базовый класс для конвертации
 * различных атрибутов
 *
 * @author Oleg V. Safonov
 * @version $Id: TimeTickConversion.java,v 1.3 2006/09/07 10:56:37 leonova Exp $
 */
public abstract class TimeTickConversion extends AbstractConversionRoutine<Long, BigDecimal> {

  /**
   * получение ЕИ для времени, необходимо переопределить в классе наследнике для получения реальной
   * ЕИ времени
   */
  protected abstract Measure getTimeUM();

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractConversionRoutine#doInputConverse(U)
   */
  @Override
  protected Long doInputConverse(BigDecimal value) {
    try {
      return MfUtils.timeToTick(value, getTimeUM());
    } catch (NullPointerException e) {
      return 0l;
    }

  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.ui.AbstractConversionRoutine#doOutputConverse(S)
   */
  @Override
  protected BigDecimal doOutputConverse(Long value) {
    try {
      return MfUtils.tickToTime(value, getTimeUM());
    } catch (NullPointerException e) {
      return BigDecimal.ZERO;
    }

  }

}
