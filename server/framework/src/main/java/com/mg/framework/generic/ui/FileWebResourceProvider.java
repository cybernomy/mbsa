/*
 * FileWebResourceProvider.java
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
package com.mg.framework.generic.ui;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This abstract class is extended by classes that provide a web resource which is primarily generated as a file.
 * <p/>
 * An input stream and a byte array representation of the file created by the subclass are provided by this class.
 * <p/>
 * 
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: FileWebResourceProvider.java,v 1.1 2007/03/19 11:44:00 safonov Exp $
 */
public abstract class FileWebResourceProvider extends AbstractWebResourceProvider {
	transient protected File fFile;

	public FileWebResourceProvider(String mimeType) {
		super(mimeType);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getBytes()
	 */
	public byte[] getBytes() throws IOException {
		BufferedInputStream inputStream = new BufferedInputStream(getInputStream());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		copyStream(inputStream, outputStream);

		inputStream.close();
		outputStream.close();

		return outputStream.toByteArray();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getFile()
	 */
	synchronized public File getFile() throws IOException {
		if (fFile == null) {
			fFile = createFile();
		}
		return fFile;
	}

	/**
	 * must be override in subclass for create file
	 * 
	 * @return
	 * @throws IOException
	 */
	abstract protected File createFile() throws IOException;
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getInputStream()
	 */
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(getFile());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getLength()
	 */
	public int getLength() throws IOException {
		return (int) getFile().length();
	}

}
