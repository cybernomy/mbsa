/**
 * SecurityAuditForm.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.core.support.ui;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.MatchMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.security.SecurityAuditType;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.core.model.SecurityAudit;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * Контроллер формы аудита безопасности
 *
 * @author Oleg V. Safonov
 * @version $Id: SecurityAuditForm.java,v 1.1 2007/10/19 06:50:09 safonov Exp $
 */
public class SecurityAuditForm extends AbstractForm {
  private DefaultTableController securityAudit;
  private List<SecurityAudit> auditList;
  private Date eventDateFrom;
  private Date eventDateTill;
  private boolean auditTypeError = true;
  private boolean auditTypeWarning = true;
  private boolean auditTypeInformation = true;
  private String userName;

  public SecurityAuditForm() {
    securityAudit = new DefaultTableController(new SecurityAuditTableModel());
  }

  private void loadSecurityAudit() {
    Criteria criteria = OrmTemplate.createCriteria(SecurityAudit.class);
    if (eventDateFrom != null)
      criteria.add(Restrictions.ge("EventDateTime", eventDateFrom));
    if (eventDateTill != null)
      criteria.add(Restrictions.le("EventDateTime", eventDateTill));
    if (!StringUtils.stringNullOrEmpty(userName))
      criteria.add(Restrictions.like("UserName", userName, MatchMode.START));
    EnumSet<SecurityAuditType> auditTypeSet = EnumSet.allOf(SecurityAuditType.class);
    if (!auditTypeError)
      auditTypeSet.remove(SecurityAuditType.ERROR);
    if (!auditTypeInformation)
      auditTypeSet.remove(SecurityAuditType.INFORMATION);
    if (!auditTypeWarning)
      auditTypeSet.remove(SecurityAuditType.WARNING);
    if (!auditTypeSet.isEmpty())
      criteria.add(Restrictions.in("AuditType", auditTypeSet));
    auditList = OrmTemplate.getInstance().findByCriteria(criteria.setMaxResults(1000));
    securityAudit.getModel().load();
  }

  protected void onActionRefresh(WidgetEvent event) {
    loadSecurityAudit();
    ((SecurityAuditTableModel) securityAudit.getModel()).fireModelChange();
  }

  protected void onActionCheckClose(WidgetEvent event) {
    close();
  }

  /**
   * запуск формы
   */
  public void execute() {
    eventDateFrom = DateTimeUtils.nowDate();
    loadSecurityAudit();
    run();
  }

  private class SecurityAuditTableModel extends DefaultEntityListTableModel<SecurityAudit> {

    /* (non-Javadoc)
     * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
     */
    @Override
    protected void doLoad() {
      setEntityList(auditList);
    }

  }

}
