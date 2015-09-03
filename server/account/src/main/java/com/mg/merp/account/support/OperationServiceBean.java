/*
 * OperationServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.account.EconomicSpecServiceLocal;
import com.mg.merp.account.OperationModelServiceLocal;
import com.mg.merp.account.OperationServiceLocal;
import com.mg.merp.account.PeriodServiceLocal;
import com.mg.merp.account.model.AccConfig;
import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.EconomicSpec;
import com.mg.merp.account.model.EconomicSpecModel;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.core.model.Folder;

/**
 * Реализация бизнес-компонента "Хозяйственные операции" 
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: OperationServiceBean.java,v 1.12 2008/04/29 10:40:01 safonov Exp $
 */
@Stateless(name="merp/account/OperationService") //$NON-NLS-1$
public class OperationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<EconomicOper, Integer> implements OperationServiceLocal {

	private AccConfig config = ConfigurationHelper.getConfiguration();

	/**
	 * проверка периода на возможность изменения
	 * 
	 * @param operDate	дата ХО
	 */
	private void checkPeriod(EconomicOper oper) {
		((PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/Period")).checkPeriod(oper.getKeepDate()); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(EconomicOper entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(EconomicOper entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(EconomicOper entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, EconomicOper entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Comment")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "KeepDate")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.account.OperationServiceLocal#storno(com.mg.merp.account.model.EconomicOper)
	 */
	public EconomicOper storno(EconomicOper economicOper) {
		return doStorno(economicOper);
	}

	/**
	 * Копирование со сторонирование
	 * 
	 * @param economicOper
	 * 				- хоз.операция
	 * @return
	 * 				- клон хоз. операции
	 */
	protected EconomicOper doStorno(EconomicOper economicOper) {
		AttributeMap attributes = economicOper.getAllAttributes();
		attributes.remove("Id"); //$NON-NLS-1$
		attributes.remove("EconomicSpecs"); //$NON-NLS-1$
		EconomicOper eoClone = clone(economicOper, true, attributes);
		List<EconomicSpec> economicSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(EconomicSpec.class)
				.add(Restrictions.eq("EconomicOper", eoClone))); //$NON-NLS-1$
		for (EconomicSpec es : economicSpecs) {
			es.setBulkOperation(true);
			es.setSummaNat(MathUtils.negate(es.getSummaNat()));
			es.setSummaCur(MathUtils.negate(es.getSummaCur()));
			es.setQuantity(MathUtils.negate(es.getQuantity()));
		}
		eoClone.setSumma(MathUtils.negate(economicOper.getSumma()));
		return eoClone;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.account.OperationServiceLocal#createByPattern(com.mg.merp.account.model.EconomicOperModel, com.mg.merp.core.model.Folder)
	 */
	public EconomicOper createByPattern(EconomicOperModel pattern, Folder folder) {
		return doCreateByPattern(pattern, folder);
	}

	/**
	 * Создание хоз.операции по образцу
	 * @param pattern - образец  
	 * @param folder - папка назначения
	 * @return хоз.операция
	 */
	protected EconomicOper doCreateByPattern(EconomicOperModel pattern, Folder folder) {
		EconomicOper economicOper = initialize();
		AttributeMap attributes = pattern.getAllAttributes();
		//удаляем атрибуты образца, которые отсутсвуют в самой хоз. операции
		attributes.remove("Id"); //$NON-NLS-1$
		attributes.remove("SourceTo"); //$NON-NLS-1$
		attributes.remove("ModelName"); //$NON-NLS-1$
		attributes.remove("SourceFrom"); //$NON-NLS-1$
		attributes.remove("ModelDestFolder"); //$NON-NLS-1$
		attributes.remove("EconomicSpecsModel"); //$NON-NLS-1$

		economicOper.setAttributes(attributes);
		economicOper.setFolder(folder);
		if(economicOper.getKeepDate() == null)
			economicOper.setKeepDate(DateTimeUtils.nowDate());
		//clone custom fields
		OperationModelServiceLocal modelService = (OperationModelServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(OperationModelServiceLocal.SERVICE_NAME); //$NON-NLS-1$
		CustomFieldsManagerLocator.locate().cloneValues(modelService, pattern, this, economicOper);
		
		create(economicOper);
		createSpecs(pattern, economicOper);
		return economicOper;
	}

	/**
	 * Создать спецификации хоз. операции по образцу
	 * @param pattern - образец
	 * @param economicOper - хоз. операция
	 */
	private void createSpecs(EconomicOperModel pattern, EconomicOper economicOper) {
		EconomicSpecServiceLocal specService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$;
		List<EconomicSpecModel> patternSpecs = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(EconomicSpecModel.class)
				.add(Restrictions.eq("EconomicOperModel", pattern))); //$NON-NLS-1$
		if(patternSpecs != null && !patternSpecs.isEmpty()) {
			EconomicSpec[] economicSpecs = new EconomicSpec[patternSpecs.size()];
			int counter = 0;
			for (EconomicSpecModel modelSpec : patternSpecs) 
				economicSpecs[counter++] = initializeEconomicSpecByPattern(modelSpec, economicOper, specService);
			storeEconomicSpecs(economicSpecs, specService);
			ServerUtils.getPersistentManager().flush();
			AccountTurnoverUpdater.execute(economicOper, false);
		}
	}

	private EconomicSpec initializeEconomicSpecByPattern(EconomicSpecModel patternSpec, EconomicOper economicOper, EconomicSpecServiceLocal specService) {
		EconomicSpec economicSpec = specService.initialize();
		AttributeMap attributes = patternSpec.getAllAttributes();
		//удаляем атрибуты образца, которые отсутсвуют в самой спецификации операции
		attributes.remove("Id"); //$NON-NLS-1$
		attributes.remove("EconomicOperModel"); //$NON-NLS-1$
		attributes.remove("QtyAlg"); //$NON-NLS-1$
		attributes.remove("SummaFormula"); //$NON-NLS-1$
		attributes.remove("EntryGoodType"); //$NON-NLS-1$
		attributes.remove("SumAlg"); //$NON-NLS-1$
		attributes.remove("EntryFolder"); //$NON-NLS-1$
		attributes.remove("TaxGroup"); //$NON-NLS-1$
		attributes.remove("QuanFormula"); //$NON-NLS-1$

		economicSpec.setAttributes(attributes);
		economicSpec.setEconomicOper(economicOper);
		economicSpec.setBulkOperation(true);
		return economicSpec;
	}

	private void storeEconomicSpecs(EconomicSpec[] economicSpecs, EconomicSpecServiceLocal specService) {
		if(economicSpecs != null && economicSpecs.length > 0)
			for (EconomicSpec spec : economicSpecs)
				specService.create(spec);				
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(EconomicOper entity, EconomicOper entityClone) {
		final String ECONOMIC_OPER_ATTRIBUTE_NAME = "EconomicOper"; //$NON-NLS-1$
		EconomicSpecServiceLocal economicSpecService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$
		AttributeMap initAttributes = new LocalDataTransferObject();
		initAttributes.put(ECONOMIC_OPER_ATTRIBUTE_NAME, entityClone);
		for (EconomicSpec economicSpec : economicSpecService.findByCriteria(Restrictions.eq(ECONOMIC_OPER_ATTRIBUTE_NAME, entity)))
			economicSpecService.clone(economicSpec, true, initAttributes);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.account.OperationServiceLocal#addFromRemnDbKt(com.mg.merp.account.model.RemnDbKt, com.mg.merp.core.model.Folder)
	 */
	public EconomicOper addFromRemnDbKt(RemnDbKt remnDbKt, Folder folder) {
		return doAddFromRemnDbKt(remnDbKt, folder);
	}

	/**
	 * Создание хоз. операции по оборотке "Ведомость расчета с контрагентом"
	 * 
	 * @param remnDbKt
	 * 				- обротка "Ведомость расчета с контрагентом"
	 * @param folder
	 * 				- папка-приемник для создаваемой хоз. операции
	 */
	protected EconomicOper doAddFromRemnDbKt(RemnDbKt remnDbKt, Folder folder) {
		if (remnDbKt != null) {
			remnDbKt = ServerUtils.getPersistentManager().find(RemnDbKt.class, remnDbKt.getId());
			final EconomicOper economicOper = initialize();
			if (folder == null)
				throw new BusinessException(Messages.getInstance().getMessage(Messages.NOT_CHOOSE_FOLDER));
			economicOper.setFolder(folder);
			//устанавливаем дату хоз. операции
			Date keepDate = remnDbKt.getPeriod().getDateTo();
			if (remnDbKt.getPeriod().getDateTo().compareTo(DateTimeUtils.nowDate()) > 0)
				keepDate = DateTimeUtils.nowDate();
			economicOper.setKeepDate(keepDate);
			economicOper.setComment(Messages.getInstance().getMessage(Messages.CLOSE_DEBIT_COMMENT));
			economicOper.setBaseDocType(remnDbKt.getDocBaseType());
			economicOper.setBaseDocNumber(remnDbKt.getDocBaseNumber());
			economicOper.setBaseDocDate(remnDbKt.getDocBaseDate());
			economicOper.setContractType(remnDbKt.getDocType());
			economicOper.setContractNumber(remnDbKt.getDocNumber());
			economicOper.setContractDate(remnDbKt.getDocDate());
			economicOper.setFrom(remnDbKt.getContractor());
			economicOper.setTo(remnDbKt.getContractor());
			create(economicOper);
			//создаем проводку
			EconomicSpecServiceLocal economicSpecService = (EconomicSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/account/EconomicSpec"); //$NON-NLS-1$
			EconomicSpec economicSpec = economicSpecService.initialize();
			economicSpec.setEconomicOper(economicOper);
			economicSpec.setAccDb(remnDbKt.getAccPlan());
			economicSpec.setAccKt(remnDbKt.getAccPlan());
			economicSpec.setAnlDb1(remnDbKt.getAnlPlan1());
			economicSpec.setAnlDb2(remnDbKt.getAnlPlan2());
			economicSpec.setAnlDb3(remnDbKt.getAnlPlan3());
			economicSpec.setAnlDb4(remnDbKt.getAnlPlan4());
			economicSpec.setAnlDb5(remnDbKt.getAnlPlan5());
			economicSpec.setAnlKt1(remnDbKt.getAnlPlan1());
			economicSpec.setAnlKt2(remnDbKt.getAnlPlan2());
			economicSpec.setAnlKt3(remnDbKt.getAnlPlan3());
			economicSpec.setAnlKt4(remnDbKt.getAnlPlan4());
			economicSpec.setAnlKt5(remnDbKt.getAnlPlan5());
			BigDecimal summaNat = remnDbKt.getRemnEndNatDb();
			if (MathUtils.compareToZero(summaNat) == 0)
				summaNat = remnDbKt.getRemnEndNatKt();
			economicSpec.setSummaNat(summaNat);
			BigDecimal summaCur = remnDbKt.getRemnEndCurDb();
			if (MathUtils.compareToZero(summaCur) == 0)
				summaCur = remnDbKt.getRemnEndCurKt();
			economicSpec.setSummaCur(summaCur);
			if (summaCur != null && summaNat != null && MathUtils.compareToZero(summaCur) != 0)
				economicSpec.setCurCource(summaCur.divide(summaNat, config.getCurrencyPrec()));
			else 
				economicSpec.setCurCource(BigDecimal.ZERO);
			economicSpecService.create(economicSpec);
			return economicOper;
		} else
			return null;		
	}

}
