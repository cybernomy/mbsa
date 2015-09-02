/*
 * ItemSpecAltServiceBean.java
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

package com.mg.merp.lbschedule.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.lbschedule.ItemSpecAltServiceLocal;
import com.mg.merp.lbschedule.model.ItemSpecAlt;

/**
 * Реализация бизнес-компонента "Возможные замены позиции спецификации пункта графика исполнения обязательств" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecAltServiceBean.java,v 1.3 2007/04/17 12:50:59 sharapov Exp $
 */
@Stateless(name="merp/lbschedule/ItemSpecAltService") //$NON-NLS-1$
public class ItemSpecAltServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ItemSpecAlt, Integer> implements ItemSpecAltServiceLocal {

}
