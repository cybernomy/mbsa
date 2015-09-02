/*
 * ItemSpecServiceBean.java
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

package com.mg.merp.lbschedule.support;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.ejb.Stateless;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.core.model.SysClient;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.lbschedule.ItemSpecServiceLocal;
import com.mg.merp.lbschedule.ItemSpecTaxServiceLocal;
import com.mg.merp.lbschedule.ScheduleServiceLocal;
import com.mg.merp.lbschedule.model.Item;
import com.mg.merp.lbschedule.model.ItemSpec;
import com.mg.merp.lbschedule.model.ItemSpecCreateData;
import com.mg.merp.lbschedule.model.ItemSpecTax;
import com.mg.merp.lbschedule.model.SpecSource;
import com.mg.merp.lbschedule.model.TaxResult;
import com.mg.merp.reference.CurrencyConversionResult;
import com.mg.merp.reference.CurrencyServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Currency;

/**
 * Реализация бизнес-компонента "Спецификация пункта графика исполнения обязательств"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: ItemSpecServiceBean.java,v 1.6 2007/04/17 14:25:55 sharapov Exp $
 */
@Stateless(name="merp/lbschedule/ItemSpecService") //$NON-NLS-1$
public class ItemSpecServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<ItemSpec, Integer> implements ItemSpecServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.lbschedule.ItemSpecServiceLocal#recomputeSum(com.mg.merp.lbschedule.model.ItemSpec, java.math.BigDecimal)
	 */
	public void recomputeSum(ItemSpec itemSpec, BigDecimal oldQuantity) {
		internalRecomputeSum(itemSpec, oldQuantity);
	}

	private void internalRecomputeSum(ItemSpec itemSpec, BigDecimal oldQuantity) {
		ItemSpecTaxServiceLocal itemSpecTaxService = (ItemSpecTaxServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(ItemSpecTaxServiceLocal.LOCAL_SERVICE_NAME);

		BigDecimal factor = computeFactor(itemSpec.getQty1(), oldQuantity);
		if(factor != null) {
			TaxResult taxResult = itemSpecTaxService.recomputeTaxes(itemSpec, factor);

			itemSpec.setSumma(itemSpec.getPrice().multiply(itemSpec.getQty1()));
			itemSpec.setClearPrice(itemSpec.getPrice().subtract(taxResult.getTaxPrice()));
			itemSpec.setClearSumma(itemSpec.getSumma().subtract(taxResult.getTaxSum()));
		}
	}

	private BigDecimal computeFactor(BigDecimal itemQuantity, BigDecimal itemOldQuantity) {
		if(itemOldQuantity.compareTo(itemQuantity) != 0) {
			if(itemOldQuantity.compareTo(BigDecimal.ZERO) != 0)
				return MathUtils.divide(itemQuantity, itemOldQuantity, new RoundContext(6));
			else
				return BigDecimal.ONE;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.lbschedule.ItemSpecServiceLocal#addSpec(com.mg.merp.lbschedule.model.Item, com.mg.merp.lbschedule.model.ItemSpecCreateData[])
	 */
	public void addSpec(Item item, ItemSpecCreateData[] itemSpecCreateData) {
		internalAddSpec(item, itemSpecCreateData);
	}

	private void internalAddSpec(Item item, ItemSpecCreateData[] itemSpecCreateData) {
		if(item == null)
			return;

		if(item.getHasSpec()) {
			if(item.getSpecSource() == SpecSource.DOCUMENT)
				addSpecByDocSpec(item, itemSpecCreateData);
			if(item.getSpecSource() == SpecSource.CATALOG)
				addSpecByCatalog(item, itemSpecCreateData);
			if(item.getSpecSource() == SpecSource.PRICELIST)
				addSpecByPriceList(item, itemSpecCreateData);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.lbschedule.ItemSpecServiceLocal#removeSpec(java.io.Serializable[])
	 */
	public void removeSpec(Serializable[] specIDs) {
		internalRemoveSpec(specIDs);
	}

	private void internalRemoveSpec(Serializable[] specIDs) {
		if(specIDs != null && specIDs.length > 0) {
			for (Serializable specId : specIDs) 
				erase((Integer) specId);
		}
	}

	/**
	 * Добавить позиции спецификации пункта графика из спецификации документа
	 * @param item - пункт графика
	 * @param itemSpecCreateData - данные для создания позиции спецификации
	 */
	private void addSpecByDocSpec(Item item, ItemSpecCreateData[] itemSpecCreateData) {
		BigDecimal factor;
		boolean isCurRateDirect = true;
		BigDecimal curRate = BigDecimal.ONE;

		for (ItemSpecCreateData data : itemSpecCreateData) {
			ItemSpec itemSpec = initialize();
			itemSpec.setItem(item);
			itemSpec.setDocSpec(data.getDocSpec());
			itemSpec.setQty1(data.getQty1());
			itemSpec.setQty2(data.getQty2());

			itemSpec.setCatalog(data.getCatalog());
			itemSpec.setMeasure1(data.getMeasure1());
			itemSpec.setMeasure2(data.getMeasure2());

			DocSpec docSpec = data.getDocSpec();
			Currency docHeadCurrency = docSpec.getDocHead().getCurrency();

			BigDecimal exactPrice = BigDecimal.ZERO;
			if (!isSameCurrency(docHeadCurrency, item.getCurCode())) {
				CurrencyConversionResult conversionResult = getCurrencyConverter().conversionEx(item.getCurCode(), docHeadCurrency, item.getCurRateAuthority(), item.getCurRateType(), item.getResultDate(), docSpec.getPrice());

				exactPrice = MathUtils.round(conversionResult.getAmount(), new RoundContext(4));
				isCurRateDirect = conversionResult.isDirect();
				curRate = conversionResult.getRate();

				itemSpec.setPrice(exactPrice);
				itemSpec.setSumma(MathUtils.round(exactPrice.multiply(itemSpec.getQty1()), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			}
			else {
				itemSpec.setPrice(docSpec.getPrice());
				itemSpec.setSumma(MathUtils.round(itemSpec.getPrice().multiply(itemSpec.getQty1()), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			}

			if(!docSpec.getQuantity().equals(BigDecimal.ZERO)) 
				factor = data.getQty1().divide(docSpec.getQuantity());
			else
				factor = BigDecimal.ZERO;

			createTaxes(itemSpec, docSpec, isCurRateDirect, curRate, factor);

			ServerUtils.getPersistentManager().persist(itemSpec);
		}
	}

	/**
	 * Добавить позиции спецификации пункта графика из прайс-листа
	 * @param item - пункт графика
	 * @param itemSpecCreateData - данные для создания позиции спецификации
	 */
	private void addSpecByPriceList(Item item, ItemSpecCreateData[] itemSpecCreateData) {
		for (ItemSpecCreateData data : itemSpecCreateData) {
			ItemSpec itemSpec = initialize();
			itemSpec.setItem(item);
			itemSpec.setDocSpec(null);
			itemSpec.setQty1(data.getQty1());
			itemSpec.setQty2(data.getQty2());
			itemSpec.setCatalog(data.getCatalog());

			Catalog catalog = data.getCatalog();
			if(catalog != null) {
				itemSpec.setMeasure1(catalog.getMeasure1());
				itemSpec.setMeasure2(catalog.getMeasure2());
			}

			Currency priceListCurrency = data.getPriceList().getCurrency();

			BigDecimal exactPrice = BigDecimal.ZERO;
			if (!isSameCurrency(priceListCurrency, item.getCurCode())) {
				exactPrice = MathUtils.round(getCurrencyConverter().conversion(item.getCurCode(), priceListCurrency, item.getCurRateAuthority(), item.getCurRateType(), item.getResultDate(), data.getPrice()), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC));

				itemSpec.setPrice(exactPrice);
				itemSpec.setSumma(MathUtils.round(exactPrice.multiply(itemSpec.getQty1()), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			}
			else {
				itemSpec.setPrice(data.getPrice());
				itemSpec.setSumma(MathUtils.round(itemSpec.getPrice().multiply(itemSpec.getQty1()), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			}
			itemSpec.setClearPrice(itemSpec.getPrice());
			itemSpec.setClearSumma(itemSpec.getSumma());

			ServerUtils.getPersistentManager().persist(itemSpec);
		}
	}

	private void createTaxes(ItemSpec itemSpec, DocSpec docSpec, boolean isCurRateDirect, BigDecimal curRate, BigDecimal factor) {
		Set<DocumentSpecTax> docSpecTaxes = docSpec.getTaxes();
		BigDecimal taxPrice = BigDecimal.ZERO;
		BigDecimal taxSum = BigDecimal.ZERO;
		BigDecimal tmpRate;

		if(isCurRateDirect)
			tmpRate = curRate;
		else
			tmpRate = MathUtils.divide(BigDecimal.ONE, curRate, new RoundContext(6));

		for (DocumentSpecTax docSpecTax : docSpecTaxes) {
			ItemSpecTax itemSpecTax = new ItemSpecTax();
			itemSpecTax.setItemSpec(itemSpec);
			itemSpecTax.setTax(docSpecTax.getTax());
			itemSpecTax.setPrice(MathUtils.round(docSpecTax.getPriceElement().multiply(tmpRate), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			itemSpecTax.setSumma(MathUtils.round(docSpecTax.getSumElement().multiply(tmpRate).multiply(factor), new RoundContext(ScheduleServiceLocal.SCHEDULE_SUM_PREC)));
			
			itemSpecTax.setSysClient((SysClient) ServerUtils.getCurrentSession().getSystemTenant());
			
			getPersistentManager().persist(itemSpecTax);

			taxPrice = taxPrice.add(itemSpecTax.getPrice());
			taxSum = taxSum.add(itemSpecTax.getSumma()); 
		}
		itemSpec.setClearPrice(itemSpec.getPrice().subtract(taxPrice));
		itemSpec.setClearSumma(itemSpec.getSumma().subtract(taxSum));
	}

	/**
	 * Добавить позиции спецификации пункта графика из каталога
	 * @param item - пункт графика
	 * @param itemSpecCreateData - данные для создания позиции спецификации
	 */
	private void addSpecByCatalog(Item item, ItemSpecCreateData[] itemSpecCreateData) {
		for (ItemSpecCreateData data : itemSpecCreateData) {
			ItemSpec itemSpec = initialize();
			itemSpec.setItem(item);
			itemSpec.setDocSpec(null);
			itemSpec.setQty1(data.getQty1());
			itemSpec.setQty2(data.getQty2());
			itemSpec.setCatalog(data.getCatalog());
			itemSpec.setMeasure1(data.getMeasure1());
			itemSpec.setMeasure2(data.getMeasure2());

			itemSpec.setPrice(BigDecimal.ZERO);
			itemSpec.setSumma(BigDecimal.ZERO);
			itemSpec.setClearPrice(BigDecimal.ZERO);
			itemSpec.setClearSumma(BigDecimal.ZERO);

			ServerUtils.getPersistentManager().persist(itemSpec);
		}
	}

	private CurrencyServiceLocal getCurrencyConverter() {
		return (CurrencyServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CurrencyServiceLocal.LOCAL_SERVICE_NAME);
	}

	private boolean isSameCurrency(Currency currency1, Currency currency2) {
		if(currency1.getId().equals(currency2.getId()))
			return true;
		else
			return false;
	}

}
