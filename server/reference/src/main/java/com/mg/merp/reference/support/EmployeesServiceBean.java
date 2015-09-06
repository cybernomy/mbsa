/*
 * EmployeesServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.EmployeesServiceLocal;
import com.mg.merp.reference.generic.ContractorServiceBean;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.NaturalPerson;

import javax.ejb.Stateless;

/**
 * Бизнес-копмонент "Сотрудники"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: EmployeesServiceBean.java,v 1.8 2009/01/12 14:39:21 safonov Exp $
 */
@Stateless(name = "merp/reference/EmployeesService")
public class EmployeesServiceBean extends ContractorServiceBean<Employees> implements EmployeesServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#adjustOrgUnit(T)
   */
  protected void adjustEmployees(Employees entity) {
    entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
    NaturalPerson np = entity.getNaturalPerson();
    StringBuilder sb = new StringBuilder(np.getSurname());
    if (np.getName() != null)
      sb.append(StringUtils.BLANK_STRING).append(np.getName());
    if (np.getPatronymic() != null)
      sb.append(StringUtils.BLANK_STRING).append(np.getPatronymic());
    entity.setFullName(sb.toString());
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#onCreate(T)
   */
  @Override
  protected void onCreate(Employees entity) {
    adjustEmployees(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.generic.ContractorServiceBean#onStore(T)
   */
  @Override
  protected void onStore(Employees entity) {
    adjustEmployees(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, final Employees entity) {
    context.addRule(new MandatoryAttribute(entity, "NaturalPerson"));
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
    context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") {
      @Override
      protected void doValidate(ValidationContext context) {
        if (OrmTemplate.getInstance().findUniqueByCriteria(Employees.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId())) != null)
          context.getStatus().error(this);
      }
    });
    if (entity.getIsDefault()) {
      context.addRule(new EntityBeanRule(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.EMPLOYESS_ISDEFAULT_UNIQUE), entity, "IsDefault") {
        @Override
        protected void doValidate(ValidationContext context) {
          if (!OrmTemplate.getInstance().findByCriteria(Employees.class, Restrictions.eq("FolderId", entity.getFolderId()), Restrictions.eq("IsDefault", true), Restrictions.ne("Id", entity.getId())).isEmpty())
            context.getStatus().error(this);
        }
      });
    }
  }

}
