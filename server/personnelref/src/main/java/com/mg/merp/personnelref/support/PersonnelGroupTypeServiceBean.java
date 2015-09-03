/*
 * PersonnelGroupTypeServiceBean.java
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

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.PersonnelGroupTypeServiceLocal;
import com.mg.merp.personnelref.model.PersonnelGroupType;

/**
 * Бизнес-компонент "Типы групп" 
 * 
 * @author leonova
 * @version $Id: PersonnelGroupTypeServiceBean.java,v 1.3 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name="merp/personnelref/PersonnelGroupTypeService")
public class PersonnelGroupTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelGroupType, Integer> implements PersonnelGroupTypeServiceLocal {


}
