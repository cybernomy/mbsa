/*
 * RCCPProcessorServiceLocal.java
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
package com.mg.merp.planning;

/**
 * @author leonova
 * @version $Id: RCCPProcessorServiceLocal.java,v 1.1 2006/03/14 11:49:49 safonov Exp $
 */
public interface RCCPProcessorServiceLocal
    extends com.mg.framework.api.BusinessObjectService {

  public void generateRccp(int rccpVersionId) throws com.mg.framework.api.ApplicationException;

}
