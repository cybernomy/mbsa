/*
 * PromotionType.java
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
package com.mg.merp.discount.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;
import com.mg.merp.baiengine.model.Repository;
import com.mg.merp.core.model.SysClient;

import java.io.Serializable;

/**
 * Модель бизнес-компонента "Тип рекламного мероприятия"
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionType.java,v 1.2 2007/11/12 13:17:05 sharapov Exp $
 */
@DataItemName("Discount.PromotionType") //$NON-NLS-1$
public class PromotionType extends PersistentObjectHibernate implements Serializable {

  // Fields

  private Integer id;

  private String name;

  private String code;

  private String description;

  private Repository bai;

  private SysClient sysClient;


  // Default constructor
  public PromotionType() {
  }

  // Constructor with id
  public PromotionType(Integer id) {
    this.id = id;
  }


  // Property accessors

  /**
   * @return the id
   */
  @DataItemName("ID") //$NON-NLS-1$
  public Integer getId() {
    return this.id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  @DataItemName("Discount.PromotionType.Name") //$NON-NLS-1$
  public String getName() {
    return this.name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the code
   */
  @DataItemName("Discount.PromotionType.Code") //$NON-NLS-1$
  public String getCode() {
    return this.code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the bai
   */
  @DataItemName("Discount.PromotionType.BAi") //$NON-NLS-1$
  public Repository getBai() {
    return this.bai;
  }

  /**
   * @param bai the bai to set
   */
  public void setBai(Repository bai) {
    this.bai = bai;
  }

  /**
   * @return the sysClient
   */
  public SysClient getSysClient() {
    return this.sysClient;
  }

  /**
   * @param sysClient the sysClient to set
   */
  public void setSysClient(SysClient sysClient) {
    this.sysClient = sysClient;
  }

  /**
   * @return the description
   */
  @DataItemName("Discount.PromotionType.Description") //$NON-NLS-1$
  public String getDescription() {
    return this.description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
