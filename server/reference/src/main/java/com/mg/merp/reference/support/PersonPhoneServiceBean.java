/*
 * PersonPhoneServiceBean.java
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

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.reference.PersonPhoneServiceLocal;
import com.mg.merp.reference.model.PersonPhone;

import javax.ejb.Stateless;

/**
 * Бизнес - компонент "Телефоны физических лиц"
 *
 * @author leonova
 * @version $Id: PersonPhoneServiceBean.java,v 1.3 2006/08/11 07:31:41 leonova Exp $
 */
@Stateless(name = "merp/reference/PersonPhoneService")
public class PersonPhoneServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonPhone, Integer> implements PersonPhoneServiceLocal {


}
