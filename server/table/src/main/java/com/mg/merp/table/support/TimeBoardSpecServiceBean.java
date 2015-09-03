/*
 * TimeBoardSpecServiceBean.java
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

package com.mg.merp.table.support;

import javax.ejb.Stateless;

import com.mg.merp.table.TimeBoardSpecServiceLocal;
import com.mg.merp.table.model.TimeBoardSpec;

/**
 * Реализация бизнес-компонента "Спецификация табеля"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: TimeBoardSpecServiceBean.java,v 1.6 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name="merp/table/TimeBoardSpecService") //$NON-NLS-1$
public class TimeBoardSpecServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<TimeBoardSpec, Integer> implements TimeBoardSpecServiceLocal {

}
