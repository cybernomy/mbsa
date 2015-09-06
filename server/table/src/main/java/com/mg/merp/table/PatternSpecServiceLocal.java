/*
 * PatternSpecServiceLocal.java
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
package com.mg.merp.table;

import com.mg.merp.table.model.PatternHead;
import com.mg.merp.table.model.PatternSpec;

import java.util.List;

/**
 * Бизнес-компонент "Спецификация шаблона графика"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PatternSpecServiceLocal.java,v 1.3 2008/08/12 14:00:54 sharapov Exp $
 */
public interface PatternSpecServiceLocal extends com.mg.framework.api.DataBusinessObjectService<PatternSpec, Integer> {

  /**
   * Имя сервиса
   */
  static final String SERVICE_NAME = "merp/table/PatternSpec"; //$NON-NLS-1$

  /**
   * Изменить спецификацию шаблона
   *
   * @param specList - список позиций
   */
  void updateSpecs(List<PatternSpec[]> specList);

  /**
   * Загрузить спецификацию шаблона
   *
   * @param patternHead - заголовок шаблона
   * @return список позиций
   */
  List<PatternSpec> loadSpecs(PatternHead patternHead);

}
