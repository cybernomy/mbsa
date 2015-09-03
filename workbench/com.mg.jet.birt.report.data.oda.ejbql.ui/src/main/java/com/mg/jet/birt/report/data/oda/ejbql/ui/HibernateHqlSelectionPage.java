/**
 * HibernateHqlSelectionPage.java
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


import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.mg.jet.birt.report.data.oda.ejbql.Connection;
import com.mg.jet.birt.report.data.oda.ejbql.HibernateDriver;

/**
 * Страница EJBQL запроса
 * 
 * @author Oleg V. Safonov
 * @version $Id: HibernateHqlSelectionPage.java,v 1.1 2007/10/29 08:54:16 safonov Exp $
 */
public class HibernateHqlSelectionPage extends DataSetWizardPage 
{
    private transient Text queryText = null;
    

    //private transient DataSetHandle dataSetHandle = null;
    private transient Button queryButton = null;
    private boolean m_initialized = false;
    private String  m_hibconfig;
    private String m_mapdir;
    private String jndiName;

    private static String DEFAULT_MESSAGE = Messages.getString("wizard.defaultMessage.selectHibernateClass"); //$NON-NLS-1$
    //private transient IPropertyPageContainer propertyContainer = null;

    /**
     * Default constructor
     */
    public HibernateHqlSelectionPage()
    {
        this(Messages.getString("wizard.title.hql")); //$NON-NLS-1$
    }

    /**
     * @param pageName
     */
    public HibernateHqlSelectionPage(String pageName)
    {
        super(pageName);
        setTitle(pageName);
        setMessage(DEFAULT_MESSAGE);

        
    }

    
    public HibernateHqlSelectionPage( String pageName, String title,
            ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }    
    
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
    {
        if( ! hasValidData() )
            return design;
        design.setQueryText( queryText.getText() );
        savePage( design );
        return design;
    }    
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPage#collectResponseState()
     */
    protected void collectResponseState()
    {        
        super.collectResponseState();
        /* 
         * Optionally assigns custom response state, for inclusion
         * in the ODA design session response, using
         *      setResponseSessionStatus( SessionStatus status )
         *      setResponseDesignerState( DesignerState customState ); 
         */
    }    
    

  
    private boolean hasValidData()
    {
        if( queryText == null )
            return false;

        if( isPageComplete() )
        {
            return true;
        }
        setMessage( "Error Reading Query" ); 
        return false;
    }
  
    
    public void createPageCustomControl( Composite parent )
    {
        setControl( createPageControl( parent ) );
        initializeControl();
    }

    private void initializeControl()
    {
    	
    	
        Properties dataSourceProps = getInitializationDesign().getDataSourceDesign().getPublicProperties();
        

        m_hibconfig = dataSourceProps.getProperty("HIBCONFIG" );
        m_mapdir = dataSourceProps.getProperty("MAPDIR" );
        jndiName = dataSourceProps.getProperty("JNDI_NAME");
          	
        /* 
         * Optionally restores the state of a previous design session.
         * Obtains designer state, using
         *      getInitializationDesignerState(); 
         */

        DataSetDesign dataSetDesign = getInitializationDesign();
        if( dataSetDesign == null )
            return; // nothing to initialize

        String queryTextTmp = dataSetDesign.getQueryText();
        if( queryTextTmp == null )
            return; // nothing to initialize

        queryText.setText(queryTextTmp);
        this.m_initialized = false;
       	setMessage( "", NONE );
       

    } 


    /*
	 * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#createPageControl(org.eclipse.swt.widgets.Composite)
	 */
    public Control createPageControl(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        composite.setLayout(layout);
        
        
		Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.getString("wizard.title.selectColumns")); //$NON-NLS-1$
        
        GridData data = new GridData(GridData.FILL_BOTH);


