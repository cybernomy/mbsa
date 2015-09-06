/*
 * PermissibleAccountsServiceBean.java
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

package com.mg.merp.account.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.account.PermissibleAccountsServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AnlPlan;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.PermissibleAccounts;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Допустимая корреспонденция счетов"
 *
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: PermissibleAccountsServiceBean.java,v 1.9 2009/03/03 15:14:40 sharapov Exp $
 */
@Stateless(name = "merp/account/PermissibleAccountsService")
public class PermissibleAccountsServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PermissibleAccounts, Integer> implements PermissibleAccountsServiceLocal {

  /* (non-Javadoc)
  * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
  */
  @Override
  protected void onValidate(ValidationContext context, final PermissibleAccounts entity) {
    context.addRule(new MandatoryAttribute(entity, "AccDb"));
    context.addRule(new MandatoryAttribute(entity, "AccKt"));
    context.addRule(new Rule() {

      /*
       * (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#getMessage()
       */
      public String getMessage() {
        return Messages.getInstance().getMessage(Messages.PERMISSIBLE_ACCOUNTS_UNIQUE);
      }

      /*
       * (non-Javadoc)
       * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
       */
      public void validate(ValidationContext context) {
        if (entity.getId() == null && !OrmTemplate.getInstance().findByCriteria(PermissibleAccounts.class, Restrictions.eq("AccDb", entity.getAccDb()), Restrictions.eq("AccKt", entity.getAccKt())).isEmpty())
          context.getStatus().error(this);
        if (!OrmTemplate.getInstance().findByCriteria(PermissibleAccounts.class, Restrictions.eq("AccDb", entity.getAccDb()), Restrictions.eq("AccKt", entity.getAccKt()), Restrictions.ne("Id", entity.getId())).isEmpty())
          context.getStatus().error(this);
      }
    });
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.account.PermissibleAccountsServiceLocal#createFromEconomicOper(java.util.Date, java.util.Date)
   */
  public void createFromEconomicOper(Date beginDate, Date endDate) {
    doCreateFromEconomicOper(beginDate, endDate);
  }

  /**
   * Создание на основе хоз. операций
   *
   * @param beginDate - с даты
   * @param endDate   - по дату
   */
  protected void doCreateFromEconomicOper(Date beginDate, Date endDate) {
    if (beginDate == null)
      beginDate = DateTimeUtils.ZERO_DATE;
    if (endDate == null)
      endDate = DateTimeUtils.nowDate();
    if (beginDate.compareTo(endDate) > 0)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.INVALID_RANGE_DATE));
    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    List<EconomicSpec> economicSpecs = ormTemplate.findByCriteria(OrmTemplate.createCriteria(EconomicSpec.class)
        .createAlias("EconomicOper", "eo")
        .add(Restrictions.between("eo.KeepDate", beginDate, endDate))
        .setFlushMode(FlushMode.MANUAL));
    for (EconomicSpec economicSpec : economicSpecs) {
      AccPlan accDb = economicSpec.getAccDb();
      AnlPlan anlDb1 = economicSpec.getAnlDb1();
      AnlPlan anlDb2 = economicSpec.getAnlDb2();
      AnlPlan anlDb3 = economicSpec.getAnlDb3();
      AnlPlan anlDb4 = economicSpec.getAnlDb4();
      AnlPlan anlDb5 = economicSpec.getAnlDb5();
      AccPlan accKt = economicSpec.getAccKt();
      AnlPlan anlKt1 = economicSpec.getAnlKt1();
      AnlPlan anlKt2 = economicSpec.getAnlKt2();
      AnlPlan anlKt3 = economicSpec.getAnlKt3();
      AnlPlan anlKt4 = economicSpec.getAnlKt4();
      AnlPlan anlKt5 = economicSpec.getAnlKt5();
      Criteria criteria = OrmTemplate.createCriteria(PermissibleAccounts.class)
          .add(Restrictions.eq("AccDb", accDb))
          .add(Restrictions.eq("AccKt", accKt));
      if (anlDb1 == null)
        criteria.add(Restrictions.isNull("AnlDb1"));
      else
        criteria.add(Restrictions.eq("AnlDb1", anlDb1));
      if (anlDb2 == null)
        criteria.add(Restrictions.isNull("AnlDb2"));
      else
        criteria.add(Restrictions.eq("AnlDb2", anlDb2));
      if (anlDb3 == null)
        criteria.add(Restrictions.isNull("AnlDb3"));
      else
        criteria.add(Restrictions.eq("AnlDb3", anlDb3));
      if (anlDb4 == null)
        criteria.add(Restrictions.isNull("AnlDb4"));
      else
        criteria.add(Restrictions.eq("AnlDb4", anlDb4));
      if (anlDb5 == null)
        criteria.add(Restrictions.isNull("AnlDb5"));
      else
        criteria.add(Restrictions.eq("AnlDb5", anlDb5));

      if (anlKt1 == null)
        criteria.add(Restrictions.isNull("AnlKt1"));
      else
        criteria.add(Restrictions.eq("AnlKt1", anlKt1));
      if (anlKt2 == null)
        criteria.add(Restrictions.isNull("AnlKt2"));
      else
        criteria.add(Restrictions.eq("AnlKt2", anlKt2));
      if (anlKt3 == null)
        criteria.add(Restrictions.isNull("AnlKt3"));
      else
        criteria.add(Restrictions.eq("AnlKt3", anlKt3));
      if (anlKt4 == null)
        criteria.add(Restrictions.isNull("AnlKt4"));
      else
        criteria.add(Restrictions.eq("AnlKt4", anlKt4));
      if (anlKt5 == null)
        criteria.add(Restrictions.isNull("AnlKt5"));
      else
        criteria.add(Restrictions.eq("AnlKt5", anlKt5));
      List<PermissibleAccounts> permAccList = ormTemplate.findByCriteria(criteria);
      // если среди допустимых счетов нет счета с аттрибутами проводки
      // создаем домустимый счет
      if (permAccList.isEmpty()) {
        PermissibleAccounts permAcc = initialize();
        permAcc.setAccDb(accDb);
        permAcc.setAnlDb1(anlDb1);
        permAcc.setAnlDb2(anlDb2);
        permAcc.setAnlDb3(anlDb3);
        permAcc.setAnlDb4(anlDb4);
        permAcc.setAnlDb5(anlDb5);
        permAcc.setAccKt(accKt);
        permAcc.setAnlKt1(anlKt1);
        permAcc.setAnlKt1(anlKt2);
        permAcc.setAnlKt1(anlKt3);
        permAcc.setAnlKt1(anlKt4);
        permAcc.setAnlKt1(anlKt5);
        create(permAcc);
      }
    }
  }

  /* (non-Javadoc)
  * @see com.mg.merp.account.PermissibleAccountsServiceLocal#ckeckPermissibleCorrespondance(com.mg.merp.account.model.EconomicSpec)
  */
  @PermitAll
  public void ckeckPermissibleCorrespondance(EconomicSpec economicSpec) {
    doCkeckPermissibleCorrespondance(economicSpec);
  }

  protected void doCkeckPermissibleCorrespondance(EconomicSpec economicSpec) {
    AccPlan accKt = economicSpec.getAccKt();
    AccPlan accDb = economicSpec.getAccDb();
    if (accKt == null || accDb == null)
      return;

    OrmTemplate ormTemplate = OrmTemplate.getInstance();
    Criteria criteria = OrmTemplate.createCriteria(PermissibleAccounts.class)
        .setProjection(Projections.rowCount())
        .setFlushMode(FlushMode.MANUAL);

    Integer avalibleAccountsCount = ormTemplate.findUniqueByCriteria(criteria);
    if (avalibleAccountsCount > 0) {
      AnlPlan anlDb1 = economicSpec.getAnlDb1();
      AnlPlan anlDb2 = economicSpec.getAnlDb2();
      AnlPlan anlDb3 = economicSpec.getAnlDb3();
      AnlPlan anlDb4 = economicSpec.getAnlDb4();
      AnlPlan anlDb5 = economicSpec.getAnlDb5();

      AnlPlan anlKt1 = economicSpec.getAnlKt1();
      AnlPlan anlKt2 = economicSpec.getAnlKt2();
      AnlPlan anlKt3 = economicSpec.getAnlKt3();
      AnlPlan anlKt4 = economicSpec.getAnlKt4();
      AnlPlan anlKt5 = economicSpec.getAnlKt5();

      criteria.add(Restrictions.eq("AccDb", accDb));
      criteria.add(Restrictions.eq("AccKt", accKt));

      criteria.add(anlDb1 != null ? Restrictions.eq("AnlDb1", anlDb1) : Restrictions.isNull("AnlDb1"));
      criteria.add(anlDb2 != null ? Restrictions.eq("AnlDb2", anlDb2) : Restrictions.isNull("AnlDb2"));
      criteria.add(anlDb3 != null ? Restrictions.eq("AnlDb3", anlDb3) : Restrictions.isNull("AnlDb3"));
      criteria.add(anlDb4 != null ? Restrictions.eq("AnlDb4", anlDb4) : Restrictions.isNull("AnlDb4"));
      criteria.add(anlDb5 != null ? Restrictions.eq("AnlDb5", anlDb5) : Restrictions.isNull("AnlDb5"));

      criteria.add(anlKt1 != null ? Restrictions.eq("AnlKt1", anlKt1) : Restrictions.isNull("AnlKt1"));
      criteria.add(anlKt2 != null ? Restrictions.eq("AnlKt2", anlKt2) : Restrictions.isNull("AnlKt2"));
      criteria.add(anlKt3 != null ? Restrictions.eq("AnlKt3", anlKt3) : Restrictions.isNull("AnlKt3"));
      criteria.add(anlKt4 != null ? Restrictions.eq("AnlKt4", anlKt4) : Restrictions.isNull("AnlKt4"));
      criteria.add(anlKt5 != null ? Restrictions.eq("AnlKt5", anlKt5) : Restrictions.isNull("AnlKt5"));

      Integer permissibleCount = ormTemplate.findUniqueByCriteria(criteria);
      if (permissibleCount == 0) {
        Messages msg = Messages.getInstance();
        String accAnlFormat = msg.getMessage(Messages.ACC_ANL_FORMAT);
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append(MessageFormat.format(msg.getMessage(Messages.ACC_ANL_DB_FORMAT), accDb.getUpAcc()));
        if (anlDb1 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlDb1.getUpCode()));
        if (anlDb2 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlDb2.getUpCode()));
        if (anlDb3 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlDb3.getUpCode()));
        if (anlDb4 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlDb4.getUpCode()));
        if (anlDb5 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlDb5.getUpCode()));

        errorMessage.append(MessageFormat.format(msg.getMessage(Messages.ACC_ANL_KT_FORMAT), accKt.getUpAcc()));
        if (anlKt1 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlKt1.getUpCode()));
        if (anlKt2 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlKt2.getUpCode()));
        if (anlKt3 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlKt3.getUpCode()));
        if (anlKt4 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlKt4.getUpCode()));
        if (anlKt5 != null)
          errorMessage.append(MessageFormat.format(accAnlFormat, anlKt5.getUpCode()));

        throw new BusinessException(msg.getMessage(Messages.ACC_WRONG_PERMISSIBLE) + errorMessage.toString());
      }
    }
  }

}
