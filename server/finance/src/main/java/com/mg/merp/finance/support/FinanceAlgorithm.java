/*
 * FinanceAlgorithm.java
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
package com.mg.merp.finance.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс BAi формирования финансовых проводок.  Класс алгоритма должен
 * реализовывать следующий метод <code>protected Double doPerform() throws Exception</code>.
 * Метод возвращает сумму проводки.
 * 
 * <p>Пример данного метода:
 * <pre>
 * protected Double doPerform() throws Exception {
 *     complete(25.0);
 * }</pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: FinanceAlgorithm.java,v 1.4 2007/12/03 15:04:59 safonov Exp $
 * @deprecated use {@link com.mg.merp.finance.support.FinanceBusinessAddin} instead
 */
@Deprecated
public abstract class FinanceAlgorithm extends AbstractBusinessAddin<Double> {

	/**
	 * Возвращает атрибут заголовка создаваемой финансовой операции
	 * 
	 * @param name	наименование атрибута, может иметь следующие значения:
	 * <dl>
	 *   <dt>KEEPDATE
	 *   <dd>дата операции, дата
	 *	 <dt>COMMENT
	 *   <dd>содержание операции, строка
	 *	 <dt>DOCTYPE
	 *   <dd>тип документа, строка
	 *	 <dt>DOCNUMBER
	 *   <dd>номер документа, строка
	 *	 <dt>DOCDATE
	 *   <dd>дата документа, дата
	 *	 <dt>BASETYPE
	 *   <dd>тип документа-основания, строка
	 *	 <dt>BASENUMBER
	 *   <dd>номер документа-основания, строка
	 *	 <dt>BASEDATE
	 *   <dd>тип документа-основания, строка
	 *	 <dt>CONTRACTTYPE
	 *   <dd>тип договора, строка
	 *	 <dt>CONTRACTNUMBER
	 *   <dd>номер договора, строка
	 *	 <dt>CONTRACTDATE
	 *   <dd>дата договора, дата
	 *	 <dt>RESPONSIBLE
	 *   <dd>идентификатор контрагента "ответственный", число 
	 *	 <dt>CURRATE
	 *   <dd>курс валюты, число
	 *	 <dt>CURCODE
	 *   <dd>код валюты, строка
	 * </dl>
	 * @return		значение атрибута
	 * @throws ApplicationException
	 */
	final public Object getFinOperParam(String name) throws ApplicationException {
		//return DataUtils.variantToObject(nativeGetFinOperParam(handle, name));
		//TODO
		return null;
	}

	/**
	 * Возвращает атрибут создаваемой проводки
	 * 
	 * @param name	наименование атрибута, может иметь следующие значения:
	 * <dl>
	 *   <dt> ACCSRC
	 *   <dd> идентификатор счета-источника, число
	 *   <dt> ANLSRC1
	 *   <dd> идентификатор аналитики 1 счета-источника, число
	 *   <dt> ANLSRC2
	 *   <dd> идентификатор аналитики 2 счета-источника, число
	 *   <dt> ANLSRC3
	 *   <dd> идентификатор аналитики 3 счета-источника, число
	 *   <dt> ANLSRC4
	 *   <dd> идентификатор аналитики 4 счета-источника, число
	 *   <dt> ANLSRC5
	 *   <dd> идентификатор аналитики 5 счета-источника, число
	 *   <dt> ACCDST
	 *   <dd> идентификатор счета-приемника, число
	 *   <dt> ANLDST1
	 *   <dd> идентификатор аналитики 1 счета-приемника, число
	 *   <dt> ANLDST2
	 *   <dd> идентификатор аналитики 2 счета-приемника, число
	 *   <dt> ANLDST3
	 *   <dd> идентификатор аналитики 3 счета-приемника, число
	 *   <dt> ANLDST4
	 *   <dd> идентификатор аналитики 4 счета-приемника, число
	 *   <dt> ANLDST5
	 *   <dd> идентификатор аналитики 5 счета-приемника, число
	 * </dl>
	 * @return		значение атрибута
	 * @throws ApplicationException
	 */
	final public Object getFinSpecParam(String name) throws ApplicationException {
		//return DataUtils.variantToObject(nativeGetFinSpecParam(handle, name));
		//TODO
		return null;
	}
	
	/**
	 * Предназначена для записи атрибутов заголовка создаваемой финансовой операции
	 * 
	 * @param name	наименование атрибута
	 * @param value	значение атрибута
	 * @throws ApplicationException
	 * 
	 * @see #getFinOperParam
	 */
	final public void setFinOperParam(String name, Object value) throws ApplicationException {
		//nativeSetFinOperParam(handle, name, DataUtils.objectToVariant(value));
		//TODO
	}

	/**
	 * Предназначена для записи атрибутов проводки создаваемой финансовой операции
	 * 
	 * @param name	наименование атрибута
	 * @param value	значение атрибута
	 * @throws ApplicationException
	 * 
	 * @see #getFinSpecParam
	 */
	final public void setFinSpecParam(String name, Object value) throws ApplicationException {
		//nativeSetFinSpecParam(handle, name, DataUtils.objectToVariant(value));
		//TODO
	}

}
