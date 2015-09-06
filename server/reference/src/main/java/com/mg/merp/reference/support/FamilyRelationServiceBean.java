/*
 * FamilyRelationServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.FamilyRelationServiceLocal;
import com.mg.merp.reference.model.FamilyRelation;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Степени родства"
 *
 * @author leonova
 * @version $Id: FamilyRelationServiceBean.java,v 1.4 2006/10/20 05:44:57 leonova Exp $
 */
@Stateless(name = "merp/reference/FamilyRelationService")
public class FamilyRelationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FamilyRelation, Integer> implements FamilyRelationServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, FamilyRelation entity) {
    context.addRule(new MandatoryStringAttribute(entity, "RCode"));
  }

}
