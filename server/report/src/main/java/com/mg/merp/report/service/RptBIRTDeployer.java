/*
 * RptBIRTDeployer.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.report.service;

import com.mg.merp.report.RptMainTransfer;

/**
 * Интерфейс связи сервера приложений и редактора отчётов в среде разработки
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptBIRTDeployer.java,v 1.7 2007/08/30 14:29:18 safonov Exp $
 */
public interface RptBIRTDeployer {

	/**
	 * Установка нового шаблона для существующего отчёта
	 * 
	 * @param rmt
	 * @return NULL, если отчёта с соответствующим кодом нет в базе, иначе
	 *         возвращает экземпляр отчёта с новой временной меткой. Если отчёт
	 *         был уже изменён, то возвращается новый экземпляр
	 *         {@link RptMainTransfer}
	 * @throws Exception
	 */
	RptMainTransfer persistTemplate(RptMainTransfer rmt) throws Exception;

	/**
	 * Возвращает доступные отчёты, код которых соответствует маске
	 * 
	 * @param likeSentence
	 *            Маска кодов отчётов. Формат допустимый для предложения "like"
	 *            в EJBQL(Символы '*' автоматически заменяются на '%', а '?' на
	 *            '_')
	 * @return Список отчётов, соответствующих маске
	 * @throws Exception
	 */
	RptMainTransfer[] getReports(String likeSentence) throws Exception;

	/**
	 * Создание нового отчёта
	 * 
	 * @param rmt
	 * @return Переданный экземпляр класса {@link RptMainTransfer}, с
	 *         установленными параметрами id и sysVersion.
	 */
	RptMainTransfer addReport(RptMainTransfer rmt) throws Exception;

	/**
	 * Удаление списка отчётов
	 * 
	 * @param ids
	 *            Массив с идентификаторами отчётов, подлежащих удалению
	 * @throws Exception
	 */
	public void deleteReportList(Integer[] ids) throws Exception;

	/**
	 * Изменение отчёта
	 * 
	 * @param rmt
	 *            Новые значения полей
	 * @return Изменённый отчёт, с новым параметром sysVersion или NULL, в
	 *         случае отката транзакции
	 * @throws Exception
	 */
	RptMainTransfer editReport(RptMainTransfer rmt) throws Exception;
}
