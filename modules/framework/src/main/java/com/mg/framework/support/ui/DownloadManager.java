/*
 * DownloadManager.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.api.ui.WebResourceProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Map;

/**
 * This class allows to register a resource provider by a unique id and to display the resource
 * provider's content for a given id. <p/> Note: This class is environment-aware (development runner
 * vs. servlet container) and will show the resource using the appropriate approach for the given
 * environment.
 *
 * @author Oleg V. Safonov
 * @author Etienne.Studer@canoo.com
 * @version $Id: DownloadManager.java,v 1.2 2007/11/09 11:58:18 safonov Exp $
 */
public class DownloadManager {
  public static final String ID_PARAMETER = "id";
  private static final Logger logger = Logger.getLogger(DownloadManager.class);
  private static final String DOWNLOAD_SERVLET_URL_PATTERN = "download";
  private static final DownloadManager INSTANCE = new DownloadManager();

  private Map<String, WebResourceProvider> entries;
  private SecureRandom secureRandom;

  private DownloadManager() {
    entries = new Hashtable<String, WebResourceProvider>();
    secureRandom = new SecureRandom();
  }

  /**
   * получить экземпляр менеджера загрузок
   *
   * @return экземпляр
   */
  public static DownloadManager getInstance() {
    return INSTANCE;
  }

  /**
   * сформировать URL для загрузки с помощью сервлета менеджера загрузок
   *
   * @param id идентификатор ресурса для загрузки
   * @return URL загрузки
   */
  public static String determineUrl(String id) {
    return new StringBuilder(DOWNLOAD_SERVLET_URL_PATTERN).append("?").append(ID_PARAMETER).append("=").append(id).toString();
  }

  /**
   * получить web ресурс по идентификатору
   *
   * @param id идентификатор
   * @return web ресурс
   */
  public WebResourceProvider get(String id) {
    return entries.get(id);
  }

  /**
   * добавить web ресурс в менеджер загрузок
   *
   * @param id               идентификатор
   * @param resourceProvider web ресурс
   */
  public void put(String id, WebResourceProvider resourceProvider) {
    entries.put(id, resourceProvider);
  }

  /**
   * добавить web ресурс в менеджер загрузок
   *
   * @param resourceProvider web ресурс
   * @return идентификатор загрузки
   */
  public String put(WebResourceProvider resourceProvider) {
    try {
      String id = createId();
      entries.put(id, resourceProvider);
      return id;
    } catch (NoSuchAlgorithmException nsae) {
      logger.error("Could not generate random id", nsae);
      throw new IllegalStateException("Could not generate random id: " + nsae.getLocalizedMessage());
    }
  }

  private String createId() throws NoSuchAlgorithmException {
    byte bytes[] = new byte[24];
    secureRandom.nextBytes(bytes);
    bytes = MessageDigest.getInstance("MD5").digest(bytes);

    StringBuffer result = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      byte b1 = (byte) ((bytes[i] & 0xf0) >> 4);
      byte b2 = (byte) (bytes[i] & 0x0f);

      result.append(toHex(b1));
      result.append(toHex(b2));
    }

    return result.toString();
  }

  private char toHex(byte b) {
    if (b < 10) {
      return (char) ('0' + b);
    }
    return (char) ('A' + b - 10);
  }

//	public void showDocument(String id, String target) {
//		String url = determineUrl(id);
//		ClientContextFactory.getInstance().getDefaultClientContext().showApplicationDocument(url);
//	}

  /**
   * показать документ по идентификатору
   *
   * @param id идентификатор
   */
  public void showDocument(String id) {
//		showDocument(id, null);
    String url = determineUrl(id);
    ClientContextFactory.getInstance().getDefaultClientContext().showApplicationDocument(url);
  }

}
