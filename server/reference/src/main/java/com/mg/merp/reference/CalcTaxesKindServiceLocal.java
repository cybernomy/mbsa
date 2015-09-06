/*
 * CalcTaxesKindServiceLocal.java
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

import com.mg.merp.reference.model.CalcTaxesKind;
import com.mg.merp.reference.model.CalcTaxesLink;
import com.mg.merp.reference.model.CalcTaxesSubject;
import com.mg.merp.reference.model.Tax;

import java.util.List;

/**
 * @author leonova
 * @version $Id: CalcTaxesKindServiceLocal.java,v 1.3 2006/10/24 10:29:59 leonova Exp $
 */
public interface CalcTaxesKindServiceLocal
    extends com.mg.framework.api.DataBusinessObjectService<CalcTaxesKind, Integer> {

  public CalcTaxesLink includeTax(CalcTaxesKind calcTaxKind, Tax tax, Short feeOrder, boolean included, CalcTaxesSubject subject) throws com.mg.framework.api.ApplicationException;

  public void excludeTax(CalcTaxesLink taxLink) throws com.mg.framework.api.ApplicationException;

  public void editTax(CalcTaxesLink taxLink, Tax tax, Short feeOrder, boolean included, CalcTaxesSubject subject) throws com.mg.framework.api.ApplicationException;

  public List<CalcTaxesLink> loadTaxesLink(CalcTaxesKind calcTaxKind) throws com.mg.framework.api.ApplicationException;

	
	
 /*  public void includeTax( int id,int calcTaxesKindId,int taxId,java.lang.String taxCode,long feeOrder,boolean included,int subject ) throws com.mg.framework.api.ApplicationException;

   public void excludeTax( int linkId ) throws com.mg.framework.api.ApplicationException;

   public void updateLink( int id,int calcTaxesKindId,int taxId,java.lang.String taxCode,long feeOrder,boolean included,int subject ) throws com.mg.framework.api.ApplicationException;

   public byte[] loadTaxesBrowse( int kindId ) throws com.mg.framework.api.ApplicationException;
*/
}
