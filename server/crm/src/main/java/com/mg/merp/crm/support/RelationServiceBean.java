/*
 * RelationServiceBean.java
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
import com.mg.merp.crm.RelationServiceLocal;
import com.mg.merp.crm.model.Relation;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Деловые отношения"
 *
 * @author leonova
 * @version $Id: RelationServiceBean.java,v 1.3 2006/09/06 05:24:25 leonova Exp $
 */
@Stateless(name = "merp/crm/RelationService")
public class RelationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Relation, Integer> implements RelationServiceLocal {


}
