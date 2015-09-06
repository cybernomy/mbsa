/**
 * PreDocFlowProcessBusinessAddin.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.docflow.support;

import com.mg.merp.docflow.generic.BaseDocFlowBusinessAddin;

/**
 * Базовый класс BAi выполняемых перед базовыми процессами (отработка или откат) документа в
 * документообороте. Класс BAi должен реализовывать следующий метод <code>protected String
 * doPerform() throws Exception</code>. В случае успешного выполнения необходимо вызвать {@link
 * #complete(Void)}, если необходимо прервать выполнение документооборота необходимо вызвать {@link
 * #abort()}. Класс BAi предназначен для выполнения настраиваемых проверок и(или) коррекции
 * параметров выполнения ДО перед запуском процесса отработки или отката.
 *
 * @author Oleg V. Safonov
 * @version $Id: PreDocFlowProcessBusinessAddin.java,v 1.1 2007/12/14 08:48:53 safonov Exp $
 */
public abstract class PreDocFlowProcessBusinessAddin extends
    BaseDocFlowBusinessAddin {

}
