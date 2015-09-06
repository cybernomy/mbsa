/*
 * DocumentNumberBusinessAddin.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.document.model.DocHead;

import java.util.Map;

/**
 * Базовый класс BAi формирования номера документа. Класс алгоритма должен реализовывать следующий
 * метод <code>protected String doPerform() throws Exception</code>. Данный метод возвращает
 * сформированный номер документа.
 *
 * <p>Пример данного метода:
 * <pre>
 *  protected void doPerform() throws Exception {
 *  	complete("My number");
 *  }
 * </pre>
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentNumberBusinessAddin.java,v 1.2 2007/09/07 08:59:20 safonov Exp $
 */
public abstract class DocumentNumberBusinessAddin extends AbstractBusinessAddin<String> {
  /**
   * имя параметра для передачи сущности документа
   */
  public final static String DOCHEAD_PARAM = "DOCHEAD_PARAM"; //$NON-NLS-1$
  private DocHead docHead;

  /**
   * получить сущность документа для которого генерится номер
   *
   * @return сущность документа
   */
  protected DocHead getDocHead() {
    return docHead;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    docHead = (DocHead) params.get(DOCHEAD_PARAM);
  }

  /**
   * Возвращает номер документа, сформированный процедурой по-умолчанию
   *
   * @return номер документа
   */
  final public String createDefaultDocNum() throws ApplicationException {
    return new DefaultDocumentNumberStrategy().generateNumber(docHead);
  }

}
