/*
 * AddressMt.java
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
package com.mg.merp.personnelref.support.ui;

import com.mg.framework.api.ui.SearchHelp;
import com.mg.framework.api.ui.SearchHelpEvent;
import com.mg.framework.api.ui.SearchHelpListener;
import com.mg.framework.api.ui.WidgetEvent;
import com.mg.framework.generic.ui.DefaultMaintenanceForm;
import com.mg.framework.support.metadata.SearchHelpProcessor;
import com.mg.merp.personnelref.AddressServiceLocal;
import com.mg.merp.personnelref.KladrServiceLocal;
import com.mg.merp.personnelref.model.Abbreviation;
import com.mg.merp.personnelref.model.Address;
import com.mg.merp.personnelref.model.Kladr;
import com.mg.merp.personnelref.model.Street;
import com.mg.merp.reference.model.Country;

/**
 * Контроллер формы поддержки "Адрес проживания сотрудников"
 * 
 * @author Artem V. Sharapov
 * @version $Id: AddressMt.java,v 1.1 2007/07/16 13:22:45 sharapov Exp $
 */
public class AddressMt extends DefaultMaintenanceForm {

	private final String COUNTRY_SEARCH_HELP_NAME = "com.mg.merp.reference.support.ui.CountrySearchHelp"; //$NON-NLS-1$
	private final String STREET_SEARCH_HELP_NAME = "com.mg.merp.personnelref.support.ui.StreetSearchHelp"; //$NON-NLS-1$
	private final String ABBREVIATION_SEARCH_HELP_NAME = "com.mg.merp.personnelref.support.ui.AbbreviationSearchHelp"; //$NON-NLS-1$
	private final String KLADR_SEARCH_HELP_NAME = "com.mg.merp.personnelref.support.ui.KladrSearchHelp"; //$NON-NLS-1$

	private final String CHOOSE_REGION_TYPE_WIDGET = "chooseRegionType"; //$NON-NLS-1$
	private final String CHOOSE_DISTRICT_TYPE_WIDGET = "chooseDistrictType"; //$NON-NLS-1$
	private final String CHOOSE_CITY_TYPE_WIDGET = "chooseCityType"; //$NON-NLS-1$
	private final String CHOOSE_AREA_TYPE_WIDGET = "chooseAreaType"; //$NON-NLS-1$
	private final String CHOOSE_STREET_TYPE_WIDGET = "chooseStreetType"; //$NON-NLS-1$

	private final String CHOOSE_REGION_WIDGET = "chooseRegion"; //$NON-NLS-1$
	private final String CHOOSE_DISTRICT_WIDGET = "chooseDistrict"; //$NON-NLS-1$
	private final String CHOOSE_CITY_WIDGET = "chooseCity"; //$NON-NLS-1$
	private final String CHOOSE_AREA_WIDGET = "chooseArea"; //$NON-NLS-1$

	private String regionCode;
	private String districtCode; 
	private String cityCode;
	private String areaCode;
	private String streetCode;


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultMaintenanceForm#doOnRun()
	 */
	@Override
	protected void doOnRun() {
		initializeCodes();
		super.doOnRun();
	}

	private void initializeCodes() {
		String kladrCode = ((Address) getEntity()).getKladrCode();
		if(kladrCode == null)
			kladrCode = KladrServiceLocal.INIT_KLADR_CODE;

		regionCode = getRegionCode(kladrCode);
		districtCode = getDistrictCode(kladrCode);
		cityCode = getCityCode(kladrCode);
		areaCode = getAreaCode(kladrCode);
		streetCode = getStreetCode(kladrCode);

		((Address) getEntity()).setKladrCode(kladrCode);
	}

