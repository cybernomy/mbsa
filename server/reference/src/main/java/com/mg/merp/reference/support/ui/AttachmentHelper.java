/*
 * AttachmentHelper.java
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
package com.mg.merp.reference.support.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.Alert;
import com.mg.framework.api.ui.AlertListener;
import com.mg.framework.api.ui.FileLoadHandler;
import com.mg.framework.api.ui.FileSizeExceededException;
import com.mg.framework.api.ui.FileStoreHandler;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.UniversalFileWebResourceProvider;
import com.mg.framework.utils.CoSUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.AttachmentHandler;

/**
 * UI ������� ��� ������ � ����������
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: AttachmentHelper.java,v 1.4 2008/05/29 15:31:20 safonov Exp $
 */
public class AttachmentHelper {
	private static Logger logger = ServerUtils.getLogger(AttachmentHelper.class); 

	private static AttachmentHandler getAttachmentService(String attachmentServiceName) {
		return (AttachmentHandler) ApplicationDictionaryLocator.locate().getBusinessService(attachmentServiceName);
	}

	private static void download(final AttachmentHandler attachmentService, final Serializable[] keys, final int index) {
		//��������� ������� ������, ������� �������� �������
		//������ ���� ������
		if (index >= keys.length)
			return;

		Integer key = (Integer) keys[index];
		final byte[] original = attachmentService.loadAttachmentBody(key);
		if (original == null) //������ ��������� ��� ������� ���������
			download(attachmentService, keys, index + 1);
		else {
			String name = attachmentService.loadAttachmentName(key);
			UIUtils.storeFile(new FileStoreHandler() {

				public void onFailure(int reason, String description) {
					logger.debug("failure downloaded, reason: " + description); //$NON-NLS-1$
					if (FileStoreHandler.FILE_SIZE_EXCEEDED == reason)
						throw new FileSizeExceededException(description);
				}

				public void onSuccess(String filePath, String fileName) {
					download(attachmentService, keys, index + 1);
					logger.debug("succsess downloaded: " + fileName); //$NON-NLS-1$
				}

				public void prepareFile(OutputStream data) throws Exception {
					data.write(original);
				}

			}, name, CoSUtils.getMaxDownloadFileSize());			
		}
	}

	/**
	 * ������������� ���������� �������� � �������, ���������� ������ ����� ��� ����������,
	 * ��������� ���� ����� �������� ��� ���� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������
	 * @param attachmentService  ������, �������������� �������� ���������������� ������ � ����������
	 */
	public static void upload(final Serializable[] keys, final AttachmentHandler attachmentService) {
		if (keys.length == 0)
			return;

		UIUtils.loadFile(new FileLoadHandler() {

			public void onFailure(int reason, String description) {
				logger.debug("failure uploaded, reason: " + description); //$NON-NLS-1$
				if (FileLoadHandler.FILE_SIZE_EXCEEDED == reason)
					throw new FileSizeExceededException(description);
			}

			public void onSuccess(InputStream[] ins, String[] filePaths, String[] fileNames) {
				try {
					if (ins == null || ins.length == 0)
						return;

					byte[] body = new byte[ins[0].available()];
					ins[0].read(body);

					//�������� ���� ��� ���� ���������� �������
					for (int i = 0; i < keys.length; i++)
						attachmentService.storeAttachment(body, fileNames != null ? fileNames[0] : "attachment", (Integer) keys[i]);
				} catch (IOException e) {
					logger.error("exception during upload", e); //$NON-NLS-1$
				}
			}
		}, CoSUtils.getMaxUploadFileSize());
	}

	/**
	 * ������������� ���������� �������� � �������, ���������� ������ ����� ��� ����������,
	 * ��������� ���� ����� �������� ��� ���� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������
	 * @param attachmentServiceName - ��� ������-�������, ��������������� �������� ���������������� ������ � ����������
	 */
	public static void upload(final Serializable[] keys, final String attachmentServiceName) {
		upload(keys, getAttachmentService(attachmentServiceName));
	}

