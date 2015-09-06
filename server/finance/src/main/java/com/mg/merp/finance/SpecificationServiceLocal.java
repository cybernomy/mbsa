/*
 * SpecificationServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.Specification;

/**
 * Сервис бизнес-компонента "Спецификации фин. операции"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: SpecificationServiceLocal.java,v 1.2 2007/03/12 07:39:36 sharapov Exp $
 */
public interface SpecificationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Specification, Integer> {

  /**
   * Создать спецификацию фин. операции по образцу
   *
   * @param pattern      - образец
   * @param finOperation - фин. операция
   */
  void createSpecificationByPattern(OperationModel pattern, FinOperation finOperation);

  /**
   * Не реализована
   */
  void recalcSum(int finoperId, double curRate);

}
