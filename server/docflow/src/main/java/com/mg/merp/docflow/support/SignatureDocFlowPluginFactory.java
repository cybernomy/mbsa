/*
 * SignatureDocFlowPluginFactory.java
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

import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPlugin;
import com.mg.merp.docflow.generic.AbstractDocFlowPluginFactory;

/**
 * Реализация фабрики реализаций этапа ДО "Электронная подпись"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SignatureDocFlowPluginFactory.java,v 1.2 2006/10/21 10:50:19 safonov Exp $
 */
public class SignatureDocFlowPluginFactory extends AbstractDocFlowPluginFactory {
	public final static int FACTORY_IDENTIFIER = 20;

	class SignatureDocFlowPlugin extends AbstractDocFlowPlugin {

		/* (non-Javadoc)
		 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doExecute()
		 */
		@Override
		protected void doExecute() {
			complete();//very simple implementation
		}

		/* (non-Javadoc)
		 * @see com.mg.merp.docflow.generic.AbstractDocFlowPlugin#doRoolback()
		 */
		@Override
		protected void doRoolback() {
			complete();//very simple implementation
		}

	}

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
		return "Signature";
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.docflow.DocFlowPluginFactory#createPlugin()
	 */
	protected DocFlowPlugin doCreatePlugin() {
		return new SignatureDocFlowPlugin();
	}
	
}