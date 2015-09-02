/**
 * HibernateDataSourceWizard.java
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
package com.mg.jet.birt.report.data.oda.ejbql.ui;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Мастер создания источника данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernateDataSourceWizard.java,v 1.1 2007/10/29 08:54:16 safonov Exp $
 */
public class HibernateDataSourceWizard extends DataSourceWizardPage {
	private HibernatePageHelper m_pageHelper;
	private Properties m_hibernateProperties;

	public HibernateDataSourceWizard( String pageName ) {
		super(pageName);
		setMessage(Messages.getString("wizard.message"));
	}


	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		if( m_pageHelper == null )
			m_pageHelper = new HibernatePageHelper( this );
		m_pageHelper.createCustomControl( parent );
		m_pageHelper.initCustomControl( m_hibernateProperties );   // in case init was called before create 

		/* 
		 * Optionally hides the Test Connection button, using
		 *      setPingButtonVisible( false );  
		 */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#initPageCustomControl(java.util.Properties)
	 */
	public void setInitialProperties( Properties dataSourceProps )
	{
		m_hibernateProperties = dataSourceProps;
		if( m_pageHelper == null )
			return;     // ignore, wait till createPageCustomControl to initialize
		m_pageHelper.initCustomControl( m_hibernateProperties );        
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#collectCustomProperties()
	 */
	public Properties collectCustomProperties()
	{
		/* 
		 * Optionally assign a custom designer state, for inclusion
		 * in the ODA design session response, using
		 * setResponseDesignerState( DesignerState customState ); 
		 */

		if( m_pageHelper != null ) 
			return m_pageHelper.collectCustomProperties( m_hibernateProperties );

		return ( m_hibernateProperties != null ) ? m_hibernateProperties : new Properties();
	}

}
