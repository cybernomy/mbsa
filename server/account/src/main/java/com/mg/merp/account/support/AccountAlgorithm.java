/*
 * AccountAlgorithm.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс BAi формирования бухгалтерских проводок. Класс алгоритма должен реализовывать
 * следующий метод <code>protected Double doPerform() throws Exception</code> и возвращать сумму
 * проводки или количество для проводки в зависимости от типа алгоритма.
 *
 * <p>Пример данного метода:
 * <pre>
 * protected Double doPerform() throws Exception {
 *     complete(getPartDocSum() * 2);
 * }
 * </pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: AccountAlgorithm.java,v 1.4 2008/03/13 06:20:53 alikaev Exp $
 * @deprecated use {@link com.mg.merp.finance.support.FinanceBusinessAddin} instead
 */
@Deprecated
public abstract class AccountAlgorithm extends AbstractBusinessAddin<Double> {

  /**
   * Возвращает значение атрибута заголовка создаваемой хозяйственной операции
   *
   * @param name наименование атрибута <br><code>KEEPDATE</code>: дата операции, дата
   *             <br><code>COMMENT</code>: содержание операции, строка <br><code>SPECMARK</code>:
   *             особая отметка, строка <br><code>DOCTYPE</code>: тип документа, строка
   *             <br><code>DOCNUMBER</code>: номер документа, строка <br><code>DOCDATE</code>: дата
   *             документа, дата <br><code>BASETYPE</code>: тип документа-основания, строка
   *             <br><code>BASENUMBER</code>: номер документа-основания, строка
   *             <br><code>BASEDATE</code>: тип документа-основания, строка
   *             <br><code>CONTRACTTYPE</code>: тип договора, строка <br><code>CONTRACTNUMBER</code>:
   *             номер договора, строка <br><code>CONTRACTDATE</code>: дата договора, дата
   *             <br><code>FROM</code>: идентификатор контрагента "от кого", число
   *             <br><code>TO</code>: идентификатор контрагента "кому", число
   * @return значение атрибута
   */
  final public Object getEconOperParam(String name) throws ApplicationException {
    //return DataUtils.variantToObject(nativeGetEconOperParam(handle, name));
    //TODO
    return null;
  }

  /**
   * Возвращает значение атрибута заголовка создаваемой проводки
   *
   * @param name наименование атрибута <br><code>ACCDB</code>: идентификатор счета-дебет, число
   *             <br><code>ANLDB1</code>: идентификатор аналитического счета-дебет 1-го уровня,
   *             число <br><code>ANLDB2</code>: идентификатор аналитического счета-дебет 2-го
   *             уровня, число <br><code>ANLDB3</code>: идентификатор аналитического счета-дебет
   *             3-го уровня, число <br><code>ANLDB4</code>: идентификатор аналитического
   *             счета-дебет 4-го уровня, число <br><code>ANLDB5</code>: идентификатор
   *             аналитического счета-дебет 5-го уровня, число <br><code>ACCKT</code>: идентификатор
   *             счета-кредит, число <br><code>ANLKT1</code>: идентификатор аналитического
   *             счета-кредит 1-го уровня, число <br><code>ANLKT2</code>: идентификатор
   *             аналитического счета-кредит 2-го уровня, число <br><code>ANLKT3</code>:
   *             идентификатор аналитического счета-кредит 3-го уровня, число
   *             <br><code>ANLKT4</code>: идентификатор аналитического счета-кредит 4-го уровня,
   *             число <br><code>ANLKT5</code>: идентификатор аналитического счета-кредит 5-го
   *             уровня, число
   * @return значение атрибута
   */
  final public Object getEconSpecParam(String name) throws ApplicationException {
    //return DataUtils.variantToObject(nativeGetEconSpecParam(handle, name));
    //TODO
    return null;
  }

  /**
   * Возвращает сумму списания позиции спецификации документа по бухгалтерскому учету с учетом
   * данных документа и типа счета. При частичной отработке позиции в бухгалтерском учете
   * рассчитывает сумму списания, которая соответствует отрабатываемому количеству
   *
   * @return сумма списания
   */
  final public double calcOutCost() throws ApplicationException {
    //return nativeCalcOutCost(handle);
    //TODO
    return 0;
  }

  /**
   * Выводит на экран пользователя список проводок с условиями отбора по позиции каталога, счету,
   * контрагенту, документу, диапазону дат. Возвращает цену, выбранную пользователем из списка
   *
   * @return цена
   */
  final public double getReturnPrice() throws ApplicationException {
    //return nativeGetReturnPrice(handle);
    //TODO
    return 0;
  }

  /**
   * Устанавливает атрибуты создаваемой хозяйственной операции
   *
   * @param name  наименование атрибута
   * @param value значение атрибута
   * @see #getEconOperParam
   */
  final public void setEconOperParam(String name, Object value) throws ApplicationException {
    //nativeSetEconOperParam(handle, name, DataUtils.objectToVariant(value));
    //TODO
  }

  /**
   * Устанавливает атрибуты создаваемой проводки
   *
   * @param name  наименование атрибута
   * @param value значение атрибута
   * @see #getEconSpecParam
   */
  final public void setEconSpecParam(String name, Object value) throws ApplicationException {
    //nativeSetEconSpecParam(handle, name, DataUtils.objectToVariant(value));
    //TODO
  }

}
