/*
 * RptEngine.java
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
package com.mg.framework.api.report;

import com.mg.framework.api.orm.PersistentObject;

/**
 * Платформа MBIRT (Millennium BI and Report Tools)
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngine.java,v 1.6 2008/08/12 09:16:49 safonov Exp $
 */
public interface RptEngine {
  /**
   * Имя сервиса Генератора отчётов
   */
  static final String SERVICE_NAME = "merp:service=RptEngineService";

  /**
   * Запуск генерации и вывод отчета, при наличии нескольких шаблонов отчетов будет выполнен
   * интерактивный запрос выбора шаблона
   *
   * @param properties параметры отчёта
   */
  void runAndRenderReport(RptProperties properties);

  /**
   * создание параметров генерации отчета
   *
   * @return экземпляр параметров генерации отчета
   */
  RptProperties createProperies();

  /**
   * Запуск генерации и вывод отчета с указанным шаблоном
   *
   * @param report     отчёт
   * @param properties параметры печати отчёта
   */
  void runAndRenderReport(PersistentObject report, RptProperties properties);

  /**
   * Return the underlying provider object for the ReportEngine, if available. The result of this
   * method is implementation specific.
   *
   * @return delegate
   */
  Object getDelegate();

  /**
   * получить контекст приложения для отчета
   *
   * @param contextId идентификатор контекста приложения
   * @return контекст или <code>null</code> если не установлен
   */
  Object getReportContext(String contextId);

}
