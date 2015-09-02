/*
 * PriceListSpecServiceBean.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.service.DataBusinessServiceInterceptorManagerLocator;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.reference.PriceListSpecServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.PriceListFolder;
import com.mg.merp.reference.model.PriceListPriceTypeLink;
import com.mg.merp.reference.model.PriceListSpec;
import com.mg.merp.reference.model.PriceListSpecPrice;
import com.mg.merp.reference.model.PriceListSpecPriceId;

/**
 * Реализация бизнес-компонента "Спецификация прайс-листов"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListSpecServiceBean.java,v 1.16 2008/06/07 12:45:42 sharapov Exp $
 */
@Stateless(name="merp/reference/PriceListSpecService") //$NON-NLS-1$
public class PriceListSpecServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PriceListSpec, Integer> implements PriceListSpecServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(PriceListSpec entity) {
		entity.setUnid(DataUtils.generateUUID());
		entity.setActDate(DateTimeUtils.nowDate());
		entity.setActDateTill(DateTimeUtils.MAX_DATE);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PriceListSpec entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Unid")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Catalog")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "SName")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "ActDate")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(PriceListSpec entity) {
		recalcActRanges(entity, true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(PriceListSpec entity) {
		createSpecPrices(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(PriceListSpec entity) {
		recalcActRanges(entity, true);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(PriceListSpec entity) {
		recalcActRanges(entity, false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doClone(com.mg.framework.api.orm.PersistentObject, boolean, com.mg.framework.api.AttributeMap)
	 */
	@Override
	protected PriceListSpec doClone(PriceListSpec entity, boolean deepClone, AttributeMap attributes) {
		if (entity == null)
			return null;

		PriceListSpec entityClone = (PriceListSpec) entity.cloneEntity(attributes);
		if (entityClone != null) {
			//копируем пользовательские поля
			if (entity instanceof EntityCustomFieldsStorageAccessor)
				CustomFieldsManagerLocator.locate().cloneValues(this, entity, this, entityClone);

			if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnClone(this, entityClone))
				onClone(entityClone);

			createEntityClone(entityClone);
			if (deepClone) {
				doDeepClone(entity, entityClone);
				createSpecPrices(entityClone);
			}
		}
		return entityClone;	
	}

	/**
	 * Создать копию спецификации прайс-листа
	 * @param entityClone - копия спецификации прайс-листа
	 */
	private void createEntityClone(PriceListSpec entityClone) {
		entityClone.setUnid(DataUtils.generateUUID());
		entityClone.setActDate(DateTimeUtils.nowDate());
		ServerUtils.getPersistentManager().persist(entityClone);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doDeepClone(com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void doDeepClone(PriceListSpec entity, PriceListSpec entityClone) {
		List<PriceListSpecPrice> prices = new ArrayList<PriceListSpecPrice>();
		for (PriceListSpecPrice price : entity.getPriceListSpecPrice()) {
			PriceListSpecPrice priceClone = new PriceListSpecPrice();
			PriceListSpecPriceId priceId = new PriceListSpecPriceId();
			priceId.setPriceListSpec(entityClone);
			priceId.setPriceType(price.getId().getPriceType());
			priceId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
			priceClone.setId(priceId);
			priceClone.setPrice(price.getPrice());
			prices.add(priceClone);
		}
		entityClone.setPriceListSpecPrice(prices);
	}

	/**
	 * Создать цены позиции спецификации прайс-листа
	 * @param entity - позиция спецификации
	 */
	private void createSpecPrices(PriceListSpec entity) {
		storeSpecPrices(getSpecPrices(entity));
	}

	/**
	 * Получить список цен позиции спецификации прайс-листа
	 * @param entity - позиция спецификации
	 * @return список цен позиции спецификации
	 */
	private List<PriceListSpecPrice> getSpecPrices(PriceListSpec entity) {
		List<PriceListSpecPrice> specPrices = new ArrayList<PriceListSpecPrice>();

		Criteria criteria = OrmTemplate.createCriteria(PriceListPriceTypeLink.class)
		.add(Restrictions.eq("PriceListHead.Id", entity.getPriceListHeadId())); //$NON-NLS-1$
		List<PriceListPriceTypeLink> priceListPriceTypeLink = OrmTemplate.getInstance().findByCriteria(criteria);

		for(PriceListPriceTypeLink link : priceListPriceTypeLink) {
			PriceListSpecPrice specPrice = new PriceListSpecPrice();
			PriceListSpecPriceId specPriceId = new PriceListSpecPriceId();

			specPriceId.setPriceType(link.getPriceType());
			specPriceId.setPriceListSpec(entity);
			specPriceId.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());

			specPrice.setId(specPriceId);
			specPrice.setPrice(BigDecimal.ZERO);

			specPrices.add(specPrice);
		}
		return specPrices;
	}

	private void storeSpecPrices(List<PriceListSpecPrice> specPrices) {
		for(PriceListSpecPrice specPrice : specPrices) 
			getPersistentManager().persist(specPrice);
	}

	/**
	 * Пересчитать диапазон действия(дату "действует до") позиций спецификации прайс-листа
	 * @param priceListSpec - позиция спецификации
	 * @param isEdit - признак изменения сущности
	 */
	private void recalcActRanges(PriceListSpec priceListSpec, boolean isEdit) {
		List<PriceListSpec> processedPriceListSpecs = new ArrayList<PriceListSpec>();
		List<PriceListSpec> priceListSpecs = getFamilyPriceListSpecs(priceListSpec);

		if(isEdit) 
			insertItemInListSortedByActDate(priceListSpec, priceListSpecs);

		for(int i = 0; i < priceListSpecs.size(); i++) {
			PriceListSpec spec = priceListSpecs.get(i);
			Date actDateTill = findActDateTill(priceListSpecs, i);
			if(spec.getActDateTill().compareTo(actDateTill) != 0) {
				spec.setActDateTill(actDateTill);
				processedPriceListSpecs.add(spec);
			}
		}
		mergePriceListSpec(processedPriceListSpecs);
	}

	/**
	 * Вставить позицию спецификации в отсортированный список
	 * @param priceListSpec - позиция спецификации
	 * @param priceListSpecs - список позиций спецификации (отсортированный по "дате действия с")
	 */
	private void insertItemInListSortedByActDate(PriceListSpec priceListSpec, List<PriceListSpec> priceListSpecs) {
		for(int i = 0; i < priceListSpecs.size(); i++) {
			if(i != priceListSpecs.size() - 1) {
				if(priceListSpec.getActDate().compareTo(priceListSpecs.get(i).getActDate()) >= 0 && priceListSpec.getActDate().compareTo(priceListSpecs.get(i + 1).getActDate()) < 0) {
					priceListSpecs.add(i + 1, priceListSpec);
					break;
				}
			} else {
				priceListSpecs.add(priceListSpec);
				break;
			}
		}
	}

	/**
	 * Получить дату "действия до" спецификации прайс-листа
	 * @param priceListSpecs - список позиций спецификации
	 * @param currentIndex - текущая позиция в списке
	 * @return дата "действия до"
	 */
	private Date findActDateTill(List<PriceListSpec> priceListSpecs, int currentIndex) {
		if(currentIndex == priceListSpecs.size() - 1)
			return DateTimeUtils.MAX_DATE;
		else {
			if(priceListSpecs.get(currentIndex + 1).getActDate().compareTo(priceListSpecs.get(currentIndex).getActDate()) == 0)
				return findActDateTill(priceListSpecs, currentIndex + 1);
			else
				return decDay(priceListSpecs.get(currentIndex + 1).getActDate(), 1);
		}
	}

	/**
	 * Получить аналогичные позиции спецификации (т.е те у которых совпадают заголовок Прайс-листа и позиция Каталога)
	 * @param entity - позиция спецификации
	 * @return список позиций спецификации
	 */
	private List<PriceListSpec> getFamilyPriceListSpecs(PriceListSpec entity) {
		Criteria criteria = OrmTemplate.createCriteria(PriceListSpec.class)
		.add(Restrictions.eq("Catalog", entity.getCatalog())) //$NON-NLS-1$
		.add(Restrictions.eq("PriceListHeadId", entity.getPriceListHeadId())) //$NON-NLS-1$
		.addOrder(Order.asc("ActDate")); //$NON-NLS-1$

		if(entity.getId() != null)
			criteria.add(Restrictions.ne("Id", entity.getId())); //$NON-NLS-1$

		return OrmTemplate.getInstance().findByCriteria(criteria);
	}

	/**
	 * Декрементировать дату на количество дней
	 * @param date - дата
	 * @param amount - количество дней
	 * @return дата
	 */
	private java.util.Date decDay(java.util.Date date, int amount) {
		Calendar calendar = Calendar.getInstance(ServerUtils.getUserLocale());
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -amount);
		return calendar.getTime();
	}

	private void mergePriceListSpec(List<PriceListSpec> priceListSpecs) {
		for(PriceListSpec spec : priceListSpecs) 
			ServerUtils.getPersistentManager().merge(spec);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListSpecServiceLocal#calcPrices(com.mg.merp.reference.model.PriceListSpec)
	 */
	public void calcPrices(PriceListSpec priceListSpec) {
		priceListSpec = load(priceListSpec.getId());
		List<PriceListPriceTypeLink> links = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(PriceListPriceTypeLink.class).createAlias("PriceListHead","psh").add(Restrictions.eq("psh.Id", priceListSpec.getPriceListHeadId()))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		final List<PriceListSpecPrice> specPrices = priceListSpec.getPriceListSpecPrice();
		for(final PriceListPriceTypeLink link : links) {
			if(link.getAlgRepository() != null) {
				Map<String, PriceListSpec> params = new HashMap<String, PriceListSpec>();
				params.put(PriceListBusinessAddin.PRICE_LIST_SPEC, priceListSpec);
				BusinessAddinEngineLocator.locate().perform(link.getAlgRepository(), params, new BusinessAddinListener<BigDecimal>() {
					public void aborted(BusinessAddinEvent<BigDecimal> event) {
						// Do nothing
					}

					public void completed(BusinessAddinEvent<BigDecimal> event) {
						for(PriceListSpecPrice specPrise : specPrices) {
							if(link.getPriceType() == specPrise.getId().getPriceType()) {
								specPrise.setPrice(event.getResult());
								getPersistentManager().merge(specPrise);
							}
						}
					}
				});
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListSpecServiceLocal#addFromCatalog(com.mg.merp.reference.model.PriceListHead, com.mg.merp.reference.model.PriceListFolder, java.util.List)
	 */
	public void addFromCatalog(Integer priceListHeadId,	PriceListFolder priceListFolder, List<Catalog> catalogs) {
		doAddFromCatalog(priceListHeadId, priceListFolder, catalogs);
	}

	/**
	 * Добавление спецификаций прайс-листа по кааталожным позициям
	 * 
	 * @param priceListHeadId
	 * 				- идентификатор заголовока прайс-листа
	 * @param priceListFolder
	 * 				- папка прайс-листа
	 * @param catalogs
	 * 				- список каталожных позиций
	 */
	protected void doAddFromCatalog(Integer priceListHeadId, PriceListFolder priceListFolder, List<Catalog> catalogs) {
		for (Catalog catalog : catalogs) {
			PriceListSpec spec = initialize();
			spec.setFolder(priceListFolder);
			spec.setCatalog(catalog);
			spec.setSName(catalog.getFullName());
			spec.setPriceListHeadId(priceListHeadId);
			spec.setActDate(DateTimeUtils.nowDate());
			create(spec);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.reference.PriceListSpecServiceLocal#findByBarCode(java.lang.String, int, int, java.util.Date)
	 */
	@PermitAll
	public PriceListSpecPrice findByBarCode(String barCode, int priceListId, int priceTypeId, java.util.Date aDate) {
		return null;//((PriceListSpecDomainImpl) getDomain()).findByBarCode(barCode, priceListId, priceTypeId, aDate);
	}

}
