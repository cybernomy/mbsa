/*
 * FieldMetadata.java
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
package com.mg.framework.api.metadata.ui;

import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.metadata.ConversionRoutine;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.Domain;
import com.mg.framework.api.ui.SearchHelp;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * Метаданные поля пользовательского интерфейса, формируется на основании элементов данных связанных
 * с атрибутом сущности или атрибутом контроллера формы
 *
 * @author Oleg V. Safonov
 * @version $Id: FieldMetadata.java,v 1.8 2008/05/29 08:36:00 safonov Exp $
 */
public class FieldMetadata implements Serializable {
  private String name;
  private BuiltInType builtInType;
  private Class<?> javaType;
  private int length;
  private String shortLabel;
  private String mediumLabel;
  private String longLabel;
  private String header;
  private String reportLabel;
  private boolean isLowercase;
  private boolean isMandatory;
  private String[] enumConstantsText = null;
  private SearchHelp searchHelp;
  private String[] entityPropertyText = null;
  private String entityPropertyFormatText = null;
  private boolean isSign = true;
  private int numberOfPlaces;
  private int numberOfDecimalPlaces;
  private boolean isInteger;
  private Number minValue = null;
  private Number maxValue = null;
  @SuppressWarnings("unchecked")
  private ConversionRoutine conversionRoutine = null;
  private boolean isReadOnly;

  public FieldMetadata(DataItem dataItem, Class<?> javaType) {
    Domain d = dataItem.getDomain();

    this.name = dataItem.getName();
    this.builtInType = d.getBuiltInType();
    this.isInteger = EnumSet.of(BuiltInType.SMALLINTEGER, BuiltInType.INTEGER, BuiltInType.BIGINTEGER).contains(this.builtInType);
    this.javaType = javaType;
    this.length = d.getLength();
    this.isLowercase = d.isLowercase();
    this.isMandatory = d.isMandatory();
    this.isReadOnly = dataItem.isReadOnly();
    this.shortLabel = dataItem.getShortLabel();
    this.mediumLabel = dataItem.getMediumLabel();
    this.longLabel = dataItem.getLongLabel();
    this.header = dataItem.getHeader();
    this.reportLabel = dataItem.getReportLabel();
    this.searchHelp = dataItem.getSearchHelp();
    this.entityPropertyText = dataItem.getEntityText().propertyText;
    this.entityPropertyFormatText = dataItem.getEntityText().textFormat;
    this.isSign = d.isSign();
    this.numberOfPlaces = d.getNumberOfPlaces();
    this.numberOfDecimalPlaces = d.getNumberOfDecimalPlaces();
    this.conversionRoutine = d.getConversion();

    setMinMaxValues(this.builtInType);
  }

  public FieldMetadata(String name, BuiltInType builtInType, Class<?> javaType, int length, String shortLabel) {
    super();
    this.name = name;
    this.builtInType = builtInType;
    this.isInteger = EnumSet.of(BuiltInType.SMALLINTEGER, BuiltInType.INTEGER, BuiltInType.BIGINTEGER).contains(this.builtInType);
    this.javaType = javaType;
    this.length = length;
    this.shortLabel = shortLabel;
    this.isLowercase = true;

    setMinMaxValues(this.builtInType);
  }

  private void setMinMaxValues(BuiltInType type) {
    if (builtInType == null)
      return;

    switch (builtInType) {
      case SMALLINTEGER:
        if (isSign)
          minValue = Short.MIN_VALUE;
        else
          minValue = new Short((short) 0);
        maxValue = Short.MAX_VALUE;
        break;
      case INTEGER:
        if (isSign)
          minValue = Integer.MIN_VALUE;
        else
          minValue = new Integer(0);
        maxValue = Integer.MAX_VALUE;
        break;
      case BIGINTEGER:
        if (isSign)
          minValue = Long.MIN_VALUE;
        else
          minValue = new Long(0L);
        maxValue = Long.MAX_VALUE;
        break;
      case FLOAT:
        if (isSign)
          minValue = Float.MIN_VALUE;
        else
          minValue = new Float((float) 0);
        maxValue = Float.MAX_VALUE;
        break;
      case DOUBLE:
        if (isSign)
          minValue = Double.MIN_VALUE;
        else
          minValue = new Double(0F);
        maxValue = Double.MAX_VALUE;
        break;
    }
  }

  /**
   * @return Returns the shortLabel.
   */
  public String getShortLabel() {
    return shortLabel;
  }

  public void setShortLabel(String shortLabel) {
    this.shortLabel = shortLabel;
  }

