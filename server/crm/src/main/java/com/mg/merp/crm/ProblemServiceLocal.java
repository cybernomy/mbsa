/*
 * ProblemServiceLocal.java
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;
import com.mg.merp.crm.model.Symptom;

/**
 * Сервис бизнес-компонента "Проблемы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProblemServiceLocal.java,v 1.3 2007/01/26 13:16:04 sharapov Exp $
 */
public interface ProblemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Problem, Integer> {

  /**
   * тип папки для симптомов
   */
  final static short FOLDER_PART = 13502;

  /**
   * Добавить симптом проблемы
   */
  void linkSymptom(Problem problem, Symptom symptom);

  /**
   * Удалить симптом проблемы
   */
  void unLinkSymptom(Problem problem, Symptom symptom);

  /**
   * Добавить решение проблемы
   */
  void linkSolution(Problem problem, Solution solution);

  /**
   * Удалить решение проблемы
   */
  void unLinkSolution(Problem problem, Solution solution);

}
