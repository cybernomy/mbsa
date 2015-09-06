/*
 * AbbreviationServiceBean.java
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

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.AbbreviationServiceLocal;
import com.mg.merp.personnelref.model.Abbreviation;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Сокращения"
 *
 * @author leonova
 * @version $Id: AbbreviationServiceBean.java,v 1.3 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name = "merp/personnelref/AbbreviationService")
public class AbbreviationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Abbreviation, Integer> implements AbbreviationServiceLocal {


}
