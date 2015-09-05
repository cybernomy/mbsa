/*
 * WebResourceProvider.java
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
package com.mg.framework.api.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * This interface is implemented by classes that provide different representations of a web resource.
 * <p/>
 * Note: This interface is meant to be used for downloading/printing documents from within an MBSA application.
 * 
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: WebResourceProvider.java,v 1.1 2007/03/19 11:24:38 safonov Exp $
 */
public interface WebResourceProvider {

	/**
	 * get MIME type
	 * 
	 * @return	MIME type
	 */
	public String getMimeType();

	/**
	 * get resources size
	 * 
	 * @return	size
	 * @throws IOException
	 */
	public int getLength() throws IOException;

	/**
	 * get input stream
	 * 
	 * @return	input stream
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException;

	/**
	 * get file representation of resource
	 * 
	 * @return	file
	 * @throws IOException
	 */
	public File getFile() throws IOException;

	/**
	 * get contents of resource
	 * 
	 * @return	contents
	 * @throws IOException
	 */
	public byte[] getBytes() throws IOException;

}
