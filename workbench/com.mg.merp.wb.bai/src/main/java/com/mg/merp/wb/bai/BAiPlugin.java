/* BAiPlugin.java
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
package com.mg.merp.wb.bai;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.osgi.framework.BundleContext;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.wb.bai.ui.BAiView;
import com.mg.merp.wb.core.ui.plugin.BusinessObjectPlugin;

/**
 * @author Valentin A. Poroxnenko
 * @version $$Id: BAiPlugin.java,v 1.5 2007/05/08 08:48:28 poroxnenko Exp $$
 */
public class BAiPlugin extends BusinessObjectPlugin<Repository> {

	private static final String RESOURCE_NAME = "com.mg.merp.wb.bai.messages";

	private static final String BAI_SERVICE_NAME = "merp:baiengine=BusinessAddinWorkbenchService";

	public static final String CHECK_SERVER_MESSAGE = "server.check.message";

	// The plug-in ID
	public static final String ID = "com.mg.merp.wb.bai";

	// The shared instance
	private static BAiPlugin plugin;

	private static IType businessAddinType = null;

	private static final String BAI_TYPE_NAME = "com.mg.merp.baiengine.BusinessAddin";

	/**
	 * The constructor
	 */
	public BAiPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
		businessAddinType = getBAiAsType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static BAiPlugin getDefault() {
		return plugin;
	}

	private static IType getBAiAsType() {
		SearchPattern pattern = SearchPattern.createPattern(BAI_TYPE_NAME,
				IJavaSearchConstants.TYPE, IJavaSearchConstants.DECLARATIONS,
				SearchPattern.R_EQUIVALENT_MATCH);
		IJavaSearchScope scope = SearchEngine.createWorkspaceScope();
		class BAiTypeSearchRequestor extends SearchRequestor {

			private IType baiType = null;

			@Override
			public void acceptSearchMatch(SearchMatch match)
					throws CoreException {
				baiType = (IType) match.getElement();
			}

			/**
			 * 
			 * @return последний из найденных(AbstractBusinessAddin может
			 *         находится в проекте, в библиотеке, внешнем архиве и т.д.)
			 */
			public IType getBaiType() {
				return baiType;
			}

		}
		BAiTypeSearchRequestor requestor = new BAiTypeSearchRequestor();

		SearchEngine searchEngine = new SearchEngine();
		try {
			searchEngine.search(pattern, new SearchParticipant[] {SearchEngine
					.getDefaultSearchParticipant()}, scope, requestor, null);

		} catch (CoreException e) {
			return null;
		}

		return requestor.getBaiType();
	}

	public static IType getBusinessAddinType() {
		return businessAddinType != null ? businessAddinType : (businessAddinType = getBAiAsType());
	}

	public Repository addBusinessObject(Repository bo) throws Exception {
		return (Repository) invoke(
				BAI_SERVICE_NAME,
				"addBai", new Object[] {bo}, new String[] {PersistentObject.class.getName()}); //$NON-NLS-1$
	}

	public Repository editBusinessObject(Repository bo) throws Exception {
		return (Repository) invoke(
				BAI_SERVICE_NAME,
				"editBai", new Object[] {bo}, new String[] {PersistentObject.class.getName()}); //$NON-NLS-1$
	}

	public Repository[] synchronize(String query) throws Exception {
		return (Repository[]) invoke(
				BAI_SERVICE_NAME,
				"getBais", new Object[] {query}, new String[] {String.class.getName()}); //$NON-NLS-1$
	}

	public void deleteBusinessObjectsList(Integer[] ids) throws Exception {
		invoke(BAI_SERVICE_NAME,
				"deleteBaiList", new Object[] {ids}, new String[] {Integer[].class.getName()}); //$NON-NLS-1$
	}

	@Override
	public String getViewId() {
		return BAiView.ID;
	}
}
