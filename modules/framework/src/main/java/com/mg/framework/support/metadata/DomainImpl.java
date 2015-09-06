/*
 * DomainImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.support.metadata;

import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ConversionRoutine;
import com.mg.framework.api.metadata.Domain;
import com.mg.framework.api.metadata.FixedValue;
import com.mg.framework.api.orm.PersistentObject;

import java.util.List;

/**
 * @author Oleg V. Safonov
 * @version $Id: DomainImpl.java,v 1.5 2008/03/03 13:13:18 safonov Exp $
 */
public class DomainImpl implements Domain {
  private Domain model;

  public DomainImpl(Domain model) {
    this.model = model;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getName()
   */
  public String getName() {
    return model.getName();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getDescription()
   */
  public String getDescription() {
    return model.getDescription();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getType()
   */
  public BuiltInType getBuiltInType() {
    return model.getBuiltInType();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getLength()
   */
  public int getLength() {
    return model.getLength();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getNumberOfPlaces()
   */
  public int getNumberOfPlaces() {
    return model.getNumberOfPlaces();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getNumberOfDecimalPlaces()
   */
  public int getNumberOfDecimalPlaces() {
    return model.getNumberOfDecimalPlaces();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getFixedValues()
   */
  public List<FixedValue<?>> getFixedValues() {
    return FixedValuesProcessor.createFixedValues((String) ((PersistentObject) model).getAttribute("FixedValuesImpl"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getDefaultValue()
   */
  public Object getDefaultValue() {
    return DefaultValueProcessor.createDefaultValue((String) ((PersistentObject) model).getAttribute("DefaultValueImpl"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getDocumentation()
   */
  public String getDocumentation() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#isLowercase()
   */
  public boolean isLowercase() {
    return model.isLowercase();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#isNotNull()
   */
  public boolean isMandatory() {
    return model.isMandatory();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#getConversion()
   */
  public ConversionRoutine<?, ?> getConversion() {
    return ConversionProcessor.createConversion((String) ((PersistentObject) model).getAttribute("ConversionRoutine"));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.Domain#isSign()
   */
  public boolean isSign() {
    return model.isSign();
  }

}
