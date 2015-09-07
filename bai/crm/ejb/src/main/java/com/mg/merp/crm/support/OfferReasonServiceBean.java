/*
 * OfferreasonServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.OfferReasonServiceLocal;
import com.mg.merp.crm.model.OfferReason;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "Причины заключения и потерь предложений"
 *
 * @author leonova
 * @version $Id: OfferReasonServiceBean.java,v 1.5 2006/10/12 05:57:42 leonova Exp $
 */
@Stateless(name = "merp/crm/OfferReasonService")
public class OfferReasonServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OfferReason, Integer> implements OfferReasonServiceLocal {

  @Override
  protected void onValidate(ValidationContext context, OfferReason entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Code"));
  }


}
