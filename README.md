[![Build Status](https://quick.linkpc.net/ci/job/MBSA.Build/badge/icon)](https://quick.linkpc.net/ci/job/MBSA.Build)

---
# Millenium BSA
---
Originally **Millennium Business Suite Anywhere (BSA)** is web based ERP/CRM solution with integrated BPM.

**Millennium BSA** automates resources' planning (MRPII), distribution, inventory, payroll, HR, purchase, sales. Millennium BSA is distributed under GPL V2.

This project was born in deep Russian forests, so most developers were the bears, worked for vodka(tm)

In two words it's open source **ERP**/**CRM** platform based on most advanced java technologies.

It's Java 5, JBoss 4, hibernate 3, BIRT 2, ANT, eclipse of some non-planet version etc. Yea, most advanced technologies for 2009 :D

Client layer is Canoo ULC - so you have not a stupid web interface with it's delays, weird interface, but regular desktop-like client with a small memory footprint, small traffic etc. It's can be executed via JNLP or browser(java plugin required). But I think we can provide some lite interface version via WEB 3.0 and mobiles

Anyway it's still much better by it's functionality, performance, ideology and visual part against lot of existing platforms.

Pls visit http://www.m-g.ru/en to get more information
or just http://www.m-g.ru/ and translate with chrome(I know you did it with taobao), as russian version more informative.

Latest documentation about project and also documentation about modules(but only on russian) you can get on generated site https://quick.linkpc.net/m2/content/sites/com.mg.mbsa/

This project is a try to give a new life for this useful platform.

---
### My/our goals are:
- **DONE**: Mavenization or gradlenization (you know, it's from gradle) and electrification of the whole country - we check Maven and Gradle and think Maven is better.
- **DONE**: JDK upgrade to the newest stable - now it's Java 8
- Check english resources are ok, cos now only russian i18n resources are 100% valid
- I would plan to make Postgresql as default DB, now it's.... it's firebird aaaaahahahhah (well 10 years ago it was cool enough). But actually I want it to be DB-agnostic.
- Currently DB versioning works via custom tool, it must be definitely liquibase
- Hiberante models still hbm.xml, I want annotations
- Re-implement some core functionality to allow execute application at any JEE server. Now it's tightly bundled with old great jboss 4
- Translate the documentation. I plan to support both russian and english versions
- Probably spring... EJB may be optional service layer implementation, but with spring it may run with any suitable container
- OSGi. Currently modular system is self-made, so it's not ideal
- REST services, and probably few more kind of connectors. We should provide easy integration for third parties as mobile clients, or even new web UI
- Lot of work with developer workbench....

Let's see, how it's possible
