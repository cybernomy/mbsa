/*
 * SysDomain.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.core.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ConversionRoutine;
import com.mg.framework.api.metadata.FixedValue;
import com.mg.framework.service.PersistentObjectHibernate;

import java.util.List;

/**
 * @author Oleg V. Safonov
 * @version $Id: SysDomain.java,v 1.4 2008/03/03 12:56:35 safonov Exp $
 */
@DataItemName("Core.SysDomain")
public class SysDomain extends PersistentObjectHibernate implements java.io.Serializable, com.mg.framework.api.metadata.Domain {
  private Integer id;
  private String name;
  private String description;
  private BuiltInType builtInType;
  private int length;
  private int numberOfPlaces;
  private int numberOfDecimalPlaces;
  private boolean isLowercase;
  private boolean isMandatory;
  private boolean isSign;
  private String conversionRoutine;
  private String fixedValuesImpl;
  private String defaultValueImpl;

  public SysDomain() {
  }

  public SysDomain(Integer id) {
    this.id = id;
  }

  /**
   * @return Returns the builtInType.
   */
  public BuiltInType getBuiltInType() {
    return builtInType;
  }

  /**
   * @param builtInType The builtInType to set.
   */
  public void setBuiltInType(BuiltInType builtInType) {
    this.builtInType = builtInType;
  }

  /**
   * @return Returns the description.
   */
  @DataItemName("Core.Description")
  public String getDescription() {
    return description;
  }

  /**
   * @param description The description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return Returns the id.
   */
  @DataItemName("ID")
  public Integer getId() {
    return id;
  }

  /**
   * @param id The id to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return Returns the isLowercase.
   */
  @DataItemName("Core.SysDomain.LowerCase")
  public boolean isLowercase() {
    return isLowercase;
  }

  /**
   * @param isLowercase The isLowercase to set.
   */
  public void setLowercase(boolean isLowercase) {
    this.isLowercase = isLowercase;
  }

  /**
   * @return Returns the isNotNull.
   */
  @DataItemName("Core.SysDomain.Mandatory")
  public boolean isMandatory() {
    return isMandatory;
  }

  /**
   * @param isMandatory The isNotNull to set.
   */
  public void setMandatory(boolean isMandatory) {
    this.isMandatory = isMandatory;
  }

  /**
   * @return Returns the isSign.
   */
  @DataItemName("Core.SysDomain.Sign")
  public boolean isSign() {
    return isSign;
  }

  /**
   * @param isSign The isSign to set.
   */
  public void setSign(boolean isSign) {
    this.isSign = isSign;
  }

  /**
   * @return Returns the length.
   */
  @DataItemName("Core.SysDomain.Length")
  public int getLength() {
    return length;
  }

  /**
   * @param length The length to set.
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * @return Returns the name.
   */
  @DataItemName("Core.SysDomain.Name")
  public String getName() {
    return name;
  }

  /**
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return Returns the numberOfDecimalPlaces.
   */
  @DataItemName("Core.SysDomain.NumberOfDecimalPlaces")
  public int getNumberOfDecimalPlaces() {
    return numberOfDecimalPlaces;
  }

  /**
   * @param numberOfDecimalPlaces The numberOfDecimalPlaces to set.
   */
  public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
    this.numberOfDecimalPlaces = numberOfDecimalPlaces;
  }

  /**
   * @return Returns the numberOfPlaces.
   */
  @DataItemName("Core.SysDomain.NumberOfPlaces")
  public int getNumberOfPlaces() {
    return numberOfPlaces;
  }

  /**
   * @param numberOfPlaces The numberOfPlaces to set.
   */
  public void setNumberOfPlaces(int numberOfPlaces) {
    this.numberOfPlaces = numberOfPlaces;
  }

  /**
   * @return Returns the conversionRoutine.
   */
  @DataItemName("Core.SysDomain.ConversionRoutine")
  public String getConversionRoutine() {
    return conversionRoutine;
  }

  /**
   * @param conversionRoutine The conversionRoutine to set.
   */
  public void setConversionRoutine(String conversionRoutine) {
    this.conversionRoutine = conversionRoutine;
  }

  /**
   * @return the defaultValueImpl
   */
  @DataItemName("Core.SysDomain.DefaultValueImpl")
  public String getDefaultValueImpl() {
    return defaultValueImpl;
  }

  /**
   * @param defaultValueImpl the defaultValueImpl to set
   */
  public void setDefaultValueImpl(String defaultValueImpl) {
    this.defaultValueImpl = defaultValueImpl;
  }

  /**
   * @return the fixedValuesImpl
   */
  @DataItemName("Core.SysDomain.FixedValueImpl")
  public String getFixedValuesImpl() {
    return fixedValuesImpl;
  }

  /**
   * @param fixedValuesImpl the fixedValuesImpl to set
   */
  public void setFixedValuesImpl(String fixedValuesImpl) {
    this.fixedValuesImpl = fixedValuesImpl;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getFixedValues()
   */
  public List<FixedValue<?>> getFixedValues() {
    throw new UnsupportedOperationException();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getDefaultValue()
   */
  public Object getDefaultValue() {
    throw new UnsupportedOperationException();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getDocumentation()
   */
  public String getDocumentation() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getConversion()
   */
  public ConversionRoutine<?, ?> getConversion() {
    throw new UnsupportedOperationException();
  }

}
