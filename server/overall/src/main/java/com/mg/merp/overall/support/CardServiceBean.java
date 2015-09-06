/*
 * CardServiceBean.java
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

package com.mg.merp.overall.support;

import com.mg.merp.overall.CardServiceLocal;
import com.mg.merp.overall.model.OvrCard;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Лицевые карточки сотрудников"
 *
 * @author Oleg V. Safonov
 * @version $Id: CardServiceBean.java,v 1.1 2008/06/30 04:19:36 alikaev Exp $
 */
@Stateless(name = "merp/overall/CardService")
public class CardServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<OvrCard, Integer> implements CardServiceLocal {

}
