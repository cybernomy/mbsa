/*
 * SimpleExpression.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.support.orm;

import com.mg.framework.api.orm.Criterion;

/**
 * @author Oleg V. Safonov
 * @version $Id: SimpleExpression.java,v 1.1 2006/01/24 14:14:25 safonov Exp $
 */
public class SimpleExpression implements Criterion {
  private org.hibernate.criterion.Criterion hibernateCriterion;

  public SimpleExpression(org.hibernate.criterion.Criterion hibernateCriterion) {
    this.hibernateCriterion = hibernateCriterion;
  }

  public org.hibernate.criterion.Criterion getHibernateCriterion() {
    return hibernateCriterion;
  }
}
