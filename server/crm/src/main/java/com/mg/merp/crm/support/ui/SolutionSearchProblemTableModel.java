/*
 * SolutionSearchProblemTableModel.java
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
package com.mg.merp.crm.support.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.reference.model.NaturalPerson;

/**
 * Модель таблицы "Проблемы"
 * Специализирована для диалога "Поиск решения"
 * 
 * @author Artem V. Sharapov
 * @version $Id: SolutionSearchProblemTableModel.java,v 1.1 2007/05/16 06:21:43 sharapov Exp $
 */
public class SolutionSearchProblemTableModel extends DefaultEJBQLTableModel {

	private List<Integer> symptoms = new ArrayList<Integer>();

	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		StringBuilder INIT_QUERY_TEXT = new StringBuilder();
		INIT_QUERY_TEXT.append("select distinct %s from Problem p join p.LinkSymptomProblems ls %s where ls.Id.CrmSymptom.Id = -1"); //$NON-NLS-1$
		for (Integer symptomId : symptoms) 
			INIT_QUERY_TEXT.append(" or ls.Id.CrmSymptom.Id = " + symptomId); //$NON-NLS-1$

		return String.format(INIT_QUERY_TEXT.toString(), fieldsList, fromList);		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(Problem.class, "Id", "p.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "Name", "p.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "Keywords", "p.Keywords", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "ProblemType", "pt.Code", "left join p.ProblemType as pt", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result.add(new TableEJBQLFieldDef(Problem.class, "Priority", "p.Priority", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "ValidFrom", "p.ValidFrom", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "ValidTo", "p.ValidTo", false));				 //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(Problem.class, "Creator", "per.Surname", "left join p.Creator as c left join c.Person per", false));		 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected void doLoad() {
		setQuery(createQueryText());
	}

	/**
	 * Установить список симптомов
	 * @param symptoms - список симптомов
	 */
	public void setSymptoms(List<Integer> symptoms) {
		this.symptoms = symptoms;
	}

	/**
	 * Удалить запись в таблицу "Проблемы"
	 * @param problemRow - запись
	 */
	public void removeRow(Object[] problemRow) {
		rowList.remove(problemRow);
		fireModelChange();
	}
	
	/**
	 * Добавить запись в таблицу "Проблемы"
	 * @param problemRow - запись
	 */
	public void addRow(Problem problem) {
		Object[] problemRow = createProblemRow(problem);
		if(!isContains(problemRow)) {
			rowList.add(problemRow);
			fireModelChange();
		}
	}
	
	private boolean isContains(Object[] problemRow) {
		if(!rowList.isEmpty()) {
			Iterator<Object[]> listIteerator = rowList.iterator();
			while(listIteerator.hasNext()) {
				Integer problemId = (Integer) listIteerator.next()[0];
				if(((Integer) problemRow[0]).compareTo(problemId) == 0) 
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Сформировать запись таблицы "Проблемы".
	 * @param problem - проблема
	 * @return запись
	 */
	private Object[] createProblemRow(Problem problem) {
		String problemTypeCode = null;
		String creatorSurname = null;

		if(problem.getProblemType() != null)
			problemTypeCode = problem.getProblemType().getCode();

		if(problem.getCreator() != null) {
			if(problem.getCreator().getPerson() != null) {
				NaturalPerson person = problem.getCreator().getPerson();
				creatorSurname = person.getSurname();
			}
		}

		return new Object[] {
				problem.getId(), 
				problem.getName(), 
				problem.getKeywords(), 
				problemTypeCode, 
				problem.getPriority(), 
				problem.getValidFrom(),
				problem.getValidTo(),
				creatorSurname}; 
	}

}