	/**
	 * ������������� �������� �������� �� ��������� �������, ���������� ������ ���� ��������� ����,
	 * ���������� �������� ����� �������������� ���������� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������
	 * @param attachmentServiceName - ��� ������-�������, ��������������� �������� ���������������� ������ � ����������
	 */
	public static void download(final Serializable[] keys, final String attachmentServiceName) {
		download(getAttachmentService(attachmentServiceName), keys, 0);
	}

	/**
	 * ������������� �������� �������� �� ��������� �������, ���������� ������ ���� ��������� ����,
	 * ���������� �������� ����� �������������� ���������� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������
	 * @param attachmentService - c����� ������-����������, �������������� �������� ���������������� ������ � ����������
	 */
	public static void download(final Serializable[] keys, final AttachmentHandler attachmentService) {
		download(attachmentService, keys, 0);
	}

	/**
	 * ����� �������� �� ��������� �������, ���������� ���������� �������� ����� ��������������
	 * ���������� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������, ���������� �������� 
	 * @param attachmentService - c����� ������-����������, �������������� �������� ���������������� ������ � ����������
	 */
	public static void show(final Serializable[] keys, final AttachmentHandler attachmentService) {
		if (keys.length == 0)
			return;

		for (int i = 0; i < keys.length; i ++) {
			final byte[] original = attachmentService.loadAttachmentBody((Integer) keys[i]);
			if (original == null) //������ ��������� ��� ������� ���������
				continue;
			String name = attachmentService.loadAttachmentName((Integer) keys[i]);
			UIUtils.showDocument(new UniversalFileWebResourceProvider(name, original));
		}
	}

	/**
	 * ����� �������� �� ��������� �������, ���������� ���������� �������� ����� ��������������
	 * ���������� ���������� ���������
	 * 
	 * @param keys - ������ ��������� ������ ���������, ���������� ��������
	 * @param attachmentServiceName - ��� ������-�������, ��������������� �������� ���������������� ������ � ����������
	 */
	public static void show(final Serializable[] keys, final String attachmentServiceName) {
		show(keys, getAttachmentService(attachmentServiceName));
	}

	/**
	 * ������������� �������� ��������; ���������� ������ �������� ��������
	 * 
	 * @param keys - ������ ��������� ������ ���������, ���������� ��������
	 * @param attachmentService - c����� ������-����������, �������������� �������� ���������������� ������ � ����������
	 */
	public static void remove(final Serializable[] keys, final AttachmentHandler attachmentService) {
		if (keys.length == 0)
			return;

		com.mg.merp.reference.support.Messages eraseAttachmentMsg = com.mg.merp.reference.support.Messages.getInstance();
		Messages msg = Messages.getInstance();
		final String yesButton = msg.getMessage(Messages.YES_BUTTON_TEXT);
		UIUtils.showAlert(Alert.MessageType.QUESTION_MESSAGE, msg.getMessage(Messages.ERASE_ALERT_TITLE), eraseAttachmentMsg.getMessage(com.mg.merp.reference.support.Messages.ERASE_ATTACHMENT_QUESTION), yesButton, msg.getMessage(Messages.NO_BUTTON_TEXT), new AlertListener() {
			public void alertClosing(String value) {
				if (value.equals(yesButton)) {
					attachmentService.removeAttachment(keys);
				}
			}
		});
	}

	/**
	 * ������������� �������� ��������; ���������� ������ �������� ��������
	 *  
	 * @param keys - ������ ��������� ������ ���������, ���������� ��������
	 * @param attachmentServiceName - ��� ������-�������, ��������������� �������� ���������������� ������ � ����������
	 */
	public static void remove(final Serializable[] keys, final String attachmentServiceName) {
		remove(keys, getAttachmentService(attachmentServiceName));
	}

}
