/*
 * CardUserServiceLocal.java
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
package com.mg.merp.discount;

import com.mg.merp.discount.model.CardUser;

/**
 * Бизнес-компонент "Пользователи дисконтной карты"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardUserServiceLocal.java,v 1.3 2007/11/08 06:38:06 sharapov Exp $
 */
public interface CardUserServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CardUser, Integer> {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/discount/CardUser"; //$NON-NLS-1$

}