        queryText = new Text(composite,SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        queryText.setLayoutData(data);
        queryText.addModifyListener(new ModifyListener(){

            public void modifyText(ModifyEvent e)
            {
            	if( m_initialized == false){
            		setPageComplete(true);
            		m_initialized = true;            		
            	}else{
            		setPageComplete(false);
            	}
            }
        });
        
        setPageComplete(true);
  
		Composite cBottom = new Composite( composite, SWT.NONE );
		cBottom.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
		cBottom.setLayout( new RowLayout( ) );
        
        queryButton = new Button(cBottom, SWT.NONE);
        //queryButton.setSize(50,10);
		queryButton.setText(Messages.getString("wizard.title.verify"));//$NON-NLS-1$
		//queryButton.setLayoutData(cBottom);

		
		// Add listener to the find button
		queryButton.addSelectionListener(new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
					verifyQuery();
			   }
			 });        

        return composite;
    }

    boolean verifyQuery(){

    	setMessage( "Verifying Query", INFORMATION );
		setPageComplete( false );
		queryButton.setEnabled(false);
		
		//check JNDI URL
		/*if (jndiName != null && jndiName.trim().length() > 0) {
			String ejbqlText = queryText.getText();
			if (ejbqlText != null && ejbqlText.trim().length() > 0) {
				setPageComplete(true);
				setMessage( "Query Verified", INFORMATION );
				return true;
			}
		}*/
		
    	//Makes a connection to the ODA runtime
    	Connection conn = new Connection( );
		try	{
	        java.util.Properties prop = new java.util.Properties();
	        if( m_hibconfig == null)m_hibconfig = "";
	        if( m_mapdir == null)m_mapdir = "";
	        if (jndiName == null) jndiName = "";
	        
	        prop.put( "HIBCONFIG", m_hibconfig );
	        prop.put( "MAPDIR", m_mapdir );
	        prop.put("JNDI_NAME", jndiName);
			        
	        
			conn.open( prop );
			IQuery query = conn.newQuery( "" );
			//does not actually run the query, just uses Hibernate to prepare metadata
			query.prepare( queryText.getText() );

			int columnCount = query.getMetaData( ).getColumnCount( );
			System.out.println("columnCount =" + columnCount);

			if ( columnCount == 0 ){
				setMessage( "No Columns Seclected", INFORMATION );
				setPageComplete(false);
				return false;
			}
			setPageComplete(true);
		   	setMessage( "Query Verified", INFORMATION );

			return true;
		}
		catch ( OdaException e )
		{
			System.out.println( e.getMessage());
			setMessage( e.getLocalizedMessage( ), ERROR );
			
			setPageComplete(false);
			return false;
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage());
			setMessage( e.getLocalizedMessage( ), ERROR );
			setPageComplete(false);
			return false;
		}

		finally
		{
			try
			{
				queryButton.setEnabled(true);
				conn.close( );
			}
			catch ( OdaException e )
			{
				System.out.println( e.getMessage());
				setMessage( e.getLocalizedMessage( ), ERROR );
				setPageComplete(false);
				return false;
			}

		}

 	
    }

 

    /*
     * @see org.eclipse.birt.report.designer.ui.dialogs.properties.IPropertyPage#canLeave()
     * overridden from super
     */
    public boolean canLeave()
    {
        if(!isPageComplete())
        {
            setMessage(Messages.getString("error.selectColumns"), ERROR); 
            return false;
        }
        return true;
    }


    
    
    
    private void savePage( DataSetDesign dataSetDesign )
    {



        // obtain query's result set metadata, and update
        // the dataSetDesign with it
        IConnection conn = null;
        try
        {
            IDriver hqDriver = new HibernateDriver();
            conn = hqDriver.getConnection( null );
            IResultSetMetaData metadata = 
                getResultSetMetaData( dataSetDesign.getQueryText(), conn );
            setResultSetMetaData( dataSetDesign, metadata );
        }
        catch( OdaException e )
        {
            // no result set definition available, reset in dataSetDesign
            dataSetDesign.setResultSets( null );
        }
        finally
        {
            closeConnection( conn );
        }
        
        /*
         * See DesignSessionUtil for more convenience methods
         * to define a data set design instance.  
         */     

        /*
         * Since this flatfile driver does not support
         * query parameters and properties, there are
         * no data set parameters and public/private properties
         * to specify in the data set design instance
         */
    }    
    private void closeConnection( IConnection conn )
    {
        try
        {
            if( conn != null )
                conn.close();
        }
        catch( OdaException e )
        {
            // ignore
        }
    }    
    private IResultSetMetaData getResultSetMetaData( 
            String queryText,
            IConnection conn ) throws OdaException
    {
 
        java.util.Properties prop = new java.util.Properties();
        if( m_hibconfig == null)m_hibconfig = "";
        if( m_mapdir == null)m_mapdir = "";
        if (jndiName == null) jndiName = "";
        prop.put( "HIBCONFIG", m_hibconfig );
        prop.put( "MAPDIR", m_mapdir );		
        prop.put("JNDI_NAME", jndiName);
		        
        
		conn.open( prop );
		IQuery query = conn.newQuery( null );

		//Do not need to run query just prepare it.
		query.prepare( queryText );
	

		return query.getMetaData();
	}    
    
    private void setResultSetMetaData( DataSetDesign dataSetDesign,
            IResultSetMetaData md ) throws OdaException
    {
        ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign( md );

        ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE
                .createResultSetDefinition();
        
        resultSetDefn.setResultSetColumns( columns );

        // no exception; go ahead and assign to specified dataSetDesign
        dataSetDesign.setPrimaryResultSet( resultSetDefn );
        dataSetDesign.getResultSets().setDerivedMetaData( true );
    }   

}


