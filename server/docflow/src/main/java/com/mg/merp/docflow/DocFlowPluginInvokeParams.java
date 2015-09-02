/*
 * DocFlowPluginInvokeParams.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.docflow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

/**
 * Параметры запуска (отката) документооборота
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginInvokeParams.java,v 1.3 2008/09/01 07:44:22 safonov Exp $
 */
public class DocFlowPluginInvokeParams {
	private DocHead document;
	private BigDecimal performedSum;
	private DocProcessStage performedStage;
	private Date processDate;
	private boolean isSilent;
	private Integer data1;
	private Integer data2;
	private Serializable headStateValue;
	private List<DocumentSpecItem> specList;
	
	public DocFlowPluginInvokeParams(DocHead document, Date processDate, DocProcessStage performedStage, boolean isSilent, BigDecimal docSum, List<DocumentSpecItem> specList) {
		this.document = document;
		this.performedSum = docSum;
		this.performedStage = performedStage;
		this.processDate = processDate;
		this.isSilent = isSilent;
		this.specList = specList;
	}
	
	/**
	 * получить отрабатываемый документ
	 * 
	 * @return документ
	 */
	public DocHead getDocument() {
		return document;
	}

	/**
	 * получить сумму отработки (отката)
	 * 
	 * @return	сумма
	 */
	public BigDecimal getPerformedSum() {
		return performedSum;
	}
	
	/**
	 * установить сумму отработки (отката)
	 * 
	 * @param value	сумма отработки (отката)
	 */
	public void setPerformedSum(BigDecimal value) {
		this.performedSum = value;
	}
	
	/**
	 * получить текущий этап документооборота
	 * 
	 * @return	текущий этап
	 */
	public DocProcessStage getPerformedStage() {
		return performedStage;
	}
	
	/**
	 * получить дату отработки
	 * 
	 * @return	дата отработки
	 */
	public Date getProcessDate() {
		return processDate;
	}

	/**
	 * получить признак "тихого" выполнения ДО, в случае если значение <code>true</code>, то этап
	 * ДО не должен взаимодействовать с пользовательским интерфейсом, если существует потребность
	 * во взаимодействии с пользователем, то выполнение этапа должно генерировать ИС {@link com.mg.merp.docflow.SilentException}
	 * 
	 * @return признак "тихого" выполнения ДО
	 */
	public boolean isSilent() {
		return isSilent;
	}

	/**
	 * установить дату отработки
	 * 
	 * @param processDate дата отработки
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	/**
	 * получить состояние выполнения документооборота, как правило используется в механизмах
	 * отката для восстановления состояния системы
	 * 
	 * @return состояние выполнения
	 */
	public Serializable getHeadStateValue() {
		return headStateValue;
	}

	/**
	 * установить состояние выполнения документооборота, используется для сохранения
	 * любой информации полученной при отработке документа, данная информация будет доступна
	 * при выполнении отката
	 * 
	 * @param headStateValue состояние выполнения
	 */
	public void setHeadStateValue(Serializable data) {
		this.headStateValue = data;
	}

	/**
	 * получить состояние выполнения документооборота 1, используется для совместимости
	 * с предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать
	 * {@link #getHeadStateValue()}
	 * 
	 * @return состояние 1
	 */
	public Integer getData1() {
		return data1;
	}

	/**
	 * установить состояние выполнения документооборота 1, используется для совместимости
	 * с предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать
	 * {@link #setHeadStateValue()}
	 * 
	 * @param data1	состояние 1
	 */
	public void setData1(Integer data1) {
		this.data1 = data1;
	}

	/**
	 * получить состояние выполнения документооборота 2, используется для совместимости
	 * с предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать
	 * {@link #getHeadStateValue()}
	 * 
	 * @return состояние 2
	 */
	public Integer getData2() {
		return data2;
	}

	/**
	 * установить состояние выполнения документооборота 2, используется для совместимости
	 * с предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать
	 * {@link #setHeadStateValue()}
	 * 
	 * @param data1	состояние 2
	 */
	public void setData2(Integer data2) {
		this.data2 = data2;
	}

	/**
	 * получить список спецификаций документа предназначенных для выполнения в документообороте
	 * 
	 * @return список спецификаций
	 */
	public List<DocumentSpecItem> getSpecList() {
		if (specList == null)
			throw new IllegalStateException("Specification list is null");
		return specList;
	}
}
