/*
 * OperationServiceLocal.java
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

import com.mg.merp.crm.model.Operation;

/**
 * 
 * @author leonova
 * @version $Id: OperationServiceLocal.java,v 1.1 2006/03/14 11:49:47 safonov Exp $
 */
public interface OperationServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<Operation, Integer>
{

   public java.lang.String sendEmail( int[] keys ) throws com.mg.framework.api.ApplicationException;

   public java.lang.String[] getTaskTemplate(  ) throws com.mg.framework.api.ApplicationException;

}
