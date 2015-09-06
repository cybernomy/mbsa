/*
 * DocNumberingAlgorithm.java
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
package com.mg.merp.document.support;


/**
 * Базовый класс BAi формирования номера документа. Класс алгоритма должен реализовывать следующий
 * метод <code>protected String doPerform() throws Exception</code>. Данный метод возвращает
 * сформированный номер документа.
 *
 * <p>Пример данного метода:
 * <pre>
 *  protected String doPerform() throws Exception {
 *  	complete("My number");
 *  }
 * </pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: DocNumberingAlgorithm.java,v 1.5 2007/03/23 14:32:35 safonov Exp $
 * @deprecated use {@link DocumentNumberBusinessAddin} instead
 */
@Deprecated
public abstract class DocNumberingAlgorithm extends DocumentNumberBusinessAddin {
}
