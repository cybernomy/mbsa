/*
 * Order.java
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
package com.mg.merp.humanresources.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Приказы"
 *
 * @author Artem V. Sharapov
 * @version $Id: Order.java,v 1.4 2007/08/27 13:08:46 sharapov Exp $
 */
public class Order extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.core.model.Folder Folder;
  private java.lang.String OrderNumber;
  private java.util.Date OrderDate;
  private java.lang.String Header;
  private java.lang.String Footer;
  private java.lang.Short OrderStatus;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.util.Set<OrderItem> OrderItems;

//	private java.util.Set<Personnel> PersonnelList;
//	private java.util.Set<PersonnelVocationalTraining> PersonnelVocationalTrainingList;
//	private java.util.Set<PersonnelSkillRaising> PersonnelSkillRaisingList;
//	private java.util.Set<PersonnelTransfer> PersonnelTransferList;
//	private java.util.Set<PersonnelLeave> PersonnelLeaveList;
//	private java.util.Set<PersonnelAttestation> PersonnelAttestationList;


  // Constructors

  /**
   * default constructor
   */
  public Order() {
  }

  /**
   * constructor with id
   */
  public Order(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  /**
   *
   * @param Id
   */
  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  /**
   *
   * @param Folder
   */
  public void setFolder(com.mg.merp.core.model.Folder Folder) {
    this.Folder = Folder;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  /**
   *
   * @param SysClient
   */
  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   * @return
   */
  public java.lang.String getOrderNumber() {
    return this.OrderNumber;
  }

  /**
   *
   * @param OrderNumber
   */
  public void setOrderNumber(java.lang.String OrderNumber) {
    this.OrderNumber = OrderNumber;
  }

  /**
   *
   * @return
   */
  public java.util.Date getOrderDate() {
    return this.OrderDate;
  }

  /**
   *
   * @param OrderDate
   */
  public void setOrderDate(java.util.Date OrderDate) {
    this.OrderDate = OrderDate;
  }

  /**
   *
   * @return
   */
  public java.lang.String getHeader() {
    return this.Header;
  }

  /**
   *
   * @param Header
   */
  public void setHeader(java.lang.String Header) {
    this.Header = Header;
  }

  /**
   *
   * @return
   */
  public java.lang.String getFooter() {
    return this.Footer;
  }

  /**
   *
   * @param Footer
   */
  public void setFooter(java.lang.String Footer) {
    this.Footer = Footer;
  }

  /**
   *
   * @return
   */
  public java.lang.Short getOrderStatus() {
    return this.OrderStatus;
  }

  /**
   *
   * @param OrderStatus
   */
  public void setOrderStatus(java.lang.Short OrderStatus) {
    this.OrderStatus = OrderStatus;
  }

  /**
   * @return the orderItems
   */
  public java.util.Set<OrderItem> getOrderItems() {
    return this.OrderItems;
  }

  /**
   * @param orderItems the orderItems to set
   */
  public void setOrderItems(java.util.Set<OrderItem> orderItems) {
    this.OrderItems = orderItems;
  }

//	/**
//	 * @return the personnelAttestationList
//	 */
//	public java.util.Set<PersonnelAttestation> getPersonnelAttestationList() {
//		return this.PersonnelAttestationList;
//	}
//
//	/**
//	 * @param personnelAttestationList the personnelAttestationList to set
//	 */
//	public void setPersonnelAttestationList(java.util.Set<PersonnelAttestation> personnelAttestationList) {
//		this.PersonnelAttestationList = personnelAttestationList;
//	}
//
//	/**
//	 * @return the personnelLeaveList
//	 */
//	public java.util.Set<PersonnelLeave> getPersonnelLeaveList() {
//		return this.PersonnelLeaveList;
//	}
//
//	/**
//	 * @param personnelLeaveList the personnelLeaveList to set
//	 */
//	public void setPersonnelLeaveList(java.util.Set<PersonnelLeave> personnelLeaveList) {
//		this.PersonnelLeaveList = personnelLeaveList;
//	}
//
//	/**
//	 * @return the personnelList
//	 */
//	public java.util.Set<Personnel> getPersonnelList() {
//		return this.PersonnelList;
//	}
//
//	/**
//	 * @param personnelList the personnelList to set
//	 */
//	public void setPersonnelList(java.util.Set<Personnel> personnelList) {
//		this.PersonnelList = personnelList;
//	}
//
//	/**
//	 * @return the personnelSkillRaisingList
//	 */
//	public java.util.Set<PersonnelSkillRaising> getPersonnelSkillRaisingList() {
//		return this.PersonnelSkillRaisingList;
//	}
//
//	/**
//	 * @param personnelSkillRaisingList the personnelSkillRaisingList to set
//	 */
//	public void setPersonnelSkillRaisingList(java.util.Set<PersonnelSkillRaising> personnelSkillRaisingList) {
//		this.PersonnelSkillRaisingList = personnelSkillRaisingList;
//	}
//
//	/**
//	 * @return the personnelTransferList
//	 */
//	public java.util.Set<PersonnelTransfer> getPersonnelTransferList() {
//		return this.PersonnelTransferList;
//	}
//
//	/**
//	 * @param personnelTransferList the personnelTransferList to set
//	 */
//	public void setPersonnelTransferList(java.util.Set<PersonnelTransfer> personnelTransferList) {
//		this.PersonnelTransferList = personnelTransferList;
//	}
//
//	/**
//	 * @return the personnelVocationalTrainingList
//	 */
//	public java.util.Set<PersonnelVocationalTraining> getPersonnelVocationalTrainingList() {
//		return this.PersonnelVocationalTrainingList;
//	}
//
//	/**
//	 * @param personnelVocationalTrainingList the personnelVocationalTrainingList to set
//	 */
//	public void setPersonnelVocationalTrainingList(java.util.Set<PersonnelVocationalTraining> personnelVocationalTrainingList) {
//		this.PersonnelVocationalTrainingList = personnelVocationalTrainingList;
//	}

}