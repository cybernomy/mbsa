/*
 * CalcListServiceLocal.java
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
package com.mg.merp.salary;

import java.io.Serializable;

import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.salary.model.CalcList;

/**
 * Сервис бизнес-компонента "Расчетные листки"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcListServiceLocal.java,v 1.2 2007/07/09 08:20:19 sharapov Exp $
 */
public interface CalcListServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CalcList, Integer> {

	/**
	 * Имя сервиса
	 */
	final static String LOCAL_SERVICE_NAME= "merp/salary/CalcList"; //$NON-NLS-1$
	
	/**
	 * Добавить расчетные листки в расчетную ведомость
	 * @param positionFills - список занимаемых должностей сотрудниками
	 * @param payRollId - идентификатор расчетной ведомости
	 */
	void addCalcLists(PositionFill[] positionFills, int payRollId);
	
	/**
	 * Рассчитать
	 * @param calcListIds - список идентификаторов расчетных листков
	 * @param isClear - признак "очистки" Р.Л. перед расчетом
	 */
	void calculate(Serializable[] calcListIds, boolean isClear);
		
	/**
	 * Установить/снять признак "Расчитан и закрыт"
	 * @param calcListIds - список идентификаторов расчетных листков
	 * @param isClosed - признак "Расчитан и закрыт"
	 */
	void setClosed(Serializable[] calcListIds, boolean isClosed);

	public int getFromNextPeriod( int calcListId ) throws com.mg.framework.api.ApplicationException;

	public int getFromPrevPeriod( int calcListId ) throws com.mg.framework.api.ApplicationException;

	public int getFromAnotherPayRoll( int calcListId,int payRollId ) throws com.mg.framework.api.ApplicationException;

}
