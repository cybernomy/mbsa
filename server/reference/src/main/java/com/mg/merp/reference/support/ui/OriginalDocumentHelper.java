/*
 * OriginalDocumentHelper.java
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
import com.mg.framework.api.ui.FileLoadHandler;
import com.mg.framework.api.ui.FileSizeExceededException;
import com.mg.framework.api.ui.FileStoreHandler;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIUtils;
import com.mg.framework.support.ui.UniversalFileWebResourceProvider;
import com.mg.framework.utils.CoSUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.reference.AttachmentHandler;

/**
 * UI утилиты "Оригиналов"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: OriginalDocumentHelper.java,v 1.5 2008/05/29 15:31:20 safonov Exp $
 * 
 * @deprecated use {@link com.mg.merp.reference.support.ui.AttachmentHelper} instead
 */
@Deprecated
public class OriginalDocumentHelper {
	private static Logger logger = ServerUtils.getLogger(OriginalDocumentHelper.class); 

	private static AttachmentHandler getOriginalDocumentService(String businessServiceName) {
		return (AttachmentHandler) ApplicationDictionaryLocator.locate().getBusinessService(businessServiceName);
	}

	private static void download(final AttachmentHandler originalDocumentService, final Serializable[] keys, final int index) {
		//сохраняем столько файлов, сколько отмечено записей
		//прошли весь список
		if (index >= keys.length)
			return;

		Integer key = (Integer) keys[index];
		final byte[] original = originalDocumentService.loadAttachmentBody(key);
		if (original == null) //нечего сохранять для данного документа
			download(originalDocumentService, keys, index + 1);
		else {
			String name = originalDocumentService.loadAttachmentName(key);
			UIUtils.storeFile(new FileStoreHandler() {

				public void onFailure(int reason, String description) {
					logger.debug("failure downloaded, reason: " + description); //$NON-NLS-1$
					if (FileStoreHandler.FILE_SIZE_EXCEEDED == reason)
						throw new FileSizeExceededException(description); 
				}

				public void onSuccess(String filePath, String fileName) {
					download(originalDocumentService, keys, index + 1);
					logger.debug("succsess downloaded: " + fileName); //$NON-NLS-1$
				}

				public void prepareFile(OutputStream data) throws Exception {
					data.write(original);
				}

			}, name, CoSUtils.getMaxDownloadFileSize());			
		}
	}

	/**
	 * интеркативное сохранение оригиналов в системе, происходит запрос файла для сохранения,
	 * выбранный файл будет сохранен для всех переданных сущностей
	 * 
	 * @param keys	список первичных ключей сущностей оригиналов документов
	 * @param businessServiceName  имя бизнес-сервиса
	 */
	public static void upload(final Serializable[] keys, final String businessServiceName) {
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

					AttachmentHandler originalDocumentService = getOriginalDocumentService(businessServiceName);

					//сохраним файл для всех отмеченных записей
					for (int i = 0; i < keys.length; i++)
						originalDocumentService.storeAttachment(body, fileNames != null ? fileNames[0] : "attachment", (Integer) keys[i]);
				} catch (IOException e) {
					logger.error("exception during upload", e); //$NON-NLS-1$
				}
			}

		}, CoSUtils.getMaxUploadFileSize());
	}

	/**
	 * интерактивное загрузка оригиналов на локальную систему, происходит запрос куда сохранить файл,
	 * количество запросов будет соответсвовать количеству переданных сущностей
	 * 
	 * @param keys	список первичных ключей сущностей оригиналов документов
	 * @param businessServiceName  имя бизнес-сервиса
	 */
	public static void download(final Serializable[] keys, final String businessServiceName) {
		download(getOriginalDocumentService(businessServiceName), keys, 0);
	}

	/**
	 * показ оригиналов на локальной системе, количество показанных документов будет соответсвовать
	 * количеству переданных сущностей
	 * 
	 * @param keys	список первичных ключей сущностей оригиналов документов
	 * @param businessServiceName  имя бизнес-сервиса
	 */
	public static void show(final Serializable[] keys, final String businessServiceName) {
		if (keys.length == 0)
			return;

		AttachmentHandler originalDocumentService = getOriginalDocumentService(businessServiceName);
		for (int i = 0; i < keys.length; i ++) {
			final byte[] original = originalDocumentService.loadAttachmentBody((Integer) keys[i]);
			if (original == null) //нечего сохранять для данного документа
				continue;
			String name = originalDocumentService.loadAttachmentName((Integer) keys[i]);
			UIUtils.showDocument(new UniversalFileWebResourceProvider(name, original));
		}
	}

}
