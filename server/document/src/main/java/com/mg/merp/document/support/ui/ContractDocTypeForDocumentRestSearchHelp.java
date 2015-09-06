/*
 * ContractDocTypeForDocumentRestSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.merp.document.model.DocumentKind;

/**
 * SearchHelp типов контрактов для вызова в форме условия отбора
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ContractDocTypeForDocumentRestSearchHelp.java,v 1.1 2008/02/12 08:10:11 alikaev Exp
 *          $
 */
public class ContractDocTypeForDocumentRestSearchHelp extends AbstractDocTypeForDocumentRestSearchHelp {

  /* (non-Javadoc)
   * @see com.mg.merp.document.support.ui.AbstractDocTypeForDocumentRestSearchHelp#getDocumentKind()
   */
  @Override
  protected DocumentKind getDocumentKind() {
    return DocumentKind.CONTRACT;
  }

}
