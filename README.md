# Millenium BSA
Originally Millennium Business Suite Anywhere (BSA) is web based ERP/CRM solution with integrated BPM. Millennium BSA automates resources' planning (MRPII), distribution, inventory, payroll, HR, purchase, sales. Millennium BSA is distributed under GPL V2.

This project was born in deep Russian forests, so most developers were the bears, worked for vodka(tm)

In two words it's open source ERP/CRM platform based on most advanced java technologies.
It's Java 5, JBoss 4, hibernate 3, BIRT 2, ANT, eclipse of some non-planet version etc. Yea, most advanced technologies for 2009 :D

Anyway it's still much better by it's functionality, performance, ideology and visual part against lot of existing platforms.

Pls visit http://www.m-g.ru/en to get more information
or just http://www.m-g.ru/ and translate with chrome(I know you did it with taobao), as russian version more informative.

This project is a try to give a new life for this useful platform.

My/our goals are:
1.  Mavenization or gradlenization (you know, it's from gradle) and electrification of the whole country
2.  JDK upgrade to the newest stable, let's say 9
3.  Check english resources are ok, cos now only russian i18n resources are 100% valid
4.  I would plan to make Postgresql as default DB, now it's.... it's firebird aaaaahahahhah (well 10 years ago it was cool enough)
But actually I want it to be DB-agnostic.
5.  Currently DB versioning works via custom tool, it must be definitely liquibase
6.  Hiberante models still hbm.xml, I want annotations
7.  Reimplement some core functionality to allow exectute application at any JEE server. Now it's tightly bundled with old great jboss 4
8.  Translate the documentation. I plan to support both russian and english versions
9.  Probably spring... EJB may be optional service layer implementation, but with spring it may run with any suitable container
10. OSGi. Currently modular system is self-made, so it's not ideal
11. .... eh, enough for now

let's see, how it's possible