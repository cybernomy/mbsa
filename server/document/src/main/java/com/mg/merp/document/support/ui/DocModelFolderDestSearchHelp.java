/*
 * DocModelFolderDestSearchHelp.java
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
package com.mg.merp.document.support.ui;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.model.DocHeadModel;
import com.mg.merp.document.model.DocSection;
import com.mg.merp.reference.support.ui.FolderByTypeSearchHelp;


/**
 * SearchHelp папка-приемника в образцах документов
 *
 * @author leonova
 * @version $Id: DocModelFolderDestSearchHelp.java,v 1.3 2007/02/05 15:00:52 safonov Exp $
 */
public class DocModelFolderDestSearchHelp extends FolderByTypeSearchHelp {

  @Override
  protected String[] defineImportContext() {
    return new String[]{"entity"};
  }

  @Override
  protected short getFolderType() {
    int docId = ((DocHeadModel) getImportContextValue("entity")).getDocSection().getId();
    DocSection docS = ServerUtils.getPersistentManager().find(DocSection.class, docId);
    int folderType = docS.getFolderType();
    return (short) folderType;
  }

}
