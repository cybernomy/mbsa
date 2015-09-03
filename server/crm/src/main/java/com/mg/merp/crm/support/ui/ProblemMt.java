/*
 * ProblemMt.java
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
package com.mg.merp.crm.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.MaintenanceHelper;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.SolutionServiceLocal;
import com.mg.merp.crm.SymptomServiceLocal;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;
import com.mg.merp.crm.model.Symptom;

/**
 * Контроллер формы поддержки бизнес-компонента "Проблемы"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ProblemMt.java,v 1.6 2007/01/26 13:16:04 sharapov Exp $
 */
public class ProblemMt extends DefaultMaintenanceForm implements MasterModelListener {

	private static final String WIGET_SYMPTOM_TABEL = "symptomTable"; //$NON-NLS-1$
	private static final String WIGET_SOLUTION_TABEL = "solutionTable"; //$NON-NLS-1$
	private static final String WIGET_INCLUDE_SYMPTOM = "includeSymptom"; //$NON-NLS-1$
	private static final String WIGET_EXCLUDE_SYMPTOM = "excludeSymptom"; //$NON-NLS-1$
	private static final String WIGET_EDIT_SYMPTOM = "editSymptom"; //$NON-NLS-1$
	private static final String WIGET_INCLUDE_SOLUTION = "includeSolution"; //$NON-NLS-1$
	private static final String WIGET_EXCLUDE_SOLUTION = "excludeSolution"; //$NON-NLS-1$
	private static final String WIGET_EDIT_SOLUTION = "editSolution"; //$NON-NLS-1$

	private ProblemServiceLocal problemService = (ProblemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Problem"); //$NON-NLS-1$
	private MaintenanceTableController original;
	private LinkedDocumentServiceLocal originalService;
	protected AttributeMap originalProperties = new LocalDataTransferObject();

	private DefaultTableController symptomTable;
	private SymptomServiceLocal symptomService;
	protected AttributeMap symptomProperties = new LocalDataTransferObject();
	private Integer linkSymptomProblem;

	private DefaultTableController solutionTable;
	private SolutionServiceLocal solutionService;
	protected AttributeMap solutionProperties = new LocalDataTransferObject();
	private Integer linkSolutionProblem;

	public ProblemMt()throws Exception{
		addMasterModelListener(this);
		setMasterDetail(true);

		originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/LinkedDocument"); //$NON-NLS-1$
		original = new MaintenanceTableController(originalProperties);
		original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Problem = :problem"; //$NON-NLS-1$
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("problem"); //$NON-NLS-1$
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel#getPrimaryKeyFieldIndex()
			 */
			@Override
			protected int getPrimaryKeyFieldIndex() {
				return 0;
			}
		});
		addMasterModelListener(original);

		symptomService = (SymptomServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Symptom"); //$NON-NLS-1$
		symptomTable = new DefaultTableController(new DefaultEJBQLTableModel() { 
			private final String INIT_QUERY_TEXT = "select %s from Symptom s join s.LinkSymptomProblems ls %s where ls.Id.CrmProblem = :problem "; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("problem"); //$NON-NLS-1$
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					linkSymptomProblem = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					linkSymptomProblem = (Integer) row[0];
				}
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Symptom.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Symptom.class, "Creator", "p.Surname", "left join s.Creator.Person as p", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Symptom.class, "Name", "s.Name", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Symptom.class, "Info", "s.Info", true)); //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, symptomService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(symptomTable);

