/*
 * DocFlowBusinessAddin.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support;

import com.mg.framework.utils.StringUtils;
import com.mg.merp.docflow.generic.BaseDocFlowBusinessAddin;
import com.mg.merp.docprocess.model.DocHeadState;

/**
 * Базовый класс BAi отработки (отката) документа в документообороте. Класс BAi должен реализовывать
 * следующий метод <code>protected String doPerform() throws Exception</code>. В случае успешного
 * выполнения необходимо вызвать {@link #complete(Void)}, если необходимо прервать выполнение
 * документооборота необходимо вызвать {@link #abort()}. Класс BAi предназначенный для отработки
 * может реализовывать методы {@link #doGetDocActionResultTextRepresentation(DocHeadState)} и {@link
 * #doShowDocActionResult(DocHeadState)} предназначенные для вывода в пользовательском интерфейсе
 * результатов выполнения этапа ДО.
 *
 * <p>Пример метода {@link #doPerform()}:
 * <pre>
 *  protected void doPerform() throws Exception {
 *  	...
 *  	if (continue)
 *  		complete(null);
 *  	else
 *  		abort();
 *  }
 * </pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowBusinessAddin.java,v 1.4 2007/12/14 08:48:53 safonov Exp $
 */
public abstract class DocFlowBusinessAddin extends BaseDocFlowBusinessAddin implements com.mg.merp.docflow.BAiDocFlowPluginViewer {

  /**
   * реализация генерации текстового представления результата выполнения этапа ДО, конкретная
   * реализация этапа ДО должна переопределить данный метод если есть возможность показать результат
   * в строковом виде, данная строка будет выведена в форме истории ДО
   *
   * @param docHeadState состояние ДО
   * @return текстовое представление
   */
  protected String doGetDocActionResultTextRepresentation(DocHeadState docHeadState) {
    return StringUtils.EMPTY_STRING;
  }

  /**
   * реализация показа результата выполнения этапа ДО (например если этап создал документ, то можно
   * показать данный документ в UI), данный метод будет вызван при выборе пункта меню "Показать
   * результат этапа" в истории ДО
   *
   * @param docHeadState состояние ДО
   */
  protected void doShowDocActionResult(DocHeadState docHeadState) {
    //do nothing
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.BAiDocFlowPluginViewer#getDocActionResultTextRepresentation(com.mg.merp.docprocess.model.DocHeadState)
   */
  public final String getDocActionResultTextRepresentation(DocHeadState docHeadState) {
    return doGetDocActionResultTextRepresentation(docHeadState);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.BAiDocFlowPluginViewer#showDocActionResult(com.mg.merp.docprocess.model.DocHeadState)
   */
  public final void showDocActionResult(DocHeadState docHeadState) {
    doShowDocActionResult(docHeadState);
  }

}
