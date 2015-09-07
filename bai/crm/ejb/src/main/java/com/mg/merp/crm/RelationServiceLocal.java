/*
 * RelationServiceLocal.java
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
package com.mg.merp.crm;

import com.mg.merp.crm.model.Relation;

/**
 * @author leonova
 * @version $Id: RelationServiceLocal.java,v 1.2 2006/09/06 05:25:06 leonova Exp $
 */
public interface RelationServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<Relation, Integer> {
  /**
   * тип папки для деловых отношений
   */
  final static short FOLDER_PART = 13500;
}
