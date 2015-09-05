/**
 * HibernateUtil.java
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
package com.mg.jet.birt.report.data.oda.ejbql;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.eclipse.birt.report.data.oda.jdbc.OdaJdbcDriver;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.osgi.framework.Bundle;

/**
 * Утилиты хранилища данных
 *
 * @author Oleg V. Safonov
 * @version $Id: HibernateUtil.java,v 1.6 2009/03/04 10:15:25 safonov Exp $
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());
	//private static Object configuration=null;

	private static String HibernateConfigFile = "";
	private static String HibernateMapDirectory = "";
	private static ClassLoader oldloader=null;
	private static List<String> FileList = new ArrayList<String>();
	public static List<URL> URLList = new ArrayList<URL>();
    public static ClassLoader changeLoader;
    public static ClassLoader pluginLoader;


	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	private static synchronized void initSessionFactory(String hibfile, String mapdir, String jndiName) throws HibernateException {
		//ClassLoader cl1;

		if( sessionFactory == null) {

			if (jndiName == null || jndiName.trim().length() == 0)
				jndiName = CommonConstant.DEFAULT_JNDI_URL;
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				sessionFactory = (SessionFactory) initCtx.lookup(jndiName);
				return;
			} catch (Exception e) {
				logger.log(Level.INFO, "Unable to get JNDI data source connection", e);
			} finally {
				if (initCtx != null)
					try {
						initCtx.close();
					} catch (NamingException e) {
						//ignore
					}
			}

			Thread thread = Thread.currentThread();
			try{
				//Class.forName("org.hibernate.Configuration");
				//Configuration ffff = new Configuration();
				//Class.forName("org.apache.commons.logging.LogFactory");

				oldloader = thread.getContextClassLoader();
				//Class thwy = oldloader.loadClass("org.hibernate.cfg.Configuration");
				//Class thwy2 = oldloader.loadClass("org.apache.commons.logging.LogFactory");
				//refreshURLs();
				//ClassLoader changeLoader = new URLClassLoader( (URL [])URLList.toArray(new URL[0]),HibernateUtil.class.getClassLoader());
				ClassLoader testLoader = new URLClassLoader( (URL [])URLList.toArray(new URL[0]),pluginLoader);
				//changeLoader = new URLClassLoader( (URL [])URLList.toArray(new URL[0]));

				thread.setContextClassLoader(testLoader);
				//Class thwy2 = changeLoader.loadClass("org.hibernate.cfg.Configuration");
				//Class.forName("org.apache.commons.logging.LogFactory", true, changeLoader);
				//Class cls = Class.forName("org.hibernate.cfg.Configuration", true, changeLoader);
				//Configuration cfg=null;
				//cfg = new Configuration();
				//Object oo = cls.newInstance();
				//Configuration cfg = (Configuration)oo;
				Configuration cfg = new Configuration();
				buildConfig(hibfile,mapdir, cfg);


				Class<? extends Driver> driverClass = testLoader.loadClass(cfg.getProperty("connection.driver_class")).asSubclass(Driver.class);
				Driver driver = driverClass.newInstance();
				WrappedDriver wd = new WrappedDriver( driver, cfg.getProperty("connection.driver_class"));

				boolean foundDriver = false;
				Enumeration<Driver> drivers = DriverManager.getDrivers();
				while (drivers.hasMoreElements()) {
					Driver nextDriver = (Driver)drivers.nextElement();
					if (nextDriver.getClass() == wd.getClass()) {
						if( nextDriver.toString().equals(wd.toString()) ){
							foundDriver = true;
							break;
						}
					}
				}
				if( !foundDriver ){

					DriverManager.registerDriver( wd  ) ;
				}



				sessionFactory = cfg.buildSessionFactory();
				//configuration = cfg;
				HibernateMapDirectory = mapdir;
				HibernateConfigFile = hibfile;
			} catch (Throwable e) {
				e.printStackTrace();
				throw new HibernateException( "No Session Factory Created "  +  e.getLocalizedMessage(), e);
			}
			finally{
				thread.setContextClassLoader(oldloader);
			}
		}
	}

	public static boolean isSessionFactoryValid() {
		if( sessionFactory != null){
			return( true );
		}
		return( false );
	}

	public static synchronized void buildConfig(String hibfile, String mapdir, Configuration cfg ) throws HibernateException, IOException, Exception {
		Bundle hibbundle = Platform.getBundle( "com.mg.jet.birt.report.data.oda.ejbql" );

		//пытаемся загрузить мапинги из указанного места
		File cfgDir = new File(mapdir);
		if( cfgDir != null) {
			if (cfgDir.isDirectory())
				cfg.addDirectory(cfgDir);
			else if (cfgDir.length() > 0)
				cfg.addJar(cfgDir);
		}

		URL hibfiles = FileLocator.find(hibbundle, new Path(CommonConstant.HIBERNATE_CLASSES), null);
		URL hibURL = FileLocator.resolve(hibfiles);
		File hibDirectory = new File(hibURL.getPath());

		//загрузим специальные мапинги перед загрузкой основного архива, например для создания описателей глобальных фильтров
		File[] files = hibDirectory.listFiles();
		if (files != null)
			for ( int i = 0; i < files.length ; i++ ) {
				if (!files[i].isDirectory() && files[i].getName().startsWith("mg.") && files[i].getName().endsWith(".hbm.xml")) {
					cfg.addFile(files[i]);
				}
			}

		//грузим стандартный мапинг MBSA
//		loadMBSADatawarehouse(cfg);
		File mbsaData = new File(hibURL.getPath().concat(CommonConstant.DATAWAREHOUSE));
		if (mbsaData.isFile())
			cfg.addJar(mbsaData);

		cfg.addDirectory(hibDirectory);

		File cfgFile = new File(hibfile);
		if( cfgFile != null && cfgFile.length() > 0) {
			cfg.configure(cfgFile);
		} else {
			File configFile = new File(hibURL.getPath() + CommonConstant.DATAWAREHOUSE_CFG_FILE);
			cfg.configure(configFile);
		}
		return;
	}

//	private static void loadMBSADatawarehouse(Configuration cfg) throws IOException {
//		Bundle mbsaLibraryPlgn = Platform.getBundle("com.mg.merp.wb.badi.library");
//		//String location = mbsaLibraryPlgn.getLocation();
//		Enumeration<String> tfiles = mbsaLibraryPlgn.getEntryPaths("/lib/" );
//		while ( tfiles.hasMoreElements() ) {
//			String fileName = tfiles.nextElement();
//			URL fileURL = mbsaLibraryPlgn.getEntry(fileName);
//			File file = new File(FileLocator.resolve(fileURL).getFile());
//			//File file = new File(location.concat(fileURL.getFile()));
//			if (file.isFile())
//				cfg.addJar(file);
//		}
//	}

	public static void constructSessionFactory( String hibfile, String mapdir, String jndiName) throws HibernateException {

		if( hibfile == null){
			hibfile = "";
		}
		if( mapdir == null){
			mapdir = "";
		}
		if( sessionFactory == null){

			initSessionFactory(hibfile, mapdir, jndiName);
			System.out.println("Initing Session Factory");
			return;
		}

		if(  HibernateMapDirectory.equalsIgnoreCase(mapdir) && HibernateConfigFile.equalsIgnoreCase(hibfile)){
			return;
		}
		System.out.println( "Session Configuration Changed, rebuilding");
		//Configuration changed need a rebuild.
		//Note this is very expensive
		synchronized(sessionFactory) {
			Session s = (Session) session.get();
			if (s != null) {
				closeSession();
			}
			if (sessionFactory != null && !sessionFactory.isClosed()){
				closeFactory();
			}
			sessionFactory = null;
			initSessionFactory(hibfile, mapdir, jndiName);
		}
	}

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this thread has none yet
		if (s == null) {
			if( sessionFactory == null){
				return null;
			}
			s = sessionFactory.openSession();
			// Store it in the ThreadLocal variable
			session.set(s);
		}
		return s;
	}

	public static void closeFactory(){
		//more error checking needed
		if (sessionFactory != null)
			sessionFactory.close();
		sessionFactory = null;
	}

	public static void closeSession() throws HibernateException {
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}

	//Get all properties for the given class
	public static  String[] getHibernateProp(String className){
		Session session = HibernateUtil.currentSession();
		SessionFactory sf = session.getSessionFactory();
		String[] hibClassProps = sf.getClassMetadata(className).getPropertyNames();

		return( hibClassProps);
	}

	//Get type for given property
	public static String  getHibernatePropTypes(String className, String propName){

		Session session = HibernateUtil.currentSession();
		SessionFactory sf = session.getSessionFactory();
		org.hibernate.type.Type hibClassProps = sf.getClassMetadata(className).getPropertyType(propName);
		return(hibClassProps.getName());

	}

	//Get type for given property
	public static Object  getHibernatePropVal(Object instObj, String className, String propName){

		Session session = HibernateUtil.currentSession();
		SessionFactory sf = session.getSessionFactory();
		Object hibObj = sf.getClassMetadata(className).getPropertyValue(instObj, propName, EntityMode.POJO);
		return(hibObj);

	}

	private static class WrappedDriver implements Driver
	{
		private Driver driver;
		private String driverClass;

		WrappedDriver( Driver d, String driverClass )
		{

			this.driver = d;
			this.driverClass = driverClass;
		}

		/*
		 * @see java.sql.Driver#acceptsURL(java.lang.String)
		 */
		public boolean acceptsURL( String u ) throws SQLException
		{
			boolean res = this.driver.acceptsURL( u );
			System.out.println( "WrappedDriver(" + driverClass +
					").acceptsURL(" + u + ")returns: " + res);
			return res;
		}

		/*
		 * @see java.sql.Driver#connect(java.lang.String, java.util.Properties)
		 */
		public java.sql.Connection connect( String u, Properties p ) throws SQLException
		{

			try
			{
				return this.driver.connect( u, p );
			}
			catch ( RuntimeException e )
			{
				throw new SQLException( e.getMessage( ) );
			}
		}

		/*
		 * @see java.sql.Driver#getMajorVersion()
		 */
		public int getMajorVersion( )
		{
			return this.driver.getMajorVersion( );
		}

		/*
		 * @see java.sql.Driver#getMinorVersion()
		 */
		public int getMinorVersion( )
		{
			return this.driver.getMinorVersion( );
		}

		/*
		 * @see java.sql.Driver#getPropertyInfo(java.lang.String, java.util.Properties)
		 */
		public DriverPropertyInfo[] getPropertyInfo( String u, Properties p )
		throws SQLException
		{
			return this.driver.getPropertyInfo( u, p );
		}

		/*
		 * @see java.sql.Driver#jdbcCompliant()
		 */
		public boolean jdbcCompliant( )
		{
			return this.driver.jdbcCompliant( );
		}

		/*
		 * @see java.lang.Object#toString()
		 */
			public String toString( )
		{
			return driverClass;
		}

			@Override
			public Logger getParentLogger()
					throws SQLFeatureNotSupportedException {
				return this.driver.getParentLogger();
			}
	}

	@SuppressWarnings("unchecked")
	public static void refreshURLs() {
		Bundle jdbcbundle = Platform.getBundle( "org.eclipse.birt.report.data.oda.jdbc" );
		Bundle hibbundle = Platform.getBundle( "com.mg.jet.birt.report.data.oda.ejbql" );
		//Bundle tomcatPlgn = Platform.getBundle( "org.eclipse.tomcat" );
		//Bundle mbsaSharedPlgn = Platform.getBundle("com.mg.merp.wb.shared.classpath");

		FileList.clear();
		URLList.clear();
		if ( jdbcbundle == null )
			return;			// init failed

		// List all files under "drivers" directory of the JDBC plugin
		Enumeration<String> files = jdbcbundle.getEntryPaths(OdaJdbcDriver.Constants.DRIVER_DIRECTORY );
		if (files != null)
			while ( files.hasMoreElements() )
			{
				String fileName = files.nextElement();
				if ( isDriverFile( fileName ) )
				{
					if ( ! FileList.contains( fileName ))
					{
						// This is a new file not previously added to URL list
						FileList.add( fileName );
						URL fileURL = jdbcbundle.getEntry( fileName );
						URLList.add(fileURL);

						System.out.println("JDBC Plugin: found JAR/Zip file: " +
								fileName + ": URL=" + fileURL );
					}
				}
			}

		/*Enumeration<String> tfiles = tomcatPlgn.getEntryPaths("/" );
		while ( tfiles.hasMoreElements() )
		{
			String fileName = tfiles.nextElement();
			//if ( fileName.equals("commons-logging-api.jar") || fileName.equals("commons-collections.jar") ){
			//	URL fileURL = tomcatPlgn.getEntry( fileName );
			//	URLList.add(fileURL);
			//}

		}*/

//		URL jarURL = tomcatPlgn.getEntry( "commons-logging-api.jar" );
//		System.out.println("Hibernate Plugin: Tomcat plugin: URL = " + jarURL );
//		URLList.add(jarURL);
//		jarURL = tomcatPlgn.getEntry( "commons-collections.jar" );
//		System.out.println("Hibernate Plugin: Tomcat plugin: URL = " + jarURL );
//		URLList.add(jarURL);


		// List all files under "hibclassfiles" directory of this plugin
		Enumeration<String> hibFiles = hibbundle.getEntryPaths(CommonConstant.HIBERNATE_CLASSES);
		if (hibFiles != null)
			while ( hibFiles.hasMoreElements() )
			{
				String fileName = (String) hibFiles.nextElement();
				if ( isDriverFile( fileName ) )
				{
					if ( ! FileList.contains( fileName ))
					{
						// This is a new file not previously added to URL list
						FileList.add( fileName );
						URL fileURL = hibbundle.getEntry( fileName );
						URLList.add(fileURL);

						System.out.println("Hibernate Plugin: found JAR/Zip file: " +
								fileName + ": URL=" + fileURL );
					}
				}
			}


		// List all files under "hibclassfiles" directory of this plugin
/*		Enumeration hiblibs = hibbundle.getEntryPaths(
				CommonConstant.HIBERNATE_LIBS );
		while ( hiblibs.hasMoreElements() )
		{
			String fileName = (String) hiblibs.nextElement();
			if ( isDriverFile( fileName ) )
			{
				if ( ! FileList.contains( fileName ))
				{
					// This is a new file not previously added to URL list
					FileList.add( fileName );
					URL fileURL = hibbundle.getEntry( fileName );
					URLList.add(fileURL);

					System.out.println("Hibernate Plugin: found JAR/Zip file: " +
							fileName + ": URL=" + fileURL );
				}
			}
		}
	*/

		//load mgframework
		//URL mgFrm = mbsaSharedPlgn.getEntry(CommonConstant.MGFRAMEWORK);
		//URLList.add(mgFrm);

		FileList.add( CommonConstant.HIBERNATE_CLASSES );
		URL fileURL = hibbundle.getEntry( CommonConstant.HIBERNATE_CLASSES );
		URLList.add(fileURL);
		System.out.println("Hibernate Plugin: add folder for standalone classes file: " +
				CommonConstant.HIBERNATE_CLASSES + ": URL=" + fileURL );

		return;
	}

	private static boolean isDriverFile( String fileName ) {
		String lcName = fileName.toLowerCase();
		return lcName.endsWith(".jar") || lcName.endsWith(".zip");
	}

}