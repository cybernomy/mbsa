/*
 * InternalActHeadServiceLocal.java
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
package com.mg.merp.account;

import com.mg.merp.document.model.DocHead;

/**
 * @author leonova
 * @version $Id: InternalActHeadServiceLocal.java,v 1.3 2006/09/20 10:43:40 safonov Exp $
 */
public interface InternalActHeadServiceLocal
    extends com.mg.merp.document.GoodsDocument<DocHead, Integer, InternalActHeadModelServiceLocal, InternalActSpecServiceLocal> {
  /**
   * тип папки для внутренних актов
   */
  final static short FOLDER_PART = 28;

  /**
   * docsection для внутренних актов
   */
  final static short DOCSECTION = 8;
}
