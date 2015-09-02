/*
 * InvHeadServiceLocal.java
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
package com.mg.merp.account;

import java.util.Date;
import java.util.List;

import com.mg.merp.account.model.AccKind;
import com.mg.merp.account.model.InvHead;
import com.mg.merp.account.model.Period;

/**
 * Бизнес-компонент "Ивентарная картотека"
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: InvHeadServiceLocal.java,v 1.3 2008/04/28 10:09:51 alikaev Exp $
 */
public interface InvHeadServiceLocal extends com.mg.framework.api.DataBusinessObjectService<InvHead, Integer> {

	/**
	 * тип папки для инвентарной картотеки
	 */
	final static short FOLDER_PART = 7;

	/**
	 * Начисление амортизации
	 * 
	 * @param month
	 * 			- месяц начисления амортизации в абсолютном исчислении (год*12 + месяц)
	 * @param invHeads
	 * 			- инвентарные карточки
	 * @param accKind
	 * 			- вид учета основных средств и нематериальных активов
	 * @return
	 */
	Integer calcAmortization(Short month, List<InvHead> invHeads, AccKind accKind);

	/**
	 * Переоценка/дооценка
	 * 
	 * @param invHeads
	 * 			- список инвентарных картотек
	 * @param accKind
	 * 			- вид учета
	 * @param accRevaluateParams
	 * 			- параметры для проведения переоценки
	 */
	void revaluate(List<InvHead> invHeads, AccKind accKind, AccRevaluateParams accRevaluateParams);

	/**
	 * Перемещение
	 * 
	 * @param invHeads
	 * 			- список инвентарных картотек
	 * @param accKind
	 * 			- вид учета
	 * @param params
	 * 			- параметры для проведения операции перемещения инвентарных карточек
	 */
	void moveInventory(List<InvHead> invHeads, AccKind accKind, AccInventoryMoveParams params);

	/**
	 * Списание
	 * 
	 * @param invHeads
	 * 			- список инвентарных картотек
	 * @param accKind
	 * 			- вид учета
	 * @param params
	 * 			- параметры для проведения операции списания инвентарных карточек
	 */
	void retire(List<InvHead> invHeads, AccKind accKind, AccInventoryRetireParams params);

	/**
	 * Консервация
	 * 
	 * @param invHeads
	 * 			- список инвентарных картотек
	 * @param accKind
	 * 			- вид учета
	 * @param freezeDate
	 * 			- дата по которую консервируем инвентарную карточку
	 */
	void freeze(List<InvHead> invHeads, AccKind accKind, Date freezeDate);

	/**
	 * Формирование остатков
	 * 
	 * @param invHeads
	 * 				- список инвентарных картотек
	 * @param accKind
	 * 				- вид учета
	 * @param period
	 * 				- учетный период
	 */
	void makeRemains(List<InvHead> invHeads, AccKind accKind, Period period);

	/**
	 * Отменить последнее действие
	 * 
	 * @param invHeads
	 * 			- инвентарные картотеки
	 * @param accKinds
	 * 			- список видов учета
	 */
	void rollback(List<InvHead> invHeads, List<AccKind> accKinds);

}
