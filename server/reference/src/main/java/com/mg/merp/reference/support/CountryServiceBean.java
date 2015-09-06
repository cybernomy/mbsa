/*
 * CountryServiceBean.java
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

package com.mg.merp.reference.support;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.reference.CountryServiceLocal;
import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.District;
import com.mg.merp.reference.model.Place;
import com.mg.merp.reference.model.Region;
import com.mg.merp.reference.model.ZipCode;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Страны"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CountryServiceBean.java,v 1.7 2008/09/22 05:57:52 sharapov Exp $
 */
@Stateless(name = "merp/reference/CountryService") //$NON-NLS-1$
public class CountryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Country, Integer> implements CountryServiceLocal {

  public static final String PATTERN_ELEMENT_DELIMETR = "|"; //$NON-NLS-1$
  public static final String ZIP_CODE_PATTERN_ELEMENT = "[zipcode]"; //$NON-NLS-1$
  public static final String COUNTRY_PATTERN_ELEMENT = "[country]"; //$NON-NLS-1$
  public static final String REGION_PATTERN_ELEMENT = "[region]"; //$NON-NLS-1$
  public static final String REGION_PREFIX_PATTERN_ELEMENT = "[region_prefix]"; //$NON-NLS-1$
  public static final String DISTRICT_PATTERN_ELEMENT = "[district]"; //$NON-NLS-1$
  public static final String DISTRICT_PREFIX_PATTERN_ELEMENT = "[district_prefix]"; //$NON-NLS-1$
  public static final String PLACE_PATTERN_ELEMENT = "[place]"; //$NON-NLS-1$
  public static final String PLACE_PREFIX_PATTERN_ELEMENT = "[place_prefix]"; //$NON-NLS-1$
  public static final String STREET_PATTERN_ELEMENT = "[street]"; //$NON-NLS-1$
  public static final String HOUSE_PATTERN_ELEMENT = "[house]"; //$NON-NLS-1$
  public static final String BUILDING_PATTERN_ELEMENT = "[building]"; //$NON-NLS-1$
  public static final String ROOM_PATTERN_ELEMENT = "[room]"; //$NON-NLS-1$


  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
   */
  @Override
  protected void onValidate(ValidationContext context, Country entity) {
    context.addRule(new MandatoryStringAttribute(entity, "CCode")); //$NON-NLS-1$
    context.addRule(new MandatoryAttribute(entity, "CName")); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see com.mg.merp.reference.CountryServiceLocal#getAddressText(com.mg.merp.reference.model.Country, com.mg.merp.reference.model.Region, com.mg.merp.reference.model.District, com.mg.merp.reference.model.Place, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.mg.merp.reference.model.ZipCode)
   */
  @PermitAll
  public String getAddressText(Country country, Region region, District district, Place place, String street, String house, String building, String room, ZipCode zipcode) {
    return doGetAddressText(country, region, district, place, street, house, building, room, zipcode);
  }

  /**
   * Сформировать полный адрес
   *
   * @param country  - страна
   * @param region   - регион
   * @param district - район
   * @param place    - населенный пункт
   * @param street   - улица
   * @param house    - дом
   * @param building - строение
   * @param room     - помещение
   * @param zipcode  - индекс
   * @return полный адрес
   */
  protected String doGetAddressText(Country country, Region region, District district, Place place, String street, String house, String building, String room, ZipCode zipcode) {
    StringBuilder adressText = new StringBuilder(0);
    if (country != null) {
      String addressPattern = country.getAddressRule();
      String addressDelimetr = country.getAddressDlm();
      if (!StringUtils.stringNullOrEmpty(addressDelimetr)) {
        if (addressDelimetr.length() > 2)
          addressDelimetr = addressDelimetr.substring(1, addressDelimetr.length() - 1);
      } else
        addressDelimetr = StringUtils.EMPTY_STRING;

      List<String> patternElements = StringUtils.split(addressPattern, PATTERN_ELEMENT_DELIMETR);
      for (String patternElement : patternElements) {
        if (StringUtils.stringNullOrEmpty(patternElement))
          continue;

        if (patternElement.indexOf(COUNTRY_PATTERN_ELEMENT) != -1) {
          adressText.append(patternElement.replace(COUNTRY_PATTERN_ELEMENT, country.getCName())).append(addressDelimetr);
        } else if (patternElement.indexOf(ZIP_CODE_PATTERN_ELEMENT) != -1 && zipcode != null) {
          adressText.append(patternElement.replace(ZIP_CODE_PATTERN_ELEMENT, zipcode.getCode())).append(addressDelimetr);
        } else if (patternElement.indexOf(REGION_PATTERN_ELEMENT) != -1 && region != null) {
          adressText.append(patternElement.replace(REGION_PATTERN_ELEMENT, region.getName()).replace(REGION_PREFIX_PATTERN_ELEMENT, region.getPrefix())).append(addressDelimetr);
        } else if (patternElement.indexOf(DISTRICT_PATTERN_ELEMENT) != -1 && district != null) {
          adressText.append(patternElement.replace(DISTRICT_PATTERN_ELEMENT, district.getName()).replace(DISTRICT_PREFIX_PATTERN_ELEMENT, district.getPrefix())).append(addressDelimetr);
        } else if (patternElement.indexOf(PLACE_PATTERN_ELEMENT) != -1 && place != null) {
          adressText.append(patternElement.replace(PLACE_PATTERN_ELEMENT, place.getName()).replace(PLACE_PREFIX_PATTERN_ELEMENT, place.getPrefix())).append(addressDelimetr);
        } else if (patternElement.indexOf(STREET_PATTERN_ELEMENT) != -1 && !StringUtils.stringNullOrEmpty(street)) {
          adressText.append(patternElement.replace(STREET_PATTERN_ELEMENT, street)).append(addressDelimetr);
        } else if (patternElement.indexOf(HOUSE_PATTERN_ELEMENT) != -1 && !StringUtils.stringNullOrEmpty(house)) {
          adressText.append(patternElement.replace(HOUSE_PATTERN_ELEMENT, house)).append(addressDelimetr);
        } else if (patternElement.indexOf(BUILDING_PATTERN_ELEMENT) != -1 && !StringUtils.stringNullOrEmpty(building)) {
          adressText.append(patternElement.replace(BUILDING_PATTERN_ELEMENT, building)).append(addressDelimetr);
        } else if (patternElement.indexOf(ROOM_PATTERN_ELEMENT) != -1 && !StringUtils.stringNullOrEmpty(room)) {
          adressText.append(patternElement.replace(ROOM_PATTERN_ELEMENT, room)).append(addressDelimetr);
        }
      }
      adressText.delete(adressText.length() - addressDelimetr.length(), adressText.length());
    }
    return adressText.toString();
  }

}
