<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>

<!-- $Id: inst.xsl,v 1.31 2008/07/16 15:18:42 safonov Exp $ -->

<!--
    Millennium BSA installation file.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/*">
    <installation version="1.0">
    
	<!-- 
	    The info section.
	-->
	<info>
	    <appname>Millennium Business Suite Anywhere 2007</appname>
	    <appversion><xsl:value-of select="version"/></appversion>
	    <authors>
		<author name="BusinessTechnology, Ltd." email="info@m-g.ru"/>
	    </authors>
	    <url>http://www.m-g.ru/</url>
	</info>
	
	<!-- 
	    The gui preferences indication.
	-->
	<guiprefs width="600" height="400" resizable="no">
      <laf name="looks">
        <os family="windows" />
        <param name="variant" value="plastic3D" />
      </laf>
    </guiprefs>  
	<!-- 
	    The locale section.
	-->
	<locale>
        <langpack iso3="rus"/>
	    <langpack iso3="eng"/>
	</locale>
	
	<!-- 
	    The resources section.
	-->
	<resources>
	    <res id="HTMLLicencePanel.licence" src="./common/license.htm"/>
	    <res id="TargetPanel.dir.windows" src="./windows/TargetPanel.dir"/>
	    <res id="TargetPanel.dir.unix" src="./linux/TargetPanel.dir"/>
	    <res id="packsLang.xml_rus" src="./packsLang.xml_rus"/>
	    <res id="packsLang.xml_eng" src="./packsLang.xml_eng"/>
	</resources>
	
	<!-- 
	    The panels section.
	-->
	<panels>
	    <panel classname="HelloPanel"/>
	    <panel classname="HTMLLicencePanel"/>
	    <panel classname="TargetPanel"/> 
	    <panel classname="PacksPanel"/>
	    <panel classname="InstallPanel"/>
	    <panel classname="SimpleFinishPanel"/>
	</panels>
	
	<!-- 
	    The variables section.
	-->
	<variables>
	</variables>
	
	<!-- 
	    The packs section.
	-->
	<packs>
	    <pack name="AppServer" id="appserver.package" required="no">
		<description>AppServer</description>
        <file src="./common/install.htm" targetdir="$INSTALL_PATH"/>
        <file src="./common/readme.htm" targetdir="$INSTALL_PATH"/>
        <parsable targetfile="$INSTALL_PATH/readme.htm" type="plain" />
        <file src="./common/releasenotes-4.0.htm" targetdir="$INSTALL_PATH"/>
		<file src="./common/license.htm" targetdir="$INSTALL_PATH"/>
		
		<!-- JBoss -->
		<file src="./common/output/appserver/bin" targetdir="$INSTALL_PATH/appserver" override="true"/>
		<file src="./common/output/appserver/client" targetdir="$INSTALL_PATH/appserver" override="true"/>
		<file src="./common/output/appserver/docs" targetdir="$INSTALL_PATH/appserver" override="true"/>
		<file src="./common/output/appserver/lib" targetdir="$INSTALL_PATH/appserver" override="true"/>
		<fileset dir="./common/output/appserver/server" 
		         targetdir="$INSTALL_PATH/appserver/server" 
			 excludes="**/merp-ds.xml"
			 override="true"/>
		<file src="./common/output/appserver/server/merp/deploy/merp-ds.xml" targetdir="$INSTALL_PATH/appserver/server/merp/deploy" 
		      override="false"/>
		
		<file src="./common/output/version" targetdir="$INSTALL_PATH/appserver/server/merp/mg-custom" override="true"/>

		<fileset dir="./common/db/scripts" 
			 targetdir="$INSTALL_PATH/appserver/server/merp/mg-custom/db/scripts"
			 includes="**/*.sql,**/*.sqlo"
			 override="true"/>
		<fileset dir="./common/conf" targetdir="$INSTALL_PATH/appserver/server/merp/mg-custom/conf"
			 override="true"/>
		<fileset dir="./common/patches" targetdir="$INSTALL_PATH/appserver/server/merp/mg-custom/patches"
			 override="true"/>
		<fileset dir="./common/languages" targetdir="$INSTALL_PATH/appserver/server/merp/mg-custom/languages"
			 override="true"/>
		
		<!-- Start-stop scripts -->
		<file src="./windows/StartServer.bat" targetdir="$INSTALL_PATH/appserver" override="true" 
		      os="windows"/>
		<parsable targetfile="$INSTALL_PATH/appserver/StartServer.bat" type="plain"
		      os="windows"/>
		<file src="./windows/StopServer.bat" targetdir="$INSTALL_PATH/appserver" override="true" 
		      os="windows"/>
		<parsable targetfile="$INSTALL_PATH/appserver/StopServer.bat" type="plain"
		      os="windows"/>
		<file src="./linux/startserver.sh" targetdir="$INSTALL_PATH/appserver" override="true" 
		      os="unix"/>
		<parsable targetfile="$INSTALL_PATH/appserver/startserver.sh" type="plain"
		      os="unix"/>
		<file src="./linux/stopserver.sh" targetdir="$INSTALL_PATH/appserver" override="true" 
		      os="unix"/>
		<parsable targetfile="$INSTALL_PATH/appserver/stopserver.sh" type="plain"
		      os="unix"/>
		<executable targetfile="$INSTALL_PATH/appserver/startserver.sh" stage="never" os="unix" />
		<executable targetfile="$INSTALL_PATH/appserver/stopserver.sh" stage="never" os="unix" />
		<executable targetfile="$INSTALL_PATH/appserver/bin/run.sh" stage="never" os="unix" />
		<executable targetfile="$INSTALL_PATH/appserver/bin/shutdown.sh" stage="never" os="unix" />
	    </pack>
	    
	    <pack name="AppServConsole" id="console.package" required="no">
	      <description>AppServConsole</description>
		<file src="./common/output/tools/console" targetdir="$INSTALL_PATH/tools"/>
		<file src="./common/output/appserver/client/jbossall-client.jar" targetdir="$INSTALL_PATH/tools/lib"/>
		<file src="./common/output/appserver/client/log4j.jar" targetdir="$INSTALL_PATH/tools/lib"/>
		<file src="./common/output/appserver/client/getopt.jar" targetdir="$INSTALL_PATH/tools/lib"/>
		<file src="./windows/StartConsole.bat" targetdir="$INSTALL_PATH/tools/console"
		      os="windows"/>
		<file src="./linux/startconsole.sh" targetdir="$INSTALL_PATH/tools/console"
		      os="unix"/>
		<executable targetfile="$INSTALL_PATH/tools/console/startconsole.sh" stage="never" os="unix" />
		<parsable targetfile="$INSTALL_PATH/tools/console/jndi.properties" type="javaprop" />
	    </pack>
         
	    <pack name="Database" id="db.package" required="no">
		<description>Database</description>
		<file src="./common/db/MERP4.FDB" targetdir="$INSTALL_PATH/appserver/server/merp/data/firebird"/>
		<file src="./common/db/MERP4.FBK" targetdir="$INSTALL_PATH/appserver/server/merp/data/firebird"/>
	    </pack>

	    <pack name="Docs" id="docs.package" required="no">
		<description>Docs</description>
		<file src="./common/output/doc" targetdir="$INSTALL_PATH"/>
		<file src="./common/output/legal" targetdir="$INSTALL_PATH"/>
	    </pack>

	    <pack name="Firebird" id="fb.package" required="no">
		<description>Firebird</description>
		<file src="./common/firebird" targetdir="$INSTALL_PATH/firebird"/>
		<file src="./common/output/udf" targetdir="$INSTALL_PATH/firebird"/>
	    </pack>
	    
	     <!--pack name="Developer Studio" id="devs.package" required="no">
		<description>Developer Studio</description>
		<file src="./windows/{mdevstudio}" targetdir="$INSTALL_PATH/mdevstudio" unpack="true" />
	    </pack-->
	</packs>
	
    </installation>
  </xsl:template>    
</xsl:stylesheet>
