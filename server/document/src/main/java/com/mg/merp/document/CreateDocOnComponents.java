/*
 * CreateDocOnComponents.java
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
package com.mg.merp.document;

import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocHeadModel;

/**
 * Сервис создания документа на комплектующие
 *
 * <p> <br>S - класс объекта источника <br>D - результирующий класс <br>P - Образец
 *
 * @author Konstantin S. Alikaev
 * @version $Id: CreateDocOnComponents.java,v 1.1 2007/10/23 13:46:50 alikaev Exp $
 */
public interface CreateDocOnComponents<S extends DocHead, D extends DocHead, P extends DocHeadModel> extends CreateDocumentBasisOf<S, D, P> {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:document=CreateDocOnComponentsService"; //$NON-NLS-1$

}
