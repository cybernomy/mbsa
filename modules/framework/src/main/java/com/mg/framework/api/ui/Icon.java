/**
 * Icon.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.api.ui;

import java.io.Serializable;
import java.net.URL;

/**
 * Иконка
 *
 * @author Oleg V. Safonov
 * @version $Id: Icon.java,v 1.1 2008/07/24 15:12:25 safonov Exp $
 */
public class Icon implements Serializable {
  private byte[] body = null;
  private URL location = null;
  private String resourceName = null;

  /**
   * создать иконку на основании содержимого
   */
  public Icon(byte[] body) {
    super();
    this.body = body;
  }

  /**
   * создать иконку из ресурса
   *
   * @param location ресурс
   */
  public Icon(URL location) {
    super();
    this.location = location;
  }

  /**
   * создать иконку из ресурса
   *
   * @param resourceName имя ресурса
   */
  public Icon(String resourceName) {
    super();
    this.resourceName = resourceName;
  }

  /**
   * @return the body
   */
  public byte[] getBody() {
    return body;
  }

  /**
   * @return the location
   */
  public URL getLocation() {
    return location;
  }

  /**
   * @return the resourceName
   */
  public String getResourceName() {
    return resourceName;
  }

}
