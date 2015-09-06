/*
 * NaturalPersonHistServiceBean.java
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

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.NaturalPersonHistServiceLocal;
import com.mg.merp.reference.model.NaturalPerson;
import com.mg.merp.reference.model.NaturalPersonHist;

import java.util.List;

import javax.ejb.Stateless;

/**
 * Бизнес-компонент "История изменений сведений физического лица"
 *
 * @author leonova
 * @version $Id: NaturalPersonHistServiceBean.java,v 1.5 2007/08/30 08:36:41 alikaev Exp $
 */
@Stateless(name = "merp/reference/NaturalPersonHistService")
public class NaturalPersonHistServiceBean extends AbstractPOJODataBusinessObjectServiceBean<NaturalPersonHist, Integer> implements NaturalPersonHistServiceLocal {

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onValidate(ValidationContext context, NaturalPersonHist entity) {
    context.addRule(new MandatoryStringAttribute(entity, "Surname"));
    context.addRule(new MandatoryAttribute(entity, "ActDate"));
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(NaturalPersonHist entity) {
    List<NaturalPersonHist> listNaturalPersonHist = getNaturalPersonHist(entity.getNaturalPerson().getId());
    if (listNaturalPersonHist.size() > 0) {
      NaturalPersonHist naturalPersonHistActual = listNaturalPersonHist.get(0);
      if (entity.getActDate().compareTo(naturalPersonHistActual.getActDate()) == 1)
        adjustNaturalPerson(entity);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(NaturalPersonHist entity) {
    List<NaturalPersonHist> listNaturalPersonHist = getNaturalPersonHist(entity.getNaturalPerson().getId());
    if (listNaturalPersonHist.size() > 0) {
      //если изменяемая запись одна или текущее изменение стало актуальным, то передаем текущее изменение
      NaturalPersonHist naturalPersonHistActual = listNaturalPersonHist.get(0);
      if (listNaturalPersonHist.size() == 1 || entity.getActDate().compareTo(naturalPersonHistActual.getActDate()) == 1)
        adjustNaturalPerson(entity);
      if (listNaturalPersonHist.size() > 1) {
        // следущее актуальное значение из истории изменений
        NaturalPersonHist naturalPersonHistActualNext = getNaturalPersonHist(entity.getNaturalPerson().getId()).get(1);
        // если изменяемое значение не стало актуальным передаем второе по актуальности значение
        if (naturalPersonHistActual.getId().compareTo(entity.getId()) == 0
            && entity.getActDate().compareTo(naturalPersonHistActualNext.getActDate()) == -1)
          adjustNaturalPerson(naturalPersonHistActualNext);
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(NaturalPersonHist entity) {
    List<NaturalPersonHist> listNaturalPersonHist = getNaturalPersonHist(entity.getNaturalPerson().getId());
    if (listNaturalPersonHist.size() > 1)
      if (entity.getId().compareTo(listNaturalPersonHist.get(0).getId()) == 0)
        adjustNaturalPerson(listNaturalPersonHist.get(1));
    if (listNaturalPersonHist.size() == 1)
      throw new BusinessException(Messages.getInstance().getMessage(Messages.CANNOT_DELETE_NATURAL_PERSON_HIST));
  }

  /**
   * устанавливаем физическому лицу значения из истории изменений, если в ней содержиться актульные
   * по дате значения
   */
  private void adjustNaturalPerson(NaturalPersonHist naturalPersonHist) {
    NaturalPerson naturalPerson = getPersistentManager().find(NaturalPerson.class, naturalPersonHist.getNaturalPerson().getId());
    naturalPerson.setSurname(naturalPersonHist.getSurname());
    naturalPerson.setName(naturalPersonHist.getName());
    naturalPerson.setPatronymic(naturalPersonHist.getPatronymic());
    naturalPerson.setActDate(naturalPersonHist.getActDate());
    naturalPerson.setInn(naturalPersonHist.getInn());
    naturalPersonHist.setNaturalPerson(naturalPerson);
    getPersistentManager().merge(naturalPerson);
  }

  /**
   * по идентификатору физического лица возвращает список его историй изменений
   *
   * @param Id идентификатор физического лица
   * @return список историй изменений
   */
  private List<NaturalPersonHist> getNaturalPersonHist(int Id) {
    List<NaturalPersonHist> naturalPersonHist = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(NaturalPersonHist.class)
            .createAlias("NaturalPerson", "np")
            .add(Restrictions.eq("np.Id", Id))
            .addOrder(Order.desc("ActDate"))
    );
    return naturalPersonHist;
  }

}
