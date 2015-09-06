/*
 * ResourceServiceLocal.java
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
package com.mg.merp.bpm;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.bpm.model.Resource;

/**
 * @author Oleg V. Safonov
 * @version $Id: ResourceServiceLocal.java,v 1.1 2007/05/28 13:05:48 safonov Exp $
 */
public interface ResourceServiceLocal extends DataBusinessObjectService<Resource, Integer> {

  void addMember(int resourceId, int roleId);

  void removeMember(int resourceId, int roleId);

}
