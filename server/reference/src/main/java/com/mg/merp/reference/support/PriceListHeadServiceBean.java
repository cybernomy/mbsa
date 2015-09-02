/*
 * PriceListHeadServiceBean.java
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

package com.mg.merp.reference.support;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BusinessException;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.FolderRights;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.PriceListFolderServiceLocal;
import com.mg.merp.reference.PriceListHeadServiceLocal;
import com.mg.merp.reference.PriceListSpecServiceLocal;
import com.mg.merp.reference.PricelistPricetypeLinkServiceLocal;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListHead;
import com.mg.merp.reference.model.PriceListPriceTypeLink;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.PriceListSpecPrice;
import com.mg.merp.reference.model.PriceListSpecPriceId;

/**
 * Реализация бизнес-компонента "Заголовки прайс-листов"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: PriceListHeadServiceBean.java,v 1.10 2009/03/04 07:42:04 sharapov Exp $
 */
@Stateless(name="merp/reference/PriceListHeadService") //$NON-NLS-1$
public class PriceListHeadServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PriceListHead, Integer> implements PriceListHeadServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PriceListHead entity) {
		context.addRule(new MandatoryStringAttribute(entity, "PrName")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Currency")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateType")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CurrencyRateAuthority")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "CreateDate")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#recalcPrices(java.io.Serializable[])
	 */
	public void recalcPrices(Serializable[] priceListHeadIds) {
		doRecalcPrices(priceListHeadIds);
	}

