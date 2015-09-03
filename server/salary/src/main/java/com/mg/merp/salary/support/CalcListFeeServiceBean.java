/*
 * CalcListFeeServiceBean.java
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

package com.mg.merp.salary.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.salary.CalcListFeeParamServiceLocal;
import com.mg.merp.salary.CalcListFeeServiceLocal;
import com.mg.merp.salary.model.CalcListFee;
import com.mg.merp.salary.model.CalcListFeeParam;
import com.mg.merp.salary.model.FeeModel;
import com.mg.merp.salary.model.FeeModelParam;

/**
 * Реализация бизнес-компонента "Начисления/ударжания расчетных листков" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListFeeServiceBean.java,v 1.5 2007/08/21 05:33:09 sharapov Exp $
 */
@Stateless(name="merp/salary/CalcListFeeService") //$NON-NLS-1$
public class CalcListFeeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcListFee, Integer> implements CalcListFeeServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onPostCreate(CalcListFee entity) {
		addParamsFromFeeModel(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(CalcListFee entity) {
		adjust(entity);
	}

	/**
	 * Добавить параметры из ообразца начисления/удержания
	 * @param calcListFee - начисление/удержание
	 */
	protected void addParamsFromFeeModel(CalcListFee calcListFee) {
		List<FeeModelParam> feeModelParams = getFeeModelParams(calcListFee.getFeeModel());
		if(!feeModelParams.isEmpty()) {
			CalcListFeeParam[] calcListFeeParams = new CalcListFeeParam[feeModelParams.size()]; 
			for(int i = 0; i < feeModelParams.size(); i++)
				calcListFeeParams[i] = initializeCalcListFeeParam(calcListFee, feeModelParams.get(i));

			createCalcListFeeParams(calcListFeeParams);
		}
	}

	/**
	 * Получить список параметров образца начисления/удержания
	 * @param feeModel - образец начисления/удержания
	 * @return список параметров образца начисления/удержания
	 */
	protected List<FeeModelParam> getFeeModelParams(FeeModel feeModel) {
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(FeeModelParam.class)
											.add(Restrictions.eq("FeeModel", feeModel)));

	}

	protected CalcListFeeParam initializeCalcListFeeParam(CalcListFee calcListFee, FeeModelParam feeModelParam) {
		CalcListFeeParam calcListFeeParam = new CalcListFeeParam();
		calcListFeeParam.setCalcListFee(calcListFee);
		calcListFeeParam.setFeeRefParam(feeModelParam.getFeeRefParam());
		calcListFeeParam.setParamValue(feeModelParam.getParamValue());
		return calcListFeeParam;
	}

	protected void createCalcListFeeParams(CalcListFeeParam[] calcListFeeParams) {
		if(calcListFeeParams != null && calcListFeeParams.length > 0) {
			CalcListFeeParamServiceLocal calcListFeeParamService = getCalcListFeeParamService();
			for(CalcListFeeParam calcListFeeParam : calcListFeeParams)
				calcListFeeParamService.create(calcListFeeParam);
		}
	}
	
	protected void adjust(CalcListFee calcListFee) {
		if(isNullParametrPresent(calcListFee))
			calcListFee.setNeedParams(true);
		else
			calcListFee.setNeedParams(false);
	}
	
	protected boolean isNullParametrPresent(CalcListFee calcListFee) {
		boolean isNullValuePresent = false;
		List<String> calcListFeeParamValues = getCalcListFeeParamValues(calcListFee);
		for(String calcListFeeParamValue : calcListFeeParamValues) {
			 if(calcListFeeParamValue == null) {
				 isNullValuePresent = true;
				 break;
			 }
		}
		return isNullValuePresent;
	}
	
	protected List<String> getCalcListFeeParamValues(CalcListFee calcListFee) {
		//TODO: is need paramValue convertation?
		return OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(CalcListFeeParam.class)
											.setProjection(Projections.property("ParamValue"))
											.add(Restrictions.eq("CalcListFee", calcListFee)));
	}


	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.CalcListFeeServiceLocal#recalc(int, int)
	 */
	public void recalc(int calcListId, int calcListFeeId) throws ApplicationException {
		//TODO: implement
	}

	private CalcListFeeParamServiceLocal getCalcListFeeParamService() {
		return (CalcListFeeParamServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(CalcListFeeParamServiceLocal.LOCAL_SERVICE_NAME);
	}

}
