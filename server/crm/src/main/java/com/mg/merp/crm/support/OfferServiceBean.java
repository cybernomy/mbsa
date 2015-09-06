/*
 * OfferServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.crm.OfferServiceLocal;
import com.mg.merp.crm.model.Offer;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Предложения"
 *
 * @author leonova
 * @version $Id: OfferServiceBean.java,v 1.3 2006/09/06 05:24:25 leonova Exp $
 */
@Stateless(name = "merp/crm/OfferService")
public class OfferServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Offer, Integer> implements OfferServiceLocal {


}
