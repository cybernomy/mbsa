/*
 * NormSpecLinkServiceBean.java
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

import com.mg.merp.overall.NormSpecLinkServiceLocal;
import com.mg.merp.overall.model.NormSpecLink;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Связь спецификаций норм выдачи и КТУ"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: NormSpecLinkServiceBean.java,v 1.1 2008/06/30 04:19:36 alikaev Exp $
 */
@Stateless(name = "merp/overall/NormSpecLinkService")
public class NormSpecLinkServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<NormSpecLink, Integer> implements NormSpecLinkServiceLocal {

}
