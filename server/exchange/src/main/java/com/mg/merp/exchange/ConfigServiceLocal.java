/*
 * ConfigServiceLocal.java
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
package com.mg.merp.exchange;

/**
 * 
 * @author leonova
 * @version $Id: ConfigServiceLocal.java,v 1.1 2006/03/14 11:49:47 safonov Exp $
 */
public interface ConfigServiceLocal
   extends com.mg.framework.api.BusinessObjectService
{

   public java.lang.String load(  ) throws com.mg.framework.api.ApplicationException;

   public void store( java.lang.String siteCode ) throws com.mg.framework.api.ApplicationException;

}
