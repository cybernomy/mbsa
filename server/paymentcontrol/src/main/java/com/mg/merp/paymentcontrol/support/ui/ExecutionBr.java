/*
 * ExecutionBr.java
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
package com.mg.merp.paymentcontrol.support.ui;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.generic.ui.DefaultHierarchyBrowseForm;
import com.mg.framework.service.ApplicationDictionaryLocator;

/**
 * @author leonova
 * @version $Id: ExecutionBr.java,v 1.4 2006/10/04 07:10:24 leonova Exp $
 */
public class ExecutionBr extends DefaultHierarchyBrowseForm {

  public ExecutionBr() throws Exception {
    super();
    folderService = (DataBusinessObjectService) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/Folder");
    treeUIProperties.put("FolderType", new Integer(13400));
  }


}
