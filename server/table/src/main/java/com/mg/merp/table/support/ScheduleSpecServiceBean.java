/*
 * ScheduleSpecServiceBean.java
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

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.table.ScheduleSpecServiceLocal;
import com.mg.merp.table.model.ScheduleHead;
import com.mg.merp.table.model.ScheduleSpec;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Спецификация графиков работ в табельном учете"
 *
 * @author leonova
 * @version $Id: ScheduleSpecServiceBean.java,v 1.6 2008/08/12 14:36:17 sharapov Exp $
 */
@Stateless(name = "merp/table/ScheduleSpecService") //$NON-NLS-1$
public class ScheduleSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScheduleSpec, Integer> implements ScheduleSpecServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.table.ScheduleSpecServiceLocal#loadSpecs(com.mg.merp.table.model.ScheduleHead)
   */
  @PermitAll
  public List<ScheduleSpec> loadSpecs(ScheduleHead scheduleHead) {
    return doLoadSpecs(scheduleHead);
  }

  protected List<ScheduleSpec> doLoadSpecs(ScheduleHead scheduleHead) {
    return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(ScheduleSpec.class)
        .add(Restrictions.eq("ScheduleHead", scheduleHead))); //$NON-NLS-1$
  }

}
