/*
 * DefaultBankDocumentPropertiesCalculationStrategy.java
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
package com.mg.merp.account.support;

import java.math.BigDecimal;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.account.model.BankDocument;

/**
 * Стандартная реализация стратегии расчета свойств банковского документа
 * 
 * @author Artem V. Sharapov
 * @version $Id: DefaultBankDocumentPropertiesCalculationStrategy.java,v 1.1 2007/11/08 12:18:48 sharapov Exp $
 */
public class DefaultBankDocumentPropertiesCalculationStrategy {
	
	private BankDocument entity;
	private RoundContext configRc;
	private RoundContext rc;

	
	public DefaultBankDocumentPropertiesCalculationStrategy(BankDocument bankDocument, int configCurrencyScale) {
		entity = bankDocument;
		configRc = new RoundContext(configCurrencyScale);
		rc = new RoundContext(6);
	}
	
	/**
	 * Рассчитать и установить сумму документа
	 */
	public void calculateDocSum() {
		BigDecimal sumNds1 = entity.getNds1Summa() == null ? BigDecimal.ZERO : entity.getNds1Summa(); 
		BigDecimal sumNds2 = entity.getNds2Summa()== null ? BigDecimal.ZERO : entity.getNds2Summa();
		BigDecimal clearSum = entity.getClearSumma() == null ? BigDecimal.ZERO : entity.getClearSumma();
		
		entity.setSumCur(clearSum.add(sumNds1).add(sumNds2));
		entity.setSumNat(MathUtils.multiply(entity.getSumCur(), entity.getCurCource(), configRc));
	}

	/**
	 * Рассчитать и установить суммы НДС
	 */
	public void calculateNdsSum() {
		BigDecimal nds1Rate = entity.getNds1Rate() == null ? BigDecimal.ZERO : entity.getNds1Rate();
		BigDecimal nds2Rate = entity.getNds2Rate() == null ? BigDecimal.ZERO : entity.getNds2Rate();
		
		if(entity.getNds1Summa() != null && MathUtils.compareToZero(entity.getNds1Summa()) != 0) {
			if(MathUtils.compareToZero(nds1Rate) != 0)
				entity.setSummaWithNds1(MathUtils.round(MathUtils.divide(MathUtils.HUNDRED.multiply(entity.getNds1Summa()), nds1Rate, rc).add(entity.getNds1Summa()), configRc));
			else { 
				entity.setNds1Summa(null);
				if(entity.getSummaWithNds1() == null || MathUtils.compareToZero(entity.getSummaWithNds1()) == 0)
					entity.setSummaWithNds1(null);
			}
		} else if(entity.getSummaWithNds1() != null) {
			entity.setNds1Summa(MathUtils.round(entity.getSummaWithNds1().multiply(BigDecimal.ONE.subtract(MathUtils.divide(MathUtils.HUNDRED, MathUtils.HUNDRED.add(nds1Rate), rc))), configRc));
		}
		
		if(entity.getNds2Summa() != null && MathUtils.compareToZero(entity.getNds2Summa()) != 0) {
			if(MathUtils.compareToZero(nds2Rate) != 0)
				entity.setSummaWithNds2(MathUtils.round(MathUtils.divide(MathUtils.HUNDRED.multiply(entity.getNds2Summa()), nds2Rate, rc).add(entity.getNds2Summa()), configRc));
			else { 
				entity.setNds2Summa(null);
				if(entity.getSummaWithNds2() == null || MathUtils.compareToZero(entity.getSummaWithNds2()) == 0)
					entity.setSummaWithNds2(null);
			}
		} else if(entity.getSummaWithNds2() != null) {
			entity.setNds2Summa(MathUtils.round(entity.getSummaWithNds2().multiply(BigDecimal.ONE.subtract(MathUtils.divide(MathUtils.HUNDRED, MathUtils.HUNDRED.add(nds2Rate), rc))), configRc));
		}
		
		BigDecimal sumWithNds1 = entity.getSummaWithNds1() == null ? BigDecimal.ZERO : entity.getSummaWithNds1(); 
		BigDecimal sumWithNds2 = entity.getSummaWithNds2() == null ? BigDecimal.ZERO : entity.getSummaWithNds2();
		BigDecimal sumNds1 = entity.getNds1Summa() == null ? BigDecimal.ZERO : entity.getNds1Summa(); 
		BigDecimal sumNds2 = entity.getNds2Summa()== null ? BigDecimal.ZERO : entity.getNds2Summa();
 
		entity.setSumCur(sumWithNds1.add(sumWithNds2));
		entity.setClearSumma(entity.getSumCur().subtract(sumNds1).subtract(sumNds2));
		entity.setSumNat(entity.getSumCur() == null ? null : MathUtils.multiply(entity.getSumCur(), entity.getCurCource(), configRc));
	}

}
