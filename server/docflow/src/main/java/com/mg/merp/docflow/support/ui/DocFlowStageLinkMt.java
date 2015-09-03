/*
 * DocFlowStageLinkMt.java
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
package com.mg.merp.docflow.support.ui;

import com.mg.framework.generic.ui.DefaultDialog;
import com.mg.merp.docprocess.model.LinkStage;

/**
 * Контроллер формы редактирования связи этапов ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowStageLinkMt.java,v 1.2 2006/08/25 11:48:17 safonov Exp $
 */
public class DocFlowStageLinkMt extends DefaultDialog {
	protected String sourceInternalName;
	protected String sourceName;
	protected String targetInternalName;
	protected String targetName;
	private boolean directly;

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		view.pack();
		super.doOnRun();
	}

	/**
	 * @return Returns the directly.
	 */
	public boolean isDirectly() {
		return directly;
	}

	/**
	 * установка модели
	 * 
	 * @param link	модель
	 */
	public void setLink(LinkStage link) {
		directly = link.isDirectly();
		sourceInternalName = link.getPrevStage().getStage().getName();
		sourceName = link.getPrevStage().getName();
		targetInternalName = link.getNextStage().getStage().getName();
		targetName = link.getNextStage().getName();
	}
}
