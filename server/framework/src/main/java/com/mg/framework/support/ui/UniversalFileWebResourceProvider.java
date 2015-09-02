/*
 * UniversalFileWebResourceProvider.java
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
package com.mg.framework.support.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mg.framework.generic.ui.FileWebResourceProvider;
import com.mg.framework.utils.FileUtils;

/**
 * ������������� �������� web ������
 * 
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: UniversalFileWebResourceProvider.java,v 1.2 2007/08/30 14:15:03 safonov Exp $
 */
public class UniversalFileWebResourceProvider extends FileWebResourceProvider {
	transient private byte[] fileContents;
	private String fileName;

	/**
	 * �������� ������� �� mime ���� � �����
	 * 
	 * @param mimeType	mime ���
	 * @param file	����
	 */
	public UniversalFileWebResourceProvider(String mimeType, File file) {
		super(mimeType);
		fFile = file;
	}

	/**
	 * �������� ������� �� �����
	 * 
	 * @param file	����
	 */
	public UniversalFileWebResourceProvider(File file) {
		super(FileUtils.getMimeTypeByFileName(file.getName()));
		fFile = file;
	}

	/**
	 * �������� ������� �� ����������� �����
	 * 
	 * @param fileName		��� �����
	 * @param fileContents	����������
	 */
	public UniversalFileWebResourceProvider(String fileName, byte[] fileContents) {
		super(FileUtils.getMimeTypeByFileName(fileName));
		this.fileContents = fileContents;
		this.fileName = fileName;
	}

	/**
	 * �������� ������� �� ����������� �����
	 * 
	 * @param mimeType		mime ���
	 * @param fileName		��� �����
	 * @param fileContents	����������
	 */
	public UniversalFileWebResourceProvider(String mimeType, String fileName, byte[] fileContents) {
		super(mimeType);
		this.fileContents = fileContents;
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.FileWebResourceProvider#createFile()
	 */
	@Override
	protected File createFile() throws IOException {
		if (fileContents == null)
			return null;
		File result = FileUtils.createTempFile(null, fileName);
		FileOutputStream fos = new FileOutputStream(result);
		ByteArrayInputStream bis = new ByteArrayInputStream(fileContents);
		copyStream(bis, fos);
		fos.flush();
		fos.close();
		bis.close();
		return result;
	}

}
