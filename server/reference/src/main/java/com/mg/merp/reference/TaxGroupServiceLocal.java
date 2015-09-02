/*
 * TaxGroupServiceLocal.java
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

import java.util.List;

import com.mg.merp.reference.model.Tax;
import com.mg.merp.reference.model.TaxGroup;
import com.mg.merp.reference.model.TaxLink;

/**
 * 
 * @author leonova
 * @version $Id: TaxGroupServiceLocal.java,v 1.2 2006/04/24 08:59:58 safonov Exp $
 */
public interface TaxGroupServiceLocal
   extends com.mg.framework.api.DataBusinessObjectService<TaxGroup, Integer>
{

   public TaxLink includeTax(TaxGroup taxGroup, Tax tax, short feeOrder) throws com.mg.framework.api.ApplicationException;

   public void excludeTax(TaxLink taxLink) throws com.mg.framework.api.ApplicationException;

   public void editTax(TaxLink taxKink, short feeOrder) throws com.mg.framework.api.ApplicationException;

   public List<TaxLink> loadTaxesLink(TaxGroup taxGroup) throws com.mg.framework.api.ApplicationException;

}
