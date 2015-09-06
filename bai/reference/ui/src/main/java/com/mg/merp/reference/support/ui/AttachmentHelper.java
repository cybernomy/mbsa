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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * UI утилиты для работы с вложениями
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
    //сохраняем столько файлов, сколько отмечено записей
    //прошли весь список
    if (index >= keys.length)
      return;

    Integer key = (Integer) keys[index];
    final byte[] original = attachmentService.loadAttachmentBody(key);
    if (original == null) //нечего сохранять для данного документа
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
   * интеркативное сохранение вложений в системе, происходит запрос файла для сохранения, выбранный
   * файл будет сохранен для всех переданных сущностей
   *
   * @param keys              - список первичных ключей сущностей
   * @param attachmentService сервис, поддерживающий основную функциональность работы с вложениями
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

          //сохраним файл для всех отмеченных записей
          for (int i = 0; i < keys.length; i++)
            attachmentService.storeAttachment(body, fileNames != null ? fileNames[0] : "attachment", (Integer) keys[i]);
        } catch (IOException e) {
          logger.error("exception during upload", e); //$NON-NLS-1$
        }
      }
    }, CoSUtils.getMaxUploadFileSize());
  }

  /**
   * интеркативное сохранение вложений в системе, происходит запрос файла для сохранения, выбранный
   * файл будет сохранен для всех переданных сущностей
   *
   * @param keys                  - список первичных ключей сущностей
   * @param attachmentServiceName - имя бизнес-сервиса, поддерживающего основную функциональность
   *                              работы с вложениями
   */
  public static void upload(final Serializable[] keys, final String attachmentServiceName) {
    upload(keys, getAttachmentService(attachmentServiceName));
  }

  /**
   * интерактивное загрузка вложений на локальную систему, происходит запрос куда сохранить файл,
   * количество запросов будет соответсвовать количеству переданных сущностей
   *
   * @param keys                  - список первичных ключей сущностей
   * @param attachmentServiceName - имя бизнес-сервиса, поддерживающего основную функциональность
   *                              работы с вложениями
   */
  public static void download(final Serializable[] keys, final String attachmentServiceName) {
    download(getAttachmentService(attachmentServiceName), keys, 0);
  }

  /**
   * интерактивное загрузка вложений на локальную систему, происходит запрос куда сохранить файл,
   * количество запросов будет соответсвовать количеству переданных сущностей
   *
   * @param keys              - список первичных ключей сущностей
   * @param attachmentService - cервис бизнес-компонента, поддерживающий основную функциональность
   *                          работы с вложениями
   */
  public static void download(final Serializable[] keys, final AttachmentHandler attachmentService) {
    download(attachmentService, keys, 0);
  }

  /**
   * показ вложений на локальной системе, количество показанных вложений будет соответсвовать
   * количеству переданных сущностей
   *
   * @param keys              - список первичных ключей сущностей, содержащих вложения
   * @param attachmentService - cервис бизнес-компонента, поддерживающий основную функциональность
   *                          работы с вложениями
   */
  public static void show(final Serializable[] keys, final AttachmentHandler attachmentService) {
    if (keys.length == 0)
      return;

    for (int i = 0; i < keys.length; i++) {
      final byte[] original = attachmentService.loadAttachmentBody((Integer) keys[i]);
      if (original == null) //нечего сохранять для данного документа
        continue;
      String name = attachmentService.loadAttachmentName((Integer) keys[i]);
      UIUtils.showDocument(new UniversalFileWebResourceProvider(name, original));
    }
  }

  /**
   * показ вложений на локальной системе, количество показанных вложений будет соответсвовать
   * количеству переданных сущностей
   *
   * @param keys                  - список первичных ключей сущностей, содержащих вложения
   * @param attachmentServiceName - имя бизнес-сервиса, поддерживающего основную функциональность
   *                              работы с вложениями
   */
  public static void show(final Serializable[] keys, final String attachmentServiceName) {
    show(keys, getAttachmentService(attachmentServiceName));
  }

  /**
   * интерактивное удаление вложений; происходит запрос удаления вложения
   *
   * @param keys              - список первичных ключей сущностей, содержащих вложения
   * @param attachmentService - cервис бизнес-компонента, поддерживающий основную функциональность
   *                          работы с вложениями
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
   * интерактивное удаление вложений; происходит запрос удаления вложения
   *
   * @param keys                  - список первичных ключей сущностей, содержащих вложения
   * @param attachmentServiceName - имя бизнес-сервиса, поддерживающего основную функциональность
   *                              работы с вложениями
   */
  public static void remove(final Serializable[] keys, final String attachmentServiceName) {
    remove(keys, getAttachmentService(attachmentServiceName));
  }

}
