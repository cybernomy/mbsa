/*
 * CountryServiceLocal.java
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
package com.mg.merp.reference;

import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.District;
import com.mg.merp.reference.model.Place;
import com.mg.merp.reference.model.Region;
import com.mg.merp.reference.model.ZipCode;

/**
 * @author leonova
 * @version $Id: CountryServiceLocal.java,v 1.2 2007/09/05 10:42:07 alikaev Exp $
 */
public interface CountryServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Country, Integer> {

  final public static String SERVICE_NAME = "merp/reference/Country";

  /**
   * Формирует полный адрес
   *
   * @param country  страна
   * @param region   регион
   * @param district район
   * @param place    населенный пункт
   * @param street   улица
   * @param house    дом
   * @param building строение
   * @param room     помещение
   * @param zipcode  индекс
   * @return полный адрес проживания
   */
  String getAddressText(Country country, Region region, District district, Place place, String street, String house, String building, String room, ZipCode zipcode);

}
