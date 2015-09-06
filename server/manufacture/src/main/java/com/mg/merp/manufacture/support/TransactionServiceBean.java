/*
 * TransactionServiceBean.java
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

package com.mg.merp.manufacture.support;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.manufacture.TransactionServiceLocal;
import com.mg.merp.manufacture.model.Transaction;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Производственные транзакции"
 *
 * @author Oleg V. Safonov
 * @version $Id: TransactionServiceBean.java,v 1.3 2007/08/06 12:44:53 safonov Exp $
 */
@Stateless(name = "merp/manufacture/TransactionService")
@PermitAll
public class TransactionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Transaction, Integer> implements TransactionServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.manufacture.TransactionServiceLocal#getQuantityByResource(int)
   */
  public BigDecimal getQuantityByResource(int resourceId) {
    BigDecimal result = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(Transaction.class)
        .setProjection(Projections.sum("Quantity"))
        .add(Restrictions.eq("JobRouteResource.Id", resourceId)));
    return result != null ? result : BigDecimal.ZERO;
  }

}
