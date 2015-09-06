/*
 * DefaultDocumentNumberStrategy.java
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
package com.mg.merp.document.support;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ReflectionUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.generic.AbstractDocumentNumberStrategy;
import com.mg.merp.document.model.DocHead;

import java.util.List;

/**
 * Стандартная стратегия формирования номера документов
 *
 * @author Oleg V. Safonov
 * @version $Id: DefaultDocumentNumberStrategy.java,v 1.3 2008/12/18 12:31:57 safonov Exp $
 */
public class DefaultDocumentNumberStrategy extends AbstractDocumentNumberStrategy {
  public final static int GENERATED_DOCNUMBER_LENGTH = 10;
  public final static char DOCNUMBER_PREFIX_CHAR = '0';

  /**
   * поиск последнего номера
   *
   * @param docHead сущность документа
   * @return номер документа или <code>null</code> если не найден
   */
  protected String loadDocNumber(DocHead docHead) {
    Criteria criteria = OrmTemplate.createCriteria(ReflectionUtils.getEntityClass(docHead))
        .setProjection(Projections.property("DocNumber"))
        .add(Restrictions.eq("DocType", docHead.getDocType()))
        .add(Restrictions.eq("DocSection", docHead.getDocSection()))
        .add(Restrictions.sqlRestriction(String.format("extract(year from DocDate) = %d", DateTimeUtils.getYear(docHead.getDocDate()))))
        .addOrder(Order.desc("DocNumber"))
        .setMaxResults(1);
    //не учитываем номер данного документа
    if (docHead.getId() != null)
      criteria.add(Restrictions.ne("Id", docHead.getId()));
    List<String> findRes = OrmTemplate.getInstance().findByCriteria(criteria);
    return findRes.isEmpty() ? null : findRes.get(0);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.generic.AbstractDocumentNumberStrategy#doGenerateNumber(com.mg.merp.document.model.DocHead)
   */
  @Override
  protected String doGenerateNumber(DocHead docHead) {
    String number = loadDocNumber(docHead);
    String prefix = StringUtils.EMPTY_STRING;
    int intNumber = 1;
    if (number != null) {
      if (logger.isDebugEnabled())
        logger.debug("Document number founded: ".concat(number));
      int len = number.length() - 1;
      int i = len;
      //удалим последние не цифры
      while ((i >= 0) && !Character.isDigit(number.charAt(i)))
        i--;

      number = number.substring(0, i + 1);
      i = number.length() - 1;
      //найдем последние цифры
      while ((i >= 0) && Character.isDigit(number.charAt(i)))
        i--;

      try {
        intNumber = Integer.parseInt(number.substring(i + 1)) + 1;
        prefix = number.substring(0, i + 1);
      } catch (NumberFormatException nfe) {

      }
    }
    return StringUtils.padLeft(prefix.concat(String.valueOf(intNumber)), GENERATED_DOCNUMBER_LENGTH, DOCNUMBER_PREFIX_CHAR, true);
  }

}
