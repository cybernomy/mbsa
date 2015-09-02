/*
 * ContractorPhoneServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.ContractorPhoneServiceLocal;
import com.mg.merp.reference.model.ContractorPhone;

/**
 * Бизнес-компонент "Телефоны партнеров" 
 * 
 * @author leonova
 * @version $Id: ContractorPhoneServiceBean.java,v 1.4 2006/08/16 04:47:14 leonova Exp $
 */
@Stateless(name="merp/reference/ContractorPhoneService")
public class ContractorPhoneServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ContractorPhone, Integer> implements ContractorPhoneServiceLocal {


}
