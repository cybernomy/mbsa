/**
 * CreateDocumentByPatternStrategy.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.document;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

import java.io.Serializable;

/**
 * Стратегия создания документа по образцу, в качестве реализации рекомендуется использовать
 * экземпляр класса наследованного от {@link com.mg.merp.document.generic.AbstractCreateDocumentByPatternStrategy}
 *
 * @author Oleg V. Safonov
 * @version $Id: CreateDocumentByPatternStrategy.java,v 1.1 2007/09/28 12:03:43 safonov Exp $
 */
public interface CreateDocumentByPatternStrategy extends Serializable {

  /**
   * создание документа на основании образца
   *
   * @param document        документ
   * @param documentPattern образец
   * @return документ
   */
  DocHead createDocument(DocHead document, DocHeadModel documentPattern);

}
