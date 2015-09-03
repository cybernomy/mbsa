/*
 * ManufactureConfigServiceBean.java
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

package com.mg.merp.mfreference.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.mfreference.ManufactureConfigServiceLocal;
import com.mg.merp.mfreference.model.ManufactureConfig;

/**
 * Реализация бизнес-компонента "Конфигурация модуля <Производство>"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ManufactureConfigServiceBean.java,v 1.5 2007/01/13 13:23:13 sharapov Exp $
 */
@Stateless(name="merp/mfreference/ManufactureConfigService") //$NON-NLS-1$
public class ManufactureConfigServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ManufactureConfig, Integer> implements ManufactureConfigServiceLocal {

}