	/**
	 * Пересчитать цены спецификации прайс-листа
	 * @param priceListHeadIds - список идентификаторов прайс-листов
	 */
	protected void doRecalcPrices(Serializable[] priceListHeadIds) {
		if(priceListHeadIds == null || priceListHeadIds.length < 1)
			return;

		List<PriceListSpec> priceListSpecList = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PriceListSpec.class)
				.add(Restrictions.in("PriceListHeadId", (Object[]) priceListHeadIds)) //$NON-NLS-1$
				.setFlushMode(FlushMode.MANUAL));

		PriceListSpecServiceLocal priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListSpecServiceLocal.SERVICE_NAME);
		for (PriceListSpec priceListSpec : priceListSpecList)
			priceListSpecService.calcPrices(priceListSpec);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(PriceListHead entity, PriceListHead entityClone) {
		final String PRICE_LIST_HEAD_ATTRIBUTE_NAME = "PriceListHead"; //$NON-NLS-1$
		final String PRICE_LIST_HEAD_ID_ATTRIBUTE_NAME = "PriceListHeadId"; //$NON-NLS-1$
		final String PRICE_LIST_FOLDER_ATTRIBUTE_NAME = "Folder"; //$NON-NLS-1$
		final String PRICE_LIST_FOLDER_ID_ATTRIBUTE_NAME = "FolderId"; //$NON-NLS-1$
		final String PRICE_LIST_SPEC_UNID_ATTRIBUTE_NAME = "Unid"; //$NON-NLS-1$

		PricelistPricetypeLinkServiceLocal pricelistPricetypeLinkService = (PricelistPricetypeLinkServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PricelistPricetypeLink"); //$NON-NLS-1$
		PriceListFolderServiceLocal priceListFolderService = (PriceListFolderServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/reference/PriceListFolder"); //$NON-NLS-1$
		PriceListSpecServiceLocal priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListSpecServiceLocal.SERVICE_NAME);

		List<PriceListFolder> clonedPriceListFolders = new ArrayList<PriceListFolder>();
		List<PriceListFolder> priceListFolders = priceListFolderService.findByCriteria(Restrictions.eq(PRICE_LIST_HEAD_ATTRIBUTE_NAME, entity));

		AttributeMap folderRightsInitAttributes = new LocalDataTransferObject();		
		AttributeMap priceListSpecInitAttr = new LocalDataTransferObject();
		AttributeMap initAttributes = new LocalDataTransferObject();
		initAttributes.put(PRICE_LIST_HEAD_ATTRIBUTE_NAME, entityClone);

		PersistentManager persistentManager = ServerUtils.getPersistentManager();
		// копирование связей с типами цен
		for(PriceListPriceTypeLink priceListPriceTypeLink : pricelistPricetypeLinkService.findByCriteria(Restrictions.eq(PRICE_LIST_HEAD_ATTRIBUTE_NAME, entity)))
			pricelistPricetypeLinkService.clone(priceListPriceTypeLink, true, initAttributes);

		// копирование иерархической структуры
		for(PriceListFolder priceListFolder : priceListFolders) {
			PriceListFolder clonedPriceListFolder = (PriceListFolder) priceListFolder.cloneEntity(initAttributes);
			clonedPriceListFolder.setAttribute("Id", DatabaseUtils.getSequenceNextValue("pricelistfolder_id_gen")); //$NON-NLS-1$ //$NON-NLS-2$
			clonedPriceListFolders.add(clonedPriceListFolder);

			// копирование прав иерархической структуры
			folderRightsInitAttributes.put(PRICE_LIST_FOLDER_ID_ATTRIBUTE_NAME, clonedPriceListFolder.getId());
			for (FolderRights folderRights : getPriceListFolderRights(priceListFolder.getId()))
				persistentManager.persist(folderRights.cloneEntity(folderRightsInitAttributes));
		}
		for(int i = 0; i < clonedPriceListFolders.size(); i++) {
			PriceListFolder clonedPriceListFolder = clonedPriceListFolders.get(i);
			PriceListFolder priceListFolder = priceListFolders.get(i);
			// настройка иерархической структуры
			if(clonedPriceListFolder.getParent() != null)
				clonedPriceListFolder.setParent(clonedPriceListFolders.get(priceListFolders.indexOf(priceListFolder.getParent())));

			// создание прав иерархической структуры
			JdbcTemplate.getInstance().update("INSERT INTO PRICELISTFOLDER (ID, PRICELISTHEAD_ID, PARENT_ID, FNAME, FOLDER_TAG) VALUES (?, ?, ?, ?, ?)", //$NON-NLS-1$
					new Object[] {clonedPriceListFolder.getId(), clonedPriceListFolder.getPriceListHead().getId(), clonedPriceListFolder.getParent() == null ? clonedPriceListFolder.getParent() : clonedPriceListFolder.getParent().getId(), clonedPriceListFolder.getFName(), clonedPriceListFolder.getFolderTag()});

			// копирование спецификации
			priceListSpecInitAttr.put(PRICE_LIST_FOLDER_ATTRIBUTE_NAME, clonedPriceListFolder);
			priceListSpecInitAttr.put(PRICE_LIST_HEAD_ID_ATTRIBUTE_NAME, entityClone.getId());
			for(PriceListSpec priceListSpec : priceListSpecService.findByCriteria(Restrictions.eq(PRICE_LIST_FOLDER_ATTRIBUTE_NAME, priceListFolder))) {
				priceListSpecInitAttr.put(PRICE_LIST_SPEC_UNID_ATTRIBUTE_NAME, DataUtils.generateUUID());
				PriceListSpec clonedPriceListSpec = (PriceListSpec) priceListSpec.cloneEntity(priceListSpecInitAttr);
				doCloneSpecPriceList(priceListSpec, clonedPriceListSpec);
				persistentManager.persist(clonedPriceListSpec);
			}
		}
	}

	/**
	 * Выполнить копирование типов цен
	 * @param priceListSpec - оригинал позиции спецификации прайс-листа
	 * @param priceListSpecClone - копия позиции спецификации прайс-листа
	 */
	protected void doCloneSpecPriceList(PriceListSpec priceListSpec, PriceListSpec priceListSpecClone) {
		List<PriceListSpecPrice> prices = new ArrayList<PriceListSpecPrice>();
		for (PriceListSpecPrice price : priceListSpec.getPriceListSpecPrice()) {
			PriceListSpecPrice priceClone = new PriceListSpecPrice();
			PriceListSpecPriceId priceId = new PriceListSpecPriceId();
			priceId.setPriceListSpec(priceListSpecClone);
			priceId.setPriceType(price.getId().getPriceType());
			priceId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
			priceClone.setId(priceId);
			priceClone.setPrice(price.getPrice());
			prices.add(priceClone);
		}
		priceListSpecClone.setPriceListSpecPrice(prices);
	}

	/**
	 * Получить список прав на папку прайс-листа
	 * @param priceListfolderId - идентификатор папки прайс-листа
	 * @return список прав на папку прайс-листа
	 */
	private List<FolderRights> getPriceListFolderRights(Integer priceListfolderId) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FolderRights.class)
				.add(Restrictions.eq("FolderId", priceListfolderId)) //$NON-NLS-1$
				.add(Restrictions.eq("FolderPart", PriceListFolderServiceLocal.FOLDER_PART))); //$NON-NLS-1$
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#overestimation(java.io.Serializable, java.util.Date, java.math.BigDecimal, java.lang.Integer)
	 */
	public void overestimation(Serializable priceListHeadId, Date actualDate, BigDecimal percent, Integer precision) {
		doOverestimation(priceListHeadId, actualDate, percent, precision);
	}
	
	/**
	 * Выполнить переоценку
	 * @param priceListHeadId - идентификатор прайс-листа
	 * @param actualDate - дата актуальности
	 * @param percent - процент
	 * @param precision - точность вычислений
	 */
	protected void doOverestimation(Serializable priceListHeadId, Date actualDate, BigDecimal percent, Integer precision) {
		validateOverestimationParams(priceListHeadId, actualDate, percent, precision);
		List<PriceListSpec> priceListSpecList = findPriceListSpecs(priceListHeadId, actualDate);
		RoundContext rc = new RoundContext(precision);
		PriceListSpecServiceLocal priceListSpecService = (PriceListSpecServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(PriceListSpecServiceLocal.SERVICE_NAME);
		PersistentManager persistentManager = getPersistentManager();
		for (PriceListSpec priceListSpec : priceListSpecList) {
			BigDecimal specPrice = priceListSpec.getPrice() != null ? priceListSpec.getPrice() : BigDecimal.ZERO;
			BigDecimal price = MathUtils.addNullable(specPrice, MathUtils.divide(specPrice.multiply(percent), MathUtils.HUNDRED, rc), rc);
			if (priceListSpec.getActDate().compareTo(actualDate) == 0) {
				priceListSpec.setPrice(price);
				priceListSpecService.store(priceListSpec);
			} else {
				AttributeMap priceListSpecAttributes = priceListSpec.getAllAttributes();
				priceListSpecAttributes.remove("Id"); //$NON-NLS-1$
				priceListSpecAttributes.remove("Unid"); //$NON-NLS-1$
				priceListSpecAttributes.remove("PriceListSpecPrice"); //$NON-NLS-1$
				PriceListSpec newPriceListSpec = priceListSpecService.initialize(priceListSpecAttributes);
				newPriceListSpec.setActDate(actualDate);
				newPriceListSpec.setPrice(price);
				priceListSpecService.create(newPriceListSpec);
				persistentManager.flush();
				persistentManager.refresh(newPriceListSpec);
			}
		}
		doRecalcPrices(new Serializable[] {priceListHeadId});
	}
	
	/**
	 * Найти позиции спецификации прайс-листа на дату
	 * @param priceListHeadId - иденификатор прайс-листа
	 * @param actualDate - дата актуальности
	 * @return список позиций спецификации прайс-листа
	 */
	private List<PriceListSpec> findPriceListSpecs(Serializable priceListHeadId, Date actualDate) {
		return MiscUtils.convertUncheckedList(PriceListSpec.class, OrmTemplate.getInstance().findByNamedParam("select pls from com.mg.merp.reference.model.PriceListSpec pls where pls.PriceListHeadId = :priceListHeadId and (:actualDate between pls.ActDate and pls.ActDateTill)", new String[] {"priceListHeadId", "actualDate"}, new Object[] {priceListHeadId, actualDate})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	/**
	 * Выполнить контроль корректности параметров переоценки
	 * @param priceListHeadId - идентификатор прайс-листа
	 * @param actualDate - дата актуальности
	 * @param percent - процент
	 * @param precision - точность вычислений
	 */
	private void validateOverestimationParams(Serializable priceListHeadId, Date actualDate, BigDecimal percent, Integer precision) {
		if (priceListHeadId == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.OVERESTIMATION_PARAM_PRICE_LIST_HEAD_INVALID));
		if (actualDate == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.OVERESTIMATION_PARAM_ACTUAL_DATE_INVALID));
		if (percent == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.OVERESTIMATION_PARAM_PERCENT_INVALID));
		if (precision == null)
			throw new BusinessException(Messages.getInstance().getMessage(Messages.OVERESTIMATION_PARAM_PRECISION_INVALID));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#loadPriceTypeBrowse(int)
	 */
	public byte[] loadPriceTypeBrowse(int aPriceListId) throws ApplicationException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#addPriceType(int, int, short, java.lang.String)
	 */
	public void addPriceType(int aPriceListId, int aPriceTypeId, short aPriority, String aFormula) throws ApplicationException {
		//TODO: implement
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#deletePriceType(int, int)
	 */
	public void deletePriceType(int aPriceListId, int aPriceTypeId) throws ApplicationException {
		//TODO: implement
	}	

	/* (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListHeadServiceLocal#updatePriceType(int, int, short, java.lang.String)
	 */
	public void updatePriceType(int aPriceListId, int aPriceTypeId, short priority,	String aFormula) throws ApplicationException {
		//TODO: implement
	}

}
