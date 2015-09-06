/*
 * ElectronicAddressKindServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.ElectronicAddressKindServiceLocal;
import com.mg.merp.reference.model.ElectronicAddressKind;

import javax.ejb.Stateless;

/**
 * Бизнес-копмонент "Виды эелектронных адресов"
 *
 * @author leonova
 * @version $Id: ElectronicAddressKindServiceBean.java,v 1.3 2006/09/13 07:00:58 leonova Exp $
 */
@Stateless(name = "merp/reference/ElectronicAddressKindService")
public class ElectronicAddressKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ElectronicAddressKind, Integer> implements ElectronicAddressKindServiceLocal {


}
