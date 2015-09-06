/*
 * AddressTest.java
 *
 * Copyright (c) 1BuildingNumberBuildingNumberHouseNumber - 200HouseNumber BusinessTechnology, Ltd.
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
package com.mg.merp.test.reference;

import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.District;
import com.mg.merp.reference.model.Place;
import com.mg.merp.reference.model.Region;
import com.mg.merp.reference.model.ZipCode;
import com.mg.merp.reference.support.CountryServiceBean;

import org.junit.Assert;
import org.junit.Test;

/**
 * Набор тестов для формирования строки адреса
 *
 * @author Artem V. Sharapov
 * @version $Id: AddressTest.java,v 1.1 2008/09/22 06:00:51 sharapov Exp $
 */
public class AddressTest {

  CountryServiceBean countryServiceBean = new CountryServiceBean();
  Country country = new Country();
  ZipCode zipcode = new ZipCode();
  Region region = new Region();
  District district = new District();
  Place place = new Place();
  String street = null;
  String house = null;
  String building = null;
  String room = null;

  String actualAddressText = null;

  public void initTestData() {
    country.setCName("CountryName");
    country.setAddressDlm("[, ]");
    country.setAddressRule("[zipcode]|[country]|[region_prefix][region]|[district_prefix][district]|[place_prefix][place]|[street]|h.[house]|corp.[building]|[room]");

    zipcode.setCode("ZipCode");

    region.setPrefix("RegionPrefix");
    region.setName("RegionName");

    district.setPrefix("DistrictPrefix");
    district.setName("DistrictName");

    place.setPrefix("PlacePrefix");
    place.setName("PlaceName");

    street = "StreetName";
    house = "HouseNumber";
    building = "BuildingNumber";
    room = "RoomNumber";
  }

  @Test
  public void fullAddressTest() throws Exception {
    initTestData();

    actualAddressText = countryServiceBean.getAddressText(country, region, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("ZipCode, CountryName, RegionPrefixRegionName, DistrictPrefixDistrictName, PlacePrefixPlaceName, StreetName, h.HouseNumber, corp.BuildingNumber, RoomNumber", actualAddressText);
  }

  @Test
  public void patternPrefixIsMissedTest() throws Exception {
    initTestData();
    country.setAddressRule("[zipcode]|[country]|[region]|[district]|[place_prefix][place]|[street]|h.[house]|corp.[building]|[room]");

    actualAddressText = countryServiceBean.getAddressText(country, region, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("ZipCode, CountryName, RegionName, DistrictName, PlacePrefixPlaceName, StreetName, h.HouseNumber, corp.BuildingNumber, RoomNumber", actualAddressText);
  }

  @Test
  public void countryIsNullTest() throws Exception {
    initTestData();
    actualAddressText = countryServiceBean.getAddressText(null, region, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("", actualAddressText);
  }

  @Test
  public void regionIsNullTest() throws Exception {
    initTestData();
    actualAddressText = countryServiceBean.getAddressText(country, null, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("ZipCode, CountryName, DistrictPrefixDistrictName, PlacePrefixPlaceName, StreetName, h.HouseNumber, corp.BuildingNumber, RoomNumber", actualAddressText);
  }

  @Test
  public void allIsNullTest() throws Exception {
    initTestData();
    actualAddressText = countryServiceBean.getAddressText(country, null, null, null, null, null, null, null, null);
    Assert.assertEquals("CountryName", actualAddressText);
  }

  @Test
  public void addressDelimetrIsNullTest() throws Exception {
    initTestData();
    country.setAddressDlm(null);

    actualAddressText = countryServiceBean.getAddressText(country, region, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("ZipCodeCountryNameRegionPrefixRegionNameDistrictPrefixDistrictNamePlacePrefixPlaceNameStreetNameh.HouseNumbercorp.BuildingNumberRoomNumber", actualAddressText);
  }

  @Test
  public void shotPatternTest() throws Exception {
    initTestData();
    country.setAddressRule("[zipcode]|[country]");

    actualAddressText = countryServiceBean.getAddressText(country, region, district, place, street, house, building, room, zipcode);
    Assert.assertEquals("ZipCode, CountryName", actualAddressText);
  }

}
