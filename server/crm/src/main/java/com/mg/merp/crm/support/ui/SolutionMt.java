/*
 * ProblemMt.java
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
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpForm;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.api.ui.widget.MaintenanceTable;
import com.mg.framework.api.ui.widget.PopupMenu;
import com.mg.framework.generic.ui.DefaultMaintenanceEJBQLTableModel;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.LocalDataTransferObject;
import com.mg.framework.support.ui.widget.MaintenanceTableController;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.crm.LinkedDocumentServiceLocal;
import com.mg.merp.crm.ProblemServiceLocal;
import com.mg.merp.crm.SolutionServiceLocal;
import com.mg.merp.crm.model.Problem;
import com.mg.merp.crm.model.Solution;

/**
 * Контроллер формы поддержки решений
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: SolutionMt.java,v 1.7 2007/09/11 12:49:12 alikaev Exp $
 */
public class SolutionMt extends DefaultMaintenanceForm implements MasterModelListener {

	private static final String WIGET_PROBLEM_TABEL = "problem"; //$NON-NLS-1$
	private static final String WIGET_INCLUDE_PROBLEM = "includeProblem"; //$NON-NLS-1$
	private static final String WIGET_EXCLUDE_PROBLEM = "excludeProblem"; //$NON-NLS-1$
	
	private MaintenanceTableController original;
	private LinkedDocumentServiceLocal originalService;
	protected AttributeMap originalProperties = new LocalDataTransferObject();
	private SolutionServiceLocal solutionService = (SolutionServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Solution"); //$NON-NLS-1$

	private MaintenanceTableController problem;
	private ProblemServiceLocal problemService;
	protected AttributeMap problemProperties = new LocalDataTransferObject();
	private Integer linkProblemSolutionId;


	public SolutionMt()throws Exception{
		addMasterModelListener(this);
		setMasterDetail(true);
		
		originalService = (LinkedDocumentServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/LinkedDocument"); //$NON-NLS-1$
		original = new MaintenanceTableController(originalProperties);
		original.initController(originalService, new LinkedDocumentMaintenanceEJBQLTableModel() {
			protected final String INIT_QUERY_TEXT = "select %s from LinkedDocument ld %s where ld.Solution = :solution"; //$NON-NLS-1$
			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();
				paramsName.add("solution"); //$NON-NLS-1$
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
		
		problemService = (ProblemServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/crm/Problem"); //$NON-NLS-1$
		problem = new MaintenanceTableController(problemProperties);
		problem.initController(problemService, new DefaultMaintenanceEJBQLTableModel() {
			private final String INIT_QUERY_TEXT = "select %s from Problem p join p.LinkProblemSolutions lp %s where lp.Id.CrmSolution = :solution "; //$NON-NLS-1$
			private List<String> paramsName = new ArrayList<String>();
			private List<Object> paramsValue = new ArrayList<Object>();
			
			/*
			 * (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
			 */
			@Override
			public void setSelectedRows(int[] rows) {
				if (rows.length == 0)
					linkProblemSolutionId = null;
				else {
					Object[] row = getRowList().get(rows[0]);
					linkProblemSolutionId = (Integer) row[0];
				}
				super.setSelectedRows(rows);
			}

			protected String createQueryText() {
				Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
				String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
				String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
				paramsName.clear();
				paramsValue.clear();	
				paramsName.add("solution"); //$NON-NLS-1$
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
				return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, problemService);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
			 */
			@Override
			protected void doLoad() {
				setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
			}
		});
		addMasterModelListener(problem);			
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		originalProperties.put("Solution", event.getEntity()); //$NON-NLS-1$
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */	
	@Override
	protected void doOnRun() {
		super.doOnRun();
		PopupMenu problemMenu = view.getWidget(WIGET_PROBLEM_TABEL).getPopupMenu();		
		problemMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setVisible(false);
		problemMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setVisible(false);
		problemMenu.getMenuItem(MaintenanceTable.CLONE_MENU_ITEM).setEnabled(false);
		problemMenu.getMenuItem(MaintenanceTable.DEEP_CLONE_MENU_ITEM).setEnabled(false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doSetDependentReadOnly(boolean)
	 */
	@Override
	protected void doSetDependentReadOnly(boolean readOnly) {
		super.doSetDependentReadOnly(readOnly);
		PopupMenu problemMenu = view.getWidget(WIGET_PROBLEM_TABEL).getPopupMenu();		
		problemMenu.getMenuItem(MaintenanceTable.ADD_MENU_ITEM).setEnabled(false);
		problemMenu.getMenuItem(MaintenanceTable.ERASE_MENU_ITEM).setEnabled(false);
		problemMenu.getMenuItem(WIGET_INCLUDE_PROBLEM).setEnabled(!readOnly); 
		problemMenu.getMenuItem(WIGET_EXCLUDE_PROBLEM).setEnabled(!readOnly); 
	}
		
	/**
	 * Обработка события выбора пункта контекстного меню "Выбрать проблему"
	 * 
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionIncludeProblem(WidgetEvent event) throws ApplicationException {
		SearchHelpForm form = (SearchHelpForm) ApplicationDictionaryLocator.locate().getBrowseForm(problemService, null);
		form.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Problem problemAdd = ((Problem) event.getItems()[0]);
				Solution solution = (Solution)getEntity();
				solutionService.linkProblem(problemAdd, solution);
				problem.getModel().load();
			}

			public void searchCanceled(SearchHelpEvent event) {
				//do nothing
			}
		});
		form.run();
	}
	
	/**
	 * Обработка события выбора пункта контекстного меню "Убрать проблему"
	 * 
	 * @param event - событие
	 * @throws ApplicationException
	 */
	public void onActionExcludeProblem(WidgetEvent event) throws ApplicationException {
		if (linkProblemSolutionId == null)
			return;
		final Problem problemUnlink = (Problem) ServerUtils.getPersistentManager().find(Problem.class, linkProblemSolutionId);
		solutionService.unLinkProblem(problemUnlink, (Solution) getEntity());
		problem.getModel().load();
	}

}
