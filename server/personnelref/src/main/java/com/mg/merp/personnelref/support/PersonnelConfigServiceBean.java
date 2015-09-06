/*
 * PersonnelconfigServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.PersonnelConfigServiceLocal;
import com.mg.merp.personnelref.model.PersonnelConfig;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Управление персоналом>"
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: PersonnelConfigServiceBean.java,v 1.4 2007/01/13 13:31:16 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelConfigService") //$NON-NLS-1$
public class PersonnelConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelConfig, Integer> implements PersonnelConfigServiceLocal {

}
