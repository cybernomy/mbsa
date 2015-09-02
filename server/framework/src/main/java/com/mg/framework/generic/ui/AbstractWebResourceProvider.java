/*
 * AbstractWebResourceProvider.java
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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.mg.framework.api.ui.WebResourceProvider;
import com.mg.framework.utils.FileUtils;

/**
 * This abstract class handles the mime type of a web resource and is extended by classes that provide different
 * representations of a web resource of the specified mime type.
 * 
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: AbstractWebResourceProvider.java,v 1.1 2007/03/19 11:44:00 safonov Exp $
 */
public abstract class AbstractWebResourceProvider implements WebResourceProvider {
	private String fMimeType;

	public AbstractWebResourceProvider(String mimeType) {
		fMimeType = mimeType;
	}

	public String getMimeType() {
		return fMimeType;
	}

	protected void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
		byte[] b = new byte[FileUtils.DEFAULT_BUFFER_SIZE];
		for (int n; (n = inputStream.read(b)) != -1;) {
			outputStream.write(b, 0, n);
		}
	}

}
