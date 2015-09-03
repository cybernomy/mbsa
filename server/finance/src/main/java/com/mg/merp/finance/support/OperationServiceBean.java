/*
 * OperationServiceBean.java
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.Folder;
import com.mg.merp.finance.OperationServiceLocal;
import com.mg.merp.finance.PeriodServiceLocal;
import com.mg.merp.finance.SpecificationServiceLocal;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;
import com.mg.merp.finance.model.Specification;

/**
 * Реализация бизнес-компонента "Финансовые операции" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: OperationServiceBean.java,v 1.10 2008/02/19 06:09:04 alikaev Exp $
 */
@Stateless(name="merp/finance/OperationService") //$NON-NLS-1$
public class OperationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FinOperation, Integer> implements OperationServiceLocal  {

	private void checkPeriod(FinOperation oper) {
		((PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Period")).checkPeriod(oper.getKeepDate()); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(FinOperation entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(FinOperation entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(FinOperation entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, FinOperation entity) {
		context.addRule(new MandatoryAttribute(entity, "KeepDate")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Comment")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Currency")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.finance.OperationServiceLocal#createByPattern(com.mg.merp.finance.model.FinOperation, com.mg.merp.core.model.Folder)
	 */
	public FinOperation createByPattern(OperationModel pattern, Folder folder) {
		return doCreateByPattern(pattern, folder);
	}

	/**
	 * Создание финансовой операции по образцу
	 * @param pattern - образец
	 * @param folder - папка назначения
	 * @return финансовая операция
	 */
	protected FinOperation doCreateByPattern(OperationModel pattern, Folder folder) {
		FinOperation finOperation = initialize();
		AttributeMap attributes = pattern.getAllAttributes();
		//удаляем атрибуты образца, отсуствующие в самой фин. операции
		attributes.remove("Id"); //$NON-NLS-1$
		attributes.remove("SourceTo"); //$NON-NLS-1$
		attributes.remove("ModelName"); //$NON-NLS-1$
		attributes.remove("SourceFrom"); //$NON-NLS-1$
		attributes.remove("SetOfSpecModel"); //$NON-NLS-1$
		attributes.remove("ModelDestFolder"); //$NON-NLS-1$
		attributes.remove("Specifications"); //$NON-NLS-1$
		
		finOperation.setAttributes(attributes);
		// если папка-приемник не указана в образце, то создаем в текущей папке
		if(pattern.getModelDestFolder() != null)
			finOperation.setFolder(pattern.getModelDestFolder());
		else
			finOperation.setFolder(folder);

		if(finOperation.getKeepDate() == null)
			finOperation.setKeepDate(Calendar.getInstance().getTime());
		ServerUtils.getPersistentManager().persist(finOperation);
		createSpecs(pattern, finOperation);
		return finOperation;
	}

	private void createSpecs(OperationModel pattern, FinOperation finOperation) {
		 getSpecificationService().createSpecificationByPattern(pattern, finOperation);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(FinOperation entity, FinOperation entityClone) {
		final String FIN_OPER_ATTRIBUTE_NAME = "FinOper"; //$NON-NLS-1$
		SpecificationServiceLocal specificationService = getSpecificationService();
		List<Specification> specifications = specificationService.findByCriteria(Restrictions.eq(FIN_OPER_ATTRIBUTE_NAME, entity));
		List<Specification> clonedSpecifications = new ArrayList<Specification>();
				
		AttributeMap initAtrributes = new LocalDataTransferObject();
		initAtrributes.put(FIN_OPER_ATTRIBUTE_NAME, entityClone); //$NON-NLS-1$
		
		for (Specification specification : specifications)
			clonedSpecifications.add(specificationService.clone(specification, true, initAtrributes));
		
		for (int i = 0; i < clonedSpecifications.size(); i++) {
			Specification clonedSpecification = clonedSpecifications.get(i);
			if(clonedSpecification.getParent() != null)
				clonedSpecification.setParent(clonedSpecifications.get(specifications.indexOf(specifications.get(i).getParent())));
		}
	}
	
	private SpecificationServiceLocal getSpecificationService() {
		return (SpecificationServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Specification"); //$NON-NLS-1$
	}

}
