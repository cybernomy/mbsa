/*
 * NaturalPersonServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.NaturalPerson;

/**
 * Бизнес-компонент "Физические лица"
 * 
 * @author leonova
 * @version $Id: NaturalPersonServiceLocal.java,v 1.2 2006/08/14 05:10:35 leonova Exp $
 */
public interface NaturalPersonServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<NaturalPerson, Integer>
{
	/**
	 * тип папки для физических лиц
	 */
	final static short FOLDER_PART = 2502;

   public byte[] loadPhoto( int personId ) throws com.mg.framework.api.ApplicationException;

   public void storePhoto( int personId,byte[] photo ) throws com.mg.framework.api.ApplicationException;

}
