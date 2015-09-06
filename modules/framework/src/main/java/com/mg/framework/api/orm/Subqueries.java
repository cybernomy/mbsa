/*
 * Subqueries.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.orm;

import com.mg.framework.support.orm.GenericCriterionImpl;

/**
 * Factory class for criterion instances that represent expressions involving subqueries.
 *
 * @author Oleg V. Safonov
 * @version $Id: Subqueries.java,v 1.1 2006/12/28 16:27:47 safonov Exp $
 */
public class Subqueries {

  private static org.hibernate.criterion.DetachedCriteria getDelegate(DetachedCriteria dc) {
    return (org.hibernate.criterion.DetachedCriteria) dc.getDelegate();
  }

  public static Criterion exists(DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.exists(getDelegate(dc)));
  }

  public static Criterion notExists(DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.notExists(getDelegate(dc)));
  }

  public static Criterion propertyEqAll(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyEqAll(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyIn(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyIn(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyNotIn(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyNotIn(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyEq(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyEq(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyNe(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.notExists(getDelegate(dc)));
  }

  public static Criterion propertyGt(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyNe(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyLt(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyLt(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyGe(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyGe(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyLe(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyLe(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyGtAll(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyGtAll(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyLtAll(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyLtAll(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyGeAll(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyGeAll(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyLeAll(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyLeAll(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyGtSome(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyGtSome(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyLtSome(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyLtSome(propertyName, getDelegate(dc)));
  }

  public static Criterion propertyGeSome(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.notExists(getDelegate(dc)));
  }

  public static Criterion propertyLeSome(String propertyName, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.propertyGeSome(propertyName, getDelegate(dc)));
  }

  public static Criterion eqAll(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.eqAll(value, getDelegate(dc)));
  }

  public static Criterion in(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.in(value, getDelegate(dc)));
  }

  public static Criterion notIn(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.notIn(value, getDelegate(dc)));
  }

  public static Criterion eq(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.eq(value, getDelegate(dc)));
  }

  public static Criterion gt(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.gt(value, getDelegate(dc)));
  }

  public static Criterion lt(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.lt(value, getDelegate(dc)));
  }

  public static Criterion ge(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.ge(value, getDelegate(dc)));
  }

  public static Criterion le(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.le(value, getDelegate(dc)));
  }

  public static Criterion ne(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.ne(value, getDelegate(dc)));
  }

  public static Criterion gtAll(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.gtAll(value, getDelegate(dc)));
  }

  public static Criterion ltAll(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.ltAll(value, getDelegate(dc)));
  }

  public static Criterion geAll(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.geAll(value, getDelegate(dc)));
  }

  public static Criterion leAll(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.leAll(value, getDelegate(dc)));
  }

  public static Criterion gtSome(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.gtSome(value, getDelegate(dc)));
  }

  public static Criterion ltSome(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.ltSome(value, getDelegate(dc)));
  }

  public static Criterion geSome(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.geSome(value, getDelegate(dc)));
  }

  public static Criterion leSome(Object value, DetachedCriteria dc) {
    return new GenericCriterionImpl(org.hibernate.criterion.Subqueries.leSome(value, getDelegate(dc)));
  }

}