		solutionService = (SolutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Solution"); //$NON-NLS-1$
		solutionTable = new DefaultTableController(new DefaultEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Solution s join s.LinkProblemSolutions ls %s where ls.Id.CrmProblem = :problem "; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					linkSolutionProblem = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					linkSolutionProblem = (Integer) row[0];
				}
			}

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("problem"); //$NON-NLS-1$
				paramsValue.add(getEntity());					
				return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
			 */
			@Override
			protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
				Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
				result.add(new TableEJBQLFieldDef(Solution.class, "Id", "s.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Solution.class, "Creator", "p.Surname", "left join s.Creator.Person as p", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				result.add(new TableEJBQLFieldDef(Solution.class, "Name", "s.Name", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Solution.class, "Info", "s.Info", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Solution.class, "ValidTo", "s.ValidTo", false)); //$NON-NLS-1$ //$NON-NLS-2$
				result.add(new TableEJBQLFieldDef(Solution.class, "ValidFrom", "s.ValidFrom", false));				 //$NON-NLS-1$ //$NON-NLS-2$
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, solutionService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(solutionTable);			
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		originalProperties.put("Problem", event.getEntity());	 //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnView()
	 */
	@Override
	protected void doOnView() {
		super.doOnView();
		setPopupMenuEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnAdd()
	 */
	@Override
	protected void doOnAdd() {
		super.doOnAdd();
		setPopupMenuEnabled(false);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnSave()
	 */
	@Override
	protected void doOnSave() {
		super.doOnSave();
		setPopupMenuEnabled(true);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnEdit()
	 */
	@Override
	protected void doOnEdit() {
		super.doOnEdit();
		setPopupMenuEnabled(true);
	}

	/**
	 * Открыть/закрыть пункты контекстного меню
	 * @param enabled - признак
	 */
	private void setPopupMenuEnabled(boolean enabled) {
		PopupMenu symptomMenu = view.getWidget(WIGET_SYMPTOM_TABEL).getPopupMenu();
		symptomMenu.getMenuItem(WIGET_INCLUDE_SYMPTOM).setEnabled(enabled); 
		symptomMenu.getMenuItem(WIGET_EXCLUDE_SYMPTOM).setEnabled(enabled); 
		symptomMenu.getMenuItem(WIGET_EDIT_SYMPTOM).setEnabled(enabled);
		PopupMenu solutionMenu = view.getWidget(WIGET_SOLUTION_TABEL).getPopupMenu(); 
		solutionMenu.getMenuItem(WIGET_INCLUDE_SOLUTION).setEnabled(enabled); 
		solutionMenu.getMenuItem(WIGET_EXCLUDE_SOLUTION).setEnabled(enabled); 
		solutionMenu.getMenuItem(WIGET_EDIT_SOLUTION).setEnabled(enabled);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Добавить симптом"
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionIncludeSymptom(WidgetEvent event) throws ApplicationException {
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(symptomService, null);
		form.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Symptom symptom = ((Symptom) event.getItems()[0]);
				problemService.linkSymptom((Problem) getEntity(), symptom);
				symptomTable.getModel().load();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		form.run();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Удалить симптом"
	 * @param event - событие 
	 * @throws ApplicationException
	 */
	public void onActionExcludeSymptom(WidgetEvent event) throws ApplicationException {
		if (linkSymptomProblem == null)
			return;
		final Symptom symptom = (Symptom) ServerUtils.getPersistentManager().find(Symptom.class, linkSymptomProblem);
		problemService.unLinkSymptom((Problem) getEntity(), symptom);
		symptomTable.getModel().load();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Просмотреть" (симптом)
	 * @param event - событие 
	 * @throws ApplicationException
	 */
	public void onActionShowSymptom(WidgetEvent event) throws ApplicationException {
		if (linkSymptomProblem == null)
			return;
		final Symptom symptom = (Symptom) ServerUtils.getPersistentManager().find(Symptom.class, linkSymptomProblem);
		MaintenanceHelper.view(symptomService, symptom.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Изменить" (симптом)
	 * @param event - событие 
	 * @throws ApplicationException
	 */
	public void onActionEditSymptom(WidgetEvent event) throws ApplicationException {
		if (linkSymptomProblem == null)
			return;
		final Symptom symptom = (Symptom) ServerUtils.getPersistentManager().find(Symptom.class, linkSymptomProblem);
		MaintenanceHelper.edit(symptomService, symptom.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Добавить решение"
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionIncludeSolution(WidgetEvent event) throws ApplicationException {
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(solutionService, null);
		form.addSearchHelpListener(new SearchHelpListener() {
			public void searchPerformed(SearchHelpEvent event) {
				final Solution solution = ((Solution) event.getItems()[0]);
				problemService.linkSolution((Problem) getEntity(), solution);
				solutionTable.getModel().load();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		form.run();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Удалить решение"
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionExcludeSolution(WidgetEvent event) throws ApplicationException {
		if (linkSolutionProblem == null)
			return;
		final Solution solution = (Solution) ServerUtils.getPersistentManager().find(Solution.class, linkSolutionProblem);
		problemService.unLinkSolution((Problem) getEntity(), solution);
		solutionTable.getModel().load();
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Просмотреть" (решение)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionShowSolution(WidgetEvent event) throws ApplicationException {
		if (linkSolutionProblem == null)
			return;
		final Solution solution = (Solution) ServerUtils.getPersistentManager().find(Solution.class, linkSolutionProblem);
		MaintenanceHelper.view(solutionService, solution.getId(), null, null);
	}

	/**
	 * Обработка события выбора пункта контекстного меню "Изменить" (решение)
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionEditSolution(WidgetEvent event) throws ApplicationException {
		if (linkSolutionProblem == null)
			return;
		final Solution solution = (Solution) ServerUtils.getPersistentManager().find(Solution.class, linkSolutionProblem);
		MaintenanceHelper.edit(solutionService, solution.getId(), null, null);
	}

}
