/*
 * EntityTransformerMapping.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.core.model;

import com.mg.framework.api.metadata.ApplicationLayer;
import com.mg.framework.service.PersistentObjectHibernate;

import java.util.Date;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityTransformerMapping.java,v 1.3 2008/12/03 09:03:16 safonov Exp $
 */
public class EntityTransformerMapping extends PersistentObjectHibernate implements java.io.Serializable {
  private Integer Id;

  private String ClassA;

  private String ClassB;

  private ApplicationLayer ApplicationLayer;

  private String MapId;

  private int HashAB;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Date SysVersion;

  public EntityTransformerMapping() {
  }

  public EntityTransformerMapping(Integer id) {
    this.Id = id;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.service.PersistentObjectHibernate#getPrimaryKey()
   */
  @Override
  public Object getPrimaryKey() {
    return getId();
  }

  public ApplicationLayer getApplicationLayer() {
    return ApplicationLayer;
  }

  public void setApplicationLayer(ApplicationLayer applicationLayer) {
    ApplicationLayer = applicationLayer;
  }

  public String getClassA() {
    return ClassA;
  }

  public void setClassA(String classA) {
    ClassA = classA;
  }

  public String getClassB() {
    return ClassB;
  }

  public void setClassB(String classB) {
    ClassB = classB;
  }

  public Integer getId() {
    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getMapId() {
    return MapId;
  }

  public void setMapId(String mapId) {
    MapId = mapId;
  }

  public int getHashAB() {
    return HashAB;
  }

  public void setHashAB(int hashAB) {
    this.HashAB = hashAB;
  }

  public SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(SysClient SysClient) {
    this.SysClient = SysClient;
  }

  public Date getSysVersion() {
    return SysVersion;
  }

  public void setSysVersion(Date version) {
    SysVersion = version;
  }

}
