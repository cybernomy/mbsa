/*
 * UserActionInterceptorManagerImpl.java
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
package com.mg.framework.service;

import com.mg.framework.api.Logger;
import com.mg.framework.api.UserActionInterceptor;
import com.mg.framework.api.UserActionInterceptorManager;
import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.utils.ServerUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация менеджера перехватчиков действий интерактивной поддержки (добавление, изменение,
 * копирование, просмотр) бизнес-компонентов
 *
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManagerImpl.java,v 1.3 2006/10/26 13:25:45 safonov Exp $
 */
public class UserActionInterceptorManagerImpl implements
    UserActionInterceptorManager {
  private Logger log = ServerUtils.getLogger(getClass());
  private Map<String, List<UserActionInterceptor>> interceptorsMap = Collections.synchronizedMap(new HashMap<String, List<UserActionInterceptor>>());

  /**
   * проверка перехватчика
   *
   * @param interceptor перехватчик
   */
  private void checkInterceptor(UserActionInterceptor interceptor) {
    if (interceptor == null)
      throw new NullPointerException("Interceptor must not be null");
  }

  /**
   * получить список перехватчиков по наименованию обслуживаемой сущности
   *
   * @param name   наименование сущности
   * @param create признак создания списка перехватчиков если не существует
   * @return список перехватчиков
   */
  private List<UserActionInterceptor> getInterceptorsByName(final String name, final boolean create) {
    List<UserActionInterceptor> interceptors = this.interceptorsMap.get(name);
    if (create && interceptors == null) {
      interceptors = Collections.synchronizedList(new ArrayList<UserActionInterceptor>());
      this.interceptorsMap.put(name, interceptors);
    }
    return interceptors;
  }

  /**
   * копируем список чтобы использовать итератор в цикле обхода списка перехватчиков, предотвращая
   * ConcurrentModificationException при изменении оригинального списка, т.к. итераторы не
   * потоко-безопасны
   *
   * @param source оригинальный список перехватчиков
   * @return копия списка
   */
  private List<UserActionInterceptor> cloneInterceptorList(List<UserActionInterceptor> source) {
    return new ArrayList<UserActionInterceptor>(source);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptorManager#registerInterceptor(com.mg.framework.api.UserActionInterceptor)
   */
  public void registerInterceptor(UserActionInterceptor interceptor) {
    checkInterceptor(interceptor);

    for (String serviceName : interceptor.getHandledServices()) {
      List<UserActionInterceptor> interceptors = getInterceptorsByName(serviceName.toUpperCase(), true);
      interceptors.add(interceptor);
    }
    log.info("register user action interceptor: ".concat(interceptor.getName()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptorManager#unregisterInterceptor(com.mg.framework.api.UserActionInterceptor)
   */
  public void unregisterInterceptor(UserActionInterceptor interceptor) {
    checkInterceptor(interceptor);

    for (String serviceName : interceptor.getHandledServices()) {
      List<UserActionInterceptor> interceptors = getInterceptorsByName(serviceName.toUpperCase(), true);
      interceptors.remove(interceptor);
    }
    log.info("unregister user action interceptor: ".concat(interceptor.getName()));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptorManager#invokeBeforeOutputInterceptor(com.mg.framework.api.ui.MaintenanceConversationSession)
   */
  public void invokeBeforeOutputInterceptor(MaintenanceConversationSession dialogSession) {
    if (log.isDebugEnabled())
      log.debug("invoke interceptors with action BeforeOutput");

    List<UserActionInterceptor> interceptors = getInterceptorsByName(dialogSession.getService().getBusinessServiceMetadata().getName(), false);
    if (interceptors == null)
      return;

    for (UserActionInterceptor interceptor : cloneInterceptorList(interceptors))
      interceptor.beforeOutput(dialogSession);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptorManager#invokeAfterInputInterceptor(com.mg.framework.api.ui.MaintenanceConversationSession, com.mg.framework.api.validator.ValidationContext, boolean, boolean)
   */
  public void invokeAfterInputInterceptor(MaintenanceConversationSession dialogSession, ValidationContext validationContext, boolean isSaveAction, boolean isCloseAction) {
    if (log.isDebugEnabled())
      log.debug("invoke interceptors with action AfterInput");

    List<UserActionInterceptor> interceptors = getInterceptorsByName(dialogSession.getService().getBusinessServiceMetadata().getName(), false);
    if (interceptors == null)
      return;

    for (UserActionInterceptor interceptor : cloneInterceptorList(interceptors))
      interceptor.afterInput(dialogSession, validationContext, isSaveAction, isCloseAction);
  }

}
