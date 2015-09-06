/*
 * LinkeddocumentServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.crm.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.model.LinkedDocument;
import com.mg.merp.crm.model.Operation;
import com.mg.merp.reference.model.OriginalDocument;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Связанные документы"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LinkedDocumentServiceBean.java,v 1.5 2008/06/17 11:32:12 sharapov Exp $
 */
@Stateless(name = "merp/crm/LinkedDocumentService") //$NON-NLS-1$
public class LinkedDocumentServiceBean extends AbstractPOJODataBusinessObjectServiceBean<LinkedDocument, Integer> implements LinkedDocumentServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.merp.crm.LinkedDocumentServiceLocal#createForOperationWithOriginal(com.mg.merp.crm.model.Operation, com.mg.merp.reference.model.OriginalDocument)
   */
  @PermitAll
  public LinkedDocument createForOperationWithOriginal(Operation operation, OriginalDocument originalDocument) {
    return doCreateForOperationWithOriginal(operation, originalDocument);
  }

  /**
   * Создать связанный документ с оригиналом для действия
   *
   * @param operation        - действие
   * @param originalDocument - оригинал
   * @return связанный документ
   */
  protected LinkedDocument doCreateForOperationWithOriginal(Operation operation, OriginalDocument originalDocument) {
    LinkedDocument linkedDocument = initialize();
    linkedDocument.setOperation(operation);
    linkedDocument.setOriginal(originalDocument);
    create(linkedDocument);
    return linkedDocument;
  }

}
