/*
 * ContractType.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.service.PersistentObjectHibernate;

import java.io.Serializable;

/**
 * Бизнес компонент "Вид договора"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: ContractType.java,v 1.1 2007/09/17 12:15:48 alikaev Exp $
 */
@DataItemName("Contract.ContractType")
public class ContractType extends PersistentObjectHibernate implements Serializable {

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private String Code;

  private String Name;


  public ContractType() {
  }

  public ContractType(int id) {
    super();
    Id = id;
  }

  @DataItemName("Contract.Code")
  public String getCode() {
    return Code;
  }

  public void setCode(String code) {
    Code = code;
  }

  @DataItemName("ID")
  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  @DataItemName("Contract.Name")
  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public com.mg.merp.core.model.SysClient getSysClient() {
    return SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient sysClient) {
    SysClient = sysClient;
  }

}
