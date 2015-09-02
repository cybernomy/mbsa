/*
 * MERPJBossService.java
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
package com.mg.framework.service.jboss;

import java.io.File;
import java.io.FileOutputStream;

import org.jboss.logging.Logger;
import org.jboss.system.ServiceMBeanSupport;
import org.jboss.system.server.ServerConfigLocator;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.service.MERPService;
import com.mg.framework.utils.FileUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: MERPJBossService.java,v 1.7 2007/12/20 10:17:14 safonov Exp $
 *
 */
public class MERPJBossService
	extends ServiceMBeanSupport
	implements MERPJBossServiceMBean {

	private static Logger log = Logger.getLogger(MERPJBossService.class);
	private MERPService delegate;
    private boolean useBiDirGIOP = false;

	public MERPJBossService() {
		delegate = new MERPService();
	}

	/**
	 * createService
	 */
	protected void createService() throws Exception {
		delegate.createService();
	}

	/**
	 * destroyService
	 */
	protected void destroyService() throws Exception {
		delegate.destroyService();
	}

	/**
	 * setMERPLocation
	 *
	 * @return String
	 */
	public String getMERPLocation() {
		return delegate.getMERPLocation();
	}

	/**
	 * setMERPLocation
	 *
	 * @param systemLocation String
	 */
	public void setMERPLocation(String systemLocation) {
		delegate.setMERPLocation(systemLocation);
	}

    /**
     * @jmx.managed-attribute 
     * 
     * @return  True if bidir GIOP enabled
     */
    public boolean isUseBiDirGIOP() {
        return useBiDirGIOP;
    }
    
    /**
     * @jmx.managed-attribute
     * 
     * @param useBiDirGIOP  True if bidir GIOP has to be enabled
     */
    public void setUseBiDirGIOP(boolean useBiDirGIOP) {
        this.useBiDirGIOP = useBiDirGIOP;
    }
    
	public void setBusinessComponent(String component) {
		delegate.setBusinessComponent(component);
	}

	public void convertDataSource() throws ApplicationException {
	    delegate.convertDataSource();
	}

	private File createDeployFile(String fileName) {
		StringBuilder sb = new StringBuilder(ServerConfigLocator.locate().getServerHomeDir().getPath())
				.append(File.separatorChar)
				.append("deploy")
				.append(File.separatorChar)
				.append(fileName);

		return new File(sb.toString());
	}
	
    public void delpoyLocalFile(String name) throws ApplicationException {
        try {
            File srcFile = new File(name);
            File dstFile = createDeployFile(srcFile.getName());
            FileUtils.copy(srcFile, dstFile);
        }
        catch (Exception e) {
        	log.error("Invalid local deploy", e);
            throw new  ApplicationException(e);
        }
    }
    
    public void delpoyRemoteFile(String name, byte[] file) throws ApplicationException {
        try {
            File srcFile = File.createTempFile("tmp", "");
            try {
                FileOutputStream fos = new FileOutputStream(srcFile);
                fos.write(file);
                fos.flush();
                fos.close();
                
                File dstFile = createDeployFile(name);
                
                FileUtils.copy(srcFile, dstFile);
            }
            finally {
                srcFile.delete();
            }
        }
        catch (Exception e) {
        	log.error("Invalid remote deploy", e);
            throw new  ApplicationException(e);
        }
    }
}
