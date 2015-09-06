/*
 * SpecificationServiceBean.java
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

package com.mg.merp.finance.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.finance.PeriodServiceLocal;
import com.mg.merp.finance.SpecFeatureModelServiceLocal;
import com.mg.merp.finance.SpecificationModelServiceLocal;
import com.mg.merp.finance.SpecificationServiceLocal;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.Specification;
import com.mg.merp.finance.model.SpecificationModel;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Спецификация финансовых операций"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: SpecificationServiceBean.java,v 1.8 2008/01/25 11:28:16 safonov Exp $
 */
@Stateless(name = "merp/finance/SpecificationService") //$NON-NLS-1$
public class SpecificationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Specification, Integer> implements SpecificationServiceLocal {

  private void checkPeriod(Specification spec) {
    ((PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Period")).checkPeriod(getPersistentManager().find(FinOperation.class, spec.getFinOper().getId()).getKeepDate()); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onInitialize(Specification entity) {
    FinOperation fo = entity.getFinOper();
    if (fo != null)
      entity.setPlanned(fo.getPlanned());
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Specification entity) {
    checkPeriod(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onErase(Specification entity) {
    checkPeriod(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Specification entity) {
    checkPeriod(entity);
  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.SpecificationServiceLocal#recalcSum(int, double)
   */
  public void recalcSum(int finoperId, double curRate) throws ApplicationException {

  }

  /*
   * (non-Javadoc)
   * @see com.mg.merp.finance.SpecificationServiceLocal#createSpecificationByPattern(com.mg.merp.finance.model.OperationModel, com.mg.merp.finance.model.FinOperation)
   */
  @PermitAll
  public void createSpecificationByPattern(OperationModel pattern, FinOperation finOperation) {
    internalCreateSpecificationByPattern(pattern, finOperation);
  }

  /**
   * Создать спецификацию фин. операции по образцу
   *
   * @param pattern      - образец
   * @param finOperation - фин. операция
   */
  protected void internalCreateSpecificationByPattern(OperationModel pattern, FinOperation finOperation) {
    List<SpecificationModel> parentPatternSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SpecificationModel.class)
        .add(Restrictions.isNull("Parent")) //$NON-NLS-1$
        .add(Restrictions.eq("FinOper", pattern))); //$NON-NLS-1$

    CustomFieldsManager customFieldsManager = CustomFieldsManagerLocator.locate();
    SpecificationModelServiceLocal specModelService = (SpecificationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(SpecificationModelServiceLocal.SERVICE_NAME);
    SpecFeatureModelServiceLocal specFeatModelService = (SpecFeatureModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(SpecFeatureModelServiceLocal.SERVICE_NAME);

    for (SpecificationModel parentPattern : parentPatternSpecs) {
      Specification parentSpecification = initializeFinSpecByPattern(parentPattern, finOperation, null);
      customFieldsManager.cloneValues(specModelService, parentPattern, this, parentSpecification);
      storeFinSpec(parentSpecification);

      List<SpecificationModel> childPatternSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(SpecificationModel.class)
          .add(Restrictions.eq("Parent", parentPattern)) //$NON-NLS-1$
          .add(Restrictions.eq("FinOper", pattern))); //$NON-NLS-1$

      for (SpecificationModel childPattern : childPatternSpecs) {
        Specification childSpecification = initializeFinSpecByPattern(childPattern, finOperation, parentSpecification);
        customFieldsManager.cloneValues(specFeatModelService, childPattern, this, childSpecification);
        storeFinSpec(childSpecification);
      }
    }
  }

  private Specification initializeFinSpecByPattern(SpecificationModel patternSpec, FinOperation finOperation, Specification parentSpecification) {
    Specification specItem = initialize();
    AttributeMap attributes = patternSpec.getAllAttributes();
    //удаляем атрибуты образца, которые отсутсвуют в самой спецификации операции
    attributes.remove("Id"); //$NON-NLS-1$
    attributes.remove("Parent"); //$NON-NLS-1$
    attributes.remove("Alg"); //$NON-NLS-1$
    attributes.remove("Formula"); //$NON-NLS-1$
    attributes.remove("FinOper"); //$NON-NLS-1$

    specItem.setAttributes(attributes);
    specItem.setFinOper(finOperation);
    specItem.setParent(parentSpecification);
    return specItem;
  }

  private void storeFinSpec(Specification finSpec) {
    if (finSpec != null)
      ServerUtils.getPersistentManager().persist(finSpec);
  }

}
