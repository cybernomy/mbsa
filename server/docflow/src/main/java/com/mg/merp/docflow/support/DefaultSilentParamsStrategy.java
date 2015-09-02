/*
 * DefaultSilentParamsStrategy.java
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
package com.mg.merp.docflow.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.mg.merp.docflow.ChooseDocumentPatternListener;
import com.mg.merp.docflow.ChooseDocumentStateListener;
import com.mg.merp.docflow.ChooseFolderListener;
import com.mg.merp.docflow.ChooseNextStageListener;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.docflow.InputDocumentParamsListener;
import com.mg.merp.docflow.SilentException;
import com.mg.merp.docflow.generic.AbstractParamsStrategy;
import com.mg.merp.docprocess.model.DocHeadState;
import com.mg.merp.docprocess.model.DocProcessStage;
import com.mg.merp.document.model.DocHead;

/**
 * Реализация стратегии ДО для"тихого" выполнения, в случае если требуется взаимодействие с пользователем
 * будет сгенерирована ИС {@link SilentException}
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultSilentParamsStrategy.java,v 1.1 2006/12/12 15:24:36 safonov Exp $
 */
public class DefaultSilentParamsStrategy extends AbstractParamsStrategy {

	private void silentError() {
		throw new SilentException();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDestanationFolder(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseFolderListener)
	 */
	public void chooseDestanationFolder(Date processDate,
			DocProcessStage performedStage, BigDecimal docSum,
			List<DocumentSpecItem> specList, ChooseFolderListener listener) {
		silentError();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocHeadState(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.docprocess.model.DocAction, java.util.List, java.util.List, com.mg.merp.docflow.ChooseDocumentStateListener)
	 */
	public void chooseDocHeadState(Date processDate,
			DocProcessStage performedStage,
			List<DocHeadState> docHeadStates, List<DocumentSpecItem> specList,
			ChooseDocumentStateListener listener) {
		if (docHeadStates.size() > 1)
			silentError();
		else
			listener.performed(docHeadStates.get(0).getId());
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseDocumentPattern(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, com.mg.merp.core.model.Folder, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.ChooseDocumentPatternListener)
	 */
	public void chooseDocumentPattern(Date processDate,
			DocProcessStage performedStage, BigDecimal docSum,
			List<DocumentSpecItem> specList,
			ChooseDocumentPatternListener listener) {
		silentError();
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#chooseNextStage(com.mg.merp.document.model.DocHead, java.util.Date, java.util.List, com.mg.merp.docflow.ChooseNextStageListener)
	 */
	public void chooseNextStage(DocHead docHead, Date processDate,
			List<DocProcessStage> stages, ChooseNextStageListener listener) {
		if (stages.size() > 1)
			silentError();
		else
			listener.performed(processDate, stages.get(0));
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSpecList(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, java.util.List, com.mg.merp.docflow.InputDocumentParamsListener)
	 */
	public void inputDocumentSpecList(Date processDate,
			DocProcessStage performedStage, BigDecimal docSum,
			List<DocumentSpecItem> specList,
			InputDocumentParamsListener listener) {
		if (performedStage.isPartial())
			silentError();
		else
			silentPrepareDocumentSpecList(docSum, specList, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowParamsStrategy#inputDocumentSum(java.util.Date, com.mg.merp.docprocess.model.DocProcessStage, java.math.BigDecimal, com.mg.merp.docflow.InputDocumentParamsListener)
	 */
	public void inputDocumentSum(Date processDate,
			DocProcessStage performedStage, BigDecimal docSum,
			InputDocumentParamsListener listener) {
		if (performedStage.isPartial())
			silentError();
		else
			listener.performed(docSum, null);
	}

}
