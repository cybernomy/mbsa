/*
 * AddressServiceBean.java
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

package com.mg.merp.personnelref.support;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.personnelref.AddressServiceLocal;
import com.mg.merp.personnelref.model.Address;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация бизнес-компонента "Адрес проживания сотрудников"
 *
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: AddressServiceBean.java,v 1.4 2007/07/16 13:18:14 sharapov Exp $
 */
@Stateless(name = "merp/personnelref/AddressService") //$NON-NLS-1$
public class AddressServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Address, Integer> implements AddressServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onCreate(Address entity) {
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
   */
  @Override
  protected void onStore(Address entity) {
    adjustEntity(entity);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.personnelref.AddressServiceLocal#adjustFullAddress(com.mg.merp.personnelref.model.Address)
   */
  @PermitAll
  public void adjustFullAddress(Address address) {
    doAdjustFullAddress(address);
  }

  protected void adjustEntity(Address address) {
    doAdjustFullAddress(address);
    doAdjustNullFields(address);
  }

  protected void doAdjustFullAddress(Address address) {
    StringBuilder fullAddress = new StringBuilder()
        .append(address.getCountry() == null ? StringUtils.EMPTY_STRING : address.getCountry()).append(ADDRESS_SEPARATOR)
        .append(address.getPostIndex() == null ? StringUtils.EMPTY_STRING : address.getPostIndex()).append(ADDRESS_SEPARATOR)
        .append(address.getRegion() == null ? StringUtils.EMPTY_STRING : address.getRegion()).append(ADDRESS_SEPARATOR)
        .append(address.getDistrict() == null ? StringUtils.EMPTY_STRING : address.getDistrict()).append(StringUtils.BLANK_STRING).append(address.getDistrictType() == null ? StringUtils.EMPTY_STRING : address.getDistrictType()).append(ADDRESS_SEPARATOR)
        .append(address.getCity() == null ? StringUtils.EMPTY_STRING : address.getCity()).append(StringUtils.BLANK_STRING).append(address.getCityType() == null ? StringUtils.EMPTY_STRING : address.getCityType()).append(ADDRESS_SEPARATOR)
        .append(address.getArea() == null ? StringUtils.EMPTY_STRING : address.getArea()).append(StringUtils.BLANK_STRING).append(address.getAreaType() == null ? StringUtils.EMPTY_STRING : address.getAreaType()).append(ADDRESS_SEPARATOR)
        .append(address.getStreet() == null ? StringUtils.EMPTY_STRING : address.getStreet()).append(StringUtils.BLANK_STRING).append(address.getStreetType() == null ? StringUtils.EMPTY_STRING : address.getStreetType()).append(ADDRESS_SEPARATOR)
        .append(address.getHouse() == null ? StringUtils.EMPTY_STRING : address.getHouse()).append(ADDRESS_SEPARATOR)
        .append(address.getBlock() == null ? StringUtils.EMPTY_STRING : address.getBlock()).append(ADDRESS_SEPARATOR)
        .append(address.getFlat() == null ? StringUtils.EMPTY_STRING : address.getFlat());

    address.setFullAddress(fullAddress.toString());
  }

  protected void doAdjustNullFields(Address address) {
    if (address.getCountry() == null)
      address.setCountry(StringUtils.EMPTY_STRING);
    if (address.getRegion() == null)
      address.setRegion(StringUtils.EMPTY_STRING);
    if (address.getDistrict() == null)
      address.setDistrict(StringUtils.EMPTY_STRING);
    if (address.getCity() == null)
      address.setCity(StringUtils.EMPTY_STRING);
    if (address.getArea() == null)
      address.setArea(StringUtils.EMPTY_STRING);
    if (address.getStreet() == null)
      address.setStreet(StringUtils.EMPTY_STRING);
  }

}
