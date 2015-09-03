/**
 * HibernatePropertyPage.java
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

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Страница свойств
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernatePropertyPage.java,v 1.1 2007/10/29 08:54:16 safonov Exp $
 */
public class HibernatePropertyPage extends DataSourceEditorPage
{
 
    private HibernatePageHelper m_pageHelper;

    public HibernatePropertyPage()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties profileProps )
    {
        /* 
         * Optionally assigns a custom designer state, for inclusion
         * in the ODA design session response, using
         *      setResponseDesignerState( DesignerState customState ); 
         */

        if( m_pageHelper == null )
            return profileProps;

        return m_pageHelper.collectCustomProperties( profileProps );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite, java.util.Properties)
     */
    protected void createAndInitCustomControl( Composite parent, Properties profileProps )
    {
        if( m_pageHelper == null )
            m_pageHelper = new HibernatePageHelper( this );

        m_pageHelper.createCustomControl( parent );

        /* 
         * Optionally hides the Test Connection button, using
         *      setPingButtonVisible( false );  
         */

        /* 
         * Optionally restores the state of a previous design session.
         * Obtains designer state, using
         *      getInitializationDesignerState(); 
         */
        m_pageHelper.initCustomControl( profileProps );
        
        if( ! isSessionEditable() )
            getControl().setEnabled( false );
    }
    
    
}
