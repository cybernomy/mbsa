/*
 * DefaultDocSpecPropertiesCalculationStrategyTest.java
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
package com.mg.merp.test.retail;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.mg.framework.api.math.RoundContext;
import com.mg.merp.retail.model.RtlInvoiceSpec;
import com.mg.merp.retail.support.DefaultRtlDocSpecPropertiesCalculationStrategy;

/**
 * @author Artem V. Sharapov
 * @version $Id: DefaultRtlDocSpecPropertiesCalculationStrategyTest.java,v 1.1 2007/10/30 14:59:08 sharapov Exp $
 */
public class DefaultRtlDocSpecPropertiesCalculationStrategyTest {

	private class Strategy extends DefaultRtlDocSpecPropertiesCalculationStrategy {
		
		public Strategy(RtlInvoiceSpec rtlInvoiceSpec, boolean isWithTaxes, int currencyScale, RoundContext roundContext) {
			super(rtlInvoiceSpec, isWithTaxes, currencyScale, roundContext);
		}
		
		public void doAdjust() {
			super.doAdjust();
		}
	}
	
	/**
	 * Расчет цены со скидкой/наценкой
	 * 
	 * Входные параметры:
	 * кол-во
	 * цена
	 * скидка/наценка
	 */
	@Test
	public void calculatePriceWithDiscount() {
		RtlInvoiceSpec rtlInvoiceSpec = new RtlInvoiceSpec(); 
		rtlInvoiceSpec.setQuantity(BigDecimal.ONE);
		rtlInvoiceSpec.setPrice1(BigDecimal.ONE);
		rtlInvoiceSpec.setDiscount(BigDecimal.TEN);
		
		BigDecimal expectedPriceWithDiscount = new BigDecimal(1.10);
		
		Strategy strategy = new Strategy(rtlInvoiceSpec, true, 4, new RoundContext(4));
		strategy.doAdjust();
			
		Assert.assertEquals(rtlInvoiceSpec.getPriceWithDiscount().doubleValue(), expectedPriceWithDiscount.doubleValue(), 0);
	}
	
	
	
	/**
	 * Расчет цены
	 * 
	 * Входные параметры:
	 * кол-во
	 * цена со скидкой/наценкой
	 */
	@Test
	public void calculatePrice() {
		BigDecimal price = new BigDecimal(1.10);
		
		RtlInvoiceSpec rtlInvoiceSpec = new RtlInvoiceSpec(); 
		rtlInvoiceSpec.setQuantity(BigDecimal.ONE);
		rtlInvoiceSpec.setPrice1(null);
		rtlInvoiceSpec.setDiscount(null);
		rtlInvoiceSpec.setPriceWithDiscount(price);
		
		BigDecimal expectedPrice1 = price;
		
		Strategy strategy = new Strategy(rtlInvoiceSpec, true, 4, new RoundContext(4));
		strategy.doAdjust();
		
		Assert.assertEquals(rtlInvoiceSpec.getPrice1().doubleValue(), expectedPrice1.doubleValue(), 0);
	}

}
	
	
