/*
 * BAiDocFlowPluginFactory.java
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
package com.mg.merp.docflow.support;

import java.util.HashMap;
import java.util.Map;

import com.mg.merp.baiengine.BusinessAddinEngineLocator;
import com.mg.merp.baiengine.BusinessAddinEvent;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.docflow.BAiDocFlowPluginViewer;
import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;
import com.mg.merp.docprocess.model.DocHeadState;

/**
 * Реализация фабрики реализаций этапа ДО "Бизнес расширение"
 * 
 * @author Oleg V. Safonov
 * @version $Id: BAiDocFlowPluginFactory.java,v 1.5 2007/09/27 09:21:55 safonov Exp $
 */
public class BAiDocFlowPluginFactory extends AbstractDocFlowPluginFactory {
	public final static int FACTORY_IDENTIFIER = 34;

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactory#getIdentifier()
	 */
	public int getIdentifier() {
		return FACTORY_IDENTIFIER;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactory#getName()
	 */
	public String getName() {
		return "Business Add-in";
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactory#createPlugin()
	 */
	protected DocFlowPlugin doCreatePlugin() {
		return new AbstractDocFlowPlugin() {

			private Map<String, DocFlowPluginInvokeParams> createParams() {
				Map<String, DocFlowPluginInvokeParams> result = new HashMap<String, DocFlowPluginInvokeParams>();
				result.put(DocFlowBusinessAddin.DOCFLOW_PARAMS_NAME, getParams());
				return result;
			}
			
			@Override
			protected void doExecute() throws Exception {
				BusinessAddinEngineLocator.locate().perform(getParams().getPerformedStage().getPerformBusinessAddin(), createParams(), new BusinessAddinListener<Void>() {

					public void completed(BusinessAddinEvent<Void> event) {
						complete();
					}

					public void aborted(BusinessAddinEvent<Void> event) {
						cancel();
					}
					
				});
			}

			@Override
			protected void doRoolback() throws Exception {
				BusinessAddinEngineLocator.locate().perform(getParams().getPerformedStage().getRollbackBusinessAddin(), createParams(), new BusinessAddinListener<Void>() {

					public void completed(BusinessAddinEvent<Void> event) {
						complete();
					}

					public void aborted(BusinessAddinEvent<Void> event) {
						cancel();
					}

				});
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doGetDocActionResultTextRepresentation(com.mg.merp.docprocess.model.DocHeadState)
			 */
			@Override
			protected String doGetDocActionResultTextRepresentation(
					DocHeadState docHeadState) {
				Object bai = BusinessAddinEngineLocator.locate().createBusinessAddin(docHeadState.getDocAction().getStage().getPerformBusinessAddin());
				if (bai instanceof BAiDocFlowPluginViewer)
					return ((BAiDocFlowPluginViewer) bai).getDocActionResultTextRepresentation(docHeadState);
				else
					return super.doGetDocActionResultTextRepresentation(docHeadState);
			}

			/* (non-Javadoc)
			 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doShowDocActionResult(com.mg.merp.docprocess.model.DocHeadState)
			 */
			@Override
			protected void doShowDocActionResult(DocHeadState docHeadState) {
				Object bai = BusinessAddinEngineLocator.locate().createBusinessAddin(docHeadState.getDocAction().getStage().getPerformBusinessAddin());
				if (bai instanceof BAiDocFlowPluginViewer)
					((BAiDocFlowPluginViewer) bai).showDocActionResult(docHeadState);
				else
					super.doShowDocActionResult(docHeadState);
			}
			
		};
	}

}