	/**
	 * Обработчик кнопки "Выбрать страну"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseCountry(WidgetEvent event) throws Exception {
		SearchHelp searchHelp = SearchHelpProcessor.createSearch(COUNTRY_SEARCH_HELP_NAME);
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				((Address) getEntity()).setCountry(((Country) event.getItems()[0]).getCCode());
				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.search();
	}

	/**
	 * Обработчик кнопки "Выбрать улицу"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseStreet(WidgetEvent event) throws Exception {
		StreetSearchHelp searchHelp = (StreetSearchHelp) SearchHelpProcessor.createSearch(STREET_SEARCH_HELP_NAME);
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Street street = (Street) event.getItems()[0];

				regionCode = getRegionCode(street.getKCode());
				districtCode = getDistrictCode(street.getKCode());
				cityCode = getCityCode(street.getKCode());
				areaCode = getAreaCode(street.getKCode());
				streetCode = getStreetCode(street.getKCode());

				((Address) getEntity()).setStreet(street.getSName());
				((Address) getEntity()).setStreetType(street.getKType());
				((Address) getEntity()).setPostIndex(street.getPostIndex());

				refreshKladrCode();
				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.setSearchParams(KladrServiceLocal.STREET_LEVEL, regionCode, districtCode, cityCode, areaCode);
		searchHelp.search();
	}

	/**
	 * Обработчик кнопки "Выбрать сокращенное наименование улицы"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseStreetType(final WidgetEvent event) throws Exception {
		searchAndSetAddressElementAbbreviation(KladrServiceLocal.STREET_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать регион"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseRegion(final WidgetEvent event) throws Exception {
		searchAndSetAddressElement(KladrServiceLocal.REGION_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать сокращенное наименование региона"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseRegionType(final WidgetEvent event) throws Exception {
		searchAndSetAddressElementAbbreviation(KladrServiceLocal.REGION_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать район"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseDistrict(final WidgetEvent event) throws Exception {
		searchAndSetAddressElement(KladrServiceLocal.DISTRICT_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать сокращенное наименование района"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseDistrictType(final WidgetEvent event) throws Exception {
		searchAndSetAddressElementAbbreviation(KladrServiceLocal.DISTRICT_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать город"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseCity(final WidgetEvent event) throws Exception {
		searchAndSetAddressElement(KladrServiceLocal.CITY_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать сокращенное наименование города"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseCityType(final WidgetEvent event) throws Exception {
		searchAndSetAddressElementAbbreviation(KladrServiceLocal.CITY_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать населенный пункт"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseArea(final WidgetEvent event) throws Exception {
		searchAndSetAddressElement(KladrServiceLocal.AREA_LEVEL, event.getWidget().getName());
	}

	/**
	 * Обработчик кнопки "Выбрать сокращенное наименование нас.пункта"
	 * @param event - событие
	 * @throws Exception - ИС
	 */
	public void onActionChooseAreaType(final WidgetEvent event) throws Exception {
		searchAndSetAddressElementAbbreviation(KladrServiceLocal.AREA_LEVEL, event.getWidget().getName());
	}
	
	/**
	 * Обработчик кнопки "Очистить наименование страны"
	 * @param event - событие
	 */
	public void onActionClearCountry(WidgetEvent event) {
		clearCountry();
	}

	/**
	 * Обработчик кнопки "Очистить наименование региона"
	 * @param event - событие
	 */
	public void onActionClearRegion(WidgetEvent event) {
		clearRegion();
	}
	
	/**
	 * Обработчик кнопки "Очистить наименование района"
	 * @param event - событие
	 */
	public void onActionClearDistrict(WidgetEvent event) {
		clearDistrict();
	}
	
	/**
	 * Обработчик кнопки "Очистить наименование города"
	 * @param event - событие
	 */
	public void onActionClearCity(WidgetEvent event) {
		clearCity();
	}
	
	/**
	 * Обработчик кнопки "Очистить наименование нас.пункта"
	 * @param event - событие
	 */
	public void onActionClearArea(WidgetEvent event) {
		clearArea();
	}
	
	/**
	 * Обработчик кнопки "Очистить наименование улицы"
	 * @param event - событие
	 */
	public void onActionClearStreet(WidgetEvent event) {
		clearStreet();
	}
	
	/**
	 * Обработчик кнопки "Обновить полный адрес"
	 * @param event - событие
	 */
	public void onActionRefreshFullAddress(WidgetEvent event) {
		((AddressServiceLocal) getService()).adjustFullAddress((Address) getEntity());
		view.flushModel();
	}

	/**
	 * Выбрать и установить сокращенное наименование элемента адреса
	 * @param abbreviationLevel - уровень для поиска сокращения
	 * @param sourceWidgetName - наименование интерфейсного элемента источника адреса
	 * @throws Exception - ИС
	 */
	private void searchAndSetAddressElementAbbreviation(final int abbreviationLevel, final String sourceWidgetName) throws Exception {
		AbbreviationSearchHelp searchHelp = (AbbreviationSearchHelp) SearchHelpProcessor.createSearch(ABBREVIATION_SEARCH_HELP_NAME);
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				if(CHOOSE_REGION_TYPE_WIDGET.compareTo(sourceWidgetName) == 0)
					((Address) getEntity()).setRegionType(((Abbreviation) event.getItems()[0]).getScName());

				if(CHOOSE_DISTRICT_TYPE_WIDGET.compareTo(sourceWidgetName) == 0)
					((Address) getEntity()).setDistrictType(((Abbreviation) event.getItems()[0]).getScName());

				if(CHOOSE_CITY_TYPE_WIDGET.compareTo(sourceWidgetName) == 0)
					((Address) getEntity()).setCityType(((Abbreviation) event.getItems()[0]).getScName());

				if(CHOOSE_AREA_TYPE_WIDGET.compareTo(sourceWidgetName) == 0)
					((Address) getEntity()).setAreaType(((Abbreviation) event.getItems()[0]).getScName());

				if(CHOOSE_STREET_TYPE_WIDGET.compareTo(sourceWidgetName) == 0)
					((Address) getEntity()).setStreetType(((Abbreviation) event.getItems()[0]).getScName());

				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.setAbbreviationLevel(abbreviationLevel);
		searchHelp.search();
	}

