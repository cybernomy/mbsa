/*
 * PersonnelAttestationServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.PersonnelAttestationServiceLocal;
import com.mg.merp.personnelref.model.PersonnelAttestation;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Аттестация сотрудников"
 *
 * @author leonova
 * @version $Id: PersonnelAttestationServiceBean.java,v 1.3 2006/08/14 12:50:04 leonova Exp $
 */
@Stateless(name = "merp/personnelref/PersonnelAttestationService")
public class PersonnelAttestationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonnelAttestation, Integer> implements PersonnelAttestationServiceLocal {


}
