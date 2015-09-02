/*
 * AttachmentHandler.java
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
package com.mg.merp.reference;

import java.io.Serializable;

/**
 * ������, �������������� �������� ���������������� ������ � ����������
 * 
 * @author Artem V. Sharapov
 * @version $Id: AttachmentHandler.java,v 1.2 2007/04/02 10:44:27 sharapov Exp $
 */
public interface AttachmentHandler { 
	
	/**
	 * �������� ����������� ��������
	 * 
	 * @param entitylId - ������������� ��������, ���������� ��������
	 * @return	���������� ��������
	 */
	byte[] loadAttachmentBody(Integer entitylId);

	/**
	 * �������� ����� ��������
	 * 
	 * @param entitylId	- ������������� ��������, ���������� ��������
	 * @return	��� ��������
	 */
	String loadAttachmentName(Integer entitylId);

	/**
	 * ���������� ��������
	 * 
	 * @param body - ���������� ��������
	 * @param name - ��� ��������
	 * @param entitylId - ������������� ��������, ���������� ��������
	 */
	void storeAttachment(byte[] body, String name, Integer entitylId);
	
	/**
	 * �������� ����������� ��������
	 * 
	 * @param entitylIds - �������������� ���������, ���������� ��������
	 */
	void removeAttachment(Serializable[] entitylIds);

}