	private void searchAndSetAddressElement(final int kladrLevel, final String sourceAddressElementWidgetName) throws Exception {
		KladrSearchHelp searchHelp = (KladrSearchHelp) SearchHelpProcessor.createSearch(KLADR_SEARCH_HELP_NAME);
		searchHelp.addSearchHelpListener(new SearchHelpListener() {

			public void searchPerformed(SearchHelpEvent event) {
				Kladr kladr = ((Kladr) event.getItems()[0]);
				if(CHOOSE_REGION_WIDGET.compareTo(sourceAddressElementWidgetName) == 0) {
					regionCode = getRegionCode(kladr.getKCode());

					((Address) getEntity()).setRegion(regionCode);
					((Address) getEntity()).setRegionType(kladr.getKType());
					((Address) getEntity()).setPostIndex(kladr.getPostIndex());
					clearDistrict();
				}

				if(CHOOSE_DISTRICT_WIDGET.compareTo(sourceAddressElementWidgetName) == 0) {
					regionCode = getRegionCode(kladr.getKCode());
					districtCode = getDistrictCode(kladr.getKCode());

					((Address) getEntity()).setDistrict(kladr.getKName());
					((Address) getEntity()).setDistrictType(kladr.getKType());
					((Address) getEntity()).setPostIndex(kladr.getPostIndex());
					clearCity();
				}

				if(CHOOSE_CITY_WIDGET.compareTo(sourceAddressElementWidgetName) == 0) {
					regionCode = getRegionCode(kladr.getKCode());
					districtCode = getDistrictCode(kladr.getKCode());
					cityCode = getCityCode(kladr.getKCode());

					((Address) getEntity()).setCity(kladr.getKName());
					((Address) getEntity()).setCityType(kladr.getKType());
					((Address) getEntity()).setPostIndex(kladr.getPostIndex());
					clearArea();
				}

				if(CHOOSE_AREA_WIDGET.compareTo(sourceAddressElementWidgetName) == 0) {
					regionCode = getRegionCode(kladr.getKCode());
					districtCode = getDistrictCode(kladr.getKCode());
					cityCode = getCityCode(kladr.getKCode());
					areaCode = getAreaCode(kladr.getKCode());

					((Address) getEntity()).setArea(kladr.getKName());
					((Address) getEntity()).setAreaType(kladr.getKType());
					((Address) getEntity()).setPostIndex(kladr.getPostIndex());
					clearStreet();
				}

				view.flushModel();
			}

			public void searchCanceled(SearchHelpEvent event) {
				// do nothing
			}
		});
		searchHelp.setSearchParams(kladrLevel, regionCode, districtCode, cityCode);
		searchHelp.search();
	}
	
	private void clearCountry() {
		((Address) getEntity()).setCountry(null);
	}
	
	private void clearRegion() {
		((Address) getEntity()).setRegion(null);
		((Address) getEntity()).setRegionType(null);
		regionCode = KladrServiceLocal.INIT_REGION_CODE;
		clearDistrict();
	}

	private void clearDistrict() {
		((Address) getEntity()).setDistrict(null);
		((Address) getEntity()).setDistrictType(null);
		districtCode = KladrServiceLocal.INIT_DISTRICT_CODE;
		clearCity();
	}

	private void clearCity() {
		((Address) getEntity()).setCity(null);
		((Address) getEntity()).setCityType(null);
		cityCode = KladrServiceLocal.INIT_CITY_CODE;
		clearArea();
	}

	private void clearArea() {
		((Address) getEntity()).setArea(null);
		((Address) getEntity()).setAreaType(null);
		areaCode = KladrServiceLocal.INIT_AREA_CODE;
		clearStreet();
	}

	private void clearStreet() {
		((Address) getEntity()).setStreet(null);
		((Address) getEntity()).setStreetType(null);
		streetCode = KladrServiceLocal.INIT_STREET_CODE;
		refreshKladrCode();
	}

	private void refreshKladrCode() {
		((Address) getEntity()).setKladrCode(getKladrCode());
	}

	private String getKladrCode() {
		return new StringBuilder()
		.append(regionCode)
		.append(districtCode)
		.append(cityCode)
		.append(areaCode)
		.append(streetCode)
		.toString();
	}

	private String getRegionCode(String kladrCode) {
		return String.copyValueOf(kladrCode.toCharArray(), 0, 2);
	}

	private String getDistrictCode(String kladrCode) {
		return String.copyValueOf(kladrCode.toCharArray(), 2, 3);
	}

	private String getCityCode(String kladrCode) {
		return String.copyValueOf(kladrCode.toCharArray(), 5, 3);
	}

	private String getAreaCode(String kladrCode) {
		return String.copyValueOf(kladrCode.toCharArray(), 8, 3);
	}

	private String getStreetCode(String kladrCode) {
		return String.copyValueOf(kladrCode.toCharArray(), 11, 4);
	}

}
