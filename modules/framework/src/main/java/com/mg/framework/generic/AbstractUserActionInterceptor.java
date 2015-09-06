/*
 * AbstractUserActionInterceptor.java
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
package com.mg.framework.generic;

import com.mg.framework.api.UserActionInterceptor;
import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.validator.ValidationContext;

/**
 * Абстрактная реализация перехватчика действий интерактивной поддержки (добавление, изменение,
 * копирование, просмотр) сервиса бизнес-компонентов
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractUserActionInterceptor.java,v 1.1 2006/10/26 13:32:39 safonov Exp $
 */
public abstract class AbstractUserActionInterceptor implements
    UserActionInterceptor {

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptor#afterInput(com.mg.framework.api.ui.MaintenanceConversationSession, com.mg.framework.api.validator.ValidationContext, boolean, boolean)
   */
  public void afterInput(MaintenanceConversationSession dialogSession,
                         ValidationContext validationContext, boolean isSaveAction,
                         boolean isCloseAction) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptor#beforeOutput(com.mg.framework.api.ui.MaintenanceConversationSession)
   */
  public void beforeOutput(MaintenanceConversationSession dialogSession) {
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptor#getHandledServices()
   */
  public abstract String[] getHandledServices();

  /* (non-Javadoc)
   * @see com.mg.framework.api.UserActionInterceptor#getName()
   */
  public abstract String getName();

}