  /**
   * @return Returns the header.
   */
  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  /**
   * @return Returns the name.
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return Returns the builtInType.
   */
  public BuiltInType getBuiltInType() {
    return builtInType;
  }

  public void setBuiltInType(BuiltInType builtInType) {
    this.builtInType = builtInType;
  }

  /**
   * @return Returns the javaType.
   */
  public Class<?> getJavaType() {
    return javaType;
  }

  public void setJavaType(Class<?> javaType) {
    this.javaType = javaType;
  }

  /**
   * @return Returns the length.
   */
  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  /**
   * @return Returns the isLowercase.
   */
  public boolean isLowercase() {
    return isLowercase;
  }

  public void setLowercase(boolean isLowercase) {
    this.isLowercase = isLowercase;
  }

  /**
   * @return Returns the isMandatory.
   */
  public boolean isMandatory() {
    return isMandatory;
  }

  public void setMandatory(boolean isMandatory) {
    this.isMandatory = isMandatory;
  }

  /**
   * @return Returns the enumConstantsText.
   */
  public String[] getEnumConstantsText() {
    return enumConstantsText;
  }

  /**
   * @param enumConstantsText The enumConstantsText to set.
   */
  public void setEnumConstantsText(String[] enumConstantsText) {
    this.enumConstantsText = enumConstantsText;
  }

  /**
   * @return Returns the searchHelp.
   */
  public SearchHelp getSearchHelp() {
    return searchHelp;
  }

  public void setSearchHelp(SearchHelp searchHelp) {
    this.searchHelp = searchHelp;
  }

  /**
   * @return Returns the entityPropertyText.
   */
  public String[] getEntityPropertyText() {
    return entityPropertyText;
  }

  /**
   * @param entityPropertyText The entityPropertyText to set.
   */
  public void setEntityPropertyText(String[] entityPropertyText) {
    this.entityPropertyText = entityPropertyText;
  }

  /**
   * @return Returns the entityPropertyFormatText.
   */
  public String getEntityPropertyFormatText() {
    return entityPropertyFormatText;
  }

  /**
   * @param entityPropertyFormatText The entityPropertyFormatText to set.
   */
  public void setEntityPropertyFormatText(String entityPropertyFormatText) {
    this.entityPropertyFormatText = entityPropertyFormatText;
  }

  /**
   * @return Returns the isSign.
   */
  public boolean isSign() {
    return isSign;
  }

  public void setSign(boolean isSign) {
    this.isSign = isSign;
  }

  /**
   * @return Returns the numberOfDecimalPlaces.
   */
  public int getNumberOfDecimalPlaces() {
    return numberOfDecimalPlaces;
  }

  public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
    this.numberOfDecimalPlaces = numberOfDecimalPlaces;
  }

  /**
   * @return Returns the numberOfPlaces.
   */
  public int getNumberOfPlaces() {
    return numberOfPlaces;
  }

  public void setNumberOfPlaces(int numberOfPlaces) {
    this.numberOfPlaces = numberOfPlaces;
  }

  /**
   * Is type of field integer
   *
   * @return <b>true</b> if type of field is integer
   */
  public boolean isInteger() {
    return isInteger;
  }

  public void setInteger(boolean isInteger) {
    this.isInteger = isInteger;
  }

  /**
   * Min value
   *
   * @return min value for field if set, otherwise <b>null</b>
   */
  public Number getMinValue() {
    return minValue;
  }

  public void setMinValue(Number minValue) {
    this.minValue = minValue;
  }

  /**
   * Max value
   *
   * @return max value for field if set, otherwise <b>null</b>
   */
  public Number getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Number maxValue) {
    this.maxValue = maxValue;
  }

  /**
   * @return Returns the conversionRoutine.
   */
  @SuppressWarnings("unchecked")
  public ConversionRoutine getConversionRoutine() {
    return conversionRoutine;
  }

  @SuppressWarnings("unchecked")
  public void setConversionRoutine(ConversionRoutine conversionRoutine) {
    this.conversionRoutine = conversionRoutine;
  }

  /**
   * @return the isReadOnly
   */
  public boolean isReadOnly() {
    return isReadOnly;
  }

  public void setReadOnly(boolean isReadOnly) {
    this.isReadOnly = isReadOnly;
  }

  public String getMediumLabel() {
    return mediumLabel;
  }

  public void setMediumLabel(String mediumLabel) {
    this.mediumLabel = mediumLabel;
  }

  public String getLongLabel() {
    return longLabel;
  }

  public void setLongLabel(String longLabel) {
    this.longLabel = longLabel;
  }

  public String getReportLabel() {
    return reportLabel;
  }

  public void setReportLabel(String reportLabel) {
    this.reportLabel = reportLabel;
  }

}
