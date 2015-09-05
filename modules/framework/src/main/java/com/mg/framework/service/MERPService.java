/*
 * MERPService.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: MERPService.java,v 1.8 2006/10/25 09:21:11 safonov Exp $
 *
 */
public class MERPService {
	private String systemLocation = null;
	private String libraryName = null;

	private Logger log;

	public MERPService() {
	    log = ServerUtils.getLogger(getClass().getName());
	}

	/**
	 * createService
	 */
	public void createService() {
		try {
			prepareParameters();
//			loadMERPLibrary();
//			initAppServer(systemLocation);
//			
//			runAppServer();
		} catch (Throwable t) {
		    log.error(t);
		}
	}

	/**
	 * destroyService
	 */
	public void destroyService() {
		SessionControlImpl.getSingleton().clear();
		
		/*
		 * Invoke for destroy legacy objects in mas
		 */
		System.runFinalization();

//		terminateAppServer();
	}

	/**
	 * setMERPLocation
	 *
	 * @return String
	 */
	public String getMERPLocation() {
		return systemLocation;
	}

	/**
	 * setMERPLocation
	 *
	 * @param systemLocation String
	 */
	public void setMERPLocation(String systemLocation) {
		this.systemLocation = systemLocation;
        ServerUtils.MBSA_CUSTOM_LOCATION = systemLocation;
	}

	public void setBusinessComponent(String component) {
		//BeanRepository.getBeanRepository().registerBean(component);
	}

	public void convertDataSource() throws ApplicationException {
	    log.info("Start database convertion");
	    try {
//		    legacyConvertDataSource0();
	    	throw new UnsupportedOperationException();
	    }
	    catch (ApplicationException e) {
	        log.error("Database convertion stop with exception: ", e);
	        throw e;
	    }
	    catch (Exception e) {
	        log.error("Database convertion stop with exception: ", e);
	        throw new ApplicationException("Database convertion stop with exception: ", e);
	    }
//	    log.info("Database convertion completed successfully");
	}
	
	/**
	 * loadMERPLibrary
	 */
	private void loadMERPLibrary() {
	    loadDelphiLibrary();
	    System.load(getMERPLibraryName());
	}

	/**
	 * getMERPLibraryName
	 *
	 * @return String
	 */
	private String getMERPLibraryName() {
		return libraryName;
	}

	/**
	 * prepareParameters
	 */
	private void prepareParameters() {
		String os = System.getProperty("os.name");
		if (os.startsWith("Windows"))
			libraryName = getCompleteFileName("mascore_w.dll.2.0");
		else if (os.startsWith("Linux"))
			libraryName = getCompleteFileName("mascore_l.so");
	}

	private void loadDelphiLibrary() {
	    //try load each library
	    try {
	        System.load(getCompleteFileName("rtl70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
	        System.load(getCompleteFileName("vcl70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
	        System.load(getCompleteFileName("dbrtl70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
	        System.load(getCompleteFileName("xmlrtl70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
	        System.load(getCompleteFileName("dsnap70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
	        System.load(getCompleteFileName("djcl70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	    try {
		    System.load(getCompleteFileName("mtdorb_lib_70.bpl"));
	    }
	    catch (Throwable t) {
	        log.error(t);
	    }
	}
	
	private String getCompleteFileName(String libraryName) {
	    return systemLocation + "/bin/" + libraryName;
	}
	
	/**
	 * initAppServer
	 *
	 * @param systemLocation String
	 */
//	native private void initAppServer(String systemLocation);

	/**
	 * runAppServer
	 */
//	native private void runAppServer();

	/**
	 * terminateAppServer
	 */
//	native private void terminateAppServer();

	/**
	 * legacyConvertDataSource0
	 * 
	 * @throws ApplicationException
	 */
//	native private void legacyConvertDataSource0() throws ApplicationException;
	
}
