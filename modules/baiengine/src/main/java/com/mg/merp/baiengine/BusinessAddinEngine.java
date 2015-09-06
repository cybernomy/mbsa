/*
 * BusinessAddinEngine.java
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
package com.mg.merp.baiengine;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.PersistentObject;

import java.util.Map;

/**
 * Сервис машины BAi (Business Add-in) бизнес расширений системы
 *
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinEngine.java,v 1.4 2007/11/15 13:13:09 safonov Exp $
 */
public interface BusinessAddinEngine {
  /**
   * имя JMX сервиса
   */
  String SERVICE_NAME = "merp:baiengine=BusinessAddinEngineService";

  /**
   * выполнить бизнес расширение системы
   *
   * @param <T>      тип результата
   * @param code     код реализации в репозитарии BAi
   * @param params   параметры передаваемые реализации BAi
   * @param listener слушатели результата выполнения BAi
   * @throws BusinessException при возникновении прикладных ИС, как правило используется для
   *                           сообщений
   */
  <T> void perform(String code, Map<String, ? extends Object> params, BusinessAddinListener<T> listener) throws BusinessException;

  /**
   * выполнить бизнес расширение системы
   *
   * @param <T>        тип результата
   * @param repository репозитарии BAi
   * @param params     параметры передаваемые реализации BAi
   * @param listener   слушатели результата выполнения BAi
   * @throws BusinessException при возникновении прикладных ИС, как правило используется для
   *                           сообщений
   */
  <T> void perform(PersistentObject repository, Map<String, ? extends Object> params, BusinessAddinListener<T> listener) throws BusinessException;

  /**
   * создать бизнес расширение системы
   *
   * @param <T>  тип результата
   * @param code код реализации в репозитарии BAi
   * @return бизнес расширение системы
   */
  <T> BusinessAddin<T> createBusinessAddin(String code);

  /**
   * создать бизнес расширение системы
   *
   * @param <T>        тип результата
   * @param repository репозитарии BAi
   * @return бизнес расширение системы
   */
  <T> BusinessAddin<T> createBusinessAddin(PersistentObject repository);

}
