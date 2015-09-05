/*
 * MemoryWebResourceProvider.java
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
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.mg.framework.utils.FileUtils;

/**
 * This abstract class is extended by classes that provide a web resource which is primarily generated in memory as a
 * byte array or into an output stream.
 * <p/>
 * An input stream and a file representation of the byte array created by the subclass are provided by this class.
 * <p/>
 * 
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: MemoryWebResourceProvider.java,v 1.1 2007/03/19 11:44:00 safonov Exp $
 */
public abstract class MemoryWebResourceProvider extends AbstractWebResourceProvider {
	transient protected byte[] fBytes;
	private String fFileNameSuffix;

	public MemoryWebResourceProvider(String mimeType, String fileNameSuffix) {
		super(mimeType);
		fFileNameSuffix = fileNameSuffix;
	}

	public MemoryWebResourceProvider(String fileNameSuffix) {
		super(FileUtils.getMimeTypeByFileExt(fileNameSuffix));
		fFileNameSuffix = fileNameSuffix;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getBytes()
	 */
	public byte[] getBytes() throws IOException {
		if (fBytes == null) {
			fBytes = createBytes();
		}
		return fBytes;
	}

	/**
	 * must be override in subclass
	 * 
	 * @return
	 * @throws IOException
	 */
	abstract protected byte[] createBytes() throws IOException;

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getFile()
	 */
	public File getFile() throws IOException {
		File file = FileUtils.createTempFile(getClass().getName(), fFileNameSuffix);
		file.deleteOnExit();

		BufferedInputStream inputStream = new BufferedInputStream(getInputStream());
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

		copyStream(inputStream, outputStream);

		inputStream.close();
		outputStream.close();

		return file;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getInputStream()
	 */
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(getBytes());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.WebResourceProvider#getLength()
	 */
	public int getLength() throws IOException {
		return getBytes().length;
	}

}
