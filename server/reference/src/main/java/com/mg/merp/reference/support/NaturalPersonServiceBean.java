/*
 * NaturalPersonServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.NaturalPersonServiceLocal;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.NaturalPersonHist;

import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Физические лица"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: NaturalPersonServiceBean.java,v 1.7 2007/08/30 08:38:10 alikaev Exp $
 */
@Stateless(name = "merp/reference/NaturalPersonService") //$NON-NLS-1$
public class NaturalPersonServiceBean extends AbstractPOJODataBusinessObjectServiceBean<NaturalPerson, Integer> implements NaturalPersonServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, NaturalPerson entity) {
    context.addRule(new MandatoryAttribute(entity, "ActDate")); //$NON-NLS-1$
    context.addRule(new MandatoryStringAttribute(entity, "Surname")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.NaturalPersonServiceLocal#loadPhoto(int)
   */
  public byte[] loadPhoto(int personId) throws ApplicationException {
    //TODO: implement
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.reference.NaturalPersonServiceLocal#storePhoto(int, byte[])
   */
  public void storePhoto(int personId, byte[] photo) throws ApplicationException {
    //TODO: implement
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onPostCreate(NaturalPerson entity) {
    NaturalPersonHist naturalPersonHist = new NaturalPersonHist();
    naturalPersonHist.setNaturalPerson(entity);
    naturalPersonHist.setSurname(entity.getSurname());
    naturalPersonHist.setName(entity.getName());
    naturalPersonHist.setPatronymic(entity.getPatronymic());
    naturalPersonHist.setActDate(entity.getActDate());
    naturalPersonHist.setInn(entity.getInn());
    getPersistentManager().persist(naturalPersonHist);
  }

}
