/*
 * CatalogNumberingAlgorithm.java
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
package com.mg.merp.reference.support;

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;

/**
 * Базовый класс BAi формирования кода позиции каталога товаров и услуг. Класс алгоритма должен
 * реализовывать следующий метод <code>protected String doPerform() throws Exception</code>.
 * Метод возвращает сформированный код позиции КТУ.
 * 
 * <p>Пример данного метода:
 * <pre>
 * protected String doPerform() throws Exception {
 *     complete("My code");
 * }</pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: CatalogNumberingAlgorithm.java,v 1.5 2006/09/21 14:42:28 safonov Exp $
 *
 */
public abstract class CatalogNumberingAlgorithm extends AbstractBusinessAddin<String> {
    
    /**
     * Возвращает код, сформированный процедурой по-умолчанию. Данная процедура формирует
     * код из последовательности кодов папок, составляющих путь от папки с идентификатором
     * RootId до текущей папки, разделенных символом Separator. Последовательность завершается
     * номером элемента в данной папке.
     * 
     * @param separator     разделитель
     * @param rootId        может быть 0, тогда путь берется от корневой папки
     * @param includeRoot   указывает, включать ли в путь папку RootId
     * @param numWidth      определяет ширину заключительного элемента кода (номера элемента). Если число символов в номере меньше NumWidth, то номер дополняется слева нулями
     * @return
     */
	final public String createDefaultCatalogCode(String separator, int rootId, boolean includeRoot, int numWidth) throws ApplicationException {
		return internalCreateDefaultCatalogCode(separator, rootId, includeRoot, numWidth);
	}

    /**
     * Возвращает код, сформированный процедурой по-умолчанию. Данная процедура формирует
     * код из последовательности кодов папок, составляющих путь от папки с идентификатором
     * RootId до текущей папки, разделенных символом Separator. Последовательность завершается
     * номером элемента в данной папке.
     * 
     * @param separator     разделитель
     * @param rootId        может быть 0, тогда путь берется от корневой папки
     * @param includeRoot   указывает, включать ли в путь папку RootId
     * @return
     */
	final public String createDefaultCatalogCode(String separator, int rootId, boolean includeRoot) throws ApplicationException {
		return internalCreateDefaultCatalogCode(separator, rootId, includeRoot, 0);
	}

    /**
     * Возвращает соответствующий атрибут элемента каталога (см. справочник атрибутов
     * бизнес-компонентов, вызываемый из окна редактирования алгоритма).
     * 
     * @param name  наименование атрибута, может иметь следующие значения:
     * <dl>
     *   <dt>Id
     *   <dd>идентификатор
     *   <dt>FolderId
     *   <dd>идентификатор папки
     *   <dt>Code
     *   <dd>код
     *   <dt>FullName
     *   <dd>наименование
     *   <dt>BarCode
     *   <dd>штрих-код
     *   <dt>PLUCode
     *   <dd>код PLU
     *   <dt>OKDPCode
     *   <dd>код ОКДП
     *   <dt>Measure1_Id
     *   <dd>идентификатор основной единицы измерения
     *   <dt>Measure2_Id
     *   <dd>идентификатор дополнительной единицы измерения
     *   <dt>Weight
     *   <dd>вес
     *   <dt>Volume
     *   <dd>объем
     *   <dt>Articul
     *   <dd>артикул
     * </dl>
     * @return      значение атрибута
     * @throws ApplicationException
     */
	final public Object getAttribute(String name) throws ApplicationException {
		return internalGetAttribute(name);
	}

	protected Object internalGetAttribute(String name) throws ApplicationException {
		//TODO
		//return DataUtils.variantToObject(nativeGetAttribute(handle, name));
		return null;
	}

	protected String internalCreateDefaultCatalogCode(String separator, int rootId, boolean includeRoot, int numWidth) throws ApplicationException {
		//TODO
		//return nativeCreateDefaultCatalogCode(handle, separator, rootId, includeRoot, numWidth);
		return null;
	}

}
