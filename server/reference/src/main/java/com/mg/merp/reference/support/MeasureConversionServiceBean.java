/*
 * MeasureConversionServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.reference.InvalidMeasureConversion;
import com.mg.merp.reference.MeasureConversionServiceLocal;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Measure;
import com.mg.merp.reference.model.MeasureConversion;

/**
 * Бизнес-компонент "Преобразование ЕИ"
 * 
 * @author leonova
 * @version $Id: MeasureConversionServiceBean.java,v 1.7 2007/07/09 14:32:49 safonov Exp $
 */
@Stateless(name="merp/reference/MeasureConversionService")
public class MeasureConversionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MeasureConversion, Integer> implements MeasureConversionServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, MeasureConversion entity) {
		context.addRule(new MandatoryAttribute(entity, "MeasureFrom"));
		context.addRule(new MandatoryAttribute(entity, "MeasureTo"));
		context.addRule(new MandatoryAttribute(entity, "AlgRepository"));		
	}

	private class BusinessAddinExecuter implements BusinessAddinListener<BigDecimal> {
		private BigDecimal valueTo;
		
		private void execute(MeasureConversion measureConversion, Measure measureFrom, Measure measureTo, Catalog catalogId, java.util.Date convTime, BigDecimal valueFrom) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(MeasureConversionBusinessAddin.MEASURE_FROM_PARAM, measureFrom);
			params.put(MeasureConversionBusinessAddin.MEASURE_TO_PARAM, measureTo);
			params.put(MeasureConversionBusinessAddin.CATALOG_PARAM, catalogId);
			params.put(MeasureConversionBusinessAddin.VALUE_FROM_PARAM, valueFrom);
			params.put(MeasureConversionBusinessAddin.CONV_TIME_PARAM, convTime);
			BusinessAddinEngineLocator.locate().perform(measureConversion.getAlgRepository(), params, this);			
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#aborted(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void aborted(BusinessAddinEvent<BigDecimal> event) {
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.baiengine.BusinessAddinListener#completed(com.mg.merp.baiengine.BusinessAddinEvent)
		 */
		public void completed(BusinessAddinEvent<BigDecimal> event) {
			valueTo = event.getResult();
		}

	}
	
	protected BigDecimal doConversion(Measure measureFrom, Measure measureTo, Catalog catalog, java.util.Date convTime, BigDecimal valueFrom) throws InvalidMeasureConversion {
		//если совпадают ЕИ, то не пересчитываем
		if (measureFrom.getId() == measureTo.getId())
			return valueFrom;
		
		OrmTemplate ormTemplate = OrmTemplate.getInstance();
		//ищем алгоритм конвертации для пары ЕИ и позиции каталога
		MeasureConversion measureConversion = ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(MeasureConversion.class)
				.add(Restrictions.eq("MeasureFrom", measureFrom))
				.add(Restrictions.eq("MeasureTo", measureTo))
				.add(Restrictions.eq("Catalog", catalog))
				.setFlushMode(FlushMode.MANUAL));
		//если не нашли, то пытаемся найти для пары ЕИ
		if (measureConversion == null) {
			measureConversion = ormTemplate.findUniqueByCriteria(OrmTemplate.createCriteria(MeasureConversion.class)
					.add(Restrictions.eq("MeasureFrom", measureFrom))
					.add(Restrictions.eq("MeasureTo", measureTo))
					.add(Restrictions.isNull("Catalog"))
					.setFlushMode(FlushMode.MANUAL));
		}
		if (measureConversion == null)
			throw new InvalidMeasureConversion(measureFrom.getCode(), measureTo.getCode());
		
		//не поддерживается интерактивность, поэтому сразу получаем результат
		BusinessAddinExecuter businessAddinExecuter = new BusinessAddinExecuter();
		businessAddinExecuter.execute(measureConversion, measureFrom, measureTo, catalog, convTime, valueFrom);
		return businessAddinExecuter.valueTo;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.reference.MeasureConversionServiceLocal#conversion(com.mg.merp.reference.model.Measure, com.mg.merp.reference.model.Measure, com.mg.merp.reference.model.Catalog, java.util.Date, java.math.BigDecimal)
	 */
	@PermitAll
	public BigDecimal conversion(Measure measureFrom, Measure measureTo, Catalog catalog, java.util.Date convTime, BigDecimal valueFrom) throws InvalidMeasureConversion {
		return doConversion(measureFrom, measureTo, catalog, convTime, valueFrom);
	}
}
