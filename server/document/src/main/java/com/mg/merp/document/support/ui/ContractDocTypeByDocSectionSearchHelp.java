/*
 * DocTypeByDocSectionSearchHelp.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
 * SearchHelp для типов контрактов по DocSection документа
 *
 * @author leonova
 * @version $Id: ContractDocTypeByDocSectionSearchHelp.java,v 1.2 2006/10/16 06:26:29 leonova Exp $
 */
public class ContractDocTypeByDocSectionSearchHelp extends AbstractDocTypeSearchHelp {

  @Override
  protected boolean isDocument() {
    return true;
  }

  @Override
  protected DocumentKind getDocumentKind() {
    return DocumentKind.CONTRACT;
  }

  @Override
  protected boolean isDocModel() {
    return false;
  }

}
