<?xml version="1.0" encoding="UTF-8" ?>
<!-- ======================================================================= -->
<!-- Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.                      -->
<!-- All rights reserved                                                     -->
<!--                                                                         -->
<!-- This program is the proprietary and confidential information            -->
<!-- of BusinessTechnology, Ltd. and may be used and disclosed only          -->
<!-- as authorized in a license agreement authorizing and                    -->
<!-- controlling such use and disclosure                                     -->
<!--                                                                         -->
<!-- Millennium Business Suite Anywhere System.                              -->
<!-- ======================================================================= -->

<!-- $Id: overall.hs,v 1.1 2006/12/17 13:25:07 konyashkina Exp $ -->

<helpset version="1.0">

 <title>Учёт спецодежды и инвентаря. Руководство пользователя</title>

  <maps>
    <mapref location="map.xml" />
  </maps>

  <links>
    <linkref location="link.xml"/>
  </links>

  <view>
    <label>Содержание</label>
    <type>oracle.help.navigator.tocNavigator.TOCNavigator</type>
    <data engine="oracle.help.engine.XMLTOCEngine">toc.xml</data>
  </view>

  <view>
    <label>Указатель</label>
    <type>oracle.help.navigator.keywordNavigator.KeywordNavigator</type>
    <title>Учёт спецодежды и инвентаря</title>
    <data engine="oracle.help.engine.XMLIndexEngine">index.xml</data>
  </view>

  <view>
    <label>Поиск</label>
    <title>Учёт спецодежды и инвентаря</title>
    <type>oracle.help.navigator.searchNavigator.SearchNavigator</type>
    <data engine="oracle.help.engine.SearchEngine">fts.idx</data>
  </view>

</helpset>
