/*
 * CardHistServiceLocal.java
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

import com.mg.merp.discount.model.CardHist;

/**
 * Бизнес-компонент "История дисконтных карт"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CardHistServiceLocal.java,v 1.3 2007/10/30 13:55:56 sharapov Exp $
 */
public interface CardHistServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CardHist, Integer> {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp/discount/CardHist"; //$NON-NLS-1$

}
