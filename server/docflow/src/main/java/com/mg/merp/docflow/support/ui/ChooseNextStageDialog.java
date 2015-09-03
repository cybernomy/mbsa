/*
 * ChooseNextStageDialog.java
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
package com.mg.merp.docflow.support.ui;

import java.util.Date;
import java.util.List;

import com.mg.framework.generic.ui.DefaultEntityListTableModel;
import com.mg.framework.generic.ui.DefaultWizardDialog;
import com.mg.framework.support.ui.widget.DefaultTableController;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.docprocess.model.DocProcessStage;

/**
 * Контроллер формы выбора этапа ДО для отработки и отката
 * 
 * @author Oleg V. Safonov
 * @version $Id: ChooseNextStageDialog.java,v 1.6 2007/10/27 08:22:04 safonov Exp $
 */
public class ChooseNextStageDialog extends DefaultWizardDialog {
	private Date processDate;
	private DefaultTableController stagesList;
	
	public ChooseNextStageDialog() {
		stagesList = new DefaultTableController(new NextStageTableModel());
	}
	
	private class NextStageTableModel extends DefaultEntityListTableModel<DocProcessStage> {
		/* (non-Javadoc)
		 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getValueAt(int, int)
		 */
		@Override
		public Object getDecoratorValueAt(int row, int column) {
			DocProcessStage st = getEntityList().get(row);
			String stdName = st.getStage().getName();
			String result = st.getName();
			//если есть название этапа то используем, иначе берем стандартное
			return StringUtils.stringNullOrEmpty(result) ? stdName : String.format("%s (%s)", result, stdName); //$NON-NLS-1$
		}

	}
	
	/**
	 * установить дату отработки документа
	 * 
	 * @param processDate	дата отработки
	 */
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
	/**
	 * получить дату отработки документа
	 * 
	 * @return	дата отработки
	 */
	public Date getProcessDate() {
		return processDate;
	}
	
	/**
	 * установить список этапов ДО доступных для отработки
	 * 
	 * @param stages	список этапов
	 */
	public void setStages(List<DocProcessStage> stages) {
		((NextStageTableModel) stagesList.getModel()).setEntityList(stages, new String[] {"Stage"}); //$NON-NLS-1$
	}
	
	/**
	 * получить этап выбранный пользователем для отработки
	 * 
	 * @return	этап для отработки
	 */
	public DocProcessStage getStage() {
		DocProcessStage[] stages = ((NextStageTableModel) stagesList.getModel()).getSelectedEntities();
		if (stages.length == 0)
			return null;
		else
			return stages[0];
	}
}
