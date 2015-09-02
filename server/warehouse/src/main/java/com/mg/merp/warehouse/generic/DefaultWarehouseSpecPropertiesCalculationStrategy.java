/**
 * DefaultWarehouseSpecPropertiesCalculationStrategy.java
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
package com.mg.merp.warehouse.generic;

import java.math.BigDecimal;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.DocumentTaxProcessor;
import com.mg.merp.document.generic.AbstractDocSpecPropertiesCalculationStrategy;
import com.mg.merp.warehouse.model.BaseStockDocumentSpec;

/**
 * Стандартная реализация стратегии расчета свойств спецификации товарного документа
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultWarehouseSpecPropertiesCalculationStrategy.java,v 1.2 2008/06/04 09:38:02 sharapov Exp $
 */
public class DefaultWarehouseSpecPropertiesCalculationStrategy extends
		AbstractDocSpecPropertiesCalculationStrategy {
	private boolean isWithTaxes;
	private BaseStockDocumentSpec entity;
	private RoundContext roundContext;
	
	public DefaultWarehouseSpecPropertiesCalculationStrategy(
			boolean isWithTaxes, BaseStockDocumentSpec entity,
			RoundContext roundContext) {
		super();
		this.isWithTaxes = isWithTaxes;
		this.entity = entity;
		this.roundContext = roundContext;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.AbstractDocSpecPropertiesCalculationStrategy#doAdjust()
	 */
	@Override
	protected void doAdjust() {
		BigDecimal quantity = entity.getQuantity();
		//скидка/наценка в % из документа
		BigDecimal docDiscount = entity.getDocDiscount();
		if (docDiscount == null)
			docDiscount = BigDecimal.ZERO;
		//сумма внешней скидки/наценки расчитанной для данной спецификации
		BigDecimal calculatedDiscount = entity.getExternalDiscountValue();
		if (calculatedDiscount == null) {
			calculatedDiscount = BigDecimal.ZERO;
			//+ скидка/наценка в % из спецификации если нет внешней скидки/наценки
			if (entity.getDiscount() != null)
				docDiscount = docDiscount.add(entity.getDiscount());
		}
		
		if (entity.getPrice1() == null || MathUtils.compareToZero(entity.getPrice1()) == 0) {
			//если не заданы цены и суммы, то выходим, вероятно здесь еще надо проверять
			//цены и суммы со скидками и от них уже вычислять
			if (entity.getSumma1() == null || MathUtils.compareToZero(entity.getSumma1()) == 0) {
				//http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4585
				entity.setSummaWithDiscount(entity.getSumma1());
				return;
			}

			boolean isZeroQuan = quantity == null || MathUtils.compareToZero(quantity) == 0;
			entity.setPrice1(isZeroQuan ?
					entity.getSumma1() : MathUtils.divide(entity.getSumma1(), quantity, roundContext));
			BigDecimal discountValue = entity.getPrice1().multiply(docDiscount).divide(MathUtils.HUNDRED);
			entity.setPriceWithDiscount(MathUtils.round(entity.getPrice1().add(calculatedDiscount).add(discountValue), roundContext));
			entity.setSummaWithDiscount(isZeroQuan ?
					entity.getPriceWithDiscount() : MathUtils.multiply(entity.getPriceWithDiscount(), quantity, roundContext));
		}
		else {
			BigDecimal discountValue = entity.getPrice1().multiply(docDiscount).divide(MathUtils.HUNDRED);
			entity.setPriceWithDiscount(MathUtils.round(entity.getPrice1().add(calculatedDiscount).add(discountValue), roundContext));
			if (MathUtils.compareToZeroOrNull(quantity) != 0) {
				entity.setSummaWithDiscount(MathUtils.multiply(entity.getPriceWithDiscount(), quantity, roundContext));
				entity.setSumma1(MathUtils.multiply(entity.getPrice1(), quantity, roundContext));
			} else {
				entity.setSummaWithDiscount(BigDecimal.ZERO);
				entity.setSumma1(BigDecimal.ZERO);
			}
		}
		
		//если есть внешняя скидка/наценка то расчитаем в % для данной спецификации
		if (entity.getExternalDiscountValue() != null)
			entity.setDiscount(MathUtils.divide(calculatedDiscount.multiply(MathUtils.HUNDRED), entity.getPrice1(), new RoundContext(6)));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.AbstractDocSpecPropertiesCalculationStrategy#doCalculateTaxes()
	 */
	@Override
	protected void doCalculateTaxes() {
		if (isWithTaxes) {
			DocumentTaxProcessor taxProcessor = (DocumentTaxProcessor) ApplicationDictionaryLocator.locate().getBusinessService(DocumentTaxProcessor.SERVICE_NAME);
			taxProcessor.calculateDocumentSpecTaxes(entity, entity.getPriceWithDiscount(), entity.getSummaWithDiscount(), false, roundContext);
		}
		else {
			entity.setSumma(entity.getSummaWithDiscount());
			entity.setPrice(entity.getPriceWithDiscount());
		}
	}

}
