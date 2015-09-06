/*
 * RptMainServiceBean.java
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

package com.mg.merp.report.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.report.RptMainServiceLocal;
import com.mg.merp.report.model.RptMain;

import org.apache.commons.lang.ArrayUtils;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Репозитарий отчетов"
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptMainServiceBean.java,v 1.7 2009/01/05 08:20:29 safonov Exp $
 */
@Stateless(name = "merp/report/RptMainService")
public class RptMainServiceBean extends AbstractPOJODataBusinessObjectServiceBean<RptMain, Integer> implements RptMainServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.report.RptMainServiceLocal#loadAvailableReports(java.lang.Integer)
   */
  @PermitAll
  public List<RptMain> loadAvailableReports(Integer classId) {
    StringBuilder sql = new StringBuilder("select rm from RptMain rm join rm.ClassLinks rcl where ");
    String[] names;
    Object[] values;
    Integer[] groups = ArrayUtils.toObject(ServerUtils.getUserProfile().getGroups());
    if (classId != null) {
      sql.append("(rcl.SysClass.Id = :classId) and");
      names = new String[]{"classId", "ids"};
      values = new Object[]{classId, groups};
    } else {
      names = new String[]{"ids"};
      values = new Object[]{groups};
    }
    sql.append(" exists (select rr from rm.Permissions rr where rr.SecGroups.Id in (:ids) and rr.Rights = 1) ")
        .append("order by rm.RptName, rm.Priority");
    List<RptMain> result = MiscUtils.convertUncheckedList(RptMain.class, OrmTemplate.getInstance().findByNamedParam(
        sql.toString(), names, values));
    return result;
  }

}
