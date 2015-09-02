/*
 * ScheduleDocHeadLinkServiceBean.java
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
import com.mg.merp.lbschedule.ScheduleDocHeadLinkServiceLocal;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;

/**
 * Реализация бизнес-компонента "Связь графика исполнения обязательств с документом"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleDocHeadLinkServiceBean.java,v 1.1 2007/04/21 11:49:33 sharapov Exp $
 */
@Stateless(name="merp/lbschedule/ScheduleDocHeadLinkService") //$NON-NLS-1$
public class ScheduleDocHeadLinkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScheduleDocHeadLink, Integer> implements ScheduleDocHeadLinkServiceLocal {

}
