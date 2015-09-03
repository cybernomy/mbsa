/*
 * ConstantValueServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System..
 *
 */

package com.mg.merp.baiengine.support;

import javax.ejb.Stateless;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.baiengine.ConstantValueServiceLocal;
import com.mg.merp.baiengine.model.ConstantValue;

/**
 * Бизнес-компонент "Значение константы"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantValueServiceBean.java,v 1.1 2007/08/21 12:56:51 alikaev Exp $
 *
 */
@Stateless(name="merp/baiengine/ConstantValueService") //$NON-NLS-1$
public class ConstantValueServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ConstantValue, Integer> implements ConstantValueServiceLocal {

}
