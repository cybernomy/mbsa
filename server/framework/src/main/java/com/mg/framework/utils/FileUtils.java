/*
 * FileUtil.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import sun.net.www.MimeEntry;
import sun.net.www.MimeTable;

import com.mg.framework.api.Logger;

/**
 * �������� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id: FileUtils.java,v 1.6 2008/08/12 09:20:09 safonov Exp $
 */
public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class);
	private static final String DEFAULT_MIME_TYPE = "text/plain";
	/** The default size of the copy buffer. */
	public static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * Copy a file.
	 *
	 * @param source  Source file to copy.
	 * @param target  Destination target file.
	 * @param buff    The copy buffer.
	 *
	 * @throws IOException  Failed to copy file.
	 */
	public static void copy(final File source, final File target, final byte buff[]) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));

		int read;

		try
		{
			while ((read = in.read(buff)) != -1)
			{
				out.write(buff, 0, read);
			}
		}
		finally
		{
			out.flush();
			in.close();
			out.close();
		}
	}

	/**
	 * Copy a file.
	 *
	 * @param source  Source file to copy.
	 * @param target  Destination target file.
	 * @param size    The size of the copy buffer.
	 *
	 * @throws IOException  Failed to copy file.
	 */
	public static void copy(final File source,
			final File target,
			final int size)
	throws IOException
	{
		copy(source, target, new byte[size]);
	}

	/**
	 * Copy a file.
	 *
	 * @param source  Source file to copy.
	 * @param target  Destination target file.
	 *
	 * @throws IOException  Failed to copy file.
	 */
	public static void copy(final File source, final File target)
	throws IOException
	{
		copy(source, target, DEFAULT_BUFFER_SIZE);
	}    

	/**
	 * ������ ���������� �����, ������������ ��������� ������ �� ��������� ��� ��
	 * 
	 * @see #readTextFile(File)
	 * 
	 * @param fullPathFilename	������ ���� � �����
	 * @return	����������
	 * @throws IOException
	 */
	public static String readTextFile(String fullPathFilename) throws IOException {
		return readTextFile(new File(fullPathFilename));
	}

	/**
	 * ������ ���������� �����
	 * 
	 * @param fullPathFilename	������ ���� � �����
	 * @param charsetName	���������, ���� <code>null</code> �� ������������ �� ��������� ��� ��
	 * @return	����������
	 * @throws IOException
	 */
	public static String readTextFile(String fullPathFilename, String charsetName) throws IOException {
		return readTextFile(new File(fullPathFilename), charsetName);
	}

	/**
	 * ������ ���������� �����, ������������ ��������� ������ �� ��������� ��� ��
	 * 
	 * @param file	����
	 * @return	����������
	 * @throws IOException
	 */
	public static String readTextFile(File file) throws IOException {
		return readTextFile(file, null);
	}

	/**
	 * ������ ���������� �����
	 * 
	 * @param file	����
	 * @param charsetName	���������, ���� <code>null</code> �� ������������ �� ��������� ��� ��
	 * @return	����������
	 * @throws IOException
	 */
	public static String readTextFile(File file, String charsetName) throws IOException {
		StringBuffer sb = new StringBuffer(DEFAULT_BUFFER_SIZE);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(charsetName == null ?
				new InputStreamReader(fis) :
				new InputStreamReader(fis, charsetName));

		char[] chars = new char[DEFAULT_BUFFER_SIZE];
		int numRead = 0;
		while( (numRead = reader.read(chars)) > -1) {
			sb.append(String.valueOf(chars, 0, numRead)); 
		}

		reader.close();

		return sb.toString();
	}

	/**
	 * ������ ���������� �����, ������������ ��������� ������ �� ��������� ��� ��
	 * 
	 * @param file	����
	 * @param body	����������
	 * @throws IOException
	 */
	public static void writeTextFile(File file, String body) throws IOException {
		writeTextFile(file, body, null);
	}

	/**
	 * ������ ���������� �����
	 * 
	 * @param file	����
	 * @param body	����������
	 * @param charsetName	���������, ���� <code>null</code> �� ������������ �� ��������� ��� ��
	 * @throws IOException
	 */
	public static void writeTextFile(File file, String body, String charsetName) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(charsetName == null ?
				new OutputStreamWriter(fos) :
				new OutputStreamWriter(fos, charsetName));
		writer.write(body);
		writer.close();
	}

	/**
	 * ������ ���������� �����, ������������ ��������� ������ �� ��������� ��� ��
	 * 
	 * @see #writeTextFile(File, String)
	 * 
	 * @param fullPathFilename	������ ���� � �����
	 * @param body	����������
	 * @throws IOException
	 */
	public static void writeTextFile(String fullPathFilename, String body) throws IOException {
		writeTextFile(new File(fullPathFilename), body);
	}

	/**
	 * ������ ���������� �����
	 * 
	 * @param fullPathFilename	������ ���� � �����
	 * @param body	����������
	 * @param charsetName	���������, ���� <code>null</code> �� ������������ �� ��������� ��� ��
	 * @throws IOException
	 */
	public static void writeTextFile(String fullPathFilename, String body, String charsetName) throws IOException {
		writeTextFile(new File(fullPathFilename), body, charsetName);		
	}
	
	/**
	 * �������� ���������� �����
	 * 
	 * @return	��������� ����
	 * @throws IOException
	 */
	public static File createTempFile() throws IOException {
		return createTempFile(null, null);
	}
	
	/**
	 * �������� ���������� �����
	 * 
	 * @param prefix	������� ������������ �����, ���� <code>null</code>, �� ����� ���������� � tmp
	 * @param suffix	������� ������������ �����, ���� <code>null</code>, �� ����� ���������� � tmp
	 * @return	��������� ����
	 * @throws IOException
	 */
	public static File createTempFile(String prefix, String suffix) throws IOException {
		File result = File.createTempFile(prefix == null ? "tmp" : prefix, suffix, ServerUtils.getServerTempDir());
		result.deleteOnExit();
		return result;
	}
	
	/**
	 * ������� ��� ����� �� ������� ����
	 * 
	 * @param fullPathFilename	������ ���� � �����
	 * @return	��� �����
	 */
	public static String extractFileName(String fullPathFilename) {
		fullPathFilename = fullPathFilename.replace('\\', '/');
		int index = fullPathFilename.lastIndexOf("/");
		return index == -1 ? StringUtils.EMPTY_STRING : fullPathFilename.substring(index + 1);
	}

	/**
	 * ������� ���������� ����� �� ����� �����
	 * 
	 * @param fileName	��� �����
	 * @return	����������
	 */
	public static String extractFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		return index == -1 ? StringUtils.EMPTY_STRING : fileName.substring(index + 1);
	}

	/**
	 * �������� mime ��� �� ����� �����
	 * 
	 * @param fileName	��� ����
	 * @return	mime ���
	 */
	public static String getMimeTypeByFileName(String fileName) {
		if (fileName == null)
			return DEFAULT_MIME_TYPE;
		
		MimeEntry en = MimeTable.getDefaultTable().findByFileName(fileName);
		return en != null ? en.getType() : DEFAULT_MIME_TYPE;
	}

	/**
	 * �������� mime ��� �� ���������� �����
	 * 
	 * @param fileExt	���������� �����
	 * @return	mime ���
	 */
	public static String getMimeTypeByFileExt(String fileExt) {
		if (fileExt == null)
			return DEFAULT_MIME_TYPE;
		
		MimeEntry en = MimeTable.getDefaultTable().findByExt(fileExt);
		return en != null ? en.getType() : DEFAULT_MIME_TYPE;
	}

	/**
	 * Deletes all files and subdirectories under dir. Returns true if all
	 * deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns false.
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return dir.delete();
	}

	/**
	 * Deletes all files and subdirectories under dir. Returns true if all
	 * deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns false.
	 * 
	 * @param dirName	dir name
	 * @return
	 */
	public static boolean deleteDir(String dirName) {
		log.debug("delete dir: " + dirName);
		return deleteDir(new File(dirName));
	}

}
