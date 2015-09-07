/*
 * PositionFillServiceLocal.java
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
package com.mg.merp.salary;

import com.mg.merp.personnelref.model.PositionFill;

/**
 * @author leonova
 * @version $Id: PositionFillServiceLocal.java,v 1.2 2006/11/02 16:23:57 safonov Exp $
 */
public interface PositionFillServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<PositionFill, Integer> {

  public void setBasicPosition(int personalAccountId, int positionFillId) throws com.mg.framework.api.ApplicationException;

  public int getCurrentSlPositionId(int staffListId, int positionFillId, java.util.Date actualDate) throws com.mg.framework.api.ApplicationException;

}
