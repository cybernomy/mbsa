/*
 * SolutionSearchDlg.java
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
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.AbstractForm;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.SolutionServiceLocal;
import com.mg.merp.crm.SymptomServiceLocal;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;
import com.mg.merp.crm.model.Symptom;

/**
 * Контроллер диалога бизнес-компонента "Поиск решения"
 * 
 * @author Artem V. Sharapov
 * @version $Id: SolutionSearchDlg.java,v 1.2 2007/05/16 06:21:43 sharapov Exp $
 */
public class SolutionSearchDlg extends AbstractForm {

	private DefaultTableController symptomTable;
	private SymptomServiceLocal symptomService = (SymptomServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Symptom"); //$NON-NLS-1$;
	private Integer symptomSelectedIndex;
	private List<Integer> symptoms = new ArrayList<Integer>();

	private DefaultTableController problemTable;
	private ProblemServiceLocal problemService = (ProblemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Problem"); //$NON-NLS-1$
	private Integer problemSelectedIndex;
	private Object[] problemSelectedRow;

	private DefaultTableController solutionTable;
	private SolutionServiceLocal solutionService = (SolutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Solution"); //$NON-NLS-1$	
	private List<String> solutionParamsName = new ArrayList<String>();
	private List<Object> solutionParamsValue = new ArrayList<Object>();
	private Integer solutionSelectedIndex;


	public SolutionSearchDlg() {

		symptomTable = new DefaultTableController(new DefaultEJBQLTableModel() { 
			protected String createQueryText() {
				StringBuilder INIT_QUERY_TEXT = new StringBuilder();
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				INIT_QUERY_TEXT.append("select %s from Symptom s %s where s.id = -1"); //$NON-NLS-1$
				for (Integer symptomId : symptoms) {
					INIT_QUERY_TEXT.append(" or s.id = " + symptomId); //$NON-NLS-1$
				}
				return String.format(INIT_QUERY_TEXT.toString(), fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					symptomSelectedIndex = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					symptomSelectedIndex = (Integer) row[0];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Symptom.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Symptom.class, "Name", "s.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, symptomService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText());
			}
		});

		problemTable = new DefaultTableController(new SolutionSearchProblemTableModel() {

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.crm.support.ui.SolutionSearchProblemTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					problemSelectedIndex = null;
				else {
					problemSelectedRow = getRowList().get(rows[0]);
					problemSelectedIndex = (Integer) problemSelectedRow[0];

					Problem problem = (Problem) ServerUtils.getPersistentManager().find(Problem.class, problemSelectedIndex);
					solutionParamsName.add("problem"); //$NON-NLS-1$
					solutionParamsValue.add(problem);
					((SolutionSearchSolutionTableModel) solutionTable.getModel()).setTableModelParams(solutionParamsName, solutionParamsValue);
					solutionTable.getModel().load();
				}
			}
		});

		solutionTable = new DefaultTableController(new SolutionSearchSolutionTableModel() {

			/*
			 * (non-Javadoc)
			 * @see com.mg.merp.crm.support.ui.SolutionSearchSolutionTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					solutionSelectedIndex = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					solutionSelectedIndex = (Integer) row[0];
				}
			}
		});
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Выбрать симптом"
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionIncludeSymptom(WidgetEvent event) throws ApplicationException {
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(symptomService, null);
		form.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Symptom symptom = ((Symptom) event.getItems()[0]);
				if(!symptoms.contains(symptom.getId())) {
					symptoms.add(symptom.getId());
					symptomTable.getModel().load();
				}
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		form.run();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Убрать симптом"
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionExcludeSymptom(WidgetEvent event) throws ApplicationException {
		if (symptomSelectedIndex == null)
			return;
		Symptom symptom = (Symptom) ServerUtils.getPersistentManager().find(Symptom.class, symptomSelectedIndex);
		symptoms.remove(symptom.getId());
		symptomTable.getModel().load();
	}

	/**
	 * Обработчик пункта КМ "Выбрать проблему"
	 * @param event - событие
	 * @throws ApplicationException - ИС
	 */
	public void onActionIncludeProblem(WidgetEvent event) throws ApplicationException {
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(problemService, null);
		form.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Problem problem = ((Problem) event.getItems()[0]);
				includeProblem(problem);
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		form.run();
	}

	/**
	 * Добавить запись в таблицу "Проблемы"
	 * @param problem - проблема
	 */
	private void includeProblem(Problem problem) {
		((SolutionSearchProblemTableModel) problemTable.getModel()).addRow(problem);
	}

	/**
	 * Обработчик пункта КМ "Убрать проблему"
	 * @param event - событие
	 */
	public void onActionExcludeProblem(WidgetEvent event) {
		((SolutionSearchProblemTableModel) problemTable.getModel()).removeRow(problemSelectedRow);
		((SolutionSearchSolutionTableModel) solutionTable.getModel()).resetTable();
	}

	/**
	 * Обработчик кнопки/пункта КМ "Найти"
	 * @param event - событие
	 */
	public void onActionSearch(WidgetEvent event) {
		((SolutionSearchProblemTableModel) problemTable.getModel()).setSymptoms(symptoms);
		problemTable.getModel().load();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Просмотреть" (проблему)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionViewProblem(WidgetEvent event) throws ApplicationException {
		if (problemSelectedIndex == null)
			return;
		Problem problem = (Problem) ServerUtils.getPersistentManager().find(Problem.class, problemSelectedIndex);
		MaintenanceHelper.view(problemService, problem.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Изменить" (проблему)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionEditProblem(WidgetEvent event) throws ApplicationException {
		if (problemSelectedIndex == null)
			return;
		Problem problem = (Problem) ServerUtils.getPersistentManager().find(Problem.class, problemSelectedIndex);
		MaintenanceHelper.edit(problemService, problem.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Просмотреть" (решение)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionViewSolution(WidgetEvent event) throws ApplicationException {
		if (solutionSelectedIndex == null)
			return;
		Solution solution = (Solution) ServerUtils.getPersistentManager().find(Solution.class, solutionSelectedIndex);
		MaintenanceHelper.view(solutionService, solution.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Изменить" (решение)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionEditSolution(WidgetEvent event) throws ApplicationException {
		if (solutionSelectedIndex == null)
			return;
		Solution solution = (Solution) ServerUtils.getPersistentManager().find(Solution.class, solutionSelectedIndex);
		MaintenanceHelper.edit(solutionService, solution.getId(), null, null);
	}

}
